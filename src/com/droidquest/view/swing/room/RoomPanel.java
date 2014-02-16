package com.droidquest.view.swing.room;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.Collection;

import com.droidquest.Game;
import com.droidquest.decorations.Spark;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * Panel which holds the main game (room display) view
 */
public class RoomPanel extends JPanel {
    private final Game game;
    private AffineTransform at = new AffineTransform();
    private final RoomRenderer roomRenderer;

    public RoomPanel(final Game game) {
        this.game = game;

        roomRenderer = new RoomRenderer();
        setPreferredSize(new Dimension(560, 384));

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
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g); // Paint background
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(at);

        // TODO This should not be in view code
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
        roomRenderer.draw(g2, getLevel().currentViewer.room, roomItems, sparks);
    }

    public boolean isFocusable()
    {
        // Necessary to get the keyboard focus to work with
        // the ScrenDisplay class.
        return(true);
    }

    /**
     * Takes a point in the swing coordinate space and returns the equivalent point in the game coordinate space.
     *
     * @param swingCoordPoint a point in the swing coordinate space
     *
     * @return an equivalent point in the game's coordinate space
     */
    public Point toGameCoordSpace(final Point swingCoordPoint) {
        return new Point(
                (int) (swingCoordPoint.getX() / at.getScaleX()),
                (int) (swingCoordPoint.getY() / at.getScaleY()));
    }

    private Level getLevel() {
        return game.getCurrentLevel();
    }
}
