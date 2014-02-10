package com.droidquest.decorations;

import java.io.Serializable;

public class Graphix implements Serializable 
{
public String[] filenames;
int animationState;
public int x; // Current position
int y;
int behavior; // Overall behavior (SIT, CYCLE, BOUNCE)
int current=1; // Current behavior; 1=move forward, -1=move backward
int dx, dy; // Direction of "forward"
public int count;
int length; // Number of times the Graphix moves forward
public static int SIT=0;
public static int CYCLE=1;
public static int BOUNCE=2;

    public Graphix() {}

public Graphix(int X, int Y) 
  {
	x=X; y=Y;
  }

public Graphix(String file, int X, int Y) 
  {
	filenames = new String[1];
	filenames[0] = new String(file);
	behavior = SIT;
	x=X; y=Y;
  }

public Graphix(String[] files, int X, int Y) 
  {
	x=X; y=Y;
	filenames = files;
	behavior = SIT;
  }

public Graphix(String file, int X, int Y, int b, int DX, int DY, int L) 
  {
	filenames = new String[1];
	filenames[0] = new String(file);
	x=X; y=Y; 
	behavior = b; 
	dx=DX; dy=DY;
	length = L;
  }

public Graphix(String[] files, int X, int Y, int b, int DX, int DY, int L) 
  {
	filenames = files;
	x=X; y=Y; 
	behavior = b; 
	dx=DX; dy=DY;
	length = L;
  }

public void Animate()
  {
	int numfiles = filenames.length;
	animationState++;
	if (animationState==numfiles)
	  animationState=0;
	if (behavior==CYCLE)
	  {
	     if (count==length)
	       {
		  x-=dx*length;
		  y-=dy*length;
		  count=0;
	       }
	     else
	       {
		  x+=dx; y+=dy;
		  count++;
	       }
	  }
	if (behavior==BOUNCE)
	  if (current==1)
	    {
	       if (count==length)
		 current=-1;
	       else
		 {
		    x+=dx; y+=dy;
		    count++;
		 }
	    }
	else
	  {
	     if (count==0)
	       current=1;
	     else
	       {
		  x-=dx; y-=dy;
		  count--;
	       }
	  }
  }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImageFilename() {
        return filenames[animationState];
    }
}