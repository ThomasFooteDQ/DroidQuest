package com.droidquest.items;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.devices.Antenna;

public class SonicLock extends Item {
    // This works much like a regular Lock, except it locks or unlocks by watching the Radio.
    //
    // The program has either an array of binary values, or an array of X,Y,Materials.
    //
    // Binary arrays start with a -1.
    // Room Changes use other single codes.
    //
    //

    public static final int MODIFY = -1;
    public static final int BINARY = -2;
    private static final int RESET = -3;
    private static final int LEFT = -4;
    private static final int RIGHT = -5;
    private static final int UP = -6;
    private static final int DOWN = -7;
    private int[][] program;
    private int doorState = 0;
    private int radioState = 1;
    private int animationState = 1;
    private Room currentRoom;

    public SonicLock(int X, int Y, Room r, int[][] p) {
        x = X;
        y = Y;
        room = r;
        program = p;
        width = 28;
        height = 30;
        grabbable = false;
        currentRoom = room;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        for (int a = 0; a < 2; a++) {
            Graphics g;
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
            switch (a) {
                case 0:
                    g.setColor(Color.white);
                    break;
                case 1:
                    g.setColor(new Color(255, 128, 0));
                    break;
            }
            g.fillRect(0, 14, 28, 2);
            g.fillRect(4, 12, 4, 6);
            g.fillRect(8, 10, 4, 2);
            g.fillRect(8, 18, 4, 2);
            g.fillRect(12, 8, 4, 2);
            g.fillRect(12, 20, 4, 2);
            g.fillRect(12, 0, 4, 6);
            g.fillRect(12, 24, 4, 6);
            g.fillRect(16, 2, 4, 26);
            g.fillRect(20, 6, 4, 18);
            g.fillRect(24, 12, 4, 6);
        }
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        if (doorState == program.length) {
            doorState = 0;
        }

        if (program[doorState][0] == BINARY) {
            animationState++;
            if (animationState == program[doorState].length) {
                animationState = 1;
            }
            currentIcon = icons[program[doorState][animationState]].getImage();
        }

        switch (program[doorState][0]) {
            case MODIFY: {
                for (int a = 0; a < program[doorState].length / 3; a++) {
                    currentRoom.SetMaterial(program[doorState][a * 3 + 1],
                            program[doorState][a * 3 + 2],
                            program[doorState][a * 3 + 3]);
                }
                doorState++;
                radioState = 1;
            }
            break;

            case BINARY: {
                currentRoom = room;
                boolean radio = (Antenna.radio > 0);
                boolean bit = (program[doorState][radioState] == 1);
                if (radio == bit) {
                    radioState++;
                    if (radioState == program[doorState].length) {
                        doorState++;
                    }
                }
                else {
                    radioState = 1;
                }
            }
            break;

            case RESET:
                currentRoom = room;
                break;

            case LEFT:
                currentRoom = currentRoom.leftRoom;
                break;

            case RIGHT:
                currentRoom = currentRoom.rightRoom;
                break;

            case UP:
                currentRoom = currentRoom.upRoom;
                break;

            case DOWN:
                currentRoom = currentRoom.downRoom;
                break;
        }

    }

}
