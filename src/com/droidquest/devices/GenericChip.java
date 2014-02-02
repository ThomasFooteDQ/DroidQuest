package com.droidquest.devices;

public class GenericChip extends Device 
{
public String label;
public boolean inBurner;
public boolean inTester;
public String description="Chip Pinouts";
public transient ChipText chiptext;

public GenericChip() {}

public void rotate(int dir) 
  {
	// Does not Rotate!
  }

public void ShowText(boolean editable) 
  {
	if (chiptext==null)
	  {
	     chiptext = new ChipText(this);
	     chiptext.setTitle("Pinout for Chip " + label);
	  }
	chiptext.setText(description,label);
	chiptext.setEditable(editable);
	chiptext.setVisible(true);
  }

}
