package com.droidquest.view.swing.material;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.droidquest.materials.Material;

/**
 * Abstract base class for creating renderers for materials that switch between multiple images.
 * @param <M> the Material class being rendered
 */
public abstract class AbstractMultiImageMaterialView<M extends Material> extends MaterialImageView<M> {
    private final Map<Object, Image> imageMap = new HashMap<Object, Image>();

    protected void addImage(Object key, Image image) {
        imageMap.put(key, image);
    }

    @Override
    protected Image getImage(M material) {
        return imageMap.get(getImageKey(material));
    }

    protected abstract Object getImageKey(M material);
}
