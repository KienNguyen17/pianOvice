package pianOvice;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;

public class PianOvice {
    private CanvasWindow canvas;
    private Track track;
    private Keyboard keyboard;

    public PianOvice() {
        canvas = new CanvasWindow("PianOvice", 840, 600);
        keyboard = new Keyboard(canvas);
        track = new Track(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth());
    }

    public void run() {
        canvas.add(track);
        canvas.onDrag(event -> {
            if (track.getX() + event.getDelta().getX() < 0 ) {
                track.moveBy(new Point(event.getDelta().getX(),0));
            }
        }); 

        canvas.onMouseDown((event) -> {
            GraphicsObject object = canvas.getElementAt(event.getPosition());
            if (object instanceof PianoKey key) {
                track.addNote(key.getNote());
                key.getNote().makeSound();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.DELETE_OR_BACKSPACE) {
                track.deleteNote();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.P) {
                track.playMelody();
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
