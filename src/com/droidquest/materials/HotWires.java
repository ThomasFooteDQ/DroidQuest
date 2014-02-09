package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class HotWires extends Material 
{
	// Hot orange wires that burn charge of any object that passes over it. 
	// Cold = white.
	// Create shape by adding the direction values. UP+DOWN, LEFT+RIGHT, UP+LEFT, etc...

	public static final int UP=1;
	public static final int DOWN=2;
	public static final int LEFT=4;
	public static final int RIGHT=8;
	public boolean value;
	int wall;

	public HotWires(int w, boolean v) 
	  {
		passable = true;
		detectable = false;
		wall = w;
		value = v;
	  }

	public void TouchedByItem(Item item)
	  {
		if (value)
		  if (item.charge>0)
		    {
		       item.charge=0;
		       level.PlaySound(item.room, Level.DISCHARGESOUND);
		    }
	  }

	public boolean equals(Material mat)
	  {
		if (super.equals(mat))
		  if (value == ((HotWires)mat).value
		      && wall == ((HotWires)mat).wall)
		    return true;
		return false;
	  }

    public boolean getValue() {
        return value;
    }

    public int getWall() {
        return wall;
    }
}
