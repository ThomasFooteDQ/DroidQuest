package com.droidquest.items;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.devices.Device;
import com.droidquest.levels.Level;
import com.droidquest.materials.ChipTrash;
import com.droidquest.operation.api.OperationFactory;

public class Item implements Serializable, Cloneable 
{
	public transient static Level level; 
	public transient Item carrying; // What this item is carrying. 
	public transient Item carriedBy; // What is carrying this item.
	public transient Image currentIcon; // Current image of this item.
	public transient ImageIcon[] icons; // Array of images for this item
	public transient Room room; // Room this item is currently in
	public transient Color outline; // Null, White, Grey
	public transient int automove; // 0=normal movement, 1=Move to autoX,autoY, 2=Move in autoX,autoY
	public transient int autoX; // Destination of automovement
	public transient int autoY; // Destination of automovement
	public Room InternalRoom = null; // Room inside this item, if any.

	protected int repeating=0; // Keyboard repeat.
	public int charge =0; // Battery Charge of this item, if any.
	public boolean grabbable=true; // Can this item be picked up?
	public int x,y; // Position X,Y
	protected int orgX, orgY; // origin, within graphics
	protected int width, height; // width & height of object from origin
	public Rectangle leftPortal;  
	public Rectangle rightPortal; 
	public Rectangle upPortal;    
	public Rectangle downPortal;  
	public boolean editable=false;
    private boolean visible = true;

	public Item() 
	{
		x=0; y=0;
	}

	public Item(String filename, int X, int Y, Room r) 
	{
		x=X; y=Y;
		room = r;
		grabbable = true;
		//	icons = new Image[1];
		//	icons[0]=Toolkit.getDefaultToolkit().getImage(filename);
		icons = new ImageIcon[1];
		icons[0]=new ImageIcon(filename);
		currentIcon = icons[0].getImage();
	}

	public Image getIcon()
	{
		return currentIcon;
	}

	public int getWidth() 
	{
		return width;
	}

	public int getHeight() 
	{
		return height;
	}

    public int getOrgX() {
        return orgX;
    }

    public void setOrgX(int orgX) {
        this.orgX = orgX;
    }

    public int getOrgY() {
        return orgY;
    }

    public void setOrgY(int orgY) {
        this.orgY = orgY;
    }

    public int getX()
	{
		return (x-orgX);
	}

    public void setX(int x) {
        this.x = x;
    }

	public int getY() 
	{
		return (y-orgY);
	}

    public void setY(int y) {
        this.y = y;
    }

	public Room getRoom() 
	{
		return room;
	}

    public void setRoom(Room room) {
        this.room = room;
    }

    public Color getOutline() {
        return outline;
    }

