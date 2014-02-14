package com.droidquest.items;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.droidquest.materials.Material;
import com.droidquest.operation.Operation;

public class Train extends Item 
{
public Train() 
  {
	x=0; y=0; room =null;
	width=56; height=32;
	GenerateIcons();
  }

public void GenerateIcons() 
  {
	icons = new ImageIcon[1];
	icons[0]= new ImageIcon(new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR));
	Graphics g;
	try
	  {
	     g = icons[0].getImage().getGraphics();
	  }
	catch (NullPointerException e)
	  {
	     System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
	     return;
	  }
	Graphics2D g2 = (Graphics2D) g;
	Color transparent = new Color(0,0,0,0);
	g2.setBackground(transparent);
	g2.clearRect(0,0,width,height);
	g.setColor(Color.white);
	g.fillRect(0,2,40,20);
	g.fillRect(4,0,32,2);
	g.fillRect(40,4,4,16);
	g.fillRect(44,6,4,12);
	g.fillRect(48,8,4,8);
	g.fillRect(52,10,4,4);
	g.fillRect(0,26,16,4);
	g.fillRect(4,24,8,8);
	g.fillRect(24,26,16,4);
	g.fillRect(28,24,8,8);
	g.setColor(Color.black);
	g.fillRect(8,4,12,8);
	g.fillRect(4,14,36,2);
	g.fillRect(16,18,16,2);
	g.fillRect(28,4,4,8);
	g.fillRect(32,6,4,6);
	g.fillRect(36,8,4,4);
	currentIcon = icons[0].getImage();
  }

public boolean CanBePickedUp(Item item) 
  {
	if (item == getPlayer())
	  {
	     PicksUp(getPlayer());
	     level.setPlayer(this);
	     Material mat = (Material) level.materials.elementAt(8);
	     mat.passable=true;
	  }
	return false;
  }

@Override
protected Operation getMouseClickOperation(MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
        return getOperationFactory().createExitTrainOperation(this);
    }

    return super.getMouseClickOperation(e);
}

public void exitTrain() {
    if (level.rooms.indexOf(room)==14)
      {
     room.SetMaterial(8,0,0);
     room.SetMaterial(9,0,0);
     room.SetMaterial(10,0,0);
     room.SetMaterial(11,0,0);
     room.SetMaterial(18,8,0);
     room.SetMaterial(18,9,0);
      }
    level.setPlayer(carrying);
    Drops();
    room=null;
    Material mat = (Material) level.materials.elementAt(8);
    mat.passable=false;
}

    public void Animate()
  {
	if (room != null)
	  if (carrying != null)
	    MoveRight(8);
  }


}
