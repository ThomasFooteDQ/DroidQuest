package com.droidquest.view.swing.image;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Swing loader that loads a BufferedImage from an image filename.
 */
public class ImageLoader implements Callable<Image> {
    private final String filename;

    public ImageLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public Image call() throws Exception {
        try {
            return ImageIO.read(new File("images" + System.getProperty("file.separator") + filename));
        } catch (IOException e) {
        }

        return null;
    }
}
