package com.droidquest.avatars;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;
import com.droidquest.materials.Material;
import com.droidquest.materials.RobotBlocker;

public class PaintBrush extends Item 
{
	// The Paintbrush works just like the original, except it allows
	// differnt color paints for differnt materials. Pressing 'P' as the
	// Paintbrush switches the Material Settings. 
	// 
	// Detectable, blocks all      Red
	// Undetectable, blocks all    Green
	// Undetectable, blocks Orange Orange
	// Undetectable, blocks White  White
	// Undetectable, blocks Blue   Blue

	int emptyIndex=0;
	int paintIndex; // Which paintMats[] am I using?
	transient Material[] paintMats;
	int matIndex;   // index of chosen paintMax in level.materials

	public PaintBrush() 
	  {
		width=28; height=32;
		GenerateIcons();
	  }

	public void GenerateIcons() 
	  {
		icons = new ImageIcon[5];
		icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		icons[1]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		icons[2]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		icons[3]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		icons[4]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		Graphics g;
		Graphics2D g2;
		Color transparent = new Color(0,0,0,0);
		for (int a=0; a<5; a++)
		  {
		     try
		       {
			  g = icons[a].getImage().getGraphics();
		       }
		     catch (NullPointerException e)
		       {
			  System.out.println("Could not get Graphics pointer to PaintBrush Image");
			  return;
		       }
		     g2 = (Graphics2D) g;
		     g2.setBackground(transparent);
		     g2.clearRect(0,0,width,height);
		     switch(a)
		       {
			case 0: g.setColor(new Color(192,0,0));
			  break;
			case 1: g.setColor(new Color(0,192,0));
			  break;
			case 2: g.setColor(new Color(192,96,0));
			  break;
			case 3: g.setColor(new Color(192,192,192));
			  break;
			case 4: g.setColor(new Color(0,0,192));
			  break;
		       }

		     g2.fillRect(0,0,28,18);
		     g2.fillRect(4,18,20,2);
		     g2.fillRect(8,20,12,10);
		     g2.fillRect(12,30,4,2);
		     g.setColor(Color.black);
		     g2.fillRect(0,12,28,2);
		     g2.fillRect(12,26,4,2);
		  }
		currentIcon=icons[0].getImage();
		
		paintMats = new Material[5];
		emptyIndex = 0;
		paintMats[0] = Material.FindSimiliar(new Material(Color.red, false, true));
		paintMats[1] = Material.FindSimiliar(new Material(Color.green, false, false));
		Item robot=null;
		for (int a=0; a<level.items.size(); a++)
		  if (((Item)level.items.elementAt(a)).getClass().toString().endsWith("OrangeRobot"))
		    robot = (GenericRobot) level.items.elementAt(a);
		if (robot==null)
		  System.out.println("Create paintbrush AFTER creating robots.");
		paintMats[2] = Material.FindSimiliar(new RobotBlocker(robot, new Color(255,128,0)));
		for (int a=0; a<level.items.size(); a++)
		  if (((Item)level.items.elementAt(a)).getClass().toString().endsWith("WhiteRobot"))
		    robot = (GenericRobot) level.items.elementAt(a);
		paintMats[3] = Material.FindSimiliar(new RobotBlocker(robot, Color.white));
		for (int a=0; a<level.items.size(); a++)
		  if (((Item)level.items.elementAt(a)).getClass().toString().endsWith("BlueRobot"))
		    robot = (GenericRobot) level.items.elementAt(a);
		paintMats[4] = Material.FindSimiliar(new RobotBlocker(robot, Color.blue));
		
		paintIndex=0;
		matIndex = level.materials.indexOf(paintMats[paintIndex]);
		
	  }

