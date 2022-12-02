package pianOvice;

public class Note {
    private String name;
    private AudioBuffer buffer;
    private double pitch;
    public static final double NOTE_LENGTH = 0.3;
    
    public Note(String name, double pitch) {
        this.name = name;
        this.pitch = pitch;
        buffer = new AudioBuffer(Utils.convertSecondsToSamples(NOTE_LENGTH));
        buffer.fill(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String note) {
        this.name = note;
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
