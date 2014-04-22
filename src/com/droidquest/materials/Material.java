package com.droidquest.materials;

import com.droidquest.RoomDisplay;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Material implements Serializable, Cloneable {
    public transient static Level level;
    public transient ImageIcon icon;
    private String file;
    public boolean passable;
    boolean detectable;
    Color color;

    Material() {
    }

    Material(String filename, boolean p, boolean d) {
        icon = new ImageIcon(filename);
        passable = p;
        detectable = d;
    }

    public Material(boolean p, boolean d) {
        passable = p;
        detectable = d;
        color = Color.black;
    }

    public Material(Color c, boolean p, boolean d) {
        passable = p;
        detectable = d;
        color = c;
    }

    public void GenerateIcons() {
        if (file != null) {
            icon = new ImageIcon(file);
        }
    }

    public void Draw(Graphics g, RoomDisplay rd, int x, int y) {
        if (icon == null) {
            // Blank Background
            g.setColor(color);
            g.fillRect(x * 28, y * 32, 28, 32);
        }
        else {
            // Material Background
            g.drawImage(icon.getImage(), x * 28, y * 32, rd);
        }
    }

    public void TouchedByItem(Item item) {
    }

    public void Animate() {
    }

    public boolean Passable(Item item) {
        // The PaintBrush can pass anything
        if(item instanceof PaintBrush) {
            return true;
        }
        return passable;
    }

    public boolean Detectable(Item item) {
        return detectable;
    }

    public boolean equals(Material mat) {
        return getClass() == mat.getClass()
                && color == mat.color
                && passable == mat.passable
                && detectable == mat.detectable
                && (file == null ? mat.file == null : file.equals(mat.file));
    }

    public static Material FindSimiliar(Material mat1) {
        for (int a = 0; a < level.materials.size(); a++) {
            Material mat2 = level.materials.elementAt(a);
            if (mat1.equals(mat2)) {
                return mat2;
            }
        }
        level.materials.addElement(mat1);
        return mat1;
    }

    public Object clone() {
        Object newObject = null;
        try {
            newObject = super.clone();
        }
        catch (CloneNotSupportedException e) {
        }
        return newObject;
    }

}
