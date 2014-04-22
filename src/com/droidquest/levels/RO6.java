package com.droidquest.levels;

import com.droidquest.Room;
import com.droidquest.RoomDisplay;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.devices.DirectionalSensor;
import com.droidquest.items.*;
import com.droidquest.materials.*;
import com.droidquest.materials.switches.Switch;

import java.awt.*;

class RO6 extends Level {
    public RO6(RoomDisplay rd) {
        super(rd);

        materials.addElement(new Material(true, false));               // 0  = Empty Space 
        materials.addElement(new Material(Color.blue, false, true));   // 1  = Blue
        materials.addElement(new Material(Color.white, false, true));  // 2  = White
        materials.addElement(new Material(Color.red, false, true));  // 3  = Red
        materials.addElement(new Material(new Color(255, 128, 0), false, true));  // 4  = Orange
        materials.addElement(new Material(Color.yellow, false, true));  // 5  = Yellow
        materials.addElement(new Material(Color.green, false, true));  // 6  = Green
        materials.addElement(new Material(new Color(255, 0, 255), false, true));  // 7  = Purple

        materials.addElement(new HotWires(HotWires.UP
                + HotWires.DOWN
                + HotWires.LEFT
                + HotWires.RIGHT,
                true
        ));           // 8=HotWire cross
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.DOWN,
                true
        ));           // 9=HotWire Vertical
        materials.addElement(new HotWires(HotWires.LEFT
                + HotWires.RIGHT,
                true
        ));           // 10=HotWire Horizontal
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.LEFT,
                true
        ));           // 11=HotWire J
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.RIGHT,
                true
        ));           // 12=HotWire L
        materials.addElement(new HotWires(+HotWires.DOWN
                + HotWires.LEFT,
                true
        ));           // 13=HotWire 7
        materials.addElement(new HotWires(+HotWires.DOWN
                + HotWires.RIGHT,
                true
        ));           // 14=HotWire r
        materials.addElement(new HotWires(HotWires.DOWN
                + HotWires.LEFT
                + HotWires.RIGHT,
                true
        ));           // 15=HotWire T
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.LEFT
                + HotWires.RIGHT,
                true
        ));           // 16=HotWire _|_
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.DOWN
                + HotWires.RIGHT,
                true
        ));           // 17=HotWire |-
        materials.addElement(new HotWires(HotWires.UP
                + HotWires.DOWN
                + HotWires.LEFT,
                true
        ));           // 18=HotWire -|

        int[][] redlock = {
                {Lock.WIDE},
                {Lock.LEFT},
                {Lock.DOWN},
                {Lock.DOWN},
                {Lock.RIGHT},
                {1, 5, 0, 1, 6, 0, 1, 2, 3, 1, 9, 3},
                {1, 4, 0, 1, 7, 0, 1, 1, 3, 1, 10, 3, 2, 5, 0, 2, 6, 0, 2, 2, 3, 2, 9, 3},
                {2, 4, 0, 2, 7, 0, 2, 1, 3, 2, 10, 3, 3, 5, 0, 3, 6, 0, 3, 2, 3, 3, 9, 3},
                {3, 4, 0, 3, 7, 0, 3, 1, 3, 3, 10, 3},
                {Lock.REMOVE},
                {Lock.LEFT},
                {Lock.DOWN},
                {Lock.DOWN},
                {Lock.RIGHT},
                {3, 4, 3, 3, 7, 3, 3, 1, 0, 3, 10, 0},
                {2, 4, 3, 2, 7, 3, 2, 1, 0, 2, 10, 0, 3, 5, 3, 3, 6, 3, 3, 2, 0, 3, 9, 0},
                {1, 4, 3, 1, 7, 3, 1, 1, 0, 1, 10, 0, 2, 5, 3, 2, 6, 3, 2, 2, 0, 2, 9, 0},
                {1, 5, 3, 1, 6, 3, 1, 2, 0, 1, 9, 0}
        };
        materials.addElement(new Lock(Color.red, Color.red, redlock)); // 19=RedLock

        int[][] orangelock = {
                {Lock.WIDE},
                {Lock.RIGHT},
                {Lock.DOWN},
                {Lock.RIGHT},
                {4, 5, 0, 4, 6, 0, 4, 2, 4, 4, 9, 4},
                {4, 4, 0, 4, 7, 0, 4, 1, 4, 4, 10, 4, 5, 5, 0, 5, 6, 0, 5, 2, 4, 5, 9, 4},
                {5, 4, 0, 5, 7, 0, 5, 1, 4, 5, 10, 4, 6, 5, 0, 6, 6, 0, 6, 2, 4, 6, 9, 4},
                {6, 4, 0, 6, 7, 0, 6, 1, 4, 6, 10, 4},
                {Lock.REMOVE},
                {Lock.RIGHT},
                {Lock.DOWN},
                {Lock.RIGHT},
                {6, 4, 4, 6, 7, 4, 6, 1, 0, 6, 10, 0},
                {5, 4, 4, 5, 7, 4, 5, 1, 0, 5, 10, 0, 6, 5, 4, 6, 6, 4, 6, 2, 0, 6, 9, 0},
                {4, 4, 4, 4, 7, 4, 4, 1, 0, 4, 10, 0, 5, 5, 4, 5, 6, 4, 5, 2, 0, 5, 9, 0},
                {4, 5, 4, 4, 6, 4, 4, 2, 0, 4, 9, 0}
        };
        materials.addElement(new Lock(new Color(255, 128, 0),
                new Color(255, 128, 0), orangelock)); // 20=OrangeLock

        int[][] yellowlock = {
                {Lock.WIDE},
                {Lock.LEFT},
                {Lock.DOWN},
                {Lock.RIGHT},
                {7, 5, 0, 7, 6, 0, 7, 2, 5, 7, 9, 5},
                {7, 4, 0, 7, 7, 0, 7, 1, 5, 7, 10, 5, 8, 5, 0, 8, 6, 0, 8, 2, 5, 8, 9, 5},
                {8, 4, 0, 8, 7, 0, 8, 1, 5, 8, 10, 5, 9, 5, 0, 9, 6, 0, 9, 2, 5, 9, 9, 5},
                {9, 4, 0, 9, 7, 0, 9, 1, 5, 9, 10, 5},
                {Lock.REMOVE},
                {Lock.LEFT},
                {Lock.DOWN},
                {Lock.RIGHT},
                {9, 4, 5, 9, 7, 5, 9, 1, 0, 9, 10, 0},
                {8, 4, 5, 8, 7, 5, 8, 1, 0, 8, 10, 0, 9, 5, 5, 9, 6, 5, 9, 2, 0, 9, 9, 0},
                {7, 4, 5, 7, 7, 5, 7, 1, 0, 7, 10, 0, 8, 5, 5, 8, 6, 5, 8, 2, 0, 8, 9, 0},
                {7, 5, 5, 7, 6, 5, 7, 2, 0, 7, 9, 0}
        };
        materials.addElement(new Lock(Color.yellow,
                Color.yellow, yellowlock)); // 21=YellowLock

        int[][] greenlock = {
                {Lock.WIDE},
                {Lock.RIGHT},
                {Lock.UP},
                {Lock.RIGHT},
                {10, 5, 0, 10, 6, 0, 10, 2, 6, 10, 9, 6},
                {10, 4, 0, 10, 7, 0, 10, 1, 6, 10, 10, 6, 11, 5, 0, 11, 6, 0, 11, 2, 6, 11, 9, 6},
                {11, 4, 0, 11, 7, 0, 11, 1, 6, 11, 10, 6, 12, 5, 0, 12, 6, 0, 12, 2, 6, 12, 9, 6},
                {12, 4, 0, 12, 7, 0, 12, 1, 6, 12, 10, 6},
                {Lock.REMOVE},
                {Lock.RIGHT},
                {Lock.UP},
                {Lock.RIGHT},
                {12, 4, 6, 12, 7, 6, 12, 1, 0, 12, 10, 0},
                {11, 4, 6, 11, 7, 6, 11, 1, 0, 11, 10, 0, 12, 5, 6, 12, 6, 6, 12, 2, 0, 12, 9, 0},
                {10, 4, 6, 10, 7, 6, 10, 1, 0, 10, 10, 0, 11, 5, 6, 11, 6, 6, 11, 2, 0, 11, 9, 0},
                {10, 5, 6, 10, 6, 6, 10, 2, 0, 10, 9, 0}
        };
        materials.addElement(new Lock(Color.green,
                Color.green, greenlock)); // 22=GreenLock

        int[][] bluelock = {
                {Lock.WIDE},
                {Lock.LEFT},
                {Lock.UP},
                {Lock.RIGHT},
                {13, 5, 0, 13, 6, 0, 13, 2, 1, 13, 9, 1},
                {13, 4, 0, 13, 7, 0, 13, 1, 1, 13, 10, 1, 14, 5, 0, 14, 6, 0, 14, 2, 1, 14, 9, 1},
                {14, 4, 0, 14, 7, 0, 14, 1, 1, 14, 10, 1, 15, 5, 0, 15, 6, 0, 15, 2, 1, 15, 9, 1},
                {15, 4, 0, 15, 7, 0, 15, 1, 1, 15, 10, 1},
                {Lock.REMOVE},
                {Lock.LEFT},
                {Lock.UP},
                {Lock.RIGHT},
                {15, 4, 1, 15, 7, 1, 15, 1, 0, 15, 10, 0},
                {14, 4, 1, 14, 7, 1, 14, 1, 0, 14, 10, 0, 15, 5, 1, 15, 6, 1, 15, 2, 0, 15, 9, 0},
                {13, 4, 1, 13, 7, 1, 13, 1, 0, 13, 10, 0, 14, 5, 1, 14, 6, 1, 14, 2, 0, 14, 9, 0},
                {13, 5, 1, 13, 6, 1, 13, 2, 0, 13, 9, 0}
        };
        materials.addElement(new Lock(Color.blue,
                Color.blue, bluelock)); // 23=BlueLock

        int[][] purplelock = {
                {Lock.WIDE},
                {Lock.LEFT},  // Positioning is not set right yet.
                {Lock.UP},
                {Lock.UP},
                {Lock.RIGHT},
                {16, 5, 0, 16, 6, 0, 16, 2, 7, 16, 9, 7},
                {16, 4, 0, 16, 7, 0, 16, 1, 7, 16, 10, 7, 17, 5, 0, 17, 6, 0, 17, 2, 7, 17, 9, 7},
                {17, 4, 0, 17, 7, 0, 17, 1, 7, 17, 10, 7, 18, 5, 0, 18, 6, 0, 18, 2, 7, 18, 9, 7},
                {18, 4, 0, 18, 7, 0, 18, 1, 7, 18, 10, 7},
                {Lock.REMOVE},
                {Lock.LEFT},
                {Lock.UP},
                {Lock.UP},
                {Lock.RIGHT},
                {18, 4, 7, 18, 7, 7, 18, 1, 0, 18, 10, 0},
                {17, 4, 7, 17, 7, 7, 17, 1, 0, 17, 10, 0, 18, 5, 7, 18, 6, 7, 18, 2, 0, 18, 9, 0},
                {16, 4, 7, 16, 7, 7, 16, 1, 0, 16, 10, 0, 17, 5, 7, 17, 6, 7, 17, 2, 0, 17, 9, 0},
                {16, 5, 7, 16, 6, 7, 16, 2, 0, 16, 9, 0}
        };
        materials.addElement(new Lock(new Color(255, 0, 255),
                new Color(255, 0, 255), purplelock)); // 24=PurpleLock

        String[] files = {"field0.jpg", "field1.jpg"};
        materials.addElement(new PlayerBlocker(files));                    // 25= Blue FF

        int flipswitch[][] = {
                {Switch.WAIT4CONTACT},
                {Switch.SETVALUEHIGH},
                {Switch.REPLACE, 7, 1, 0, 8, 1, 0, 7, 2, 0, 8, 2, 0, 5, 3, 1, 6, 3, 1, 5, 4, 1, 6, 4, 1},
                {Switch.REPLACE, 11, 1, 0, 12, 1, 0, 11, 2, 0, 12, 2, 0, 9, 3, 1, 10, 3, 1, 9, 4, 1, 10, 4, 1},
                {Switch.REPLACE, 15, 5, 0, 16, 5, 0, 15, 6, 0, 16, 6, 0, 13, 3, 1, 14, 3, 1, 13, 4, 1, 14, 4, 1},
                {Switch.REPLACE, 3, 9, 0, 4, 9, 0, 3, 10, 0, 4, 10, 0, 1, 7, 1, 2, 7, 1, 1, 8, 1, 2, 8, 1},
                {Switch.REPLACE, 7, 9, 0, 8, 9, 0, 7, 10, 0, 8, 10, 0, 9, 7, 1, 10, 7, 1, 9, 8, 1, 10, 8, 1},
                {Switch.REPLACE, 11, 5, 0, 12, 5, 0, 11, 6, 0, 12, 6, 0, 13, 7, 1, 14, 7, 1, 13, 8, 1, 14, 8, 1},
                {Switch.REPLACE, 15, 9, 0, 16, 9, 0, 15, 10, 0, 16, 10, 0, 17, 7, 1, 18, 7, 1, 17, 8, 1, 18, 8, 1},
                {Switch.REPLACE, 18, 10, 0}
        };
        materials.addElement(new Switch(Switch.ROT_DOWN, flipswitch)); // 26= FlipSwitch

        materials.addElement(new CrystalRecharger()); // 27 = Recharger
        materials.addElement(new Portal("ROEndGame2.lvl", true, true)); // 28= Portal

        for (int a = 0; a <= 22; a++) {
            rooms.addElement(new Room());
        }

        {// Room  0 Help 
            Room room = rooms.elementAt(0);
            room.AddTextBox("You're on your own here!",
                    136, 6 * 32, 560);
            room.AddTextBox("(To continue, press RETURN.)",
                    96, 346, 500);
        }
        {// Room  1 Maze 
            Room room = rooms.elementAt(1);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1}
            };

        }
        {// Room  1 Maze 
            Room room = rooms.elementAt(1);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                    {1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1}
            };

        }
        {// Room  2 Maze 
            Room room = rooms.elementAt(2);
            room.SetMaterialFromRoom(1);
        }
        {// Room  3 Maze 
            Room room = rooms.elementAt(3);
            room.SetMaterialFromRoom(1);
        }
        {// Room  4 Maze 
            Room room = rooms.elementAt(4);
            room.SetMaterialFromRoom(1);
        }
        {// Room  5 Maze 
            Room room = rooms.elementAt(5);
            room.SetMaterialFromRoom(1);
        }
        {// Room  6 Maze exit 
            Room room = rooms.elementAt(6);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("Welcome to the Super Secret 6th level!", 52, 64, 560);
            room.AddTextBox("{255,000,000} Cross {255,128,000} the {255,255,000} rainbow {000,255,000} bridge {000,000,255} to {255,000,255} freedom!",
                    64, 9 * 32, 560);
//	     items.addElement(new Key(2*28,3*32,room,Color.red));
//	     items.addElement(new Key(2*28,4*32,room,new Color(255,128,0)));
//	     items.addElement(new Key(2*28,5*32,room,Color.yellow));
//	     items.addElement(new Key(2*28,6*32,room,Color.green));
//	     items.addElement(new Key(2*28,7*32,room,Color.blue));
//	     items.addElement(new Key(2*28,8*32,room,new Color(255,0,255)));
        }

        {// Room  7 Hallway 
            Room room = rooms.elementAt(7);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2}
            };
            room.AddTextBox("Rainbow bridge", 364, 6 * 32, 560);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        {// Room  8 Rainbow Bridge 
            Room room = rooms.elementAt(8);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 2},
                    {0, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 0},
                    {0, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 0},
                    {0, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 0},
                    {0, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 0},
                    {2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 1, 1, 1, 7, 7, 7, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

        }
        {// Room  9 Hallway 
            Room room = rooms.elementAt(9);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2}
            };

            room.AddTextBox("Orange Lock", 28, 6 * 32, 560);
            room.AddArrow(0, 6 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Yellow Lock", 400, 6 * 32, 560);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        {// Room 10 Hallway 
            Room room = rooms.elementAt(10);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2}
            };

            room.AddTextBox("Red Lock Map", 28, 9 * 32, 560);
            room.AddArrow(0, 9 * 32 + 16, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Red Lock", 436, 2 * 32, 560);
            room.AddArrow(559, 2 * 32 + 16, Arrow.DIR_RIGHT, 28, Color.white);
        }
        {// Room 11 Hallway 
            Room room = rooms.elementAt(11);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2}
            };

            room.AddTextBox("Green Lock", 28, 6 * 32, 560);
            room.AddArrow(0, 6 * 32, Arrow.DIR_LEFT, 28, Color.white);
            room.AddTextBox("Blue Lock", 424, 6 * 32, 560);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }
        {// Room 12 Hallway 
            Room room = rooms.elementAt(12);
            room.RoomArray = new int[][]{
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                    {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
            };

            room.AddTextBox("Purple Lock", 400, 6 * 32, 560);
            room.AddArrow(559, 6 * 32, Arrow.DIR_RIGHT, 28, Color.white);
        }

        {// Room 13 Red Lock upper 
            Room room = rooms.elementAt(13);
            room.RoomArray = new int[][]{
                    {10, 10, 10, 10, 10, 10, 10, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 13},
                    {0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 9},
                    {14, 10, 10, 13, 0, 0, 0, 9, 0, 0, 0, 14, 10, 10, 10, 10, 10, 10, 10, 18},
                    {9, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 9},
                    {9, 0, 0, 0, 14, 10, 10, 16, 10, 10, 10, 10, 10, 10, 10, 18, 0, 0, 0, 9}
            };
            int[] pace = {2 * 28, 2 * 32, 2 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 18 * 28, 11 * 32, 0, 2 * 32};
            items.addElement(new Sentry(5 * 28, 2 * 32, room, pace, program, true));
        }
        {// Room 14 Red Lock lower 
            Room room = rooms.elementAt(14);
            room.RoomArray = new int[][]{
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 9, 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 18},
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 12, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
                    {12, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11}
            };

        }
        {// Room 15 Red Lock map upper (lower) 
            Room room = rooms.elementAt(15);
            room.RoomArray = new int[][]{
                    {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
        }
        {// Room 16 Red Lock map lower (upper) 
            Room room = rooms.elementAt(16);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1}
            };

        }
        {// Room 17 Orange Lock (pac-man) 
            Room room = rooms.elementAt(17);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 20, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            items.addElement(new Pellet(2 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(4 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(6 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(8 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(10 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(12 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(14 * 28 - 14, 2 * 32 - 14, room));
            items.addElement(new Pellet(2 * 28 - 14, 4 * 32 - 14, room));
            items.addElement(new Pellet(6 * 28 - 14, 4 * 32 - 14, room));
            items.addElement(new Pellet(10 * 28 - 14, 4 * 32 - 14, room));
            items.addElement(new Pellet(14 * 28 - 14, 4 * 32 - 14, room));
            items.addElement(new Pellet(2 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(4 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(6 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(8 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(10 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(12 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(14 * 28 - 14, 6 * 32 - 14, room));
            items.addElement(new Pellet(2 * 28 - 14, 8 * 32 - 14, room));
            items.addElement(new Pellet(6 * 28 - 14, 8 * 32 - 14, room));
            items.addElement(new Pellet(10 * 28 - 14, 8 * 32 - 14, room));
            items.addElement(new Pellet(14 * 28 - 14, 8 * 32 - 14, room));
            items.addElement(new Pellet(2 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(4 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(6 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(8 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(10 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(12 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Pellet(14 * 28 - 14, 10 * 32 - 14, room));
            items.addElement(new Ghost(42, 48, room));
            items.addElement(new DirectionalSensor(16 * 28 + 8, 4 * 32 + 16, room,
                    new Ghost(0, 0, null)));
        }
        {// Room 18 Yellow Lock (Hot tunnel) 
            Room room = rooms.elementAt(18);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            items.addElement(new Wave(room));

            int[] pace = {2 * 28, 2 * 32, 2 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 18 * 28, 11 * 32, 0, 5 * 32};
            items.addElement(new Sentry(5 * 28, 2 * 32, room, pace, program, true));
        }
        {// Room 19 Green Lock (Turbines) 
            Room room = rooms.elementAt(19);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 22, 1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            int[] cells1 = {12, 3, 0, 1, 11, 3, 0, 1, 10, 3, 0, 1, 9, 3, 0, 1, 8, 3, 0, 1, 7, 3, 0, 1};
            items.addElement(new Turbine(28, 9 * 32, room, cells1));
            int[] cells2 = {12, 5, 0, 1, 11, 5, 0, 1, 10, 5, 0, 1, 9, 5, 0, 1, 8, 5, 0, 1, 7, 5, 0, 1};
            items.addElement(new Turbine(5 * 28, 9 * 32, room, cells2));
            int[] pace = {6 * 28, 2 * 32, 13 * 28, 2 * 32};
            int[] program = {28, 32, 15 * 28, 10 * 32, 19 * 28, 5 * 32};
            items.addElement(new Sentry(6 * 28, 2 * 32, room, pace, program, true));
        }
        {// Room 20 Blue Lock (Flip Maze) 
            Room room = rooms.elementAt(20);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 1},
                    {0, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 1},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
                    {0, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 1},
                    {1, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 1},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 26, 23},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

            int[] pace = {2 * 28, 2 * 32, 2 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 18 * 28, 11 * 32, 0, 5 * 32};
            items.addElement(new Sentry(5 * 28, 2 * 32, room, pace, program, true));
        }
        {// Room 21 Purple Lock (4 Pull Locks) 
            Room room = rooms.elementAt(21);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 24, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            items.addElement(new WallHandle(11 * 28, 3 * 32 + 8, room));
            items.addElement(new WallHandle(11 * 28, 5 * 32 + 8, room));
            items.addElement(new WallHandle(11 * 28, 7 * 32 + 8, room));
            items.addElement(new WallHandle(11 * 28, 9 * 32 + 8, room));
            int[] pace = {2 * 28, 2 * 32, 2 * 28, 9 * 32};
            int[] program = {4 * 28, 0, 18 * 28, 11 * 32, 0, 5 * 32};
            items.addElement(new Sentry(5 * 28, 2 * 32, room, pace, program, true));
        }

        {// Room 22 Final Portal! 
            Room room = rooms.elementAt(22);
            room.RoomArray = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            room.AddTextBox("You did it! You beat the Secret 6th level!", 2 * 28, 2 * 32, 500);
            room.AddTextBox("Believe it or not, you have now accomplished something that Tom hasn't even done yet.!",
                    2 * 28, 5 * 32, 500);
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
        }

        for (int a = 1; a <= 5; a++) {
            Room roomA = rooms.elementAt(a);
            Room room1 = rooms.elementAt(1);
            roomA.upRoom = room1;
            roomA.downRoom = room1;
            roomA.leftRoom = room1;
            roomA.rightRoom = room1;
        }

        rooms.elementAt(1).leftRoom = rooms.elementAt(2);
        rooms.elementAt(2).upRoom = rooms.elementAt(3);
        rooms.elementAt(3).rightRoom = rooms.elementAt(4);
        rooms.elementAt(4).leftRoom = rooms.elementAt(5);
        rooms.elementAt(5).downRoom = rooms.elementAt(6);
        rooms.elementAt(6).upRoom = rooms.elementAt(1);

        LinkRoomsLeftRight(6, 7);
        LinkRoomsLeftRight(7, 8);
        LinkRoomsLeftRight(8, 22);

        int[] list1 = {10, 9, 7, 11, 12};
        LinkRoomsVertically(list1);

        LinkRoomsUpDown(16, 15);
        LinkRoomsUpDown(13, 14);
        LinkRoomsLeftRight(15, 10);
        LinkRoomsLeftRight(10, 13);
        LinkRoomsLeftRight(17, 9);
        LinkRoomsLeftRight(9, 18);
        LinkRoomsLeftRight(19, 11);
        LinkRoomsLeftRight(11, 20);
        LinkRoomsLeftRight(12, 21);

        gameCursor = new GameCursor(9 * 28 + 14, 5 * 32 + 16, rooms.elementAt(1));
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