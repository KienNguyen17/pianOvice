package pianOvice;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class PianoKey extends Rectangle {

    private String note;
    private double pitch;

    public PianoKey(double x, double y, double width, double height, Color color, String note, double pitch) {
        super(x, y, width, height);
        setFillColor(color);
        setStrokeColor(new Color(0x30808080));
        setStrokeWidth(3);
        this.note = note;
        this.pitch = pitch;
    }
        
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    // public double amplitudeAt(double t) {
    //     double wavelength = Utils.convertPitchToWavelength(pitch);

    // }
}
