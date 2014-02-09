package com.droidquest;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;

public class Room implements Serializable, Cloneable 
{
public transient static Level level;
public transient Room upRoom;
public transient Room downRoom;
public transient Room rightRoom;
public transient Room leftRoom;
public transient Item portalItem = null;

public int[][] RoomArray = { // Array of image references 
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
	{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
};
public transient Material[][] MaterialArray = new Material[12][20];
public Vector textBoxes = new Vector();
public Vector wires = new Vector();
public Vector graphix = new Vector();
public Vector arrows = new Vector();
public boolean editable;

public Room() 
  {
	upRoom=this;
	downRoom=this;
	rightRoom=this;
	leftRoom=this;
	editable=false;
  }

public void writeRef(ObjectOutputStream s) throws IOException 
  {
	s.writeInt(level.rooms.indexOf(upRoom));
	s.writeInt(level.rooms.indexOf(downRoom));
	s.writeInt(level.rooms.indexOf(rightRoom));
	s.writeInt(level.rooms.indexOf(leftRoom));
	s.writeInt(level.items.indexOf(portalItem));
	s.writeInt(wires.size());
	for (int a=0; a<wires.size(); a++)
	  ((Wire)wires.elementAt(a)).writeRef(s);
  }

public void readRef(ObjectInputStream s) throws IOException 
  {
	upRoom = level.FindRoom(s.readInt());
	downRoom = level.FindRoom(s.readInt());
	rightRoom = level.FindRoom(s.readInt());
	leftRoom = level.FindRoom(s.readInt());
	portalItem = level.FindItem(s.readInt());

	int numWires = s.readInt();
	wires = new Vector();
	for (int a=0; a<numWires; a++)
	  {
	     Wire wire = new Wire();
	     wires.addElement(wire);
	     wire.readRef(s,level);
	  }
	
	for (int a=0; a<graphix.size(); a++)
	  {
	     ((Graphix)graphix.elementAt(a)).GenerateIcons();
	  }
	
	GenerateArray();
  }

public void GenerateArray() 
  {
	MaterialArray = new Material[12][20];
	for (int y=0; y<12; y++)
	  for (int x=0; x<20; x++)
	    MaterialArray[y][x] = (Material) level.materials.elementAt(RoomArray[y][x]);
  }

public void SetMaterial(int X, int Y, int index) 
  {
	Material mat = (Material) level.materials.elementAt(index);
	if (mat != null)
	  {
	     RoomArray[Y][X] = index;
	     MaterialArray[Y][X] = mat;
	  }
  }

public void SetMaterial(int X, int Y, Material mat) 
  {
	int index = level.materials.indexOf(mat);
	RoomArray[Y][X] = index;
	MaterialArray[Y][X] = mat;
  }

public void SetMaterialFill(int X1, int Y1, int X2, int Y2, int index) 
  {
	Material mat = (Material) level.materials.elementAt(index);
	if (mat != null)
	  {
	     for (int Y=Y1; Y<=Y2; Y++)
	       for(int X=X1; X<=X2; X++)
		 {
		    RoomArray[Y][X] = index;
		    MaterialArray[Y][X] = mat;
		 }
	  }
  }

public void SetMaterialOutline(int X1, int Y1, int X2, int Y2, int index) 
  {
	Material mat = (Material) level.materials.elementAt(index);
	if (mat != null)
	  {
	     for (int Y=Y1; Y<=Y2; Y++)
	       {
		  RoomArray[Y][X1] = index;
		  MaterialArray[Y][X1] = mat;
		  RoomArray[Y][X2] = index;
		  MaterialArray[Y][X2] = mat;
	       }
	     
	     for(int X=X1; X<=X2; X++)
	       {
		  RoomArray[Y1][X] = index;
		  MaterialArray[Y1][X] = mat;
		  RoomArray[Y2][X] = index;
		  MaterialArray[Y2][X] = mat;
	       }
	  }
  }

public void SetMaterialFromRoom(int roomIndex) 
  {
	Room r = (Room) level.rooms.elementAt(roomIndex);
	for (int Y=0; Y<12; Y++)
	  for (int X=0; X<20; X++)
	    {
	       RoomArray[Y][X] = r.RoomArray[Y][X];
	       MaterialArray[Y][X] = r.MaterialArray[Y][X];
	    }
  }

public void AddTextBox(String t, int X, int Y, int W) 
  {
	TextBox newText = new TextBox(t,X,Y,W);
	textBoxes.addElement(newText);
  }

public void AddArrow(int X, int Y, int dir, int len, Color col) 
  {
	Arrow newArrow = new Arrow( X, Y, dir, len, col);
	arrows.addElement(newArrow);
  }

public void AddGraphix(String t, int X, int Y) 
  {
	Graphix newGraphix = new Graphix(t,X,Y);
	graphix.addElement(newGraphix);
  }

public void AddGraphix(String[] t, int X, int Y) 
  {
	Graphix newGraphix = new Graphix(t,X,Y);
	graphix.addElement(newGraphix);
  }

public Room getUpRoom(Item item)
  {
	return upRoom;
  }

public Room getDownRoom(Item item) 
  {
	return downRoom;
  }

public Room getLeftRoom(Item item) 
  {
	return leftRoom;
  }

public Room getRightRoom(Item item) 
  {
	return rightRoom;
  }

public Wire FindWire(int wireIndex) 
  {
	if (wireIndex==-1) return null;
	if (wireIndex>=wires.size()) return null;
	return (Wire) wires.elementAt(wireIndex);
  }

public Object clone() 
  {
	Object newObject = null;
	try
	  {
	     newObject = super.clone();
	  }
	catch (CloneNotSupportedException e) {}
	return newObject;
  }

public void Erase() 
  {
	upRoom = null;
	downRoom = null;
	rightRoom = null;
	leftRoom = null;
	portalItem = null;
	arrows.clear();
	graphix.clear();
	for (int a=0; a< wires.size(); a++)
	  {
	     Wire wire = (Wire) wires.elementAt(a);
	     wire.fromPort = null;
	     wire.toPort = null;
	     wire.inPort = null;
	     wire.outPort = null;
	  }
	wires.clear();
  }
    public Material getMaterial(int x, int y) {
        return MaterialArray[y][x];
    }

    public List<TextBox> getTextBoxes() {
        return textBoxes;
    }

    public List<Graphix> getGraphix() {
        return graphix;
    }

    public List<Arrow> getArrows() {
        return arrows;
    }

    public List<Wire> getWires() {
        return wires;
    }
}
