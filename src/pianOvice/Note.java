package pianOvice;

public class Note {
    private String note;
    private AudioBuffer buffer;
    private double pitch;
    public static final double NOTE_LENGTH = 0.5;
    
    public Note(String note, double pitch) {
        this.note = note;
        this.pitch = pitch;
        buffer = new AudioBuffer(Utils.convertSecondsToSamples(NOTE_LENGTH));
        buffer.fill(this);
        // buffer.normalize();
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
