package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

public class Track extends GraphicsGroup{
    private List<String> melody = new ArrayList<>();
    private double textX;
    private double textY; 
    private Rectangle trackDisplay;

    public Track(double x, double y) {
        super();
        textX = 0;
        textY=y;
        trackDisplay = new Rectangle(x, y, 100000, 84);
        trackDisplay.setCenter(x, y);
        trackDisplay.setStrokeColor(Color.BLACK);
        // add(new GraphicsText("text", x, y));
        add(trackDisplay);
    }

    public void addNote(String note) {
        GraphicsText noteDisplay = new GraphicsText();
        noteDisplay.setText(note);
        noteDisplay.setFontSize(40);
        noteDisplay.setFillColor(Color.BLACK);
        textX += noteDisplay.getWidth()/2;
        noteDisplay.setCenter(textX,textY);
        textX += noteDisplay.getWidth()/2 + 20;
        add(noteDisplay);
    }
}
