package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.Switch;

import java.awt.*;

class RO5 extends Level {
    public RO5(RoomDisplay rd) {
        super(rd);

        materials.addElement(new Material(true, false));                         // 0  = Empty Space 
        materials.addElement(new Material(new Color(0, 204, 0), false, true));      // 1  = Green
        materials.addElement(new Material(new Color(192, 192, 255), false, true));  // 2  = Light Blue
        materials.addElement(new Material(new Color(63, 32, 0), false, true));      // 3  = Brown
        materials.addElement(new Material(new Color(128, 128, 128), false, true));  // 4  = Grey
        materials.addElement(new Material(new Color(255, 128, 0), false, true));    // 5  = Orange
        materials.addElement(new Material(new Color(255, 255, 255), false, true));  // 6  = White
        materials.addElement(new Material(new Color(0, 0, 255), false, true));      // 7  = Blue
        materials.addElement(new Material(new Color(0, 0, 128), false, true));       // 8  = DarkBlue
        materials.addElement(new Material(new Color(255, 224, 192), false, true));  // 9  = Tan Flesh
//	materials.addElement(new Material(new Color(128,0,0),false, true));      // 10 = Black Wall
        materials.addElement(new Material(Color.black, false, true));      // 10 = Black Wall
        int[][] program1 = {
                {Lock.NARROW},
                {15, 4, 0, 15, 5, 0, 15, 6, 0, 15, 7, 0, 16, 4, 2, 17, 5, 2, 17, 6, 2, 16, 7, 2},
                {16, 4, 0, 17, 5, 0, 17, 6, 0, 16, 7, 0, 16, 3, 2, 17, 3, 2, 16, 8, 2, 17, 8, 2},
                {Lock.NARROW},
                {16, 4, 2, 17, 5, 2, 17, 6, 2, 16, 7, 2, 16, 3, 0, 17, 3, 0, 16, 8, 0, 17, 8, 0},
                {15, 4, 2, 15, 5, 2, 15, 6, 2, 15, 7, 2, 16, 4, 0, 17, 5, 0, 17, 6, 0, 16, 7, 0}
        };
        materials.addElement(new Lock(Color.blue, Color.blue, program1)); // 11= 1st lock
        materials.addElement(new AutoRunner(AutoRunner.UP));              // 12= UP
        materials.addElement(new AutoRunner(AutoRunner.RIGHTUP));         // 13= RIGHTUP
        materials.addElement(new AutoRunner(AutoRunner.RIGHT));           // 14= RIGHT
        materials.addElement(new AutoRunner(AutoRunner.RIGHTDOWN));       // 15= RIGHTDOWN
        materials.addElement(new AutoRunner(AutoRunner.DOWN));            // 16= DOWN
        materials.addElement(new AutoRunner(AutoRunner.LEFTDOWN));        // 17= LEFTDOWN
        materials.addElement(new AutoRunner(AutoRunner.LEFT));            // 18= LEFT
        materials.addElement(new AutoRunner(AutoRunner.LEFTUP));          // 19= LEFTUP
        materials.addElement(new AutoRunner(AutoRunner.STOP));            // 20= STOP
        int[][] program2 = {
                {BinaryLock.NARROW},
                {2, 10, 0, 3, 10, 0, 4, 10, 0, 5, 10, 0, 2, 9, 2, 3, 8, 2, 4, 8, 2, 5, 9, 2},
                {2, 9, 0, 3, 8, 0, 4, 8, 0, 5, 9, 0, 1, 9, 2, 1, 8, 2, 6, 9, 2, 6, 8, 2},
                {BinaryLock.NARROW},
                {2, 9, 2, 3, 8, 2, 4, 8, 2, 5, 9, 2, 1, 9, 0, 1, 8, 0, 6, 9, 0, 6, 8, 0},
                {2, 10, 2, 3, 10, 2, 4, 10, 2, 5, 10, 2, 2, 9, 0, 3, 8, 0, 4, 8, 0, 5, 9, 0},
        };
        materials.addElement(new BinaryLock(Color.blue, program2));       // 21=Lock
        materials.addElement(new HotWires(HotWires.LEFT + HotWires.RIGHT, true)); // 22=HotWire
        materials.addElement(new HotWires(HotWires.LEFT + HotWires.DOWN, true));  // 23=HotWire
        materials.addElement(new HotWires(HotWires.UP + HotWires.DOWN, true));    // 24=HotWire
        materials.addElement(new DeactivatorSwitch());                     // 25=Deactivator
        materials.addElement(new CrystalRecharger());                      // 26=Recharger
        int[][] program3 = {
                {BinaryLock.NARROW}
        };
        materials.addElement(new BinaryLock(Color.white, program3));       // 27=BinaryLock2
        materials.addElement(new MultiSwitch(0, 0));                        // 28=Starter
        materials.addElement(new MultiSwitch(1, 0));                        // 29=1
        materials.addElement(new MultiSwitch(2, 0));                        // 30=2
        materials.addElement(new MultiSwitch(3, 0));                        // 31=3
        materials.addElement(new MultiSwitch(4, 0));                        // 32=4
        materials.addElement(new HotWires(0, true));                        // 33=BlueGrid
        materials.addElement(new CameraEnable());                          // 34=CameraEnable
        materials.addElement(new CameraDisable());                         // 35=Cam Disable
        materials.addElement(new BlueGridSwitch());                        // 36=BlueGridOff
        int[][] program4 = {
                {-12}, // {Switch.WAIT4PLAYERCONTACT},
//	     {Switch.WAIT4CONTACT},
                {Switch.SETVALUEHIGH},
                {Switch.REPLACE, 2, 11, 0},
                {Switch.REPLACE, 3, 11, 0},
                {Switch.REPLACE, 4, 11, 0},
                {Switch.REPLACE, 5, 11, 0},
                {Switch.DOWNROOM},
                {Switch.REPLACE, 2, 0, 0, 3, 0, 0, 4, 0, 0, 5, 0, 0},
                {Switch.WAIT4REMOVAL},
                {Switch.REPLACE, 5, 11, 9},
                {Switch.REPLACE, 4, 11, 9},
                {Switch.REPLACE, 3, 11, 9},
                {Switch.REPLACE, 2, 11, 9},
                {Switch.DOWNROOM},
                {Switch.REPLACE, 2, 0, 9, 3, 0, 9, 4, 0, 9, 5, 0, 9},
                {Switch.SETVALUELOW}
        };
        materials.addElement(new Switch(Switch.ROT_RIGHT, program4)); //37=Grid door Switch
        int[][] program5 = {
                {BinaryLock.NARROW},
                {6, 2, 2, 9, 2, 0, 10, 2, 0, 13, 2, 2},
                {5, 2, 2, 8, 2, 0, 11, 2, 0, 14, 2, 2},
                {BinaryLock.NARROW},
                {5, 2, 0, 8, 2, 2, 11, 2, 2, 14, 2, 0},
                {6, 2, 0, 9, 2, 2, 10, 2, 2, 13, 2, 0}
        };
        materials.addElement(new BinaryLock(Color.blue, program5)); //38=Binary Lock
        materials.addElement(new MultiButton(0, 1)); // 39= MultiButton Starter
        materials.addElement(new MultiButton(1, 0)); // 40= Multibutton 1
        materials.addElement(new MultiButton(2, 0)); // 41= Multibutton 2
        materials.addElement(new MultiButton(3, 0)); // 42= Multibutton 3
        materials.addElement(new MultiButton(4, 0)); // 43= Multibutton 4
        materials.addElement(new MultiButton(5, 0)); // 44= Multibutton 5
        materials.addElement(new MultiButton(6, 0)); // 45= Multibutton 6
        materials.addElement(new MultiButton(7, 0)); // 46= Multibutton 7
        materials.addElement(new MultiButton(8, 0)); // 47= Multibutton 8
        materials.addElement(new MultiButton(9, 0)); // 48= Multibutton 9
        materials.addElement(new SkyGuardMat());       // 49= SkyGuard

        materials.addElement(new Material(new Color(192, 192, 255), true, true)); // 50=False wall

        for (int a = 0; a < 38; a++) {
            rooms.addElement(new Room());
        }

        String[] skyway0 = {"skyway00.gif", "skyway01.gif", "skyway02.gif"};
        String[] skyway1 = {"skyway03.gif", "skyway04.gif"};
        String[] skyway2 = {"skyway05.gif", "skyway06.gif", "skyway07.gif", "skyway08.gif"};
        String[] skyway3 = {"skyway09.gif", "skyway10.gif", "skyway11.gif"};
        String[] skyway4 = {"skyway12.gif", "skyway13.gif"};
        String[] skyway5 = {"skyway14.gif", "skyway15.gif"};
        String[] skyway6 = {"skyway16.gif", "skyway17.gif"};
        String[] skyway7 = {"skyway18.gif", "skyway19.gif", "skyway20.gif"};
        String[] skyguard = {"skyguard0.gif", "skyguard1.gif", "skyguard2.gif", "skyguard3.gif", "skyguard4.gif"};

        {// Room  0 Help 
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

            room.AddTextBox("Travel the Skyways to freedom!", 80, 64, 560);
            room.AddTextBox("HINTS:",
                    56, 3 * 32, 560);
            room.AddTextBox("Beware of Skyway traffic!",
                    56, 4 * 32, 560);
            room.AddTextBox("Timing is critical for many locks.",
                    56, 5 * 32, 500);
            room.AddTextBox("The Disk-O-Tek key is a sonic pattern. Read it from left to right. Repeat it once.",
                    56, 6 * 32, 500);
            room.AddTextBox("Don't linger on the buttons.",
                    56, 8 * 32, 500);
            room.AddTextBox("Arrows show the path through the blue grid. Your 'bot must ride it alone.",
                    56, 9 * 32, 500);
            room.AddTextBox("(To continue, press RETURN.)",
                    96, 346, 500);
        }
        {// Room  1 Entrance: Locked Door 
            Room room = rooms.elementAt(1);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 2, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 50, 2, 2, 2}
            };

        }
        {// Room  2 Entrance Chamber 
            Room room = rooms.elementAt(2);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2},
                    {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

        }
        {// Room  3 Skyway 
            Room room = rooms.elementAt(3);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            room.AddTextBox("The Skyways of Robotropolis", 118, 86, 560);
            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway0, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway1, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway2, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway3, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway4, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway5, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway6, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway7, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway0, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway1, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway2, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway3, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  4 Skyway 
            Room room = rooms.elementAt(4);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway4, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway5, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway6, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway7, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway0, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway1, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway2, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway3, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway4, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway5, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway6, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway7, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  5 Skyway 
            Room room = rooms.elementAt(5);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            room.AddTextBox("I left my nodes in Robotropolis.", 160, 98, 250);
            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway0, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway2, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway4, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway6, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway1, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway3, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway5, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway7, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway0, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway3, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway6, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway1, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  6 Skyway 
            Room room = rooms.elementAt(6);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway4, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway7, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway2, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway5, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway0, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway4, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway1, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway5, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway2, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway6, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway3, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway7, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  7 Skyway 
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            room.AddTextBox("I'll have a ramburger and chips to go.", 160, 96, 300);
            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway0, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway5, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway2, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway7, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway4, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway1, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway6, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway3, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway0, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway6, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway4, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway2, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  8 Skyway 
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4},
                    {0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                    {0, 0, 0, 4, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 4, 0, 0, 0}
            };

            items.addElement(new SkywayFlyer(5 * 28 + 2, 2 * 32, room, skyway7, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 5 * 32, room, skyway5, -4));
            items.addElement(new SkywayFlyer(5 * 28 + 2, 8 * 32, room, skyway3, -4));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 2 * 32, room, skyway1, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 5 * 32, room, skyway0, 2));
            items.addElement(new SkywayFlyer(8 * 28 + 2, 8 * 32, room, skyway7, 2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 2 * 32, room, skyway6, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 5 * 32, room, skyway5, -2));
            items.addElement(new SkywayFlyer(11 * 28 + 2, 8 * 32, room, skyway4, -2));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 2 * 32, room, skyway3, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 5 * 32, room, skyway2, 4));
            items.addElement(new SkywayFlyer(14 * 28 + 2, 8 * 32, room, skyway1, 4));
            items.addElement(new SkyGuard(4 * 28, 0, room, 4));
            items.addElement(new SkyGuard(10 * 28, 11 * 32, room, 4));
        }
        {// Room  9 Pinto Puzzle 
            Room room = rooms.elementAt(9);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 29, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 31, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 30, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 32, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 27, 0, 0, 0, 28, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };

            room.AddTextBox("{BIG} {255,255,255} 1", 506, 94, 560);
            room.AddTextBox("{BIG} {255,255,255} 2", 506, 158, 560);
            room.AddTextBox("{BIG} {255,255,255} 3", 506, 222, 560);
            room.AddTextBox("{BIG} {255,255,255} 4", 506, 286, 560);
            room.AddTextBox("Based on the 1973 Ford Pinto... What an engine-ous lock!",
                    44, 64, 350);
            room.AddTextBox("STARTER", 252, 288, 560);
            room.AddArrow(11 * 28 + 14, 10 * 32, Arrow.DIR_DOWN, 32, Color.white);
            room.AddGraphix("whiteVertical.gif", 14 * 28, 2 * 32);
            int[] pace = {14 * 28, 3 * 32, 14 * 28, 9 * 32};
            int[] protect = {14 * 28, 2 * 32, 19 * 28, 10 * 32, 10 * 28, 0};
            items.addElement(new Sentry(14 * 28, 3 * 32, room, pace, protect, false));
        }
        {// Room 10 First Sentry, Upper 
            Room room = rooms.elementAt(10);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 0, 0, 0, 0, 8, 8, 0, 0, 8, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 8, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 0, 0, 0, 8},
                    {8, 8, 8, 8, 8, 0, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8, 8, 8, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 8, 8, 8, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8},
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8}
            };

            int[] pace = {11 * 28, 3 * 32, 11 * 28, 10 * 32};
            int[] program = {4 * 28, 0, 13 * 28, 11 * 32, 0, 3 * 32, 13 * 28, 0, 18 * 28, 11 * 32, 19 * 28, 8 * 32};
            items.addElement(new Sentry(11 * 28, 3 * 32, room, pace, program, false));
        }
        {// Room 11 Sound Lock 
            Room room = rooms.elementAt(11);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 8, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 8, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 8, 0, 0, 0, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

            room.AddTextBox("This lock is wired for sound. ", 76, 80, 240);
            room.AddTextBox("Beep in time to the flashing receiver.", 76, 304, 240);
            int[][] program = {
                    {SonicLock.BINARY, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0},
                    {SonicLock.MODIFY, 15, 4, 0, 15, 5, 0, 15, 6, 0, 15, 7, 0, 16, 4, 8, 17, 5, 8, 17, 6, 8, 16, 7, 8},
                    {SonicLock.MODIFY, 16, 4, 0, 17, 5, 0, 17, 6, 0, 16, 7, 0, 16, 3, 8, 17, 3, 8, 16, 8, 8, 17, 8, 8},
                    {SonicLock.BINARY, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0},
                    {SonicLock.MODIFY, 16, 4, 8, 17, 5, 8, 17, 6, 8, 16, 7, 8, 16, 3, 0, 17, 3, 0, 16, 8, 0, 17, 8, 0},
                    {SonicLock.MODIFY, 15, 4, 8, 15, 5, 8, 15, 6, 8, 15, 7, 8, 16, 4, 0, 17, 5, 0, 17, 6, 0, 16, 7, 0}
            };
            items.addElement(new SonicLock(18 * 28, 5 * 32 + 15, room, program));
        }
        {// Room 12 Path after Disk Drive 
            Room room = rooms.elementAt(12);
            room.RoomArray = new int[][]{
                    {8, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

        }
        {// Room 13 Chamber before Hot Wires 
            Room room = rooms.elementAt(13);
            room.RoomArray = new int[][]{
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 9}
            };

        }
        {// Room 14 Dark Maze 
            Room room = rooms.elementAt(14);
            room.RoomArray = new int[][]{
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
            };

            room.AddTextBox("?", 274, 6 * 32 + 6, 100);
            room.AddArrow(280, 112, Arrow.DIR_UP, 30, Color.white);
            room.AddArrow(280, 272, Arrow.DIR_DOWN, 30, Color.white);
            room.AddArrow(200, 192, Arrow.DIR_LEFT, 30, Color.white);
            room.AddArrow(360, 192, Arrow.DIR_RIGHT, 30, Color.white);
        }
        {// Room 15 First Sentry, Lower 
            Room room = rooms.elementAt(15);
            room.RoomArray = new int[][]{
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

        }
        {// Room 16 Rest Stop 
            Room room = rooms.elementAt(16);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 8},
                    {8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8}
            };

            room.AddTextBox("SKYWAY REST STOP", 184, 2 * 28, 500);
            room.AddTextBox("Next Rest Stop 50 Klicks.", 130, 4 * 28, 500);
        }
        {// Room 17 Timer Sentry Button 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {3, 3, 0, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };

            room.AddTextBox("00", 32, 344, 560);
            room.AddTextBox("Deactivate Guard Switch", 140, 246, 560);
            room.AddTextBox("Timer", 140, 344, 560);
            room.AddArrow(3 * 28, 7 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddArrow(3 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
        }
        {// Room 18 Hot Wire Room 
            Room room = rooms.elementAt(18);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {5, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {5, 22, 22, 22, 22, 22, 22, 22, 23, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {5, 5, 5, 5, 0, 0, 0, 0, 24, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 5},
                    {5, 5, 0, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}
            };

            room.AddTextBox("Hot wires burn batteries...", 80, 60, 560);
            int[] pace = {2 * 28, 5 * 32, 11 * 28, 5 * 32};
            int[] program = {4 * 28, 0, 12 * 28, 11 * 32, 0, 5 * 32};
            items.addElement(new Sentry(2 * 28, 5 * 32, room, pace, program, true));
        }
        {// Room 19 Chamber before Disk Drive 
            Room room = rooms.elementAt(19);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("To Robotropolis Disk-O-Tek", 316, 182, 325);
            room.AddArrow(18 * 28, 6 * 32 + 8, Arrow.DIR_RIGHT, 2 * 28, Color.white);
        }
        {// Room 20 Path after Disk Drive 
            Room room = rooms.elementAt(20);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

        }
        {// Room 21 Dark Maze 
            Room room = rooms.elementAt(21);
            room.RoomArray = new int[][]{
                    {10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10}
            };

            room.AddTextBox("?", 274, 6 * 32 + 6, 100);
            room.AddArrow(280, 112, Arrow.DIR_UP, 30, Color.white);
            room.AddArrow(280, 272, Arrow.DIR_DOWN, 30, Color.white);
            room.AddArrow(200, 192, Arrow.DIR_LEFT, 30, Color.white);
            room.AddArrow(360, 192, Arrow.DIR_RIGHT, 30, Color.white);
        }
        {// Room 22 Dark Maze 
            Room room = rooms.elementAt(22);
            room.RoomArray = new int[][]{
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 10, 0, 0, 0, 10, 10, 10, 10, 10, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 10, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 0, 0, 0, 10, 10, 0, 0, 0, 10}
            };

            room.AddTextBox("?", 274, 6 * 32 + 6, 100);
            room.AddArrow(280, 112, Arrow.DIR_UP, 30, Color.white);
            room.AddArrow(280, 272, Arrow.DIR_DOWN, 30, Color.white);
            room.AddArrow(200, 192, Arrow.DIR_LEFT, 30, Color.white);
            room.AddArrow(360, 192, Arrow.DIR_RIGHT, 30, Color.white);
        }
        {// Room 23 Dark Maze 
            Room room = rooms.elementAt(23);
            room.RoomArray = new int[][]{
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10},
                    {0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10},
                    {10, 10, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10, 0, 0, 0, 0, 10},
                    {0, 0, 10, 10, 0, 0, 0, 0, 0, 10, 10, 0, 0, 0, 10, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10}
            };

            room.AddTextBox("?", 274, 6 * 32 + 6, 100);
            room.AddArrow(280, 112, Arrow.DIR_UP, 30, Color.white);
            room.AddArrow(280, 272, Arrow.DIR_DOWN, 30, Color.white);
            room.AddArrow(200, 192, Arrow.DIR_LEFT, 30, Color.white);
            room.AddArrow(360, 192, Arrow.DIR_RIGHT, 30, Color.white);
        }
        {// Room 24 Dark Maze 
            Room room = rooms.elementAt(24);
            room.RoomArray = new int[][]{
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 10, 10, 10, 0, 10, 10, 10, 10, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 10, 0, 0, 0, 0},
                    {10, 0, 0, 0, 10, 10, 10, 10, 10, 0, 10, 10, 0, 10, 0, 10, 0, 10, 0, 0},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0, 0, 0, 10, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 10, 10, 10, 10, 0, 10},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
            };

            room.AddTextBox("?", 274, 6 * 32 + 6, 100);
            room.AddArrow(280, 112, Arrow.DIR_UP, 30, Color.white);
            room.AddArrow(280, 272, Arrow.DIR_DOWN, 30, Color.white);
            room.AddArrow(200, 192, Arrow.DIR_LEFT, 30, Color.white);
            room.AddArrow(360, 192, Arrow.DIR_RIGHT, 30, Color.white);
        }
        {// Room 25 Grid Puzzle 
            Room room = rooms.elementAt(25);
            room.RoomArray = new int[][]{
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 33, 33, 33, 33, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 33, 33, 33, 33, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 0, 0, 0, 0, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 0, 0, 0, 0, 0, 0, 0, 0, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 0, 0, 0, 0, 33, 33, 33, 33, 33, 33, 33, 33, 9, 9},
                    {9, 9, 33, 33, 33, 33, 0, 0, 0, 0, 33, 33, 33, 33, 33, 33, 33, 33, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 9, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9}
            };

            items.addElement(new HiddenCamera(room));
            room.AddTextBox("00", 28, 350, 100);
            room.AddTextBox("Timer", 4 * 28, 350, 100);
            room.AddArrow(3 * 28, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Blue Grid Switch", 9 * 28, 350, 560);
            room.AddArrow(17 * 28, 10 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
            room.AddGraphix("blueHorizontal.gif", 2 * 28 + 2, 1 * 32 + 2);
            room.AddGraphix("blueHorizontal.gif", 2 * 28 + 2, 3 * 32);
            room.AddGraphix("blueHorizontal.gif", 2 * 28 + 2, 5 * 32);
            room.AddGraphix("blueHorizontal.gif", 2 * 28 + 2, 7 * 32);
            room.AddGraphix("blueHorizontal.gif", 2 * 28 + 2, 9 * 32);
            room.AddGraphix("blueVertical.gif", 2 * 28 + 2, 34);
            room.AddGraphix("blueVertical.gif", 6 * 28, 34);
            room.AddGraphix("blueVertical.gif", 10 * 28, 34);
            room.AddGraphix("blueVertical.gif", 14 * 28, 34);
            room.AddGraphix("blueVertical.gif", 18 * 28 - 6, 34);
        }
        {// Room 26 Grid puzzle map 
            Room room = rooms.elementAt(26);
            room.RoomArray = new int[][]{
                    {9, 9, 0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 34, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 9, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            };

            room.AddArrow(4 * 28 + 22, 2 * 32, Arrow.DIR_RIGHT, 32, Color.white);
            room.AddArrow(8 * 28 + 22, 2 * 32, Arrow.DIR_RIGHT, 32, Color.white);
            room.AddArrow(12 * 28 + 22, 2 * 32, Arrow.DIR_RIGHT, 32, Color.white);
            room.AddTextBox("{BIG} {255,128,000} X", 15 * 28 + 14, 1 * 32, 50);
            room.AddTextBox("{BIG} {255,128,000} X", 3 * 28 + 14, 3 * 32, 50);
            room.AddTextBox("{BIG} {255,128,000} X", 7 * 28 + 14, 3 * 32, 50);
            room.AddArrow(12 * 28, 5 * 32 - 18, Arrow.DIR_DOWN, 32, Color.white);
            room.AddTextBox("{BIG} {255,128,000} X", 15 * 28 + 14, 3 * 32, 50);
            room.AddTextBox("{BIG} {255,128,000} X", 3 * 28 + 14, 5 * 32, 50);
            room.AddArrow(8 * 28 - 18, 6 * 32, Arrow.DIR_LEFT, 32, Color.white);
            room.AddArrow(12 * 28 - 18, 6 * 32, Arrow.DIR_LEFT, 32, Color.white);
            room.AddTextBox("{BIG} {255,128,000} X", 15 * 28 + 14, 5 * 32, 50);
            room.AddTextBox("{BIG} {255,128,000} X", 3 * 28 + 14, 7 * 32, 50);
            room.AddArrow(8 * 28, 9 * 32 - 18, Arrow.DIR_DOWN, 32, Color.white);
            room.AddTextBox("{BIG} {255,128,000} X", 11 * 28 + 14, 7 * 32, 50);
            room.AddTextBox("{BIG} {255,128,000} X", 15 * 28 + 14, 7 * 32, 50);

            room.AddTextBox("Open Door", 380, 338, 560);
            room.AddTextBox("View room     below", 104, 320, 120);
            room.AddArrow(104, 10 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddGraphix("whiteHorizontal.gif", 2 * 28 + 2, 1 * 32 + 2);
            room.AddGraphix("whiteHorizontal.gif", 2 * 28 + 2, 3 * 32);
            room.AddGraphix("whiteHorizontal.gif", 2 * 28 + 2, 5 * 32);
            room.AddGraphix("whiteHorizontal.gif", 2 * 28 + 2, 7 * 32);
            room.AddGraphix("whiteHorizontal.gif", 2 * 28 + 2, 9 * 32);
            room.AddGraphix("whiteVertical.gif", 2 * 28 + 2, 34);
            room.AddGraphix("whiteVertical.gif", 6 * 28, 34);
            room.AddGraphix("whiteVertical.gif", 10 * 28, 34);
            room.AddGraphix("whiteVertical.gif", 14 * 28, 34);
            room.AddGraphix("whiteVertical.gif", 18 * 28 - 6, 34);

        }
        {// Room 27 Final Puzzle 
            Room room = rooms.elementAt(27);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 2, 38, 0, 0, 39, 2, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 42, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 43, 2, 2},
                    {2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2},
                    {2, 2, 40, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 41, 2, 2},
                    {2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 44, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 45, 2, 2},
                    {2, 0, 0, 0, 0, 46, 0, 2, 0, 0, 0, 0, 2, 0, 47, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 2, 0, 2, 48, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("{BIG} {000,000,255} 3", 28, 94, 560);
            room.AddTextBox("STARTER", 412, 52, 560);
            room.AddArrow(13 * 28, 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("{BIG} {255,000,255} 2", 504, 158, 560);
            room.AddTextBox("{BIG} {000,000,255} 1", 28, 158, 560);
            room.AddTextBox("{BIG} {255,000,255} 4", 504, 94, 560);
            room.AddTextBox("{BIG} {000,204,000} 5", 28, 318, 560);
            room.AddTextBox("{BIG} {255,128,000} 6", 504, 318, 560);
            room.AddTextBox("{BIG} {000,204,000} 7", 112, 350, 560);
            room.AddTextBox("{BIG} {255,128,000} 8", 420, 350, 560);
            room.AddTextBox("TRANSPORTER", 216, 134, 560);
            room.AddTextBox("Press buttons   in order.", 196, 166, 170);
            room.AddTextBox("Press last.", 228, 270, 100);
            room.AddArrow(8 * 28 + 14, 10 * 32, Arrow.DIR_DOWN, 32, Color.white);
        }
        {// Room 28 Disk Drive Entrance 
            Room room = rooms.elementAt(28);
            room.RoomArray = new int[][]{
                    {0, 8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 8, 0, 8, 0, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 8, 0, 8, 0, 8, 0, 8, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {0, 8, 8, 8, 8, 8, 0, 8, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 20, 12, 12, 12, 8, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 20, 12, 12, 12, 8, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 8, 8, 8, 0, 0, 0, 8, 12, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                    {0, 8, 0, 0, 8, 0, 0, 8, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                    {0, 8, 8, 8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 8, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

            room.AddTextBox("{BIG} {255,255,255}  Robart ", 320, 208, 560);
        }
        {// Room 29 Disk Drive Path 
            Room room = rooms.elementAt(29);
            room.RoomArray = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 12, 12, 12, 8, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 8, 0, 0, 0},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 12, 12, 12, 8, 0, 0, 0},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 12, 12, 12, 8, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 8, 0, 0, 0},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 8, 0, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 12, 12, 12, 8, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 12, 12, 12, 8, 0, 0, 0}
            };

            room.AddTextBox("{BIG} {255,255,255} RO400 Disk Drive", 12, 208, 560);
        }
        {// Room 30 Disk Drive Top Left 
            Room room = rooms.elementAt(30);
            room.RoomArray = new int[][]{
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 18, 18},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 18, 18, 18, 18, 18, 18, 18, 18},
                    {7, 7, 7, 7, 7, 7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                    {7, 7, 7, 7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 18, 18, 17, 17, 18, 18},
                    {7, 7, 7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 18, 18, 17, 17, 17, 18, 18},
                    {7, 7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 18, 16, 17, 17, 17, 17, 18, 18},
                    {7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 18, 17, 17, 17, 17, 17, 17, 18, 18},
                    {7, 7, 7, 16, 18, 18, 18, 18, 18, 18, 18, 17, 16, 17, 17, 17, 17, 18, 18, 18},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 17, 16, 16, 16, 16, 17, 16, 18, 18, 7},
                    {18, 18, 18, 18, 18, 18, 18, 7, 7, 0, 16, 16, 16, 16, 16, 16, 18, 7, 7, 7},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 16, 16, 16, 16, 16, 16, 7, 7, 7, 7}
            };

            room.AddTextBox("On a clear disk you can seek forever...", 200, 188, 300);
        }
        {// Room 31 Disk Drive Top Right 
            Room room = rooms.elementAt(31);
            room.RoomArray = new int[][]{
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {18, 18, 18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 7, 7, 7, 7, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 7, 7, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 18, 18, 19, 18, 18, 12, 12, 12, 12, 18, 7, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 12, 12, 12, 12, 12, 18, 7, 7, 7, 7},
                    {18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 12, 12, 12, 12, 12, 18, 7, 7, 7},
                    {18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 12, 12, 12, 12, 12, 7, 7, 7},
                    {7, 12, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 12, 12, 12, 12, 18, 7, 7},
                    {7, 7, 7, 19, 12, 12, 12, 12, 19, 19, 19, 19, 19, 19, 12, 12, 12, 12, 18, 7},
                    {7, 7, 7, 7, 12, 12, 12, 12, 19, 19, 19, 19, 19, 19, 19, 12, 12, 12, 12, 7}
            };

        }
        {// Room 32 Disk Drive Bottom Right 
            Room room = rooms.elementAt(32);
            room.RoomArray = new int[][]{
                    {7, 7, 7, 7, 12, 13, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 7},
                    {7, 7, 7, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 7},
                    {7, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 7, 7},
                    {14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 12, 12, 7, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 12, 12, 7, 7, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 12, 12, 12, 7, 7, 7, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 12, 7, 7, 7, 7, 7, 7, 7, 7},
                    {14, 14, 14, 14, 14, 14, 14, 14, 12, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {14, 14, 12, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}
            };

        }
        {// Room 33 Disk Drive Bottom Left 
            Room room = rooms.elementAt(33);
            room.RoomArray = new int[][]{
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 16, 16, 16, 16, 16, 16, 7, 7, 7, 7},
                    {14, 14, 14, 15, 15, 15, 16, 7, 7, 16, 16, 16, 16, 16, 16, 16, 0, 7, 7, 7},
                    {14, 14, 14, 14, 15, 15, 14, 14, 14, 16, 16, 16, 16, 16, 16, 16, 0, 0, 0, 7},
                    {7, 7, 7, 14, 14, 15, 15, 14, 14, 14, 15, 15, 15, 15, 15, 14, 14, 14, 14, 14},
                    {7, 7, 7, 14, 14, 14, 15, 15, 15, 15, 14, 15, 15, 15, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 7, 14, 14, 14, 14, 14, 14, 14, 15, 15, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 7, 7, 14, 14, 14, 14, 14, 14, 14, 15, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 7, 7, 7, 7, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 14, 14, 14},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}
            };

            room.AddTextBox("Welcome to", 284, 222, 250);
            room.AddTextBox("The Robotropolis", 248, 242, 250);
            room.AddTextBox("Disk-O-Tek", 316, 262, 250);
            items.addElement(new BinaryKey(9 * 28, 1 * 32, room));
            String[] disco1 = {"disco0.gif", "disco1.gif"};
            String[] disco2 = {"disco2.gif", "disco3.gif"};
            String[] disco3 = {"disco4.gif", "disco5.gif"};
            room.AddGraphix(disco1, 9 * 28, 8 * 32);
            room.AddGraphix(disco2, 12 * 28, 7 * 32);
            room.AddGraphix(disco3, 16 * 28, 9 * 32);
        }
        {// Room 34 Disk Drive Path 
            Room room = rooms.elementAt(34);
            room.RoomArray = new int[][]{
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 12, 12, 12, 9, 9, 9, 9},
                    {9, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 12, 12, 12, 9, 0, 0, 0},
                    {9, 12, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 12, 12, 9, 0, 0, 0},
                    {9, 12, 12, 12, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0},
                    {9, 12, 12, 12, 9, 0, 0, 9, 9, 9, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0},
                    {9, 12, 12, 12, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 0},
                    {9, 12, 12, 12, 9, 0, 0, 9, 9, 9, 0, 0, 0, 9, 0, 0, 9, 0, 0, 0},
                    {9, 12, 12, 12, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 9, 9, 0, 0, 0, 0},
                    {9, 12, 12, 12, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 12, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                    {9, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            };

        }
        {// Room 35 Disk Drive Path 
            Room room = rooms.elementAt(35);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {3, 12, 12, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
                    {3, 12, 12, 12, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 12, 12, 12, 3, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 3, 3, 0, 0, 0},
                    {3, 12, 12, 12, 3, 0, 0, 3, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0},
                    {3, 12, 12, 12, 3, 0, 0, 3, 0, 3, 0, 3, 0, 0, 3, 3, 3, 3, 0, 0},
                    {3, 12, 12, 12, 3, 0, 0, 3, 3, 3, 3, 3, 0, 0, 3, 0, 0, 3, 0, 0},
                    {3, 12, 12, 12, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0},
                    {3, 12, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 0, 0, 0},
                    {3, 12, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 12, 12, 3, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 12, 12, 12, 3, 3, 3, 3}
            };

        }
        {// Room 36 Chamber after Grid Puzzle 
            Room room = rooms.elementAt(36);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2}
            };

        }

        {// Room 37 Secret Room 
            Room room = rooms.elementAt(37);
            room.SetMaterialOutline(0, 0, 19, 11, 2);
            room.SetMaterial(16, 0, 0);
            room.AddTextBox("All right! You've found the fifth secret.", 2 * 28, 4 * 32, 500);
            room.AddTextBox("To find the Secret 6th Level, look for a hidden path to the right when you're declared a Robot Master.",
                    2 * 28, 6 * 32, 500);
            String[] helperlist = {
                    "helper0.gif", "helper0.gif", "helper0.gif", "helper0.gif",
                    "helper0.gif", "helper0.gif", "helper0.gif", "helper0.gif",
                    "helper7.gif", "helper6.gif", "helper7.gif", "helper6.gif",
                    "helper7.gif", "helper6.gif", "helper7.gif", "helper6.gif",
                    "helper7.gif", "helper6.gif", "helper7.gif", "helper6.gif",
                    "helper7.gif", "helper6.gif", "helper7.gif", "helper6.gif",
                    "helper0.gif", "helper0.gif", "helper0.gif", "helper0.gif",
                    "helper1.gif", "helper4.gif", "helper2.gif", "helper3.gif",
                    "helper0.gif", "helper0.gif", "helper0.gif", "helper0.gif",
                    "helper5.gif", "helper5.gif", "helper5.gif", "helper5.gif",
            };
            Graphix helper = new Graphix(helperlist, 15 * 28, 8 * 32);
            room.graphix.addElement(helper);
            items.addElement(new Key(16 * 28, 3 * 32, room, new Color(255, 0, 255)));
        }

        int[] skyways = {8, 7, 6, 5, 4, 3, 1};
        LinkRoomsVertically(skyways);

        int[] roomlist1 = {1, 2, 3, 10, 4, 19, 28, 29};
        LinkRoomsHorizontally(roomlist1);
        LinkRoomsUpDown(10, 15);
        LinkRoomsUpDown(1, 37);

        LinkRoomsUpDown(19, 12);
        int[] roomlist2 = {12, 20, 5, 11, 6, 13, 18};
        LinkRoomsHorizontally(roomlist2);

        int[][] roomgrid1 = {
                {34, 30, 31},
                {35, 33, 32}};
        LinkRoomsGrid(roomgrid1);
        LinkRoomsUpDown(35, 29);
        LinkRoomsUpDown(29, 34);

        LinkRoomsUpDown(18, 17);

        int[] roomlist3 = {22, 23, 8, 16, 17, 7, 14};
        LinkRoomsHorizontally(roomlist3);

        LinkRoomsLeftRight(21, 24);
        LinkRoomsUpDown(22, 21);
        LinkRoomsUpDown(21, 14);
        LinkRoomsUpDown(23, 24);

        rooms.elementAt(24).rightRoom = rooms.elementAt(14); // 1-way connection

        int[] roomlist4 = {13, 16, 9, 26, 25, 36, 27};
        LinkRoomsVertically(roomlist4);

        gameCursor = new GameCursor(6 * 28, 8 * 32, rooms.elementAt(1));
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
