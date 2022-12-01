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
            if (track.getX() + event.getDelta().getX() < 0 && track.isActive()) {
                track.moveBy(new Point(event.getDelta().getX(),0));
            }
        }); 

        canvas.onMouseDown((event) -> {
            GraphicsObject object = canvas.getElementAt(event.getPosition());
            if (object instanceof PianoKey key) {
                if (track.isActive()) {
                    track.addNote(key.getNote());
                }
                key.getNote().makeSound();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.DELETE_OR_BACKSPACE && track.isActive()) {
                track.deleteNote();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.RETURN_OR_ENTER && track.isActive()) {
                track.playMelody();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.SPACE && track.isActive()) {
                track.addNote(new Note("𝄽", -48));
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
