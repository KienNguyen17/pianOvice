package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;

public class Track extends GraphicsGroup{
    private List<String> melody = new ArrayList<>();
    private Rectangle trackDisplay;

    public Track(double x, double y, CanvasWindow canvas) {
        super();
        trackDisplay = new Rectangle(x, y, canvas.getWidth(), canvas.getWidth()*0.1);
        trackDisplay.setCenter(x, y);
        trackDisplay.setStrokeColor(Color.BLACK);
        add(new GraphicsText("text", x, y));
        add(trackDisplay);
        canvas.add(this);
    }
}