	public boolean KeyUp(KeyEvent e) 
	  {
		if (e.getKeyCode() == e.VK_C) 
		  {
		     level.gameCursor.x = x;
		     level.gameCursor.y = y;
		     level.gameCursor.room = room;
		     room = null;
		     if (level.currentViewer == level.player)
		       level.currentViewer=level.gameCursor;
		     level.player = level.gameCursor;
		  }
		if (e.getKeyCode() == e.VK_S) 
		  {
		     if (level.solderingPen == null) return false;
		     level.solderingPen.x = x;
		     level.solderingPen.y = y;
		     level.solderingPen.room = room;
		     room = null;
		     if (level.currentViewer == level.player)
		       level.currentViewer=level.solderingPen;
		     level.player = level.solderingPen;
		  }
		if (e.getKeyCode() == e.VK_R) 
		  {
		     if (level.remote == null) return false;
		     level.remote.x = x;
		     level.remote.y = y;
		     level.remote.room = room;
		     room = null;
		     if (level.currentViewer == level.player)
		       level.currentViewer=level.remote;
		     level.player = level.remote;
		  }
		if (e.getKeyCode() == e.VK_SLASH) 
		  {
		     if (level.helpCam == null) return false;
		     level.player = level.helpCam;
		     level.currentViewer = level.helpCam;
		  }
		if (e.getKeyCode() == e.VK_RIGHT) 
		  {
		     if (e.isShiftDown())
		       SetRoom(room.rightRoom);
		     if (carriedBy==null)
		       MoveRight(e.isControlDown());
		     repeating=0;
		     return true;
		  }
		if (e.getKeyCode() == e.VK_LEFT) 
		  {
		     if (e.isShiftDown())
		       SetRoom(room.leftRoom);
		     if (carriedBy==null)
		       MoveLeft(e.isControlDown());
		     repeating=0;
		     return true;
		  }
		if (e.getKeyCode() == e.VK_UP) 
		  {
		     if (e.isShiftDown())
		       SetRoom(room.upRoom);
		     if (carriedBy==null)
		       MoveUp(e.isControlDown());
		     repeating=0;
		     return true;
		  }
		if (e.getKeyCode() == e.VK_DOWN) 
		  {
		     if (e.isShiftDown())
		       SetRoom(room.downRoom);
		     if (carriedBy==null)
		       MoveDown(e.isControlDown());
		     repeating=0;
		     return true;
		  }
		if (e.getKeyCode() == e.VK_P) 
		  {
		     paintIndex++;
		     if (paintIndex==5) paintIndex=0;
		     matIndex = level.materials.indexOf(paintMats[paintIndex]);
		     currentIcon=icons[paintIndex].getImage();
		  }
		if (e.getKeyCode() == e.VK_SPACE) 
		  {
		     if (!room.editable) return false;
		     int bigX=(x+14)/28;
		     int bigY=(y+16)/32;
		     if (room.RoomArray[bigY][bigX]==emptyIndex)
		       room.SetMaterial(bigX,bigY,matIndex);
		     else
		       room.SetMaterial(bigX,bigY,emptyIndex);
		  }
		return false;
	  }

	public void MoveUp(boolean nudge) 
	  {
		int dist = 32;
		if (nudge) dist = 2;
		y=y-dist;
		if (y<0)
		  {
		     if (room.getUpRoom(this) != null)
		       { // change Rooms
			  y=y+384;
			  SetRoom(room.getUpRoom(this));
		       }
		     else // stop at top
		       y=0;
		  }
	  }

	public void MoveDown(boolean nudge) 
	  {
		int dist = 32;
		if (nudge) dist = 2;
		y=y+dist;
		if (y>383)
		  {
		     if (room.getDownRoom(this) != null)
		       { // change Rooms
			  y=y-384;
			  SetRoom(room.getDownRoom(this));
		       }
		     else // stop at bottom
		       y=384-32;
		  }
	  }

	public void MoveLeft(boolean nudge) 
	  {
		int dist = 28;
		if (nudge) dist = 2;
		x=x-dist;
		if (x<0)
		  {
		     if (room.getLeftRoom(this) != null)
		       { // change Rooms
			  x=x+560;
			  SetRoom(room.getLeftRoom(this));
		       }
		     else // stop at left
		       x=0;
		  }
	  }

	public void MoveRight(boolean nudge) 
	  {
		int dist = 28;
		if (nudge) dist = 2;
		x=x+dist;
		if (x>559)
		  {
		     if (room.getRightRoom(this) != null)
		       { // change Rooms
			  x=x-560;
			  SetRoom(room.getRightRoom(this));
		       }
		     else // stop at right
		       x=560-28;
		  }
	  }

	}
