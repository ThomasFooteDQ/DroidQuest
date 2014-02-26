package com.droidquest.materials;

import javax.swing.*;
import java.util.Date;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class MultiSwitch extends Material 
{
public int number; //0=starter, 1,2,3,4=pistons
transient ImageIcon images[];
static int[] states = {0,0,0,0,0}; //0=Blue, 1=White, 2=Orange
public Date timeout;
transient Room room=null;

    public MultiSwitch(int n, int s)
  {
	super(true, false);
	number = n;
	states[number] = s;
  }

public void TouchedByItem(Item item)
  {
	if (room==null)
	  room = item.room;
	
	if (number==0)
	  {
	     if (states[0]!=2)
	       {
		  states[0]=2;
		  states[1]=1;
		  states[2]=1;
		  states[3]=1;
		  states[4]=1;
		  timeout = new Date(new Date().getTime() + 5000);
	       }
	  }
	else
	  {
	     boolean okay = true;
	     if (states[0]<2) okay=false;
	     for (int a=1; a<number; a++)
	       if (states[a]!=2) okay=false;
	     if (okay)
	       states[number]=2;
	     else
	       {
		  states[0]=0;
		  states[1]=0;
		  states[2]=0;
		  states[3]=0;
		  states[4]=0;
	       }
	  }
  }

public void Animate() 
  {
	if (number!=0)
	  return;
	
	boolean flag = true;
	for (int a=0; a<5; a++)
	  if (states[a]!=2)
	    flag=false;
	
	if (flag)
	  {
	     for (int a=0; a<level.materials.size(); a++)
	       {
		  Material mat = (Material) level.materials.elementAt(a);
		  if (mat instanceof BinaryLock)
		    {
		       BinaryLock bl = (BinaryLock) mat;
		       if (bl.program.length==1)
			 {
			    int[][] program = {
				 {BinaryLock.NARROW},
				 {2,10,0, 3,10,0, 4,10,0, 5,10,0, 2,9,4, 3,8,4, 4,8,4, 5,9,4},
				 {2,9,0, 3,8,0, 4,8,0, 5,9,0, 1,9,4, 1,8,4, 6,9,4, 6,8,4},
				 {BinaryLock.NARROW},
				 {2,9,4, 3,8,4, 4,8,4, 5,9,4, 1,9,0, 1,8,0, 6,9,0, 6,8,0},
				 {2,10,4, 3,10,4, 4,10,4, 5,10,4, 2,9,0, 3,8,0, 4,8,0, 5,9,0}
			    };
			    bl.program = program;
			 }
		    }
	       }
	  }
	
	if (states[0]==2)
	  {
	     Date now = new Date();
	     if (now.getTime() > timeout.getTime())
	       {
		  states[0]=0;
		  states[1]=0;
		  states[2]=0;
		  states[3]=0;
		  states[4]=0;
	       }
	  }
	
  }

public boolean equals(Material mat) 
  {
	if (super.equals(mat))
	  if (number == ((MultiSwitch)mat).number)
	    return true;
	return false;
  }

    public int getCurrentState() {
        return states[number];
    }
}
