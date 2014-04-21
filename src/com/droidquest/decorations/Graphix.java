package com.droidquest.decorations;

import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;

import com.droidquest.RoomDisplay;

public class Graphix implements Serializable {
    public String[] filenames;
    private transient ImageIcon[] icons; // Array of images for this item
    private transient ImageIcon icon;
    private int animationState;
    public int x; // Current position
    private int y;
    private int behavior; // Overall behavior (SIT, CYCLE, BOUNCE)
    private int current = 1; // Current behavior; 1=move forward, -1=move backward
    private int dx;
    private int dy; // Direction of "forward"
    public int count;
    private int length; // Number of times the Graphix moves forward
    private static int SIT = 0;
    public static int CYCLE = 1;
    public static int BOUNCE = 2;

    public Graphix() {
    }

    public Graphix(int X, int Y) {
        x = X;
        y = Y;
        GenerateIcons();
    }

    public Graphix(String file, int X, int Y) {
        filenames = new String[1];
        filenames[0] = file;
        behavior = SIT;
        x = X;
        y = Y;
        GenerateIcons();
    }

    public Graphix(String[] files, int X, int Y) {
        x = X;
        y = Y;
        filenames = files;
        behavior = SIT;
        GenerateIcons();
    }

    public Graphix(String file, int X, int Y, int b, int DX, int DY, int L) {
        filenames = new String[1];
        filenames[0] = file;
        x = X;
        y = Y;
        behavior = b;
        dx = DX;
        dy = DY;
        length = L;
        GenerateIcons();
    }

    public Graphix(String[] files, int X, int Y, int b, int DX, int DY, int L) {
        filenames = files;
        x = X;
        y = Y;
        behavior = b;
        dx = DX;
        dy = DY;
        length = L;
        GenerateIcons();
    }

    public void GenerateIcons() {
        int numfiles = filenames.length;
        icons = new ImageIcon[numfiles];
        for (int a = 0; a < numfiles; a++) {
            icons[a] = new ImageIcon("images/" + filenames[a]);
        }
        icon = icons[0];
    }

    public void Draw(Graphics g, RoomDisplay rd) {
        if (icon != null) {
            g.drawImage(icon.getImage(), x, y, rd);
        }
    }

    public void Animate() {
        int numfiles = filenames.length;
        animationState++;
        if (animationState == numfiles) {
            animationState = 0;
        }
        icon = icons[animationState];
        if (behavior == CYCLE) {
            if (count == length) {
                x -= dx * length;
                y -= dy * length;
                count = 0;
            }
            else {
                x += dx;
                y += dy;
                count++;
            }
        }
        if (behavior == BOUNCE) {
            if (current == 1) {
                if (count == length) {
                    current = -1;
                }
                else {
                    x += dx;
                    y += dy;
                    count++;
                }
            }
            else {
                if (count == 0) {
                    current = 1;
                }
                else {
                    x -= dx;
                    y -= dy;
                    count--;
                }
            }
        }
    }

}