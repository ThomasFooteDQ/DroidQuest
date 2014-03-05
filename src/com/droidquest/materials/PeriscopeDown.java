package com.droidquest.materials;

import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

public class PeriscopeDown extends Material 
{
public PeriscopeDown() 
  {
	super(true, false);
  }


public void TouchedByItem(Item item) 
  {
	
	if (item == getPlayer())
	  if (item.x < 462 || item.y > 80)
	  {
	     GenericRobot gr = (GenericRobot) getPlayer().room.portalItem;
	     level.currentViewer = getPlayer();
	     gr.periscope = false;
	  }
  }

}
