package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.PortDevice;
import com.droidquest.devices.Thruster;
import com.droidquest.items.BlueRobot;
import com.droidquest.items.OrangeRobot;
import com.droidquest.items.WhiteRobot;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTutB extends Level {
    public ROTutB(RoomDisplay rd) {
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
        // Material 6, Portal to Tutorial C;
        materials.addElement(new Portal("ROTutC.lvl", false, true));
        // Material 7, Portal to Main Menu;
        materials.addElement(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 26; a++) {
            rooms.addElement(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.elementAt(0);
            room.SetMaterialOutline(0, 0, 19, 11, 2);
            room.AddTextBox("Circuits are made of wires connected to INPUTS and OUTPUTS. Circuits make robots move, grab objects, and send signals.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Press S to use the Solderpen to connect an INPUT to an OUTPUT.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Press C to become the Cursor again.",
                    2 * 28, 6 * 32, 500);
            room.AddTextBox("Use SPACEBAR or RIGHT BUTTON to connect or disconnect wires from INPUTS or OUTPUTS when soldering.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("To continue, press RETURN",
                    5 * 28, 11 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.elementAt(1);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.SetMaterial(0, 10, 0);
            room.AddTextBox("{BIG} ROBOT WIRING", 4 * 28, 2 * 32, 500);
            room.AddTextBox("The robots in the Robotropolis Sewer are prewired to work for you. In the subway and higher levels of Robotropolis you need to change the wiring.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Here you will find out how to wire a robot.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Circuit basics
            Room room = rooms.elementAt(2);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 5, 0, 7, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("The wiring inside a robot makes it move, pick up objects, and send signals. This wiring is called a CIRCUIT.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To create a circuit in a robot you solder wires to the INPUTS and OUTPUTS of various robot parts.",
                    2 * 28, 5 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 3, Input & Scanner
            Room room = rooms.elementAt(3);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 0, 3, 3, 1);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("This is an INPUT. Go inside Scanner and look for all the INPUTS. You will see them on the four thrusters, the grabber control, and the antenna control.",
                    5 * 28, 2 * 32, 375);
            room.AddTextBox("Take Scanner with you.",
                    5 * 28, 7 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new BlueRobot(9 * 28, 8 * 32, room));
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_INPUT);
            items.addElement(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 4, Output
            Room room = rooms.elementAt(4);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 0, 3, 3, 1);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("This is an OUTPUT. Inside the robot you will see outputs on the graber control, the four bumpers, and the antenna control. When a robot part is activated, electricity flows OUT of it's OUTPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("Take Scanner with you.",
                    5 * 28, 8 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_OUTPUT);
            items.addElement(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 5, First demo
            Room room = rooms.elementAt(5);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(7, 11, 10, 11, 0);
            room.SetMaterial(19, 10, 0);
            room.AddTextBox("Move Scanner so one bumper just touches a wall. (Be sure the Remote Control is on.) The robot squawks and the outside bumper turns orange. Go inside and look at the output for that bumper. It is also orange. Electricity is flowing OUT of it's OUTPUT.",
                    2 * 28, 2 * 32, 450);
            room.AddTextBox("Leave Scanner here.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(9 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 6, Solderpen
            Room room = rooms.elementAt(6);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 0, 5, 3, 1);
            room.SetMaterialOutline(7, 0, 10, 0, 0);
            room.SetMaterialOutline(5, 11, 8, 11, 0);
            room.AddTextBox("The Solderpen is used to wire OUTPUTS to INPUTS.",
                    11 * 28, 2 * 32, 230);
            room.AddTextBox("Press S to become the Solderpen.",
                    11 * 28, 6 * 32, 230);
            room.AddArrow(7 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            SolderingPen sp = new SolderingPen();
            sp.x = 3 * 28 + 14;
            sp.y = 32 + 16;
            sp.room = room;
            items.addElement(sp);
            room.AddTextBox("TIP", 1 * 28, 2 * 32 + 10, 200);
            room.AddArrow(3 * 28 + 12, 2 * 32 + 4, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 7, Attach wires
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
            };
            room.AddTextBox("Move the Solderpen to the INPUT until the tip glows orange.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Press Spacebar or RIGHT BUTTON to begin soldering. Move the Solderpen to the OUTPUT. (A wire will follow you.) When the tip glows orange press SPACEBAR to conenct the wire.",
                    2 * 28, 7 * 32, 400);
            room.AddArrow(0, 3 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.addElement(new Thruster(17 * 28, 9 * 32, room, Port.ROT_RIGHT, Color.blue));
            PortDevice pd = new PortDevice(15 * 28, 10 * 32, room, 28, Port.TYPE_OUTPUT);
            pd.value = true;
            items.addElement(pd);
        }
        { // Room 8, Detatch wires
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
            };
            room.AddTextBox("It's easy to disconnect a wire.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Move the tip of the Solderpen to either the INPUT or OUTPUT. Press the SPACEBAR when the tip glows green.",
                    2 * 28, 8 * 32, 350);
            room.AddArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            Thruster thruster = new Thruster(17 * 28, 9 * 32, room, Port.ROT_RIGHT, Color.blue);
            items.addElement(thruster);
            PortDevice pd = new PortDevice(15 * 28, 10 * 32, room, 28, Port.TYPE_OUTPUT);
            pd.value = true;
            items.addElement(pd);
            Wire wire = new Wire(thruster.ports[0], pd.ports[0]);
        }
        { // Room 9, Sparky
            Room room = rooms.elementAt(9);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(19, 4, 19, 6, 0);
            room.SetMaterialOutline(8, 11, 11, 11, 0);
            room.AddTextBox("Now you are ready to create a circuit in a robot. Press C to use the cursor again.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Take Sparky with you through the next few rooms to make a wall-hugging robot.",
                    2 * 28, 8 * 32, 300);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            OrangeRobot robot = new OrangeRobot(14 * 28, 8 * 32, room);
            robot.charge = 100000;
            robot.thrusterPower = true;
            robot.InternalRoom.AddTextBox("A", 3 * 28, 8 * 32, 100);
            robot.InternalRoom.AddTextBox("B", 6 * 28, 11 * 32, 100);
            robot.InternalRoom.AddTextBox("C", 14 * 28, 11 * 32, 100);
            robot.InternalRoom.AddTextBox("D", 16 * 28, 8 * 32, 100);
            robot.InternalRoom.AddTextBox("E", 16 * 28, 5 * 32, 100);
            robot.InternalRoom.AddTextBox("F", 15 * 28, 2 * 32, 100);
            robot.InternalRoom.AddTextBox("G", 7 * 28, 2 * 32, 100);
            robot.InternalRoom.AddTextBox("H", 3 * 28, 5 * 32, 100);
            items.addElement(robot);
        }
        { // Room 10, A to B
            Room room = rooms.elementAt(10);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(8, 0, 11, 0, 0);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.AddTextBox("Press S to use the Solderpen again.",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("Go inside Sparky and connect the left thruster (marked A) to the bottom bumper (marked B). Be sure the Remote Control and the robot thruster switch are on.",
                    2 * 28, 6 * 32, 300);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 11, First test
            Room room = rooms.elementAt(11);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("Place Sparky next to the bottom wall so it's bumper just touches the wall. When the bumper touches the wall, electricity flows from the bumper to the thruster, propelling the robot.",
                    2 * 28, 2 * 32, 300);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 12, Continuing
            Room room = rooms.elementAt(12);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialOutline(0, 8, 19, 10, 0);
            room.AddTextBox("When the left thruster is on, Sparky moves to the right. The thruster pushes the robot in the opposite direction.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("What will happen when the right thruster is on?",
                    2 * 28, 5 * 32, 450);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 13, C to D
            Room room = rooms.elementAt(13);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.AddTextBox("Sparky stops at the right wall. To make Sparky move up, connect a wire between the bottom thruster (C) and the right bumper (D). Now since Sparky is touching the right wall, electricity flows into the bottom thruster and Sparky moves up.",
                    2 * 28, 2 * 32, 300);
            room.AddArrow(17 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 14, Flipping wires
            Room room = rooms.elementAt(14);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(14, 4, 19, 4, 1);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.SetMaterialOutline(19, 1, 19, 3, 0);
            room.AddTextBox("The wire between C and D is hard to see. Move the Solderpen over the INPUT or OUTPUT until the tip turns green. Press F. This will flip the wire so it will be easier to see.",
                    2 * 28, 2 * 32, 300);
            room.AddArrow(559, 2 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 15, E to F, H to G
            Room room = rooms.elementAt(15);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(12, 6, 19, 11, 1);
            room.SetMaterialOutline(0, 1, 0, 3, 0);
            room.SetMaterialOutline(5, 0, 8, 0, 0);
            room.SetMaterial(12, 10, 0);
            room.AddTextBox("Now go back inside Sparky and connect E to F and H to G. Put Sparky in the small chamber so one bumper touches a wall and watch it go!",
                    9 * 28, 2 * 32, 300);
            room.AddTextBox("You have just created you first robot circuit!",
                    2 * 28, 8 * 32, 200);
            room.AddArrow(7 * 28, 0, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 16, Clockwise?
            Room room = rooms.elementAt(16);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(12, 6, 19, 11, 1);
            room.SetMaterial(12, 10, 0);
            room.SetMaterialOutline(5, 11, 8, 11, 0);
            room.SetMaterialOutline(6, 0, 9, 0, 0);
            room.SetMaterialOutline(19, 3, 19, 4, 0);
            room.AddTextBox("Sparky moves counter- clockwise.",
                    2 * 28, 2 * 32, 200);
            room.AddTextBox("Can you rewire it to go clockwise instead?",
                    2 * 28, 5 * 32, 200);
            room.AddTextBox("Leave Sparky here when you are done.",
                    2 * 28, 9 * 32, 200);
            room.AddTextBox("SOLUTION",
                    15 * 28, 4 * 32 + 8, 300);
            room.AddArrow(8 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 4 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 17, Clockwise solution
            Room room = rooms.elementAt(17);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 3, 0, 4, 0);
            room.AddTextBox("To make a clockwise wall-hugging robot, connect A to F, D to G, E to B, and H to C.",
                    4 * 28, 4 * 32, 400);
        }
        { // Room 18, Checkers
            Room room = rooms.elementAt(18);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(6, 11, 9, 11, 0);
            room.SetMaterialOutline(13, 0, 16, 0, 0);
            room.AddTextBox("Robot can signal other robots with their antennas. Connect a wire from Checker's left bumper (A) to it's antenna INPUT (B).",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("Take Checkers with you.",
                    11 * 28, 10 * 32, 200);
            room.AddArrow(15 * 28, 0, Arrow.DIR_UP, 28, Color.white);
            WhiteRobot robot = new WhiteRobot(3 * 28, 9 * 32, room);
            items.addElement(robot);
            robot.charge = 100000;
            robot.InternalRoom.AddTextBox("A", 3 * 28, 5 * 32, 100);
            robot.InternalRoom.AddTextBox("B", 4 * 28, 4 * 32, 100);
        }
        { // Room 19, X marks the spot
            Room room = rooms.elementAt(19);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(13, 11, 16, 11, 0);
            room.SetMaterialOutline(19, 7, 19, 9, 0);
            room.SetMaterial(1, 0, 0);
            room.AddTextBox("Take the short cut and bring back Scanner. Connect a wire from Scanner's antenna OUTPUT to one of it's thrusters.",
                    4 * 28, 3 * 32, 450);
            room.AddTextBox("Now bump Checkers into the wall on the left and watch Scanner move! Put Scanner on the X and take Checkers with you.",
                    4 * 28, 6 * 32, 450);
            room.AddTextBox("SHORT CUT",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("{BIG} {000,255,000} X",
                    7 * 28, 9 * 32, 300);
            room.AddArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(28 + 14, 32, Arrow.DIR_UP, 28, Color.white);
        }
        { // Room 20, Shortcut
            Room room = rooms.elementAt(20);
            room.SetMaterialFill(0, 0, 19, 11, 5);
            room.SetMaterial(0, 10, 0);
            room.SetMaterial(1, 10, 0);
            room.SetMaterial(1, 11, 0);
        }
        { // Room 21, Antenna talk
            Room room = rooms.elementAt(21);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 7, 0, 9, 0);
            room.SetMaterialOutline(8, 11, 11, 11, 0);
            room.AddTextBox("An antenna signal is tranmitted to all the robots at once, even if they aren't in the same room. Bump Checkers into the left wall again so the antenna beeps. Then look back into the previous room. Scanner is no longer on the X.",
                    2 * 28, 2 * 32, 450);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 22, more radio talk
            Room room = rooms.elementAt(22);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(8, 0, 11, 0, 0);
            room.SetMaterialOutline(19, 7, 19, 9, 0);
            room.AddTextBox("Robot radios have only one channel. That means that if all the robots try to send signals at once, only one robot's signal gets through.",
                    2 * 28, 5 * 32, 400);
            room.AddArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 23, End of tutorial
            Room room = rooms.elementAt(23);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 7, 0, 9, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("Now you know the basics of wiring circuits in robots. Before returning to Robotropolis, we suggest you learn how to use Sensors and parts from the Toolkit to make some useful robot circuits.",
                    2 * 28, 2 * 32, 500);
        }
        { // Room 24, Portals
            Room room = rooms.elementAt(24);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterial(19, 10, 0);
            room.SetMaterial(4, 2, 6);
            room.SetMaterial(4, 5, 7);
            room.AddTextBox("Learn about Robot Wiring",
                    5 * 28, 3 * 32, 400);
            room.AddTextBox("Return to the Main Menu",
                    5 * 28, 6 * 32, 400);
        }
        { // Room 25, Shortcut to beginning
            Room room = rooms.elementAt(25);
            room.SetMaterialOutline(0, 0, 19, 9, 1);
            room.SetMaterialOutline(0, 11, 19, 11, 1);
            room.SetMaterial(0, 10, 0);
            room.SetMaterial(19, 10, 0);
            room.AddTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }


        int[] list1 = {22, 23, 24, 25, 1, 2, 3, 4, 5, 20};
        LinkRoomsHorizontally(list1);
        LinkRoomsUpDown(5, 6);
        LinkRoomsUpDown(6, 7);
        LinkRoomsLeftRight(8, 7);
        LinkRoomsLeftRight(9, 8);
        LinkRoomsUpDown(9, 10);
        LinkRoomsUpDown(10, 11);
        LinkRoomsLeftRight(11, 12);
        LinkRoomsLeftRight(12, 13);
        LinkRoomsUpDown(14, 13);
        LinkRoomsLeftRight(14, 15);
        int[] list2 = {20, 19, 18, 16, 15};
        LinkRoomsVertically(list2);
        LinkRoomsLeftRight(16, 17);
        LinkRoomsLeftRight(19, 21);
        LinkRoomsUpDown(21, 22);


        gameCursor = new GameCursor(9 * 28 + 14, 9 * 32 + 16, rooms.elementAt(1));
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