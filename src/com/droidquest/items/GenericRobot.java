package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Spark;
import com.droidquest.devices.Device;
import com.droidquest.levels.Level;
import com.droidquest.materials.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GenericRobot extends Item {
    public Device devices[] = new Device[10];
    public boolean topBumper;
    public boolean bottomBumper;
    public boolean leftBumper;
    public boolean rightBumper;
    public boolean thrusterPower;
    public boolean topThruster;
    public boolean bottomThruster;
    public boolean leftThruster;
    public boolean rightThruster;
    public boolean antenna;
    public boolean broadcasting;
    public int grabber; // use Port.ROT_ for rotations.
    private Color color;
    public boolean periscope;
    private int periscopeAnimation;
    private transient boolean oldTopBumper;
    private transient boolean oldBottomBumper;
    private transient boolean oldLeftBumper;
    private transient boolean oldRightBumper;
    private transient ImageIcon[] images;

    GenericRobot(int X, int Y, Room r, Color c) {
        x = X;
        y = Y;
        room = r;
        grabber = 1;
        charge = 100000;
        color = c;
//	orgX = 32; orgY = 24;
        orgX = 14;
        orgY = 24;
//	orgX = 14; orgY = 0;
        width = 56;
        height = 42;


        InternalRoom = new Room();
        InternalRoom.RoomArray = new int[][]{
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0}
        };

        Material mat1 = Material.FindSimiliar(new Material(color, false, true));
        int mat1Index = level.materials.indexOf(mat1);
        Material perUp = Material.FindSimiliar(new PeriscopeUp());
        int perUpIndex = level.materials.indexOf(perUp);
        Material perDown = Material.FindSimiliar(new PeriscopeDown());
        int perDownIndex = level.materials.indexOf(perDown);
        Material battIn = Material.FindSimiliar(new BatteryIn());
        int battInIndex = level.materials.indexOf(battIn);
        Material battOut = Material.FindSimiliar(new BatteryOut());
        int battOutIndex = level.materials.indexOf(battOut);
        ((BatteryIn) battIn).robot = this;
        ((BatteryOut) battOut).robot = this;

        for (int rY = 0; rY < 12; rY++) {
            for (int rX = 0; rX < 20; rX++) {
                if (InternalRoom.RoomArray[rY][rX] == 1) {
                    InternalRoom.RoomArray[rY][rX] = mat1Index;
                }
            }
        }
        InternalRoom.RoomArray[2][17] = perUpIndex;
        InternalRoom.RoomArray[2][16] = perDownIndex;
        InternalRoom.RoomArray[3][17] = perDownIndex;
        InternalRoom.RoomArray[9][2] = battOutIndex;
        InternalRoom.RoomArray[9][3] = battInIndex;
        InternalRoom.portalItem = this;
        level.rooms.addElement(InternalRoom);
        InternalRoom.upRoom = null;
        InternalRoom.downRoom = null;
        InternalRoom.leftRoom = null;
        InternalRoom.rightRoom = null;
//	leftPortal = new Rectangle (-14,0,8,10);
//	rightPortal = new Rectangle (36,0,8,10);
//	upPortal = new Rectangle (12,-18,12,8);
//	downPortal = new Rectangle (10,24,12,8);
//	
        leftPortal = new Rectangle(-28, 0, 8, 14);
        rightPortal = new Rectangle(50, 0, 8, 14);
        upPortal = new Rectangle(10, -30, 12, 18);
        downPortal = new Rectangle(10, 24, 12, 18);

        level.items.addElement(new PowerSwitch(17 * 28 - 4, 9 * 32 - 4, InternalRoom));

        GenerateIcons();
        Animate();
    }

    public void GenerateIcons() {
//	orgX = 14; orgY = 24;
//	width = 56; height = 42;
        orgX = 14;
        orgY = 24;
        width = 56;
        height = 44;
        icons = new ImageIcon[1];
//	icons[0]= new ImageIcon(new BufferedImage(122,92,BufferedImage.TYPE_4BYTE_ABGR));
        icons[0] = new ImageIcon(new BufferedImage(84, 84, BufferedImage.TYPE_4BYTE_ABGR));
        currentIcon = icons[0].getImage();
        ((BatteryIn) level.materials.elementAt(InternalRoom.RoomArray[9][3])).robot = this;
        ((BatteryOut) level.materials.elementAt(InternalRoom.RoomArray[9][2])).robot = this;
        images = new ImageIcon[10];
        for (int a = 0; a < 10; a++) {
//	     images[a] = new ImageIcon( new BufferedImage(122,92,BufferedImage.TYPE_4BYTE_ABGR));
            images[a] = new ImageIcon(new BufferedImage(84, 84, BufferedImage.TYPE_4BYTE_ABGR));
            Graphics g;
            try {
                g = images[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color transparent = new Color(0, 0, 0, 0);
            g2.setBackground(transparent);
//	     g2.clearRect(0,0,122,92);
            g2.clearRect(0, 0, 84, 84);

            switch (a) {
                case 0: // Robot Body
                {
                    g.setColor(color);
                    int X = orgX + 18;
                    int Y = orgY + 6;
                    g.fillRect(X, Y, 22, 32);
                    g.fillRect(X - 4, Y + 2, 30, 28);
                    g.fillRect(X - 8, Y + 4, 38, 24);
                    g.fillRect(X - 12, Y + 6, 46, 20);
                }
                break;

                case 1: // Grabber Up
                    g.setColor(Color.white);
                {
                    int X = orgX + 40;
                    int Y = orgY - 16;
                    g.fillRect(X + 0, Y + 20, 20, 4);
                    g.fillRect(X + 16, Y + 16, 4, 8);
                    g.fillRect(X + 12, Y + 10, 4, 8);
                    g.fillRect(X + 20, Y + 10, 4, 8);
                    g.fillRect(X + 12, Y + 12, 12, 2);
                    g.fillRect(X + 8, Y + 2, 4, 10);
                    g.fillRect(X + 24, Y + 2, 4, 10);
                    g.fillRect(X + 12, Y + 0, 4, 4);
                    g.fillRect(X + 20, Y + 0, 4, 4);
                    break;
                }
                case 2: // Grabber Right
                    g.setColor(Color.white);
                {
                    int X = orgX + 46;
                    int Y = orgY + 34;
                    g.fillRect(X + 0, Y + 0, 4, 10);
                    g.fillRect(X + 0, Y + 4, 12, 2);
                    g.fillRect(X + 0, Y + 8, 12, 2);
                    g.fillRect(X + 8, Y + 2, 4, 10);
                    g.fillRect(X + 12, Y + 0, 4, 4);
                    g.fillRect(X + 12, Y + 10, 4, 4);
                    g.fillRect(X + 12, Y + 0, 12, 2);
                    g.fillRect(X + 12, Y + 12, 12, 2);
                    g.fillRect(X + 16, Y + 0, 4, 4);
                    g.fillRect(X + 16, Y + 10, 4, 4);
                    g.fillRect(X + 20, Y + 2, 4, 4);
                    g.fillRect(X + 20, Y + 8, 4, 4);
                }
                break;
                case 3: // Grabber Down
                    g.setColor(Color.white);
                {
                    int X = orgX - 10;
                    int Y = orgY + 32;
                    g.fillRect(X + 8, Y + 0, 12, 4);
                    g.fillRect(X + 8, Y + 0, 4, 10);
                    g.fillRect(X + 4, Y + 8, 4, 8);
                    g.fillRect(X + 12, Y + 8, 4, 8);
                    g.fillRect(X + 4, Y + 12, 12, 2);
                    g.fillRect(X + 0, Y + 14, 4, 10);
                    g.fillRect(X + 16, Y + 14, 4, 10);
                    g.fillRect(X + 4, Y + 22, 4, 4);
                    g.fillRect(X + 12, Y + 22, 4, 4);
                }
                break;
                case 4: // Grabber Left
                    g.setColor(Color.white);
                {
                    int X = orgX - 16;
                    int Y = orgY - 6;
                    g.fillRect(X + 24, Y + 4, 4, 10);
                    g.fillRect(X + 16, Y + 4, 12, 2);
                    g.fillRect(X + 16, Y + 8, 12, 2);
                    g.fillRect(X + 16, Y + 2, 4, 10);
                    g.fillRect(X + 12, Y + 0, 4, 4);
                    g.fillRect(X + 12, Y + 10, 4, 4);
                    g.fillRect(X + 4, Y + 0, 12, 2);
                    g.fillRect(X + 4, Y + 12, 12, 2);
                    g.fillRect(X + 4, Y + 0, 4, 4);
                    g.fillRect(X + 4, Y + 10, 4, 4);
                    g.fillRect(X + 0, Y + 2, 4, 4);
                    g.fillRect(X + 0, Y + 8, 4, 4);
                    break;
                }

                case 5: // Antenna Off
                {
                    int X = orgX + 14;
                    int Y = orgY - 18;
                    g.setColor(Color.white);
                    g.fillRect(X, Y, 12, 6);
                    g.fillRect(X + 4, Y + 6, 4, 16);
                }
                break;
                case 6: // Antenna On
                {
                    int X = orgX + 14;
                    int Y = orgY - 18;
                    g.setColor(new Color(255, 128, 0));
                    g.fillRect(X, Y, 12, 6);
                    g.fillRect(X + 4, Y + 6, 4, 16);
                }
                break;

                case 7: // Periscope Left
                {
                    int X = orgX + 40;
                    int Y = orgY + 5;
                    g.fillRect(X - 10, Y - 24, 6, 14);
                    g.fillRect(X - 6, Y - 22, 12, 10);
                    g.fillRect(X + 6, Y - 20, 4, 6);
                    g.fillRect(X + 2, Y - 12, 4, 18);
                }
                break;
                case 8: // Periscope Straight
                {
                    int X = orgX + 40;
                    int Y = orgY + 5;
                    g.fillRect(X - 2, Y - 24, 12, 2);
                    g.fillRect(X - 2, Y - 14, 12, 2);
                    g.fillRect(X - 6, Y - 22, 4, 2);
                    g.fillRect(X - 6, Y - 16, 4, 2);
                    g.fillRect(X + 10, Y - 22, 4, 2);
                    g.fillRect(X + 10, Y - 16, 4, 2);
                    g.fillRect(X - 10, Y - 20, 4, 4);
                    g.fillRect(X + 2, Y - 20, 4, 4);
                    g.fillRect(X + 14, Y - 20, 4, 4);
                    g.fillRect(X + 2, Y - 12, 4, 18);
                }
                break;
                case 9: // Periscope Right
                {
                    int X = orgX + 40;
                    int Y = orgY + 5;
                    g.fillRect(X + 14, Y - 24, 6, 14);
                    g.fillRect(X + 2, Y - 22, 12, 10);
                    g.fillRect(X - 2, Y - 20, 4, 6);
                    g.fillRect(X + 2, Y - 12, 4, 18);
                }
                break;
            }
        }
    }

    public void Animate() {
        // Do Thrusting
        if (charge > 0 && level.electricity && (carriedBy == null) && thrusterPower) {
            if (topThruster) {
                moveDown(8);
            }
            if (rightThruster) {
                moveLeft(8);
            }
            if (bottomThruster) {
                moveUp(8);
            }
            if (leftThruster) {
                moveRight(8);
            }
        }

        if (charge > 0 && level.electricity && thrusterPower) {
            Dimension d = GetXY();
            int X = d.width;
            int Y = d.height;
            if (topThruster) {
                level.sparks.addElement(new Spark(X - orgX + 32 + level.random.nextInt(24), Y - orgY + 24, 0, -4, room));
                level.sparks.addElement(new Spark(X - orgX + 32 + level.random.nextInt(24), Y - orgY + 24, 0, -4, room));
                charge -= 2;
            }
            if (rightThruster) {
                level.sparks.addElement(new Spark(X - orgX + 74, Y - orgY + 36 + level.random.nextInt(20), 4, 0, room));
                level.sparks.addElement(new Spark(X - orgX + 74, Y - orgY + 36 + level.random.nextInt(20), 4, 0, room));
                charge -= 2;
            }
            if (bottomThruster) {
                level.sparks.addElement(new Spark(X - orgX + 32 + level.random.nextInt(24), Y - orgY + 64, 0, 4, room));
                level.sparks.addElement(new Spark(X - orgX + 32 + level.random.nextInt(24), Y - orgY + 64, 0, 4, room));
                charge -= 2;
            }
            if (leftThruster) {
                level.sparks.addElement(new Spark(X - orgX + 14, Y - orgY + 36 + level.random.nextInt(20), -4, 0, room));
                level.sparks.addElement(new Spark(X - orgX + 14, Y - orgY + 36 + level.random.nextInt(20), -4, 0, room));
                charge -= 2;
            }
            charge--;
            if (charge < 0) {
                charge = 0;
            }
        }


        // Draw Antenna sparks around Broadcasting Antenna
        if (broadcasting && level.electricity) {
            Dimension d = GetXY();
            level.sparks.addElement(new Spark(d.width - orgX + 34, d.height - orgY + 10,
                    level.random.nextInt(9) - 4,
                    level.random.nextInt(9) - 4,
                    room));
        }

        // Make sounds
        if (topBumper && !oldTopBumper) {
            level.PlaySound(room, Level.BUMPSOUND);
        }
        if (bottomBumper && !oldBottomBumper) {
            level.PlaySound(room, Level.BUMPSOUND);
        }
        if (rightBumper && !oldRightBumper) {
            level.PlaySound(room, Level.BUMPSOUND);
        }
        if (leftBumper && !oldLeftBumper) {
            level.PlaySound(room, Level.BUMPSOUND);
        }
        if (broadcasting && level.electricity) {
            level.PlaySound(room, Level.BEEPSOUND);
        }
        oldTopBumper = topBumper;
        oldBottomBumper = bottomBumper;
        oldRightBumper = rightBumper;
        oldLeftBumper = leftBumper;

    }

    public void Decorate() {
        // Paint background
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to " + getClass() + "Image");
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        Color transparent = new Color(0, 0, 0, 0);
        g2.setBackground(transparent);
        g2.clearRect(0, 0, 123, 92);

        // Paint Robot Body
        g.drawImage(images[0].getImage(), 0, 0, level);

        // Draw Bumpers
        if (topBumper) {
            g.setColor(new Color(255, 128, 0));
        }
        else {
            g.setColor(Color.white);
        }
        g.fillRect(32, 24, 22, 2);

        if (bottomBumper) {
            g.setColor(new Color(255, 128, 0));
        }
        else {
            g.setColor(Color.white);
        }
        g.fillRect(32, 66, 22, 2);

        if (leftBumper) {
            g.setColor(new Color(255, 128, 0));
        }
        else {
            g.setColor(Color.white);
        }
        g.fillRect(12, 36, 4, 20);

        if (rightBumper) {
            g.setColor(new Color(255, 128, 0));
        }
        else {
            g.setColor(Color.white);
        }
        g.fillRect(70, 36, 4, 20);

        // Draw Antenna
        if (antenna) {
            g.drawImage(images[6].getImage(), 0, 0, level);
        }
        else {
            g.drawImage(images[5].getImage(), 0, 0, level);
        }

        // Draw Grabber
        g.setColor(Color.white);
        switch (grabber) {
            case Port.ROT_UP:
                g.drawImage(images[1].getImage(), 0, 0, level);
                break;
            case Port.ROT_RIGHT:
                g.drawImage(images[2].getImage(), 0, 0, level);
                break;
            case Port.ROT_DOWN:
                g.drawImage(images[3].getImage(), 0, 0, level);
                break;
            case Port.ROT_LEFT:
                g.drawImage(images[4].getImage(), 0, 0, level);
                break;
        }

        // Draw Periscope
//	if (periscope)
        if (level.currentViewer == this) {
            periscopeAnimation++;
            if (periscopeAnimation == 32) {
                periscopeAnimation = 0;
            }
            if (periscopeAnimation < 8) {
                g.drawImage(images[7].getImage(), 0, 0, level);
            }
            if ((periscopeAnimation >= 8 && periscopeAnimation < 16)
                    || periscopeAnimation >= 24) {
                g.drawImage(images[8].getImage(), 0, 0, level);
            }
            if (periscopeAnimation >= 16 && periscopeAnimation < 24) {
                g.drawImage(images[9].getImage(), 0, 0, level);
            }
        }



    }

    public boolean CanBePickedUp(Item item) {
        return !item.getClass().toString().endsWith("Robot") && super.CanBePickedUp(item);
    }

    public void Erase() {
        super.Erase();
        devices = null;
    }

}

