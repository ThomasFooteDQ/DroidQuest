package com.droidquest.materials.switches;

import com.droidquest.items.Item;
import com.droidquest.items.Train;
import com.droidquest.materials.Material;

public class Switch1 extends Switch {
    private transient Train train;

    public Switch1() {
        super(Switch.ROT_UP);
    }

    public void TouchedByItem(Item item) {
        if (train == null) {
            for (int a = 0; a < Material.level.items.size(); a++) {
                Item t = Material.level.items.elementAt(a);
                if (t.getClass().toString().endsWith("Train")) {
                    train = (Train) t;
                }
            }
        }
        if (train == null) {
            return;
        }
        if (train.room == null) {
            train.x = 108;
            train.y = 250;
            train.room = item.room;
        }
    }

}