package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.MultiButton;

public class MultiButtonView extends AbstractMultiImageMaterialView<MultiButton> {

    public MultiButtonView() {
        addImage(0, createButton(Color.blue));
        addImage(1, createButton(Color.white));
        addImage(2, createButton(new Color(255, 128, 0)));
    }

    private Image createButton(Color color) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(4, 14, 20, 4);
        g.fillRect(8, 12, 12, 8);
        g.fillRect(12, 8, 4, 16);
        g.setColor(color);
        g.fillRect(12, 14, 4, 4);

        return image;
    }

    @Override
    protected Object getImageKey(MultiButton material) {
        return material.getCurrentState();
    }
}
