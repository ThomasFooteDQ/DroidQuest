package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTutD extends Level {
    public ROTutD(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.addElement(new Material(true, false));
        // Material 1, LightBlue Wall 
        materials.addElement(new Material(new Color(192, 192, 255), false, true));
        // Material 2, Green Wall 
        materials.addElement(new Material(new Color(0, 255, 0), false, true));
        // Material 3, Orange Wall
        materials.addElement(new Material(new Color(255, 128, 0), false, true));
        // Material 4, LightOrange Wall 
        materials.addElement(new Material(new Color(255, 224, 192), false, true));
        // Material 5, Blue Wall 
        materials.addElement(new Material(new Color(0, 0, 255), false, true));
        // Material 6, Dark Blue Wall 
        materials.addElement(new Material(new Color(0, 0, 128), false, true));
        // Material 7, Portal to next Tutorial
        materials.addElement(new Portal("ROTutE.lvl", false, true));
        // Material 8, Portal to Main Menu
        materials.addElement(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 31; a++) {
            rooms.addElement(new Room());
        }

        { // Room 0, Help Screen 
            Room room = rooms.elementAt(0);
            room.SetMaterialOutline(0, 0, 19, 11, 2);
            room.AddTextBox("SPECIAL KEYS",
                    7 * 28, 2 * 32, 500);
            room.AddTextBox("H",
                    2 * 28, 4 * 32, 100);
            room.AddTextBox("Make the Cursor \"hot\" with electricity or cold again. use it to test circuits.",
                    3 * 28, 4 * 32, 450);
            room.AddTextBox("T",
                    2 * 28, 7 * 32, 400);
            room.AddTextBox("Summon Toolkit to you room. Open/Close Toolkit in room.",
                    3 * 28, 7 * 32, 450);
            room.AddTextBox("To continue, press RETURN.",
                    4 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen 
            Room room = rooms.elementAt(1);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(19, 6, 19, 8, 0);
            room.SetMaterial(0, 10, 0);
            room.AddTextBox("{BIG} THE TOOLKIT", 4 * 28, 2 * 32, 500);
            room.AddTextBox("With what you have learned so far, you can make robots do a lot of simple tasks. But to get through the higher levels of Robotropolis, you will need to make circuits with parts from the Toolkit.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Notice that the cursor has changed. This cursor can be made \"hot\". You'll see how soon.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Freeze Electricity 
            Room room = rooms.elementAt(2);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 6, 19, 8, 0);
            room.AddTextBox("This circuit was made by connecting wires between the INPUTS and OUTPUTS of two parts from the Toolkit. Use the Remote Control to stop and start the flow of electricity in the circuit.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("The Remote Control starts and stops electricity both inside and outside robots.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            Node node = new Node(10 * 28, 5 * 32, room, Node.TYPE_STRAIGHT);
            NOTGate notgate = new NOTGate(15 * 28, 6 * 32, room);
            items.addElement(node);
            items.addElement(notgate);
            Wire wire = new Wire(node.ports[0], notgate.ports[1]);
            wire = new Wire(notgate.ports[0], node.ports[2]);
        }
        { // Room 3, Move parts 
            Room room = rooms.elementAt(3);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 6, 0, 8, 0);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.AddTextBox("It's hard to see where the wires connect in this circuit, but you can fix that. Pick up one of the parts. When you move, the wires will \"stretch\" to follow the part.",
                    2 * 28, 2 * 32, 450);
            room.AddTextBox("However, the wire will break if you stretch it out of the room.",
                    2 * 28, 9 * 32, 350);
            room.AddArrow(17 * 28, 559, Arrow.DIR_DOWN, 28, Color.white);
            Node node = new Node(10 * 28, 7 * 32, room, Node.TYPE_STRAIGHT);
            NOTGate notgate = new NOTGate(15 * 28, 6 * 32, room);
            items.addElement(node);
            items.addElement(notgate);
            Wire wire = new Wire(node.ports[0], notgate.ports[1]);
            wire = new Wire(notgate.ports[0], node.ports[2]);
        }
        { // Room 4, Toolkit 
            Room room = rooms.elementAt(4);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.AddTextBox("This is the TOOLKIT. Inside are parts you need to wire circuits.",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("Take the Toolkit into the next room.",
                    2 * 28, 5 * 32, 300);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            toolbox = new ToolBox(16 * 28, 9 * 32, room);
            items.addElement(toolbox);
        }
        { // Room 5, Open & close 
            Room room = rooms.elementAt(5);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.SetMaterialOutline(1, 11, 3, 11, 0);
            room.AddTextBox("You can open and close the Toolkit by pressing T. Try it a few times.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Leave the Toolkit here.",
                    2 * 28, 4 * 32, 300);
            room.AddTextBox("Drop Toolkit here.",
                    2 * 28, 7 * 32, 200);
            room.AddArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.AddArrow(7 * 28, 8 * 32, Arrow.DIR_RIGHT, 56, Color.white);
        }
        { // Room 6, Toolkit Sentry 
            Room room = rooms.elementAt(6);
            room.RoomArray = new int[][]{
                    {3, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3},
                    {3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0},
                    {3, 0, 0, 3, 3, 3, 3, 3, 3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 0},
                    {3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 3, 3, 0, 0, 3},
                    {3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.AddTextBox("NO TOOLKITS ALLOWED!",
                    2 * 28, 2 * 32, 150);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
            items.add(new SentryT2(2 * 28, 2 * 32, room));
        }
        { // Room 7, Summon 
            Room room = rooms.elementAt(7);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 4, 0, 7, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("If the Toolkit is nowhere in sight, you can summon it to you. (Think of it as sitting in your pocket wherever you go.)",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Press T to summon the Toolkit. Drop it and press T to open and close it.",
                    2 * 28, 9 * 32, 400);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 8, Objects in Toolkit 
            Room room = rooms.elementAt(8);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 8, 19, 10, 0);
            room.AddTextBox("You can take parts in and out of the Toolkit with the cursor.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To take a part out, move on top of it and press the SPACEBAR. To put it back, drop it anywhere in the Toolkit.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Open Toolkit here.",
                    3 * 28, 9 * 32, 100);
            room.AddArrow(7 * 28, 10 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 9, Rotating Objects 
            Room room = rooms.elementAt(9);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.AddTextBox("You can rotate parts left and right by holding a part and pressing the [ and ] keys.",
                    2 * 28, 2 * 32, 450);
            room.AddTextBox("There is no limit to the number of parts you can take out of the Toolkit.",
                    2 * 28, 5 * 32, 450);
            room.AddTextBox("Open Toolkit here.",
                    3 * 28, 9 * 32, 100);
            room.AddArrow(7 * 28, 10 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 10, Hot Cursor 
            Room room = rooms.elementAt(10);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.SetMaterialOutline(1, 11, 4, 11, 0);
            room.AddTextBox("The cursor here is special. It can be made \"HOT\" with electricity to test parts.",
                    4 * 28, 2 * 32, 300);
            room.AddTextBox("Press H to make the cursor hot.",
                    4 * 28, 5 * 32, 300);
            room.AddTextBox("Pass the hot cursor over the antenna INPUT to turn the antenna on.",
                    5 * 28, 7 * 32, 400);
            room.AddTextBox("Press H again to make the cursor cold.",
                    5 * 28, 10 * 32, 400);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
        }
        { // Room 11, Hot Cursor 2 
            Room room = rooms.elementAt(11);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(1, 0, 4, 0, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("You can also use the hot cursor to move a robot. Just sit on the thruster input and the robot will move.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The hot cursor is available for testing circuits in the Innovation Lab. Unfortunately you can't use it in Robotropolis. (It would spoil the game.)",
                    2 * 28, 7 * 32, 400);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            OrangeRobot robot = new OrangeRobot(13 * 28, 4 * 32, room);
            robot.thrusterPower = true;
            items.addElement(robot);
        }
        { // Room 12, Crossroads intro 
            Room room = rooms.elementAt(12);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 8, 19, 10, 0);
            room.AddTextBox("The next room is called the CROSSROADS. From it you can follow various paths to learn about different parts in the Toolkit.",
                    2 * 28, 2 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 13, Crossroads 
            Room room = rooms.elementAt(13);
            room.RoomArray = new int[][]{
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6}
            };
            room.AddTextBox("{BIG} CROSSROADS",
                    3 * 28, 4 * 32, 500);
            room.AddTextBox("(Turn the Remote Control on.)",
                    4 * 28, 5 * 32, 250);
            room.AddTextBox("NODES",
                    2 * 28, 32 + 16, 300);
            room.AddTextBox("FLIPFLOPS",
                    15 * 28, 32 + 16, 300);
            room.AddTextBox("LOGIC GATES",
                    16 * 28, 6 * 32, 100);
            room.AddTextBox("When you have learned all about the Toolkit, go this way.",
                    4 * 28, 9 * 32, 300);
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(17 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 14, Paths to Nodes & FlipFlops 
            Room room = rooms.elementAt(14);
            room.RoomArray = new int[][]{
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6}
            };
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 15, Nodes Intro 
            Room room = rooms.elementAt(15);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Nodes make electricity branch to two or three places.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("Take a node from the Toolkit and carry it to the next room.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.addElement(new Node(13 * 28, 32 + 16, room, Node.TYPE_STRAIGHT));
            items.addElement(new Node(15 * 28, 32 + 16, room, Node.TYPE_RIGHT));
            items.addElement(new Node(17 * 28, 32 + 16, room, Node.TYPE_THREE));
        }
        { // Room 16, Nodes Workshop 
            Room room = rooms.elementAt(16);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Wire the INPUT (circle) of the node to the OUTPUT of the CONTACT sensor. Wire the node outputs to the thrusters.",
                    2 * 28, 2 * 32, 275);
            room.AddTextBox("Drop the blue key so that it touches the sensor. Electricity flows to both thrusters.",
                    2 * 28, 9 * 32, 500);
            items.addElement(new Key(11 * 28, 2 * 32, room, Color.white));
            items.addElement(new ContactSensor(11 * 28, 4 * 32, room, new Key(0, 0, null, Color.white)));
            items.addElement(new Thruster(15 * 28, 32 + 16, room, Port.ROT_UP, Color.white));
            items.addElement(new Thruster(16 * 28, 3 * 32, room, Port.ROT_RIGHT, Color.white));
        }
        { // Room 17, Flipflop intro 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("A flipflop is like a light switch. It 'flips' electricity from one output to the other.",
                    5 * 28, 2 * 32, 300);
            room.AddTextBox("Take a flipflop from the Toolkit. Put the hot cursor on one input at a time to make the electricity 'flip' or 'flop'.",
                    5 * 28, 6 * 32, 300);
            items.addElement(new FlipFlop(17 * 28 + 7, 32 + 16, room));
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 18, Flipflop workshop 
            Room room = rooms.elementAt(18);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Go inside the robot and wire the orange OUTPUT of the flipflop to the thruster on the right. Sit on the eye to see what you did.",
                    5 * 28, 8 * 32, 420);
            BlueRobot robot = new BlueRobot(9 * 28, 4 * 32, room);
            items.addElement(robot);
            FlipFlop ff = new FlipFlop(10 * 28, 5 * 32, robot.InternalRoom);
            items.addElement(ff);
            Wire wire = new Wire(ff.ports[0], robot.devices[7].ports[0]);
            wire = new Wire(ff.ports[2], robot.devices[3].ports[0]);
            wire = new Wire(robot.devices[5].ports[0], ff.ports[1]);
            robot.thrusterPower = true;
        }
        { // Room 19, Crossroads II 
            Room room = rooms.elementAt(19);
            room.RoomArray = new int[][]{
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };
            room.AddTextBox("Crossroads",
                    2 * 28, 6 * 32 + 8, 500);
            room.AddTextBox("NOT-gate",
                    2 * 28, 32 + 16, 500);
            room.AddTextBox("AND-gate",
                    15 * 28, 2 * 32 + 16, 500);
            room.AddTextBox("XOR-gate",
                    15 * 28, 10 * 32, 500);
            room.AddTextBox("OR-gate",
                    2 * 28, 10 * 32 + 16, 500);
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 2 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.AddArrow(0, 6 * 32, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 20, NOT gate intro 
            Room room = rooms.elementAt(20);
            room.RoomArray = new int[][]{
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("A NOT-gate inverts electricity flow. It turns on when its INPUT is NOT on.",
                    5 * 28, 2 * 32, 300);
            room.AddTextBox("Take a NOT-gate from the Toolkit and use the hot cursor to see how it works.",
                    5 * 28, 6 * 32, 400);
            items.addElement(new NOTGate(17 * 28 + 10, 32 + 12, room));
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 21, NOT gate workshop 
            Room room = rooms.elementAt(21);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Wire the NOT-gate output to the Antenna INPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The antenna beeps when the crystal is NOT touching the CONTACT sensor.",
                    5 * 28, 8 * 32, 400);
            items.addElement(new Crystal(3 * 28, 7 * 32, room, 100000));
            ContactSensor sensor = new ContactSensor(2 * 28, 6 * 32, room, new Crystal(0, 0, null, 0));
            items.addElement(sensor);
            NOTGate ng = new NOTGate(4 * 28, 4 * 32, room);
            items.addElement(ng);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
            Wire wire = new Wire(sensor.ports[0], ng.ports[0]);
        }
        { // Room 22, OR gate intro 
            Room room = rooms.elementAt(22);
            room.RoomArray = new int[][]{
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5}
            };
            room.AddTextBox("An OR-gate turns on when one OR the other INPUT is on, or both.",
                    5 * 28, 2 * 32, 300);
            room.AddTextBox("Take an OR-gate from the Toolkit and use the hot cursor to see how it works.",
                    2 * 28, 8 * 32, 400);
            items.addElement(new ORGate(17 * 28 + 10, 32 + 12, room));
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 23, OR workshop 
            Room room = rooms.elementAt(23);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Wire the OR-gate output to the Antenna INPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The antenna beeps when the key is above OR left of the sensor.",
                    5 * 28, 10 * 32, 400);
            items.addElement(new Key(3 * 28, 7 * 32, room, Color.blue));
            DirectionalSensor sensor = new DirectionalSensor(8 * 28, 6 * 32, room,
                    new Key(0, 0, null, Color.white));
            items.addElement(sensor);
            ORGate og = new ORGate(4 * 28, 4 * 32, room);
            items.addElement(og);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
            Wire wire = new Wire(sensor.ports[0], og.ports[1]);
            wire = new Wire(sensor.ports[3], og.ports[0]);
        }
        { // Room 24, Paths to AND & XOR 
            Room room = rooms.elementAt(24);
            room.RoomArray = new int[][]{
                    {6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };
            room.AddArrow(9 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(5 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 25, AND gate intro 
            Room room = rooms.elementAt(25);
            room.RoomArray = new int[][]{
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("An AND-gate turns on when its left AND right INPUTS are on.",
                    5 * 28, 2 * 32, 300);
            room.AddTextBox("Take a AND-gate from the Toolkit and test it with the hot cursor.",
                    5 * 28, 6 * 32, 400);
            items.addElement(new ANDGate(17 * 28 + 10, 32 + 12, room));
            room.AddArrow(3 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 26, AND gate workshop 
            Room room = rooms.elementAt(26);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Wire the AND-gate output to the Antenna INPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The antenna beeps when the key is left AND up from this DIRECTIONAL sensor.",
                    5 * 28, 9 * 32, 400);
            items.addElement(new Key(3 * 28, 7 * 32, room, Color.blue));
            DirectionalSensor sensor = new DirectionalSensor(5 * 28, 6 * 32, room,
                    new Key(0, 0, null, Color.white));
            items.addElement(sensor);
            ANDGate ag = new ANDGate(4 * 28, 4 * 32, room);
            items.addElement(ag);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
            Wire wire = new Wire(sensor.ports[0], ag.ports[1]);
            wire = new Wire(sensor.ports[3], ag.ports[0]);
        }
        { // Room 27, XOR gate intro 
            Room room = rooms.elementAt(27);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5}
            };
            room.AddTextBox("An EXCLUSIVE-OR- gate (XOR-gate) turns on when one OR the other INPUT is on, NOT both.",
                    7 * 28, 2 * 32, 250);
            room.AddTextBox("Take an XOR-gate from the Toolkit and test it with the hot cursor.",
                    2 * 28, 9 * 32, 400);
            items.addElement(new XORGate(17 * 28 + 10, 32 + 12, room));
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 28, XOR workshop 
            Room room = rooms.elementAt(28);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
            };
            room.AddTextBox("Wire the XOR-gate output to the Antenna INPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The antenna beeps when either the key or the crystal is in the room, but not both.",
                    5 * 28, 9 * 32, 400);
            items.addElement(new Key(2 * 28, 9 * 32, room, Color.blue));
            items.addElement(new Crystal(2 * 28, 7 * 32, room, 100000));
            RoomSensor sensor1 = new RoomSensor(8 * 28, 6 * 32, room, new Key(0, 0, null, Color.white));
            RoomSensor sensor2 = new RoomSensor(8 * 28, 7 * 32, room, new Crystal(0, 0, null, 0));
            sensor1.rotate(1);
            sensor1.rotate(1);
            sensor2.rotate(1);
            sensor2.rotate(1);
            items.addElement(sensor1);
            items.addElement(sensor2);
            XORGate xg = new XORGate(4 * 28, 4 * 32, room);
            items.addElement(xg);
            items.addElement(new Antenna(2 * 28, 2 * 32, room, Color.white));
            Wire wire = new Wire(sensor1.ports[0], xg.ports[1]);
            wire = new Wire(sensor2.ports[0], xg.ports[0]);
        }
        { // Room 29, End 
            Room room = rooms.elementAt(29);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(8, 0, 11, 0, 0);
            room.SetMaterial(19, 10, 0);
            room.SetMaterial(4, 7, 7);
            room.SetMaterial(4, 9, 8);
            room.AddTextBox("Now you know what the parts in the Toolkit do. To find out how to make simple circuits inside robots, continue with Robot Circuits.",
                    2 * 28, 4 * 32, 450);
            room.AddTextBox("Learn about Robot Circuits",
                    5 * 28, 8 * 32, 450);
            room.AddTextBox("Return to the Main Menu",
                    5 * 28, 10 * 32, 450);
        }
        { // Room 30, Shortcut to beginning 
            Room room = rooms.elementAt(30);
            room.SetMaterialOutline(0, 0, 19, 9, 1);
            room.SetMaterialOutline(0, 11, 19, 11, 1);
            room.SetMaterial(0, 10, 0);
            room.SetMaterial(19, 10, 0);
            room.AddTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }

        int[] list0 = {29, 30, 1, 2, 3};
        LinkRoomsHorizontally(list0);
        LinkRoomsUpDown(3, 4);
        LinkRoomsLeftRight(5, 4);
        LinkRoomsUpDown(5, 6);
        LinkRoomsLeftRight(6, 7);
        LinkRoomsLeftRight(7, 8);
        LinkRoomsLeftRight(8, 9);
        LinkRoomsUpDown(9, 10);
        LinkRoomsUpDown(10, 11);
        int[] list1 = {11, 12, 13, 19, 24};
        LinkRoomsHorizontally(list1);
        int[] list2 = {18, 17, 14, 13, 29};
        LinkRoomsVertically(list2);
        int[] list3 = {21, 20, 19, 22, 23};
        LinkRoomsVertically(list3);
        int[] list4 = {26, 25, 24, 27, 28};
        LinkRoomsVertically(list4);
        LinkRoomsLeftRight(16, 15);
        LinkRoomsLeftRight(15, 14);


        gameCursor = new LabCursor(17 * 28 + 14, 7 * 32 + 16, rooms.elementAt(1));
        helpCam = new HelpCam(rooms.elementAt(0));
        solderingPen = new SolderingPen();
        remote = new Remote();
        items.addElement(gameCursor);
        items.addElement(helpCam);
        items.addElement(solderingPen);
        items.addElement(remote);
        player = gameCursor;
        currentViewer = player;

    }

}
