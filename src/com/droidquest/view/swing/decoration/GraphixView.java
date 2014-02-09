package com.droidquest.view.swing.decoration;

import java.awt.Graphics;

import com.droidquest.decorations.Graphix;

/**
 * Swing Renderer class for painting Graphix elements
 */
public class GraphixView {

    public void draw(Graphics g, Graphix graphix) /*{{{*/ {
        if (graphix.getIcon() != null) {
            g.drawImage(graphix.getIcon().getImage(), graphix.getX(), graphix.getY(), null);
        }
    }/*}}}*/
}
