package com.droidquest.materials;

import com.droidquest.items.Item;

public class CameraDisable extends Material 
{
public CameraDisable() 
  {
	super(true, false);
  }


public void TouchedByItem(Item item) 
  {
	if (item == level.player)
	  if (level.currentViewer != level.player)
	    level.currentViewer = level.player;
  }
}
