package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.swing.util.DirectionUtil;

public class Remote extends Item {
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
    Operation op = null;
	if (e.getKeyCode() == KeyEvent.VK_S)
	  {
         op = getOperationFactory().createSwitchToSolderingPenOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_C)
	  {
         op = getOperationFactory().createSwitchToGameCursorOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_P)
	  {
         op = getOperationFactory().createSwitchToPaintbrushOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_SLASH)
	  {
         op = getOperationFactory().createHelpOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	  {
	     op = getOperationFactory().createMoveOperation(this, Direction.Right,
                 e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Left,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == KeyEvent.VK_UP)
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Up,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	  {
          op = getOperationFactory().createMoveOperation(this, Direction.Down,
                  e.isControlDown() ? Distance.Nudge : Distance.Step);
	  }
	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	  {
          op = getOperationFactory().createToggleRemoteOperation(this);
	  }

    if (op != null && op.canExecute()) {
        op.execute();
    }
	return false;
  }

public boolean KeyDown(KeyEvent e) 
  {
      Operation op = null;
      switch (e.getKeyCode()) {
          case KeyEvent.VK_RIGHT:
          case KeyEvent.VK_LEFT:
          case KeyEvent.VK_UP:
          case KeyEvent.VK_DOWN:
              op = getOperationFactory().createMoveRepeatOperation(
                      this, DirectionUtil.getDirection(e.getKeyCode()), e.isControlDown() ? Distance.Nudge : Distance.Step);
      }

      if (op != null && op.canExecute()) {
          op.execute();
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
