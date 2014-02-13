package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.Room;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;
import com.droidquest.operation.swing.util.DirectionUtil;

public class GameCursor extends Item {
	private int walk = 0; // 0 or 1, used in animation

	public GameCursor(int X, int Y, Room r)
	{
		x=X; y=Y; 
		room=r;
		width=28; height=32;
		GenerateIcons();
	}

	public void GenerateIcons() 
	{
		// Executed once during initialization
		icons = new ImageIcon[8];
		icons[0]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[1]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[2]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[3]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[4]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[5]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[6]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		icons[7]= new ImageIcon(new BufferedImage(28,32,BufferedImage.TYPE_4BYTE_ABGR));
		Graphics g;
		Graphics2D g2;
		Color transparent = new Color(0,0,0,0);

		// 0 = up, left leg up
		try
		{
			g = icons[0].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,20,4);
		g.fillRect(12,8,4,2);
		g.fillRect(4,10,20,2);
		g.fillRect(8,10,12,14);
		g.fillRect(0,12,4,8);
		g.fillRect(24,12,4,6);
		g.fillRect(4,22,8,8);
		g.fillRect(16,20,8,12);
		g.setColor(Color.black);
		g.fillRect(8,18,12,2);
		g.fillRect(4,26,8,2);
		g.fillRect(16,28,8,2);

		// 1 = up, right leg up
		try
		{
			g = icons[1].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,20,4);
		g.fillRect(12,8,4,2);
		g.fillRect(4,10,20,2);
		g.fillRect(8,10,12,14);
		g.fillRect(0,12,4,6);
		g.fillRect(24,12,4,8);
		g.fillRect(4,20,8,12);
		g.fillRect(16,22,8,8);
		g.setColor(Color.black);
		g.fillRect(8,18,12,2);
		g.fillRect(4,28,8,2);
		g.fillRect(16,26,8,2);

		// 2 = down, left(side) leg up	
		try
		{
			g = icons[2].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,20,4);
		g.fillRect(12,8,4,2);
		g.fillRect(4,10,20,2);
		g.fillRect(8,10,12,14);
		g.fillRect(0,12,4,8);
		g.fillRect(24,12,4,6);
		g.fillRect(4,22,8,8);
		g.fillRect(16,20,8,12);
		g.setColor(Color.black);
		g.fillRect(8,2,4,2);
		g.fillRect(16,2,4,2);
		g.fillRect(12,4,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(4,26,8,2);
		g.fillRect(16,28,8,2);

		// 3 = down, right(side) leg up
		try
		{
			g = icons[3].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,20,4);
		g.fillRect(12,8,4,2);
		g.fillRect(4,10,20,2);
		g.fillRect(8,10,12,14);
		g.fillRect(0,12,4,6);
		g.fillRect(24,12,4,8);
		g.fillRect(4,20,8,12);
		g.fillRect(16,22,8,8);
		g.setColor(Color.black);
		g.fillRect(8,2,4,2);
		g.fillRect(16,2,4,2);
		g.fillRect(12,4,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(4,28,8,2);
		g.fillRect(16,26,8,2);

		// 4 = left, Stand
		try
		{
			g = icons[4].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(20,2,4,4);
		g.fillRect(4,4,4,2);
		g.fillRect(12,8,4,2);
		g.fillRect(12,10,8,18);
		g.fillRect(8,12,4,12);
		g.fillRect(8,30,12,2);
		g.setColor(Color.black);
		g.fillRect(12,2,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(12,28,8,2);

		// 5 = left, walk
		try
		{
			g = icons[5].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(20,2,4,4);
		g.fillRect(4,4,4,2);
		g.fillRect(12,8,4,2);
		g.fillRect(8,10,12,14);
		g.fillRect(4,12,20,4);
		g.fillRect(0,14,4,4);
		g.fillRect(24,14,4,6);
		g.fillRect(4,24,8,8);
		g.fillRect(16,22,8,10);
		g.fillRect(0,30,4,2);
		g.setColor(Color.black);
		g.fillRect(12,2,4,2);
		g.fillRect(16,14,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(4,28,8,2);
		g.fillRect(16,28,8,2);

		// 6 = right, Stand
		try
		{
			g = icons[6].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,4,4);
		g.fillRect(20,4,4,2);
		g.fillRect(12,8,4,2);
		g.fillRect(8,10,8,18);
		g.fillRect(16,12,4,12);
		g.fillRect(8,30,12,2);
		g.setColor(Color.black);
		g.fillRect(12,2,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(8,28,8,2);

		// 7 = right, walk
		try
		{
			g = icons[7].getImage().getGraphics();
		}
		catch (NullPointerException e)
		{
			System.out.println("Could not get Graphics pointer to GameCursor Image");
			return;
		}
		g2 = (Graphics2D) g;
		g2.setBackground(transparent);
		g2.clearRect(0,0,28,32);
		g.setColor(Color.white);
		g.fillRect(8,0,12,8);
		g.fillRect(4,2,4,4);
		g.fillRect(20,4,4,2);
		g.fillRect(12,8,4,2);
		g.fillRect(8,10,12,14);
		g.fillRect(4,12,20,4);
		g.fillRect(0,14,4,6);
		g.fillRect(24,14,4,4);
		g.fillRect(4,22,8,10);
		g.fillRect(16,24,8,8);
		g.fillRect(24,30,4,2);
		g.setColor(Color.black);
		g.fillRect(12,2,4,2);
		g.fillRect(8,14,4,2);
		g.fillRect(8,18,12,2);
		g.fillRect(4,28,8,2);
		g.fillRect(16,28,8,2);
		currentIcon = icons[6].getImage();

	}

	public void MoveUp(boolean nudge) 
	{
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
		super.MoveUp(nudge);
		walk = 1-walk;
		currentIcon = icons[0+walk].getImage();
	}

	public void MoveDown(boolean nudge) 
	{
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
		super.MoveDown(nudge);
		walk = 1-walk;
		currentIcon = icons[2+walk].getImage();
	}

	public void MoveLeft(boolean nudge) 
	{
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
		super.MoveLeft(nudge);
		walk = 1-walk;
		currentIcon = icons[4+walk].getImage();
	}

	public void MoveRight(boolean nudge) 
	{
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
		super.MoveRight(nudge);
		walk = 1-walk;
		currentIcon = icons[6+walk].getImage();
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

		if (e.getKeyCode() == KeyEvent.VK_L)
		{
            op = getOperationFactory().createLoadSmallChipOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
            op = getOperationFactory().createSwitchToSolderingPenOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_R)
		{
            op = getOperationFactory().createToggleRemoteOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_T)
		{
            op = getOperationFactory().createToggleToolboxOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_SLASH)
		{
            op = getOperationFactory().createHelpOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
            if (e.isShiftDown()) {
                op = getOperationFactory().createSetRoomOperation(this, Direction.Right, true);
            } else {
                op = getOperationFactory().createMoveOperation(this, Direction.Right,
                        e.isControlDown() ? Distance.Nudge : Distance.Step);
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
            if (e.isShiftDown()) {
                op = getOperationFactory().createSetRoomOperation(this, Direction.Left, true);
            } else {
                op = getOperationFactory().createMoveOperation(this, Direction.Left,
                        e.isControlDown() ? Distance.Nudge : Distance.Step);
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
            if (e.isShiftDown()) {
                op = getOperationFactory().createSetRoomOperation(this, Direction.Up, true);
            } else {
                op = getOperationFactory().createMoveOperation(this, Direction.Up,
                        e.isControlDown() ? Distance.Nudge : Distance.Step);
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
            if (e.isShiftDown()) {
                op = getOperationFactory().createSetRoomOperation(this, Direction.Down, true);
            } else {
                op = getOperationFactory().createMoveOperation(this, Direction.Down,
                        e.isControlDown() ? Distance.Nudge : Distance.Step);
            }
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
            op = getOperationFactory().createPickUpItemOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET)
		{
            op = getOperationFactory().createRotateCarriedDeviceOperation(this, Rotation.Clockwise);
		}
		if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET)
		{
            op = getOperationFactory().createRotateCarriedDeviceOperation(this, Rotation.CounterClockwise);
		}
		if (e.getKeyCode() == KeyEvent.VK_E)
		{
            op = getOperationFactory().createEnterItemOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_X)
		{
            op = getOperationFactory().createExitItemOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_F)
		{
			op = getOperationFactory().createFlipCarriedDeviceOperation(this);
		}
		if (e.getKeyCode() == KeyEvent.VK_M)
		{
            op = getOperationFactory().createOutputMemoryUsageOperation();
		}

        if (op != null && op.canExecute()) {
            op.execute();
        }

        return false;
	}

    public boolean KeyDown(KeyEvent e)
	{
        Operation op = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                op = getOperationFactory().createMoveRepeatOperation(
                        this, DirectionUtil.getDirection(e.getKeyCode()), e.isControlDown() ? Distance.Nudge : Distance.Step);
                break;

            case KeyEvent.VK_SPACE:
                op = getOperationFactory().createOutlineItemOperation(this);
                break;
        }

        if (op != null && op.canExecute()) {
            op.execute();
        }
		return false;
	}

    public void Animate()
	{
		if (automove==1 && room == null)
			automove=0;
		if (automove==1)
		{
			int dx = autoX - x;
			int dy = autoY - y;
			if (dx==0 && dy==0) 
			{
				automove=0;
				return;
			}
			if (dx<-28) dx =-28;
			if (dx>28) dx=28;
			if (dy<-32) dy=-32;
			if (dy>32) dy=32;
			walk = 1-walk;
			if (dx==0)
			{
				if (dy<0)
					currentIcon = icons[0+walk].getImage();
				else
					currentIcon = icons[2+walk].getImage();
			}
			else
			{
				if (dx<0)
					currentIcon = icons[4+walk].getImage();
				else
					currentIcon = icons[6+walk].getImage();
			}
			if (dx>0) MoveRight(dx);
			if (dx<0) MoveLeft(-dx);
			if (dy>0) MoveDown(dy);
			if (dy<0) MoveUp(-dy);
		}
		if (automove==2)
		{
			walk = 1-walk;
			if (autoX>0) 
			{
				currentIcon = icons[6+walk].getImage();
				MoveRight(autoX);
			}

			if (autoX<0) 
			{
				currentIcon = icons[4+walk].getImage();
				MoveLeft(-autoX);
			}

			if (autoY>0) 
			{
				currentIcon = icons[2+walk].getImage();
				MoveDown(autoY);
			}

			if (autoY<0) 
			{
				currentIcon = icons[0+walk].getImage();
				MoveUp(-autoY);
			}
		}
	}

	public GenericRobot PlayerInRobot(GenericRobot robot) 
	{
		if (robot==null)
		{
			if (level.player.room.portalItem!=null)
			{
				if (level.player.room.portalItem.getClass().toString().endsWith("Robot"))
					return (PlayerInRobot ((GenericRobot) level.player.room.portalItem));
				else return (null);
			}
			else
				return (null);
		}
		else
			if (robot.room.portalItem != null)
			{
				if (robot.room.portalItem.getClass().toString().endsWith("Robot"))
					return (PlayerInRobot ((GenericRobot) robot.room.portalItem));
				else
					return null;
			}
			else
				return robot;
	}

}
