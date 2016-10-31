DroidQuest
==========

A Java recreation of the classic game Robot Odyssey I

Build with:

    mvn install

Run the game:

    MacOSX / Linux: ./start.sh
    Windows: start.bat (IN PROGRESS)
OR
    java -jar target/dq-1.1-SNAPSHOT.jar

Controls:

    / - contextual help
    arrows - normal movement
    control + arrows - fine-grained movement [OS X: command + arrows]
    space - pickup/drop
    C - game cursor
    H - hot cursor
    S - solder pen
    R - toggle radio
    P - paintbrush
    T - toolbox
    [, ] - rotate device
    F - flip
    L - load small chip
    E - enter robot
    X - exit robot

    Cheat/debug:

    shift + arrows - move rooms
    M - dump memory usage

Note: On OS X, control + arrow-keys are bound to Apple's "Mission Control".  I switched to the 'shortcut' modifier which is control on windows and command on OS X.  Alternatively, you can disable
built in OS X shortcuts via System Preferences -> Keyboard -> Shorcuts.


Copyright (c) 2000 Thomas Foote

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
