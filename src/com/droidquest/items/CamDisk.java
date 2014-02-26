package com.droidquest.items;

import java.awt.Color;

import com.droidquest.Room;
import com.droidquest.decorations.TextBox;
import com.droidquest.materials.Material;

public class CamDisk extends Disk 
{
transient SpyCam spycam=null;

public CamDisk(int X, int Y, Room r) 
  {
	super(X,Y,r,Color.white,0);
  }

public void IsDropped() 
  {
	if (spycam==null)
	  for (int a=0; a<level.items.size(); a++)
	    {
	       Item item = (Item) level.items.elementAt(a);
	       if (item.getClass().toString().endsWith("SpyCam"))
		 spycam = (SpyCam) item;
	    }
	int bigX = (x+width/2)/28;
	int bigY = (y+height/2)/32;
	Material mat = room.MaterialArray[bigY][bigX];
	if (mat.getClass().toString().endsWith("Monitor"))
	  {
	     level.currentViewer=spycam;
	     level.setPlayer(spycam);
	     spycam.room=room;
	     for (int a=5; a<60; a++)
	       {
		  Room r = (Room) level.rooms.elementAt(a);
		  TextBox tb = (TextBox) r.textBoxes.elementAt(0);
		  tb.y -= 500;
	       }
	  }
  }

}