package com.droidquest;

//This is the source code for DroidQuest 2.7. Copyright 2003 by Thomas Foote.

import com.droidquest.levels.MainMenu;
import com.droidquest.operation.swing.SwingOperationFactory;
import com.droidquest.view.swing.DQFrame;
import com.droidquest.view.swing.event.SwingEventHandlerFactory;
import com.droidquest.view.swing.sound.SwingSoundPlayer;

public class DQ
{
	public static void main(String[] args)
	{
        Game game = new GameImpl();
        game.setSoundPlayer(new SwingSoundPlayer(game));
        game.setOperationFactory(new SwingOperationFactory(game));
        game.setCurrentLevel(new MainMenu(game));
        game.getCurrentLevel().Init();

        DQFrame dq = new DQFrame(game);
        SwingEventHandlerFactory.createPlayerEventHandler(game);

        dq.setVisible(true);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//Updating Tutorial levels to 2.0
//
//ROTUT1 : Robot Anatomy
//ROTUT2 : Robot Wiring
//ROTUT3 : Sensors
//ROTUT4 : The Toolkit
//ROTUT5 : Robot Circuits
//ROTUT6 : Robot Teamwork
//ROTUT7 : Chip Design
//

//Text has the following embedded commands:
//"{BIG} "         : Switch to large font
//"{SML} "         : Switch to small font
//"{rrr,ggg,bbb} " : Switch color. rrr, ggg, bbb == 000-255
//"{BSP} "         : BackSpace, good for switching between BIG and SML
//
//Small characters are all 12 pixels wide, and Large charaacters are all 27 pixels wide.
//
//Undo Support
//Undo the folowing actions
//Summon Device
//Destroy Device
//Move Device
//Make Wire
//Delete Wire
//
//class GameAction
//{
//static int TYPE_BLANK = 0;
//static int TYPE_SUMMON_DEVICE = 1;
//static int TYPE_DESTROY_DEVICE = 2;
//static int TYPE_MOVE_DEVICE = 3;
//static int TYPE_MAKE_WIRE = 4;
//static int TYPE_DELETE_WIRE = 5;
//
//int type;
//Device device;
//int x;
//int y;
//Room room;
//Wire wire;
//
//public Action (int t, Device dev)
//{
//  type = t;
//  device = dev;
//  x=dev.x;
//  y=dev.y;
//  room = dev.room;
//}
//
//public Action (int t, Wire w)
//{
//  type = t;
//  wire = w;
//  room = w.fromPort.myDevice.room;
//}
//
//public void Reverse()
//{
//  switch (type)
//  {
//    case 1: // Destroy Device
//            // remove all wires
//            dev.Erase()
//            dev.level.items.removeElement(dev);
//            break;
//    case 2: // Re-summon Device
//            
//            break;
//    case 3: // Move Device
//            
//            break;
//    case 4: // Delete Wire
//            
//            break;
//    case 5: // Remake Wire
//            
//            break;
//  }
//  type=TYPE_BLANK;
//  dev=null;
//  x=0; y=0;
//  room=null;
//  wire=null;
//}
//
//}
//
//Room:MaterialsArray[][] references to materials, instead of indexes
//Initialize the Room Arrays when loading from inventories
//Can't board the subway train while carrying things...!
//
//
//Hot cursor makes input port true, but it doesn't show graphically.
//Add some way to show how much of a charge a Crystal has.
//Add {CENTER}, {LEFT}, & {RIGHT} to TextBoxes 
//Give Rooms an array of Materials that's used instead of the RoomArray matrix.
//Make burners & tester put chips on even pixels
//
//Populate Levels 2-5
//
//Oscillator
//Gates
//Bus
//Clock Chip
//Delay
//One Shot Chip 
//RS
//6 bit Counter
//Full Adder
//Count-to-N
//Monomer
//Wall hugger
//Stereo Recorder
//
//Game ideas:
//1) Classic Robot Odyssey
//2) Return to Robotropolis
//3) Adventure (Classic Atari game)
//4) Adventure Odyssey (Use robots to solve problems in the Adventure world)
//
//
//
//JAR file created with this command:
//% jar cmf0 manifest.txt DQ.jar *.class 
//
//ZIP file created with these files:
//DQ.jar
//DQlogo.gif
//Readme.txt
//
//





