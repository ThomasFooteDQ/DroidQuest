package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.items.Item;

public class MineField extends Material 
{
int hit=0;
Item target = null;

public MineField() 
  {
	super(Color.black, false, false);
  }

public boolean Passable(Item item) 
  {
	hit += 2;
	target = item;
	return false;
  }

public void Animate() 
  {
	if (hit>0)
	  hit--;
	if (hit>=2)
	  {
	     target.room = (Room) level.rooms.elementAt(58);
	     target.charge=0;
	     if (target.InternalRoom != null)
	       {
		  Room room = target.InternalRoom;
		  if (room.wires.size()>0)
		    for(int a=0; a<room.wires.size(); a++)
		      ((Wire) room.wires.elementAt(0)).Remove();
	       }
	  }
  }

}
