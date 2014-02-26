package com.droidquest.materials;

import com.droidquest.items.Item;

public class Portal extends Material 
{
public String levelName;
public boolean bringStuff;
public boolean initLevel;

public Portal(String ln, boolean bs, boolean il) 
  {
	super(true, false);
	levelName = ln;
	bringStuff = bs;
	initLevel = il;
  }

public void TouchedByItem(Item item)
  {
	if (item == getPlayer() && getPlayer().carriedBy == null)
	  {
	     if (item.x%28==0 && item.y%32==0)
	       level.portal = this;
	  }
  }

public boolean equals(Material mat) 
  {
	if (super.equals(mat))
	  if (levelName == ((Portal)mat).levelName
	      && bringStuff == ((Portal)mat).bringStuff
	      && initLevel == ((Portal)mat).initLevel)
	    return true;
	return false;
  }


}
