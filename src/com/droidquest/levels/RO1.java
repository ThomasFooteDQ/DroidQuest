package com.droidquest.levels;

import java.awt.Color;

import com.droidquest.Game;
import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.devices.ANDGate;
import com.droidquest.devices.ContactSensor;
import com.droidquest.devices.DirectionalSensor;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.GenericChip;
import com.droidquest.devices.NOTGate;
import com.droidquest.devices.Node;
import com.droidquest.devices.RoomSensor;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.AmpireBot;
import com.droidquest.items.BlackCrystal;
import com.droidquest.items.BlueRobot;
import com.droidquest.items.Crystal;
import com.droidquest.items.Initializer;
import com.droidquest.items.Item;
import com.droidquest.items.Key;
import com.droidquest.items.Magnet;
import com.droidquest.items.OrangeRobot;
import com.droidquest.items.Sentry;
import com.droidquest.items.Sentry3;
import com.droidquest.items.Suitcase;
import com.droidquest.items.Sweeper;
import com.droidquest.items.Token;
import com.droidquest.items.WhiteRobot;
import com.droidquest.materials.BlueWall;
import com.droidquest.materials.Lock;
import com.droidquest.materials.LockS1;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

class RO1 extends Level 
{
public RO1(Game game)
  {
    super(game);

	materials.addElement(new Material(true, false)); 	                // 0= Blank
	materials.addElement(new Material(new Color(190,190,255),false, true)); // 1= Light Blue Wall
	int[][] lockProgram1 = {
	     {Lock.NARROW},
	     {4,9,0, 4,6,1},
	     {4,8,0, 4,5,1},
	     {Lock.NARROW},
	     {4,8,1, 4,5,0},
	     {4,9,1, 4,6,0},
	};
	materials.addElement(new Lock(Color.blue, Color.blue, lockProgram1));   // 2= Lock 1
	materials.addElement(new Material(new Color(0,0,128),false,true)); 	// 3= Dark Blue Wall
	materials.addElement(new BlueWall()); 		                        // 4= Blue Wall
	int[][] lockProgram2 = {
	     {Lock.NARROW},
	     { 5,1,0,  5,2,0,  5,3,0,  6,1,4,  6,2,4,  6,3,4},
	     { 6,1,0,  6,2,0,  6,3,0,  7,1,4,  7,2,4,  7,3,4},
	     { 7,1,0,  7,2,0,  7,3,0,  8,1,4,  8,2,4,  8,3,4},
	     { 8,1,0,  8,2,0,  8,3,0,  9,1,4,  9,2,4,  9,3,4},
	     { 9,1,0,  9,2,0,  9,3,0, 10,1,4, 10,2,4, 10,3,4},
	     {10,1,0, 10,2,0, 10,3,0, 11,1,4, 11,2,4, 11,3,4},
	     {11,1,0, 11,2,0, 11,3,0, 12,1,4, 12,2,4, 12,3,4},
	     {12,1,0, 12,2,0, 12,3,0, 13,1,4, 13,2,4, 13,3,4},
	     {13,1,0, 13,2,0, 13,3,0, 14,1,4, 14,2,4, 14,3,4},
	     {14,1,0, 14,2,0, 14,3,0, 15,1,4, 15,2,4, 15,3,4},
	     {15,1,0, 15,2,0, 15,3,0, 16,1,4, 16,2,4, 16,3,4},
	     {16,1,0, 16,2,0, 16,3,0, 17,1,4, 17,2,4, 17,3,4},
	     {17,1,0, 17,2,0, 17,3,0, 18,1,4, 18,2,4, 18,3,4},
	     {Lock.NARROW},
	     {17,1,4, 17,2,4, 17,3,4, 18,1,0, 18,2,0, 18,3,0},
	     {16,1,4, 16,2,4, 16,3,4, 17,1,0, 17,2,0, 17,3,0},
	     {15,1,4, 15,2,4, 15,3,4, 16,1,0, 16,2,0, 16,3,0},
	     {14,1,4, 14,2,4, 14,3,4, 15,1,0, 15,2,0, 15,3,0},
	     {13,1,4, 13,2,4, 13,3,4, 14,1,0, 14,2,0, 14,3,0},
	     {12,1,4, 12,2,4, 12,3,4, 13,1,0, 13,2,0, 13,3,0},
	     {11,1,4, 11,2,4, 11,3,4, 12,1,0, 12,2,0, 12,3,0},
	     {10,1,4, 10,2,4, 10,3,4, 11,1,0, 11,2,0, 11,3,0},
	     { 9,1,4,  9,2,4,  9,3,4, 10,1,0, 10,2,0, 10,3,0},
	     { 8,1,4,  8,2,4,  8,3,4,  9,1,0,  9,2,0,  9,3,0},
	     { 7,1,4,  7,2,4,  7,3,4,  8,1,0,  8,2,0,  8,3,0},
	     { 6,1,4,  6,2,4,  6,3,4,  7,1,0,  7,2,0,  7,3,0},
	     { 5,1,4,  5,2,4,  5,3,4,  6,1,0,  6,2,0,  6,3,0}
	};
	materials.addElement(new Lock(Color.blue, Color.blue, lockProgram2));   // 5= Lock2
	materials.addElement(new Material(new Color(0,255,0),false, true)); 	// 6= Green Wall
	materials.addElement(new Portal("RO2.lvl", true, true));	        // 7= Portal to Level 2
	materials.addElement(new LockS1()); //8=Secret Lock

	for (int a=0; a<32; a++)
	  rooms.addElement(new Room());

	  { // Room  0, Entry point 
	     Room room = (Room) rooms.elementAt(0);
	     room.SetMaterialOutline(0,0,19,11,1);
	     room.SetMaterialFill(19,8,19,10,0);
	     room.AddTextBox("Welcome Traveller!",150,64, 400);
	     room.AddTextBox("You have fallen into the under- ground city of Robotropolis. To escape, travel upward through all five levels of the city."
			     ,60,100,400);
	     room.AddTextBox("The three robots in the next room will help you on your journey. To learn how they work, choose Robot Anatomy from the menu."
			     ,60,200,400);
	     room.AddTextBox("This way to Robotropolis",360,290,200);
	     room.AddArrow(559, 11*28+16, Arrow.DIR_RIGHT, 100, Color.white);
	  }
	  { // Room  1, Robots Here 
	     Room room = (Room) rooms.elementAt(1);
	     int[][] table1 =  { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
		  {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
		  {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	     };
	     room.RoomArray = table1;
	     items.addElement(new BlueRobot(6*28, 5*32, room));
	       {
		  Item robot = (Item) items.lastElement();
		  BlueRobot brobot = (BlueRobot) robot;
		  brobot.thrusterPower=true;
		  RoomSensor rSensor = new RoomSensor(8*28,4*32+16,brobot.InternalRoom, 
						      new Key(0,0,null,Color.white));
		  items.addElement(rSensor);
		  rSensor.rotate(1); rSensor.rotate(1); 
		  NOTGate ng = new NOTGate(5*28,3*32,brobot.InternalRoom);
		  items.addElement(ng);
		  FlipFlop ff = new FlipFlop(9*28,6*32,brobot.InternalRoom);
		  items.addElement(ff);
		  Wire dummy;
		  dummy = new Wire(rSensor.ports[0], ng.ports[0]);
		  dummy = new Wire(ng.ports[1], brobot.devices[9].ports[0]);
		  dummy = new Wire(ff.ports[0], brobot.devices[5].ports[0]);
		  dummy = new Wire(ff.ports[1], brobot.devices[7].ports[0]);
		  dummy = new Wire(ff.ports[2], brobot.devices[1].ports[0]);
		  dummy = new Wire(ff.ports[3], brobot.devices[3].ports[0]);
	       }
	     
	     items.addElement(new WhiteRobot(8*28, 3*32, room));
	       {
		  Item robot = (Item) items.lastElement();
		  WhiteRobot wrobot = (WhiteRobot) robot;
		  wrobot.thrusterPower=true;
		  
		  RoomSensor rSensor = new RoomSensor(4*28,4*32, wrobot.InternalRoom,
						      new Crystal(0,0,null,0));
		  items.addElement(rSensor);
		  rSensor.rotate(1); rSensor.rotate(1);
		  NOTGate ng = new NOTGate(5*28, 7*32, wrobot.InternalRoom);
		  items.addElement(ng);
		  ng.rotate(1);ng.rotate(1);ng.rotate(1);
		  FlipFlop ff = new FlipFlop(8*28,6*32,wrobot.InternalRoom);
		  items.addElement(ff);
		  ANDGate ag1 = new ANDGate(9*28,2*32,wrobot.InternalRoom);
		  items.addElement(ag1);
		  ANDGate ag2 = new ANDGate(12*28,8*32,wrobot.InternalRoom);
		  items.addElement(ag2);
		  ag2.rotate(1);ag2.rotate(1);
		  Node node = new Node(15*28, 7*32, wrobot.InternalRoom, Node.TYPE_THREE);
		  items.addElement(node);
		  node.rotate(1); node.rotate(1);
		  
		  Wire dummy;
		  dummy = new Wire(rSensor.ports[0], wrobot.devices[8].ports[0]);
		  dummy = new Wire(node.ports[0], wrobot.devices[5].ports[0]);
		  dummy = new Wire(node.ports[1], ag2.ports[0]);
		  dummy = new Wire(node.ports[2], ng.ports[0]);
		  dummy = new Wire(node.ports[3], ag1.ports[1]);
		  dummy = new Wire(ag1.ports[2], wrobot.devices[0].ports[0]);
		  dummy = new Wire(ag2.ports[2], wrobot.devices[2].ports[0]);
		  dummy = new Wire(ng.ports[1], wrobot.devices[3].ports[0]);
		  dummy = new Wire(ff.ports[0], wrobot.devices[4].ports[0]);
		  dummy = new Wire(ff.ports[1], wrobot.devices[6].ports[0]);
		  dummy = new Wire(ff.ports[2], ag1.ports[0]);
		  dummy = new Wire(ff.ports[3], ag2.ports[1]);
	       }
	     
	     items.addElement(new OrangeRobot(14*28, 9*32, room));
	       {
		  Item robot = (Item) items.lastElement();
		  OrangeRobot orobot = (OrangeRobot) robot;
		  orobot.thrusterPower=true;
		  
		  RoomSensor rSensor = new RoomSensor(8*28,4*32+16,orobot.InternalRoom, 
						      new Key(0,0,null, Color.white));
		  items.addElement(rSensor);
		  rSensor.rotate(1); rSensor.rotate(1); 
		  NOTGate ng = new NOTGate(5*28,3*32,orobot.InternalRoom);
		  items.addElement(ng);
		  SmallChip sc = new SmallChip(10*28,6*32,orobot.InternalRoom,"2");
		  items.addElement(sc);
		  sc.LoadChip("chips/WallHugger.chip");
		  Wire dummy;
		  dummy = new Wire(rSensor.ports[0], ng.ports[0]);
		  dummy = new Wire(ng.ports[1], orobot.devices[9].ports[0]); // Antenna
		  dummy = new Wire(sc.ports[0], orobot.devices[0].ports[0]); // Top Thruster
		  dummy = new Wire(sc.ports[1], orobot.devices[7].ports[0]); // Left Bumper
		  dummy = new Wire(sc.ports[2], orobot.devices[3].ports[0]); // Left Thruster
		  dummy = new Wire(sc.ports[3], orobot.devices[6].ports[0]); // Bottom Bumper
		  dummy = new Wire(sc.ports[4], orobot.devices[2].ports[0]); // Bottom Thruster
		  dummy = new Wire(sc.ports[5], orobot.devices[5].ports[0]); // Right Bumper
		  dummy = new Wire(sc.ports[6], orobot.devices[1].ports[0]); // Right Thruster
		  dummy = new Wire(sc.ports[7], orobot.devices[4].ports[0]); // Top Bumper
	       }
	     items.addElement(new Key(8*28, 8*32, room, Color.blue));
	  }
	  { // Room  2, Sewer Door 
	     Room room = (Room) rooms.elementAt(2);
	     int[][] table2 =  { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0},
		  {1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0},
		  {0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1},
		  {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {0,0,0,2,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1}
	     };
	     room.RoomArray = table2;
	     room.AddTextBox("{BIG} The City Sewer",40,80,500);
	     items.addElement(new Sweeper(476, 224, room));
	  }
	  { // Room  3, Maze 1 "PR" 
	     Room room = (Room) rooms.elementAt(3);
	     int[][] table3= { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
		  {1,0,0,1,0,0,1,0,0,1,1,1,1,0,0,0,0,0,0,0},
		  {1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0},
		  {1,0,0,1,1,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0},
		  {1,0,0,1,0,0,0,0,0,1,1,1,1,0,0,1,1,1,1,1},
		  {1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,1},
		  {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1}
	     };	
	     room.RoomArray = table3;
	     String[] i1 = {"0073.jpg","0074.jpg"};
	     room.graphix.addElement(new Graphix(i1, 4*28+14,5*32));
	  }
	  { // Room  4, Maze 2 "CG" 
	     Room room = (Room) rooms.elementAt(4);
	     int[][] table4= { 
		  {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1},
		  {1,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
		  {1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
		  {1,0,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,0},
		  {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}
	     };	
	     room.RoomArray = table4;
	     String[] i6 = {"0075.jpg","0076.jpg","0077.jpg", "0076.jpg"};
	     room.graphix.addElement(new Graphix(i6, 13*28, 4*32,
						 Graphix.BOUNCE, 0,2, 3*16));
	  }
	  { // Room  5, Maze 3 "PC" 
	     Room room = (Room) rooms.elementAt(5);
	     int[][] table5= { 
		  {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,1,1,1,1,0,0,1,0,0,1,1,1,0,0,0,0},
		  {0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0},
		  {0,0,0,0,1,1,1,1,0,0,1,0,0,1,1,1,1,1,1,1},
		  {0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		  {1,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1}
	     };	
	     room.RoomArray = table5;
	  }
	  { // Room  6, Maze 4 "RC" 
	     Room room = (Room) rooms.elementAt(6);
	     int[][] table6= { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1,1},
		  {1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
		  {1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0},
		  {1,0,0,1,1,1,0,0,0,0,1,0,0,0,0,0,1,1,1,1},
		  {1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1},
		  {0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,0,0,0,1},
		  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1}
	     };	
	     room.RoomArray = table6;
	     String[] i7 = {"0082.jpg","0083.jpg","0084.jpg", "0085.jpg", "0084.jpg", "0083.jpg", "0082.jpg"};
	     room.graphix.addElement(new Graphix(i7, 12*28, 4*32,
						 Graphix.BOUNCE, 0,2, 3*16));
	  }
	  { // Room  7, Maze 5 "MW" 
	     Room room = (Room) rooms.elementAt(7);
	     int[][] table7= { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,0,0,1},
		  {0,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1},
		  {1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1},
		  {1,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,0,1},
		  {1,0,0,1,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1},
		  {1,0,0,1,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1}
	     };	
	     room.RoomArray = table7;
	     String[] i2 = {"0086.jpg","0087.jpg"};
	     room.graphix.addElement(new Graphix(i2, 5*28,2*32));
	  }
	  { // Room  8, Maze 6 "CIG" 
	     Room room = (Room) rooms.elementAt(8);
	     int[][] table8= { 
		  {1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
		  {1,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,1,1},
		  {0,0,0,1,0,0,1,1,1,0,0,0,1,0,0,1,0,0,0,0},
		  {0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,0,0},
		  {1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1,0,0},
		  {1,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
		  {1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {1,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
		  {1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
		  {1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1}
	     };	
	     room.RoomArray = table8;
	  }
	  { // Room  9, Maze 7 "NH" 
	     Room room = (Room) rooms.elementAt(9);
	     int[][] table9= { 
		  {1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1},
		  {1,0,0,1,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,1,0,1,0,0,1,0,0,1,1,1,1,1,0,0,0,1},
		  {1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,1,0,0,0,1,1,0,0,1,0,0,0,1,0,0,0,1},
		  {1,0,0,1,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,1},
		  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1}
	     };	
	     room.RoomArray = table9;
	  }
	  { // Room 10, Maze 8 "SG" 
	     Room room = (Room) rooms.elementAt(10);
	     int[][] table10= { 
		  {1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1},
		  {0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		  {0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		  {0,0,0,1,1,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0},
		  {0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,0,0,0},
		  {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1}
	     };	
	     room.RoomArray = table10;
	     String[] i8 = {"0078.jpg","0079.jpg","0080.jpg", "0081.jpg"};
	     room.graphix.addElement(new Graphix(i8, 13*28, 10*32,
						 Graphix.BOUNCE, 2,0, 5*14));
	  }
	  { // Room 11, Maze Bottom 
	     Room room = (Room) rooms.elementAt(11);
	     int[][] table11= { 
		  {3,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };	
	     room.RoomArray = table11;
	     items.addElement(new ContactSensor(9*28,2*32,room, new Token(0,0,null)));
	  }
	  { // Room 12, Top Corridor 1 
	     Room room = (Room) rooms.elementAt(12);
	     int[][] table12 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table12;
	  }
	  { // Room 13, Top Corridor 2 
	     Room room = (Room) rooms.elementAt(13);
	     int[][] table13 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table13;
	  }
	  { // Room 14, Top Corridor Branch 
	     Room room = (Room) rooms.elementAt(14);
	     int[][] table14 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3}
	     };
	     room.RoomArray = table14;
	  }
	  { // Room 15, Hallway Top Right 
	     Room room = (Room) rooms.elementAt(15);
	     int[][] table15 =  { 
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3}
	     };
	     room.RoomArray = table15;
	  }
	  { // Room 16, Hallway Bottom Right 
	     Room room = (Room) rooms.elementAt(16);
	     int[][] table16 =  { 
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table16;
	  }
	  { // Room 17, Hallway Bottom 
	     Room room = (Room) rooms.elementAt(17);
	     int[][] table17 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,0,0,0,0,0,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3}
	     };
	     room.RoomArray = table17;
	     items.addElement(new AmpireBot(10*28,3*32, room));
	  }
	  { // Room 18, Hallway Bottom Left 
	     Room room = (Room) rooms.elementAt(18);
	     int[][] table18 =  { 
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table18;
	  }
	  { // Room 19, Hallway Top Left 
	     Room room = (Room) rooms.elementAt(19);
	     int[][] table19 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3}
	     };
	     room.RoomArray = table19;
	  }
	  { // Room 20, Hallway Top 
	     Room room = (Room) rooms.elementAt(20);
	     int[][] table20 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table20;
	  }
	  { // Room 21, WallHugger Puzzle 
	     Room room = (Room) rooms.elementAt(21);
	     int[][] table21 =  { 
		  {3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3},
		  {3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table21;
	     items.addElement(new Magnet(10*28,9*32, room));
	     int[] pace = {3*28, 120, 16*28, 120};
	     int[] protect = {0,3*32,19*28,11*32, 10*28,0};
	     items.addElement(new Sentry(3*28,120, room, pace, protect, false));
	  }
	  { // Room 22, Bouncer Puzzle 
	     Room room = (Room) rooms.elementAt(22);
	     int[][] table22 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table22;
	     items.addElement(new Crystal(16*28,5*32, room,100000));
	     int[] pace = {3*28,94, 3*28,302};
	     int[] protect = {4*28,0,19*28,11*32, 0,5*32};
	     items.addElement(new Sentry(3*28,94, room, pace, protect, false));
	  }
	  { // Room 23, AnteChamber 
	     Room room = (Room) rooms.elementAt(23);
	     int[][] table23 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,3,3,3,3,3,0,0,0,0,3,3,3},
		  {0,0,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,3,3},
		  {0,0,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,0,0},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,0,0},
		  {3,3,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,0,0},
		  {3,3,3,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,3,3},
		  {3,3,3,3,3,0,0,0,3,3,3,3,3,0,0,0,0,0,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,3,3}
	     };
	     room.RoomArray = table23;
	     room.AddTextBox("Do you have EVERYTHING?",
			     98,278,168);
	  }
	  { // Room 24, Directional Token Sensor here 
	     Room room = (Room) rooms.elementAt(24);
	     int[][] table24 =  { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,3,0,0,0,0,3,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3},
		  {3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };
	     room.RoomArray = table24;
	     room.AddTextBox("Time to board a 'Bot!",
			     170,320,500);
	     items.addElement(new DirectionalSensor(3*28+14,4*32,room,new Token(0,0,null)));
	  }
	  { // Room 25, Sewer Grate Top 
	     Room room = (Room) rooms.elementAt(25);
	     int[][] table25 =  { 
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
		  {4,0,0,0,4,0,0,0,0,0,4,0,0,4,0,0,4,0,0,4},
		  {4,0,0,0,4,0,0,0,0,0,4,0,0,4,0,0,4,0,0,4},
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,0},
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,0},
		  {0,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,0},
		  {0,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4},
		  {0,0,0,0,4,0,0,4,0,0,0,0,0,4,0,0,4,0,0,4},
		  {0,0,0,0,4,0,0,4,0,0,0,0,0,4,0,0,4,0,0,4},
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,0,0,0,4},
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,0,0,0,4},
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4}
	     };
	     room.RoomArray = table25;
	     room.AddTextBox("SEWER GRATE",
			     182,48,90);
	     items.addElement(new Sentry3(2*28,64, room));
	  }
	  { // Room 26, Sewer Grate Bottom 
	     Room room = (Room) rooms.elementAt(26);
	     int[][] table26 =  { 
		  {4,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4},
		  {4,0,0,0,0,0,0,4,0,0,4,0,0,4,0,0,4,0,0,4},
		  {4,0,0,0,0,0,0,4,4,4,4,0,0,0,0,0,4,4,4,4},
		  {4,4,4,4,4,0,0,4,0,0,4,0,0,0,0,0,4,0,0,4},
		  {4,0,0,0,4,0,0,4,0,0,4,4,4,4,4,4,4,0,0,4},
		  {4,0,0,0,4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,4,4,4,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,4,4,4,0,0,4,4,0,0,4},
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
	     };
	     room.RoomArray = table26;
	     String[] i3 = {"0064.jpg","0065.jpg"};
	     room.graphix.addElement(new Graphix(i3, 5*28,10*32));
	     String[] i4 = {"0066.jpg","0067.jpg"};
	     room.graphix.addElement(new Graphix(i4, 11*28,8*32));
	     String[] i5 = {"0068.jpg","0069.jpg"};
	     room.graphix.addElement(new Graphix(i5, 16*28,9*32));
	     room.AddTextBox("These poor creatures never made it out...Will you???",
			     84,224,500);
	  }
	  { // Room 27, 2nd Lock 
	     Room room = (Room) rooms.elementAt(27);
	     int[][] table27 =  { 
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
		  {4,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {0,0,0,0,0,4,4,4,4,4,4,4,4,4,4,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0},
		  {4,4,4,4,4,4,0,0,0,0,0,0,0,5,4,0,0,0,0,0},
		  {4,0,0,0,0,4,4,4,4,4,4,0,0,4,4,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,4,4,4,4,4,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
	     };
	     room.RoomArray = table27;
	     room.AddTextBox("Congratulations!",
			     2*28,304,500);
	     room.AddTextBox("That was a great job!",
			     2*28,320,500);
	  }
	  { // Room 28, Portal Chamber 
	     Room room = (Room) rooms.elementAt(28);
	     int[][] table28 =  { 
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
		  {4,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,7,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
		  {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4}
	     };
	     room.RoomArray = table28;
	     room.AddTextBox("Prepare for an experience that will transport you to another dimension",
			     336,48,224);
	     room.AddTextBox("You CAN take it with you (If you hold on tight)....",
			     336,256,224);
	  }
	  { // Room 29, Help Screen 
	     Room room = (Room) rooms.elementAt(29);
	     int[][] table29 =  { 
		  {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6},
		  {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6}
	     };	
	     room.RoomArray = table29;
	     room.AddTextBox("Escape from the City Sewer!",
			     3*28,2*32,500);
	     room.AddTextBox("HINTS:",
			     2*28,3*32,500);
	     room.AddTextBox("Observe how the robots move. Each is pre-wired to help you.",
			     2*28,4*32,500);
	     room.AddTextBox("Robots go where humans dare not tread.",
			     2*28,6*32,500);
	     room.AddTextBox("The key to success lies within.",
			     2*28,7*32,500);
	     room.AddTextBox("Black crystals foil Ampire Bots.",
			     2*28,8*32,500);
	     room.AddTextBox("Chip 1 is \"COUNT-TO-N\" Chip 2 is \"WALLHUGGER\"",
			     2*28,9*32,400);
	     room.AddTextBox("(To continue, press RETURN.)",
			     3*28,11*32,500);
	  }

	  { // Room 30, Secret Trash-flow 
	     Room room = (Room) rooms.elementAt(30);
	     int[][] table= { 
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3}
	     };	
	     room.RoomArray = table;
	  }
	  { // Room 31, Secret room 
	     Room room = (Room) rooms.elementAt(31);
	     int[][] table= { 
		  {3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
		  {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3}
	     };	
	     room.RoomArray = table;
	     room.AddTextBox("Hi! I'm the Secret Helper. Congratulations on finding Secret #1 (and #2... wink wink!)",
			     3*28,2*32,450);
	     room.AddTextBox("Look at this... Somebody threw out a perfectly good suitcase. I wonder what's inside it?",
			     3*28,9*32,450);
	     String[] helperlist = {
		"helper0.gif","helper0.gif","helper0.gif","helper0.gif",
		  "helper0.gif","helper0.gif","helper0.gif","helper0.gif",
		  "helper7.gif","helper6.gif","helper7.gif","helper6.gif",
		  "helper7.gif","helper6.gif","helper7.gif","helper6.gif",
		  "helper7.gif","helper6.gif","helper7.gif","helper6.gif",
		  "helper7.gif","helper6.gif","helper7.gif","helper6.gif",
		  "helper0.gif","helper0.gif","helper0.gif","helper0.gif",
		  "helper1.gif","helper4.gif","helper2.gif","helper3.gif",
		  "helper0.gif","helper0.gif","helper0.gif","helper0.gif",
		  "helper5.gif","helper5.gif","helper5.gif","helper5.gif",
	     };
	     Graphix helper = new Graphix(helperlist, 4*28, 5*32);
	     room.graphix.addElement(helper);
	     Item sc = new Suitcase(14*28,5*32,room);
	     items.addElement(sc);
	     items.addElement(new Key(2*28,5*32,sc.InternalRoom,Color.green));
	  }

	
	int[] roomlist1 = {0,1,2,12,13,14,23,25,27,28};
	LinkRoomsHorizontally(roomlist1);
	
	int[] roomlist2 = {2,3,4,5,6,7,8,9,10,11};
	LinkRoomsVertically(roomlist2);
	
	int[] roomlist3 = {3,5,7,9};
	LinkRoomsHorizontally(roomlist3);
	
	int[] roomlist4 = {4,6,8,10,18,17,16,22};
	LinkRoomsHorizontally(roomlist4);
	
	LinkRoomsUpDown(14,15);
	LinkRoomsUpDown(15,16);
	
	LinkRoomsUpDown(19,18);
	
	LinkRoomsLeftRight(19,20);
	LinkRoomsLeftRight(20,15);
	
	LinkRoomsUpDown(17,21);
	
	LinkRoomsUpDown(23,24);
	
	LinkRoomsUpDown(25,26);
	
	LinkRoomsUpDown(30,31);
	
	
	for (int a=0; a<14; a++)
	  {
	     int speed = (random.nextInt(5)+1)*2;
	     Graphix grx = new Graphix("junk"+a+".jpg", 
				       19*28,
				       random.nextInt(3*32)+4*32,
				       Graphix.CYCLE,
				       -speed, 0, 20*32/speed
				       );
	     int t = random.nextInt(20*32/speed);
	     grx.x-=speed*t;
	     grx.count=t;
	     ((Room) rooms.elementAt(11)).graphix.addElement(grx);
	  }
	
	gameCursor = new GameCursor(252,288,(Room) rooms.elementAt(0));
	solderingPen = new SolderingPen();
	remote = new Remote();
	helpCam = new HelpCam( (Room) rooms.elementAt(29));
	
	items.addElement(gameCursor);
	
	SmallChip sc = new SmallChip(0,0,null,"1");
	sc.LoadChip("chips/CountToN.chip");
	items.addElement(sc);
	items.addElement(new RO1Init());
	
	items.addElement(solderingPen);
	items.addElement(remote);
	items.addElement(helpCam);
	setPlayer(gameCursor);
	setCurrentViewer(getPlayer());
	items.addElement(new BlackCrystal(2*28,7*32, (Room) rooms.elementAt(3)));
	
  }

}

class RO1Init extends Initializer 
{
public void Init() 
  {
	// The Magnet and the Energy Crystal go into (one or the other)
	// X=10*28, Y=9*32, Room=21
	// X=16*28, Y=5*32, Room-22
	Item magnet = level.FindItem("Magnet");
	Item crystal = level.FindItem(".Crystal");
	if (level.random.nextInt(2)==1)
	  {
	     magnet.x=10*28;
	     magnet.y=9*32;
	     magnet.room=(Room) level.rooms.elementAt(21);
	     crystal.x=16*28;
	     crystal.y=5*32;
	     crystal.room=(Room) level.rooms.elementAt(22);
	  }
	else
	  {
	     crystal.x=10*28;
	     crystal.y=9*32;
	     crystal.room=(Room) level.rooms.elementAt(21);
	     magnet.x=16*28;
	     magnet.y=5*32;
	     magnet.room=(Room) level.rooms.elementAt(22);
	  }
	
	// The Blue Key goes inside one of the Robots.
	Item robot1 = level.FindItem("BlueRobot");
	Item robot2 = level.FindItem("WhiteRobot");
	Item robot3 = level.FindItem("OrangeRobot");
	Item key = level.FindItem("Key");
	switch (level.random.nextInt(3))
	  {
	   case 0: key.room = robot1.InternalRoom; break;
	   case 1: key.room = robot2.InternalRoom; break;
	   case 2: key.room = robot3.InternalRoom; break;
	  }
	
	// The Black Crystal and Chip 1 go somewhere in the Maze
	// Room=3, 2,7  
	// Room=4  4,1  
	// Room=5  1,18 
	// Room=6  6,6  
	// Room=7  4,4  
	// Room=8  16,5 
	// Room=9  4,3  
	// Room=10 6,8  
	
	Item bcrystal = level.FindItem("BlackCrystal");
	switch (level.random.nextInt(8))
	  {
	   case 0: bcrystal.x = 2*28; bcrystal.y = 7*32;
	     bcrystal.room = (Room) level.rooms.elementAt(3); break;
	   case 1: bcrystal.x = 4*28; bcrystal.y = 1*32;
	     bcrystal.room = (Room) level.rooms.elementAt(4); break;
	   case 2: bcrystal.x = 14*28; bcrystal.y = 4*32;
	     bcrystal.room = (Room) level.rooms.elementAt(5); break;
	   case 3: bcrystal.x = 6*28; bcrystal.y = 6*32;
	     bcrystal.room = (Room) level.rooms.elementAt(6); break;
	   case 4: bcrystal.x = 4*28; bcrystal.y = 4*32;
	     bcrystal.room = (Room) level.rooms.elementAt(7); break;
	   case 5: bcrystal.x =16*28; bcrystal.y = 5*32;
	     bcrystal.room = (Room) level.rooms.elementAt(8); break;
	   case 6: bcrystal.x = 4*28; bcrystal.y = 3*32;
	     bcrystal.room = (Room) level.rooms.elementAt(9); break;
	   case 7: bcrystal.x = 6*28; bcrystal.y = 8*32;
	     bcrystal.room = (Room) level.rooms.elementAt(10); break;
	  }
	
	// Room=3  17,10
	// Room=4  5,2
	// Room=5  2,9
	// Room=6  17,9
	// Room=7  8,5
	// Room=8  7,9
	// Room=9  13,7
	// Room=10 5,1
	Item chip=null;
	for (int a=0; a< level.items.size(); a++)
	  {
	     Item item = (Item) level.items.elementAt(a);
	     if (item.getClass().toString().endsWith("SmallChip"))
	       if (((GenericChip)item).label.endsWith("1"))
		 chip = item;
	  }
	if (chip==null)
	  return;
	switch (level.random.nextInt(8))
	  {
	   case 0: chip.x =17*28; chip.y =10*32;
	     chip.room = (Room) level.rooms.elementAt(3); break;
	   case 1: chip.x = 5*28; chip.y = 2*32;
	     chip.room = (Room) level.rooms.elementAt(4); break;
	   case 2: chip.x = 2*28; chip.y = 9*32;
	     chip.room = (Room) level.rooms.elementAt(5); break;
	   case 3: chip.x =17*28; chip.y = 9*32;
	     chip.room = (Room) level.rooms.elementAt(6); break;
	   case 4: chip.x = 8*28; chip.y = 5*32;
	     chip.room = (Room) level.rooms.elementAt(7); break;
	   case 5: chip.x = 7*28; chip.y = 9*32;
	     chip.room = (Room) level.rooms.elementAt(8); break;
	   case 6: chip.x =13*28; chip.y = 7*32;
	     chip.room = (Room) level.rooms.elementAt(9); break;
	   case 7: chip.x = 5*28; chip.y = 1*32;
	     chip.room = (Room) level.rooms.elementAt(10); break;
	  }
  }

}