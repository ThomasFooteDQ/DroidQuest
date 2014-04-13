package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.decorations.Spark;

public class Turbine extends Item {
    // Turbines are wind-powered locks. They watch the level.sparks vector
    // for sparks that intersect them. When Sparks intersect the turbines
    // they animate and change materials in the cells array. When the sparks
    // stop, the animation stops and the cells are slowly replaced. The
    // direction of the Sparks don't matter in this case.
    //
    // The cells array is a straight array with each cell defined with 4 numbers;
    // X,Y, Open Material, Closed Material.
    //

    private int[] cells;
    private int animationState;
    private int cellCounter = 0;

    public Turbine(int X, int Y, Room r, int[] c) {
        x = X;
        y = Y;
        room = r;
        cells = c;
        width = 28;
        height = 64;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[4];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[2] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[3] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        for (int a = 0; a < 4; a++) {
            try {
                g = icons[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color transparent = new Color(0, 0, 0, 0);
            g2.setBackground(transparent);
            g2.clearRect(0, 0, width, height);
            g.setColor(Color.yellow);
            g.fillRect(0, 28, 28, 8);
            switch (a) {
                case 0:
                    g.fillOval(4, 4, 20, 24);
                    g.fillOval(4, 36, 20, 24);
                    break;
                case 1:
                    g.fillOval(4, 12, 20, 16);
                    g.fillOval(4, 36, 20, 16);
                    break;
                case 2:
                    g.fillOval(4, 20, 20, 8);
                    g.fillOval(4, 36, 20, 8);
                    break;
                case 3:
                    g.fillOval(4, 12, 20, 16);
                    g.fillOval(4, 36, 20, 16);
                    break;
            }
        }
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        boolean flag = false;
        for (int a = 0; a < level.sparks.size(); a++) {
            Spark spark = level.sparks.elementAt(a);
            if (spark.room == room) {
                if (spark.y >= y && spark.y <= y + height) {
                    if (spark.x >= x && spark.x <= x + width) {
                        flag = true;
                    }
                }
            }
        }
        if (flag) {
            animationState = (animationState + 1) % 4;
            currentIcon = icons[animationState].getImage();
            if (cellCounter < cells.length * 2) {
                cellCounter++;
            }
            int cell = cellCounter / 8;
            if (cell < cells.length / 4) {
                room.SetMaterial(cells[4 * cell],
                        cells[4 * cell + 1],
                        cells[4 * cell + 2]);
            }
        }
        else {
            if (cellCounter > 0) {
                cellCounter--;
            }
            int cell = cellCounter / 8;
            if (cell < cells.length / 4) {
                room.SetMaterial(cells[4 * cell],
                        cells[4 * cell + 1],
                        cells[4 * cell + 3]);
            }
        }
    }

}