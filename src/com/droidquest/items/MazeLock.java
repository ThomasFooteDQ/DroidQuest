package com.droidquest.items;

import com.droidquest.materials.Switch;


public class MazeLock extends Switch {
    private transient static Item paintbrush;

    public MazeLock() {
        super(Switch.ROT_DOWN);
    }

    public void TouchedByItem(Item item) {
        if (paintbrush == null) {
            paintbrush = level.paintbrush;
        }

        if (!value) {
            level.paintbrush = null;
            value = true;
        }
        else {
            level.paintbrush = paintbrush;
            value = false;
        }
    }

}
