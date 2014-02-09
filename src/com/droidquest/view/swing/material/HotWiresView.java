package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.HotWires;

/**
 * Custom HotWires Renderer
 */
public class HotWiresView extends MaterialImageView<HotWires> {
    @Override
    protected Image getImage(HotWires material) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setBackground(Color.black);
        g.clearRect(0, 0, 28, 32);
        g.setColor(material.getValue() ? new Color(255, 128, 0) : Color.white);
        if ((material.getWall() & HotWires.UP) != 0)
            g.fillRect(13, 0, 2, 16);
        if ((material.getWall() & HotWires.DOWN) != 0)
            g.fillRect(13, 16, 2, 16);
        if ((material.getWall() & HotWires.LEFT) != 0)
            g.fillRect(0, 15, 14, 2);
        if ((material.getWall() & HotWires.RIGHT) != 0)
            g.fillRect(14, 15, 14, 2);

        return image;
    }
}
