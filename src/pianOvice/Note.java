package pianOvice;

/**
 * A single note with a pitch given in MIDI unit and a name.
 * 
 * Adapted from Homework 5: Audio Synth
 * Github: https://github.com/Mac-COMP-127-Fall-2022/hw5-KienNguyen17
 */
public class Note {
    private String name;
    private AudioBuffer buffer;
    private double pitch;
    public static final double NOTE_LENGTH = 0.3;
    
    /**
     * Create a single note with a name and a pitch given in MIDI unit. The duration of every 
     * note is the same. An AudioBuffer is created to sample the note.
     */
    public Note(String name, double pitch) {
        this.name = name;
        this.pitch = pitch;
        buffer = new AudioBuffer(Utils.convertSecondsToSamples(NOTE_LENGTH));
        buffer.fill(this);
    }

    public String getName() {
        return name;
    }

    /**
     * Return the amplitude of the Square Waveform of the note 
     * at the given time in seconds
     */
    public double amplitudeAt(double t) {
        double wavelength = Utils.convertPitchToWavelength(pitch);
        return (Math.round(t / wavelength % 1) - 0.5) * 0.7;
    }

    public void makeSound() {
        buffer.play();
    }
}
