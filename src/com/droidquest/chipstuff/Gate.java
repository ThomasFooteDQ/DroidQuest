package com.droidquest.chipstuff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import com.droidquest.devices.SmallChip;

public class Gate implements Serializable 
{
public transient PortSignal[] portSignals = new PortSignal[8];
public boolean state;
public String type;
public int speed;

public Vector mySignals = new Vector();
public Vector myGates = new Vector();

public Gate(String t) 
    {
	  // Called whenever a non-chip gate is created.
	  type = t;
	  speed = 1;
	  for (int a=0; a<8; a++)
	    portSignals[a] = new PortSignal();
    }

public Gate(SmallChip sc) 
  {
	// Called by ChipCompiler to put a nested chip into a gate
	speed = sc.speed;
	type = "Chip";	
	Signal dummySignal;
	portSignals = new PortSignal[8];
	for (int a=0; a<8; a++)
	  portSignals[a] = new PortSignal();
	for (int a=0; a<sc.signals.size(); a++)
	  {
	     Signal newsig = new Signal();
	     Signal oldsig = (Signal) sc.signals.elementAt(a);
	     newsig.Set(oldsig.Get());
	     newsig.working = oldsig.working;
	     mySignals.addElement(newsig);
	     if (newsig.working == false)
	       dummySignal=newsig;
	  }
	
	for (int a=0; a<sc.gates.size(); a++)
	  {
	     Gate oldgate = (Gate) sc.gates.elementAt(a);
	     Gate newgate = new Gate(oldgate);
	     myGates.addElement(newgate);
	     for (int b=0; b<8; b++)
	       if (oldgate.portSignals[b].externalSignal!=null)
		 {
		    int sigIndex = sc.signals.indexOf(oldgate.portSignals[b].externalSignal);
		    Signal sig = (Signal) mySignals.elementAt(sigIndex);
		    newgate.portSignals[b].externalSignal = sig;
		 }
	  }
	
	for (int a=0; a<8; a++)
	  {
	     if (sc.portSignals[a].internalSignal!=null)
	       {
		  int sigIndex = sc.signals.indexOf(sc.portSignals[a].internalSignal);
		  Signal sig = (Signal) mySignals.elementAt(sigIndex);
		  portSignals[a].internalSignal = sig;
		  portSignals[a].type = sc.portSignals[a].type;
	       }
	  }

  }
	
public Gate(Gate g) 
  {
	// Create a new Gate based off an existing one
	type=g.type;
	state=g.state;
	speed = g.speed;
	portSignals = new PortSignal[8];
	for (int a=0; a<8; a++)
	  portSignals[a] = new PortSignal();
	if (type.equalsIgnoreCase("Chip"))
	  {
	     for (int a=0; a<g.mySignals.size(); a++)
	       {
		  Signal newsig = new Signal();
		  Signal oldsig = (Signal) g.mySignals.elementAt(a);
		  newsig.Set(oldsig.Get());
		  newsig.working = oldsig.working;
		  mySignals.addElement(newsig);
	       }
	     for (int a=0; a<g.myGates.size(); a++)
	       {
		  Gate oldgate = (Gate) g.myGates.elementAt(a);
		  Gate newgate = new Gate(oldgate);
		  myGates.addElement(newgate);
		  for (int b=0; b<8; b++)
		    {
		       int signalIndex = g.mySignals.indexOf(oldgate.portSignals[b].externalSignal);
		       if (signalIndex != -1)
			 newgate.portSignals[b].externalSignal = (Signal)mySignals.elementAt(signalIndex);
		    }
	       }
	     for (int a=0; a<8; a++)
	       {
		  if (g.portSignals[a].internalSignal != null)
		    {
		       int sigIndex = g.mySignals.indexOf(g.portSignals[a].internalSignal);
		       portSignals[a].internalSignal = (Signal) mySignals.elementAt(sigIndex);
		       portSignals[a].type = g.portSignals[a].type;
		    }
	       }
	  }
  }

public void writeRef(ObjectOutputStream s) throws IOException 
  {
	for (int a=0; a<8; a++)
	  s.writeInt(mySignals.indexOf(portSignals[a].internalSignal));
	for (int a=0; a<myGates.size(); a++)
	  {
	     Gate gate = (Gate) myGates.elementAt(a);
	       for (int b=0; b<8; b++)
	       {
		  s.writeInt(mySignals.indexOf(gate.portSignals[b].externalSignal));
		  s.writeInt(gate.portSignals[b].type);
	       }
	     gate.writeRef(s);
	  }
  }

public void readRef(ObjectInputStream s) throws IOException 
  {
	for (int a=0; a<8; a++)
	  {
	     int portIndex = s.readInt();
	     if (portIndex>=0)
	       portSignals[a].internalSignal = (Signal) mySignals.elementAt(portIndex);
	  }
	for (int a=0; a<myGates.size(); a++)
	  {
	     Gate gate = (Gate) myGates.elementAt(a);
	     gate.portSignals = new PortSignal[8];
	     for (int b=0; b<8; b++)
	       {
		  gate.portSignals[b] = new PortSignal();
		  int sigIndex = s.readInt();
		  if (sigIndex>=0)
		    gate.portSignals[b].externalSignal = (Signal) mySignals.elementAt(sigIndex);
		  gate.portSignals[b].type = s.readInt();
	       }
	     gate.readRef(s);
	  }
  }

public void Function() 
  {
	if (type.equalsIgnoreCase("AND"))
	  portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get() 
					    & portSignals[1].externalSignal.Get());
	if (type.equalsIgnoreCase("OR"))
	  portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get() 
					    | portSignals[1].externalSignal.Get());
	if (type.equalsIgnoreCase("NOT"))
	  portSignals[1].externalSignal.Set(!portSignals[0].externalSignal.Get());
	if (type.equalsIgnoreCase("XOR"))
	  portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get() 
					    ^ portSignals[1].externalSignal.Get());
	if (type.equalsIgnoreCase("FF"))
	  {
	     if (portSignals[0].externalSignal.Get() ^ portSignals[1].externalSignal.Get())
	       state = portSignals[0].externalSignal.Get();
	     portSignals[2].externalSignal.Set(state);
	     portSignals[3].externalSignal.Set(!state);
	  }
	if (type.equalsIgnoreCase("NODE"))
	  {
	     portSignals[1].externalSignal.Set(portSignals[0].externalSignal.Get());
	     portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get());
	     if (portSignals[3].externalSignal!=null)
	       portSignals[3].externalSignal.Set(portSignals[0].externalSignal.Get());
	  }
	if (type.equalsIgnoreCase("Chip"))
	  {
		  
	     for (int s=0; s<speed; s++)
	       {
		  for (int a=0; a<mySignals.size(); a++)
		    ((Signal) mySignals.elementAt(a)).Flip();
		  
		  for (int a=0; a<8; a++)
		    if (portSignals[a].externalSignal!=null 
			&& portSignals[a].internalSignal!=null)
		      if (portSignals[a].type==Port.TYPE_INPUT)
			portSignals[a].internalSignal.Set(portSignals[a].externalSignal.Get());
	
		  for (int a=0; a<myGates.size(); a++)
		    ((Gate) myGates.elementAt(a)).Function();
		  
		  for (int a=0; a<8; a++)
		    if (portSignals[a].externalSignal!=null 
			&& portSignals[a].internalSignal!=null)
		      if (portSignals[a].type==Port.TYPE_OUTPUT)
			portSignals[a].externalSignal.Set(portSignals[a].internalSignal.Get());
	       }
	  }
  }