    public void setOutline(Color outline) {
        this.outline = outline;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void PicksUp(Item item)
	{
		// This picks up an item
		if (carrying == null)
			if (item.CanBePickedUp(this) == true && item.carriedBy == null)
			{
				carrying = item;
				item.carriedBy = this;
				item.x -= x;
				item.y -= y;
				item.outline=Color.white;
                level.getSoundPlayer().playIfInRoom(room, Level.PICKUPSOUND);
            }
	}

	public void Drops() 
	{
		// This drops the carried item
		if (carrying != null)
		{
			Item item = carrying;
			carrying.carriedBy = null;
			Dimension d = GetXY();
			carrying.x += d.width;
			carrying.y += d.height;
			if (carrying.x<0)
			{
				carrying.x += 560;
				carrying.room = room.leftRoom;
			}
			if (carrying.y<0)
			{
				carrying.y += 384;
				carrying.room = room.upRoom;
			}
			if (carrying.x>559)
			{
				carrying.x -= 560;
				carrying.room = room.rightRoom;
			}
			if (carrying.y>383)
			{
				carrying.y -= 384;
				carrying.room = room.downRoom;
			}
			carrying = null;
			outline=new Color(128,128,128);
			item.IsDropped();
            level.getSoundPlayer().playIfInRoom(room, Level.DROPSOUND);
        }
	}

	public void IsDropped() 
	{
		if (!editable) return;

		int bigXl = (x)/28;
		int bigXr = (x+width-1)/28;
		int bigYt = (y)/32;
		int bigYb = (y+height-1)/32;

		if (bigXr>19) bigXr=19;
		if (bigYb>11) bigYb=11;

		for (int a=bigYt; a<=bigYb; a++)
			for (int b=bigXl; b<=bigXr; b++)
			{
				if (room.MaterialArray[a][b] instanceof ChipTrash)
				{
					SetRoom(null); // Cheap way to remove the wires;
					level.items.removeElement(this);
                    level.getSoundPlayer().playIfInRoom(room, Level.DISCHARGESOUND);
                    return;
				}
			}

		return;
	}

	public void SetRoom(Room r) 
	{
		// Goes through recursively from Item to carried item to carried
		// item.... Puts all items in the same room.
		Room cr = r;
		if (r != room)
			if (isDevice())
			{
				Device device = (Device) this;
				for (int a = 0; a<device.ports.length; a++)
					if (device.ports[a].myWire != null)
					{
						Wire wire = device.ports[a].myWire;
						wire.Remove();
					}
			}
		if (carriedBy == null)
		{
			if (x<0)    { cr=r.leftRoom; x+=560; }
			if (y<0)    { cr=r.upRoom; y+=384; }
			if (x>=560) { cr=r.rightRoom; x-=560; }
			if (y>=384) { cr=r.downRoom; y-=384; }
		}
		room = cr;
		automove=0;
		if (carrying != null)
			carrying.SetRoom(cr);
	}

	public boolean KeyUp(KeyEvent e) 
	{
		// Handles keybord input.
		// Return TRUE if repaint is needed (usually for movement)
		return false;
	}

    public OperationFactory getOperationFactory() {
        return level.getGame().getOperationFactory();
    }

    public boolean KeyDown(KeyEvent e)
	{
		// Handles keybord input.
		// Return TRUE if repaint is needed (usually for movement)
		return false;
	}

	public void MouseClick(MouseEvent e) 
	{
		int button=0;
		if ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)
			button = 1;
		if ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)
			button = 3;

		if (button==1)
		{
			if (e.getClickCount()==1)
			{
				autoX = e.getX() - width/2;
				autoY = e.getY() - height/2;
				autoX -= autoX%2; // Even numbered pixel only!
				autoY -= autoY%2;
				automove = 1;
			}
			else if (e.getClickCount()==2)
			{
				int dx = e.getX() - width/2 - x;
				int dy = e.getY() - height/2 - y;
				if (Math.abs(dx) > Math.abs(dy))
				{
					autoY=0; autoX=28;
					if (dx<0) autoX=-28;
					automove=2;
				}
				else
				{
					autoX=0; autoY=32;
					if (dy<0) autoY=-32;
					automove=2;
				}
			}
		}

