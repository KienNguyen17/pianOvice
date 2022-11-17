package pianOvice;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Key extends Rectangle {

    public Key(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        setFillColor(color);
        setStrokeColor(new Color(0x30808080));
        setStrokeWidth(3);

        //TODO Auto-generated constructor stub
    }
    
    public void OnClick() {
        System.out.println(this);
    }
}
