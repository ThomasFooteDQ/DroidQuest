package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.CrystalRecharger;
import com.droidquest.materials.Lock;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

import java.awt.*;

class ROTut1 extends Level {
    public ROTut1(RoomDisplay rd) {
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
        // Material 5, CrystalRecharger
        materials.addElement(new CrystalRecharger());
        // Material 6, Blue Wall
        materials.addElement(new Material(new Color(0, 0, 255), false, true));
        // Material 7, LockT1
        int[][] lockProgram = {
                {Lock.NARROW},
                {12, 10, 0},
                {12, 9, 0, 12, 6, 1},
                {12, 8, 0, 12, 5, 1},
                {Lock.NARROW},
                {12, 5, 0, 12, 8, 1},
                {12, 6, 0, 12, 9, 1},
                {12, 10, 1},
        };
        materials.addElement(new Lock(Color.white, Color.blue, lockProgram));
        // Material 8, Portal to Tutorial 2;
        materials.addElement(new Portal("ROTut2.lvl", false, true));
        // Material 9, Portal to Main Menu;
        materials.addElement(new Portal("MainMenu.lvl", false, true));

        for (int a = 0; a < 34; a++) {
            rooms.addElement(new Room());
        }

        { // Room 0, Help Screen
            Room room = rooms.elementAt(0);
            room.SetMaterialOutline(0, 0, 19, 11, 2);
            room.AddTextBox("Use the Menubar above to turn sound on or off, or to return to the Main Menu level", 2 * 28, 4 * 32, 450);
            room.AddTextBox("Press ? to get help or hints", 2 * 28, 8 * 32, 500);
            room.AddTextBox("To continue, press RETURN", 4 * 28, 10 * 32, 500);
        }
        { // Room 1, Title Screen
            Room room = rooms.elementAt(1);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.SetMaterial(0, 10, 0);
            room.AddTextBox("{BIG} ROBOT ANATOMY", 4 * 28, 2 * 32, 500);
            room.AddTextBox("In ROBOT ANATOMY, you will learn how to move, how to handle objects, and how robots ",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("- Move", 6 * 28, 5 * 32, 500);
            room.AddTextBox("- Send Signals", 6 * 28, 6 * 32, 500);
            room.AddTextBox("- Grab Objects", 6 * 28, 7 * 32, 500);
            room.AddTextBox("- Detect Objects", 6 * 28, 8 * 32, 500);
            room.AddTextBox("Follow the Arrows", 6 * 28, 10 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 2, Movement
            Room room = rooms.elementAt(2);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(0, 5, 19, 7, 0);
            room.AddTextBox("You can move the cursor using the Arrow keys on your keyboard, or by clicking anywhere on the screen with the mouse.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Click here", 3 * 28, 8 * 32, 500);
            room.AddArrow(5 * 28, 6 * 32 + 16, Arrow.DIR_UP, 28, Color.white);
            room.AddTextBox("Double-Click     here", 12 * 28 + 14, 8 * 32, 160);
            room.AddArrow(15 * 28, 6 * 32 + 16, Arrow.DIR_UP, 28, Color.white);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 3, Movement 2
            Room room = rooms.elementAt(3);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(0, 5, 19, 7, 0);
            room.AddTextBox("Double-Clicking the mouse on one side of the cursor starts your cursor moving in that direction until it reaches a wall or the next room.",
                    2 * 28, 2 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 4, Pick up Key
            Room room = rooms.elementAt(4);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 5, 0, 7, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("You can pick up and drop objects.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("To pick up an object, move on top of it and press the SPACEBAR (or Right-Click the mouse).",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("Pick up this key and move it around. To drop it, press the SPACEBAR again.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Take the Key with you",
                    6 * 28, 10 * 32 + 16, 500);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Key(9 * 28, 5 * 32, room, Color.blue));
        }
        { // Room 5, Locked Sentry
            Room room = rooms.elementAt(5);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(8, 11, 11, 11, 0);
            room.SetMaterialOutline(12, 7, 19, 11, 1);
            room.SetMaterial(12, 7, 7);
            room.AddTextBox("You can move in small steps. Press the control key and the cursor keys at the same time.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("This sentry is trapped.",
                    2 * 28, 4 * 32 + 16, 500);
            room.AddTextBox("To let it out, hold the key by the HANDLE. Use small steps to put the key in the lock.",
                    2 * 28, 6 * 32, 500);
            room.AddArrow(10 * 28, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new SentryT1(17 * 28, 9 * 32 + 16, room));
        }
        { // Room 6, Blue Robot
            Room room = rooms.elementAt(6);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(8, 0, 11, 0, 0);
            room.SetMaterialOutline(16, 11, 18, 11, 0);
            room.AddTextBox("This is a robot. You can go inside it.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("To go inside, line yourself up with one of the robot's white BUMPERS and move in slowly. It may take a few tries.",
                    2 * 28, 4 * 32, 350);
            room.AddTextBox("Go inside and explore.",
                    2 * 28, 9 * 32, 350);
            room.AddTextBox("Come back and take the robot with you.",
                    2 * 28, 10 * 32, 350);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.AddTextBox("BUMPER",
                    15 * 28, 4 * 32 + 16, 350);
            room.AddArrow(15 * 28 + 2, 3 * 32 + 2, Arrow.DIR_UP, 28, Color.white);
            GenericRobot robot = new BlueRobot(15 * 28, 2 * 32, room);
            items.addElement(robot);
            {
                robot.charge = 0;
                robot.thrusterPower = true;
                Wire dummy;
                dummy = new Wire(robot.devices[7].ports[0],
                        robot.devices[0].ports[0]);
                dummy = new Wire(robot.devices[1].ports[0],
                        robot.devices[4].ports[0]);
                dummy = new Wire(robot.devices[3].ports[0],
                        robot.devices[6].ports[0]);
                dummy = new Wire(robot.devices[5].ports[0],
                        robot.devices[2].ports[0]);
                robot.InternalRoom.AddTextBox("GRABBER", 7 * 28, 2 * 32 + 20, 100);
                robot.InternalRoom.AddTextBox("ANTENNA", 7 * 28, 4 * 32 - 8, 100);
                robot.InternalRoom.AddTextBox("BUMPER", 3 * 28, 6 * 32, 100);
                robot.InternalRoom.AddTextBox("BATTERY", 6 * 28, 9 * 32 + 24, 100);
                robot.InternalRoom.AddTextBox("SWITCH", 13 * 28 + 8, 10 * 32 - 8, 100);
                robot.InternalRoom.AddTextBox("THRUSTER", 14 * 28, 5 * 32, 100);
                robot.InternalRoom.AddTextBox("EYE", 13 * 28, 3 * 32, 100);
                robot.InternalRoom.AddArrow(6 * 28, 3 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.AddArrow(4 * 28 + 14, 4 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.AddArrow(2 * 28 + 14, 5 * 32, Arrow.DIR_UP, 28, Color.white);
                robot.InternalRoom.AddArrow(4 * 28 + 14, 10 * 32 - 16, Arrow.DIR_LEFT, 28, Color.white);
                robot.InternalRoom.AddArrow(16 * 28 + 16, 10 * 32 - 16, Arrow.DIR_RIGHT, 28, Color.white);
                robot.InternalRoom.AddArrow(18 * 28, 5 * 32 + 8, Arrow.DIR_UP, 28, Color.white);
                robot.InternalRoom.AddArrow(16 * 28, 3 * 32 - 8, Arrow.DIR_RIGHT, 28, Color.white);
            }
        }
        { // Room 7, Alternate Entry
            Room room = rooms.elementAt(7);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 11, 0);
            room.AddTextBox("You can also enter the robot by moving the cursor so it overlaps the robot, and then pressing E.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("Once inside, you can exit by pressing E again.",
                    2 * 28, 6 * 32, 350);
        }
        { // Room 8, Periscope
            Room room = rooms.elementAt(8);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 11, 0);
            room.AddTextBox("You can be inside the robot and still see outside.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("Go inside the robot. Sit on the robot's EYE to activate its periscope.",
                    2 * 28, 4 * 32, 350);
            room.AddTextBox("Move off the eye to see inside the robot again.",
                    2 * 28, 6 * 32, 350);
            room.AddTextBox("Come outside.",
                    2 * 28, 9 * 32, 350);
            room.AddTextBox("Take the robot with you through the next few rooms.",
                    2 * 28, 10 * 32, 350);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 9, Triangle
            Room room = rooms.elementAt(9);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 0, 0);
            room.SetMaterialFill(19, 5, 19, 7, 0);
            room.AddTextBox("You can put things inside robots. You can even put robots inside robots!",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Pick up the triangle. Carry it inside the robot. Drop it and come outside.",
                    2 * 28, 9 * 32, 400);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Triangle(9 * 28, 6 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 10, Input
            Room room = rooms.elementAt(10);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 0, 3, 3, 1);
            room.SetMaterialFill(0, 5, 19, 8, 0);
            room.AddTextBox("This is an INPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("Some robot parts connect to inputs. Go inside the robot and see.",
                    5 * 28, 3 * 32, 400);
            room.AddTextBox("When electricity flows IN to an input, it turns on the robot part.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("You can see electricity flow. It is orange.",
                    2 * 28, 10 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 24, room, 24, Port.TYPE_INPUT);
            items.addElement(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 11, Output
            Room room = rooms.elementAt(11);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 0, 3, 3, 1);
            room.SetMaterialFill(0, 5, 19, 8, 0);
            room.AddTextBox("This is an OUTPUT.",
                    5 * 28, 2 * 32, 400);
            room.AddTextBox("Some robot parts connect to outputs. Go inside the robot and see.",
                    5 * 28, 3 * 32, 400);
            room.AddTextBox("When a robot part is activated, electricity flows OUT of its output.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 32, Color.white);
            PortDevice pd = new PortDevice(2 * 28 - 8, 20, room, 24, Port.TYPE_OUTPUT);
            items.addElement(pd);
            pd.rotate(1);
            pd.rotate(1);
        }
        { // Room 12, Bumper
            Room room = rooms.elementAt(12);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 5, 0, 8, 0);
            room.SetMaterialOutline(16, 11, 18, 11, 0);
            room.AddTextBox("When a robot touches a wall, its bumper beeps and turns orange with electricity. Inside the robot, the bumper's OUTPUT turns on too.",
                    2 * 28, 2 * 32, 450);
            room.AddTextBox("Try it and see what happens.",
                    2 * 28, 5 * 32, 450);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 13, Thrusters
            Room room = rooms.elementAt(13);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(16, 0, 18, 0, 0);
            room.SetMaterialOutline(14, 11, 16, 11, 0);
            room.AddTextBox("Inside a robot are four THRUSTERS.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("You can propel robots by making electricity flow into the thrusters' INPUTS.",
                    2 * 28, 4 * 32, 350);
            room.AddTextBox("There is also a BATTERY inside the robot.",
                    2 * 28, 7 * 32, 300);
            room.AddTextBox("This robot can't move because its battery is dead.",
                    2 * 28, 9 * 32, 300);
            room.AddArrow(15 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 14, Crystal
            Room room = rooms.elementAt(14);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialOutline(14, 0, 16, 0, 0);
            room.SetMaterialOutline(19, 4, 19, 6, 0);
            room.AddTextBox("Use this ENERGY CRYSTAL to recharge dead batteries.",
                    2 * 28, 2 * 32, 250);
            room.AddTextBox("Take it inside the robot. Pass it over the battery. Notice how the battery level fills with electricity.",
                    2 * 28, 4 * 32 + 16, 350);
            room.AddTextBox("The crystal goes dead (white) as its electricity drains.",
                    2 * 28, 8 * 32, 500);
            room.AddTextBox("Drop the crystal in the robot. Take the robot with you.",
                    2 * 28, 10 * 32, 450);
            room.AddArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Crystal(12 * 28, 2 * 32, room, 100000));
        }
        { // Room 15, Crystal Recharger
            Room room = rooms.elementAt(15);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialOutline(16, 11, 18, 11, 0);
            room.SetMaterialOutline(0, 4, 0, 6, 0);
            room.SetMaterial(17, 2, 5);
            room.AddTextBox("Use this CRYSTAL RECHARGER to recharge energy crystals.",
                    2 * 28, 2 * 32, 350);
            room.AddTextBox("Bring the dead energy crystal outside. Pass it over the recharger. Watch it fill with electricity.",
                    2 * 28, 7 * 32, 400);
            room.AddTextBox("Continue to take the robot with you.",
                    2 * 28, 10 * 32, 350);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 16, Thruster Demo
            Room room = rooms.elementAt(16);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 11, 0);
            room.AddTextBox("START",
                    13 * 28 + 14, 52, 350);
            room.AddArrow(13 * 28, 32, Arrow.DIR_UP, 28, Color.white);
            room.AddTextBox("Move the robot so ONLY its top bumper touches the top wall. Drop it.",
                    4 * 28, 4 * 32, 350);
            room.AddTextBox("When the robot touches a wall, electricity flows from its bumper to the thruster, propelling the robot.",
                    4 * 28, 7 * 32, 350);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 17, Thruster Talk
            Room room = rooms.elementAt(17);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 11, 0);
            room.AddTextBox("A thruster moves a robot in the direction opposite its thrust.",
                    2 * 28, 3 * 32, 400);
            room.AddTextBox("When the left thruster is on, the robot moves RIGHT.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("When the right thruster is on, the robot moves LEFT.",
                    2 * 28, 7 * 32, 400);
            room.AddTextBox("What happens when the top or bottom thruster is on?",
                    2 * 28, 9 * 32, 400);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
        }
        { // Room 18, Switch
            Room room = rooms.elementAt(18);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialFill(16, 0, 18, 0, 0);
            room.SetMaterialFill(0, 5, 0, 7, 0);
            room.AddTextBox("The SWITCH inside a robot turns electricity flow to the thrusters on or off.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Go inside and sit on the switch. Press SPACEBAR to open and close it.",
                    2 * 28, 5 * 32, 400);
            room.AddTextBox("Thrusters work when the switch is closed (orange). Open the switch to save batteries.",
                    2 * 28, 8 * 32, 450);
            room.AddTextBox("Leave the robot here.",
                    2 * 28, 11 * 32, 350);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 19, Antenna Input
            Room room = rooms.elementAt(19);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 0, 4, 5, 4);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.AddTextBox("The ANTENNA control inside a robot controls the antenna outside.",
                    6 * 28, 2 * 32, 400);
            room.AddTextBox("When its INPUT is on, the robot's antenna sends signals to other robots, wherever they may be.",
                    6 * 28, 5 * 32, 400);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            Antenna ant = new Antenna(2 * 28 - 12, 2 * 32, room, Color.white);
            PortDevice pd = new PortDevice(18, 4 * 32, room, 28, Port.TYPE_OUTPUT);
            items.addElement(ant);
            items.addElement(pd);
            pd.value = true;
            pd.rotate(1);
            Wire dummy = new Wire(pd.ports[0], ant.ports[0]);
        }
        { // Room 20, Antenna Output
            Room room = rooms.elementAt(20);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 0, 4, 4, 4);
            room.SetMaterialOutline(0, 5, 0, 7, 0);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.AddTextBox("When a robot's antenna receives signals, the antenna control's OUTPUT turns on.",
                    6 * 28, 2 * 32, 350);
            room.AddArrow(0, 6 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.addElement(new Antenna(2 * 28 - 12, 2 * 32, room, Color.white));
        }
        { // Room 21, Grabber Input
            Room room = rooms.elementAt(21);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 0, 4, 5, 4);
            room.SetMaterialOutline(16, 11, 18, 11, 0);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.AddTextBox("The GRABBER control inside a robot controls the grabber outside the robot.",
                    6 * 28, 2 * 32, 350);
            room.AddTextBox("When the grabber control's INPUT is on, the robot will grab an object that touches the robot's body.",
                    6 * 28, 4 * 32, 350);
            room.AddTextBox("Note: Robots can't grab objects held by you or another robot.",
                    3 * 28, 9 * 32, 400);
            room.AddArrow(17 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            room.graphix.addElement(new Graphix("grab0.jpg", 28, 48));
        }
        { // Room 22, Grabber Output
            Room room = rooms.elementAt(22);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(0, 0, 4, 5, 4);
            room.SetMaterialOutline(16, 0, 18, 0, 0);
            room.SetMaterialOutline(19, 5, 19, 7, 0);
            room.AddTextBox("When a robot grabs a object, the grabber control's OUTPUT turns on.",
                    6 * 28, 2 * 32, 300);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.graphix.addElement(new Graphix("grab1.jpg", 28, 56));
        }
        { // Room 23, Sensors
            Room room = rooms.elementAt(23);
            room.SetMaterialOutline(0, 0, 19, 4, 6);
            room.SetMaterialOutline(0, 8, 19, 11, 6);
            room.SetMaterialOutline(1, 8, 18, 8, 0);
            room.AddTextBox("These are SENSORS. Use them inside robots to detect objects that MATCH the sensor shape. Each of these sensors detects energy crystals in a different way.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new DirectionalSensor(3 * 28 + 14, 1 * 32 + 4, room, new Crystal(0, 0, null, 0)));
            items.addElement(new RoomSensor(10 * 28, 2 * 32, room, new Crystal(0, 0, null, 0)));
            items.addElement(new ContactSensor(16 * 28, 2 * 32, room, new Crystal(0, 0, null, 0)));
        }
        { // Room 24, Contact Sensor
            Room room = rooms.elementAt(24);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 5, 19, 7, 0);
            room.AddTextBox("This is a CONTACT sensor. It detects objects that touch it.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Inside the robot, it detects objects that touch the robot's body.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Place the square ON the sensor. What happens when you let go?",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(559, 6 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new ContactSensor(16 * 28, 2 * 32, room, new Square(0, 0, null, Color.white)));
            items.addElement(new Square(10 * 28, 6 * 32, room, Color.blue));
        }
        { // Room 25, Room Sensor
            Room room = rooms.elementAt(25);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 5, 0, 7, 0);
            room.SetMaterialOutline(1, 11, 3, 11, 0);
            room.AddTextBox("This is an IN-SAME-ROOM sensor. It detects objects in the same room.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("Inside the robot, it detects objects in the same room as the robot.",
                    2 * 28, 4 * 32, 500);
            room.AddTextBox("Sensors can't detect an object that is held. Pick up the triangle. What happens?",
                    6 * 28, 9 * 32, 400);
            room.AddArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new Triangle(10 * 28, 6 * 32, room, new Color(255, 128, 0)));
            items.addElement(new RoomSensor(15 * 28, 2 * 32, room, new Triangle(0, 0, null, Color.white)));
        }
        { // Room 26, Directional Sensor
            Room room = rooms.elementAt(26);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(0, 4, 0, 6, 0);
            room.SetMaterialOutline(1, 0, 3, 0, 0);
            room.AddTextBox("This is a DIRECTIONAL sensor. It detects the direction of an object in the same room.",
                    4 * 28, 2 * 32, 450);
            room.AddTextBox("Inside the robot, it detects the direction of an object in the robot's room.",
                    4 * 28, 4 * 32, 450);
            room.AddTextBox("Pick up the sensor. move it around the crystal. Outputs pointing in the DIRECTION of the crystal turn on.",
                    2 * 28, 9 * 32, 500);
            room.AddArrow(0, 5 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            items.addElement(new Crystal(10 * 28, 6 * 32, room, 100000));
            items.addElement(new DirectionalSensor(14 * 28, 5 * 32, room, new Crystal(0, 0, null, 0)));
        }
        { // Room 27, Orange Robot Talk
            Room room = rooms.elementAt(27);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(19, 4, 19, 6, 0);
            room.SetMaterialOutline(0, 8, 0, 10, 0);
            room.AddTextBox("Next door is a robot wired with three sensors.",
                    2 * 28, 2 * 32, 400);
            room.AddTextBox("The sensors are wired to make the robot:",
                    2 * 28, 4 * 32, 400);
            room.AddTextBox("- beep when it contacts a square.",
                    4 * 28, 6 * 32, 400);
            room.AddTextBox("- move left or right toward an energy crystal.",
                    4 * 28, 8 * 32, 400);
            room.AddTextBox("- move down when a triangle is in the room",
                    4 * 28, 10 * 32, 400);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        { // Room 28, Orange Robot
            Room room = rooms.elementAt(28);
            room.SetMaterialOutline(0, 0, 19, 11, 6);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.SetMaterialOutline(1, 11, 2, 11, 0);
            room.AddTextBox("Go inside the robot. Notice how the sensors are wired.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Come outside. Move the objects and the robot to new locations. Watch what happens.",
                    2 * 28, 4 * 32, 500);
            room.AddArrow(2 * 28 + 14, 383, Arrow.DIR_DOWN, 28, Color.white);
            items.addElement(new Square(4 * 28, 6 * 32, room, Color.blue));
            items.addElement(new Crystal(9 * 28, 6 * 32, room, 100000));
            items.addElement(new Triangle(14 * 28, 6 * 32, room, new Color(255, 128, 0)));
            GenericRobot robot = new OrangeRobot(2 * 28, 2 * 32, room);
            items.addElement(robot);
            {
                robot.thrusterPower = true;
                items.addElement(new DirectionalSensor(7 * 28 + 14, 5 * 32 + 4, robot.InternalRoom, new Crystal(0, 0, null, 0)));
                Item dsensor = items.lastElement();
                Wire dummy;
                dummy = new Wire(robot.devices[1].ports[0],
                        ((Device) dsensor).ports[3]);
                dummy = new Wire(robot.devices[3].ports[0],
                        ((Device) dsensor).ports[1]);

                items.addElement(new RoomSensor(8 * 28, 2 * 32, robot.InternalRoom, new Triangle(0, 0, null, Color.white)));
                Item rsensor = items.lastElement();
                ((Device) rsensor).rotate(1);
                ((Device) rsensor).rotate(1);
                dummy = new Wire(((Device) rsensor).ports[0],
                        robot.devices[0].ports[0]);

                items.addElement(new ContactSensor(13 * 28, 2 * 32, robot.InternalRoom, new Square(0, 0, null, Color.white)));
                Item csensor = items.lastElement();
                dummy = new Wire(robot.devices[8].ports[0],
                        ((Device) csensor).ports[0]);
            }
        }
        { // Room 29, White Robot Talk
            Room room = rooms.elementAt(29);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(19, 8, 19, 10, 0);
            room.SetMaterialOutline(1, 0, 2, 0, 0);
            room.AddTextBox("The robot next door is wired to pick up and carry an object.",
                    4 * 28, 2 * 32, 400);
            room.AddTextBox("The robot is also wired to follow walls.",
                    4 * 28, 4 * 32, 400);
            room.AddTextBox("Go inside the robot. Close the switch. Move quickly onto the eye and watch how the robot works.",
                    4 * 28, 6 * 32, 400);
            room.AddArrow(559, 9 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        { // Room 30, White Robot Maze
            Room room = rooms.elementAt(30);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {4, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 0, 4, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            items.addElement(new Crystal(12 * 28, 6 * 32, room, 100000));
            items.addElement(new WhiteRobot(1 * 28, 6 * 32, room));
            {
                Item robot = items.lastElement();
                Wire dummy;
                dummy = new Wire(((GenericRobot) robot).devices[1].ports[0],
                        ((GenericRobot) robot).devices[6].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[5].ports[0],
                        ((GenericRobot) robot).devices[0].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[7].ports[0],
                        ((GenericRobot) robot).devices[2].ports[0]);
                dummy = new Wire(((GenericRobot) robot).devices[3].ports[0],
                        ((GenericRobot) robot).devices[4].ports[0]);

                items.addElement(new ContactSensor(8 * 28, 2 * 32, robot.InternalRoom, new Triangle(0, 0, null, Color.white)));
                Item csensor = items.lastElement();

                items.addElement(new NOTGate(10 * 28, 5 * 32, robot.InternalRoom));
                Item notgate = items.lastElement();
                ((Device) notgate).rotate(1);
                ((Device) notgate).rotate(1);
                dummy = new Wire(((Device) csensor).ports[0],
                        ((Device) notgate).ports[0]);
                dummy = new Wire(((Device) notgate).ports[1],
                        ((GenericRobot) robot).devices[9].ports[0]);
            }
        }
        { // Room 31, After Maze
            Room room = rooms.elementAt(31);
            room.SetMaterialOutline(0, 0, 19, 11, 4);
            room.SetMaterialOutline(19, 4, 19, 6, 0);
            room.SetMaterial(3, 0, 0);
            room.SetMaterial(5, 0, 0);
            room.AddTextBox("Drop a triangle on the robot above to make it let go of the crystal. Look inside to see why it works.",
                    2 * 28, 8 * 32, 500);
            room.AddArrow(559, 5 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            items.addElement(new Triangle(14 * 28, 6 * 32, room, new Color(255, 128, 0)));
        }
        { // Room 32, End
            Room room = rooms.elementAt(32);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.SetMaterialOutline(0, 4, 0, 6, 0);
            room.SetMaterial(19, 10, 0);
            room.SetMaterial(5, 5, 8);
            room.SetMaterial(5, 7, 9);
            room.AddTextBox("You have learned a lot about ROBOT ANATOMY.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Go to TOOLKIT tutorial.",
                    6 * 28, 6 * 32, 500);
            room.AddTextBox("Return to MAIN MENU.",
                    6 * 28, 8 * 32, 500);
            room.AddTextBox("Press ? for help or hints.",
                    2 * 28, 10 * 32, 500);
        }
        { // Room 33, Shortcut
            Room room = rooms.elementAt(33);
            room.SetMaterialOutline(0, 0, 19, 9, 1);
            room.SetMaterialOutline(0, 11, 19, 11, 1);
            room.AddTextBox("Aha! A shortcut between the beginning and end of ROBOT ANATOMY!",
                    4 * 28, 4 * 32, 400);
            room.AddTextBox("or",
                    10 * 28, 10 * 32 + 20, 500);
            room.AddArrow(8 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 50, Color.white);
            room.AddArrow(13 * 28, 10 * 32 + 16, Arrow.DIR_RIGHT, 50, Color.white);
        }

        int[] list1 = {31, 32, 33, 1, 2, 3, 4, 5};
        LinkRoomsHorizontally(list1);

        int[] list2 = {5, 6, 7, 8, 9};
        LinkRoomsVertically(list2);

        int[] list3 = {9, 10, 11, 12};
        LinkRoomsHorizontally(list3);

        int[] list4 = {12, 13, 14};
        LinkRoomsVertically(list4);

        LinkRoomsLeftRight(14, 15);

        int[] list5 = {15, 16, 17, 18};
        LinkRoomsVertically(list5);

        int[] list6 = {21, 20, 19, 18};
        LinkRoomsHorizontally(list6);

        LinkRoomsUpDown(21, 22);

        int[] list7 = {22, 23, 24, 25};
        LinkRoomsHorizontally(list7);

        LinkRoomsUpDown(25, 26);

        int[] list8 = {28, 27, 26};
        LinkRoomsHorizontally(list8);

        LinkRoomsUpDown(28, 29);
        LinkRoomsLeftRight(29, 30);
        LinkRoomsUpDown(30, 31);

        gameCursor = new GameCursor(16 * 28 + 14, 5 * 32 + 16, rooms.elementAt(1));
        helpCam = new HelpCam(rooms.elementAt(0));
        items.addElement(gameCursor);
        items.addElement(helpCam);

        player = gameCursor;
        currentViewer = player;
        electricity = true;
    }

}
