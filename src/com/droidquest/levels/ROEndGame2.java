package com.droidquest.levels;

import java.awt.Color;

import com.droidquest.Game;
import com.droidquest.Room;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.decorations.Arrow;
import com.droidquest.items.EndAnimation;
import com.droidquest.materials.Material;
import com.droidquest.materials.Portal;

class ROEndGame2 extends Level 
{
public ROEndGame2(final Game game)
  {
    super(game);
	materials.addElement(new Material(true, false));                         // 0 = Empty Space
	materials.addElement(new Material(new Color(0,0,255),false, true));      // 1 = Blue
	materials.addElement(new Portal("MainMenu.lvl",true,false));             // 2 = Portal
	
	for (int a=0; a<2; a++)
	  rooms.addElement(new Room());
	
	  {// Room 0  Help  
	     Room room = (Room) rooms.elementAt(0);
	     int[][] table = { 
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	     };
	     room.RoomArray = table;
	     room.AddTextBox("No help here. Press Return.",
			     118, 5*32, 450);
	  }

	  {// Room 1 Portal to Main Menu  
	     Room room = (Room) rooms.elementAt(1);
	     int[][] table = { 
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		  {1,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1},
		  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	     };
	     room.RoomArray = table;
	     
	     room.AddTextBox("{BIG} {255,000,000} CONGRATULATIONS!",64,64, 500);
	     room.AddTextBox("You are one of the rare and elite",82,4*32, 560);
	     room.AddTextBox("Robot Lords.",214,5*32+20, 560);
	     room.AddTextBox("Return to the Main Menu",2*28,10*32+24, 500);
	     room.AddArrow(360,10*32+16,Arrow.DIR_RIGHT,28,Color.white);
	     items.addElement(new EndAnimation(room));
	  }
	
	gameCursor = new GameCursor(10*28,8*32,(Room) rooms.elementAt(1));
	helpCam = new HelpCam( (Room) rooms.elementAt(0));
	solderingPen = new SolderingPen();
	remote = new Remote();
	items.addElement(gameCursor);
	items.addElement(helpCam);
	items.addElement(solderingPen);
	items.addElement(remote);
	setPlayer(gameCursor);
	setCurrentViewer(getPlayer());
	
  }

}
