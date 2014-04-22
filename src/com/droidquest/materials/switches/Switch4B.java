package com.droidquest.materials.switches;

import com.droidquest.items.Item;
import com.droidquest.materials.Material;

public class Switch4B extends Switch {
    private transient Switch4A sw = null;

    public Switch4B() {
        super(Switch.ROT_RIGHT);
    }

    public void TouchedByItem(Item item) {
        if (sw == null) {
            for (int a = 0; a < Material.level.materials.size(); a++) {
                Material mat = Material.level.materials.elementAt(a);
                if (mat instanceof Switch4A) {
                    sw = (Switch4A) mat;
                }
            }
        }

        if (!value) {
            value = true;
            sw.count++;
            sw.room = item.room;
        }
    }
}
