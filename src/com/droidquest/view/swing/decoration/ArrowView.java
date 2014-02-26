package com.droidquest.view.swing.decoration;

import java.awt.Graphics;

import com.droidquest.decorations.Arrow;

/**
 * Swing Renderer for Arrows.
 */
public class ArrowView {

    public void draw(Graphics g, Arrow arrow) /*{{{*/ {
        g.setColor(arrow.getColor());
        switch (arrow.getDirection()) {
            case Arrow.DIR_UP:
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() - 8, arrow.getY() + 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() + 8, arrow.getY() + 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX(), arrow.getY() + arrow.getLength());
                break;
            case Arrow.DIR_RIGHT:
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() - 8, arrow.getY() - 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() - 8, arrow.getY() + 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() - arrow.getLength(), arrow.getY());
                break;
            case Arrow.DIR_DOWN:
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() - 8, arrow.getY() - 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() + 8, arrow.getY() - 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX(), arrow.getY() - arrow.getLength());
                break;
            case Arrow.DIR_LEFT:
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() + 8, arrow.getY() - 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() + 8, arrow.getY() + 8);
                g.drawLine(arrow.getX(), arrow.getY(), arrow.getX() + arrow.getLength(), arrow.getY());
                break;
        }
    }/*}}}*/
}
