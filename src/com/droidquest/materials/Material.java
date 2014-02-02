package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;

import com.droidquest.RoomDisplay;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class Material implements Serializable, Cloneable 
{
	public transient static Level level;
	public transient ImageIcon icon;
	public String file;
	public boolean passable;
	public boolean detectable;
	public Color color;

	public Material(){}

	public Material(String filename, boolean p, boolean d) 
	{
		//	icon=Toolkit.getDefaultToolkit().getImage(filename);
		icon = new ImageIcon(filename);
		passable = p;
		detectable = d;
	}

	public Material(boolean p, boolean d) 
	{
		passable = p;
		detectable = d;
		color = Color.black;
	}

	public Material (Color c, boolean p, boolean d) 
	{
		passable=p;
		detectable = d;
		color=c;
	}

	public void GenerateIcons() 
	{
		if (file != null)
			icon = new ImageIcon(file);
	}

	public void Draw(Graphics g, RoomDisplay rd, int x, int y) 
	{
		if (icon==null)
		{
			// Blank Background
			g.setColor(color);
			g.fillRect(x*28,y*32,28,32);
//			if (color!=Color.BLACK){
//				g.setColor(color.brighter());
//				g.drawLine(x*28, y*32, x*28+27, y*32);
//				g.drawLine(x*28, y*32, x*28, y*32+31);
//				g.setColor(color.darker());
//				g.drawLine(x*28+27, y*32, x*28+27, y*32+31);
//				g.drawLine(x*28, y*32+31, x*28+27, y*32+31);
//			}
			return;
		}
		else
		{
			// Material Background
			g.drawImage(icon.getImage(), x*28, y*32, rd);
			return;
		}
	}

	public void TouchedByItem(Item item) 
	{
	}

	public void Animate() 
	{
	}

	public boolean Passable(Item item) 
	{
		return passable;
	}

	public boolean Detectable(Item item) 
	{
		return detectable;
	}

	public boolean equals(Material mat) 
	{
		if (getClass() == mat.getClass()
				&& color == mat.color 
				&& passable == mat.passable 
				&& detectable == mat.detectable
				&& file == mat.file)
			return true;
		else
			return false;
	}

	public static Material FindSimiliar(Material mat1) 
	{
		for (int a=0; a<level.materials.size(); a++)
		{
			Material mat2 = (Material) level.materials.elementAt(a);
			if (mat1.equals(mat2))
				return mat2;
		}
		level.materials.addElement(mat1);
		return mat1;
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

}
