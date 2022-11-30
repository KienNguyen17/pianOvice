package pianOvice;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class Keyboard {
    private List<PianoKey> keyList = new ArrayList<>();
    private CanvasWindow canvas;
    private List<String> whiteNotes;
    private List<String> blackNotes;
    private final int NUMBER_OF_WHITE_KEY = 14;
    private final int NUMBER_OF_BLACK_KEY = 24; 
    private final int FIRST_PITCH = 48;
    private List<Integer> blackKeyPositions = 
        List.of(1, 3, 6, 8, 10, 13, 15, 18, 20, 22);

    public Keyboard(CanvasWindow canvas) {
        this.canvas = canvas;
        double startY = canvas.getWidth() / NUMBER_OF_WHITE_KEY *4;
        whiteNotes = List.of("C3", "D3", "E3", "F3", "G3", "A3", "B3", 
            "C4", "D4", "E4", "F4", "G4", "A4", "B4");
        blackNotes = List.of("C#3", "D#3", "F#3", "G#3", "A#3", 
            "C#4", "D#4", "F#4", "G#4", "A#4");
        createKeys(startY);
    }

    private void createKeys(double startY) {
        createWhiteKeys(startY);
        createBlackKeys(startY);
    }

    // (0, 2, 4, 5, 7, 9, 11, 12, 14, 16, 17, 19, 21, 23)

    private void createWhiteKeys(double startY) {
        double whiteKeySize = canvas.getWidth() / NUMBER_OF_WHITE_KEY;
        double x = 0;
        double y = canvas.getHeight() - startY;
        int pitchTracker = 0;
        for (int i = 0; i < NUMBER_OF_WHITE_KEY; i++) {
            if (blackKeyPositions.contains(pitchTracker)) {
                pitchTracker += 1;
            }
            PianoKey key = new PianoKey(x, y, 
                whiteKeySize, whiteKeySize * 4, Color.WHITE, whiteNotes.get(i), FIRST_PITCH + pitchTracker);
            canvas.add(key);
            keyList.add(key);
            x += whiteKeySize;
            pitchTracker += 1;
        } 
    }

    private void createBlackKeys(double startY) {
        double blackKeySize = canvas.getWidth() / NUMBER_OF_BLACK_KEY;
        double x = 0;
        double y = canvas.getHeight() - startY;
        int noteTracker = 0;
        for (int i = 0; i < NUMBER_OF_BLACK_KEY; i++) {
            if (blackKeyPositions.contains(i)) {
                PianoKey key = new PianoKey(x, y, 
                    blackKeySize, blackKeySize * 4.5, Color.BLACK, blackNotes.get(noteTracker), FIRST_PITCH + i);
                noteTracker += 1;
                canvas.add(key);
                keyList.add(key);
            }
            x += blackKeySize;
        }
    }
}
