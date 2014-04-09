package com.droidquest;

import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Room implements Serializable, Cloneable {
    public transient static Level level;
    public transient Room upRoom;
    public transient Room downRoom;
    public transient Room rightRoom;
    public transient Room leftRoom;
    public transient Item portalItem = null;

    public int[][] RoomArray = { // Array of image references
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    public transient Material[][] MaterialArray = new Material[12][20];
    public Vector<TextBox> textBoxes = new Vector<TextBox>();
    public Vector<Wire> wires = new Vector<Wire>();
    public Vector<Graphix> graphix = new Vector<Graphix>();
    public Vector<Arrow> arrows = new Vector<Arrow>();
    public boolean editable;

    public Room() {
        upRoom = this;
        downRoom = this;
        rightRoom = this;
        leftRoom = this;
        editable = false;
    }

    public void writeRef(ObjectOutputStream s) throws IOException {
        s.writeInt(level.rooms.indexOf(upRoom));
        s.writeInt(level.rooms.indexOf(downRoom));
        s.writeInt(level.rooms.indexOf(rightRoom));
        s.writeInt(level.rooms.indexOf(leftRoom));
        s.writeInt(level.items.indexOf(portalItem));
        s.writeInt(wires.size());
        for (int a = 0; a < wires.size(); a++) {
            wires.elementAt(a).writeRef(s);
        }
    }

    public void readRef(ObjectInputStream s) throws IOException {
        upRoom = level.FindRoom(s.readInt());
        downRoom = level.FindRoom(s.readInt());
        rightRoom = level.FindRoom(s.readInt());
        leftRoom = level.FindRoom(s.readInt());
        portalItem = level.FindItem(s.readInt());

        int numWires = s.readInt();
        wires = new Vector<Wire>();
        for (int a = 0; a < numWires; a++) {
            Wire wire = new Wire();
            wires.addElement(wire);
            wire.readRef(s, level);
        }

        for (int a = 0; a < graphix.size(); a++) {
            graphix.elementAt(a).GenerateIcons();
        }

        GenerateArray();
    }

    public void GenerateArray() {
        MaterialArray = new Material[12][20];
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 20; x++) {
                MaterialArray[y][x] = level.materials.elementAt(RoomArray[y][x]);
            }
        }
    }

    public void SetMaterial(int X, int Y, int index) {
        Material mat = level.materials.elementAt(index);
        if (mat != null) {
            RoomArray[Y][X] = index;
            MaterialArray[Y][X] = mat;
        }
    }

    public void SetMaterial(int X, int Y, Material mat) {
        int index = level.materials.indexOf(mat);
        RoomArray[Y][X] = index;
        MaterialArray[Y][X] = mat;
    }

    public void SetMaterialFill(int X1, int Y1, int X2, int Y2, int index) {
        Material mat = level.materials.elementAt(index);
        if (mat != null) {
            for (int Y = Y1; Y <= Y2; Y++) {
                for (int X = X1; X <= X2; X++) {
                    RoomArray[Y][X] = index;
                    MaterialArray[Y][X] = mat;
                }
            }
        }
    }

    public void SetMaterialOutline(int X1, int Y1, int X2, int Y2, int index) {
        Material mat = level.materials.elementAt(index);
        if (mat != null) {
            for (int Y = Y1; Y <= Y2; Y++) {
                RoomArray[Y][X1] = index;
                MaterialArray[Y][X1] = mat;
                RoomArray[Y][X2] = index;
                MaterialArray[Y][X2] = mat;
            }

            for (int X = X1; X <= X2; X++) {
                RoomArray[Y1][X] = index;
                MaterialArray[Y1][X] = mat;
                RoomArray[Y2][X] = index;
                MaterialArray[Y2][X] = mat;
            }
        }
    }

    public void SetMaterialFromRoom(int roomIndex) {
        Room r = level.rooms.elementAt(roomIndex);
        for (int Y = 0; Y < 12; Y++) {
            for (int X = 0; X < 20; X++) {
                RoomArray[Y][X] = r.RoomArray[Y][X];
                MaterialArray[Y][X] = r.MaterialArray[Y][X];
            }
        }
    }

    public void AddTextBox(String t, int X, int Y, int W) {
        TextBox newText = new TextBox(t, X, Y, W);
        textBoxes.addElement(newText);
    }

    public void AddArrow(int X, int Y, int dir, int len, Color col) {
        Arrow newArrow = new Arrow(X, Y, dir, len, col);
        arrows.addElement(newArrow);
    }

    public void AddGraphix(String t, int X, int Y) {
        Graphix newGraphix = new Graphix(t, X, Y);
        graphix.addElement(newGraphix);
    }

    public void AddGraphix(String[] t, int X, int Y) {
        Graphix newGraphix = new Graphix(t, X, Y);
        graphix.addElement(newGraphix);
    }

    public void DrawTextBoxes(Graphics g, RoomDisplay rd) {
        for (int a = 0; a < textBoxes.size(); a++) {
            TextBox textBox = textBoxes.elementAt(a);
            g.setColor(Color.white);
            g.setFont(rd.smallFont);

            int cursX = textBox.x;
            int cursY = textBox.y;
            int advY = 0;
            int advX;
            String nextWord;
            int indexFrom = 0;
            int indexTo;

            do {
                // Get the next word in the string
                if (indexFrom >= textBox.textString.lastIndexOf(" ")) {
                    indexTo = textBox.textString.length();
                }
                else {
                    indexTo = textBox.textString.indexOf(" ", indexFrom + 1);
                }
                nextWord = textBox.textString.substring(indexFrom, indexTo);
                if (nextWord.startsWith(" ")) {
                    nextWord = nextWord.substring(1, nextWord.length());
                }
                if (!nextWord.endsWith(" ")) {
                    nextWord = nextWord + " ";
                }

                if (nextWord.startsWith("{BIG}")) {
                    g.setFont(rd.bigFont);
                }

                else if (nextWord.startsWith("{SML}")) {
                    g.setFont(rd.smallFont);
                }

                else if (nextWord.startsWith("{BSP}")) {
                    FontMetrics fm = g.getFontMetrics();
                    advX = fm.stringWidth(" ");
                    cursX -= advX;
                }

                // if (nextWord fits "{rrr,ggg,bbb} "
                else if (nextWord.startsWith("{")
                        && nextWord.endsWith("} ")
                        && nextWord.length() == 14) {
                    // extract rrr,ggg,bbb
                    Integer rr = new Integer(nextWord.substring(1, 4));
                    Integer gg = new Integer(nextWord.substring(5, 8));
                    Integer bb = new Integer(nextWord.substring(9, 12));
                    g.setColor(new Color(rr.intValue(),
                            gg.intValue(),
                            bb.intValue()));
                }
                else {
                    FontMetrics fm = g.getFontMetrics();
                    if (fm.getAscent() > advY) {
                        advY = fm.getAscent();
                    }
                    advX = fm.stringWidth(nextWord);
                    if (cursX + advX > textBox.width + textBox.x) {
                        cursX = textBox.x;
                        cursY += advY;
                        advY = fm.getAscent();
                    }
                    g.drawString(nextWord, cursX, cursY);
                    cursX += advX;
                    if (cursX + advX > textBox.width + textBox.x) {
                        cursX = textBox.x;
                        cursY += advY;
                        advY = fm.getAscent();
                    }
                }
                indexFrom = indexTo;
            }
            while (indexFrom < textBox.textString.length());
        }
    }

    public void DrawGraphix(Graphics g, RoomDisplay rd) {
        for (int a = 0; a < graphix.size(); a++) {
            Graphix grx = graphix.elementAt(a);
            grx.Draw(g, rd);
        }
    }

    public void DrawArrows(Graphics g) {
        for (int a = 0; a < arrows.size(); a++) {
            arrows.elementAt(a).Draw(g);
        }
    }

    public Room getUpRoom(Item item) {
        return upRoom;
    }

    public Room getDownRoom(Item item) {
        return downRoom;
    }

    public Room getLeftRoom(Item item) {
        return leftRoom;
    }

    public Room getRightRoom(Item item) {
        return rightRoom;
    }

    public Wire FindWire(int wireIndex) {
        if (wireIndex == -1) {
            return null;
        }
        if (wireIndex >= wires.size()) {
            return null;
        }
        return wires.elementAt(wireIndex);
    }

    public Object clone() {
        Object newObject = null;
        try {
            newObject = super.clone();
        }
        catch (CloneNotSupportedException e) {
        }
        return newObject;
    }

    public void Erase() {
        upRoom = null;
        downRoom = null;
        rightRoom = null;
        leftRoom = null;
        portalItem = null;
        arrows.clear();
        graphix.clear();
        for (int a = 0; a < wires.size(); a++) {
            Wire wire = wires.elementAt(a);
            wire.fromPort = null;
            wire.toPort = null;
            wire.inPort = null;
            wire.outPort = null;
        }
        wires.clear();
    }

} 
