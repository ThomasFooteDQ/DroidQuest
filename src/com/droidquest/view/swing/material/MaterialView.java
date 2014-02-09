package com.droidquest.view.swing.material;

import java.awt.Graphics;

import com.droidquest.materials.Material;

/**
 * Swing MaterialView interface.  MaterialViews paint materials on the canvas.
 */
public interface MaterialView<M extends Material> {
    void draw(Graphics g, M material, int x, int y);
}
