package pianOvice;

import edu.macalester.graphics.*;

public class PianOvice {
    private CanvasWindow canvas;
    private Track track;
    private Keyboard keyboard;

    public PianOvice() {
        canvas = new CanvasWindow("PianOvice", 840, 600);
        keyboard = new Keyboard(canvas);
        track = new Track(canvas.getWidth()/2, canvas.getHeight()/2);
    }

    public void run() {
        canvas.add(track);
        canvas.onDrag(event -> track.moveBy(new Point(event.getDelta().getX(),0)));
        canvas.onMouseDown((event) -> {
            GraphicsObject object = canvas.getElementAt(event.getPosition());
            if (object instanceof Key key) {
                track.addNote(key.getNote());
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
