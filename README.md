Project Title: PianOvice
Members: Kien Nguyen, Avianna Bui

1. Project description:
    - An interactive piano interface that allows users to compose three separate melodies that can be played simultaneously.
    - Technical guide:
        i. Our code requires Java 17 installed to run, and was written to be run on Visual Studio Code. The program requires an audio output in order to produce audio. The user also needs a keyboard and a mouse to interact with the program. 
        ii. To run the program, the user should run the main method in the PianOvice class. This is the main class that creates the user interface.
        iii. Three classes in the code (AudioBuffer, Notes and Utils) were adapted from Homework 5: AudioSynth for COMP 127 created by the MSCS department at Macalester College. Git link: https://github.com/Mac-COMP-127-Fall-2022/hw5-KienNguyen17 

2. Known issues:
    - Once played, the user cannot pause or stop the audio. The audio created only spans two octaves (C3 to B4) and the piano graphics cannot be scrolled to display more octaves. All the notes produced are of the same duration and cannot be changed by the user. In addition, the track display can be dragged manually to show more notes, but does not move along with the melody while the user is composing or while the melody is playing. Moreover, the melody’s text display on a track cannot be copied and pasted onto different tracks. 
    - So far, we are not aware of any particular bugs or glitches. One minor shortcoming is that the text display of notes are of different sizes, and thus can be slightly difficult for users to follow and compose. 

3. Societal impact:
    - We tried to design the user interface to be as easily navigated as possible. However, it is only useful for people who can hear sounds as our visual display of notes can be confusing, and are based mainly on auditory capabilities. In addition, the sounds that we synthesized can be blaring and cause discomfort to some people. 
    - Because the program models a piano, the notes are based on Western Music Theory while the display uses Western Music Notations. We acknowledge that this Eurocentric design can be exclusive of other musical traditions and demographics of composers. 
    - Although we have implemented many features that allows for keyboard interaction, the main composing feature still requires the use of a mouse. As a result, the program is only practicable to people who use devices that have a mouse while remains inaccessible to those who use other assistive devices.




