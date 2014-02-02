package com.droidquest.items;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com.droidquest.Room;
import com.droidquest.decorations.TextBox;

public class SpyCam extends Item 
{
public SpyCam(Room r) 
  {
	x=0; y=0; 
	room = r;
	width=0; height=0; 
	grabbable=false;
  }

public void Draw(Graphics g, JPanel jp)
  {}

public boolean KeyUp(KeyEvent e) 
  {
	if (e.getKeyCode() == e.VK_RIGHT) 
	  {
	     SetRoom(room.rightRoom);
	     return true;
	  }
	if (e.getKeyCode() == e.VK_LEFT) 
	  {
	     SetRoom(room.leftRoom);
	     return true;
	  }
	if (e.getKeyCode() == e.VK_UP) 
	  {
	     SetRoom(room.upRoom);
	     return true;
	  }
	if (e.getKeyCode() == e.VK_DOWN) 
	  {
	     SetRoom(room.downRoom);
	     return true;
	  }
	if (e.getKeyCode() == e.VK_SPACE) 
	  {
	     level.player=level.gameCursor;
	     level.currentViewer=level.player;
	     for (int a=5; a<60; a++)
	       {
		  Room r = (Room) level.rooms.elementAt(a);
		  TextBox tb = (TextBox) r.textBoxes.elementAt(0);
		  tb.y += 500;
	       }
	     return false;
	  }
	return false;
  }

}