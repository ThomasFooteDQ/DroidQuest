package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.items.Item;

public class Remote extends Item 
{
public Remote() 
  {
//	width=28; height=32;
	width=4; height=20;
	level.electricity = true;
	GenerateIcons();
  }

public void GenerateIcons() 
  {
//	icons = new ImageIcon[2];
//	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
//	icons[1]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));

	icons = new ImageIcon[1];
	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));

	Graphics g;
	Graphics2D g2;
	try
	  {
	     g = icons[0].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to Remote Image");
	     return;
	  }
	g2 = (Graphics2D) g;
	g2.setBackground(new Color(0,0,0,0));
	g2.clearRect(0,0,width,height);
	g2.setColor(new Color(255,128,0));
	g2.fillRect(2,0,2,20);
	g2.fillRect(0,16,4,4);
	currentIcon = icons[0].getImage();	

//	try
//	  {
//	     g = icons[0].getImage().getGraphics();
//	  }
//	catch (NullPointerException e)
//	  {
//	     System.out.println("Could not get Graphics pointer to Remote Image[0]");
//	     return;
//	  }
//	g2 = (Graphics2D) g;
//	g2.setBackground(new Color(0,0,0,0));
//	g2.clearRect(0,0,width,height);
//	g2.setColor(Color.white);
//	g2.fillRect(20,0,4,12);
//	g2.fillRect(0,12,28,20);
//	g2.setColor(Color.black);
//	g2.fillRect(8,14,12,2);
//	g2.fillRect(4,18,20,2);
//	g2.fillRect(8,22,12,2);
//	g2.fillRect(20,26,4,4);
	
//	try
//	  {
//	     g = icons[1].getImage().getGraphics();
//	  }
//	catch (NullPointerException e)
//	  {
//	     System.out.println("Could not get Graphics pointer to Remote Image[1]");
//	     return;
//	  }
//	g2 = (Graphics2D) g;
//	g2.setBackground(new Color(0,0,0,0));
//	g2.clearRect(0,0,width,height);
//	g2.setColor(new Color(255,128,0));
//	g2.fillRect(20,0,4,12);
//	g2.fillRect(0,12,28,20);
//	g2.setColor(Color.black);
//	g2.fillRect(8,14,12,2);
//	g2.fillRect(4,18,20,2);
//	g2.fillRect(8,22,12,2);
//	g2.fillRect(20,26,4,4);	

  }

public void Animate() 
  {
	if (carriedBy != null)
	  if (carriedBy.room != room)
	    room = carriedBy.room;
	super.Animate();
//	if (level.electricity)
//	  currentIcon = icons[1].getImage();
//	else
//	  currentIcon = icons[0].getImage();
  }

public boolean CanBePickedUp(Item i) 
  {
//	if (i.getClass().toString().endsWith("Robot"))
	  return false;
//	return true;
  }

public boolean KeyUp(KeyEvent e) 
  {
	if (e.getKeyCode() == e.VK_S) 
	  {
          getOperationFactory().createSolderingPenOperation(this).execute();
	  }
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
	if (e.getKeyCode() == e.VK_P) 
	  {
	     if (level.paintbrush == null) return false;
	     level.paintbrush.x = x;
	     level.paintbrush.y = y;
	     level.paintbrush.room = room;
	     room = null;
	     if (level.currentViewer == level.player)
	       level.currentViewer=level.paintbrush;
	     level.player = level.paintbrush;
	  }
	if (e.getKeyCode() == e.VK_SLASH) 
	  {
	     if (level.helpCam == null) return false;
	     if (level.player != level.helpCam)
	       {
		  level.player = level.helpCam;
		  level.currentViewer = level.helpCam;
	       }
	  }
	if (e.getKeyCode() == e.VK_RIGHT) 
	  {
	     if (carriedBy==null)
	       MoveRight(e.isControlDown());
	     repeating=0;
	     return true;
	  }
	if (e.getKeyCode() == e.VK_LEFT) 
	  {
	     if (carriedBy==null)
	       MoveLeft(e.isControlDown());
	     repeating=0;
	     return true;
	  }
	if (e.getKeyCode() == e.VK_UP) 
	  {
	     if (carriedBy==null)
	       MoveUp(e.isControlDown());
	     repeating=0;
	     return true;
	  }
	if (e.getKeyCode() == e.VK_DOWN) 
	  {
	     if (carriedBy==null)
	       MoveDown(e.isControlDown());
	     repeating=0;
	     return true;
	  }
	if (e.getKeyCode() == e.VK_SPACE) 
	  {
	     level.electricity = ! level.electricity;
	  }
	return false;
  }

public boolean KeyDown(KeyEvent e) 
  {
	if (e.getKeyCode() == e.VK_RIGHT) 
	  {
	     repeating++;
	     if (repeating>10)
	       {
		  MoveRight(e.isControlDown());
		  return true;
	       }
	     return false;
	  }
	if (e.getKeyCode() == e.VK_LEFT) 
	  {
	     repeating++;
	     if (repeating>10)
	       {
		  MoveLeft(e.isControlDown());
		  return true;
	       }
	     return false;
	  }
	if (e.getKeyCode() == e.VK_UP) 
	  {
	     repeating++;
	     if (repeating>10)
	       {
		  MoveUp(e.isControlDown());
		  return true;
	       }
	     return false;
	  }
	if (e.getKeyCode() == e.VK_DOWN) 
	  {
	     repeating++;
	     if (repeating>10)
	       {
		  MoveDown(e.isControlDown());
		  return true;
	       }
	     return false;
	  }
	return false;
  }

public void MoveUp(boolean nudge) 
  {
	Item item = level.FindNearestItem(this);
	if (item != null)
	  {
	     if (item.InternalRoom != null)
	       if (item.UpEnterOverlap(this))
		 {
		    int newX = 280; // 10 * 28
		    int newY = 320; // 10 * 32
		    x = newX;
		    y = newY;
		    SetRoom(item.InternalRoom);
		 }
	  }
	super.MoveUp( nudge);
  }

public void MoveDown(boolean nudge) 
  {
	Item item = level.FindNearestItem(this);
	if (item != null)
	  {
	     if (item.InternalRoom != null)
	       if (item.DownEnterOverlap(this))
		 {
		    int newX = 280; // 10 * 28
		    int newY =   0; //  0 * 32
		    x = newX;
		    y = newY;
		    SetRoom(item.InternalRoom);
		 }
	  }
	super.MoveDown( nudge);
  }

public void MoveLeft(boolean nudge) 
  {
	Item item = level.FindNearestItem(this);
	if (item != null)
	  {
	     if (item.InternalRoom != null)
	       if (item.LeftEnterOverlap(this))
		 {
		    int newX = 532; // 19 * 28
		    int newY = 176; // 5.5 * 32
		    x = newX;
		    y = newY;
		    SetRoom(item.InternalRoom);
		 }
	  }
	super.MoveLeft( nudge);
  }

public void MoveRight(boolean nudge) 
  {
	Item item = level.FindNearestItem(this);
	if (item != null)
	  {
	     if (item.InternalRoom != null)
	       {
		  if (item.RightEnterOverlap(this))
		    {
		       int newX =   0; // 0 * 28
		       int newY = 176; // 5.5 * 32
		       x = newX;
		       y = newY;
		       SetRoom(item.InternalRoom);
		    }
	       }
	  }
	super.MoveRight( nudge);
  }

}
