package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;

public class ForceField extends Material 
{
String robotClassName=null;

public ForceField(String rc, Color c) 
  {
	super(true,false);
	robotClassName = rc;
	color = c;
  }

public boolean Passable(Item item)
  {
	if (item == getPlayer())
	  return false;
	else if (item instanceof GenericRobot)
	  {
	     GameCursor gc = (GameCursor) level.gameCursor;
	     if (gc.PlayerInRobot(null) == item)
	       return false;
	  }
	
	if (item.getClass().toString().endsWith(robotClassName))
	  return false;
	
	return true;
  }

public boolean equals(Material mat) 
  {
	if (super.equals(mat))
	  if (robotClassName == ((ForceField)mat).robotClassName)
	    return true;
	return false;
  }

}
