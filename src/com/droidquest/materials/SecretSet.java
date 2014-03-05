package com.droidquest.materials;

import com.droidquest.items.Item;

public class SecretSet extends Material 
{
public SecretSet() 
  {
	super(true, false);
  }

public void TouchedByItem(Item item) 
  {
	if (item== getPlayer())
	  {
	     level.LinkRoomsUpDown(2,7);
	     level.LinkRoomsUpDown(8,2);
	  }
  }
}