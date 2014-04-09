package com.droidquest.devices;

import com.droidquest.Room;
import com.droidquest.Wire;
import com.droidquest.chipstuff.*;
import com.droidquest.decorations.TextBox;
import com.droidquest.levels.Level;
import com.droidquest.materials.ChipTester;
import com.droidquest.materials.ChipTrash;
import com.droidquest.materials.SmallChipBurner;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;

public class SmallChip extends GenericChip {
    public int speed;

    public transient PortSignal[] portSignals = new PortSignal[8];
    public Vector<Signal> signals = new Vector<Signal>();
    public Vector<Gate> gates = new Vector<Gate>();

    public SmallChip(int X, int Y, Room r, String l) {
        x = X;
        y = Y;
        room = r;
        label = l;
        width = 26;
        height = 30;
        speed = 1;
        GenerateIcons();
        for (int a = 0; a < 8; a++) {
            portSignals[a] = new PortSignal();
        }
    }

    public void GenerateIcons() {
        if (ports == null) {
            ports = new Port[8];
            ports[0] = new Port(0, 3, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[1] = new Port(0, 11, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[2] = new Port(0, 19, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[3] = new Port(0, 27, Port.TYPE_UNDEFINED, 0, Port.ROT_LEFT, this);
            ports[4] = new Port(25, 26, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[5] = new Port(25, 18, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[6] = new Port(25, 10, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
            ports[7] = new Port(25, 2, Port.TYPE_UNDEFINED, 0, Port.ROT_RIGHT, this);
        }
        ChipText chiptext = new ChipText(this);
        chiptext.setTitle("Pinout for Chip " + label);
        if (portSignals == null) {
            portSignals = new PortSignal[8];
            for (int a = 0; a < 8; a++) {
                portSignals[a] = new PortSignal();
            }
        }
        icons = new ImageIcon[1];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(transparent);
        g2.clearRect(0, 0, width, height);
        g.setColor(Color.blue);
        g.fillRect(4, 0, 18, 30);
        g.fillRect(0, 2, 26, 2);
        g.fillRect(0, 10, 26, 2);
        g.fillRect(0, 18, 26, 2);
        g.fillRect(0, 26, 26, 2);
        g.setColor(Color.black);
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        label = String.valueOf(label.charAt(0));
        int X = fm.stringWidth(label);
        g.drawString(label, 13 - X / 2, 22);
        currentIcon = icons[0].getImage();
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        super.writeRef(s);
        for (int a = 0; a < 8; a++) {
            s.writeInt(signals.indexOf(portSignals[a].internalSignal));
            s.writeInt(portSignals[a].type);
        }
        for (int a = 0; a < gates.size(); a++) {
            Gate gate = gates.elementAt(a);
            for (int b = 0; b < 8; b++) {
                s.writeInt(signals.indexOf(gate.portSignals[b].externalSignal));
                s.writeInt(gate.portSignals[b].type);
            }
            gate.writeRef(s);
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        super.readRef(s);
        GenerateIcons();
        for (int a = 0; a < 8; a++) {
            int portIndex = s.readInt();
            if (portIndex >= 0) {
                portSignals[a].internalSignal = signals.elementAt(portIndex);
            }
            portSignals[a].type = s.readInt();
        }
        for (int a = 0; a < gates.size(); a++) {
            Gate gate = gates.elementAt(a);
            gate.portSignals = new PortSignal[8];
            for (int b = 0; b < 8; b++) {
                gate.portSignals[b] = new PortSignal();
                int sigIndex = s.readInt();
                if (sigIndex >= 0) {
                    gate.portSignals[b].externalSignal = signals.elementAt(sigIndex);
                }
                gate.portSignals[b].type = s.readInt();
            }
            gate.readRef(s);
        }
    }

    public void Decorate() {
    }

    public void IsDropped() {
        inBurner = false;
        inTester = false;
        int bigXl = (x) / 28;
        int bigXr = (x + width - 1) / 28;
        int bigYt = (y) / 32;
        int bigYb = (y + height - 1) / 32;

        if (bigXr > 19) {
            bigXr = 19;
        }
        if (bigYb > 11) {
            bigYb = 11;
        }

        for (int a = bigYt; a <= bigYb; a++) {
            for (int b = bigXl; b <= bigXr; b++) {
                if (room.MaterialArray[a][b] instanceof SmallChipBurner) {
                    x = 13 * 28 - width / 2;
                    y = 8 * 32 - height / 2;
                    inBurner = true;
                    ChipCompiler.chipSpeed = speed;
                    TextBox tb = room.textBoxes.elementAt(1);
                    tb.textString = speed + "x";
                    return;
                }
                if (room.MaterialArray[a][b] instanceof ChipTrash) {
                    SetRoom(null); // Cheap way to remove the wires;
                    level.items.removeElement(this);
                    level.PlaySound(room, Level.DISCHARGESOUND);
                    return;
                }
                if (room.MaterialArray[a][b] instanceof ChipTester) {
                    x = 10 * 28 - width / 2;
                    y = 5 * 32 - height / 2;
                    inTester = true;
                    return;
                }
            }
        }
    }

    public boolean Function() {
        int a;

        for (int s = 0; s < speed; s++) {
            for (a = 0; a < signals.size(); a++) {
                signals.elementAt(a).Flip();
            }

            for (a = 0; a < 8; a++) {
                if (ports[a].type == Port.TYPE_INPUT) {
                    if (portSignals[a].internalSignal != null) {
                        portSignals[a].internalSignal.Set(ports[a].value);
                    }
                }
            }

            for (a = 0; a < gates.size(); a++) {
                gates.elementAt(a).Function();
            }

            for (a = 0; a < 8; a++) {
                if (ports[a].type == Port.TYPE_OUTPUT) {
                    if (portSignals[a].internalSignal != null) {
                        ports[a].value = portSignals[a].internalSignal.Get();
                    }
                }
            }
        }
        return false;
    }

    public void Erase() {
        super.Erase();
        portSignals = null;
        signals = null;
        gates = null;
    }

    public void Empty() {
        if (signals != null) {
            signals.removeAllElements();
        }
        signals = new Vector<Signal>();
        if (gates != null) {
            gates.removeAllElements();
        }
        gates = new Vector<Gate>();
        if (portSignals == null) {
            portSignals = new PortSignal[8];
            for (int a = 0; a < 8; a++) {
                portSignals[a] = new PortSignal();
            }
        }
        for (int a = 0; a < 8; a++) {
            portSignals[a].externalSignal = null;
            portSignals[a].internalSignal = null;
            portSignals[a].type = Port.TYPE_UNDEFINED;
        }
    }

    public void LoadChip(String filename) {
        try {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream s = new ObjectInputStream(in);

            // Signals
            int numSignals = s.readInt();
            signals = new Vector<Signal>();
            for (int a = 0; a < numSignals; a++) {
                Signal sig = new Signal();
                sig.Set(s.readBoolean());
                sig.working = s.readBoolean();
                signals.addElement(sig);
            }

            // Gates
            int numGates = s.readInt();
            gates = new Vector<Gate>();
            for (int a = 0; a < numGates; a++) {
                Gate gate = new Gate((String) s.readObject());
                gates.addElement(gate);
                gate.state = s.readBoolean();
                for (int b = 0; b < 8; b++) {
                    int sigIndex = s.readInt();
                    if (sigIndex >= 0) {
                        gate.portSignals[b].externalSignal = signals.elementAt(sigIndex);
                    }
                    gate.portSignals[b].type = s.readInt();
                }
                if (gate.type.equalsIgnoreCase("Chip")) {
                    gate.LoadSubGate(s);
                }
            }

            // PortSignals
            for (int a = 0; a < 8; a++) {
                int sigIndex = s.readInt();
                if (sigIndex >= 0) {
                    portSignals[a].internalSignal = signals.elementAt(sigIndex);
                }
                ports[a].type = s.readInt();
                portSignals[a].type = ports[a].type;
            }

            // Description
            description = (String) s.readObject();

            // Speed
            speed = s.readInt();

            s.close();
            in.close();
        }
        catch (ClassNotFoundException e) {
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            return;
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

        for (int a = 0; a < 8; a++) {
            if (ports[a].myWire != null) {
                Wire wire = ports[a].myWire;
                if (wire.fromPort == ports[a]) {
                    if (wire.toPort.type == ports[a].type) {
                        wire.Remove();
                    }
                }
                else if (wire.toPort == ports[a]) {
                    if (wire.fromPort.type == ports[a].type) {
                        wire.Remove();
                    }
                }
            }
        }
    }

    public void SaveChip(String filename) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream s = new ObjectOutputStream(out);

            // Signals
            s.writeInt(signals.size());
            for (int a = 0; a < signals.size(); a++) {
                Signal sig = signals.elementAt(a);
                s.writeBoolean(sig.Get());
                s.writeBoolean(sig.working);
            }

            // Gates
            s.writeInt(gates.size());
            for (int a = 0; a < gates.size(); a++) {
                Gate gate = gates.elementAt(a);
                s.writeObject(gate.type);
                s.writeBoolean(gate.state);
                for (int b = 0; b < 8; b++) {
                    s.writeInt(signals.indexOf(gate.portSignals[b].externalSignal));
                    s.writeInt(gate.portSignals[b].type);
                }
                if (gate.type.equalsIgnoreCase("Chip")) {
                    gate.SaveSubGate(s);
                }
            }
            // PortSignals
            for (int a = 0; a < 8; a++) {
                s.writeInt(signals.indexOf(portSignals[a].internalSignal));
                s.writeInt(ports[a].type);
            }

            // Description
            s.writeObject(description);

            // Speed
            s.writeInt(speed);

            s.flush();
            s.close();
            out.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }

    }

}
