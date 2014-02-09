package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;

public class PlayerBlocker extends Material 
{
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
  }

public void Animate()
  {
	if (filenames!=null)
	  {
	     animationState++;
	     if (animationState==filenames.length)
	       animationState=0;
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

    public int getAnimationState() {
        return animationState;
    }

    public String getFilename(int animationState) {
        return filenames[animationState];
    }
}
