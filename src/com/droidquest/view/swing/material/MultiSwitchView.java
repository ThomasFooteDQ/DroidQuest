package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.MultiSwitch;

public class MultiSwitchView extends AbstractMultiImageMaterialView<MultiSwitch> {

    public MultiSwitchView() {
        addImage(0, createSwitch(Color.blue));
        addImage(1, createSwitch(Color.white));
        addImage(2, createSwitch(Color.orange));
    }

    private static Image createSwitch(Color color) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(color);
        g.fillRect(12, 4, 6, 24);
        g.fillRect(18, 12, 10, 8);

        return image;
    }

    @Override
    protected Object getImageKey(MultiSwitch material) {
        return material.getCurrentState();
    }
}
