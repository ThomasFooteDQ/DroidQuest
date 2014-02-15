package com.droidquest.items;

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
    setVisible(false);
  }

public void exitCamera() {
    level.setPlayer(level.gameCursor);
    level.currentViewer= getPlayer();
    for (int a=5; a<60; a++)
      {
     Room r = (Room) level.rooms.elementAt(a);
     TextBox tb = (TextBox) r.textBoxes.elementAt(0);
     tb.y += 500;
      }
}

}