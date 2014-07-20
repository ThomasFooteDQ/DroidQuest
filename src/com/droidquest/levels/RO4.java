package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Graphix;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.Switch4A;
import com.droidquest.materials.switches.Switch4B;
import com.droidquest.materials.switches.Switch4C;
import com.droidquest.materials.switches.Switch4D;

import java.awt.*;

class RO4 extends Level {
    public RO4(RoomDisplay rd) {
        super(rd);

        materials.addElement(new Material(true, false));                         // 0=Empty Space
        materials.addElement(new Material(new Color(0, 204, 0), false, true));      // 1=green
        materials.addElement(new Material(new Color(128, 128, 128), false, true));  // 2=grey
        materials.addElement(new Material(new Color(0, 0, 255), false, true));      // 3=blue
        materials.addElement(new Material(new Color(255, 128, 0), false, true));    // 4=orange
        materials.addElement(new Material(new Color(255, 255, 255), false, true));  // 5=white
        materials.addElement(new Material(new Color(0, 0, 128), false, true));      // 6=dk blue
        materials.addElement(new Material(new Color(192, 192, 255), false, true));  // 7=grey-blue
        materials.addElement(new MineField());                                   // 8=Minefield
        materials.addElement(new Material(new Color(255, 224, 192), false, true));  // 9=
        materials.addElement(new Material(new Color(63, 32, 0), false, true));      // 10=dk orange
        materials.addElement(new Material(new Color(128, 128, 128), false, true));  // 11=grey
        materials.addElement(new Monitor());                                     // 12=monitor
        materials.addElement(new CrystalRecharger());                            // 13=Recharger
        materials.addElement(new PlayerBlocker(Color.black));                    // 14=FF (Energy)
        materials.addElement(new ElevatorDoor());                                // 15=ElevatorDoor
        materials.addElement(new AntiPlayer());                                  // 16=AntiPlayer
        String[] grillwork = {"ventgrill.gif"};
        materials.addElement(new PlayerBlocker(grillwork));                      // 17=VentGrill
        int[][] lockProgram1 = {
                {Lock.WIDE},
                {17, 9, 0, 17, 10, 0},
                {16, 9, 0, 16, 10, 0},
                {15, 9, 0, 15, 10, 0},
                {Lock.REMOVE},
                {15, 9, 9, 15, 10, 9},
                {16, 9, 9, 16, 10, 9},
                {17, 9, 9, 17, 10, 9}
        };
        materials.addElement(new Lock(new Color(255, 128, 0),
                Color.blue, lockProgram1));                // 18= Lock1
        int[][] lockProgram2 = {
                {Lock.NARROW},
                {2, 3, 0},
                {Lock.REMOVE},
                {2, 3, 9}
        };
        materials.addElement(new Lock(new Color(255, 128, 0),
                Color.blue, lockProgram2));                // 19= Lock1
        materials.addElement(new ForceField("OrangeRobot",
                new Color(255, 128, 0)));              // 20=OrangeFF
        materials.addElement(new ForceField("WhiteRobot", Color.white));         // 21=WhiteFF
        materials.addElement(new ForceField("BlueRobot", Color.blue));           // 22=BlueFF
        materials.addElement(new Switch4A());                                    // 23=Switch
        materials.addElement(new Switch4B());                                    // 24=Switch
        materials.addElement(new Switch4C());                                    // 25=Switch
        materials.addElement(new Switch4D());                                    // 26=Switch
        materials.addElement(new Portal("RO5.lvl", true, true));                  // 27=Portal

        int[][] lockProgram3 = {
                {Lock.NARROW},
                {9, 11, 0, 10, 11, 0},
                {8, 11, 0, 11, 11, 0},
                {Lock.NARROW},
                {8, 11, 5, 11, 11, 5},
                {9, 11, 5, 10, 11, 5},
        };
        materials.addElement(new Lock(Color.red, Color.red, lockProgram3));       // 28= Lock1

        for (int a = 0; a < 62; a++) {
            rooms.addElement(new Room());
        }

        String[] camFiles = {
                "cam0.gif",
                "cam0.gif",
                "cam1.gif",
                "cam1.gif",
                "cam2.gif",
                "cam2.gif",
                "cam1.gif",
                "cam1.gif"
        };
        Graphix camGraphix = new Graphix(camFiles, 28, 32);


        {// Room  0 : Help Screen 
            Room room = rooms.elementAt(0);
            room.SetMaterialOutline(0, 0, 19, 11, 1);
            room.AddTextBox("MASTER COMPUTER CENTER", 136, 64, 560);
            room.AddTextBox("HINTS:", 2 * 28, 3 * 32, 560);
            room.AddTextBox("A central robot can become your friend.", 4 * 28, 4 * 32, 400);
            room.AddTextBox("The Sweeper 'Bot likes to keep the hallways clean.", 4 * 28, 6 * 32 + 16, 400);
            room.AddTextBox("Chips 7 & 8 are blank.", 4 * 28, 9 * 32, 400);
            room.AddTextBox("(To continue, press RETURN.)", 4 * 28, 11 * 32, 560);
        }
        {// Room  1 : Master Computer Help 
            Room room = rooms.elementAt(1);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.AddTextBox("THE MASTER COMPUTER", 6 * 28, 2 * 32, 500);
            room.AddTextBox("The master computer controls many of the features of Robotropolis.",
                    72, 4 * 32, 480);
            room.AddTextBox("Use the cameras to monitor robot progress.",
                    72, 6 * 32, 500);
            room.AddTextBox("The computer can be turned off and the robot released by pressing the four buttons.",
                    72, 8 * 32, 450);
            room.AddTextBox("(To continue, press RETURN.)", 4 * 28, 11 * 32, 560);

        }
        {// Room  2 : Minefield Help 
            Room room = rooms.elementAt(2);
            room.SetMaterialFromRoom(1);
            room.AddTextBox("THE MINEFIELD", 6 * 28, 2 * 32, 450);
            room.AddTextBox("The walls in the map room are in the same position as the mines in the minefield.",
                    2 * 28, 3 * 32, 500);
            room.AddTextBox("Mines are set off when a robot thrusts against them for more than an instant.",
                    2 * 28, 6 * 32, 500);
            room.AddTextBox("Additional mines keep out non-robots.", 2 * 28, 8 * 32, 500);
            room.AddTextBox("Robot communication is helpful.", 2 * 28, 9 * 32, 500);
            room.AddTextBox("(To continue, press RETURN.)", 4 * 28, 11 * 32, 560);
        }
        {// Room  3 : Force Field Help 
            Room room = rooms.elementAt(3);
            room.SetMaterialFromRoom(1);
            room.AddTextBox("THE FORCE FIELD TUNNEL", 6 * 28, 2 * 32, 500);
            room.AddTextBox("Robot relays pass key items along.", 2 * 28, 4 * 32, 500);
            room.AddTextBox("Plan ahead for the return journey.", 2 * 28, 6 * 32, 500);
            room.AddTextBox("(To continue, press RETURN.)", 4 * 28, 11 * 32, 560);
        }
        {// Room  4 : Ventilation Shaft Help 
            Room room = rooms.elementAt(4);
            room.SetMaterialFromRoom(1);
            room.AddTextBox("THE VENTILATION SHAFT", 6 * 28, 2 * 32, 500);
            room.AddTextBox("Counting chips can pick out air tubes.", 2 * 28, 4 * 32, 500);
            room.AddTextBox("Be sure to pick the right tube.", 2 * 28, 6 * 32, 560);
            room.AddTextBox("(To continue, press RETURN.)", 4 * 28, 11 * 32, 560);
        }
        {// Room  5 : Top Left Corridor 
            Room room = rooms.elementAt(5);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room  6 : Top Corridor 
            Room room = rooms.elementAt(6);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room  7 : Top Corridor 
            Room room = rooms.elementAt(7);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room  8 : Top Corridor, Entance to Map Room 
            Room room = rooms.elementAt(8);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 7, 11, 3);
            room.SetMaterialFill(12, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room  9 : Top Corridor 
            Room room = rooms.elementAt(9);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 10 : Top Corridor 
            Room room = rooms.elementAt(10);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 11 : Top Right Corridor 
            Room room = rooms.elementAt(11);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(15, 0, 19, 11, 3);
            room.SetMaterialFill(0, 9, 4, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 12 : Right Corridor, Exit from Map Room 
            Room room = rooms.elementAt(12);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3}
            };

            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 13 : Right Corridor 
            Room room = rooms.elementAt(13);
            int[][] table = {
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3}
            };
            room.RoomArray = table;
            rooms.elementAt(15).RoomArray = table;
            rooms.elementAt(33).RoomArray = table;
            rooms.elementAt(35).RoomArray = table;

            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 14 : Right Corridor, Key Tunnel 
            Room room = rooms.elementAt(14);
            int[][] table = {
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3}
            };
            room.RoomArray = table;
            rooms.elementAt(16).RoomArray = table;

            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 15 : Right Corridor 
            Room room = rooms.elementAt(15);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 16 : Right Corridor, Main Office 
            Room room = rooms.elementAt(16);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 17 : Bottom Right Corridor 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };

            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 18 : Bottom Corridor 
            Room room = rooms.elementAt(18);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 19 : Bottom Corridor, Entrance 
            Room room = rooms.elementAt(19);
            room.SetMaterialFill(0, 0, 7, 2, 3);
            room.SetMaterialFill(12, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new TrashCollector(266, 176, room));
        }
        {// Room 20 : Ventilation Shaft Maze 
            Room room = rooms.elementAt(20);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 5},
                    {5, 5, 0, 0, 0, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 21 : Ventilation Shaft Exit (Entrance to Master Chamber) 
            Room room = rooms.elementAt(21);
            room.SetMaterialFill(0, 0, 19, 1, 3);
            room.SetMaterialFill(0, 0, 2, 11, 3);
            room.SetMaterialFill(17, 0, 19, 4, 3);
            room.SetMaterialFill(17, 8, 19, 11, 3);
            room.SetMaterialFill(7, 10, 19, 11, 3);
            room.SetMaterialFill(19, 5, 19, 8, 17);
            room.AddTextBox("{BIG} {000,204,000} NORTH CHAMBER", 98, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 22 : Key Tunnel, Bottom Right 
            Room room = rooms.elementAt(22);
            room.SetMaterialFill(0, 0, 14, 1, 3);
            room.SetMaterialFill(18, 0, 19, 11, 3);
            room.SetMaterialFill(0, 10, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} TUNNEL BYPASS", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 23 : Key Tunnel, Bottom Left 
            Room room = rooms.elementAt(23);
            room.SetMaterialFill(0, 0, 2, 11, 3);
            room.SetMaterialFill(0, 10, 19, 11, 3);
            room.SetMaterialFill(5, 0, 19, 1, 3);
            room.AddTextBox("{BIG} {000,204,000} TUNNEL BYPASS", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 24 : Upper Library 
            Room room = rooms.elementAt(24);
            room.SetMaterialFill(0, 0, 19, 0, 3);
            room.SetMaterialFill(0, 0, 0, 3, 3);
            room.SetMaterialFill(0, 7, 0, 11, 3);
            room.SetMaterial(19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} LIBRARY", 185, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 25 : Library, entrance to Ventilation Shaft 
            Room room = rooms.elementAt(25);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialFill(0, 1, 0, 10, 0);
            room.SetMaterialFill(19, 5, 19, 6, 17);
            room.AddTextBox("{BIG} {000,204,000} LIBRARY", 185, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new Disk(17 * 28, 3 * 32, room, Color.blue, 4));
        }
        {// Room 26 : Lower Library 
            Room room = rooms.elementAt(26);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialFill(1, 0, 18, 0, 0);
            room.SetMaterialFill(19, 7, 19, 9, 0);
            room.AddTextBox("{BIG} {000,204,000} LIBRARY", 185, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 27 : Computer Room 
            Room room = rooms.elementAt(27);
            room.SetMaterialOutline(0, 0, 19, 11, 3);
            room.SetMaterialFill(0, 7, 0, 9, 0);
            room.SetMaterial(10, 2, 12);
            room.AddTextBox("{BIG} {000,204,000} COMPUTER ROOM", 104, 708, 560);
            room.AddGraphix("monitor.gif", 8 * 28, 2 * 32);
            room.graphix.addElement(camGraphix);
            items.addElement(new CamDisk(2 * 28, 9 * 32, room));
            items.addElement(new SpyCam(room));
        }
        {// Room 28 : Bottom Corridor, Entrance 
            Room room = rooms.elementAt(28);
            room.SetMaterialFill(0, 0, 7, 2, 3);
            room.SetMaterialFill(12, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 29 : Bottom Corridor 
            Room room = rooms.elementAt(29);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 30 : Bottom Corridor 
            Room room = rooms.elementAt(30);
            room.SetMaterialFill(0, 0, 19, 2, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 31 : Bottom Left Corridor 
            Room room = rooms.elementAt(31);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(0, 9, 19, 11, 3);
            room.SetMaterialFill(15, 0, 19, 2, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 32 : Left Corridor, Entrance to Elevator 
            Room room = rooms.elementAt(32);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 0, 19, 3, 3);
            room.SetMaterialFill(15, 7, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 33 : Left Corridor 
            Room room = rooms.elementAt(33);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 0, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 34 : Left Corridor, Entrance to Minefield 
            Room room = rooms.elementAt(34);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 0, 19, 6, 3);
            room.SetMaterialFill(15, 10, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 35 : Left Corridor 
            Room room = rooms.elementAt(35);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 0, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 36 : Master Chamber 
            Room room = rooms.elementAt(36);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 26, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 0, 0, 0, 25, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 4, 4, 0, 0, 23, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 24, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 4}
            };

            room.AddTextBox("{BIG} {000,204,000} MASTER    COMPUTER", 37, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new MasterRobot(9 * 28, 5 * 32 + 12, room));
        }
        {// Room 37 : Left Corridor, Entrance to Library 
            Room room = rooms.elementAt(37);
            room.SetMaterialFill(0, 0, 4, 11, 3);
            room.SetMaterialFill(15, 0, 19, 3, 3);
            room.SetMaterialFill(15, 7, 19, 11, 3);
            room.AddTextBox("{BIG} {000,204,000} HALLWAY", 180, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 38 : Map Antechamber 
            Room room = rooms.elementAt(38);
            room.RoomArray = new int[][]{
                    {6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };

            room.AddTextBox("{BIG} {000,204,000} MAP ANTEROOM", 118, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new Disk(6 * 28, 8 * 32, room, Color.yellow, 2));
        }
        {// Room 39 : Map Room, Top Left 
            Room room = rooms.elementAt(39);
            room.RoomArray = new int[][]{
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 6}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD MAP", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 40 : Main Office 
            Room room = rooms.elementAt(40);
            room.RoomArray = new int[][]{
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}
            };

            room.AddTextBox("{BIG} {000,204,000} MAIN OFFICE", 131, 708, 560);
            items.addElement(new Disk(17 * 28, 2 * 32, room, Color.red, 1));
            room.graphix.addElement(camGraphix);
        }
        {// Room 41 : Map Room, Top Right 
            Room room = rooms.elementAt(41);
            room.RoomArray = new int[][]{
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD MAP", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 42 : Minefield, Bottom Left 
            Room room = rooms.elementAt(42);
            room.RoomArray = new int[][]{
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 0, 0, 0, 0, 0, 8, 0, 0},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD", 159, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 43 : Minefield, Bottom Right 
            Room room = rooms.elementAt(43);
            room.RoomArray = new int[][]{
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD", 159, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 44 : Minefield, Top Left 
            Room room = rooms.elementAt(44);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 0, 0, 0, 8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {16, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {16, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                    {16, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD", 159, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 45 : Minefield, Top Right 
            Room room = rooms.elementAt(45);
            room.RoomArray = new int[][]{
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD", 159, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 46 : Key Tunnel, Top Left 
            Room room = rooms.elementAt(46);
            room.RoomArray = new int[][]{
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {0, 0, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 0, 0, 0, 20, 0, 0, 0, 21, 0, 0, 0, 21, 0, 0, 0},
                    {9, 9, 9, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 9, 0, 0, 19, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 9, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
            };

            room.AddTextBox("{BIG} {000,204,000} FORCE FIELD    TUNNEL", 132, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 47 : Key Tunnel, Top Right 
            Room room = rooms.elementAt(47);
            room.RoomArray = new int[][]{
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 9, 9},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 18, 9},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 9, 9},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 9, 9},
                    {0, 0, 0, 22, 0, 0, 0, 22, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 9, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
                    {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 9, 9}
            };

            room.AddTextBox("{BIG} {000,204,000} FORCE FIELD    TUNNEL", 132, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new Disk(16 * 28, 2 * 32, room, Color.green, 3));
        }
        {// Room 48 : Ventilation Shaft Left 
            Room room = rooms.elementAt(48);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 49 : Ventilation Shaft Right 
            Room room = rooms.elementAt(49);
            room.RoomArray = new int[][]{
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
            String[] fanlist = {
                    "fan0.gif",
                    "fan1.gif",
                    "fan2.gif",
                    "fan3.gif",
                    "fan2.gif",
                    "fan1.gif"};
            room.graphix.addElement(new Graphix(fanlist, 17 * 28, 5 * 32));
        }
        {// Room 50 : Ventilation Shaft Maze 
            Room room = rooms.elementAt(50);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 5},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 5, 5, 0, 0, 0, 0},
                    {5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 51 : Ventilation Shaft Maze 
            Room room = rooms.elementAt(51);
            room.RoomArray = new int[][]{
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 52 : Ventilation Shaft Maze 
            Room room = rooms.elementAt(52);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                    {5, 5, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5},
                    {5, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                    {5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5},
                    {5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0, 0, 0, 5, 5, 5}
            };

            room.AddTextBox("{BIG} {000,204,000} VENTILATION    SHAFT", 126, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 53 : Map Room, Bottom Left 
            Room room = rooms.elementAt(53);
            room.RoomArray = new int[][]{
                    {6, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 6},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {6, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0, 0, 0, 0, 0, 6, 0, 0},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD MAP", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 54 : Map Room, Bottom Right 
            Room room = rooms.elementAt(54);
            room.RoomArray = new int[][]{
                    {6, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}
            };

            room.AddTextBox("{BIG} {000,204,000} MINEFIELD MAP", 104, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 55 : Energy Chamber, Upper 
            Room room = rooms.elementAt(55);
            room.RoomArray = new int[][]{
                    {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10}
            };

            room.AddTextBox("{BIG} {000,204,000} HIGH VOLTAGE     ROOM", 118, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new NotAButton(10 * 28, 6 * 32, room));
            items.addElement(new EnergyButton());
        }
        {// Room 56 : Elevator, Left 
            Room room = rooms.elementAt(56);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("{BIG} {000,204,000} LOBBY", 212, 708, 560);
            room.graphix.addElement(camGraphix);
        }
        {// Room 57 : Energy Chamber, Lower 
            Room room = rooms.elementAt(57);
            room.RoomArray = new int[][]{
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10},
                    {10, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 10},
                    {10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                    {10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10}
            };

            room.AddTextBox("{BIG} {000,204,000} SAFETY ROOM", 131, 708, 560);
            room.graphix.addElement(camGraphix);
            room.AddGraphix("FFleft.gif", 30, 4 * 32 + 3);
            room.AddGraphix("FFright.gif", 18 * 28 - 2, 4 * 32 + 3);
            room.AddTextBox("..........................................", 28, 4 * 32 + 16, 560);
        }
        {// Room 58 : Trash Dump 
            Room room = rooms.elementAt(58);
            room.RoomArray = new int[][]{
                    {11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
                    {11, 11, 11, 11, 11, 11, 11, 11, 0, 0, 0, 0, 11, 11, 11, 11, 11, 11, 11, 11}
            };

            room.AddTextBox("{BIG} {000,204,000} TRASH DUMP", 145, 708, 560);
            room.graphix.addElement(camGraphix);
            items.addElement(new SmallChip(3 * 28, 9 * 32, room, "7"));
        }
        {// Room 59 : Elevator, Right 
            Room room = rooms.elementAt(59);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 27, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("{BIG} {000,204,000} ELEVATOR", 172, 708, 560);
            room.AddTextBox("\"Hold, please!\"", 84, 54, 560);
            room.AddTextBox("HOLD", 480, 350, 560);
            room.graphix.addElement(camGraphix);
            room.AddGraphix("hold.gif", 18 * 28, 10 * 32 - 4);
            items.addElement(new SmallChip(3 * 28, 9 * 32, room, "8"));
        }

        {// Room 60 : Purgatorium 
            Room room = rooms.elementAt(60);
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
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, 5},
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
            room.AddTextBox("{BIG} {255,000,000} PURGATORIUM", 131, 64, 500);
            room.AddTextBox("(Please wait for Trash Room)", 112, 11 * 32, 500);
        }
        {// Room 61 : Secret Room 
            Room room = rooms.elementAt(61);
            room.RoomArray = new int[][]{
                    {5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5},
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
                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
            room.AddTextBox("Hello again, organic dude!", 2 * 28, 4 * 32, 500);
            room.AddTextBox("To find secret #5, you must enter a Zen state of mind... To open a door is to close a door, and to close a door is to open a door.",
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
            items.addElement(new Key(16 * 28, 3 * 32, room, new Color(255, 128, 0)));
        }

        // Main area map
        int[][] roomgrid =
                {
                        {5, 6, 7, 8, 9, 10, 11},
                        {37, 24, 25, 38, 39, 41, 12},
                        {35, 26, 27, 21, 53, 54, 13},
                        {34, 44, 45, 36, 46, 47, 14},
                        {33, 42, 43, 55, 23, 22, 15},
                        {32, 56, 59, 57, 58, 40, 16},
                        {31, 30, 29, 28, 19, 18, 17}
                };
        LinkRoomsGrid(roomgrid);

        // Ventilation shaft map
        // 
        //      20       52
        //       |        |
        //(25)- 48 ----- 49 -(38)
        //       |        | 
        //       | (21)- 51 -(53)
        //       |        |
        // 20 - 50 ----- 52 - 20
        //       |        
        //      20

        int[] roomlist1 = {25, 48, 49, 38};
        int[] roomlist2 = {21, 51, 53};
        int[] roomlist3 = {20, 50, 52, 20};
        int[] roomlist4 = {20, 48, 50, 20};
        int[] roomlist5 = {52, 49, 51, 52};

        LinkRoomsHorizontally(roomlist1);
        LinkRoomsHorizontally(roomlist2);
        LinkRoomsHorizontally(roomlist3);
        LinkRoomsVertically(roomlist4);
        LinkRoomsVertically(roomlist5);

        LinkRoomsUpDown(60, 61);

        gameCursor = new GameCursor(6 * 28, 8 * 32, rooms.elementAt(40));
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