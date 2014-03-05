package com.droidquest.materials;

import com.droidquest.Room;

public class XitSlot extends Material 
{
public int doorState=0;

public Room room=null;

public XitSlot() 
  {
	super(true, false);
  }

public void Animate()
  {
	switch (doorState)
	  {
	   case 1:
	     room.SetMaterial(11,8,0);
	     room.SetMaterial(14,5,0);
	     room.SetMaterial(17,2,0);
	     room.SetMaterial(12,7,2);
	     room.SetMaterial(15,4,2);
	     doorState=2;
	     break;
	   case 2:
	     room.SetMaterial(12,7,0);
	     room.SetMaterial(15,4,0);
	     room.SetMaterial(13,6,2);
	     room.SetMaterial(16,3,2);
	     doorState=3;
	     break;
	   case 3:
	     room.SetMaterial(13,6,0);
	     room.SetMaterial(16,3,0);
	     room.SetMaterial(11,8,2);
	     room.SetMaterial(14,5,2);
	     room.SetMaterial(17,2,2);
	     doorState=1;
	     break;
	  }
  }

}

