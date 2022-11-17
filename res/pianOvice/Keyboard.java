package pianOvice;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class Keyboard {
    private List<Key> keyList = new ArrayList<>();
    private final int NUMBER_OF_WHITE_KEY = 14;
    private final int NUMBER_OF_BLACK_KEY = 24; 

    public Keyboard(CanvasWindow canvas) {
        double whiteKeySize = canvas.getWidth() / NUMBER_OF_WHITE_KEY;
        double blackKeySize = canvas.getWidth() / NUMBER_OF_BLACK_KEY;

        double x = 0;
        double y = canvas.getHeight() - whiteKeySize * 4;
        for (int i = 0; i < NUMBER_OF_WHITE_KEY; i++) {
            Key key = new Key(x, y, whiteKeySize, whiteKeySize * 4, Color.WHITE);
            canvas.add(key);
            keyList.add(key);
            x += whiteKeySize;
        } 

        double x2 = 0;
        double y2 = canvas.getHeight() - whiteKeySize * 4;
        List<Integer> num = List.of(1, 3, 6, 8, 10, 13, 15, 18, 20, 22);
        for (int i = 0; i < NUMBER_OF_BLACK_KEY; i++) {
            if (num.contains(i)) {
                Key key = new Key(x2, y2, blackKeySize, blackKeySize * 4.5, Color.BLACK);
                canvas.add(key);
                keyList.add(key);
            }
            x2 += blackKeySize;
        }
    }
}
