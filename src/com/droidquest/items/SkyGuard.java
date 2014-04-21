package com.droidquest.items;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class SkyGuard extends Item {
    private int animationState = 0;
    private int speed;

    public SkyGuard(int X, int Y, Room r, int s) {
        x = X;
        y = Y;
        room = r;
        speed = s;
        width = 28;
        height = 32;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[5];
        for (int a = 0; a < 5; a++) {
            icons[a] = new ImageIcon("images/skyguard" + a + ".gif");
        }
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        animationState = 1 - animationState;

        if (speed > 0) {
            if (speed + x < 420) {
                moveRight(speed);
                currentIcon = icons[animationState].getImage();
            }
            else {
                speed = -speed;
                currentIcon = icons[2].getImage();
            }
        }
        else if (speed < 0) {
            if (speed + x > 112) {
                moveLeft(-speed);
                currentIcon = icons[3 + animationState].getImage();
            }
            else {
                speed = -speed;
                currentIcon = icons[2].getImage();
            }
        }

    }

}
