package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.levels.Level;

public class UnBurner extends Item 
{
int burning;
int animation;

public UnBurner(int X, int Y, Room r) 
  {
	x=X; y=Y; room=r;
	width=28; height=30;
	GenerateIcons();
  }

public void GenerateIcons() 
  {
	icons = new ImageIcon[2];
	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	icons[1]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	Graphics g;
	Graphics2D g2;
	Color transparent = new Color(0,0,0,0);
	
	// 0 = Off
	try
	  {
	     g = icons[0].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
	     return;
	  }
	g.setColor(Color.white);
	g.fillRect(0,0,28,30);
	g.setColor(Color.black);
	g.fillRect(8,8,12,14);
	g.fillRect(4,10,20,10);
	g.setColor(Color.white);
	g.fillRect(12,10,4,10);
	g.fillRect(8,12,12,6);

	// 1 = On
	try
	  {
	     g = icons[1].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
	     return;
	  }
	g.setColor(new Color(255,128,0));
	g.fillRect(0,0,28,30);
	g.setColor(Color.black);
	g.fillRect(8,8,12,14);
	g.fillRect(4,10,20,10);
	g.setColor(new Color(255,128,0));
	g.fillRect(12,10,4,10);
	g.fillRect(8,12,12,6);

	currentIcon = icons[0].getImage();
	
  }

public boolean CanBePickedUp(Item i) 
  {
	// Find the SmallChip and Erase it
	Item sc=null;
	for (int a=0; a<level.items.size(); a++)
	  {
	     Item item = (Item) level.items.elementAt(a);
	     if (item.getClass().toString().endsWith("SmallChip"))
	       if (((SmallChip)item).inBurner)
		 sc = (SmallChip) item;
	  }
	if (sc==null) return false;
	
	// Find the PrototypeChip
	Item pc=null;
	for (int a=0; a<level.items.size(); a++)
	  {
	     Item item = (Item) level.items.elementAt(a);
	     if (item.getClass().toString().endsWith("PrototypeChip"))
	       if (((PrototypeChip)item).inBurner)
		 pc = (PrototypeChip) item;
	  }
	if (pc==null) return false;
	
	// Start the ChipDecompiler thread
	ChipDecompiler cd = new ChipDecompiler( (PrototypeChip) pc, (SmallChip) sc);

      level.getSoundPlayer().playIfInRoom(room, Level.BURNSOUND);
      burning = 10;
	
	return false;
  }

public void Animate() 
  {
	if (burning>0)
	  {
	     animation = 1- animation;
	     currentIcon = icons[animation].getImage();
	     burning--;
	  }
	else
	  currentIcon = icons[0].getImage();
  }

}
