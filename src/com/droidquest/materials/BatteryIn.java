package com.droidquest.materials;

import com.droidquest.items.Crystal;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class BatteryIn extends Material 
{
	// Charges the Battery when an Energy Crystal is passed over it.

	public void BatteryIn() 
	  {
		passable = true;
	  }

	public transient Item robot;

	public void TouchedByItem(Item item)
	  {
		if (item.charge > 0)
		  {
		     // Check to see if it's a pure Crystal, not a Black Crystal
		     if (item instanceof Crystal)
		       {
			  int empty = 100000 - robot.charge;
			  if (empty >= item.charge)
			    {
			       robot.charge += item.charge;
			       item.charge=0;
			    }
			  else
			    {
			       item.charge-=empty;
			       robot.charge=100000;
			    }
                   level.getSoundPlayer().playIfInRoom(robot.InternalRoom, Level.DISCHARGESOUND);
               }
		  }
	  }

	public boolean equals(Material mat) 
	  {
		if (super.equals(mat))
		  if (robot != null)
		    if (robot == ((BatteryIn)mat).robot)
		      return true;
		return false;
	  }

	}