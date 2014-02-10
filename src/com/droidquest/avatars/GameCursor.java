package com.droidquest.avatars;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.droidquest.Room;
import com.droidquest.devices.Device;
import com.droidquest.devices.GenericChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

public class GameCursor extends Item 
{
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
		if (e.getKeyCode() == e.VK_L) 
		{
			if (carrying != null)
				if (carrying.getClass().toString().endsWith("SmallChip"))
				{
                    JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(e.getComponent());
					FileDialog fd = new FileDialog(parent,"Load Chip", FileDialog.LOAD);
					fd.setDirectory("chips");
					fd.show();
					System.out.println("Dialog returned with " 
							+ fd.getDirectory()
							+ fd.getFile());
					if (fd.getFile() != null)
					{
						((SmallChip)carrying).Empty();
						((SmallChip)carrying).LoadChip(fd.getDirectory()+fd.getFile());
					}
				}
		}
		if (e.getKeyCode() == e.VK_S) 
		{
            getOperationFactory().createSolderingPenOperation(this).execute();
		}
		if (e.getKeyCode() == e.VK_R) 
		{
			if (level.remote == null) return false;
			if (level.remote.carriedBy == null)
			{ // Summon Remote
				level.remote.x = 28;
				level.remote.y = -20;
				level.remote.carriedBy = level.player;
				level.remote.room = level.player.room;
				level.electricity = true;
			}
			else
			{ // Hide Remote
				level.remote.carriedBy = null;
				level.remote.room = null;
				level.electricity = false;
			}
			//	     if (carrying != null)
			//	       Drops();
			//	     level.remote.x = x;
			//	     level.remote.y = y;
			//	     level.remote.room = room;
			//	     room = null;
			//	     if (level.currentViewer == level.player)
			//	       level.currentViewer=level.remote;
			//	     level.player = level.remote;
		}
		if (e.getKeyCode() == e.VK_T) 
		{
			if (level.toolbox == null)
			{
				if (carrying != null)
					Drops();
				level.toolbox = new ToolBox(x,y+8,room);
				level.items.addElement(level.toolbox);
				((ToolBox)level.toolbox).Toggle();
				PicksUp(level.toolbox);
			}
			if (level.toolbox.room != room)
			{
				// Summon Toolbox
				if (carrying != null) return false;
				if (((ToolBox)level.toolbox).open) ((ToolBox)level.toolbox).Toggle();
				level.toolbox.room = room;
				level.toolbox.x = x+28;
				level.toolbox.y = y+6;
				PicksUp(level.toolbox);
			}
			else
				((ToolBox)level.toolbox).Toggle();
		}
		if (e.getKeyCode() == e.VK_SLASH) 
		{
			if (carrying != null)
				if (carrying.getClass().toString().endsWith("Chip"))
				{
					((GenericChip)carrying).ShowText(true);
					return false;
				}
			if (level.helpCam == null) return false;
			level.player = level.helpCam;
			level.currentViewer = level.helpCam;
		}
		if (e.getKeyCode() == e.VK_RIGHT) 
		{
			if (level.cheatmode)
				if (e.isShiftDown())
					SetRoom(room.rightRoom);
			if (carriedBy==null)
				MoveRight(e.isControlDown());
			repeating=0;
			return true;
		}
		if (e.getKeyCode() == e.VK_LEFT) 
		{
			if (level.cheatmode)
				if (e.isShiftDown())
					SetRoom(room.leftRoom);
			if (carriedBy==null)
				MoveLeft(e.isControlDown());
			repeating=0;
			return true;
		}
		if (e.getKeyCode() == e.VK_UP) 
		{
			if (level.cheatmode)
				if (e.isShiftDown())
					SetRoom(room.upRoom);
			if (carriedBy==null)
				MoveUp(e.isControlDown());
			repeating=0;
			return true;
		}
		if (e.getKeyCode() == e.VK_DOWN) 
		{
			if (level.cheatmode)
				if (e.isShiftDown())
					SetRoom(room.downRoom);
			if (carriedBy==null)
				MoveDown(e.isControlDown());
			repeating=0;
			return true;
		}
		if (e.getKeyCode() == e.VK_SPACE) 
		{
			{
				Item item = level.FindNearestItem(level.gameCursor);
				if (item!=null)
					if (item.getClass().toString().endsWith("Train"))
					{
						item.CanBePickedUp(this);
						return false;
					}
			}

			if (carrying != null)
				Drops();
			else
			{
				Item item = level.FindNearestItem(level.gameCursor);
				if (item != null)
					if (item.CanBePickedUp(level.gameCursor))
						PicksUp(item);
			}
			outline = null;
			return false;
		}
		if (e.getKeyCode() == e.VK_CLOSE_BRACKET) 
		{
			if (carrying !=null)
				if (carrying.isDevice())
					((Device) carrying).rotate(1);
			return false;
		}
		if (e.getKeyCode() == e.VK_OPEN_BRACKET) 
		{
			if (carrying !=null)
				if (carrying.isDevice())
					((Device) carrying).rotate(-1);
			return false;
		}
		if (e.getKeyCode() == e.VK_E) 
		{
			boolean found=false;
			Item item = level.FindNearestItem(this);
			if (item!=null)
				if (item.InternalRoom!=null)
					if (Overlaps(item))
						//		 if (x>=item.x && y>=item.y 
						//		     && x+width <= item.x + item.width
						//		     && y+height <= item.y + item.height)
						if (!item.OverWall())
						{
							int newX = 280; // 10 * 28
							int newY = 176; // 5.5 * 32
							x = newX;
							y = newY;
							SetRoom(item.InternalRoom);
							found=true;
						}
		}
		if (e.getKeyCode() == e.VK_X) 
		{
			if (room.portalItem!=null)
			{
				Dimension d = room.portalItem.GetXY();
				int newX = d.width
						+ room.portalItem.getWidth()/2 
						- width/2;
				int newY = d.height
						+ room.portalItem.getHeight()/4*2 
						- height/2;
				x = newX;
				y = newY;
				SetRoom(room.portalItem.room);
				level.currentViewer = level.player;
			}
		}
		if (e.getKeyCode() == e.VK_F) 
		{
			if (carrying != null)
				if (carrying instanceof Device)
					((Device)carrying).flip();
		}
		if (e.getKeyCode() == e.VK_M) 
		{
			Runtime runtime = Runtime.getRuntime();
			long freemem = runtime.freeMemory();
			long totalmem = runtime.totalMemory();
			System.out.println("Total Memory = "+ totalmem
					+ ", (" + totalmem/1024 + "K), ("
					+ totalmem/1024/1024 + "M)");
			System.out.println("Free Memory = "+ freemem
					+ ", (" + freemem/1024 + "K), ("
					+ freemem/1024/1024 + "M)");
		}

		return false;
	}

    public boolean KeyDown(KeyEvent e)
	{
		if (e.getKeyCode() == e.VK_RIGHT) 
		{
			repeating++;
			if (repeating>5)
			{
				if (carriedBy==null)
					MoveRight(e.isControlDown());
				return true;
			}
			return false;
		}
		if (e.getKeyCode() == e.VK_LEFT) 
		{
			repeating++;
			if (repeating>5)
			{
				if (carriedBy==null)
					MoveLeft(e.isControlDown());
				return true;
			}
			return false;
		}
		if (e.getKeyCode() == e.VK_UP) 
		{
			repeating++;
			if (repeating>5)
			{
				if (carriedBy==null)
					MoveUp(e.isControlDown());
				return true;
			}
			return false;
		}
		if (e.getKeyCode() == e.VK_DOWN) 
		{
			repeating++;
			if (repeating>5)
			{
				if (carriedBy==null)
					MoveDown(e.isControlDown());
				return true;
			}
			return false;
		}
		if (e.getKeyCode() == e.VK_SPACE) 
		{
			if (level.player == level.gameCursor)
				outline = Color.white;
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
