package pianOvice;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class Keyboard {
    private List<Key> keyList = new ArrayList<>();
    private final int NUMBER_OF_KEY = 14;

    public Keyboard(CanvasWindow canvas) {
        double keySize = canvas.getWidth()/NUMBER_OF_KEY;
        double x = 0;
        double y = canvas.getHeight()-keySize*8;
        for (int i=0;i<NUMBER_OF_KEY;i++) {
            Key key = new Key(x, y, keySize, Color.WHITE);
            canvas.add(key);
            keyList.add(key);
            x += keySize;
        }
    }
}
