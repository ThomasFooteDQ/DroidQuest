package com.droidquest.devices;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;

import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

public class Device extends Item 
{
	// Base Class for the Logical Devices

	transient Graphics g; 
	transient static Color transparent = new Color(0,0,0,0); 
	public Port[] ports;
	public int rotation; // 0=Up, 1=Right, 2=Down, 3=Left
	// Reference to the toolbox means this device can be put inside the ToolBox
	transient boolean goesInToolbox; 

	public Device() 
	  {
		// Constructor
		rotation =0;
	  }

	public void writeRef(ObjectOutputStream s) throws IOException 
	  {
		super.writeRef(s);
		for (int a=0; a<ports.length; a++)
		  ports[a].writeRef(s);
	  }

	public void readRef(ObjectInputStream s) throws IOException 
	  {
		super.readRef(s);
		for (int a=0; a<ports.length; a++)
		  ports[a].readRef(s,level);
		GenerateIcons();
	  }

	public void GenerateIcons() 
	  {
		goesInToolbox=false;
		if (ports!=null)
		  for (int a=0; a<ports.length; a++)
		    ports[a].myDevice = this;
		
		icons = new ImageIcon[2];
		if (rotation%2==0)
		  {
		     icons[0] = new ImageIcon( new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		     icons[1] = new ImageIcon( new BufferedImage(height,width,BufferedImage.TYPE_4BYTE_ABGR));
		  }
		else
		  {
		     icons[1] = new ImageIcon( new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
		     icons[0] = new ImageIcon( new BufferedImage(height,width,BufferedImage.TYPE_4BYTE_ABGR));
		  }
	  }

	public boolean Function() 
	  {
		// Performs the function of the device, such as calculating the
		// output based upon inputs, or handling external functions such as
		// thrusting, touching walls, grabbing objects, antenna, etc...
		// 
		// 
		// Return False unless the device is a Node-like device and output
		// has changed. 
		// 
		return false;
	  }

	public void Decorate() 
	  {
		currentIcon = icons[rotation%2].getImage();
		try
		  {
		     g = currentIcon.getGraphics();
		  }
		catch (NullPointerException e)
		  {
		     System.out.println("Could not get Graphics pointer to Device Image");
		     return;
		  }
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,width,height);
		for (int a=0; a<ports.length; a++)
		  ports[a].Draw(g, rotation);
	  }
	  
	public boolean isDevice() 
	  {
		return true;
	  }

	public boolean isNode() 
	  {
		return false;
	  }

	public void rotate(int dir) 
	  {
		if (rotation ==0 && dir == -1)
		  rotation = 3;
		else if (rotation == 3 && dir == 1)
		  rotation =0;
		else
		  rotation += dir;
		
		int oldW = width;
		int oldH = height;
		int temp = width;
		width = height;
		height = temp;
		for (int a=0; a<ports.length; a++)
		  {
		     int oldX = ports[a].x*2 + 1;
		     int oldY = ports[a].y*2 + 1;
		     temp = ports[a].width;
		     ports[a].width = ports[a].height;
		     ports[a].height = temp;
		     switch (dir)
		       {
			case 1: // Turn Right
			  oldX = oldX - oldW;
			  oldY = oldY - oldH;
			  ports[a].x = (width - oldY)/2;
			  ports[a].y = (height + oldX)/2 ;		    
			  break;
			case -1: // Turn Left
			  oldX = oldX - oldW;
			  oldY = oldY - oldH;
			  ports[a].x = (width + oldY)/2;
			  ports[a].y = (height - oldX)/2;		    
			  break;
		       }
		  }
	  }

	public void IsDropped() 
	  {
		super.IsDropped();
		if (goesInToolbox)
		  {
		     if (level.toolbox != null)
		       if (((ToolBox)level.toolbox).open)
			 {
			    if (Overlaps(level.toolbox))
			      {
				 // Remove all wires and delete device
				 for (int a = 0; a<ports.length; a++)
				   if (ports[a].myWire!=null)
				     ports[a].myWire.Remove();
				 level.items.removeElement(this);
			      }
			 }
		  }
	  }

	public boolean CanBePickedUp(Item item) 
	  {
		if (item.getClass().toString().endsWith("Robot"))
		  return false;
		else
		  return super.CanBePickedUp(item);
	  }

	public void Erase() 
	  {
		super.Erase();
		g = null;
		for (int a=0; a<ports.length; a++)
		  {
		     ports[a].myDevice = null;
		     ports[a].myWire = null;
		  }
	  }

	public void flip() 
	  {
		return;
	  }

	public Object clone()
	  {
		Device newDevice = null;
		newDevice = (Device) super.clone();
		newDevice.ports = null;
		newDevice.GenerateIcons();
		return newDevice;
	  }

	}

