package pianOvice;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Key extends Rectangle {

    public Key(double x, double y, double size, Color color) {
        super(x, y, size, size*8);
        setFillColor(color);
        //TODO Auto-generated constructor stub
    }
    
    public void OnClick() {
        System.out.println(this);
    }
}
