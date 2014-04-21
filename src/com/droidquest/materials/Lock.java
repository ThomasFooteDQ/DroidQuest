package com.droidquest.materials;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.items.Key;

public class Lock extends Material {
    // Generic Lock; Used to redefine materials in the local room.

    private int doorState = 0;
    private Color keyColor;
    private transient Key latchKey = null;
    private transient Room room;
    private transient Room currentRoom;
    static public int NARROW = -1;
    static public int WIDE = -2;
    static public int REMOVE = -3;
    private static int RESET = -4;
    static public int LEFT = -5;
    static public int RIGHT = -6;
    static public int UP = -7;
    static public int DOWN = -8;
    private int[][] program;

    // program[][] is an array of arrays. Each array holds the behavior of a
    // single value of doorState.
    //
    // A single array can hold one of the following:
//	    A single value of Lock.NARROW, Lock.WIDE, or Lock.REMOVE to define a pause
//	    A single value of RESET, LEFT, RIGHT, UP, or DOWN to change rooms
//	    A series of triplets (X,Y,M) with the XY position and the Materials
//	    Index.
    //
    // Pause value can be one of the following:
    // Lock.NARROW = Pause until the key is placed once more precisely into the lock.
    // Lock.WIDE   = Pause until the key is placed ANYWHERE into the lock
    // Lock.REMOVE = Pause until the key is removed.
    //
    // Pause values automatically reset the current Room to the original value.
    //
    // Lock.RESET  = Set current room to the original room value
    // Lock.LEFT   = Change the current room to the room's left room
    // Lock.RIGHT  = Same, but right
    // Lock.UP     = Same, but up
    // Lock.DOWN   = Same, but down
    //
    // The room is normally the key's rom when the key touches the lock, and
    // the triplets change the materials within the current room. With these
    // commands the current room can be changed so other rooms can be
    // manipulated.
    //
    // Here's a sample program[][]
    //
    // int[][] = {
//	            {Lock.NARROW},    // Wait for precise placement
//	            {10,5,0, 11,5,0}, // Converts two spots to holes
//	            {10,6,0, 11,6,0}, // Same, but lower
//	            {Lock.NARROW},     // Wait again
//	            {10,6,1, 11,6,1}, // Converts two spots to wall
//	            {10,5,1, 11,5,1}  // same, in reverse, go to pause.
    // };
    //

    public Lock(Color lc, Color kc, int[][] prg) {
        super(true, false);
        color = lc;
        keyColor = kc;
        program = prg;
        GenerateIcons();
    }

    public void GenerateIcons() {
        BufferedImage bi = new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g;
        try {
            g = bi.getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }

        g.setColor(color);
        g.fillRect(0, 0, 28, 32);
        g.setColor(Color.black);
        g.fillRect(0, 14, 16, 2);
        g.fillRect(0, 16, 12, 2);
        g.fillRect(2, 18, 6, 2);
        icon = new ImageIcon(bi);
    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (keyColor == ((Lock) mat).keyColor
                    && program == ((Lock) mat).program) {
                return true;
            }
        }
        return false;
    }

    public void Animate() {
        if (doorState == program.length) {
            doorState = 0;
        }

        if (latchKey == null) {
            if (program[doorState][0] == Lock.REMOVE) {
                for (int a = 0; a < level.items.size(); a++) {
                    Item item = level.items.elementAt(a);
                    if (item instanceof Key) {
                        Key testKey = (Key) item;
                        if (testKey.color.equals(keyColor)) {
                            latchKey = testKey;
                            room = latchKey.room;
                            currentRoom = room;
                        }
                    }
                }
            }
        }

        if (latchKey == null) {
            return;
        }


        if (program[doorState].length > 1) {
            for (int a = 0; a < program[doorState].length / 3; a++) {
                currentRoom.SetMaterial(program[doorState][a * 3],
                        program[doorState][a * 3 + 1],
                        program[doorState][a * 3 + 2]);
            }
            doorState++;
        }
        else {
            if (program[doorState][0] == REMOVE) {
                currentRoom = room;
                Dimension d = latchKey.GetXY();
                int bigXL = d.width / 28;
                int bigXR = (d.width + latchKey.getWidth()) / 28;
                int bigYT = d.height / 32;
                int bigYB = (d.height + latchKey.getHeight()) / 32;
                boolean flag = false;
                if (room.MaterialArray[bigYT][bigXL] == this) {
                    flag = true;
                }
                if (room.MaterialArray[bigYT][bigXR] == this) {
                    flag = true;
                }
                if (room.MaterialArray[bigYB][bigXL] == this) {
                    flag = true;
                }
                if (room.MaterialArray[bigYB][bigXR] == this) {
                    flag = true;
                }
                if (!flag) {
                    doorState++;
                }
            }
            else if (program[doorState][0] == RESET) {
                currentRoom = room;
                doorState++;
            }
            else if (program[doorState][0] == LEFT) {
                currentRoom = currentRoom.leftRoom;
                if (currentRoom == null) {
                    currentRoom = room;
                }
                doorState++;
            }
            else if (program[doorState][0] == RIGHT) {
                currentRoom = currentRoom.rightRoom;
                if (currentRoom == null) {
                    currentRoom = room;
                }
                doorState++;
            }
            else if (program[doorState][0] == UP) {
                currentRoom = currentRoom.upRoom;
                if (currentRoom == null) {
                    currentRoom = room;
                }
                doorState++;
            }
            else if (program[doorState][0] == DOWN) {
                currentRoom = currentRoom.downRoom;
                if (currentRoom == null) {
                    currentRoom = room;
                }
                doorState++;
            }
        }

        if (doorState == program.length) {
            doorState = 0;
        }
    }

    public void TouchedByItem(Item item) {
        if (item instanceof Key) {
            if (((Key) item).color.equals(keyColor)) {
                latchKey = (Key) item;
                room = latchKey.room;
            }
        }

        if (latchKey == null) {
            return;
        }

        if (latchKey != item) {
            return;
        }

        if (program[doorState].length == 1) {
            if (program[doorState][0] == NARROW) {
                currentRoom = room;
                Dimension d = latchKey.GetXY();
                int X = d.width % 28;
                int Y = d.height % 32;
                if (X >= 16 && X <= 20
                        && Y >= 10 && Y <= 14) {
                    doorState++;
                }
            }
            else if (program[doorState][0] == WIDE) {
                currentRoom = room;
                doorState++;
            }
        }

        if (doorState == program.length) {
            doorState = 0;
        }

    }

}