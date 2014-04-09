package com.droidquest.items;

import java.awt.Color;

import com.droidquest.decorations.TextBox;

public class EnergyButton extends Button {
    transient NotAButton nb = null;
    private int animationState = 0;

    public EnergyButton() {
        super(0, 0, null, new Color(255, 128, 0));
        grabbable = false;
    }

    public void Animate() {
        if (animationState == 0) {
            if (room != null) {
                for (int a = 0; a < level.items.size(); a++) {
                    Item item = level.items.elementAt(a);
                    if (Overlaps(item)) {
                        animationState = 1;
                        nb.animationState = 51;
                        for (int b = 1; b < 19; b++) {
                            room.downRoom.SetMaterial(b, 4, 0);
                        }
                        TextBox line = room.downRoom.textBoxes.elementAt(1);
                        line.textString = " ";
                    }

                }
            }
        }
    }

}

