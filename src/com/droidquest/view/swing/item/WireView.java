package com.droidquest.view.swing.item;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.droidquest.Wire;
import com.droidquest.chipstuff.Port;

/**
 * Spring view class which renders wires.
 */
public class WireView {
    public void draw(Graphics g, Wire wire)
    {
        g.setColor(Color.white);
        final Port fromPort = wire.getFromPort();
        final Port toPort = wire.getToPort();

        if (fromPort.getType() == Port.TYPE_OUTPUT && fromPort.getValue()) {
            g.setColor(new Color(255, 128, 0));
        }
        if (toPort.getType() == Port.TYPE_OUTPUT && toPort.getValue()) {
            g.setColor(new Color(255, 128, 0));
        }

        Dimension d1, d2;
        int x1, y1, x2, y2;
        d1 = fromPort.getOwner().GetXY();
        d2 = toPort.getOwner().GetXY();
        x1 = d1.width + fromPort.getX();
        y1 = d1.height + fromPort.getY();
        x2 = d2.width + toPort.getX();
        y2 = d2.height + toPort.getY();
        switch ((fromPort.getOwner().getRotation() + fromPort.getRotation()) % 4) {
            case 0: // Up
                x1 += 1;
                y1 += 1;
                break;
            case 1: // Right
                x1 -= 2;
                y1 += 1;
                break;
            case 2: // Down
                x1 -= 2;
                y1 -= 2;
                break;
            case 3: // Left
                x1 += 1;
                y1 -= 2;
                break;
        }
        switch ((toPort.getOwner().getRotation() + toPort.getRotation()) % 4) {
            case 0: // Up
                x2 += 1;
                y2 += 1;
                break;
            case 1: // Right
                x2 -= 2;
                y2 += 1;
                break;
            case 2: // Down
                x2 -= 2;
                y2 -= 2;
                break;
            case 3: // Left
                x2 += 1;
                y2 -= 2;
                break;
        }

        g.fillRect(Math.min(x1, x2), y1, Math.abs(x1 - x2), 2);
        g.fillRect(x2, Math.min(y1, y2), 2, Math.abs(y1 - y2));
        g.fillRect(x1, y1, 2, 2);
        g.fillRect(x2, y2, 2, 2);
        g.fillRect(x2, y1, 2, 2);
    }
}
