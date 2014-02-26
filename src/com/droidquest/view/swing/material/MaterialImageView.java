package com.droidquest.view.swing.material;

import java.awt.Graphics;
import java.awt.Image;

import com.droidquest.materials.Material;

/**
 * Swing renderer that paints Materials using an ImageIcon.
 */
public class MaterialImageView<M extends Material> implements MaterialView<M> {
    private final Image image;

    public MaterialImageView(final Image image) {
        this.image = image;
    }

    protected MaterialImageView() {
        this(null);
    }

    @Override
    public void draw(Graphics g, M material, int x, int y) {
        g.drawImage(getImage(material), x * 28, y * 32, null);
    }

    protected Image getImage(M material) {
        return image;
    }
}
