package com.droidquest.decorations;

import java.io.Serializable;

import com.droidquest.Room;

public class Spark implements Serializable 
{
	public int x,y;
	private int dx,dy;
	public int age;
	public Room room;

	public Spark(int X, int Y, int Dx, int Dy, Room r)
	{
		x=X; y=Y; 
		dx = Dx; dy= Dy;
		room = r;
		age=0;
	}

	public void animate()
	{
		x += dx; y+= dy;
		if (x<0 || x>560 || y<0 || y>384) room=null;
		age++;
	}

    public int getAge() {
        return age;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Room getRoom() {
        return room;
    }
}
