package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.Room;
import com.droidquest.avatars.GameCursor;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

public class AntiPlayer extends Material 
{
	// Sends the Player (in or out of a robot) to the Main Office

	public AntiPlayer() 
	  {
		super(Color.black,true,false);
	  }

	public void TouchedByItem(Item item) 
	  {
		boolean trigger = false;
		if (item == getPlayer())
		  trigger = true;
		else if (item instanceof GenericRobot)
		  {
		     GameCursor gc = (GameCursor) level.gameCursor;
		     if (gc.PlayerInRobot(null) == item)
		       trigger=true;;
		  }
		
		if (trigger)
		  {
		     getPlayer().room = (Room) level.rooms.elementAt(40);
		     getPlayer().x = 10*28;
		     getPlayer().y = 5*32;
		     level.currentViewer = getPlayer();
		  }
	  }

}
