package com.droidquest.materials;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

public class SkyGuardMat extends Material {
    public SkyGuardMat() {
        super(true, false);
    }

    public void TouchedByItem(Item item) {
        if (item == level.player) {
            level.PlaySound(level.player.room, Level.DISCHARGESOUND);
            level.player.x = 2 * 28;
            level.player.y = 8 * 32;
            level.player.SetRoom(level.player.room.downRoom);
        }
    }

}