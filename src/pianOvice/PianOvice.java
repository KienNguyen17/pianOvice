package pianOvice;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.events.Key;
import edu.macalester.graphics.ui.Button;

public class PianOvice {
    private CanvasWindow canvas;
    private final int CANVAS_WIDTH = 840;
    private final int CANVAS_HEIGHT = 600;
    private List<Track> tracks = new ArrayList<>();
    private final int NUMBER_OF_TRACK = 3;
    private Keyboard keyboard;
    private Button activeButton;

    public PianOvice() {
        canvas = new CanvasWindow("PianOvice", CANVAS_WIDTH, CANVAS_HEIGHT);
        keyboard = new Keyboard(canvas);
        double trackHeight = canvas.getHeight() / 2;
        for (int i=0 ; i<NUMBER_OF_TRACK ; i++) {
            Track track = new Track(canvas.getWidth() / 2, trackHeight, canvas.getWidth());
            tracks.add(track);
            createActiveButton(track);
            trackHeight += (-track.getHeight() - 5);
        }
    }

    private void createActiveButton(Track track) {
        activeButton = new Button("active");
        activeButton.setCenter(CANVAS_WIDTH - activeButton.getWidth(), track.getCenter().getY());
        activeButton.onClick(() -> {
            track.setActive(!track.isActive());
            track.setColor(track.isActive() ? Color.WHITE : Color.DARK_GRAY);
        });
        canvas.add(activeButton);
    }

    private Track getActiveTrack() {
        List<Track> activeTracks = new ArrayList<>();
        for (Track track : tracks) {
            if (track.isActive()) {
                activeTracks.add(track);
            }
        }
        if (activeTracks.size() == 1) {
            return activeTracks.get(0);
        } else {
            return null;
        }
    }

    public void run() {
        for (Track track : tracks) {
            canvas.add(track);
        }
    
        canvas.onDrag(event -> {
            if (getActiveTrack() != null) {
                Track track = getActiveTrack();
                if (track.getX() + event.getDelta().getX() < 0 && track.isActive()) {
                    track.moveBy(new Point(event.getDelta().getX(),0));
                }
            }
        }); 

        canvas.onMouseDown((event) -> {
            GraphicsObject object = canvas.getElementAt(event.getPosition());
            if (object instanceof PianoKey key) {
                if (getActiveTrack() != null) {
                    Track track = getActiveTrack();
                    track.addNote(key.getNote());
                }
                key.getNote().makeSound();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.DELETE_OR_BACKSPACE && getActiveTrack() != null) {
                Track track = getActiveTrack();
                track.deleteNote();
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.RETURN_OR_ENTER) {
                if (tracks.get(0).isActive()) {
                    tracks.get(0).playMelody();
                }
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.RETURN_OR_ENTER) {
                if (tracks.get(1).isActive()) {
                    tracks.get(1).playMelody();
                }
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.RETURN_OR_ENTER) {
                if (tracks.get(2).isActive()) {
                    tracks.get(2).playMelody();
                }
            }
        });

        canvas.onKeyDown((event) -> {
            if (event.getKey() == Key.SPACE && getActiveTrack() != null) {
                Track track = getActiveTrack();
                track.addNote(new Note("𝄽", -48));
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
