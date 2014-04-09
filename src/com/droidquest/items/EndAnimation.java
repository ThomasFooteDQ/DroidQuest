package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.levels.Level;

public class EndAnimation extends HiddenCamera {

    private int animationState = 0;
    private transient boolean playsong = false;

    public EndAnimation(Room r) {
        super(r);
    }

    public void Animate() {
        if (!playsong) {
            level.PlaySound(room, Level.ENDMUSICSOUND);
            playsong = true;
        }

        animationState = 1 - animationState;
        for (int a = 0; a < 20; a++) {
            if (a % 2 == animationState) {
                room.SetMaterial(a, 0, 0);
                room.SetMaterial(a, 11, 1);
            }
            else {
                room.SetMaterial(a, 0, 1);
                room.SetMaterial(a, 11, 0);
            }
        }

        for (int a = 0; a < 12; a++) {
            if (a % 2 == animationState) {
                room.SetMaterial(0, a, 0);
                room.SetMaterial(19, a, 1);
            }
            else {
                room.SetMaterial(0, a, 1);
                room.SetMaterial(19, a, 0);
            }
        }
    }

}
