package com.droidquest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.droidquest.chipstuff.Port;
import com.droidquest.devices.Device;
import com.droidquest.levels.Level;

public class Wire implements Serializable 
{
public transient Port fromPort; // Connected First
public transient Port toPort;   // Connected 2nd
public transient Port inPort;   // Connected to Input
public transient Port outPort;  // Connected to Output (Source of Value)

public Wire() {}

public Wire(Port f, Port t) 
  {
	if (f.myDevice!=null)
	  {
	     if (f.myDevice.room!=null)
	       {
		  if (f.myDevice.room.wires==null)
		    System.out.println("f.myDevice.room.wires is null");
	       }
	     else
	       System.out.println("f.myDevice.room is null");
	  }
	else
	  System.out.println("f.myDevice is null");
	
	f.myDevice.room.wires.addElement(this);
      f.myDevice.level.getSoundPlayer().playIfInRoom(f.myDevice.room, Level.ATTACHSOUND);

      if (f.type == Port.TYPE_INPUT)
	  {
	     if (t.type == Port.TYPE_INPUT)
	       {
		  Remove();
		  return;
	       }
	     if (t.type == Port.TYPE_OUTPUT)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  inPort = fromPort;
		  outPort = toPort;
		  return;
	       }
	     if (t.type == Port.TYPE_UNDEFINED)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  inPort = fromPort;
		  outPort = toPort;
		  t.type = Port.TYPE_OUTPUT;
		  return;
	       }
	  }
	if (f.type == Port.TYPE_OUTPUT)
	  {
	     if (t.type == Port.TYPE_INPUT)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  outPort = fromPort;
		  inPort = toPort;
		  return;
	       }
	     if (t.type == Port.TYPE_OUTPUT)
	       {
		  Remove();
		  return;
	       }
	     if (t.type == Port.TYPE_UNDEFINED)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  outPort = fromPort;
		  inPort = toPort;
		  t.type = Port.TYPE_INPUT;
		  return;
	       }
	  }
	if (f.type == Port.TYPE_UNDEFINED)
	  {
	     if (t.type == Port.TYPE_INPUT)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  outPort = fromPort;
		  inPort = toPort;
		  f.type = Port.TYPE_OUTPUT;
		  return;
	       }
	     if (t.type == Port.TYPE_OUTPUT)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  inPort = fromPort;
		  outPort = toPort;
		  f.type = Port.TYPE_INPUT;
		  return;
	       }
	     if (t.type == Port.TYPE_UNDEFINED)
	       {
		  fromPort = f;
		  toPort=t;
		  f.myWire = this;
		  t.myWire = this;
		  return;
	       }
	  }
  }

protected void writeRef(ObjectOutputStream s) throws IOException 
  {
	Level level = fromPort.myDevice.level;
	int a;
	
	s.writeInt(level.items.indexOf(fromPort.myDevice)); // Index of fromport device
	a=0; while (((Device)fromPort.myDevice).ports[a] != fromPort) a++;
	s.writeInt(a); // Index of fromport (as device.ports[?]

	s.writeInt(level.items.indexOf(toPort.myDevice)); // Index of toPort device
	a=0; while (((Device)toPort.myDevice).ports[a] != toPort) a++;
	s.writeInt(a); // Index of toPort (as device.ports[?]
	
	s.writeInt(level.items.indexOf(inPort.myDevice)); // Index of inPort device
	a=0; while (((Device)inPort.myDevice).ports[a] != inPort) a++;
	s.writeInt(a); // Index of inPort (as device.ports[?]
	
	s.writeInt(level.items.indexOf(outPort.myDevice)); // Index of outPort device
	a=0; while (((Device)outPort.myDevice).ports[a] != outPort) a++;
	s.writeInt(a); // Index of outPort (as device.ports[?]
  }

protected void readRef(ObjectInputStream s, Level level) throws IOException 
  {
	Device tempDevice;
	tempDevice = (Device) level.FindItem(s.readInt());
	fromPort = tempDevice.ports[s.readInt()];
	tempDevice = (Device) level.FindItem(s.readInt());
	toPort = tempDevice.ports[s.readInt()];
	tempDevice = (Device) level.FindItem(s.readInt());
	inPort = tempDevice.ports[s.readInt()];
	tempDevice = (Device) level.FindItem(s.readInt());
	outPort = tempDevice.ports[s.readInt()];
  }

