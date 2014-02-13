package com.droidquest.materials;

import com.droidquest.items.Item;

public class CameraEnable extends Material 
{
transient Item hiddenCamera=null;

public CameraEnable() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item)
  {
	if (hiddenCamera==null)
	  for (int a=0; a<level.items.size(); a++)
	    {
	       Item i = (Item) level.items.elementAt(a);
	       if (i.getClass().toString().endsWith("HiddenCamera"))
		 hiddenCamera = i;
	    }
	if (item == getPlayer())
	  level.currentViewer = hiddenCamera;
  }

}
