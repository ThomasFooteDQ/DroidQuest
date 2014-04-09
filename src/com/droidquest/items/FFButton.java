package com.droidquest.items;

import java.awt.Color;

import com.droidquest.Room;

public class FFButton extends Button {
    private transient GenericRobot[] robots = null;

    public FFButton(int X, int Y, Room r) {
        super(X, Y, r, Color.white);
        grabbable = false;
    }

    public void Animate() {
        if (robots == null) {
            robots = new GenericRobot[3];
            int rcount = 0;
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item instanceof GenericRobot) {
                    robots[rcount] = (GenericRobot) item;
                    rcount++;
                }
            }
        }
        else {
            for (int a = 0; a < 3; a++) {
                if (robots[a] != null) {
                    if (Overlaps(robots[a])) {
                        room.SetMaterial(0, 4, 0);
                        room.SetMaterial(0, 5, 0);
                        room.SetMaterial(0, 6, 0);
                        room.SetMaterial(19, 4, 0);
                        room.SetMaterial(19, 5, 0);
                        room.SetMaterial(19, 6, 0);
                        room = room.leftRoom;
                        room.SetMaterial(19, 4, 0);
                        room.SetMaterial(19, 5, 0);
                        room.SetMaterial(19, 6, 0);
                        room = null;
                        level.items.remove(this);
                    }
                }
            }
        }
    }

}
