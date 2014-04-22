package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.items.*;
import com.droidquest.materials.Material;
import com.droidquest.materials.PanicButton;
import com.droidquest.materials.Portal;
import com.droidquest.materials.switches.Switch;

import java.awt.*;

class ROTutF extends Level {
    public ROTutF(RoomDisplay rd) {
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
        // Material 7, Switch
        int[][] matprogram = {
                {Switch.WAIT4CONTACT},
                {Switch.SETVALUEHIGH},
                {Switch.REPLACE, 11, 1, 0, 11, 7, 5},
                {Switch.REPLACE, 11, 2, 0, 11, 8, 5},
                {Switch.REPLACE, 11, 3, 0, 11, 9, 5},
                {Switch.WAIT4REMOVAL},
                {Switch.REPLACE, 11, 3, 5, 11, 9, 0},
                {Switch.REPLACE, 11, 2, 5, 11, 8, 0},
                {Switch.REPLACE, 11, 1, 5, 11, 7, 0},
                {Switch.SETVALUELOW}
        };
        materials.addElement(new Switch(Switch.ROT_UP, matprogram));
        // Material 8, Switch, another
        materials.addElement(new Switch(Switch.ROT_UP, matprogram));
        // Material 9, Portal to next Tutorial
        materials.addElement(new Portal("ROTut3.lvl", false, true));
        // Material 10, Portal to Main Menu
        materials.addElement(new Portal("MainMenu.lvl", false, true));
        // Material 11, Panic Button #1
        materials.addElement(new PanicButton(1));
        // Material 12, Panic Button #2
        materials.addElement(new PanicButton(2));

        for (int a = 0; a < 22; a++) {
            rooms.addElement(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.elementAt(0);
            room.SetMaterialOutline(0, 0, 19, 11, 2);
            room.AddTextBox("If your circuit doesn't work, check the state of the flipflops. If the robot doesn't go at all, check the Remote Control, thruster switch, and battery.",
                    2 * 28, 2 * 32, 450);
            room.AddTextBox("To continue, press RETURN.",
                    4 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.elementAt(1);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(19, 7, 19, 9, 0);
            room.SetMaterial(0, 10, 0);
            room.AddTextBox("{BIG} ROBOT TEAMWORK", 3 * 28, 2 * 32, 500);
            room.AddTextBox("Some of the challenges you'll encounter in Robotropolis need robot teamwork. Here you'll see how to use two robots to solve puzzles.",
                    2 * 28, 4 * 32, 500);
            room.AddArrow(559, 8 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            toolbox = new ToolBox(3 * 28, 7 * 32, room);
            items.addElement(toolbox);
        }
        { // Room 2, Sparky & Checkers
            Room room = rooms.elementAt(2);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 7, 0, 9, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.SetMaterialOutline(13, 11, 16, 11, 0);
            room.AddTextBox("To solve the puzzle next door, you need two robots. One robot must push the button that opens the door to the crystal. The other must get the crystal.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("This sentry won't let you ride past inside a robot.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("(Use the PANIC BUTTON if your robot gets stuck.)",
                    2 * 28, 7 * 32, 500);
            room.AddTextBox("If you want help, try the following solution.",
                    2 * 28, 10 * 32, 300);
            room.AddTextBox("PUZZLE",
                    17 * 28, 10 * 32, 500);
            room.AddArrow(559, 10 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(15 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new OrangeRobot(11 * 28, 8 * 32, room));
            items.addElement(new WhiteRobot(14 * 28, 8 * 32, room));
        }
        { // Room 3, Puzzle
            Room room = rooms.elementAt(3);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(11, 0, 19, 4, 5);
            room.SetMaterialOutline(11, 0, 11, 6, 5);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterial(18, 5, 7);
            room.SetMaterial(1, 1, 11);
            room.AddTextBox("PANIC BUTTON",
                    2 * 28, 2 * 32, 150);
            room.AddTextBox("DOOR",
                    9 * 28, 3 * 32, 200);
            int[] pace = {3 * 28, 2 * 32, 3 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 20 * 28, 12 * 32, 0, 9 * 32};
            items.addElement(new Sentry(0, 0, room, pace, program, true));
            items.addElement(new Crystal(17 * 28, 2 * 32, room, 100000));
        }
        { // Room 4, Divide the tasks
            Room room = rooms.elementAt(4);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(13, 0, 17, 0, 0);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.AddTextBox("The problem has two parts:",
                    2 * 28, 2 * 32, 300);
            room.AddTextBox("1. Push door button",
                    2 * 28, 4 * 32, 300);
            room.AddTextBox("2. Retrieve crystal",
                    2 * 28, 5 * 32, 300);
            room.AddTextBox("Use one robot for each task.",
                    2 * 28, 7 * 32, 300);
            room.AddTextBox("Put Checkers inside Sparky and take both robots with you.",
                    2 * 28, 9 * 32, 300);
            room.AddTextBox("SOLUTION",
                    15 * 28 + 14, 11 * 32, 300);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 5, Assigning tasks
            Room room = rooms.elementAt(5);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.SetMaterialOutline(19, 4, 19, 6, 0);
            room.AddTextBox("Use Sparky to push the door button. You want Sparky to move right until it hits the right wall, and then up until it hits the top wall.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Sparky should stop there and stay on the button until Checkers has safely retrieved the crystal.",
                    2 * 28, 6 * 32, 400);
            room.AddArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 6, Sparky goes right
            Room room = rooms.elementAt(6);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 4, 0, 6, 0);
            room.SetMaterialOutline(15, 11, 18, 11, 0);
            room.AddTextBox("Use a flipflop and a node. Turn the Remote Control off before you start wiring.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To make Sparky go right until it hits a wall, connect the left thruster to the \"hot\" (on) side of the flipflop. Try it to see how it works!",
                    2 * 28, 8 * 32, 400);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 7, Sparky cont.
            Room room = rooms.elementAt(7);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(15, 0, 18, 11, 0);
            room.AddTextBox("Next you want Sparky to do two things when it hits the right wall. It should stop going right, and it should go up. Use a node.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Connect the node to the right bumper. The node will turn on when Sparky touches the wall on the right.",
                    2 * 28, 6 * 32, 400);
            room.AddTextBox("Where should you wire the node so Sparky will stop going right and move up?",
                    2 * 28, 9 * 32, 400);
            room.AddArrow(17 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 8, Try Sparky right & up
            Room room = rooms.elementAt(8);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(16, 6, 19, 6, 6);
            room.SetMaterialOutline(15, 0, 18, 0, 0);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.AddTextBox("Connect one OUTPUT of the node to the flipflop INPUT that is now off. That will stop Sparky from moving right. Connect the other OUTPUT of the node to the bottom thruster. Sparky will move up while touching the right wall.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Try it now.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 9, Sparky comes back
            Room room = rooms.elementAt(9);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.SetMaterialOutline(1, 11, 4, 11, 0);
            room.AddTextBox("The next step is to make Sparky come back after Checkers gets the crystal. Let Checkers signal Sparky with the antenna when it's okay to return.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Use another flipflop. Connect Sparky's antenna OUTPUT to the \"cold\" (off) INPUT of the flipflop.",
                    2 * 28, 5 * 32, 470);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 10, Sparky goes left & down
            Room room = rooms.elementAt(10);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(1, 0, 4, 0, 0);
            room.SetMaterialOutline(19, 3, 19, 5, 0);
            room.AddTextBox("For Sparky to return, it should move left and down. Use a node.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("Connect the node INPUT to the cold OUTPUT of the flipflop that you just wired to the antenna. Connect the node OUTPUTS to the top and right thrusters.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(559, 4 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 11, Test antenna
            Room room = rooms.elementAt(11);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 3, 19, 5, 0);
            room.AddTextBox("To test the new circuit, touch Sparky's antenna INPUT with the hot cursor. (Remote Control must be on.)",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("The antenna OUTPUT will turn on; the flipflop will flip, and both thrusters will turn on.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Use the hot cursor again to reset both flipflops (left sides off).",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Turn Sparky's thruster switch off and put Spark inside of Checkers.",
                    2 * 28, 10 * 32, 500);
            room.AddArrow(559, 4 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 12, Checkers task
            Room room = rooms.elementAt(12);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 3, 0, 5, 0);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.AddTextBox("The second task is to use Checkers to get the crystal.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Grabbing the crystal is easy. Connect a NOT-gate to Checkers' grabber INPUT.",
                    2 * 28, 10 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 13, How to go right & left?
            Room room = rooms.elementAt(13);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 5, 19, 7, 0);
            room.AddTextBox("Here is one way to move Checkers to the crystal. Connect a wire from the left thruster to the hot side of a flipflop. Checkers will move right.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Should you use the right bumper to flip the flipflop and make Checkers return? Or is there a better way?",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 14, Use the grabber output
            Room room = rooms.elementAt(14);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 5, 0, 7, 0);
            room.SetMaterialOutline(19, 6, 19, 8, 0);
            room.AddTextBox("You don't want to use the right bumper to stop Checkers, since the door may not be open when Checkers reaches it. You want Checkers to keep moving right until it grabs the crystal.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("When Checkers gabs the crystal, the grabber OUTPUT will come on. Use that to flip the flipflop and make Checkers return.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 15, Test grabber
            Room room = rooms.elementAt(15);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 6, 19, 8, 0);
            room.AddTextBox("Connect a wire from the grabber OUTPUT to the cold flipflop input. Connect another wire from the cold flipflop OUTPUT to the right thruster. When Checkers grabs the crystal, the flipflop will flip and and Checkers will move left.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Test your circuit by letting Checkers grab this crystal. Then restore the original circuit.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 7 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Crystal(9 * 28, 6 * 32, room, 100000));
        }
        { // Room 16, Checkers signals Sparky
            Room room = rooms.elementAt(16);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 6, 0, 9, 0);
            room.SetMaterialOutline(1, 11, 4, 11, 0);
            room.AddTextBox("The last step is for Checkers to let Sparky know it's time to come back. Connect a wire from Checkers' left bumper to it's antenna. When Checkers comes back after grabbing the crystal it will hit the wall and beep.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("You can test that if you want. Be sure to reset all the flipflops with the hot cursor before you continue (left sides off).",
                    5 * 28, 8 * 32, 400);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 17, Big test
            Room room = rooms.elementAt(17);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(1, 0, 4, 11, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("Now it's time for the real thing! Turn the Remote Control off and the thruster switches on. Put Sparky on the S and Checkers on the C next door. Turn the Remote Control on.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("(Use the PANIC BUTTON to stop the sentry if you get stuck. Press it again to restart it.)",
                    5 * 28, 7 * 32, 400);
            room.AddTextBox("GOOD LUCK!",
                    5 * 28, 10 * 32, 500);
            room.AddTextBox("PUZZLE",
                    17 * 28, 10 * 32, 500);
            room.AddArrow(559, 10 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddArrow(3 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 18, Puzzle again
            Room room = rooms.elementAt(18);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(11, 0, 19, 4, 5);
            room.SetMaterialOutline(11, 0, 11, 6, 5);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterial(18, 5, 8);
            room.SetMaterial(1, 1, 12);
            room.AddTextBox("{BIG} C",
                    3 * 28, 3 * 32, 500);
            room.AddTextBox("{BIG} S",
                    3 * 28, 10 * 32, 500);
            room.AddTextBox("PANIC BUTTON",
                    4 * 28, 2 * 32, 200);
            int[] pace = {3 * 28, 2 * 32, 3 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 20 * 28, 12 * 32, 0, 9 * 32};
            items.addElement(new Sentry(0, 0, room, pace, program, true));
            items.addElement(new Crystal(17 * 28, 2 * 32, room, 100000));
        }
        { // Room 19, End
            Room room = rooms.elementAt(19);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(1, 0, 4, 0, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("You have learned some handy robot circuits. Now you are ready to move through the Town of Robotropolis. As you journey there, remember to solve the puzzles one step at a time, and use the Lab to test your ideas.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("The levels above the Town are most easily solved with chips. Explore Chip design after you leave Town.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 20, Portals
            Room room = rooms.elementAt(20);
            room.SetMaterialOutline(0, 0, 19, 11, 5);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterial(19, 10, 0);
            room.SetMaterial(4, 2, 9);
            room.SetMaterial(4, 5, 10);
            room.AddTextBox("Learn about Chip Design.",
                    5 * 28, 3 * 32, 500);
            room.AddTextBox("Return to the Main Menu.",
                    5 * 28, 6 * 32, 500);
        }
        { // Room 21, Shortcut
            Room room = rooms.elementAt(21);
            room.SetMaterialOutline(0, 0, 19, 9, 5);
            room.SetMaterialOutline(0, 11, 19, 11, 5);
            room.SetMaterial(0, 10, 0);
            room.SetMaterial(19, 10, 0);
            room.AddTextBox("{BIG} {000,255,000} SHORTCUT",
                    172, 6 * 32, 400);
        }

        int[] list1 = {19, 20, 21, 1, 2, 3};
        LinkRoomsHorizontally(list1);
        LinkRoomsUpDown(2, 4);
        LinkRoomsUpDown(4, 5);
        LinkRoomsLeftRight(5, 6);
        LinkRoomsUpDown(6, 7);
        LinkRoomsUpDown(7, 8);
        LinkRoomsLeftRight(9, 8);
        LinkRoomsUpDown(9, 10);
        int[] list2 = {10, 11, 12, 13, 14, 15, 16};
        LinkRoomsHorizontally(list2);
        LinkRoomsUpDown(16, 17);
        LinkRoomsLeftRight(17, 18);
        LinkRoomsUpDown(17, 19);

        gameCursor = new LabCursor(16 * 28 + 14, 9 * 32 + 16, rooms.elementAt(1));
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