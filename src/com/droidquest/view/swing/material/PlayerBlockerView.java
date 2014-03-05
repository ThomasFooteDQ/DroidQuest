package com.droidquest.view.swing.material;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.droidquest.materials.PlayerBlocker;

public class PlayerBlockerView implements MaterialView<PlayerBlocker> {
    private Map<String, Image> images = new HashMap<String, Image>();

    @Override
    public void draw(Graphics g, PlayerBlocker material, int x, int y) {
        final Image image = getImage(material);
        if (image != null) {
            g.drawImage(image, x * 28, y * 32, null);
        } else {
            g.setColor(material.getColor());
            g.fillRect(x * 28, y * 32, 28, 32);
        }
    }

    protected Image getImage(PlayerBlocker blocker) {
        String filename = blocker.getFilename(blocker.getAnimationState());
        if (filename == null) {
            return null;
        }

        Image image = images.get(filename);
        if (image == null) {
            image = loadImage(filename);
            images.put(filename, image);
        }
        return image;
    }

    private Image loadImage(String filename) {
        ImageIcon icon = new ImageIcon(getClass().getResource("images/" + filename));

        return icon.getImage();
    }
}
