package com.droidquest.decorations;

import java.io.Serializable;

public class TextBox implements Serializable {
    public String textString;
    public int x; // Position
    public int y;
    public int width; // Size

    public TextBox() {
    }

    public TextBox(String t, int X, int Y, int W) {
        textString = t;
        x = X;
        y = Y;
        width = W;
    }

    public TextBox(String t, int X, int Y) {
        textString = t;
        x = X;
        y = Y;
        width = 500;
    }

}