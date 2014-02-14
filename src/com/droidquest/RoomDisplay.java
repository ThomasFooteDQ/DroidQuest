package com.droidquest;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Collection;

import com.droidquest.avatars.LabCursor;
import com.droidquest.decorations.Spark;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.view.swing.SwingView;
import com.droidquest.view.swing.room.RoomView;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class RoomDisplay extends JPanel implements SwingView
{
    private final Game game;
    private final RoomView roomView;
    private Timer timer;
	private int timerspeed=128;
	private AffineTransform at = new AffineTransform();

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
				if (e.getKeyCode() == KeyEvent.VK_Q)
				{
					if (timerspeed>1)
						timerspeed /= 2;
					timer.setDelay(timerspeed);
				}

				if (e.getKeyCode() == KeyEvent.VK_W)
				{
					if (timerspeed<128) 
						timerspeed*=2;
					if ( (timerspeed>=128) && (getLevel().getPlayer() instanceof LabCursor) )
						timerspeed*=2;
					timer.setDelay(timerspeed);
				}

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
				getLevel().getPlayer().MouseClick(e);
			}
		});

        timer = new Timer(timerspeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                game.getClockTickHandler().handleClockTick();
            }
        });
		timer.start();

		game.getSoundPlayer().play(Level.STARTMUSICSOUND);
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

        // Populate material array, if not already done
        if (getLevel().currentViewer.room.MaterialArray==null)
			getLevel().currentViewer.room.GenerateArray();

        Collection<Item> roomItems = Collections2.<Item>filter(getLevel().items, new Predicate<Item>() {
            @Override
            public boolean apply(Item item) {
                return item.getRoom() == getLevel().currentViewer.room;
            }
        });

        Collection<Spark> sparks = Collections2.<Spark>filter(getLevel().sparks, new Predicate<Spark>() {
            @Override
            public boolean apply(Spark spark) {
                return spark.getRoom() == getLevel().currentViewer.room;
            }
        });

        // Paint Room
        roomView.draw(g2, getLevel().currentViewer.room, roomItems, sparks);
	}

    public boolean isFocusable()
    {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return(true);
    }

    private Level getLevel() {
        return game.getCurrentLevel();
    }

    @Override
    public JComponent getComponent() {
        return this;
    }
}
