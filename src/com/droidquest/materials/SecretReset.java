package com.droidquest.materials;

import com.droidquest.items.Item;

public class SecretReset extends Material 
{
public SecretReset() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item) 
  {
	if (item==level.player)
	  {
	     level.LinkRoomsUpDown(8,7);
	  }
  }
}