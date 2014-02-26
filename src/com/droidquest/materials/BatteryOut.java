package com.droidquest.materials;

import com.droidquest.items.Item;

public class BatteryOut extends Material 
{
	// Graph that shows the battery charge in a Generic Robot

	public BatteryOut() 
	  {
		passable = true;
	  }

	public transient Item robot;

	public boolean equals(Material mat)
	  {
		if (super.equals(mat))
		  if (robot != null)
		    if (robot == ((BatteryOut)mat).robot)
		      return true;
		return false;
	  }

    public Item getRobot() {
        return robot;
    }
}
