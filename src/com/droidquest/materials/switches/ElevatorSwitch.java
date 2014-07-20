package com.droidquest.materials.switches;

import com.droidquest.Room;
import com.droidquest.decorations.Arrow;
import com.droidquest.items.Item;
import com.droidquest.materials.ElevatorOutPortal;
import com.droidquest.materials.Material;

public class ElevatorSwitch extends Switch {
    private int animationState = 0;
    // 0=open
// 1=closing
// 2=closing
// 3=switch arrow, switch outRoom
// 4=opening
// 5=opening
    private transient static Room room;

    public ElevatorSwitch() {
        super(Switch.ROT_LEFT);
    }

    public void TouchedByItem(Item item) {
        room = item.room;
        if (animationState == 0) {
            animationState = 1;
        }
    }

    public void Animate() {
        super.Animate();
        switch (animationState) {
            case 0:
                value = false;
                break;
            case 1:
                // Play sound
                value = true;
                room.SetMaterial(0, 7, 4);
                room.SetMaterial(0, 10, 4);
                animationState++;
                break;
            case 2:
                room.SetMaterial(0, 8, 4);
                room.SetMaterial(0, 9, 4);
                animationState++;
                break;
            case 3:
                if (ElevatorOutPortal.outRoom == Material.level.rooms.elementAt(11)) {
                    for (int a = 0; a < room.arrows.size(); a++) {
                        Arrow arrow = room.arrows.elementAt(a);
                        arrow.direction = Arrow.DIR_UP;
                        arrow.y = 66;
                    }
                    ElevatorOutPortal.SetOutRoom(9);
                }
                else {
                    for (int a = 0; a < room.arrows.size(); a++) {
                        Arrow arrow = room.arrows.elementAt(a);
                        arrow.direction = Arrow.DIR_DOWN;
                        arrow.y = 94;
                    }
                    ElevatorOutPortal.SetOutRoom(11);
                }
                animationState++;
                break;
            case 4:
                room.SetMaterial(0, 8, 12);
                room.SetMaterial(0, 9, 12);
                animationState++;
                break;
            case 5:
                room.SetMaterial(0, 7, 12);
                room.SetMaterial(0, 10, 12);
                animationState = 0;
                break;
        }
    }

}

