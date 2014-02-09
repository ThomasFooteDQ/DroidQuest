package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class ElevatorInPortal extends Material 
{
public ElevatorInPortal() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item)
  {
	if (item == level.player)
	  {
	     Room elevatorRoom = (Room) level.rooms.elementAt(35);
	     item.x = 28;
	     item.y = 10*32;
	     item.SetRoom(elevatorRoom);
	  }
  }

}