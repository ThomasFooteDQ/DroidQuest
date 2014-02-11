package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.Room;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.move.Direction;
import com.droidquest.operation.api.move.Distance;

public class LabCursor extends Item {
public boolean hot;

public LabCursor(){}

public LabCursor(int X, int Y, Room r) 
  {
	x=X; y=Y; 
	hot=false;
	room=r;
	width=28; height=32;
	GenerateIcons();
  }

public void GenerateIcons() 
  {
	icons = new ImageIcon[2];
	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	icons[1]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	Graphics g;
	try
	  {
	     g = icons[0].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to LabCursor Image");
	     return;
	  }
	Graphics2D g2 = (Graphics2D) g;
	g.setColor(Color.white);
	g.fillRect(0,0,width,height);
	try
	  {
	     g = icons[1].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to LabCursor Image");
	     return;
	  }
	g2 = (Graphics2D) g;
	g.setColor(new Color(255,128,0));
	g.fillRect(0,0,width,height);
	if (hot)
	  currentIcon = icons[1].getImage();
	else
	  currentIcon = icons[0].getImage();
  }

public boolean CanBePickedUp(Item i) 
  {
	if (i.getClass().toString().endsWith("Robot"))
	  return false;
	return true;
  }

public boolean KeyUp(KeyEvent e) 
  {
      Operation op = null;
	if (e.getKeyCode() == e.VK_L) 
	  {
         op = getOperationFactory().createLoadSmallChipOperation(this);
	  }
	if (e.getKeyCode() == e.VK_H) 
	  {
	     hot = !hot;
	     if (hot)
	       currentIcon = icons[1].getImage();
	     else
	       currentIcon = icons[0].getImage();
	     return false;
	  }
	if (e.getKeyCode() == e.VK_S) 
	  {
          op = getOperationFactory().createLabSolderingPenOperation(this);
	  }
	if (e.getKeyCode() == e.VK_R) 
	  {
          op = getOperationFactory().createToggleRemoteOperation();
	  }
	if (e.getKeyCode() == e.VK_P) 
	  {
	     if (level.paintbrush == null) return false;
	     if (carrying != null)
	       Drops();
	     level.paintbrush.x = (x/28)*28;
	     level.paintbrush.y = (y/32)*32;
	     level.paintbrush.room = room;
	     room = null;
	     if (level.currentViewer == level.player)
	       level.currentViewer=level.paintbrush;
	     level.player = level.paintbrush;
	  }
	if (e.getKeyCode() == e.VK_T) 
	  {
         op = getOperationFactory().createToggleToolboxOperation(this);
	  }
	if (e.getKeyCode() == e.VK_SLASH) 
	  {
         op = getOperationFactory().createHelpOperation(this);
	  }
	if (e.getKeyCode() == e.VK_RIGHT) 
	  {
         op = getOperationFactory().createMoveOperation(this, Direction.Right,
                 e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == e.VK_LEFT) 
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Left,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == e.VK_UP) 
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Up,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == e.VK_DOWN) 
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Down,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == e.VK_SPACE) 
	  {
	     if (carrying != null)
	       Drops();
	     else
	       {
		  Item item = level.FindNearestItem(level.gameCursor);
		  if (item != null)
		    if (item.CanBePickedUp(level.gameCursor))
		      PicksUp(item);
	       }
	     return false;
	  }
	if (e.getKeyCode() == e.VK_CLOSE_BRACKET) 
	  {
	     if (carrying !=null)
	       if (carrying.isDevice())
		 ((Device) carrying).rotate(1);
	     return false;
	  }
	if (e.getKeyCode() == e.VK_OPEN_BRACKET) 
	  {
	     if (carrying !=null)
	       if (carrying.isDevice())
		 ((Device) carrying).rotate(-1);
	     return false;
	  }
	if (e.getKeyCode() == e.VK_E) 
	  {
	     boolean found=false;
	     Item item = level.FindNearestItem(this);
	     if (item!=null)
	       if (item.InternalRoom!=null)
		 if (Overlaps(item))
		   if (!item.OverWall())
		     {
			int newX = 280; // 10 * 28
			int newY = 176; // 5.5 * 32
			x = newX;
			y = newY;
			SetRoom(item.InternalRoom);
			found=true;
		     }
	  }
	if (e.getKeyCode() == e.VK_X) 
	  {
	     if (room.portalItem!=null)
	       {
		  Dimension d = room.portalItem.GetXY();
		  int newX = d.width
		    + room.portalItem.getWidth()/2 
		    - width/2;
		  int newY = d.height
		    + room.portalItem.getHeight()/4*2 
		    - height/2;
		  x = newX;
		  y = newY;
		  SetRoom(room.portalItem.room);
		  level.currentViewer = level.player;
	       }
	  }
	if (e.getKeyCode() == e.VK_F) 
	  {
	     if (carrying != null)
	       if (carrying instanceof Device)
		 ((Device)carrying).flip();
	  }
	if (e.getKeyCode() == e.VK_M) 
	  {
	     Runtime runtime = Runtime.getRuntime();
	     long freemem = runtime.freeMemory();
	     long totalmem = runtime.totalMemory();
	     System.out.println("Total Memory = "+ totalmem
				+ ", (" + totalmem/1024 + "K), ("
				+ totalmem/1024/1024 + "M)");
	     System.out.println("Free Memory = "+ freemem
				+ ", (" + freemem/1024 + "K), ("
				+ freemem/1024/1024 + "M)");
	  }

    if (op != null && op.canExecute()) {
        op.execute();
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
		  if (carriedBy==null)
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
		  if (carriedBy==null)
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
		  if (carriedBy==null)
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
		  if (carriedBy==null)
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