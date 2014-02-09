package com.droidquest.materials;

import java.awt.Dimension;
import java.util.Date;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class Switch extends Material 
{
	// Programmable wall switch, normally used for opening doors when
	// touched. Each line in the program is an array of numbers, with the
	// first explaining what function the array of numbers performs.
	// 
	// Wait4Contact
	// Wait4Removal
	// Wait4Time x
	// SetValueHigh
	// SetValueLow
	// Replace (x,y,m)...
	// LeftRoom
	// RightRoom
	// UpRoom
	// DownRoom
	// ResetRoom

	public static final int ROT_UP =0; // Rotation refers to which wall the switch is attached to
	public static final int ROT_RIGHT =1;
	public static final int ROT_DOWN =2;
	public static final int ROT_LEFT =3;
	int rotation;
	protected boolean value;

	public static final int WAIT4CONTACT =-1;
	public static final int WAIT4REMOVAL =-2;
	public static final int WAIT4TIME    =-3;
	public static final int SETVALUEHIGH =-4;
	public static final int SETVALUELOW  =-5;
	public static final int REPLACE      =-6;
	public static final int LEFTROOM     =-7;
	public static final int RIGHTROOM    =-8;
	public static final int UPROOM       =-9;
	public static final int DOWNROOM     =-10;
	public static final int RESETROOM    =-11;
	//public static final int WAIT4PLAYERCONTACT = -12;
	//If I actually add this, it'll change the signature.

	int switchState=0;
	transient Room room;
	transient Room currentRoom;
	int[][] program;
	Item trigger=null;
	Date timeout;
	boolean timing=false;

	public Switch(int rot) 
	  {
		super(true,false);
		int[][] p = {{0}};
		program = p;
		rotation = rot;
	  }

	public Switch(int rot, int[][] p) 
	  {
		super(true,false);
		program = p;
		rotation = rot;
	  }

	public boolean equals(Material mat)
	  {
		if (super.equals(mat))
		  if (rotation == ((Switch)mat).rotation
		      && value == ((Switch)mat).value
		      && program == ((Switch)mat).program)
		    return true;
		return false;
	  }

	public void TouchedByItem(Item item) 
	  {
		if (switchState == program.length)
		  switchState=0;
		
		if (program[switchState][0]==WAIT4CONTACT)
		  {
		     trigger = item;
		     room = item.room;
		     currentRoom = room;
		     switchState++;
		  }
		
		if (program[switchState][0]== -12) // == WAIT4PLAYERCONTACT
		  if (item == level.player)
		    {
		       trigger = item;
		       room = item.room;
		       currentRoom = room;
		       switchState++;
		    }
	  }

	public void Animate() 
	  {
		if (switchState == program.length)
		  switchState=0;
		
		switch (program[switchState][0])
		  {
		   case WAIT4REMOVAL: 
		       {
			  Dimension d = trigger.GetXY();
			  int bigXL = d.width / 28;
			  int bigXR = (d.width + trigger.getWidth() ) / 28;
			  int bigYT = d.height / 32;
			  int bigYB = (d.height + trigger.getHeight()) /32;
			  boolean removed = true;
			  for (int Y=bigYT; Y<bigYB; Y++)
			    for (int X=bigXL; X<bigXR; X++)
			      if (trigger.room.MaterialArray[Y][X]==this)
				removed=false;
			  if (removed)
			    switchState++;
			  currentRoom = room;
		       }
		     break;
		   case WAIT4TIME: 
		     if (timing)
		       {
			  Date now = new Date();
			  if (now.getTime() >= timeout.getTime())
			    {
			       switchState++;
			       timing = false;
			       currentRoom = room;
			    }
		       }
		     else
		       {
			  timeout = new Date(new Date().getTime() + 1000*program[switchState][1]);
			  timing = true;
		       }
		     break;
		   case SETVALUEHIGH: 
		     value=true;
		     switchState++;
		     break;
		   case SETVALUELOW: 
		     value=false;
		     switchState++;
		     break;
		   case REPLACE:
		     if (currentRoom == null)
		       currentRoom = trigger.room;
		     for (int a=0; a<(program[switchState].length-1)/3; a++)
		       currentRoom.SetMaterial(program[switchState][a*3+1],
					       program[switchState][a*3+2],
					       program[switchState][a*3+3]);
		     switchState++;
		     break;
		   case LEFTROOM: 
		     currentRoom = currentRoom.leftRoom;
		     switchState++;
		     break;
		   case RIGHTROOM: 
		     currentRoom = currentRoom.rightRoom;
		     switchState++;
		     break;
		   case UPROOM: 
		     currentRoom = currentRoom.upRoom;
		     switchState++;
		     break;
		   case DOWNROOM: 
		     currentRoom = currentRoom.downRoom;
		     switchState++;
		     break;
		   case RESETROOM: 
		     currentRoom = room;
		     switchState++;
		     break;
		  }
		
	  }

    public boolean getValue() {
        return value;
    }

    public int getRotation() {
        return rotation;
    }
}
