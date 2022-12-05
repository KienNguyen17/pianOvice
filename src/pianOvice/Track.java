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
        cursorDisplay = new Line(x - size / 2 + 16, y - size * 0.05, 
            x - size / 2 + 16, y - size * 0.05 + size * 0.1);
        cursorDisplay.setStrokeWidth(2.5);
        cursorDisplay.setStrokeColor(Color.WHITE);
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

        noteDisplay.setCenter(textX + noteDisplay.getWidth() / 2, textY);

        melody.add(cursor+1, note);
        texts.add(cursor+1, noteDisplay);

        for (int i = cursor + 2; i<texts.size(); i++) {
            GraphicsText text = texts.get(i);
            text.moveBy(noteDisplay.getWidth() + 20, 0);
        }

        add(noteDisplay);
        advanceCursor(true);
    }

    public void deleteNote() {
        if (cursor > -1) {
            remove(texts.get(cursor));
            melody.remove(cursor);
            for (int i = cursor + 1; i<texts.size(); i++) {
                GraphicsText text = texts.get(i);
                text.moveBy(-texts.get(cursor).getWidth() - 20, 0);
            }
            advanceCursor(false);
            texts.remove(cursor+1);
        }
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

    public void advanceCursor(boolean isForward) {
        if (isForward && cursor < texts.size()-1) {
            cursor += 1;
            cursorDisplay.moveBy(texts.get(cursor).getWidth() + 20, 0);
            textX += texts.get(cursor).getWidth() + 20;
        } else if (!isForward && cursor > -1){
            textX = textX - texts.get(cursor).getWidth() - 20;
            cursorDisplay.moveBy(-texts.get(cursor).getWidth() - 20, 0);
            cursor -= 1;
        }
    }
}
