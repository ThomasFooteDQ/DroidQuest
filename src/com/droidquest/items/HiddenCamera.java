package com.droidquest.items;

import com.droidquest.Room;

public class HiddenCamera extends Item 
{
public HiddenCamera(Room r) 
  {
	x=0; y=0; 
	room = r;
	width=0; height=0; 
	grabbable=false;
    setVisible(false);
  }
}
