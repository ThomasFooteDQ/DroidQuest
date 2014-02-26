package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.droidquest.materials.BatteryOut;

/**
 * Custom renderer that renderers the battery level gauge in a robot.
 */
public class BatteryOutView extends MaterialImageView<BatteryOut> implements MaterialView<BatteryOut> {

    @Override
    protected Image getImage(final BatteryOut batteryOut) {
        Image image = createBaseImage();

        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(12, 0, 6, 32);
        g.setColor(new Color(255, 128, 0));
        if (batteryOut.getRobot() != null) {
            int fuel = (batteryOut.getRobot().charge + 1564) / 3125; // 3125 = 100,000/32
            g.fillRect(12, 32 - fuel, 6, fuel);
        }

        return image;
    }

    private Image createBaseImage() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bi.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(4, 0, 8, 32);
        g.fillRect(18, 0, 10, 32);
        g.fillRect(0, 8, 4, 4);
        g.fillRect(0, 20, 4, 4);

        return bi;
    }
}
