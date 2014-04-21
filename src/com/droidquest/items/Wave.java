package com.droidquest.items;

import com.droidquest.Room;

public class Wave extends HiddenCamera {
    private int animationState;
    private int animationDir = 1;

    public Wave(Room r) {
        super(r);
    }

    public void Animate() {
        animationState += animationDir;
        if (animationState == 0 || animationState == 56) {
            animationDir = -animationDir;
        }
        if (animationState % 8 == 0) {
            int Y = (animationState / 8) + 1;
            room.SetMaterialFill(3, 1, 14, 10, 8);
            room.SetMaterialFill(3, Y, 14, Y + 2, 0);
        }
    }

}