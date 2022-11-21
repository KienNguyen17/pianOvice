package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

public class Track extends GraphicsGroup{
    private List<String> melody = new ArrayList<>();
    private List<GraphicsText> texts = new ArrayList<>();
    private double textX, textY;
    private Rectangle trackDisplay;

    public Track(double x, double y, double size) {
        super();
        textX = 20;
        textY = y;
        trackDisplay = new Rectangle(x - size / 2, y - size * 0.05, 
                                100000, size * 0.1);
        trackDisplay.setStrokeColor(Color.BLACK);
        add(trackDisplay);
    }

    public void addNote(String note) {
        GraphicsText noteDisplay = new GraphicsText(note);
        noteDisplay.setFontSize(trackDisplay.getHeight() * 0.5);
        noteDisplay.setFillColor(Color.BLACK);

        textX += noteDisplay.getWidth() / 2;
        noteDisplay.setCenter(textX, textY);
        textX += noteDisplay.getWidth() / 2 + 20;

        add(noteDisplay);
        melody.add(note);
        texts.add(noteDisplay);
    }

    public void deleteNote() {
        remove(texts.get(texts.size() - 1));
        melody.remove(melody.size() - 1);
        texts.remove(texts.size() - 1);
    }
}
