package pianOvice;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class PianoKey extends Rectangle {

    private Note note;

    public PianoKey(double x, double y, double width, double height, Color color, String name, double pitch) {
        super(x, y, width, height);
        setFillColor(color);
        setStrokeColor(new Color(0x30808080));
        setStrokeWidth(3);
        this.note = new Note(name, pitch);
    }

    public Note getNote() {
        return note;
    }
}
