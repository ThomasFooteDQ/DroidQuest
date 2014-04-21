package com.droidquest.items;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.droidquest.Room;

public class HiddenCamera extends Item {
    public HiddenCamera(Room r) {
        x = 0;
        y = 0;
        room = r;
        width = 0;
        height = 0;
        grabbable = false;
    }

    public void Draw(Graphics g, JPanel jp) {
    }

}
