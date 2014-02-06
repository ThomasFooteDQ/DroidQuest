package com.droidquest;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.droidquest.avatars.LabCursor;
import com.droidquest.decorations.Spark;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.levels.MainMenu;
import com.droidquest.materials.Material;

public class RoomDisplay extends JPanel 
{  
	public DQ dq;
	Level level;   
	public Timer timer;
	int timerspeed=128;
	public boolean useSounds = true;
	AffineTransform at = new AffineTransform();

	public Font bigFont;
	public Font smallFont;
	private int repeating=0; // Used for repeating keys

	//public boolean isFocusTraversable() 
	//  {
	// Necessary to get the keyboard focus to work with
	// the ScrenDisplay class.
	//	return(true);
	//  }

	public boolean isFocusable() 
	{
		// Necessary to get the keyboard focus to work with
		// the ScrenDisplay class.
		return(true);
	}

	public RoomDisplay()  
	{
		setSize(new Dimension(560,384));
		level = new MainMenu(this);
		getLevel().Init();
		smallFont = new Font("Courier",Font.BOLD, 20);
		bigFont = new Font("Courier",Font.BOLD, 45);
		//	setFocusable(true);
		requestFocus();

		// Resizing Fuctions
		addComponentListener(new ComponentAdapter() { 
			public void componentResized(ComponentEvent e)
			{
				Dimension d = new Dimension();
				getSize(d);
				double w = d.width  / 560.0;
				double h = d.height / 384.0;
				at.setToScale(w,h);
			}
		});	

		// Key Released Functions
		addKeyListener(new KeyAdapter() { 
			public void keyReleased(KeyEvent e) {
				// Event Handler for KeyReleased here
				if (getLevel().player.KeyUp(e))
					repaint();

				if (e.getKeyCode() == e.VK_Q)
				{
					if (timerspeed>1)
						timerspeed /= 2;
					timer.setDelay(timerspeed);
				}

				if (e.getKeyCode() == e.VK_W)
				{
					if (timerspeed<128) 
						timerspeed*=2;
					if ( (timerspeed>=128) && (getLevel().player instanceof LabCursor) )
						timerspeed*=2;
					timer.setDelay(timerspeed);
				}

			}
		});

		// Key Pressed Functions
		addKeyListener(new KeyAdapter() { 
			public void keyPressed(KeyEvent e) {
				if (getLevel().player.KeyDown(e))
					repaint();
				return;
			}
		});

		// Mouse Functions
		addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				int newX = (int) (e.getX() / at.getScaleX());
				int newY = (int) (e.getY() / at.getScaleY());
				int deltaX = newX - e.getX();
				int deltaY = newY - e.getY();
				e.translatePoint(deltaX,deltaY);
				getLevel().player.MouseClick(e);
			}
		});

        final ClockTickHandler clockTickHandler = new ClockTickHandler(this);
		timer = new Timer(timerspeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clockTickHandler.handleClockTick();
            }
        });

		Image tempImage= new BufferedImage(200,200,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = tempImage.getGraphics();
		Image tempIcon;
		ImageIcon tempImageIcon;

		for (int a = 0; a< getLevel().materials.size(); a++)
		{
			Material mat = (Material) getLevel().materials.elementAt(a);
			tempImageIcon = mat.icon;
			if (tempImageIcon != null)
				g.drawImage(tempImageIcon.getImage(), 0, 0, this);
		}

		for (int a = 0; a< getLevel().items.size(); a++)
		{
			Item itm = (Item) getLevel().items.elementAt(a);
			for (int b=0; b<itm.icons.length; b++)
			{
				tempImageIcon = itm.icons[b];
				if (tempImageIcon != null)
					g.drawImage(tempImageIcon.getImage(), 0, 0, this);
			}
		}

		timer.start();
		getLevel().PlaySound(getLevel().player.room, Level.STARTMUSICSOUND);
	}

    public void paintComponent(Graphics g)
	{
		super.paintComponents(g); // Paint background
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(at);

		// Paint Materials
		if (getLevel().currentViewer.room.MaterialArray==null)
			getLevel().currentViewer.room.GenerateArray();
		for (int y=0; y<12; y++)
			for (int x=0;x<20;x++)
				getLevel().currentViewer.room.MaterialArray[y][x].Draw(g2,this,x,y);

		// Paint Texts
		getLevel().currentViewer.room.DrawTextBoxes(g2, this);

		// Paint Graphix
		getLevel().currentViewer.room.DrawGraphix(g2,this);

		// Paint Arrows
		getLevel().currentViewer.room.DrawArrows(g2);

		// Paint Items
		for (int a = 0; a < getLevel().items.size(); a++)
			if (getLevel().currentViewer.room == ((Item) getLevel().items.elementAt(a)).room)
				((Item) getLevel().items.elementAt(a)).Draw(g2,this);

		// Paint Wires
		for (int a = 0; a< getLevel().currentViewer.room.wires.size(); a++)
			((Wire) getLevel().currentViewer.room.wires.elementAt(a)).Draw(g2);

		// Paint Sparks
		for (int a = 0; a< getLevel().sparks.size(); a++)
		{
			Spark spark = (Spark) getLevel().sparks.elementAt(a);
			if (spark.room == getLevel().currentViewer.room)
				spark.Draw(g2);
		}

		// Repaint the Current Player on top of everything else
		//	if (level.currentViewer.room == level.player.room) 
		//	  level.player.Draw(g2,this);
		//	
		//	Problem with this: You can't find the Black Crystal. This could be fixed by 
		//	putting a menu item in "Cursor always on top".

	}

	public void SaveLevel() 
	{
		String temp = getLevel().getClass().toString();
		System.out.println("Class name is " + temp);
		String[] path = temp.split("\\.");
		for (int a=0; a< path.length; a++)
			System.out.println(a + " = " + path[a]);
		//	String filename = temp.substring(6);
		String filename = path[path.length-1];
		SaveLevel(filename+".lvl");
	}

	public void SaveLevel(String filename) 
	{
		System.out.println("Saving level " + filename);
		try
		{
			FileOutputStream out = new FileOutputStream(filename);
			ObjectOutputStream s = new ObjectOutputStream(out);
			getLevel().writeObject(s);
			s.flush();
			s.close();
			out.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
			System.out.println(e.getMessage());
		}
	}

	public void LoadLevel(String filename) 
	{
		timer.stop();
		getLevel().Empty();
		level = new Level(this);
		Item.level = getLevel();
		Room.level = getLevel();
		Material.level = getLevel();

		// Add flags for loading Object inventories or running Init()
		try 
		{
			FileInputStream in = new FileInputStream(filename);
			ObjectInputStream s = new ObjectInputStream(in);
			getLevel().readObject(s);
			s.close();
			in.close();
		}	
		catch (FileNotFoundException e)
		{
			System.out.println("File Not Found");
			return;
		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}

		if (getLevel().remote != null)
		{
			if (getLevel().electricity)
			{
				getLevel().remote.x = 28;
				getLevel().remote.y = -20;
				getLevel().remote.carriedBy = getLevel().player;
				getLevel().remote.room = getLevel().player.room;
			}
			else // Electricity is off
			{
				getLevel().remote.carriedBy = null;
				getLevel().remote.room = null;
			}
		}

		timer.start();
	}

    public Level getLevel() {
        return level;
    }

}
