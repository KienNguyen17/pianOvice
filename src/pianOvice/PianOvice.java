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
    private Button playButton;
    private Button sampleButton;
    private Button deleteButton;

    public PianOvice() {
        canvas = new CanvasWindow("PianOvice", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(0xB4869F));

        keyboard = new Keyboard();
        keyboard.createKeys(canvas);

        createTrack();
        createPlayButton();
        createSampleButton();
        createDeleteButton();
    }

    private void createTrack() {
        double trackHeight = canvas.getHeight() / 2;
        for (int i = 0 ; i < NUMBER_OF_TRACK ; i++) {
            Track track = new Track(canvas.getWidth() / 2, trackHeight, canvas.getWidth());
            tracks.add(track);
            canvas.add(track);
            createBlock(track, trackHeight);
            createActiveButton(track);
            trackHeight += (-track.getHeight() - 5);
        }
    }

    private void createPlayButton() {
        playButton = new Button("Play"); 
        playButton.setCenter(CANVAS_WIDTH - playButton.getWidth() / 2 - 8, CANVAS_HEIGHT * 0.06);
        canvas.add(playButton);
    }

    private void activatePlayButton(Track track) {
        playButton.onClick(() -> {
            if (track.isActive()) {
                track.playMelody();
            } 
        });
    }

    private void createSampleButton() {
        sampleButton = new Button("Sample"); 
        sampleButton.setCenter(
            CANVAS_WIDTH - sampleButton.getWidth() - playButton.getWidth() / 2 - 8, 
            CANVAS_HEIGHT * 0.06);
        canvas.add(sampleButton);
    }

    private void activateSampleButton() {
        sampleButton.onClick(() -> {
            for (Track track : tracks) {
                track.deleteAll();
            }
            for (Note note: Sample.track1Sample) {
                tracks.get(0).addNote(note);
            }

            for (Note note: Sample.track2Sample) {
                tracks.get(1).addNote(note);
            }
        });
    }

    private void createDeleteButton() {
        deleteButton = new Button("Delete All"); 
        deleteButton.setCenter(
            CANVAS_WIDTH - deleteButton.getWidth() - sampleButton.getWidth() - playButton.getWidth() / 2 - 13, 
            CANVAS_HEIGHT * 0.06);
        canvas.add(deleteButton);
    }

    private void createBlock(Track track, double trackHeight) {
        double blockWidth = 140;
        Rectangle block = new Rectangle(CANVAS_WIDTH - blockWidth, 
            trackHeight, blockWidth,canvas.getWidth()*0.1);
        block.setFillColor(Color.BLACK);
        block.setCenter(CANVAS_WIDTH - blockWidth/2, trackHeight);
        canvas.add(block);
    }

    private void createActiveButton(Track track) {
        activeButton = new Button("active/inactive");
        activeButton.setCenter(CANVAS_WIDTH - activeButton.getWidth() + 44, track.getCenter().getY());
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
        for (Track track: tracks) {
            activatePlayButton(track);
        }

        activateSampleButton();

        deleteButton.onClick(() -> {
            for (Track track: tracks) {
                track.deleteAll();
            }
        });

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
            if (getActiveTrack() != null) {
                if (event.getKey() == Key.DELETE_OR_BACKSPACE) {
                    Track track = getActiveTrack();
                    track.deleteNote();
                }
                if (event.getKey() == Key.SPACE) {
                    Track track = getActiveTrack();
                    track.addNote(new Note("𝄽", -48));
                }
                if (event.getKey() == Key.RIGHT_ARROW) {
                    Track track = getActiveTrack();
                    track.advanceCursor(true);
                } else if (event.getKey() == Key.LEFT_ARROW) {
                    Track track = getActiveTrack();
                    track.advanceCursor(false);
                } 
            }
        });
    }

    public static void main(String[] args) {
        new PianOvice().run();
    }
}
