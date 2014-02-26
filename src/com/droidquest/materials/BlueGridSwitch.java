package com.droidquest.materials;

import java.util.Date;

import com.droidquest.Room;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;

public class BlueGridSwitch extends Material 
{
	// This object turns off the Blue grid (and HotWires), and opens the
	// doorway to the room above.

	boolean value=false;
	int animationState=0;
	transient Room room=null;
	Date timeout;
	transient TextBox textbox = null;

	public BlueGridSwitch() 
	  {
		super(true, false);
	  }

	public void TouchedByItem(Item item)
	  {
		if (animationState==0)
		  {
		     animationState = 1;
		     room = item.room;
		     timeout = new Date(new Date().getTime() + 20000);
		     textbox = (TextBox) item.room.textBoxes.elementAt(0);
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
			  textbox.textString = "00";
			  animationState++;
		       }
		       
		  }
		
		switch (animationState)
		  {
		   case 1:value=true;
		     room.SetMaterial(2,0,0);
		     room.upRoom.SetMaterial(2,11,0);
		     for (int a=0; a<level.materials.size(); a++)
		       {
			  Material mat = (Material) level.materials.elementAt(a);
			  if (mat instanceof HotWires)
			    {
			       HotWires hw = (HotWires) mat;
			       if (hw.wall==0)
				 hw.value=false;
			    }
		       }
		     for (int a=0; a<5; a++)
		       {
			  Graphix gr = (Graphix) room.graphix.elementAt(a);
			  String[] filelist = {"whiteHorizontal.gif"};
			  gr.filenames = filelist;
		       }
		     for (int a=0; a<5; a++)
		       {
			  Graphix gr = (Graphix) room.graphix.elementAt(a+5);
			  String[] filelist = {"whiteVertical.gif"};
			  gr.filenames = filelist;
		       }
		     animationState++;
		     break;
		   case 2:
		     room.SetMaterial(3,0,0);
		     room.upRoom.SetMaterial(3,11,0);
		     animationState++;
		     break;
		   case 3:
		     room.SetMaterial(4,0,0);
		     room.upRoom.SetMaterial(4,11,0);
		     animationState++;
		     break;
		   case 4:
		     room.SetMaterial(5,0,0);
		     room.upRoom.SetMaterial(5,11,0);
		     animationState++;
		     break;
		   case 5:
		     break;
		   case 6:
		     room.SetMaterial(5,0,9);
		     room.upRoom.SetMaterial(5,11,9);
		     value = false;
		     for (int a=0; a<level.materials.size(); a++)
		       {
			  Material mat = (Material) level.materials.elementAt(a);
			  if (mat instanceof HotWires)
			    {
			       HotWires hw = (HotWires) mat;
			       if (hw.wall==0)
				 hw.value=true;
			    }
		       }
		     for (int a=0; a<5; a++)
		       {
			  Graphix gr = (Graphix) room.graphix.elementAt(a);
			  String[] filelist = {"blueHorizontal.gif"};
			  gr.filenames = filelist;
		       }
		     for (int a=0; a<5; a++)
		       {
			  Graphix gr = (Graphix) room.graphix.elementAt(a+5);
			  String[] filelist = {"blueVertical.gif"};
			  gr.filenames = filelist;
		       }
		     animationState++;
		     break;
		   case 7:
		     room.SetMaterial(4,0,9);
		     room.upRoom.SetMaterial(4,11,9);
		     animationState++;
		     break;
		   case 8:
		     room.SetMaterial(3,0,9);
		     room.upRoom.SetMaterial(3,11,9);
		     animationState++;
		     break;
		   case 9:
		     room.SetMaterial(2,0,9);
		     room.upRoom.SetMaterial(2,11,9);
		     animationState = 0;
		     break;
		  }
	  }

    public boolean getValue() {
        return value;
    }
}
