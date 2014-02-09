package com.droidquest.view.swing.material;

import java.awt.Graphics;

import com.droidquest.materials.Material;

/**
 * Swing renderer for painting material tiles.
 */
public class MaterialView {
    public void draw(Graphics g, Material material, int x, int y) /*{{{*/ {
        if (material.getIcon() == null) {
            // Blank Background
            g.setColor(material.getColor());
            g.fillRect(x * 28, y * 32, 28, 32);
            return;
        } else {
            // Material Background
            g.drawImage(material.getIcon().getImage(), x * 28, y * 32, null);
            return;
        }
    }/*}}}*/

}
