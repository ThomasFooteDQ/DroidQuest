package com.droidquest.view.swing.decoration;

import java.awt.Graphics;
import java.awt.Image;

import com.droidquest.decorations.Graphix;
import com.droidquest.view.swing.image.ImageRepository;

/**
 * Swing Renderer class for painting Graphix elements
 */
public class GraphixView {
    private final ImageRepository imageRepository;

    public GraphixView(ImageRepository repository) {
        this.imageRepository = repository;
    }

    public void draw(Graphics g, Graphix graphix) {
        Image image = getImage(graphix);
        if (image != null) {
            g.drawImage(image, graphix.getX(), graphix.getY(), null);
        }
    }

    private Image getImage(Graphix graphix) {
        return imageRepository.getImage(graphix.getImageFilename());
    }
}
