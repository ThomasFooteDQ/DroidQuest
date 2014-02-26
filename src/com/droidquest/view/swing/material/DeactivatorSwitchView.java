package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.DeactivatorSwitch;

/**
 * Custom Material Renderer for the DeactivatorSwitch.
 */
public class DeactivatorSwitchView extends MaterialImageView<DeactivatorSwitch> {

    private final Image whiteSwitch;
    private final Image orangeSwitch;

    public DeactivatorSwitchView() {
        whiteSwitch = createSwitchImage(Color.white);
        orangeSwitch = createSwitchImage(new Color(255, 128, 0));
    }

    private Image createSwitchImage(Color color) {
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
    protected Image getImage(DeactivatorSwitch material) {
        return material.getValue() ? orangeSwitch : whiteSwitch;
    }
}
