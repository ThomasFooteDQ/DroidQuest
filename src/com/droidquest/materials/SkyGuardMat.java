package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class SkyGuardMat extends Material 
{
public SkyGuardMat() 
  {
	super(true,false);
  }

public void TouchedByItem(Item item) 
  {
	if (item== getPlayer())
	  {
          level.getSoundPlayer().playIfInRoom(getPlayer().room, Level.DISCHARGESOUND);
          getPlayer().x = 2*28;
	     getPlayer().y = 8*32;
	     getPlayer().SetRoom(getPlayer().room.downRoom);
	  }
  }

}