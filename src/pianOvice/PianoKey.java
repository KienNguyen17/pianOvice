package pianOvice;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class PianoKey extends Rectangle {

    private String note;
    private double pitch;
    private AudioBuffer buffer;

    public PianoKey(double x, double y, double width, double height, Color color, String note, double pitch) {
        super(x, y, width, height);
        setFillColor(color);
        setStrokeColor(new Color(0x30808080));
        setStrokeWidth(3);
        this.note = note;
        this.pitch = pitch;
        buffer = new AudioBuffer(Utils.convertSecondsToSamples(1));
        buffer.mix(this, 0, Utils.convertSecondsToSamples(1));
        buffer.normalize();
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

    public double amplitudeAt(double t) {
        double wavelength = Utils.convertPitchToWavelength(pitch);
        return (Math.round(t / wavelength % 1) - 0.5) * 0.7;
    }

    public void makeSound() {
        buffer.play();
    }
}
