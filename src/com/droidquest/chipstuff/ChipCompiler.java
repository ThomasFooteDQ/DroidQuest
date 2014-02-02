package com.droidquest.chipstuff;

import com.droidquest.Wire;
import com.droidquest.devices.Device;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.PrototypeChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.Item;

public class ChipCompiler extends Thread 
{
public static int chipSpeed=1;

public ChipCompiler(PrototypeChip pc, SmallChip sc) 
  {
	pc.grabbable = false;
	sc.grabbable = false;
	int a;
	
	sc.Empty();
	
	for (a=0; a<pc.InternalRoom.wires.size(); a++)
	  sc.signals.addElement(new Signal());
	
	Signal dummy = new Signal();
	dummy.working = false;
	sc.signals.addElement(dummy);
	
	for (a=0; a<8; a++)
	  {
	     Wire wire = pc.portdevices[a].ports[0].myWire;
	     int index = pc.InternalRoom.wires.indexOf(wire);
	     if (index>=0)
	       {
		  Signal sig = (Signal) sc.signals.elementAt(index);
		  sc.portSignals[a].internalSignal= sig;
	       }
	     sc.ports[a].type = pc.ports[a].type;
	     sc.portSignals[a].type = pc.ports[a].type;
	  }

	for (a=0; a<pc.level.items.size(); a++)
	  {
	     Item item = (Item) pc.level.items.elementAt(a);
	     if (item.room == pc.InternalRoom)
	       if (item.isDevice())
		 {
		    Device device = (Device) item;
		    Gate gate=null;
		    String type = item.getClass().toString();
		    if (type.endsWith("ANDGate"))
		      gate = new Gate("AND");
		    if (type.endsWith("ORGate"))
		      gate = new Gate("OR");
		    if (type.endsWith("NOTGate"))
		      gate = new Gate("NOT");
		    if (type.endsWith("XORGate"))
		      gate = new Gate("XOR");
		    if (type.endsWith("FlipFlop"))
		      {
			 gate = new Gate("FF");
			 gate.state = ((FlipFlop)device).state;
		      }
		    if (type.endsWith("Node"))
		      gate = new Gate("NODE");
		    if (type.endsWith("SmallChip"))
		      gate = new Gate((SmallChip)device);
		    if (gate != null)
		      {
			 sc.gates.addElement(gate);
			 for (int p=0; p<device.ports.length; p++)
			   {
			      if (device.ports[p].myWire != null)
				{
				   int index = pc.InternalRoom.wires.indexOf(device.ports[p].myWire);
				   Signal sig = (Signal) sc.signals.elementAt(index);
				   gate.portSignals[p].externalSignal= sig;
//				   System.out.println("Signal " 
//						      + index 
//						      + " attached to "
//						      + gate.type
//						      + " Gate["
//						      + p
//						      + "]");
				}
			      else
				gate.portSignals[p].externalSignal=dummy;
			   }
		      }
		 }
	  }
	
	// Remove Node Gates, and transfer Signals
	for (a=0; a<sc.gates.size(); a++) //For every Gate in the chip
	  {
	     Gate gate1 = (Gate) sc.gates.elementAt(a);
	     if (gate1.type == "NODE")
	       {
		  for (int ap=1; ap<4; ap++) // For every output Signal in the Node
		    {
		       Signal s1 = gate1.portSignals[ap].externalSignal;
		       if (s1!= null)
			 {
			    for (int b=0; b<sc.gates.size(); b++) // For every other Gate in the Chip
			      {
				 Gate gate2 = (Gate) sc.gates.elementAt(b);
				 if (gate1 != gate2)
				   {
				      for (int bp=0; bp<8; bp++) // For every Signal connection in that other gate
					{
					   Signal s2 = gate2.portSignals[bp].externalSignal;
					   if (s1==s2) // If  Signal is an output Node signal
					     {
//						System.out.println("Changing " + gate2.type
//								   + "[" + bp + "] = Signal "
//								   + sc.signals.indexOf(s2)
//								   + " to Signal "
//								   + sc.signals.indexOf(gate1.portSignals[0]));
						gate2.portSignals[bp].externalSignal 
						  = gate1.portSignals[0].externalSignal; 
					     }
					}
				   }
			      }
			    for (int ps=0; ps<8; ps++)
			      if (sc.portSignals[ps].internalSignal==s1)
				sc.portSignals[ps].internalSignal 
			          = gate1.portSignals[0].externalSignal;
			 }
		    }
		  sc.gates.removeElement(gate1);
		  a--;
	       }
	  }

	// Remove unused Signals
//	System.out.println("Starting with " + sc.signals.size() + " signals");
//	System.out.println("Starting with " + sc.gates.size() + " gates");
	for (a=0; a<sc.signals.size(); a++)
	  {
	     boolean used = false;
	     Signal sig1 = (Signal) sc.signals.elementAt(a);
	     for (int g = 0; g< sc.gates.size(); g++)
	       {
		  Gate gate = (Gate) sc.gates.elementAt(g);
		  for (int s = 0; s< 8; s++)
		    {
		       Signal sig2 = gate.portSignals[s].externalSignal;
		       if (sig2!=null)
//			 System.out.println(gate.type + "Gate["+s+"] connected to Signal "
//					    + sc.signals.indexOf(sig2));
			 if (sig1==sig2)
			   {
//			    System.out.println("Signal " + a + " is used by " + gate.type + "Gate ");
			      used = true;
			   }
		    }
	       }
	     for (int ps = 0; ps<8; ps++)
	       if (sc.portSignals[ps].internalSignal==sig1)
		 used=true;
	     if (used == false)
	       {
//		  System.out.println("Removing unused Signal at " + a);
		  sc.signals.removeElement(sig1);
		  a--;
	       }
	  }
	
	// Set Signal types
	for (a=0; a<8; a++)
	  if (sc.portSignals[a]!=null)
	    sc.portSignals[a].type = sc.ports[a].type;
	
	// Debug report
	System.out.println(sc.signals.size() + " Signals");
	System.out.println(sc.gates.size() + " Gates");
	for(a=0; a<sc.gates.size(); a++)
	  {
	     Gate gate1 = (Gate) sc.gates.elementAt(a);
	     gate1.DebugReport(1);
	     for (int b=0; b<8; b++)
	       if (gate1.portSignals[b].externalSignal!=null)
		 System.out.println(a + ": " + gate1.type 
				    + " gate["
				    + b
				    + "] = Signal "
				    + sc.signals.indexOf(gate1.portSignals[b].externalSignal));
	  }
	for (a=0; a<8; a++)
	  if (sc.portSignals[a].internalSignal != null)
	    System.out.println("PortSignal " 
			       + a 
			       + " = Signal " 
			       + sc.signals.indexOf(sc.portSignals[a].internalSignal));
	
	sc.speed=chipSpeed;
	if (pc.label != null)
	  sc.label = new String(pc.label);
	if (pc.description != null)
	  sc.description = new String(pc.description);
	sc.GenerateIcons();
	pc.grabbable = true;
	sc.grabbable = true;
  }

}
