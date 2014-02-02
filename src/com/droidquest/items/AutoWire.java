package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Device;
import com.droidquest.devices.PortDevice;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.devices.SmallChip;

public class AutoWire extends Item 
{
int animation; // 0=Wait to Wire
               // 1-8 = Wiring/Unwiring Port 1-8
Device chip;
PortDevice[] portdevices = new PortDevice[8];

public AutoWire(int X, int Y, Room r) 
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
	if (animation!=0) return false;
	// Find the Item that has inTester set true;
	chip=null;
	int pdcount =0;
	for (int a=0; a<level.items.size(); a++)
	  {
	     Item item = (Item) level.items.elementAt(a);
	     if (item.getClass().toString().endsWith("PrototypeChip"))
	       {
		  PrototypeChip pc = (PrototypeChip) item;
		  if (pc.inTester) chip = pc;
	       }
	     if (item.getClass().toString().endsWith("SmallChip"))
	       {
		  SmallChip sc = (SmallChip) item;
		  if (sc.inTester) chip = sc;
	       }
	     if (item.getClass().toString().endsWith("PortDevice"))
	       {
		  if (item.room == room)
		    {
		       portdevices[pdcount] = (PortDevice) item;
		       pdcount++;
		    }
	       }
	  }
	if (chip==null) return false;
	animation=1;
	return false;
  }

public void Animate() 
  {
	if (animation==0) return;
	
	if (animation==1)
	  {
	     if (portdevices[0].ports[0].myWire==null)
	       { // Wiring
		  portdevices[0].ports[0].type = Port.TYPE_UNDEFINED;
		  portdevices[0].ports[0].value = false;
		  Wire wire = new Wire(portdevices[0].ports[0], chip.ports[0]);
	       }
	     else
	       { // Unwiring
		  portdevices[0].ports[0].myWire.Remove();
		  portdevices[0].ports[0].type = Port.TYPE_UNDEFINED;
		  portdevices[0].ports[0].value = false;
	       }
	     animation++;
	     currentIcon = icons[1].getImage();
	     return;
	  }
	
	if (animation>=2 && animation <=8)
	  {
	     if (portdevices[0].ports[0].myWire!=null)
	       { // Wiring
		  if (portdevices[animation-1].ports[0].myWire==null)
		    {
		       portdevices[animation-1].ports[0].type = Port.TYPE_UNDEFINED;
		       portdevices[animation-1].ports[0].value = false;
		       Wire wire = new Wire(portdevices[animation-1].ports[0], chip.ports[animation-1]);
		    }
	       }
	     else
	       { // Unwiring
		  if (portdevices[animation-1].ports[0].myWire!=null)
		    {
		       portdevices[animation-1].ports[0].myWire.Remove();
		       portdevices[animation-1].ports[0].type = Port.TYPE_UNDEFINED;
		       portdevices[animation-1].ports[0].value = false;
		    }
	       }
	     animation++;
	  }

	if (animation==9)
	  {
	     currentIcon = icons[0].getImage();
	     animation=0;
	  }
	     
  }

public void Erase() 
  {
	super.Erase();
	chip=null;
	portdevices = null;
  }

}
