package com.droidquest;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.droidquest.avatars.LabCursor;
import com.droidquest.levels.Level;
import com.droidquest.view.swing.ControlPanel;
import com.droidquest.view.swing.SwingView;
import com.droidquest.view.swing.room.RoomPanel;

public class RoomDisplay extends JPanel implements SwingView
{
    private static final String ENABLE_CONTROL_PANEL = "enableControlPanel";

    private final Game game;
    private final ControlPanel controlPanel;
    private Timer timer;
	private int timerspeed=128;
    private final RoomPanel roomPanel;

    public RoomDisplay(final Game game)
	{
        this.game = game;

        roomPanel = new RoomPanel(game);
        controlPanel = new ControlPanel();

        setLayout(new BorderLayout());
        add(roomPanel, BorderLayout.CENTER);
        if (System.getProperty(ENABLE_CONTROL_PANEL) != null) {
            add(controlPanel, BorderLayout.EAST);
        }

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
        roomPanel.repaint();
        controlPanel.refresh();
    }

    /**
     * Takes a point in the swing coordinate space and returns the equivalent point in the game coordinate space.
     *
     * @param swingCoordPoint a point in the swing coordinate space
     *
     * @return an equivalent point in the game's coordinate space
     */
    @Override
    public Point toGameCoordSpace(final Point swingCoordPoint) {
        return roomPanel.toGameCoordSpace(swingCoordPoint);
    }

    public boolean isFocusable()
    {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return(true);
    }

    @Override
    public JComponent getViewPanel() {
        return this;
    }

    @Override
    public JComponent getRoomPanel() {
        return roomPanel;
    }

    @Override
    public JComponent getControlPanel() {
        return controlPanel;
    }

    private Level getLevel() {
        return game.getCurrentLevel();
    }
}
