package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.droidquest.materials.Switch;

public class SwitchView implements MaterialView<Switch> {
    @Override
    public void draw(Graphics g, Switch material, int x, int y) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 28, 32);

        if (material.getValue()) {
            graphics.setColor(new Color(255, 128, 0));
        } else {
            graphics.setColor(Color.white);
        }

        switch (material.getRotation()) {
            case Switch.ROT_UP:
                graphics.fillRect(2, 10, 24, 6);
                graphics.fillRect(10, 0, 8, 10);
                break;
            case Switch.ROT_RIGHT:
                graphics.fillRect(12, 4, 6, 24);
                graphics.fillRect(18, 12, 10, 8);
                break;
            case Switch.ROT_DOWN:
                graphics.fillRect(2, 16, 24, 6);
                graphics.fillRect(10, 22, 8, 10);
                break;
            case Switch.ROT_LEFT:
                graphics.fillRect(10, 4, 6, 24);
                graphics.fillRect(0, 12, 10, 8);
                break;
        }

        g.drawImage(image, x * 20, y * 32, null);
    }
}
