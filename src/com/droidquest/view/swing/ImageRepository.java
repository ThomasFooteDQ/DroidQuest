package com.droidquest.view.swing;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.droidquest.view.swing.decoration.GraphixView;

/**
 * An image loader that loads images in a background thread.
 */
public class ImageRepository {
    private static final Logger LOG = LoggerFactory.getLogger(GraphixView.class);

    private final ExecutorService imageLoader;
    private final Map<String, Future<Image>> images = new HashMap<String, Future<Image>>();

    public ImageRepository() {
        imageLoader = Executors.newSingleThreadExecutor();
    }

    public Image getImage(String filename) {
        Future<Image> imageFuture = images.get(filename);
        if (imageFuture == null) {
            imageFuture = getImageLoader().submit(new ImageLoader(filename));
            images.put(filename, imageFuture);
        }

        try {
            return imageFuture.isDone() ? imageFuture.get() : null;
        } catch (Exception ex) {
            LOG.error("Image loading error", ex);
            return null;
        }
    }

    private ExecutorService getImageLoader() {
        return imageLoader;
    }
}
