package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.items.Sentry;

public class PanicButton extends Material 
{
boolean state = true;
transient Sentry sentry;
int number;

// image[0] = blue, off, Sentry alive
// image[1] = orange, on, Sentry dead

public PanicButton(int n) 
  {
	super(true, false);
	number = n;
  }

public void TouchedByItem(Item item)
  {
	if (item == level.player && level.player.carriedBy == null)
	  {
	     if (sentry==null)
	       {
		  for (int a=0; a<level.items.size(); a++)
		    {
		       Item i = (Item) level.items.elementAt(a);
		       if (i instanceof Sentry && i.room == item.room)
			 sentry = (Sentry) i;
		    }
	       }
	     if (sentry != null)
	       {
		  if (state)
		    { // turn off
		       sentry.previousBehavior = sentry.behavior;
		       sentry.behavior = -2;
		       state = false;
		    }
		  else
		    { // turn on
		       sentry.behavior = sentry.previousBehavior;
		       state = true;
		    }
	       }
	       
	  }
  }

public boolean equals(Material mat) 
  {
	if (super.equals(mat))
	  if (number == ((PanicButton)mat).number)
	    return true;
	return false;
  }

    public boolean getState() {
        return state;
    }
}
