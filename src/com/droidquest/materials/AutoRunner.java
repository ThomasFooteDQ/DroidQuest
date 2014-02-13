package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.items.Item;

public class AutoRunner extends Material 
{
int direction;
public static final int UP       =0;
public static final int RIGHTUP  =1;
public static final int RIGHT    =2;
public static final int RIGHTDOWN=3;
public static final int DOWN     =4;
public static final int LEFTDOWN =5;
public static final int LEFT     =6;
public static final int LEFTUP   =7;
public static final int STOP     =8;

public AutoRunner(int d) 
  {
	super(Color.black, true, false);
	direction = d;
  }

public void TouchedByItem(Item item) 
  {
	if (item == getPlayer())
	  switch (direction)
	    {
	     case UP:
	       getPlayer().autoX= getPlayer().x;
	       getPlayer().autoY= getPlayer().y-32;
	       getPlayer().automove=1;
	       break;
	     case RIGHTUP:
	       getPlayer().autoX= getPlayer().x+28;
	       getPlayer().autoY= getPlayer().y-32;
	       getPlayer().automove=1;
	       break;
	     case RIGHT:
	       getPlayer().autoX= getPlayer().x+28;
	       getPlayer().autoY= getPlayer().y;
	       getPlayer().automove=1;
	       break;
	     case RIGHTDOWN:
	       getPlayer().autoX= getPlayer().x+28;
	       getPlayer().autoY= getPlayer().y+32;
	       getPlayer().automove=1;
	       break;
	     case DOWN:
	       getPlayer().autoX= getPlayer().x;
	       getPlayer().autoY= getPlayer().y+32;
	       getPlayer().automove=1;
	       break;
	     case LEFTDOWN:
	       getPlayer().autoX= getPlayer().x-28;
	       getPlayer().autoY= getPlayer().y+32;
	       getPlayer().automove=1;
	       break;
	     case LEFT:
	       getPlayer().autoX= getPlayer().x-28;
	       getPlayer().autoY= getPlayer().y;
	       getPlayer().automove=1;
	       break;
	     case LEFTUP:
	       getPlayer().autoX= getPlayer().x-28;
	       getPlayer().autoY= getPlayer().y-32;
	       getPlayer().automove=1;
	       break;
	     case STOP:
	       getPlayer().automove=0;
	       break;
	    }
  }

}
