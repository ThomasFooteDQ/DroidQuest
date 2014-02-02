package com.droidquest.items;

import com.droidquest.materials.Switch;


public class MazeLock extends Switch 
{
transient static Item paintbrush;

public MazeLock() 
  {
	super(Switch.ROT_DOWN);
  }

public void TouchedByItem(Item item) 
  {
	if (paintbrush==null)
	     paintbrush = level.paintbrush;
	  
	if (!value)
	  {
	     level.paintbrush = null;
	     value=true;
	  }
	else
	  {
//	     for (int a=0; a<level.items.size(); a++)
//	       {
//		  Item i = (Item) level.items.elementAt(a);
//		  if (i instanceof PaintBrush)
//		    level.paintbrush = i;
//	       }
	     level.paintbrush = paintbrush;
	     value = false;
	  }
  }

}
