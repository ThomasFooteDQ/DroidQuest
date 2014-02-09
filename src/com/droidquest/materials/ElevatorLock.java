package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class ElevatorLock extends Material 
{
int doorstate = 0;
// 0=closed
// 1=opening
// 2=opening
// 3=open
// 4=closing
// 5=closing
Room room;

public ElevatorLock() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item)
  {
	if (item.getClass().toString().endsWith("ElevatorKey"))
	  {
	     room = item.room;
	     if (doorstate==0)
	       doorstate=1;
	     else if (doorstate==3)
	       doorstate=4;
	  }
  }

public void Animate() 
  {
	switch(doorstate)
	  {
	   case 0: // Do nothing
	     break;
	   case 1:
	     room.SetMaterial(15,5,0);
	     doorstate++;
	     break;
	   case 2:
	     room.SetMaterial(15,4,0);
	     doorstate++;
	     break;
	   case 3:
	     break;
	   case 4:
	     room.SetMaterial(15,4,7);
	     doorstate++;
	     break;
	   case 5:
	     room.SetMaterial(15,5,7);
	     doorstate=0;
	     break;
	  }
  }

}