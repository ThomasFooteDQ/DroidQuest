package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.ForceField;

/**
 * Custom Force Field renderer.
 */
public class ForceFieldView extends MaterialImageView<ForceField> {
    @Override
    protected Image getImage(ForceField forceField) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(forceField.getColor());
        g.fillRect(12, 0, 4, 32);

        return image;
    }
}
