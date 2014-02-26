package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.Lock;

/**
 * Custom Lock Renderer
 */
public class LockView extends MaterialImageView<Lock> {
    @Override
    protected Image getImage(Lock material) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(material.getColor());
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(0, 14, 16, 2);
        g.fillRect(0, 16, 12, 2);
        g.fillRect(2, 18, 6, 2);

        return image;
    }
}
