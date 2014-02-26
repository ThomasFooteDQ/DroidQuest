package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.PanicButton;

public class PanicButtonView extends AbstractMultiImageMaterialView<PanicButton> {

    public PanicButtonView() {
        addImage(true, createImage(Color.blue));
        addImage(false, createImage(new Color(255, 128, 0)));
    }

    private Image createImage(Color color) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(color);
        g.fillRect(8, 0, 12, 26);
        g.fillRect(4, 2, 20, 22);
        g.fillRect(0, 4, 28, 18);
        g.setColor(Color.black);
        g.fillRect(8, 6, 12, 14);
        g.fillRect(4, 8, 20, 10);
        g.setColor(color);
        g.fillRect(12, 8, 4, 10);
        g.fillRect(8, 10, 12, 6);

        return image;
    }

    @Override
    protected Object getImageKey(PanicButton material) {
        return material.getState();
    }
}
