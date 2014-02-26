package com.droidquest.decorations;

import java.awt.Color;
import java.io.Serializable;

public class Arrow implements Serializable 
{
public static final int DIR_UP    = 0;
public static final int DIR_RIGHT = 1;
public static final int DIR_DOWN  = 2;
public static final int DIR_LEFT  = 3;
public int direction;
public int length;
public int x;
public int y;
public Color color;

public Arrow() {}

public Arrow(int X, int Y, int dir, int len, Color c) 
  {
	x=X; y=Y;
	direction = dir;
	length = len;
	color = c;
  }

    public Color getColor() {
        return color;
    }

    public int getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }
}
