package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.decorations.Arrow;
import com.droidquest.items.BlueRobot;
import com.droidquest.items.Crystal;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;
import java.io.File;

public class MainMenu extends Level {
    public MainMenu(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.addElement(new Material(true, false));
        // Material 1, White Wall
        materials.addElement(new Material(new Color(255, 255, 255), false, true));
        // Material 2, Green Wall
        materials.addElement(new Material(new Color(0, 255, 0), false, true));
        // Material 3, Red Wall
        materials.addElement(new Material(new Color(255, 0, 0), false, true));
        // Material 4, Blue Wall
        materials.addElement(new Material(new Color(0, 0, 255), false, true));
        // Material 5, Portal to RO game
        materials.addElement(new Portal("RO1.lvl", false, true));
        // Material 6, Portal to RO Lab
        materials.addElement(new Portal("ROLab.lvl", false, true));
        // Material 7, Portal to RO tutorial A
        materials.addElement(new Portal("ROTutA.lvl", false, true));
        // Material 8, Portal to RO tutorial B
        materials.addElement(new Portal("ROTutB.lvl", false, true));
        // Material 9, Portal to RO tutorial C
        materials.addElement(new Portal("ROTutC.lvl", false, true));
        // Material 10, Portal to RO tutorial D
        materials.addElement(new Portal("ROTutD.lvl", false, true));
        // Material 11, Portal to RO tutorial E
        materials.addElement(new Portal("ROTutE.lvl", false, true));
        // Material 12, Portal to RO tutorial F
        materials.addElement(new Portal("ROTutF.lvl", false, true));
        // Material 13, Portal to RO Tutorial 3
        materials.addElement(new Portal("ROTut3.lvl", false, true));

        // Material 14, Portal to EndGame 1
        materials.addElement(new Portal("ROEndGame.lvl", true, true));
        // Material 15, Portal to RO Level 6
        materials.addElement(new Portal("RO6.lvl", true, true));


        // Room  0, Help Screen
        // Room  1, Credits
        // Room  2, Credits part 2
        // Room  3, Credits part 3
        // Room  4, Title, Entry Point
        // Room  5, Saved Games List
        // Room  6, New Games List

        for (int a = 0; a < 10; a++) {
            rooms.addElement(new Room());
        }

        {  // Room 0: Help Screen
            Room room = rooms.elementAt(0);
            room.AddTextBox("Droid Quest Temporary Cheats", 4 * 28, 2 * 32, 500);
            room.AddTextBox("Q = Quicken the Animation Timer", 2 * 28, 4 * 32, 500);
            room.AddTextBox("W = Slow the Animation Timer", 2 * 28, 5 * 32, 500);
            room.AddTextBox("M = Memory Report", 2 * 28, 6 * 32, 500);
            room.AddTextBox("(To go to Main Menu, press Return.)", 70, 11 * 32, 500);

        }
        {  // Room 1: Credits
            Room room = rooms.elementAt(1);
            room.AddTextBox("Credits:", 7 * 28, 2 * 32, 500);
            room.AddTextBox("Original Robot Odyssey by Mike Wallace and Leslie Grimm, (C) The Learning Company",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("Original Atari Adventure by Warren Robinett, (C) Atari International",
                    2 * 28, 6 * 32, 500);
            room.AddTextBox("DroidQuest (C) 2000 Thomas Foote", 2 * 28, 8 * 32, 500);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
        }
        {  // Room 2: Credits 2
            Room room = rooms.elementAt(2);
            room.AddTextBox("Special thanks to...", 2 * 28, 2 * 32, 500);
            room.AddTextBox("Eric Welsh       Eric Jacobs Vladimir Dimitrov Nathan Woods John Isidoro Derek Pechel Jeffery Hanke Matheww Russo Jim Veneskey Erik Santiso Michael Mol", 2 * 28, 4 * 32, 220);
            room.AddTextBox("Che Fox illuvius lexspoon shuffles Locklainn samdroid", 12 * 28, 4 * 32, 200);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            LinkRoomsUpDown(1, 2);
        }
        {  // Room 3: Credits 3
            Room room = rooms.elementAt(3);
            room.AddTextBox("Christopher Walkup, age 6  Billy Leete, age 5", 2 * 28, 4 * 32, 350);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            LinkRoomsUpDown(2, 3);
        }
        {  // Room 4: Title
            Room room = rooms.elementAt(4);
            room.AddGraphix("DQlogo.gif", 2 * 28, 1 * 32);
            room.AddTextBox("Credits", 2 * 28, 6 * 32 + 8, 500);
            room.AddArrow(0, 6 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Saved Games", 9 * 28, 10 * 32, 80);
            room.AddTextBox("Games", 450, 6 * 32 + 8, 500);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddTextBox("{000,000,000} Version 2.7", 0, 16, 500);
            if (cheatmode) {
                room.AddTextBox("{BIG} CHEAT ENABLED!", 91, 8 * 32, 500);
            }
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            LinkRoomsLeftRight(1, 4);
        }
        {  // Room 5: Save games
            Room room = rooms.elementAt(5);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            LinkRoomsUpDown(4, 5);
        }
        {  // Room 6: Robot Odyssey
            Room room = rooms.elementAt(6);
            room.AddTextBox("{BIG} ROBOT ODYSSEY I", 2 * 28, 2 * 32, 600);
            room.AddTextBox("The Original Game", 2 * 28, 3 * 32, 500);
            room.AddTextBox("Robotropolis", 8 * 28, 6 * 32, 500);
            room.AddTextBox("Innovation Lab", 8 * 28, 8 * 32, 500);
            room.AddTextBox("Tutorials", 8 * 28, 11 * 32, 300);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 32, Color.white);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            LinkRoomsLeftRight(4, 6);
        }
        {  // Room 7: RO Tutorials
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.AddTextBox("Robot Anatomy", 3 * 28, 4 * 32, 500);
            room.AddTextBox("Robot Wiring", 3 * 28, 6 * 32, 500);
            room.AddTextBox("Sensors", 3 * 28, 8 * 32, 500);
            room.AddTextBox("Toolkit", 3 * 28, 10 * 32, 500);
            LinkRoomsUpDown(6, 7);
        }
        {  // Room 8: RO Tutorials
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.AddTextBox("Robot Circuits", 3 * 28, 4 * 32, 500);
            room.AddTextBox("Robot Teamwork", 3 * 28, 6 * 32, 500);
            room.AddTextBox("Chip Design", 3 * 28, 8 * 32, 500);
            LinkRoomsUpDown(7, 8);
        }
        {  // Room 9: Secret Room
            Room room = rooms.elementAt(9);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterial(2, 2, 14);
            room.SetMaterial(2, 4, 15);
            LinkRoomsUpDown(9, 4);
            items.addElement(new BlueRobot(2 * 28, 6 * 32, room));
            items.addElement(new Crystal(5 * 28, 6 * 32, room, 100000));
        }


        gameCursor = new GameCursor(9 * 28, 6 * 32, rooms.elementAt(4));
        helpCam = new HelpCam(rooms.elementAt(0));
        items.addElement(gameCursor);
        items.addElement(helpCam);
        player = gameCursor;
        currentViewer = player;

        File f = new File("ROlevels/");
        if (!f.exists()) {
            f.mkdir();
        }
        String[] files = f.list();
        int pageIndex = 5;
        for (int a = 0; a < files.length; a++) {
            if (a > 4 && a % 5 == 0) {
                // Add a new room
                Room oldRoom = rooms.elementAt(pageIndex);
                oldRoom.RoomArray[11][8] = 0;
                oldRoom.RoomArray[11][9] = 0;
                oldRoom.RoomArray[11][10] = 0;
                oldRoom.RoomArray[11][11] = 0;
                Room newRoom = new Room();
                rooms.addElement(newRoom);
                newRoom.RoomArray = new int[][]{
                        {3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
                };
                int newPageIndex = rooms.indexOf(newRoom);
                LinkRoomsUpDown(pageIndex, newPageIndex);
                pageIndex = newPageIndex;
            }
            materials.addElement(new Portal("ROlevels/" + files[a], false, false));
            int matIndex = materials.size() - 1;
            int y = 1 + (a % 5) * 2;
            Room room = rooms.elementAt(pageIndex);
            room.RoomArray[y][2] = matIndex;
            room.AddTextBox(files[a], 3 * 28 + 14, y * 32 + 32, 400);
        }

    }

}

