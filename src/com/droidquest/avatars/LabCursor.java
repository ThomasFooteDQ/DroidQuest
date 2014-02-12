package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;

public class LabCursor extends Item {
private boolean hot;

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
	if (e.getKeyCode() == KeyEvent.VK_L)
	  {
         op = getOperationFactory().createLoadSmallChipOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_H)
	  {
         op = getOperationFactory().createToggleHotStateOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_S)
	  {
          op = getOperationFactory().createLabSolderingPenOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_R)
	  {
          op = getOperationFactory().createToggleRemoteOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_P)
	  {
          op = getOperationFactory().createSwitchToPaintbrushOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_T)
	  {
         op = getOperationFactory().createToggleToolboxOperation(this);
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
          op = getOperationFactory().createPickUpItemOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET)
	  {
          op = getOperationFactory().createRotateCarriedDeviceOperation(this, Rotation.Clockwise);
	  }
	if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET)
	  {
          op = getOperationFactory().createRotateCarriedDeviceOperation(this, Rotation.CounterClockwise);
	  }
	if (e.getKeyCode() == KeyEvent.VK_E)
	  {
	     op = getOperationFactory().createEnterItemOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_X)
	  {
         op = getOperationFactory().createExitItemOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_F)
	  {
          op = getOperationFactory().createFlipCarriedDeviceOperation(this);
	  }
	if (e.getKeyCode() == KeyEvent.VK_M)
	  {
          op = getOperationFactory().createOutputMemoryUsageOperation();
	  }

    if (op != null && op.canExecute()) {
        op.execute();
    }

	return false;
  }

public boolean KeyDown(KeyEvent e) 
  {
	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
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
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
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
	if (e.getKeyCode() == KeyEvent.VK_UP)
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
	if (e.getKeyCode() == KeyEvent.VK_DOWN)
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

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}