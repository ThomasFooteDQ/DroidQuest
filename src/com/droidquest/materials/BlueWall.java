package com.droidquest.materials;

import java.awt.Color;

import com.droidquest.items.Item;

public class BlueWall extends Material {
    public BlueWall() {
        super(new Color(0, 0, 255), false, true);
    }

    public boolean Passable(Item item) {
        return item.getClass().toString().endsWith("Sentry3") || passable;
    }
}
