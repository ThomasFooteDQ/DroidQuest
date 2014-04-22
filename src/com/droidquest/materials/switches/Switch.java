package com.droidquest.materials.switches;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.materials.Material;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class Switch extends Material {
    // Programmable wall switch, normally used for opening doors when
    // touched. Each line in the program is an array of numbers, with the
    // first explaining what function the array of numbers performs.
    //
    // Wait4Contact
    // Wait4Removal
    // Wait4Time x
    // SetValueHigh
    // SetValueLow
    // Replace (x,y,m)...
    // LeftRoom
    // RightRoom
    // UpRoom
    // DownRoom
    // ResetRoom

    public static final int ROT_UP = 0; // Rotation refers to which wall the switch is attached to
    public static final int ROT_RIGHT = 1;
    public static final int ROT_DOWN = 2;
    protected static final int ROT_LEFT = 3;
    private int rotation;
    private transient ImageIcon[] images;
    protected boolean value;

    public static final int WAIT4CONTACT = -1;
    public static final int WAIT4REMOVAL = -2;
    public static final int WAIT4TIME = -3;
    public static final int SETVALUEHIGH = -4;
    public static final int SETVALUELOW = -5;
    public static final int REPLACE = -6;
    private static final int LEFTROOM = -7;
    private static final int RIGHTROOM = -8;
    private static final int UPROOM = -9;
    public static final int DOWNROOM = -10;
    private static final int RESETROOM = -11;
    //public static final int WAIT4PLAYERCONTACT = -12;
    //If I actually add this, it'll change the signature.

    private int switchState = 0;
    private transient Room room;
    private transient Room currentRoom;
    private int[][] program;
    private Item trigger = null;
    private Date timeout;
    private boolean timing = false;

    protected Switch(int rot) {
        super(true, false);
        program = new int[][]{{0}};
        GenerateIcons();
        rotation = rot;
    }

    public Switch(int rot, int[][] p) {
        super(true, false);
        program = p;
        GenerateIcons();
        rotation = rot;
    }

    public void GenerateIcons() {
        images = new ImageIcon[2];
        for (int a = 0; a < 2; a++) {
            images[a] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
            Graphics g;
            try {
                g = images[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setBackground(Color.black);
            g2.clearRect(0, 0, 28, 32);
            if (a == 0) {
                g2.setColor(Color.white);
            }
            else {
                g2.setColor(new Color(255, 128, 0));
            }

            switch (rotation) {
                case ROT_UP:
                    g2.fillRect(2, 10, 24, 6);
                    g2.fillRect(10, 0, 8, 10);
                    break;
                case ROT_RIGHT:
                    g2.fillRect(12, 4, 6, 24);
                    g2.fillRect(18, 12, 10, 8);
                    break;
                case ROT_DOWN:
                    g2.fillRect(2, 16, 24, 6);
                    g2.fillRect(10, 22, 8, 10);
                    break;
                case ROT_LEFT:
                    g2.fillRect(10, 4, 6, 24);
                    g2.fillRect(0, 12, 10, 8);
                    break;
            }
        }

    }

    public boolean equals(Material mat) {
        if (super.equals(mat)) {
            if (rotation == ((Switch) mat).rotation
                    && value == ((Switch) mat).value
                    && program == ((Switch) mat).program) {
                return true;
            }
        }
        return false;
    }

    public void TouchedByItem(Item item) {
        if (switchState == program.length) {
            switchState = 0;
        }

        if (program[switchState][0] == WAIT4CONTACT) {
            trigger = item;
            room = item.room;
            currentRoom = room;
            switchState++;
        }

        if (program[switchState][0] == -12) // == WAIT4PLAYERCONTACT
        {
            if (item == level.player) {
                trigger = item;
                room = item.room;
                currentRoom = room;
                switchState++;
            }
        }
    }

    public void Animate() {
        if (switchState == program.length) {
            switchState = 0;
        }

        if (value) {
            icon = images[1];
        }
        else {
            icon = images[0];
        }

        switch (program[switchState][0]) {
            case WAIT4REMOVAL: {
                Dimension d = trigger.GetXY();
                int bigXL = d.width / 28;
                int bigXR = (d.width + trigger.getWidth()) / 28;
                int bigYT = d.height / 32;
                int bigYB = (d.height + trigger.getHeight()) / 32;
                boolean removed = true;
                for (int Y = bigYT; Y < bigYB; Y++) {
                    for (int X = bigXL; X < bigXR; X++) {
                        if (trigger.room.MaterialArray[Y][X] == this) {
                            removed = false;
                        }
                    }
                }
                if (removed) {
                    switchState++;
                }
                currentRoom = room;
            }
            break;
            case WAIT4TIME:
                if (timing) {
                    Date now = new Date();
                    if (now.getTime() >= timeout.getTime()) {
                        switchState++;
                        timing = false;
                        currentRoom = room;
                    }
                }
                else {
                    timeout = new Date(new Date().getTime() + 1000 * program[switchState][1]);
                    timing = true;
                }
                break;
            case SETVALUEHIGH:
                value = true;
                switchState++;
                break;
            case SETVALUELOW:
                value = false;
                switchState++;
                break;
            case REPLACE:
                if (currentRoom == null) {
                    currentRoom = trigger.room;
                }
                for (int a = 0; a < (program[switchState].length - 1) / 3; a++) {
                    currentRoom.SetMaterial(program[switchState][a * 3 + 1],
                            program[switchState][a * 3 + 2],
                            program[switchState][a * 3 + 3]);
                }
                switchState++;
                break;
            case LEFTROOM:
                currentRoom = currentRoom.leftRoom;
                switchState++;
                break;
            case RIGHTROOM:
                currentRoom = currentRoom.rightRoom;
                switchState++;
                break;
            case UPROOM:
                currentRoom = currentRoom.upRoom;
                switchState++;
                break;
            case DOWNROOM:
                currentRoom = currentRoom.downRoom;
                switchState++;
                break;
            case RESETROOM:
                currentRoom = room;
                switchState++;
                break;
        }

    }

}