public void SaveSubGate(ObjectOutputStream s) throws IOException 
  {
	s.writeInt(mySignals.size());
	for (int a=0; a<mySignals.size(); a++)
	  {
	     Signal sig = (Signal)mySignals.elementAt(a);
	     s.writeBoolean(sig.Get());
	     s.writeBoolean(sig.working);
	  }
	
	for (int a=0; a<8; a++)
	  {
	     s.writeInt(mySignals.indexOf(portSignals[a].internalSignal));
	     s.writeInt(portSignals[a].type);
	  }
	
	s.writeInt(myGates.size());
	for (int a=0; a<myGates.size(); a++)
	  {
	     Gate gate = (Gate) myGates.elementAt(a);
	     s.writeObject(gate.type);
	     s.writeBoolean(gate.state);
	     for (int b=0; b<8; b++)
	       s.writeInt(mySignals.indexOf(gate.portSignals[b].externalSignal));
	     if (gate.type.equalsIgnoreCase("Chip"))
	       gate.SaveSubGate(s);
	  }	
	s.writeInt(speed);
  }

public void LoadSubGate(ObjectInputStream s) throws IOException, ClassNotFoundException 
  {
	int numSignals = s.readInt();
	mySignals = new Vector();
	for (int a=0; a<numSignals; a++)
	  {
	     Signal newSignal = new Signal();
	     newSignal.Set(s.readBoolean());
	     newSignal.working = s.readBoolean();
	     mySignals.addElement(newSignal);
	  }
	
	for (int a=0; a<8; a++)
	  {
	     int sigIndex = s.readInt();
	     if (sigIndex>=0)
	       portSignals[a].internalSignal = (Signal) mySignals.elementAt(sigIndex);
	     portSignals[a].type = s.readInt();
	  }
	
	int numGates = s.readInt();
	for (int a=0; a<numGates; a++)
	  {
	     Gate newGate = new Gate((String) s.readObject());	     
	     newGate.state = s.readBoolean();
	     myGates.addElement(newGate);
	     for (int b=0; b<8; b++)
	       {
		  int sigIndex = s.readInt();
		  if (sigIndex>=0)
		    newGate.portSignals[b].externalSignal = (Signal) mySignals.elementAt(sigIndex);
	       }
	     if (newGate.type.equalsIgnoreCase("Chip"))
	       newGate.LoadSubGate(s);
	  }
	speed = s.readInt();
  }

public void DebugReport(int indent) 
  {
//	String ind = "";
//	for (int a=0; a<indent; a++)
//	  ind += "  ";
//	System.out.println(ind + type + "Gate");
//	System.out.println(ind + mySignals.size() + " Signals");
//	System.out.println(ind + myGates.size() + " Gates");
//	for(int a=0; a<myGates.size(); a++)
//	  {
//	     Gate gate1 = (Gate) myGates.elementAt(a);
//	     gate1.DebugReport(indent+1);
//	     for (int b=0; b<8; b++)
//	       if (gate1.myPortSignals[b]!=null)
//		 System.out.println(ind + a + ": " + gate1.type 
//				    + " gate["
//				    + b
//				    + "] = Signal "
//				    + mySignals.indexOf(gate1.myPortSignals[b]));
//	  }
//	for (int a=0; a<8; a++)
//	  if (portSignals[a] != null)
//	    System.out.println(ind + "PortSignal " 
//			       + a 
//			       + " = Signal " 
//			       + mySignals.indexOf(portSignals[a]));
  }


}
