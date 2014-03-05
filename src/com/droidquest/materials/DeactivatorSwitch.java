package com.droidquest.materials;

import java.util.Date;

import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.items.Sentry;

public class DeactivatorSwitch extends Material 
{
	// This object deactivates the Sentry in the Hot Wires room. When
	// activated, it sets the counter at 20 seconds, sets all versions of
	// the HotWires material to value=false, sets the sentry program so that
	// it's not sensitive to the area, and updates the timer text display.
	// When the counter reaches 0, it resets everything back to normal. 

	public boolean value;
	public Date timeout;
	transient Item sentry = null;
	transient TextBox textbox = null;

	public DeactivatorSwitch() 
	  {
		super(true, false);
		value=false;
	  }

	public void TouchedByItem(Item item)
	  {
		if (value==false)
		  {
		     value=true;
		     Date now = new Date();
		     timeout = new Date(now.getTime() + 20000);
		     // Find HotWires and set their value to false;
		     for (int a=0; a<level.materials.size(); a++)
		       {
			  Material mat = (Material) level.materials.elementAt(a);
			  if (mat instanceof HotWires)
			    {
			       HotWires hw = (HotWires) mat;
			       if (hw.wall != 0)
				 hw.value = false;
			    }
		       }
		     
		     // Find Sentry and set it's progam to something else.
		     if (sentry==null)
		       {
			  for(int a=0; a<level.items.size(); a++)
			    {
			       Item i = (Item) level.items.elementAt(a);
			       if (i instanceof Sentry && i.room == item.room.upRoom)
				 sentry = i;
			    }
		       }
		     ((Sentry) sentry).protect[3]=0;
		     
		     // Find the TextBox
		     if (textbox==null)
		       {
			  textbox = (TextBox) item.room.textBoxes.elementAt(0);
		       }
		  }
	  }

	public void Animate() 
	  {
		if (value)
		  {
		     Date now = new Date();
		     long timer = timeout.getTime() - now.getTime();
		     if (timer > 0)
		       {
			  long seconds = Math.abs(timer/1000) + 1;
			  if (seconds<10)
			    textbox.textString = "0" + seconds;
			  else
			    textbox.textString = "" + seconds;
		       }
		     else
		       {
			  for (int a=0; a<level.materials.size(); a++)
			    {
			       Material mat = (Material) level.materials.elementAt(a);
			       if (mat instanceof HotWires)
				 ((HotWires)mat).value =true;
			    }
			  ((Sentry) sentry).protect[3]=11*32;
			  value=false;
			  textbox.textString = "00";
		       }
		  }
	  }

    public boolean getValue() {
        return value;
    }
}