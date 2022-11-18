package pianOvice;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class Keyboard {
    private List<Key> keyList = new ArrayList<>();
    private CanvasWindow canvas;
    private List<String> whiteNotes;
    private List<String> blackNotes;
    private final int NUMBER_OF_WHITE_KEY = 14;
    private final int NUMBER_OF_BLACK_KEY = 24; 

    public Keyboard(CanvasWindow canvas) {
        this.canvas = canvas;
        double startY = canvas.getWidth() / NUMBER_OF_WHITE_KEY *4;
        whiteNotes = List.of("C3", "D3", "E3", "F3", "G3", "A3", "B3", 
            "C4", "D4", "E4", "F4", "G4", "A4", "B4");
        blackNotes = List.of("C#3", "D#3", "F#3", "G#3", "A#3", 
            "C#4", "D#4", "F#4", "G#4", "A#4");
        createWhiteKeys(startY);
        createBlackKeys(startY);
    }

    private void createWhiteKeys(double startY) {
        double whiteKeySize = canvas.getWidth() / NUMBER_OF_WHITE_KEY;
        double x = 0;
        double y = canvas.getHeight() - startY;
        for (int i = 0; i < NUMBER_OF_WHITE_KEY; i++) {
            Key key = new Key(x, y, 
                whiteKeySize, whiteKeySize * 4, Color.WHITE);
            key.setNote(whiteNotes.get(i));
            canvas.add(key);
            keyList.add(key);
            x += whiteKeySize;
        } 
    }

    private void createBlackKeys(double startY) {
        double blackKeySize = canvas.getWidth() / NUMBER_OF_BLACK_KEY;
        double x = 0;
        double y = canvas.getHeight() - startY;
        List<Integer> blackKeyPositions = List.of
            (1, 3, 6, 8, 10, 13, 15, 18, 20, 22);
        int noteTracker = 0;
        for (int i = 0; i < NUMBER_OF_BLACK_KEY; i++) {
            if (blackKeyPositions.contains(i)) {
                Key key = new Key(x, y, 
                    blackKeySize, blackKeySize * 4.5, Color.BLACK);
                key.setNote(blackNotes.get(noteTracker));
                noteTracker += 1;
                canvas.add(key);
                keyList.add(key);
            }
            x += blackKeySize;
        }
    }
}
