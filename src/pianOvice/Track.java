package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

public class Track extends GraphicsGroup{
    private List<Note> melody = new ArrayList<>();
    private List<GraphicsText> texts = new ArrayList<>();
    private double textX, textY;
    private Rectangle trackDisplay;
    private boolean active = true;
    private int cursor;
    private Line cursorDisplay;

    public Track(double x, double y, double size) {
        super();
        cursor = -1;
        textX = 20;
        textY = y;
        trackDisplay = new Rectangle(x - size / 2, y - size * 0.05, 
                                100000, size * 0.1);
        trackDisplay.setStrokeColor(Color.BLACK);
        trackDisplay.setFillColor(new Color(0x2D5A80));
        add(trackDisplay);
        cursorDisplay = new Line(x - size / 2 + 18, y - size * 0.05, 
            x - size / 2 + 18, y - size * 0.05 + size * 0.1);
        cursorDisplay.setStrokeWidth(2);
        add(cursorDisplay);
    }

    public void setColor(Color color) {
        trackDisplay.setFillColor(color);
    }

    public Rectangle getTrackDisplay() {
        return trackDisplay;
    }

    public void addNote(Note note) {
        GraphicsText noteDisplay = new GraphicsText();
        noteDisplay.setText(note.getName());
        noteDisplay.setFontSize(trackDisplay.getHeight() * 0.5);
        noteDisplay.setFillColor(Color.BLACK);

        textX += noteDisplay.getWidth() / 2;
        noteDisplay.setCenter(textX, textY);
        textX += noteDisplay.getWidth() / 2 + 20;

        add(noteDisplay);
        melody.add(note);
        texts.add(noteDisplay);
        advanceCursor(true);
    }

    public void deleteNote() {
        textX = textX - texts.get(texts.size() - 1).getWidth() - 20;
        remove(texts.get(texts.size() - 1));
        melody.remove(melody.size() - 1);
        advanceCursor(false);
        texts.remove(texts.size() - 1);
    }

    public void playMelody() {
        AudioBuffer buffer = new AudioBuffer(
            Utils.convertSecondsToSamples(melody.size()*Note.NOTE_LENGTH));
        int newStart = 0;
        for (Note note : melody) {
            buffer.mix(note, newStart, Utils.convertSecondsToSamples(Note.NOTE_LENGTH));
            newStart += Utils.convertSecondsToSamples(Note.NOTE_LENGTH);
        }
        buffer.play();
     }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void advanceCursor(boolean isForward) {
        if (isForward) {
            cursor += 1;
            cursorDisplay.moveBy(texts.get(cursor).getWidth() + 20, 0);
        } else {
            cursorDisplay.moveBy(-texts.get(cursor).getWidth() - 20, 0);
            cursor -= 1;
        }
    }
}
