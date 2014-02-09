package com.droidquest.materials;

import javax.swing.*;
import java.awt.Color;
import java.io.Serializable;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class Material implements Serializable, Cloneable 
{
	public transient static Level level;
	public transient ImageIcon icon;
	public boolean passable;
	public boolean detectable;
	public Color color;

	public Material(){}

	public Material(boolean p, boolean d)
	{
        this(Color.black, p, d);
	}

	public Material (Color c, boolean p, boolean d) 
	{
		passable=p;
		detectable = d;
		color=c;
	}

	public void GenerateIcons() 
	{
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
				&& detectable == mat.detectable)
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

    public ImageIcon getIcon() {
        return icon;
    }

    public Color getColor() {
        return color;
    }
}
