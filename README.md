# Project Title: PianOvice
###### Members: Kien Nguyen, Avianna Bui

## Project Description
# Project: PianOvice
###### Members: Kien Nguyen, Avianna Bui

## Project Description
Our project features an interactive piano interface that allows users to compose three separate melodies that can be played simultaneously. Our code requires users to install Java 17 in order to use kilt-graphics, a graphic library developed by the Mathematics, Statistics, and Computer Science (MSCS) department in Macalester College: https://mac-comp127.github.io/kilt-graphics/index.html. It is written to run on Visual Studio Code.

Three classes in the code (AudioBuffer, Notes and Utils) were adapted from Homework 5: AudioSynth for COMP 127 course, also created by Macalester's MSCS department: https://github.com/Mac-COMP-127-Fall-2022/hw5-KienNguyen17 

To run the program, the user should run the main method in the PianOvice class. This is the main class that creates the user interface. In order to use the program, users should click on the piano keys displayed on the screen. It is worth noting that the program only displays the clicked notes on the track and saves them for playing later when there is only one active track. As a result, users should use the Active/Inactive buttons to the right of the track to activate or disable certain tracks before composing. Users can also use the Left and Right Arrow keys on the keyboard to move the cursor along the notes, which is especially helpful should they want to add a rest note (by pressing the Space key) or delete notes (by pressing the Backspace key). If they want to see every composed notes in the track, users can use their mouse to drag the active tracks. 

To play the composed melody, users should set all the tracks they want to play to Active and click the Play button on the top-right corner of the screen. In addition to this, they can load a pre-written melodies onto the tracks using the Sample button, or delete every notes on the tracks through the Delete All button.

## Known issues 
Once played, the user cannot pause or stop the audio. The audio created only spans two octaves (C3 to B4) and the piano graphics cannot be scrolled to display more octaves. All the notes produced are of the same duration and cannot be changed by the user. In addition, the text display of notes are of different sizes, and thus can be slightly difficult for users to follow and compose. The track display can be dragged manually to show more notes, but does not move along with the melody while the user is composing or while the melody is playing. Moreover, the melody’s text display on a track cannot be copied and pasted onto different tracks. We hope to implement these additional features to the program for an upgraded version of the project

## Societal impact
We tried to design the user interface to be as easily navigated as possible. However, it is only useful for people who can hear sounds as our visual display of notes can be confusing, and are based mainly on auditory capabilities. In addition, since we only utilize a basic waveform formula, the sounds that we synthesized can be blaring and cause discomfort to some people. 

Because the program models a piano, the notes are based on Western Music Theory while the display uses Western Music Notations. We acknowledge that this Eurocentric design can be exclusive of other musical traditions and demographics of composers. Finally, although we have implemented many features that allows for keyboard interaction, the main composing feature still requires the use of a mouse. As a result, the program is only practicable to people who use devices that have a mouse while remains inaccessible to those who use other assistive devices.




