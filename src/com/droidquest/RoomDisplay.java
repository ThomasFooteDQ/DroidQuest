package com.droidquest;

import javax.swing.*;
import java.awt.Dimension;
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

import com.droidquest.avatars.LabCursor;
import com.droidquest.decorations.Spark;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;
import com.droidquest.view.View;
import com.droidquest.view.swing.room.RoomView;

public class RoomDisplay extends JPanel implements View
{
    private final Game game;
    private final RoomView roomView;
    private Timer timer;
	int timerspeed=128;
	AffineTransform at = new AffineTransform();

	public RoomDisplay(final Game game)
	{
        this.game = game;

		setSize(new Dimension(560, 384));
        roomView = new RoomView();
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

        timer = new Timer(timerspeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.getClockTickHandler().handleClockTick();
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

    @Override
    public void render() {
        repaint();
    }

    public void paintComponent(Graphics g)
	{
		super.paintComponents(g); // Paint background
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(at);

		// Paint Materials
		if (getLevel().currentViewer.room.MaterialArray==null)
			getLevel().currentViewer.room.GenerateArray();

        // Paint Room
        roomView.draw(g2, getLevel().currentViewer.room);

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

    public boolean isFocusable()
    {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return(true);
    }

    public Level getLevel() {
        return game.getCurrentLevel();
    }
}
