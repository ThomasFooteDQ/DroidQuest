package com.droidquest.view.swing.material;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Static image repository for Materials.
 */
public class MaterialImages {
    public static Image createBatteryInImage() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bi.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);
        g.fillRect(8, 8, 12, 16);
        g.fillRect(4, 10, 20, 12);
        g.fillRect(0, 14, 28, 4);
        g.fillRect(0, 8, 4, 2);
        g.fillRect(0, 22, 4, 2);
        g.fillRect(24, 8, 4, 2);
        g.fillRect(24, 22, 4, 2);

        return bi;
    }

    public static Image createBinaryLockImage(Color color) {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(16, 2, 4, 8);
        g.fillRect(16, 12, 4, 2);
        g.fillRect(12, 14, 4, 4);
        g.fillRect(20, 14, 4, 4);
        g.fillRect(16, 18, 4, 2);
        g.fillRect(16, 22, 4, 8);
        g.fillRect(24, 4, 4, 4);
        g.fillRect(24, 24, 4, 4);

        return image;
    }

    public static Image createCameraDisableImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);

        return image;
    }

    public static Image createCameraEnableImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 9, 12, 2);
        g.fillRect(8, 21, 12, 2);
        g.fillRect(4, 11, 4, 2);
        g.fillRect(4, 19, 4, 2);
        g.fillRect(20, 11, 4, 2);
        g.fillRect(20, 19, 4, 2);
        g.fillRect(0, 13, 4, 6);
        g.fillRect(24, 13, 4, 6);
        g.fillRect(8, 13, 12, 6);

        return image;
    }

    public static Image createCoinSlotImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);

        return image;
    }

    public static Image createCrystalRechargerImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(new Color(255, 128, 0));
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);
        g.fillRect(8, 8, 12, 16);
        g.fillRect(4, 10, 20, 12);
        g.fillRect(0, 14, 28, 4);
        g.fillRect(0, 8, 4, 2);
        g.fillRect(0, 22, 4, 2);
        g.fillRect(24, 8, 4, 2);
        g.fillRect(24, 22, 4, 2);

        return image;
    }

    public static Image createElevatorInPortalImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(10, 4, 8, 2);
        g.fillRect(10, 8, 8, 2);
        g.fillRect(10, 12, 8, 2);
        g.fillRect(10, 16, 8, 2);
        g.fillRect(10, 20, 8, 2);
        g.fillRect(10, 24, 8, 2);
        g.fillRect(10, 28, 8, 2);

        return image;
    }

    public static Image createElevatorLockImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(4, 4, 4, 2);
        g.fillRect(12, 4, 4, 2);
        g.fillRect(4, 8, 4, 2);
        g.fillRect(20, 8, 4, 2);
        g.fillRect(20, 26, 4, 2);
        g.fillRect(4, 14, 12, 4);
        g.fillRect(4, 22, 12, 4);
        g.fillRect(20, 12, 4, 12);

        return image;
    }

    public static Image createPeriscopeDownImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);

        return image;
    }

    public static Image createPeriscopeUpImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(8, 9, 12, 2);
        g.fillRect(8, 21, 12, 2);
        g.fillRect(4, 11, 4, 2);
        g.fillRect(4, 19, 4, 2);
        g.fillRect(20, 11, 4, 2);
        g.fillRect(20, 19, 4, 2);
        g.fillRect(0, 13, 4, 6);
        g.fillRect(24, 13, 4, 6);
        g.fillRect(8, 13, 12, 6);

        return image;
    }

    public static Image createPortalImage() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = bi.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(0, 0, 28, 2);
        g.fillRect(0, 0, 2, 32);

        return bi;
    }

    public static Image createVendingSlotImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 128));
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(0, 24, 28, 4);

        return image;
    }

    public static Image createXitSlotImage() {
        BufferedImage image = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(12, 4, 4, 24);

        return image;
    }
}
