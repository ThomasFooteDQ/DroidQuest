package com.droidquest.decorations;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import com.droidquest.Room;

public class Spark implements Serializable {
    public int x, y;
    private int dx;
    private int dy;
    public int age;
    public Room room;


    public Spark(int X, int Y, int Dx, int Dy, Room r) {
        x = X;
        y = Y;
        dx = Dx;
        dy = Dy;
        room = r;
        age = 0;
    }

    public void Age() {
        x += dx;
        y += dy;
        if (x < 0 || x > 560 || y < 0 || y > 384) {
            room = null;
        }
        age++;
    }

    public void Draw(Graphics g) {
        if (age < 2) {
            g.setColor(Color.white);
        }
        else if (age >= 2 && age < 4) {
            g.setColor(Color.yellow);
        }
        else {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, 2, 2);
    }

}
