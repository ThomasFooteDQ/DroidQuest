package com.droidquest.materials;

import java.util.Date;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class MultiButton extends Material 
{
public int number; //0=starter, 1,2,3,4,5,6,7,8,9 = buttons
static int[] states = new int[10]; //0=Blue, 1=White, 2=Orange
public Date timeout;
transient Room room=null;

    public MultiButton(int n, int s)
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
	     if (states[0]==1)
	       {
		  states[0]=2;
		  for (int a=1; a<10; a++)
		    states[a]=1;
		  timeout = new Date(new Date().getTime() + 30000);
	       }
	  }
	else
	  {
	     boolean okay = true;
	     if (states[0]<2) okay=false;
	     for (int a=1; a<number; a++)
	       if (states[a]!=2) okay=false;
	     if (okay)
	       {
		  states[number]=2;
		  timeout = new Date(new Date().getTime() + 5000);
	       }
	  }
  }

public void Animate() 
  {
	if (number!=0)
	  return;
	
	if (states[0]==0)
	  {
	     states[0] = 1;
	     for (int a=1; a<10; a++)
	       states[a]=0;
	  }
	
	boolean flag = true;
	for (int a=0; a<10; a++)
	  if (states[a]!=2)
	    flag=false;
	
	if (flag)
	  {
	     Portal ptl = new Portal("ROEndGame.lvl",true,true);
	     level.materials.addElement(ptl);
	     room.SetMaterial(10,9,ptl);
	  }
	
	if (states[0]==2)
	  {
	     Date now = new Date();
	     if (now.getTime() > timeout.getTime())
	       {
		  states[0]=1;
		  for (int a=1; a<10; a++)
		    states[a]=0;
	       }
	  }
	
  }

public boolean equals(Material mat) 
  {
	if (super.equals(mat))
	  if (number == ((MultiButton)mat).number)
	    return true;
	return false;
  }

    public int getCurrentState() {
        return states[number];
    }
}
