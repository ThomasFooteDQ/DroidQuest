package com.droidquest.devices;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.chipstuff.Port;
import com.droidquest.items.Item;

public class ContactSensor extends Device {
    private String targetClass;
    private Item target;
    private Dimension d1 = new Dimension(); // Output pointing Right, Left
    private Dimension d2 = new Dimension(); // Output pointing Up, Down

    public ContactSensor(int X, int Y, Room r, Item item) {
        x = X;
        y = Y;
        room = r;
        target = item;
        editable = true;
        targetClass = target.getClass().toString().substring(6); // Removes "class "
        rotation = 1; // Right
        d1.width = 28 + target.getWidth();
        d1.height = Math.max(target.getHeight(), 12);
        d2.width = Math.max(target.getWidth(), 12);
        d2.height = 28 + target.getHeight();
        width = d1.width;
        height = d1.height;
        GenerateIcons();
    }

    public void GenerateIcons() {
        if (ports == null) {
            ports = new Port[1];
            ports[0] = new Port(width - 2, height / 2 - 2, Port.TYPE_OUTPUT, 24, Port.ROT_UP, this);
            if (rotation > 0) {
                int rot = rotation;
                if (rotation % 2 == 1) {
                    int temp = width;
                    width = height;
                    height = temp;
                }
                rotation = 0;
                for (int r = 0; r < rot; r++) {
                    rotate(1);
                }
            }
        }
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(d2.width, d2.height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(d1.width, d1.height, BufferedImage.TYPE_4BYTE_ABGR));
        target.GenerateIcons();
        currentIcon = icons[rotation % 2].getImage();
    }

    public boolean Function() {
        ports[0].value = false;
        if (room.portalItem == null) {
            // Contact Sensor is not inside robot.
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item.room == room) {
                    if (target.getClass().isInstance(item)) {
                        if (item.carriedBy == null) {
                            if (Overlaps(item)) {
                                ports[0].value = true;
                                a = level.items.size();
                            }
                        }
                    }
                }
            }
        }
        else {
            // Contact Sensor is inside Robot.
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item.room == room.portalItem.room) {
                    if (target.getClass().isInstance(item)) {
                        if (item.carriedBy == null) {
                            if (room.portalItem.Overlaps(item)) {
                                ports[0].value = true;
                                a = level.items.size();
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void Decorate() {
        super.Decorate();
        switch (rotation) {
            case Port.ROT_UP:
                g.drawImage(target.currentIcon, width / 2 - target.getWidth() / 2, 28, level);
                break;
            case Port.ROT_RIGHT:
                g.drawImage(target.currentIcon, 0, height / 2 - target.getHeight() / 2, level);
                break;
            case Port.ROT_DOWN:
                g.drawImage(target.currentIcon, width / 2 - target.getWidth() / 2, 0, level);
                break;
            case Port.ROT_LEFT:
                g.drawImage(target.currentIcon, 28, height / 2 - target.getHeight() / 2, level);
                break;
        }
    }

    public void rotate(int dir) {
        if (rotation == 0 && dir == -1) {
            rotation = 3;
        }
        else if (rotation == 3 && dir == 1) {
            rotation = 0;
        }
        else {
            rotation += dir;
        }

        if (rotation % 2 == 0) // if rotation == Up or Down
        {
            width = d2.width;
            height = d2.height;
        }
        else {
            width = d1.width;
            height = d1.height;
        }
        switch (rotation) {
            case Port.ROT_UP:
                ports[0].x = width / 2 - 2;
                ports[0].y = 2;
                break;
            case Port.ROT_RIGHT:
                ports[0].x = width - 2;
                ports[0].y = height / 2 - 2;
                break;
            case Port.ROT_DOWN:
                ports[0].x = width / 2 + 1;
                ports[0].y = height - 2;
                break;
            case Port.ROT_LEFT:
                ports[0].x = 2;
                ports[0].y = height / 2 + 1;
                break;
        }
    }

    public void Erase() {
        super.Erase();
        target = null;
    }

}
