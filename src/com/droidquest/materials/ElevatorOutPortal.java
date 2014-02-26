package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class ElevatorOutPortal extends Material 
{
transient static public Room outRoom;

public ElevatorOutPortal() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item) 
  {
	if (item == getPlayer())
	  {
	     if (outRoom == null)
	       outRoom = (Room) level.rooms.elementAt(9);
	     if (outRoom == (Room) level.rooms.elementAt(9))
	       {
		  item.x = 15*28;
		  item.y = 5*32;
		  item.SetRoom(outRoom);
	       }
	     else
	       {
		  item.x = 13*28;
		  item.y = 5*32;
		  item.SetRoom(outRoom);
	       }
	       
	  }
  }

static public void SetOutRoom(int roomNum) 
  {
	outRoom = (Room) level.rooms.elementAt(roomNum);
  }

}