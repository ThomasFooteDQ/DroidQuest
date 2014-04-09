package com.droidquest.chipstuff;

import com.droidquest.devices.SmallChip;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Gate implements Serializable {
    public transient PortSignal[] portSignals = new PortSignal[8];
    public boolean state;
    public String type;
    public int speed;

    public Vector<Signal> mySignals = new Vector<Signal>();
    public Vector<Gate> myGates = new Vector<Gate>();

    public Gate(String t) {
        // Called whenever a non-chip gate is created.
        type = t;
        speed = 1;
        for (int a = 0; a < 8; a++) {
            portSignals[a] = new PortSignal();
        }
    }

    public Gate(SmallChip sc) {
        // Called by ChipCompiler to put a nested chip into a gate
        speed = sc.speed;
        type = "Chip";

        portSignals = new PortSignal[8];
        for (int a = 0; a < 8; a++) {
            portSignals[a] = new PortSignal();
        }
        for (int a = 0; a < sc.signals.size(); a++) {
            Signal newsig = new Signal();
            Signal oldsig = sc.signals.elementAt(a);
            newsig.Set(oldsig.Get());
            newsig.working = oldsig.working;
            mySignals.addElement(newsig);
        }

        for (int a = 0; a < sc.gates.size(); a++) {
            Gate oldgate = sc.gates.elementAt(a);
            Gate newgate = new Gate(oldgate);
            myGates.addElement(newgate);
            for (int b = 0; b < 8; b++) {
                if (oldgate.portSignals[b].externalSignal != null) {
                    int sigIndex = sc.signals.indexOf(oldgate.portSignals[b].externalSignal);
                    newgate.portSignals[b].externalSignal = mySignals.elementAt(sigIndex);
                }
            }
        }

        for (int a = 0; a < 8; a++) {
            if (sc.portSignals[a].internalSignal != null) {
                int sigIndex = sc.signals.indexOf(sc.portSignals[a].internalSignal);
                portSignals[a].internalSignal = mySignals.elementAt(sigIndex);
                portSignals[a].type = sc.portSignals[a].type;
            }
        }

    }

    public Gate(Gate g) {
        // Create a new Gate based off an existing one
        type = g.type;
        state = g.state;
        speed = g.speed;
        portSignals = new PortSignal[8];
        for (int a = 0; a < 8; a++) {
            portSignals[a] = new PortSignal();
        }
        if (type.equalsIgnoreCase("Chip")) {
            for (int a = 0; a < g.mySignals.size(); a++) {
                Signal newsig = new Signal();
                Signal oldsig = g.mySignals.elementAt(a);
                newsig.Set(oldsig.Get());
                newsig.working = oldsig.working;
                mySignals.addElement(newsig);
            }
            for (int a = 0; a < g.myGates.size(); a++) {
                Gate oldgate = g.myGates.elementAt(a);
                Gate newgate = new Gate(oldgate);
                myGates.addElement(newgate);
                for (int b = 0; b < 8; b++) {
                    int signalIndex = g.mySignals.indexOf(oldgate.portSignals[b].externalSignal);
                    if (signalIndex != -1) {
                        newgate.portSignals[b].externalSignal = mySignals.elementAt(signalIndex);
                    }
                }
            }
            for (int a = 0; a < 8; a++) {
                if (g.portSignals[a].internalSignal != null) {
                    int sigIndex = g.mySignals.indexOf(g.portSignals[a].internalSignal);
                    portSignals[a].internalSignal = mySignals.elementAt(sigIndex);
                    portSignals[a].type = g.portSignals[a].type;
                }
            }
        }
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        for (int a = 0; a < 8; a++) {
            s.writeInt(mySignals.indexOf(portSignals[a].internalSignal));
        }
        for (int a = 0; a < myGates.size(); a++) {
            Gate gate = myGates.elementAt(a);
            for (int b = 0; b < 8; b++) {
                s.writeInt(mySignals.indexOf(gate.portSignals[b].externalSignal));
                s.writeInt(gate.portSignals[b].type);
            }
            gate.writeRef(s);
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        for (int a = 0; a < 8; a++) {
            int portIndex = s.readInt();
            if (portIndex >= 0) {
                portSignals[a].internalSignal = mySignals.elementAt(portIndex);
            }
        }
        for (int a = 0; a < myGates.size(); a++) {
            Gate gate = myGates.elementAt(a);
            gate.portSignals = new PortSignal[8];
            for (int b = 0; b < 8; b++) {
                gate.portSignals[b] = new PortSignal();
                int sigIndex = s.readInt();
                if (sigIndex >= 0) {
                    gate.portSignals[b].externalSignal = mySignals.elementAt(sigIndex);
                }
                gate.portSignals[b].type = s.readInt();
            }
            gate.readRef(s);
        }
    }

    public void Function() {
        if (type.equalsIgnoreCase("AND")) {
            portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get()
                    & portSignals[1].externalSignal.Get());
        }
        else if (type.equalsIgnoreCase("OR")) {
            portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get()
                    | portSignals[1].externalSignal.Get());
        }
        else if (type.equalsIgnoreCase("NOT")) {
            portSignals[1].externalSignal.Set(!portSignals[0].externalSignal.Get());
        }
        else if (type.equalsIgnoreCase("XOR")) {
            portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get()
                    ^ portSignals[1].externalSignal.Get());
        }
        else if (type.equalsIgnoreCase("FF")) {
            if (portSignals[0].externalSignal.Get() ^ portSignals[1].externalSignal.Get()) {
                state = portSignals[0].externalSignal.Get();
            }
            portSignals[2].externalSignal.Set(state);
            portSignals[3].externalSignal.Set(!state);
        }
        else if (type.equalsIgnoreCase("NODE")) {
            portSignals[1].externalSignal.Set(portSignals[0].externalSignal.Get());
            portSignals[2].externalSignal.Set(portSignals[0].externalSignal.Get());
            if (portSignals[3].externalSignal != null) {
                portSignals[3].externalSignal.Set(portSignals[0].externalSignal.Get());
            }
        }
        else if (type.equalsIgnoreCase("Chip")) {

            for (int s = 0; s < speed; s++) {
                for (int a = 0; a < mySignals.size(); a++) {
                    mySignals.elementAt(a).Flip();
                }

                for (int a = 0; a < 8; a++) {
                    if (portSignals[a].externalSignal != null
                            && portSignals[a].internalSignal != null) {
                        if (portSignals[a].type == Port.TYPE_INPUT) {
                            portSignals[a].internalSignal.Set(portSignals[a].externalSignal.Get());
                        }
                    }
                }

                for (int a = 0; a < myGates.size(); a++) {
                    myGates.elementAt(a).Function();
                }

                for (int a = 0; a < 8; a++) {
                    if (portSignals[a].externalSignal != null
                            && portSignals[a].internalSignal != null) {
                        if (portSignals[a].type == Port.TYPE_OUTPUT) {
                            portSignals[a].externalSignal.Set(portSignals[a].internalSignal.Get());
                        }
                    }
                }
            }
        }
    }

    public void SaveSubGate(ObjectOutputStream s) throws IOException {
        s.writeInt(mySignals.size());
        for (int a = 0; a < mySignals.size(); a++) {
            Signal sig = mySignals.elementAt(a);
            s.writeBoolean(sig.Get());
            s.writeBoolean(sig.working);
        }

        for (int a = 0; a < 8; a++) {
            s.writeInt(mySignals.indexOf(portSignals[a].internalSignal));
            s.writeInt(portSignals[a].type);
        }

        s.writeInt(myGates.size());
        for (int a = 0; a < myGates.size(); a++) {
            Gate gate = myGates.elementAt(a);
            s.writeObject(gate.type);
            s.writeBoolean(gate.state);
            for (int b = 0; b < 8; b++) {
                s.writeInt(mySignals.indexOf(gate.portSignals[b].externalSignal));
            }
            if (gate.type.equalsIgnoreCase("Chip")) {
                gate.SaveSubGate(s);
            }
        }
        s.writeInt(speed);
    }

    public void LoadSubGate(ObjectInputStream s) throws IOException, ClassNotFoundException {
        int numSignals = s.readInt();
        mySignals = new Vector<Signal>();
        for (int a = 0; a < numSignals; a++) {
            Signal newSignal = new Signal();
            newSignal.Set(s.readBoolean());
            newSignal.working = s.readBoolean();
            mySignals.addElement(newSignal);
        }

        for (int a = 0; a < 8; a++) {
            int sigIndex = s.readInt();
            if (sigIndex >= 0) {
                portSignals[a].internalSignal = mySignals.elementAt(sigIndex);
            }
            portSignals[a].type = s.readInt();
        }

        int numGates = s.readInt();
        for (int a = 0; a < numGates; a++) {
            Gate newGate = new Gate((String) s.readObject());
            newGate.state = s.readBoolean();
            myGates.addElement(newGate);
            for (int b = 0; b < 8; b++) {
                int sigIndex = s.readInt();
                if (sigIndex >= 0) {
                    newGate.portSignals[b].externalSignal = mySignals.elementAt(sigIndex);
                }
            }
            if (newGate.type.equalsIgnoreCase("Chip")) {
                newGate.LoadSubGate(s);
            }
        }
        speed = s.readInt();
    }
}
