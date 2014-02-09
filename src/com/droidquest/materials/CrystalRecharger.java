package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class CrystalRecharger extends Material 
{
	// Charges a Crystal when one is passed over it.
	public CrystalRecharger() 
	  {
		super(true, false);
	  }

	public void TouchedByItem(Item item)
	  {
		// Check to see if it's a pure Crystal, not a Black Crystal
		if (item.getClass().toString().endsWith("Crystal"))
		  {
		     item.charge=100000;
		     level.PlaySound(item.room, Level.CHARGESOUND);
		  }
	  }

	}
