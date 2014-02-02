package com.droidquest.materials;

import java.awt.Color;

import javax.swing.ImageIcon;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;

public class PlayerBlocker extends Material 
{
transient ImageIcon images[];
String[] filenames;
int animationState=0;

public PlayerBlocker(Color col) 
  {
	color = col;
	detectable = false;
  }

public PlayerBlocker(String[] files) 
  {
	detectable = false;
	filenames = files;
	GenerateIcons();
  }

public void GenerateIcons() 
  {
	if (filenames != null)
	  {
	     int numfiles = filenames.length;
	     images = new ImageIcon[numfiles];
	     for (int a=0; a<filenames.length; a++)
	       images[a] = new ImageIcon("images/"+ filenames[a]);
	     icon = images[0];
	  }
	else
	  super.GenerateIcons();
  }

public void Animate() 
  {
	if (images!=null)
	  {
	     animationState++;
	     if (animationState==images.length)
	       animationState=0;
	     icon = images[animationState];
	  }
  }

public boolean Passable(Item item) 
  {
	if (level.gameCursor.getClass().toString().endsWith("GameCursor"))
	  {
	     GameCursor gc = (GameCursor) level.gameCursor;
	     if (gc.PlayerInRobot(null) == item)
	       return false;
	  }	
	if (item == level.player)
	  return false;
	else
	  return true;
  }

}
