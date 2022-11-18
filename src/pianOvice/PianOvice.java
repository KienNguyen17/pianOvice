package pianOvice;

import edu.macalester.graphics.*;

public class PianOvice {
    private CanvasWindow canvas;

    public PianOvice() {
        canvas = new CanvasWindow("PianOvice", 840, 600);
        Keyboard keyboard = new Keyboard(canvas);
        Track track = new Track(canvas.getWidth()/2, canvas.getHeight()/2, canvas);
        canvas.onDrag(event -> track.moveBy(event.getDelta()));
    }

    public void run() {
        canvas.onMouseDown((event) -> {
            GraphicsObject object = canvas.getElementAt(event.getPosition());
            if (object instanceof Key key) {
                key.OnClick();
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
