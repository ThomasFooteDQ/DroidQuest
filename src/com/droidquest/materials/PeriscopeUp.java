package com.droidquest.materials;

import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

public class PeriscopeUp extends Material 
{
public PeriscopeUp() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item)
  {
	if (item == getPlayer())
	  if (item.x > 462 && item.y < 80)
	    {
	       GenericRobot gr = (GenericRobot) getPlayer().room.portalItem;
	       level.currentViewer = gr;
	       gr.periscope = true;
	    }
  }

}

