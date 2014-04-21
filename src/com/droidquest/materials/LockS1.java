package com.droidquest.materials;

import com.droidquest.Room;
import com.droidquest.items.Item;
import com.droidquest.items.Magnet;

import java.awt.*;
import java.util.Date;

public class LockS1 extends Material {
    private Date timeout;
    private transient Item magnet = null;
    private transient Room room = null;

    public LockS1() {
        super(new Color(0, 0, 128), false, true);
    }

    public void TouchedByItem(Item item) {
        if (item instanceof Magnet) {
            magnet = item;
            room = item.room;
            timeout = new Date(new Date().getTime() + 10000);
        }
    }

    public void Animate() {
        if (magnet != null) {
            Date now = new Date();
            if (now.getTime() > timeout.getTime()) {
                if (magnet.room == room) {
                    level.LinkRoomsLeftRight(11, 30);
                    level.LinkRoomsLeftRight(30, 11);
                    room.SetMaterial(10, 3, 0);
                    room.SetMaterial(11, 3, 0);
                }
            }
        }
    }

}
