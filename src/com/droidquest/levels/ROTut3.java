package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.*;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.MazeLock;

import java.awt.*;

class ROTut3 extends Level {
    public ROTut3(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.addElement(new Material(true, false));
        // Material 1, Green Wall 
        materials.addElement(new Material(new Color(0, 255, 0), false, true));
        // Material 2, Light Blue Wall
        materials.addElement(new Material(new Color(190, 190, 255), false, true));
        // Material 3, Blue Wall 
        materials.addElement(new Material(new Color(0, 0, 255), false, true));
        // Material 4, LightOrange Wall 
        materials.addElement(new Material(new Color(255, 224, 192), false, true));
        // Material 5, Red Wall 
        materials.addElement(new Material(new Color(255, 0, 0), false, true));
        // Material 6, Recharger
        materials.addElement(new CrystalRecharger());
        // Material 7, Crystal Shape Editor
        materials.addElement(new ShapeEditor(new Crystal(0, 0, null, 0)));
        // Material 8, Hexagon Shape Editor
        materials.addElement(new ShapeEditor(new Hexagon(0, 0, null, Color.blue)));
        // Material 9, Square Shape Editor
        materials.addElement(new ShapeEditor(new Square(0, 0, null, Color.white)));
        // Material 10, Triangle Shape Editor
        materials.addElement(new ShapeEditor(new Triangle(0, 0, null, new Color(255, 128, 0))));
        // Material 11, Portal to Innovation Lab;
        materials.addElement(new Portal("ROLab.lvl", false, true));
        // Material 12, Portal to Main Menu;
        materials.addElement(new Portal("MainMenu.lvl", false, true));
        // Material 13, Trash
        materials.addElement(new ChipTrash());
        // Material 14, ChipTester
        materials.addElement(new ChipTester());
        // Material 15, MazeLock
        materials.addElement(new MazeLock());

        for (int a = 0; a < 42; a++) {
            rooms.addElement(new Room());
        }

        { // Room 0, Help Screen 
            Room room = rooms.elementAt(0);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("INNOVATION LAB Chip Functions", 3 * 28, 2 * 32, 500);
            room.AddTextBox("BURN:",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Wire prototype. Position chips in Burner Room. Activate burn button.",
                    6 * 28, 4 * 32, 400);
            room.AddTextBox("SAVE:",
                    2 * 28, 6 * 32, 500);
            room.AddTextBox("Pick up Chip. Press S",
                    6 * 28, 6 * 32, 500);
            room.AddTextBox("LOAD:",
                    2 * 28, 7 * 32, 500);
            room.AddTextBox("Pick up chip. Press L",
                    6 * 28, 7 * 32, 500);
            room.AddTextBox("RECORD:",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Pick up chip. Press ?",
                    6 * 28, 8 * 32, 500);
            room.AddTextBox("To continue, press RETURN.", 4 * 28, 11 * 32, 500);
        }
        { // Room 1, Title Screen 
            Room room = rooms.elementAt(1);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("{BIG} Chip Design", 4 * 28 + 16, 2 * 32, 500);
            room.AddTextBox("Welcome! Here you will learn about things you can do in the INNOVATION LAB. You'll learn how to use the:",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("- Chips", 6 * 28, 5 * 32, 500);
            room.AddTextBox("- Paintbrush", 6 * 28, 6 * 32, 500);
            room.AddTextBox("- Factories", 6 * 28, 7 * 32, 500);
            room.AddTextBox("- Shape Editor", 6 * 28, 8 * 32, 500);
            room.AddTextBox("If you don't know about ROBOT ANATOMY and TOOLKIT, return to the Main Menu by using the menubar.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Crossroads 
            Room room = rooms.elementAt(2);
            room.RoomArray = new int[][]{
                    {2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("{BIG} CROSSROADS", 5 * 28, 5 * 32, 500);
            room.AddTextBox("PAINTBRUSH", 28 + 14, 2 * 32, 500);
            room.AddTextBox("FACTORY", 8 * 28 + 14, 2 * 32, 100);
            room.AddTextBox("SHAPE EDITOR", 16 * 28, 2 * 32, 100);
            room.AddTextBox("CHIPS", 16 * 28, 8 * 32 - 8, 500);
            room.AddTextBox("When you have learned about the Paintbrush, shape editor, and chips, go this way.",
                    5 * 28, 9 * 32, 400);
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(10 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(17 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            toolbox = new ToolBox(28, 10 * 32, room);
            items.addElement(toolbox);
        }
        { // Room 3, Paths to Paintbrush & Shape Editor 
            Room room = rooms.elementAt(3);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2}
            };
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddArrow(10 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 4, Paintbrush intro 
            Room room = rooms.elementAt(4);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("This is a PAINTBRUSH.",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("Use it in special rooms in the INNOVATION LAB to design mazes.",
                    2 * 28, 3 * 32, 400);
            room.AddTextBox("Press P to become the Paintbrush.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Press C to become the Cursor.",
                    2 * 28, 10 * 32, 500);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 5, Paintbrush intro 2
            Room room = rooms.elementAt(5);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Try painting and erasing walls in the maze rooms below.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To paint, press the SPACEBAR and move the paintbrush.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("To erase, sit on a wall and press SPACEBAR as you move.",
                    2 * 28, 6 * 32, 500);
            room.AddTextBox("Paintable Maze Rooms",
                    1 * 28, 9 * 32, 100);
            room.AddTextBox("Return to the Cross- roads when you have finished your maze.",
                    9 * 28, 9 * 32, 300);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 6, Maze Top Left
            Room room = rooms.elementAt(6);
            room.RoomArray = new int[][]{
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.editable = true;
        }
        { // Room 7, Maze Top Right
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5}
            };
            room.editable = true;
        }
        { // Room 8, Maze Bottom Left
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.editable = true;
        }
        { // Room 9, Maze Bottom Right
            Room room = rooms.elementAt(9);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.editable = true;
        }
        { // Room 10, Shape Editor intro 
            Room room = rooms.elementAt(10);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("The INNOVATION LAB contains a SHAPE EDITOR like the one next door.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("You can use the shape editor to change the shapes of the sensors and the objects they detect.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Go try the shape editor. Take the crystal or sensor with you.",
                    2 * 28, 10 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Crystal(5 * 28, 8 * 32, room, 100000));
            items.addElement(new DirectionalSensor(11 * 28, 6 * 32, room, new Crystal(0, 0, null, 0)));
        }
        { // Room 11, Shape Editor room 
            Room room = rooms.elementAt(11);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 7, 0, 0, 0, 0, 8, 0, 0, 0, 0, 9, 0, 0, 0, 0, 10, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.AddTextBox("Shape Editor", 7 * 28, 2 * 32, 200);
            room.AddTextBox("Shape Icons", 7 * 28, 8 * 32, 200);
            room.AddTextBox("Pass a sensor or item over one of the shape icons to change it to that shape.",
                    2 * 28, 3 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 12, Shape Editor workshop 
            Room room = rooms.elementAt(12);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("This robot will beep when it touches an energy crystal. Try it.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Now, unwire the sensor in the robot and change its shape to a triangle.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("Wire it up again and make it beep when it touches the triangle.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Then return to the Crossroads.",
                    2 * 28, 10 * 32, 500);
            BlueRobot robot = new BlueRobot(16 * 28, 2 * 32, room);
            items.addElement(robot);
            ContactSensor sensor = new ContactSensor(4 * 28, 6 * 32, robot.InternalRoom, new Crystal(0, 0, null, 0));
            items.addElement(sensor);
            Wire wire = new Wire(robot.devices[8].ports[0], sensor.ports[0]);
            items.addElement(new Triangle(15 * 28, 4 * 32, room, new Color(255, 128, 0)));
            items.addElement(new Crystal(17 * 28, 4 * 32, room, 100000));
        }
        { // Room 13, Prototype Intro 
            Room room = rooms.elementAt(13);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("This is a PROTOTYPE CHIP. It has 8 pins that can be soldered to wires.",
                    2 * 28, 2 * 32, 250);
            room.AddTextBox("Notice how the pins are numbered.",
                    2 * 28, 5 * 32, 250);
            room.AddTextBox("CROSSROADS",
                    36, 7 * 32 + 24, 300);
            room.AddTextBox("The pins on this chip are neither outputs nor inputs because the chip isn't wired.",
                    2 * 28, 10 * 32, 550);
            room.AddTextBox("1 2 3 4",
                    13 * 28, 2 * 32, 30);
            room.AddTextBox("8 7 6 5",
                    18 * 28, 2 * 32, 30);
            room.AddArrow(0, 7 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new PrototypeChip(14 * 28, 2 * 32 - 8, room));
        }
        { // Room 14, Prototype Intro 2 
            Room room = rooms.elementAt(14);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("But chips can be wired inside!",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("You can create circuits inside a prototype chip (using nodes, fliplflops, and logic gates) and connect them to the pins inside.",
                    2 * 28, 4 * 32, 480);
            room.AddTextBox("When a chip is wired, pins change to outputs or inputs, depending on how they are wired. Unwired pins remain neutral.",
                    2 * 28, 8 * 32, 480);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 15, Prototype Chip 
            Room room = rooms.elementAt(15);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("This prototype chip has a circuit inside.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("This circuit sends out pulses of electricity. Go inside the chip and see.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("Because of the circuit wiring, pin 1 is now an input on the inside and an output on the outside.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Take the chip with you.",
                    2 * 28, 11 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            PrototypeChip pc = new PrototypeChip(14 * 28, 2 * 32 - 8, room);
            items.addElement(pc);
            Node node = new Node(4 * 28, 2 * 32, pc.InternalRoom, Node.TYPE_STRAIGHT);
            items.addElement(node);
            NOTGate ng = new NOTGate(6 * 28, 3 * 32, pc.InternalRoom);
            items.addElement(ng);
            Wire wire = new Wire(node.ports[0], ng.ports[1]);
            wire = new Wire(ng.ports[0], node.ports[2]);
            wire = new Wire(pc.portdevices[0].ports[0], node.ports[1]);
        }
        { // Room 16, Antenna 
            Room room = rooms.elementAt(16);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2}
            };
            room.AddTextBox("You can use a chip with a circuit inside to activate a robot part.",
                    5 * 28, 2 * 32, 350);
            room.AddTextBox("Solder pin 1 to the INPUT of this antenna. Notice how the antenna pulses on and off.",
                    5 * 28, 9 * 32, 300);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
        }
        { // Room 17, Antenna & SmallChip 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2}
            };
            room.AddTextBox("This SMALL CHIP was created by 'burning' into it a copy of the prototype chip's circuit.",
                    6 * 28, 2 * 32, 400);
            room.AddTextBox("It work's just like a prototype chip, but you can't go inside.",
                    6 * 28, 5 * 32, 400);
            room.AddTextBox("Solder pin 1 (upper left pin) to the antenna INPUT. Does it work like the prototype chip?",
                    2 * 28, 9 * 32, 450);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
            SmallChip sc = new SmallChip(4 * 28, 4 * 32, room, "1");
            items.addElement(sc);
            sc.LoadChip("chips/oscillator.chip");
        }
        { // Room 18, Burner Intro 
            Room room = rooms.elementAt(18);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Small chips are 'burned' in the Burner Room of the INNOVATION LAB.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Go look at the burner room at the left.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("Look here",
                    0, 6 * 32, 100);
            room.AddTextBox("To burn a chip, become the cursor and sit on the burn button. Then press SPACEBAR. Try it now and see what happens.",
                    5 * 28, 9 * 32, 400);
            room.AddArrow(0, 7 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.AddArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 19, Burner Room 
            Room room = rooms.elementAt(19);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {4, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 0},
                    {4, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 0, 0, 4, 0, 4, 0, 0, 0},
                    {4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 4, 0, 0, 4},
                    {4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 4, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.AddTextBox("Burner Room",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Prototype chip goes here.",
                    2 * 28, 4 * 32, 250);
            room.AddTextBox("Small chip goes here.",
                    11 * 28, 6 * 32, 170);
            room.AddTextBox("BURN BUTTON",
                    17 * 28, 8 * 32, 100);
            room.AddArrow(5 * 28, 5 * 32 + 16, Arrow.DIR_DOWN, 28, Color.white);
            room.AddArrow(13 * 28, 7 * 32 + 16, Arrow.DIR_DOWN, 28, Color.white);
            room.AddArrow(18 * 28 + 14, 9 * 32 + 16, Arrow.DIR_DOWN, 28, Color.white);
            PrototypeChip pc = new PrototypeChip(3 * 28 + 4, 5 * 32 + 12, room);
            items.addElement(pc);
            pc.inBurner = true;
            SmallChip sc = new SmallChip(13 * 28 - 13, 8 * 32 - 15, room, "1");
            items.addElement(sc);
            sc.inBurner = true;
            items.addElement(new Burner(18 * 28, 10 * 32 + 2, room));
        }
        { // Room 20, Nesting 
            Room room = rooms.elementAt(20);
            room.RoomArray = new int[][]{
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("You can use small chips as part of a circuit inside the prototype chip. This is called 'nesting'.",
                    4 * 28, 2 * 32, 400);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 21, Nesting II 
            Room room = rooms.elementAt(21);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Take the shortcut up. Bring back the prototype chip and the small chip.",
                    2 * 28, 3 * 32, 400);
            room.AddTextBox("Disconnect the wires in the prototype chip.",
                    2 * 28, 6 * 32, 400);
            room.AddTextBox("Take both chips next door to try nesting the small chip inside the prototype chip.",
                    2 * 28, 9 * 32, 500);
            room.AddTextBox("SHORTCUT",
                    14 * 28, 32 - 8, 100);
            room.AddArrow(18 * 28 + 14, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 22, Nesting III 
            Room room = rooms.elementAt(22);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Take the small chip inside the prototype chip. Solder it's pin 1 to the prototype's pin 1.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Next, solder a NOT-gate's OUTPUT to the prototype's pin 3.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Come outside and take the chip into the next room.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 23, Nesting workshop 
            Room room = rooms.elementAt(23);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Put the prototype chip in the robot and solder the pins as shown:",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Pin 1 to the antenna INPUT Pin 2 to the left thruster",
                    3 * 28, 4 * 32, 350);
            room.AddTextBox("Close the robot's thruster switch.",
                    5 * 28, 8 * 32, 400);
            room.AddTextBox("Now you have a robot that moves right and beeps.",
                    5 * 28, 10 * 32, 400);
            room.AddArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            OrangeRobot robot = new OrangeRobot(16 * 28, 3 * 32, room);
            items.addElement(robot);
            items.addElement(new RoomSensor(8 * 28, 3 * 32, robot.InternalRoom,
                    new Square(0, 0, null, Color.white)));
            items.addElement(new ContactSensor(10 * 28, 5 * 32, robot.InternalRoom,
                    new Hexagon(0, 0, null, Color.white)));
        }
        { // Room 24, Wallhugger intro 
            Room room = rooms.elementAt(24);
            room.RoomArray = new int[][]{
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("By nesting chips inside chips, you can create very complex circuits.",
                    6 * 28, 2 * 32, 400);
            room.AddTextBox("This WALLHUGGER chip is an example of a complex circuit. It is wired with 16 logic gates and one nested chip.",
                    6 * 28, 5 * 32, 400);
            room.AddTextBox("Take the chip with you.",
                    6 * 28, 10 * 32, 500);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            SmallChip sc = new SmallChip(4 * 28, 5 * 32, room, "3");
            items.addElement(sc);
            sc.LoadChip("chips/WallHugger.chip");
        }
        { // Room 25, Wallhugger wiring 
            Room room = rooms.elementAt(25);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("You can use chips inside robots.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Take the WALLHUGGER chip inside this robot.",
                    2 * 28, 3 * 32, 400);
            room.AddTextBox("Solder the pins as shown.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("PIN   TO           PIN   TO",
                    2 * 28, 6 * 32, 400);
            room.AddTextBox("1 top thruster 2 left bumper 3 left thruster 4 bottom bumper",
                    3 * 28, 6 * 32 + 16, 250);
            room.AddTextBox("8 top bumper       7 right thruster 6 right bumper 5 bottom thruster",
                    11 * 28, 6 * 32 + 16, 240);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            WhiteRobot robot = new WhiteRobot(16 * 28, 3 * 32, room);
            items.addElement(robot);
            items.addElement(new DirectionalSensor(8 * 28, 3 * 32, robot.InternalRoom,
                    new Crystal(0, 0, null, 0)));
            items.addElement(new ContactSensor(10 * 28, 5 * 32, robot.InternalRoom,
                    new Crystal(0, 0, null, 0)));
        }
        { // Room 26, Wallhugger intro II
            Room room = rooms.elementAt(26);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2}
            };
            room.AddTextBox("Take the robot to the maze below. Close the thruster switch.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("How does the WALLHUGGER chip work in this kind of maze?",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("Maze",
                    16 * 28, 11 * 32, 400);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 27, Wallhugger maze 
            Room room = rooms.elementAt(27);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
        }
        { // Room 28, Chip speed 
            Room room = rooms.elementAt(28);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Chips can also be set to run faster than normal circuits.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("The Burner Room in the INNOVATION LAB has a SPEED SETTING that can be changed by using the Up and Down arrows in the Burner Room. A chip receives this speed setting when it's burned.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("A chip's speed sets the number of times the chip will be processed in one INSTANT. One instant is the time it takes for a gate (outside a chip) to process its inputs and send an output.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 29, Chip talk 
            Room room = rooms.elementAt(29);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Here are some special uses of chips.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Chips can be wired inside other chips to create very complex chips.",
                    3 * 28, 3 * 32, 450);
            room.AddTextBox("Chips can be transferred to ROBOTROPOLIS where you can use them to help you escape.",
                    3 * 28, 5 * 32, 450);
            room.AddTextBox("Chips can be used to save space in a robot.",
                    3 * 28, 7 * 32, 450);
            room.AddTextBox("Chips can be used to make complex circuits run as fast as a single gate.",
                    3 * 28, 9 * 32, 450);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 30, Chip Record 
            Room room = rooms.elementAt(30);
            room.RoomArray = new int[][]{
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.AddTextBox("You can make a RECORD of a chip's circuit. You can then review the chip's record whenever you want.",
                    4 * 28, 2 * 32, 400);
            room.AddTextBox("Pick up this chip and press ? to see and edit its record.",
                    4 * 28, 5 * 32, 400);
            room.AddTextBox("You can also use the record to change the label on the small chip. Just change the letter in the top right field and press Save.",
                    4 * 28, 7 * 32, 400);
            room.AddArrow(2 * 28 + 14, 0, Arrow.DIR_UP, 28, Color.white);
            SmallChip sc = new SmallChip(2 * 28, 5 * 32, room, "5");
            items.addElement(sc);
            sc.LoadChip("chips/gates.chip");
        }
        { // Room 31, Chip Save 
            Room room = rooms.elementAt(31);
            room.RoomArray = new int[][]{
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
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
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.AddTextBox("You can also save a chip's circuit for later use.",
                    4 * 28, 2 * 32, 400);
            room.AddTextBox("To save a chip's circuit, pick up a chip in the INNOVATION LAB and press S.",
                    4 * 28, 4 * 32 + 16, 400);
            room.AddTextBox("You can recycle small chips. First save the old version of the chip. Then burn in a new circuit.",
                    4 * 28, 8 * 32, 400);
            room.AddArrow(2 * 28 + 14, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 32, Chip Load 
            Room room = rooms.elementAt(32);
            room.RoomArray = new int[][]{
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
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
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.AddTextBox("You can LOAD circuits you have saved into small chips.",
                    4 * 28, 3 * 32, 400);
            room.AddTextBox("To load a chip, pick up a chip and press L. A dialog box of circuit names will appear. Select the one you want.",
                    4 * 28, 5 * 32, 400);
            room.AddTextBox("Chips can be loaded in the INNOVATION LAB or in ROBOTROPOLIS where you can use them to help you escape.",
                    4 * 28, 8 * 32, 400);
            room.AddArrow(2 * 28 + 14, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 33, Exit 
            Room room = rooms.elementAt(33);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("You have learned how to use many features of the INNOVATION LAB.",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("Go to the INNOVATION LAB.",
                    6 * 28, 7 * 32, 500);
            room.AddTextBox("Return to the MAIN MENU.",
                    6 * 28, 9 * 32, 500);
            room.AddTextBox("Press ? for help or hints.",
                    2 * 28, 11 * 32, 500);
        }
        { // Room 34, Paintbrush colors 
            Room room = rooms.elementAt(34);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("The Paintbrush can also paint walls of different colors and properties. Press P to change colors.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("{255,000,000} RED {128,128,128} walls block all robots and are detectable.",
                    3 * 28, 5 * 32, 500);
            room.AddTextBox("{000,255,000} GREEN {128,128,128} walls block all robots and are not detectable.",
                    3 * 28, 7 * 32, 450);
            room.AddTextBox("{255,128,000} ORANGE, {255,255,255} WHITE, {128,128,128} and {000,000,255} BLUE {128,128,128} walls block their respectivly colored robots, and are not detectable.",
                    3 * 28, 9 * 32, 500);
        }

        { // Room 35, Factory Intro 
            Room room = rooms.elementAt(35);
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
            room.AddTextBox("The INNOVATION LAB has two FACTORIES for creating chips, shapes, and sensors.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Factories also have TRASH containers, which you can use to dispose of items that you no longer need.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Go try the factory in the next room.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(11 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 36, Factory 
            Room room = rooms.elementAt(36);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 13, 13, 13, 13, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 13, 13, 13, 13, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 13, 13, 13, 13, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 13, 13, 13, 13, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("FACTORY",
                    8 * 28 + 14, 2 * 32, 500);
            room.AddTextBox("Press to summon an Energy Crystal",
                    5 * 28, 3 * 32 + 24, 500);
            items.addElement(new Factory(2 * 28, 3 * 32, room, new Crystal(0, 0, null, 100000)));
            room.AddArrow(3 * 28 + 14, 4 * 28, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Drag crystal into Trash to dispose.",
                    2 * 28, 6 * 32 - 8, 500);
            room.AddTextBox("TRASH",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(3 * 28, 7 * 32, Arrow.DIR_DOWN, 28, Color.white);
            room.AddTextBox("Return to the Crossroads.",
                    12 * 28, 10 * 32, 200);
        }
        { // Room 37, Unburning 
            Room room = rooms.elementAt(37);
            room.RoomArray = new int[][]{
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
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
                    {2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Chips can also be unburned.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To unburn a chip, place a burned or loaded chip into the right holder, and an empty Prototype chip in the left. Then press the UNBURN button.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("The Prototype chip will automatically be wired to match the small chip's circutry.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(2 * 28 + 14, 559, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 38, ChipTest intro 
            Room room = rooms.elementAt(38);
            room.RoomArray = new int[][]{
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.AddTextBox("To the Crossroads",
                    28, 32 + 16, 500);
            room.AddTextBox("The Innovation Lab also has a CHIP TESTER for testing prototype chips and small chips.",
                    4 * 28, 3 * 32, 450);
            room.AddTextBox("Place a chip in the holder and then press the AUTOWIRE button. The chip will be wired to the tester, and the buttons can be used to set or display port values.",
                    4 * 28, 6 * 32, 450);
            room.AddTextBox("Press the autowire button again to unwire the chip.",
                    4 * 28, 10 * 32, 450);
            room.AddArrow(28 + 14, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 39, Chip Testing 
            Room room = rooms.elementAt(39);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 2, 14, 14, 14, 14, 14, 14, 2, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 2, 14, 14, 14, 14, 14, 14, 2, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 2, 14, 14, 14, 14, 14, 14, 2, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 2, 14, 14, 14, 14, 14, 14, 2, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Chip Tester", 2 * 28, 32 + 24, 500);
            PortDevice[] portdevices = new PortDevice[8];
            portdevices[0] = new PortDevice(6 * 28, 3 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[1] = new PortDevice(6 * 28, 4 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[2] = new PortDevice(6 * 28, 5 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[3] = new PortDevice(6 * 28, 6 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[4] = new PortDevice(12 * 28 + 8, 6 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[5] = new PortDevice(12 * 28 + 8, 5 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[6] = new PortDevice(12 * 28 + 8, 4 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[7] = new PortDevice(12 * 28 + 8, 3 * 32 + 8, room, 28, Port.TYPE_UNDEFINED);
            portdevices[0].rotate(1);
            portdevices[1].rotate(1);
            portdevices[2].rotate(1);
            portdevices[3].rotate(1);
            portdevices[4].rotate(-1);
            portdevices[5].rotate(-1);
            portdevices[6].rotate(-1);
            portdevices[7].rotate(-1);
            for (int a = 0; a < 8; a++) {
                items.addElement(portdevices[a]);
            }
            items.addElement(new AutoWire(2 * 28, 10 * 32, room));
            items.addElement(new WireTester(5 * 28, 3 * 32, room, portdevices[0]));
            items.addElement(new WireTester(5 * 28, 4 * 32, room, portdevices[1]));
            items.addElement(new WireTester(5 * 28, 5 * 32, room, portdevices[2]));
            items.addElement(new WireTester(5 * 28, 6 * 32, room, portdevices[3]));
            items.addElement(new WireTester(14 * 28 + 2, 6 * 32, room, portdevices[4]));
            items.addElement(new WireTester(14 * 28 + 2, 5 * 32, room, portdevices[5]));
            items.addElement(new WireTester(14 * 28 + 2, 4 * 32, room, portdevices[6]));
            items.addElement(new WireTester(14 * 28 + 2, 3 * 32, room, portdevices[7]));
            room.AddArrow(3 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Autowirer", 4 * 28, 11 * 32 - 8, 200);
        }
        { // Room 40, Maze Control 
            Room room = rooms.elementAt(40);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("The Maze can also be changed to whatever size you want.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("The Innovation Lab's MAZE CONTROL ROOM has five buttons that allow you to set the size the maze, and a sixth button that locks the maze by preventing the cursor from becoming the paintbrush.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("You can use this to create your own puzzles and share them with your friends.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Try it in the next room.",
                    2 * 28, 11 * 32, 500);
        }
        { // Room 41, Maze Control 
            Room room = rooms.elementAt(41);
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
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
//	     room.AddTextBox("",
//			     2*28, 2*32, 500);
            room.AddTextBox("Maze Control Room", 178, 2 * 32, 300);
            room.AddTextBox("4x2", 262, 5 * 32 + 24, 500);
            items.addElement(new MazeControl(9 * 28 + 14, 3 * 32 + 6, room, MazeControl.DIR_UP));
            items.addElement(new MazeControl(9 * 28 + 14, 7 * 32, room, MazeControl.DIR_DOWN));
            items.addElement(new MazeControl(7 * 28 + 2, 5 * 32 + 4, room, MazeControl.DIR_LEFT));
            items.addElement(new MazeControl(12 * 28, 5 * 32 + 4, room, MazeControl.DIR_RIGHT));
            items.addElement(new MazeCreator(2 * 28, 10 * 32, room));
            room.AddArrow(3 * 28 + 14, 10 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Press to resize Maze", 5 * 28, 10 * 32 + 18, 500);
            room.AddArrow(18 * 28 + 14, 10 * 32, Arrow.DIR_DOWN, 28, Color.white);
            room.AddTextBox("Lock", 17 * 28 + 4, 9 * 32 - 4, 100);
            room.AddTextBox("These arrows change the size of the maze.", 2 * 28, 4 * 32, 150);
        }

        int[] list1 = {1, 2, 13, 14, 15, 16, 17};
        LinkRoomsHorizontally(list1);

        int[] list2 = {36, 35, 3, 2, 33};
        LinkRoomsVertically(list2);

        int[] list3 = {17, 18, 37, 20};
        LinkRoomsVertically(list3);
        LinkRoomsUpDown(16, 21);
        LinkRoomsLeftRight(19, 18);

        int[] list4 = {23, 22, 21, 20};
        LinkRoomsHorizontally(list4);

        int[] list5 = {30, 29, 28, 26, 25, 24};
        LinkRoomsHorizontally(list5);
        LinkRoomsUpDown(23, 24);
        LinkRoomsUpDown(26, 27);

        int[] list6 = {13, 38, 32, 31, 30};
        LinkRoomsVertically(list6);
        LinkRoomsLeftRight(38, 39);

        int[] list7 = {41, 40, 34, 5, 4, 3, 10, 11, 12};
        LinkRoomsHorizontally(list7);

        int[][] grid = {
                {6, 7},
                {8, 9}
        };
        LinkRoomsGrid(grid);
        LinkRoomsUpDown(5, 6);


        { // Paintbrush code comes after Robots are constructed 
            Room room = rooms.elementAt(4);
            PaintBrush pb = new PaintBrush();
            pb.x = 17 * 28 + 10;
            pb.y = 44;
            pb.room = room;
            items.addElement(pb);
        }


        gameCursor = new LabCursor(16 * 28 + 14, 5 * 32 + 16, rooms.elementAt(1));
        helpCam = new HelpCam(rooms.elementAt(0));
        solderingPen = new SolderingPen();
        remote = new Remote();
        paintbrush = new PaintBrush();
        items.addElement(gameCursor);
        items.addElement(helpCam);
        items.addElement(solderingPen);
        items.addElement(remote);
        items.addElement(paintbrush);
        player = gameCursor;
        currentViewer = player;

    }

}