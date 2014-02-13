package com.droidquest.items;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.droidquest.Room;
import com.droidquest.decorations.TextBox;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.avatar.Direction;

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

public boolean KeyUp(KeyEvent e)
  {
    Operation op = null;
	if (e.getKeyCode() == KeyEvent.VK_RIGHT)
	  {
	     op = getOperationFactory().createSetRoomOperation(this, Direction.Right, false);
	  }
	if (e.getKeyCode() == KeyEvent.VK_LEFT)
	  {
	     op = getOperationFactory().createSetRoomOperation(this, Direction.Left, false);
	  }
	if (e.getKeyCode() == KeyEvent.VK_UP)
	  {
          op = getOperationFactory().createSetRoomOperation(this, Direction.Up, false);
	  }
	if (e.getKeyCode() == KeyEvent.VK_DOWN)
	  {
          op = getOperationFactory().createSetRoomOperation(this, Direction.Down, false);
	  }
	if (e.getKeyCode() == KeyEvent.VK_SPACE)
	  {
          op = getOperationFactory().createExitCameraOperation(this);
	  }

    if (op != null && op.canExecute()) {
        op.execute();
    }
	return false;
  }


    @Override
    protected Operation getMouseClickOperation(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            return getOperationFactory().createExitCameraOperation(this);
        }

        return super.getMouseClickOperation(e);
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