package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.*;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.devices.*;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.MazeLock;

import java.awt.*;

class ROLab extends Level {
    public ROLab(RoomDisplay rd) {
        super(rd);

        // Material 0, Blank
        materials.addElement(new Material(true, false));
        // Material 1, LightBlue Wall 
        materials.addElement(new Material(new Color(192, 192, 255), false, true));
        // Material 2, LightOrange Wall 
        materials.addElement(new Material(new Color(255, 224, 192), false, true));
        // Material 3, Red Wall 
        materials.addElement(new Material(new Color(255, 0, 0), false, true));
        // Material 4, Green Wall 
        materials.addElement(new Material(new Color(0, 255, 0), false, false));
        // Material 5, CrystalRecharger 
        materials.addElement(new CrystalRecharger());
        // Material 6, Crystal Shape Editor
        materials.addElement(new ShapeEditor(new Crystal(0, 0, null, 0)));
        // Material 7, Hexagon Shape Editor
        materials.addElement(new ShapeEditor(new Hexagon(0, 0, null, Color.blue)));
        // Material 8, Square Shape Editor
        materials.addElement(new ShapeEditor(new Square(0, 0, null, Color.white)));
        // Material 9, Triangle Shape Editor
        materials.addElement(new ShapeEditor(new Triangle(0, 0, null, new Color(255, 128, 0))));
        // Material 10, PrototypeBurner
        materials.addElement(new PrototypeBurner());
        // Material 11, SmallChipBurner
        materials.addElement(new SmallChipBurner());
        // Material 12, ChipTrash
        materials.addElement(new ChipTrash());
        // Material 13, ChipTester
        materials.addElement(new ChipTester());
        // Material 14, Lock
        int[][] lockProgram = {
                {Lock.NARROW},
                {0, 1, 1, 19, 1, 1},
                {0, 2, 1, 19, 2, 1},
                {0, 3, 1, 19, 3, 1},
                {Lock.NARROW},
                {0, 3, 0, 19, 3, 0},
                {0, 2, 0, 19, 2, 0},
                {0, 1, 0, 19, 1, 0},
        };
        materials.addElement(new Lock(new Color(192, 192, 255), Color.white, lockProgram));
        // Materials 15, MazeLock
        materials.addElement(new MazeLock());


        for (int a = 0; a < 18; a++) {
            rooms.addElement(new Room());
        }

        {  // Room 0, Help Screen 
            Room room = rooms.elementAt(0);
            room.RoomArray = new int[][]{
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
            };
            room.AddTextBox("Use the Innovation Lab to design and test circuits in robots and the large prototype chip.",
                    2 * 28, 2 * 32, 500);
            room.AddTextBox("Burn a small chip from your prototype chip in the burn room.",
                    2 * 28, 5 * 32, 500);
            room.AddTextBox("Change maze walls with the paint brush. Change sensor bodies and maze objects in the Shape Editor.",
                    2 * 28, 7 * 32, 500);
            room.AddTextBox("For help, see Tutorials.",
                    2 * 28, 10 * 32, 500);
            room.AddTextBox("(To go to Lab, press Return.)",
                    4 * 28, 11 * 32, 500);
        }
        {  // Room 1, Chip Testing Room 
            Room room = rooms.elementAt(1);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 13, 13, 13, 13, 13, 13, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("{000,000,000} Chip Tester", (560 - (11 * 12)) / 2, 32 + 24, 500);
            PortDevice[] portdevices = new PortDevice[8];
            portdevices[0] = new PortDevice(6 * 28 + 4, 3 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[1] = new PortDevice(6 * 28 + 4, 4 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[2] = new PortDevice(6 * 28 + 4, 5 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[3] = new PortDevice(6 * 28 + 4, 6 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[4] = new PortDevice(12 * 28 + 4, 6 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[5] = new PortDevice(12 * 28 + 4, 5 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[6] = new PortDevice(12 * 28 + 4, 4 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
            portdevices[7] = new PortDevice(12 * 28 + 4, 3 * 32 + 4, room, 28, Port.TYPE_UNDEFINED);
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
        {  // Room 2, Storage Space 1 
            Room room = rooms.elementAt(2);
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
            room.AddTextBox("Storage Space", 2 * 28, 2 * 32, 500);
        }
        {  // Room 3, Storage Space 2 
            Room room = rooms.elementAt(3);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
            };
        }
        {  // Room 4, Burner Room 
            Room room = rooms.elementAt(4);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 2, 10, 10, 10, 10, 2, 0, 0, 0, 2, 11, 11, 2, 0, 0, 0, 0, 0},
                    {2, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 11, 11, 2, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };
            room.AddTextBox("Burner Room", 2 * 28, 2 * 32, 200);
            room.AddTextBox("1x", 15 * 28 - 14, 11 * 32 - 8, 100);
            items.addElement(new SpeedControl(15 * 28, 7 * 32, room, SpeedControl.DIR_UP));
            items.addElement(new SpeedControl(15 * 28, 9 * 32, room, SpeedControl.DIR_DOWN));
            items.addElement(new SmallChip(12 * 28, 1 * 32 + 16, room, "1"));
            items.addElement(new SmallChip(14 * 28, 1 * 32 + 16, room, "2"));
            items.addElement(new SmallChip(16 * 28, 1 * 32 + 16, room, "3"));
            items.addElement(new SmallChip(18 * 28, 1 * 32 + 16, room, "4"));
            items.addElement(new SmallChip(12 * 28, 3 * 32, room, "5"));
            items.addElement(new SmallChip(14 * 28, 3 * 32, room, "6"));
            items.addElement(new SmallChip(16 * 28, 3 * 32, room, "7"));
            items.addElement(new SmallChip(18 * 28, 3 * 32, room, "8"));
            items.addElement(new Burner(18 * 28, 10 * 32 + 2, room));
            items.addElement(new UnBurner(2 * 28, 10 * 32 + 2, room));
            room.AddTextBox("{000,000,000} Unburner", 1 * 28, 12 * 32 - 8, 200);
            room.AddTextBox("{000,000,000} Burner", 17 * 28, 12 * 32 - 8, 200);
        }
        {  // Room 5, Title Room  
            Room room = rooms.elementAt(5);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
            };
            room.AddTextBox("{BIG} Innovation Lab", 3 * 28, 2 * 32, 600);
            items.addElement(new PrototypeChip(8 * 28, 4 * 32, room));
            items.addElement(new BlueRobot(4 * 28, 8 * 32, room));
            items.addElement(new WhiteRobot(9 * 28, 8 * 32, room));
            items.addElement(new OrangeRobot(14 * 28, 8 * 32, room));
        }
        {  // Room 6, Chip Factory 
            Room room = rooms.elementAt(6);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 12, 12, 12, 12, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("Chip Factory", 2 * 28, 2 * 32, 500);
            room.AddTextBox("Press for Prototype", 5 * 28, 9 * 32 + 18, 500);
            room.AddTextBox("Press for Small Chip", 5 * 28, 10 * 32 + 18, 500);
            room.AddTextBox("TRASH", 16 * 28 - 2, 9 * 32, 500);
            room.AddArrow(3 * 28 + 14, 9 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            room.AddArrow(3 * 28 + 14, 10 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            items.addElement(new PCButton(2 * 28, 9 * 32, room));
            items.addElement(new Factory(2 * 28, 10 * 32, room, new SmallChip(0, 0, null, "X")));
            items.addElement(new PC16Button(18 * 28, 1 * 32, room));
            items.addElement(new PC32Button(18 * 28, 2 * 32, room));
            room.AddTextBox("16 Pin", 15 * 28, 1 * 32 + 14, 500);
            room.AddTextBox("32 Pin", 15 * 28, 2 * 32 + 14, 500);
        }
        {  // Room 7, Sensor & Object Factory  
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 6, 0, 0, 7, 0, 0, 8, 0, 0, 9, 0, 0, 1},
                    {1, 12, 12, 12, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

            Triangle t = new Triangle(0, 0, null, new Color(255, 128, 0));
            items.addElement(new Factory(2 * 28, 3 * 32, room, t));
            items.addElement(new Factory(5 * 28, 3 * 32, room,
                    new ContactSensor(0, 0, null, t)));
            items.addElement(new Factory(8 * 28, 3 * 32, room,
                    new RoomSensor(0, 0, null, t)));
            items.addElement(new Factory(12 * 28, 3 * 32, room,
                    new DirectionalSensor(0, 0, null, t)));

            room.AddTextBox("Sensor & Object Factory", 142, 2 * 32 - 8, 500);
            room.AddTextBox("Shape Editor Icons", 228, 8 * 32, 400);
            room.AddTextBox("TRASH", 40, 9 * 32, 400);

//	     items.addElement(new Crystal(2*28,10*32,room,100000));
//	     items.addElement(new Crystal(5*28,10*32,room,100000));
//	     items.addElement(new Square(8*28,10*32,room,Color.white));
//	     items.addElement(new Triangle(11*28,10*32,room,Color.blue));
//	     items.addElement(new Hexagon(14*28,10*32,room,new Color(255,128,0)));
//	     items.addElement(new Crystal(17*28,10*32,room,0));
//	     items.addElement(new ContactSensor(3*28,2*32,room,new Square(0,0,null,Color.white)));
//	     items.addElement(new ContactSensor(9*28,2*32,room,new Crystal(0,0,null,0)));
//	     items.addElement(new ContactSensor(15*28,2*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.addElement(new RoomSensor(3*28,4*32,room,new Crystal(0,0,null,0)));
//	     items.addElement(new RoomSensor(9*28,4*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.addElement(new RoomSensor(15*28,4*32,room,new Triangle(0,0,null,Color.white)));
//	     items.addElement(new DirectionalSensor(2*28,6*32,room,new Hexagon(0,0,null,Color.white)));
//	     items.addElement(new DirectionalSensor(8*28,6*32,room,new Triangle(0,0,null,Color.white)));
//	     items.addElement(new DirectionalSensor(14*28,6*32,room,new Square(0,0,null,Color.white)));
        }
        {  // Room 8, Recharger Room 
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 14},
                    {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("To Maze", 2 * 28, 11 * 32, 200);
            room.AddArrow(28 + 14, 12 * 32 - 1, Arrow.DIR_DOWN, 32, Color.white);
            room.AddArrow(28 + 14, 12 * 32 - 1, Arrow.DIR_DOWN, 32, Color.white);
            items.addElement(new Key(15 * 28 + 16, 10 * 32 + 12, room, Color.white));
        }
        {  // Room 9, Maze Control Room  
            Room room = rooms.elementAt(9);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("Maze Control Room", 178, 2 * 32, 300);
            room.AddTextBox("4x2", 262, 5 * 32 + 24, 500);
            items.addElement(new MazeControl(9 * 28 + 14, 3 * 32 + 6, room, MazeControl.DIR_UP));
            items.addElement(new MazeControl(9 * 28 + 14, 7 * 32, room, MazeControl.DIR_DOWN));
            items.addElement(new MazeControl(7 * 28 + 2, 5 * 32 + 4, room, MazeControl.DIR_LEFT));
            items.addElement(new MazeControl(12 * 28, 5 * 32 + 4, room, MazeControl.DIR_RIGHT));
            items.addElement(new MazeCreator(2 * 28, 10 * 32, room));
            room.AddArrow(3 * 28 + 14, 10 * 32 + 12, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Press to resize Maze", 5 * 28, 10 * 32 + 18, 500);
            room.AddArrow(18 * 28, 10 * 32, Arrow.DIR_DOWN, 28, Color.white);
            room.AddTextBox("Lock", 17 * 28 + 14, 9 * 32, 100);
        }
        {  // Room 10, Maze Top Far Left 
            Room room = rooms.elementAt(10);
            room.RoomArray = new int[][]{
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 11, Maze Top Near Left 
            Room room = rooms.elementAt(11);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 12, Maze Top Near Right 
            Room room = rooms.elementAt(12);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 13, Maze Top Far Right 
            Room room = rooms.elementAt(13);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
            };
            room.editable = true;
        }
        {  // Room 14, Maze Bot Far Left 
            Room room = rooms.elementAt(14);
            room.RoomArray = new int[][]{
                    {3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 15, Maze Bot Near Left 
            Room room = rooms.elementAt(15);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 16, Maze Bot Near Right 
            Room room = rooms.elementAt(16);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }
        {  // Room 17, Maze Bot Far Right 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
            };
            room.editable = true;
        }

        // 00=Help
        // 
        // 01-02-03
        // 04-05-06
        // 07-08-09
        // 
        // 10+ = Maze
        // 10-11-12-13
        // 14-15-16-17

        // 15-16-17
        // 00-01-14
        // 02-03-04
        // 
        // 05-06-07-08
        // 09-10-11-12

        int[][] roomgrid1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        LinkRoomsGrid(roomgrid1);

        LinkRoomsLeftRight(6, 4);

        int[][] roomgrid2 = {
                {10, 11, 12, 13},
                {14, 15, 16, 17}
        };
        LinkRoomsGrid(roomgrid2);

        LinkRoomsUpDown(8, 10);

        gameCursor = new LabCursor(9 * 28 + 14, 9 * 32 + 16, rooms.elementAt(5));
        solderingPen = new SolderingPen();
        remote = new Remote();
        toolbox = new ToolBox(7 * 28, 10 * 32, rooms.elementAt(8));
        helpCam = new HelpCam(rooms.elementAt(0));
        items.addElement(toolbox);
        ((ToolBox) toolbox).Toggle();
        paintbrush = new PaintBrush();
        items.addElement(paintbrush);
        items.addElement(gameCursor);
        items.addElement(solderingPen);
        items.addElement(remote);
        items.addElement(helpCam);
        player = gameCursor;
        currentViewer = player;
    }

}
