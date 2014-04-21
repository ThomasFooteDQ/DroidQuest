package com.droidquest.materials;

import com.droidquest.items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Portal extends Material {
    public String levelName;
    public boolean bringStuff;
    public boolean initLevel;

    public Portal(String ln, boolean bs, boolean il) {
        super("", true, false);
        levelName = ln;
        bringStuff = bs;
        initLevel = il;
        GenerateIcons();
    }

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }

        g.setColor(Color.blue);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.white);
        g.fillRect(0, 0, 28, 2);
        g.fillRect(0, 0, 2, 32);
        icon = new ImageIcon(bi);
    }

    public void TouchedByItem(Item item) {
        if (item == level.player && level.player.carriedBy == null) {
            if (item.x % 28 == 0 && item.y % 32 == 0) {
                level.portal = this;
            }
        }
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (levelName.equals(((Portal) mat).levelName)
                    && bringStuff == ((Portal) mat).bringStuff
                    && initLevel == ((Portal) mat).initLevel) {
                return true;
            }
        }
        return false;
    }


}
