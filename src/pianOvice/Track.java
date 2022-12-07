package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

/**
 * Create a track, represented on the canvas by a long rectangle that can display 
 * GraphicsText objects of the notes added, while keeping track of the order of the 
 * names and pitches of notes added to it.
 */
public class Track extends GraphicsGroup{
    private List<Note> melody = new ArrayList<>();
    private List<GraphicsText> texts = new ArrayList<>();

    private Rectangle trackDisplay;
    private double textX, textY;
    private boolean active = true;

    private Line cursorDisplay;
    private int cursor;
   
    /**
     * Create a GraphicsGroup that includes a long rectangle and a cursor to keep track
     * of the notes added. The rectangle is placed such that its left edge touches the left
     * edge of the canvas. The cursor is placed at the start of the rectangle.
     * 
     * @param x The x-coordinate of the center of the visible portion of the rectangle 
     * @param y The y-coordinate of the center of the visible portion of the rectangle 
     * @param size The width of the visible portion of the rectangle that fits on the screen
     */
    public Track(double x, double y, double size) {
        super();
        cursor = -1;
        textX = 20;
        textY = y;

        trackDisplay = new Rectangle(x - size / 2, y - size * 0.05, 
                                100000, size * 0.1);
        trackDisplay.setStrokeColor(Color.BLACK);
        trackDisplay.setFillColor(Color.WHITE);
        add(trackDisplay);
        
        cursorDisplay = new Line(x - size / 2 + 16, y - size * 0.05, 
            x - size / 2 + 16, y - size * 0.05 + size * 0.1);
        cursorDisplay.setStrokeWidth(2.5);
        cursorDisplay.setStrokeColor(new Color(0x2D5A80));
        add(cursorDisplay);
    }

    /**
     * Set the fill color of the rectangle that represents the track 
     */
    public void setColor(Color color) {
        trackDisplay.setFillColor(color);
    }

    /**
     * Create a text representation of the note right before the location of the cursor 
     * on the rectangle and add the note to the melody at the corresponding location.
     */
    public void addNote(Note note) {
        GraphicsText noteDisplay = new GraphicsText();
        noteDisplay.setText(note.getName());
        noteDisplay.setFontSize(trackDisplay.getHeight() * 0.5);
        noteDisplay.setFillColor(Color.BLACK);

        noteDisplay.setCenter(textX + noteDisplay.getWidth() / 2, textY);

        melody.add(cursor + 1, note);
        texts.add(cursor + 1, noteDisplay);

        for (int i = cursor + 2; i < texts.size(); i++) {
            GraphicsText text = texts.get(i);
            text.moveBy(noteDisplay.getWidth() + 20, 0);
        }

        add(noteDisplay);
        advanceCursor(true);
    }

    /**
     * Remove the text representation of the note before the cursor and remove the 
     * corresponding note in the melody.
     */
    public void deleteNote() {
        if (cursor > -1) {
            remove(texts.get(cursor));
            melody.remove(cursor);
            for (int i = cursor + 1; i < texts.size(); i++) {
                GraphicsText text = texts.get(i);
                text.moveBy(-texts.get(cursor).getWidth() - 20, 0);
            }
            advanceCursor(false);
            texts.remove(cursor+1);
        }
    }

    /**
     * Remove every GraphicsText object on the rectangle, removing all the note in
     * the melody and moving the cursor back to the beginning.
     */
    public void deleteAll() {
        while (cursor < texts.size()-1) {
            advanceCursor(true);
        }
        while (cursor > -1) {
            deleteNote();
        }
        melody.clear();
        texts.clear();
    }

    /**
     * Create an AudioBuffer object and play the entire melody, each note with the exact 
     * same duration.
     */
    public void playMelody() {
        AudioBuffer buffer = new AudioBuffer(
            Utils.convertSecondsToSamples(melody.size() * Note.NOTE_LENGTH));
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

    /**
     * Move the cursor display one note over, forward if true and backward if false
     */
    public void advanceCursor(boolean isForward) {
        if (isForward && cursor < texts.size() - 1) {
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