public void ConnectTo(Port t) 
  {
      fromPort.myDevice.level.getSoundPlayer().playIfInRoom(fromPort.myDevice.room, Level.DETATCHSOUND);

      if (toPort.myDevice == toPort.myDevice.level.solderingPen)
	  {
	     toPort.value = false;
	     toPort.type = Port.TYPE_UNDEFINED;
	     toPort.myWire = null;	
	     
	     if (fromPort.type == Port.TYPE_INPUT)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       Remove();
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       toPort=t;
		       t.myWire = this;
		       inPort = fromPort;
		       outPort = toPort;
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       toPort=t;
		       t.myWire = this;
		       inPort = fromPort;
		       outPort = toPort;
		       t.type = Port.TYPE_OUTPUT;
		       return;
		    }
	       }
	     if (fromPort.type == Port.TYPE_OUTPUT)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       toPort=t;
		       t.myWire = this;
		       outPort = fromPort;
		       inPort = toPort;
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       Remove();
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       toPort=t;
		       t.myWire = this;
		       outPort = fromPort;
		       inPort = toPort;
		       t.type = Port.TYPE_INPUT;
		       return;
		    }
	       }
	     if (fromPort.type == Port.TYPE_UNDEFINED)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       toPort=t;
		       t.myWire = this;
		       outPort = fromPort;
		       inPort = toPort;
		       fromPort.type = Port.TYPE_OUTPUT;
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       toPort=t;
		       t.myWire = this;
		       inPort = fromPort;
		       outPort = toPort;
		       fromPort.type = Port.TYPE_INPUT;
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       toPort=t;
		       t.myWire = this;
		       return;
		    }
	       }
	  }
	else 
	  {
	     fromPort.value = false;
	     fromPort.type = Port.TYPE_UNDEFINED;
	     fromPort.myWire = null;	
	     
	     if (toPort.type == Port.TYPE_INPUT)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       Remove();
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       fromPort=t;
		       t.myWire = this;
		       inPort = toPort;
		       outPort = fromPort;
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       fromPort=t;
		       t.myWire = this;
		       inPort = toPort;
		       outPort = fromPort;
		       t.type = Port.TYPE_OUTPUT;
		       return;
		    }
	       }
	     if (toPort.type == Port.TYPE_OUTPUT)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       fromPort=t;
		       t.myWire = this;
		       outPort = toPort;
		       inPort = fromPort;
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       Remove();
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       fromPort=t;
		       t.myWire = this;
		       outPort = toPort;
		       inPort = fromPort;
		       t.type = Port.TYPE_INPUT;
		       return;
		    }
	       }
	     if (toPort.type == Port.TYPE_UNDEFINED)
	       {
		  if (t.type == Port.TYPE_INPUT)
		    {
		       fromPort=t;
		       t.myWire = this;
		       outPort = toPort;
		       inPort = fromPort;
		       toPort.type = Port.TYPE_OUTPUT;
		       return;
		    }
		  if (t.type == Port.TYPE_OUTPUT)
		    {
		       fromPort=t;
		       t.myWire = this;
		       inPort = toPort;
		       outPort = fromPort;
		       toPort.type = Port.TYPE_INPUT;
		       return;
		    }
		  if (t.type == Port.TYPE_UNDEFINED)
		    {
		       fromPort=t;
		       t.myWire = this;
		       return;
		    }
	       }
	  }

  }

public void Remove() 
  {
	Room room = fromPort.myDevice.room;

      room.level.getSoundPlayer().playIfInRoom(room, Level.DETATCHSOUND);

      fromPort.myWire = null;
	toPort.myWire = null;
	fromPort = null;
	toPort = null;
	inPort = null;
	outPort = null;
	room.wires.removeElement(this);
	
  }

public Port otherPort(Port p)
  {
	if (fromPort == p) return toPort;
	if (toPort == p) return fromPort;
	return null;
  }

    public Port getFromPort() {
        return fromPort;
    }

    public Port getToPort() {
        return toPort;
    }
}