		if (button==3)
		{
			KeyEvent k = new KeyEvent(e.getComponent(), e.getID(), 
					e.getWhen(), 0, 
					KeyEvent.VK_SPACE,' ');
			KeyUp(k);
		}

	}

	public void MoveUp(int dist) 
	{
		int bigXl = x/28;
		int bigXr = (x+getWidth()-1)/28;
		int bigY  = (y-dist)/32;
		if (   (level.materialAt(bigXl,bigY,room).Passable(this) == false)
				|| (level.materialAt(bigXr,bigY,room).Passable(this) == false) )
		{
			automove=0;
			int newDist = y - (bigY+1)*32;
			y = (bigY+1)*32;
			ItemEffectsMaterials();
			return;
		}
		y=y-dist;
		if (y<0)
		{
			if (room.getUpRoom() != null)
			{ // change Rooms
				y=y+384;
				SetRoom(room.getUpRoom());
			}
			else if (this==level.player && room.portalItem != null)
			{ // Exit item, Player only
				Dimension d = room.portalItem.GetXY();
				x = d.width + (room.portalItem.width - width)/2;
				y = d.height + (room.portalItem.height - height)/2;
				SetRoom(room.portalItem.room);
				MoveUp(dist);
			}
			else
			{ // stop at top
				y+=384;
				automove=0;
			}
		}
		ItemEffectsMaterials();
	}

	public void MoveDown(int dist) 
	{
		int bigXl = x/28;
		int bigXr = (x+getWidth()-1)/28;
		int bigY  = (y+getHeight()-1+dist)/32;
		if (   (level.materialAt(bigXl,bigY,room).Passable(this) == false)
				|| (level.materialAt(bigXr,bigY,room).Passable(this) == false) )
		{
			automove=0;
			int newDist = bigY*32 - getHeight() - y;
			y += newDist;
			ItemEffectsMaterials();
			return;
		}
		y=y+dist;
		if (y>383)
		{
			if (room.getDownRoom() != null)
			{ // change Rooms
				y=y-384;
				SetRoom(room.getDownRoom());
			}
			else if (this==level.player && room.portalItem != null)
			{ // Exit item, GameCursor only
				Dimension d = room.portalItem.GetXY();
				x = d.width + (room.portalItem.width - width)/2;
				y = d.height + (room.portalItem.height - height)/2;
				SetRoom(room.portalItem.room);
				MoveDown(dist);
			}
			else
			{ // stop at bottom
				y-=384;
				automove=0;
			}
		}
		ItemEffectsMaterials();
	}

	public void MoveLeft(int dist) 
	{
		int bigX = (x-dist)/28;
		int bigYt = y/32;
		int bigYb = (y+getHeight()-1)/32;
		if (   (level.materialAt(bigX,bigYt,room).Passable(this) == false)
				|| (level.materialAt(bigX,bigYb,room).Passable(this) == false) )
		{
			automove=0;
			int newDist = x - (bigX+1)*28 ;
			x = (bigX+1)*28;
			ItemEffectsMaterials();
			return;
		}
		x=x-dist;
		if (x<0)
		{
			if (room.getLeftRoom() != null)
			{ // change Rooms
				x=x+560;
				SetRoom(room.getLeftRoom());
			}
			else if (this==level.player && room.portalItem != null)
			{ // Exit item, GameCursor only
				Dimension d = room.portalItem.GetXY();
				x = d.width + (room.portalItem.width - width)/2;
				y = d.height + (room.portalItem.height - height)/2;
				SetRoom(room.portalItem.room);
				MoveLeft(dist);
			}
			else
			{ // stop at Left
				x+=560;
				automove=0;
			}
		}
		ItemEffectsMaterials();
	}

	public void MoveRight(int dist)
	{
		int bigX = (x+getWidth()-1+dist)/28;
		int bigYt = y/32;
		int bigYb = (y+getHeight()-1)/32;
		//	if (bigX<20 && bigYb<20 && bigYt>=0)
		if (   (level.materialAt(bigX,bigYt,room).Passable(this) == false)
				|| (level.materialAt(bigX,bigYb,room).Passable(this) == false) )
		{
			automove=0;
			int newDist = bigX*28 - getWidth()- x;
			x += newDist;
			ItemEffectsMaterials();
			return;
		}
		x=x+dist;
		if (x>559)
		{
			if (room.getRightRoom() != null)
			{ // change Rooms
				x=x-560;
				SetRoom(room.getRightRoom());
			}
			else if (this==level.player && room.portalItem != null)
			{ // Exit item, GameCursor only
				Dimension d = room.portalItem.GetXY();
				x = d.width + (room.portalItem.width - width)/2;
				y = d.height + (room.portalItem.height - height)/2;
				SetRoom(room.portalItem.room);
				MoveRight(dist);
			}
			else
			{ // stop at Right
				x-=560;
				automove=0;
			}
		}
		ItemEffectsMaterials();
	}

	public void MoveUp(boolean nudge) 
	{
		int dist = 32;
		if (nudge) dist = 2;
		MoveUp(dist);
	}

	public void MoveDown(boolean nudge) 
	{
		int dist = 32;
		if (nudge) dist = 2;
		MoveDown(dist);
	}

	public void MoveLeft(boolean nudge) 
	{
		int dist = 28;
		if (nudge) dist = 2;
		MoveLeft(dist);
	}

	public void MoveRight(boolean nudge) 
	{
		int dist = 28;
		if (nudge) dist = 2;
		MoveRight(dist);
	}

	public void Animate() 
	{
		if (automove==1 && room == null)
			automove=0;
		if (automove==1)
		{
			int dx = autoX - x;
			int dy = autoY - y;
			if (dx==0 && dy==0) automove=0;
			if (dx<-28) dx =-28;
			if (dx>28) dx=28;
			if (dy<-32) dy=-32;
			if (dy>32) dy=32;
			if (dx>0) MoveRight(dx);
			if (dx<0) MoveLeft(-dx);
			if (dy>0) MoveDown(dy);
			if (dy<0) MoveUp(-dy);
		}
		if (automove==2)
		{
			if (autoX>0) MoveRight(autoX);
			if (autoX<0) MoveLeft(-autoX);
			if (autoY>0) MoveDown(autoY);
			if (autoY<0) MoveUp(-autoY);
		}
	}

	public void Decorate() 
	{
	}

	public void GenerateIcons() 
	{
		// This is where the icons[] array is filled with ImageIcons, and
		// the ImageIcons are painted. Depending on the Item, this can be
		// done either once during initialization, or once per Animation
		// phase.
	}

	public boolean CanBePickedUp(Item i) 
	{
		// Returns True if THIS Item can be picked up by Item i.
		return grabbable;
	}

	private void ItemEffectsMaterials() 
	{
		// called after every Move() function
		// 
		// Checks the materials touched by this item, and calls their
		// Material.TouchedByItem(Item)

		if (room == null) return;

		Dimension d = GetXY();

		int bigXl = (d.width)/28;
		int bigXr = (d.width+width-1)/28;
		int bigYt = (d.height)/32;
		int bigYb = (d.height+height-1)/32;

		if (bigXr>19) bigXr=19;
		if (bigYb>11) bigYb=11;

		for (int a=bigYt; a<=bigYb; a++)
			for (int b=bigXl; b<=bigXr; b++)
				if (a>=0 && a<12 && b>=0 &&b<20)
					room.MaterialArray[a][b].TouchedByItem(this);

		if (carrying != null)
			carrying.ItemEffectsMaterials();


	}

	public Dimension GetXY() 
	{
		// Recursively goes up the carrying tree to figure out the XY
		// coordinates of an item.

		if (carriedBy != null)
		{
			Dimension d = carriedBy.GetXY();
			d.width += x;
			d.height += y;
			return d;
		}
		else
			return new Dimension(x,y);     
	}

	public boolean Overlaps(Item testItem)
	{
		boolean overlap = false;
		if (this!=testItem && this.room == testItem.room)
		{
			overlap = true;
			Dimension d1 = GetXY();
			Dimension d2 = testItem.GetXY();
			if (this.carrying == testItem) {overlap=false;}
			if (this == testItem.carrying) {overlap=false;}
			if (d1.width + this.width < d2.width) {overlap=false;}
			if (d2.width + testItem.width < d1.width) {overlap=false;}
			if (d1.height + this.height < d2.height) {overlap=false;}
			if (d2.height + testItem.height < d1.height) {overlap=false;}
		}
		return overlap;
	}

	public boolean RightEnterOverlap(Item item) 
	{
		boolean result = true;
		if (leftPortal!= null)
		{
			if (item.x < x + leftPortal.x) result = false;
			if (item.x > x + leftPortal.x + leftPortal.width) result = false;
			if (item.y < y + leftPortal.y) result = false;
			if (item.y > y + leftPortal.y + leftPortal.height) result = false;
		}
		else
			result = false;
		if (OverWall()) result=false;
		return result;
	}

	public boolean DownEnterOverlap(Item item) 
	{
		boolean result = true;
		if (upPortal!=null)
		{
			if (item.x < x + upPortal.x) result = false;
			if (item.x > x + upPortal.x + upPortal.width) result = false;
			if (item.y < y + upPortal.y) result = false;
			if (item.y > y + upPortal.y + upPortal.height) result = false;
		}
		else
			result = false;
		if (OverWall()) result=false;
		return result;	  
	}

	public boolean LeftEnterOverlap(Item item) 
	{
		boolean result = true;
		if (leftPortal!=null)
		{
			if (item.x < x + rightPortal.x) result = false;
			if (item.x > x + rightPortal.x + rightPortal.width) result = false;
			if (item.y < y + rightPortal.y) result = false;
			if (item.y > y + rightPortal.y + rightPortal.height) result = false;
		}
		else
			result = false;
		if (OverWall()) result=false;
		return result;
	}

	public boolean UpEnterOverlap(Item item) 
	{
		boolean result = true;
		if (downPortal!=null)
		{
			if (item.x < x + downPortal.x) result = false;
			if (item.x > x + downPortal.x + downPortal.width) result = false;
			if (item.y < y + downPortal.y) result = false;
			if (item.y > y + downPortal.y + downPortal.height) result = false;
		}
		else
			result = false;
		if (OverWall()) result=false;
		return result;	  
	}

	public boolean isDevice() 
	{
		return false;
	}

	public Object clone() 
	{
		Object newObject = null;
		try
		{
			newObject = super.clone();
		}
		catch (CloneNotSupportedException e) {}
		//	if (newObject instanceof Device)
		//	  {
		//	     Device newDevice = (Device) newObject;
		//	     newDevice.ports = null;
		//	     newDevice.GenerateIcons();
		//	  }
		return newObject;
	}

	public void Erase() 
	{
		carrying = null;
		carriedBy = null;
		room = null;
		currentIcon=null;
		icons = null;
		InternalRoom = null;
	}

	public boolean OverWall() 
	{
		Dimension d = GetXY();
		int bigXL = (d.width+width/2-14)/28;
		int bigXR = (d.width+width/2+14)/28;
		int bigYT = (d.height+height/2-16)/32;
		int bigYB = (d.height+height/2+16)/32;
		if (bigXR>19) bigXR=19;
		if (bigYB>11) bigYB=11;
		boolean flag = false;
		for (int Y=bigYT; Y<=bigYB; Y++)
			for (int X=bigXL; X<=bigXR; X++)
				if (!room.MaterialArray[Y][X].passable)
					flag=true;
		return flag;
	}

    public void writeRef(ObjectOutputStream s) throws IOException
    {
        s.writeInt(level.items.indexOf(carrying));
        s.writeInt(level.items.indexOf(carriedBy));
        s.writeInt(level.rooms.indexOf(room));
        s.writeInt(level.rooms.indexOf(InternalRoom));
    }

    public void readRef(ObjectInputStream s) throws IOException
    {
        carrying = level.FindItem(s.readInt());
        carriedBy =  level.FindItem(s.readInt());
        room = level.FindRoom(s.readInt());
        InternalRoom = level.FindRoom(s.readInt());
        GenerateIcons();
    }

    /**
     * Returns the item that this item is carrying, or null if no item is being carried.
     * @return an item
     */
    public Item getCarrying() {
        return carrying;
    }

    public Item getCarriedBy() {
        return carriedBy;
    }

    public void setRepeating(int repeated) {
        this.repeating = repeated;
    }

    public Room getInternalRoom() {
        return InternalRoom;
    }
}
