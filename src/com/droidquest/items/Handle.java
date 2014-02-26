package com.droidquest.items;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.droidquest.Room;

public class Handle extends Item 
{
	// Handle used to pull sliding wall

	public Handle(int X, int Y, Room r) 
	  {
		x=X; y=Y; room=r;
		width=28; height=12;
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
		g.fillRect(0,4,16,4);
		g.fillRect(16,2,12,8);
		g.fillRect(20,0,4,12);
		currentIcon = icons[0].getImage();
	  }

	public boolean CanBePickedUp(Item item) 
	  {
		if (item != getPlayer()) return false;
		PicksUp(item);
		level.setPlayer(this);
		return false;
	  }

    public void dropHandle() {
        level.setPlayer(carrying);
        Drops();
        room.SetMaterial(1, 4, 8);
        room.SetMaterial(2, 4, 8);
        room.SetMaterial(13, 4, 0);
        room.SetMaterial(14, 4, 0);
        x=13*28;
    }

    public void pullWallLeft() {
        if (x>13*28)
          {
         room.SetMaterial(x/28-13, 4, 8);
         room.SetMaterial(x/28-1, 4, 0);
         MoveLeft(28);
          }
    }

    public void pullWallRight() {
        if (x<15*28)
        {
            room.SetMaterial(x/28-12, 4, 0);
            MoveRight(28);
            room.SetMaterial(x/28-1, 4, 8);
        }
    }
}

