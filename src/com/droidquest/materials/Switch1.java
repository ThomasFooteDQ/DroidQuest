package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.items.Train;

public class Switch1 extends Switch 
{
transient Train train;

public Switch1() 
  {
	super(Switch.ROT_UP);
  }

public void TouchedByItem(Item item) 
  {
	if (train==null)
	  for (int a=0; a<level.items.size(); a++)
	    {
	       Item t = (Item) level.items.elementAt(a);
	       if (t.getClass().toString().endsWith("Train"))
		 train = (Train) t;
	    }
	if (train==null) 
	  return;
	if (train.room==null)
	  {
	     train.x = 108;
	     train.y = 250;
	     train.room = item.room;
	  }
  }

}