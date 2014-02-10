package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

public class SolderingPen extends Device 
{
boolean hot;
Port currentPort=null; // Port that Soldering pen is currently over

public SolderingPen() 
  {
	width=22; height=26;
	GenerateIcons();
	currentIcon = icons[0].getImage();
	ports = new Port[1];
	ports[0] = new Port(2,20,Port.TYPE_UNDEFINED,0,Port.ROT_DOWN, this);
	
  }

public void GenerateIcons() 
  {
	// Executed once during initialization
	icons = new ImageIcon[3];
	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	icons[1]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	icons[2]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	Graphics g;
	Graphics2D g2;
	try
	  {
	     g = icons[0].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to SolderingPen Image");
	     return;
	  }
	g2 = (Graphics2D) g;
	g2.setBackground(new Color(0,0,0,0));
	g2.clearRect(0,0,width,height);
	g2.setColor(Color.blue);
	g2.fillRect(18,0,6,4);
	g2.fillRect(10,2,2,4);
	g2.fillRect(10,4,10,2);
	g2.fillRect(16,6,10,4);
	g2.fillRect(10,10,6,4);
	g2.fillRect(6,14,6,4);
	g2.fillRect(0,18,12,8);
	
	try
	  {
	     g = icons[1].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to SolderingPen Image");
	     return;
	  }
	g2 = (Graphics2D) g;
	g2.setBackground(new Color(0,0,0,0));
	g2.clearRect(0,0,width,height);
	g2.setColor(Color.blue);
	g2.fillRect(18,0,6,4);
	g2.fillRect(10,2,2,4);
	g2.fillRect(10,4,10,2);
	g2.fillRect(16,6,10,4);
	g2.fillRect(10,10,6,4);
	g2.fillRect(6,14,6,4);
	g2.setColor(new Color(255,128,0));
	g2.fillRect(0,18,12,8);
	
	try
	  {
	     g = icons[2].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to SolderingPen Image");
	     return;
	  }
	g2 = (Graphics2D) g;
	g2.setBackground(new Color(0,0,0,0));
	g2.clearRect(0,0,width,height);
	g2.setColor(Color.blue);
	g2.fillRect(18,0,6,4);
	g2.fillRect(10,2,2,4);
	g2.fillRect(10,4,10,2);
	g2.fillRect(16,6,10,4);
	g2.fillRect(10,10,6,4);
	g2.fillRect(6,14,6,4);
	g2.setColor(Color.green);
	g2.fillRect(0,18,12,8);
	
	currentIcon = icons[0].getImage();
  }

public void CheckPort() 
  {
	hot=false;
	currentPort=null;
//	Item item = level.FindNearestItem(this);	
	for (int a=0; a<level.items.size(); a++)
	  {
	     Item item = (Item) level.items.elementAt(a);
	     if (!item.isDevice() || !Overlaps(item) || item==this)
	       item=null;
	     if (item!=null)
	       {
		  Device device = (Device) item;
		  for (int b=0; b<device.ports.length; b++)
		    {
		       hot = true;
		       if (device.ports[b].x + device.x < x) hot = false;
		       if (device.ports[b].x + device.x > x+9) hot = false;
		       if (device.ports[b].y + device.y < y+18) hot = false;
		       if (device.ports[b].y + device.y > y+25) hot = false;
		       if (hot) 
			 {
			    currentPort = device.ports[b];
			    if (device.ports[b].myWire == null)
			      currentIcon = icons[1].getImage();
			    else
			      currentIcon = icons[2].getImage();
			    b = device.ports.length;
			    a = level.items.size();
			 }
		       else
			 currentIcon = icons[0].getImage();
		    }
	       }
	  }
	if (hot==false)
	  currentIcon = icons[0].getImage();
  }

public void MoveUp(boolean nudge) 
  {
	Room tempRoom = room;
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
	if (tempRoom != room && ports[0].myWire != null)
	  ports[0].myWire.Remove();
//	  wiredPort=null;
	CheckPort();
  }

public void MoveDown(boolean nudge) 
  {
	Room tempRoom = room;
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
	if (tempRoom != room && ports[0].myWire != null)
	  ports[0].myWire.Remove();
//	  wiredPort=null;
	CheckPort();
  }

public void MoveLeft(boolean nudge) 
  {
	Room tempRoom = room;
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
	if (tempRoom != room && ports[0].myWire != null)
	  ports[0].myWire.Remove();
//	  wiredPort=null;
	CheckPort();
  }

public void MoveRight(boolean nudge) 
  {
	Room tempRoom = room;
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
	if (tempRoom != room && ports[0].myWire != null)
	  ports[0].myWire.Remove();
//	  wiredPort=null;
	CheckPort();
  }

public void Animate() 
  {
	Room tempRoom = room;
	super.Animate();
	if (tempRoom != room && ports[0].myWire != null)
	  ports[0].myWire.Remove();
	CheckPort();
  }

public void Decorate() 
  {return;}

public void WirePort() 
  {
	if (hot)
	  {
	     if (ports[0].myWire == null) // If SP is not wired
	       {
		  if (currentPort.myWire != null) // If currentPort is wired
		    {
		       // Remove Wire from currentPort
		       currentPort.myWire.Remove();
		       ports[0].value = false;
		       ports[0].type = Port.TYPE_UNDEFINED;
		    }
		  else // If currentPort not wired
		    {
		       // Create Wire from CurrentPort to Soldering Pen
		       Wire tempWire = new Wire(currentPort, ports[0]);
		    }
	       }
	     else // if SP is wired
	       {
		  if (currentPort.myWire != null) // If currentPort is wired
		    {
		       // Remove wire at currentPort
		       currentPort.myWire.Remove();
		       // Remove wire attached to Pen
		       if (ports[0].myWire != null)
			 ports[0].myWire.Remove();
		       ports[0].value = false;
		       ports[0].type = Port.TYPE_UNDEFINED;
		    }
		  else // If currentPort not wired
		    {
		       // Attach Wire to currentPort
		       ports[0].myWire.ConnectTo(currentPort);
		    }
	       }
	  }
	else if (ports[0].myWire != null) // If not hot and SP wired
	  {
	     // Remove Wire to Pen
	     ports[0].myWire.Remove();
	     ports[0].value = false;
	     ports[0].type = Port.TYPE_UNDEFINED;
	  }
  }

public boolean Function () 
  {
	if (ports[0].myWire == null)
	  {
	     ports[0].value = false;
	     ports[0].type = Port.TYPE_UNDEFINED;
	  }
	return false;
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
	if (e.getKeyCode() == e.VK_C) 
	  {
	     if (ports[0].myWire != null)
	       ports[0].myWire.Remove();
	     level.gameCursor.x = x;
	     level.gameCursor.y = y;
	     level.gameCursor.room = room;
	     room = null;
	     if (level.currentViewer == level.player)
	       level.currentViewer=level.gameCursor;
	     level.player = level.gameCursor;
	     if (level.remote != null)
	       if (level.remote.carriedBy != null)
		 {
		    level.remote.carriedBy = level.player;
		 }
	  }
	if (e.getKeyCode() == e.VK_R) 
	  {
          op = getOperationFactory().createToggleRemoteOperation();
	  }
	if (e.getKeyCode() == e.VK_P) 
	  {
	     if (level.paintbrush == null) return false;
	     if (ports[0].myWire != null)
	       ports[0].myWire.Remove();
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
	     level.player = level.helpCam;
	     level.currentViewer = level.helpCam;
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
	     WirePort();
	  }
	if (e.getKeyCode() == e.VK_F) 
	  {
	     if (hot)
	       {
		  if (ports[0].myWire != null) // If SP is wired
		    {
		       // Flip wire attached to SP
		       Port tempPort = ports[0].myWire.fromPort;
		       ports[0].myWire.fromPort = ports[0].myWire.toPort;
		       ports[0].myWire.toPort = tempPort;
		    }
		  else if (ports[0].myWire == null) // If SP is not wired
		    {
		       // Flip wire attached to CurrentPort
		       if (currentPort.myWire != null)
			 {
			    Port tempPort = currentPort.myWire.fromPort;
			    currentPort.myWire.fromPort = currentPort.myWire.toPort;
			    currentPort.myWire.toPort = tempPort;
			 }
		    }
	       }
	     else
	       {
		  if (ports[0].myWire != null) // If SP is wired
		    {
		       // Flip wire attached to SP
		       Port tempPort = ports[0].myWire.fromPort;
		       ports[0].myWire.fromPort = ports[0].myWire.toPort;
		       ports[0].myWire.toPort = tempPort;
		    }
	       }
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

public void MouseClick(MouseEvent e) 
  {
	int button=0;
	if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)
	  button = 1;
	if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)
	  button = 3;
	
	if (button==1)
	  {
	     if (e.getClickCount()==1)
	       {
		  autoX = e.getX() - 2;
		  autoY = e.getY() - 20;
		  automove = 1;
	       }
	     else if (e.getClickCount()==2)
	       {
		  int dx = e.getX() - 2 - x;
		  int dy = e.getY() - 20 - y;
		  if (Math.abs(dx) > Math.abs(dy))
		    {
		       autoY=0; autoX=28;
		       if (dx<0) autoX=-28;
		       automove=2;
		    }
		  else
		    {
		       autoX=0; autoY=32;
		       if (dy<0) autoY=-32;
		       automove=2;
		    }
	       }
	  }
		
	if (button==3)
	  {
	     KeyEvent k = new KeyEvent(e.getComponent(), e.getID(), 
				       e.getWhen(), 0, 
				       KeyEvent.VK_SPACE, ' ');
	     KeyUp(k);
	  }
	
  }

}
