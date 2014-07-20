package com.droidquest.materials.switches;

import com.droidquest.Room;
import com.droidquest.items.Item;

public class Switch4A extends Switch {
    int count = 0;
    private int doorState = 0;
    transient Room room = null;

    public Switch4A() {
        super(Switch.ROT_UP);
        GenerateIcons();
    }

    public void TouchedByItem(Item item) {
        if (!value) {
            value = true;
            count++;
            room = item.room;
        }
    }

    public void Animate() {
        super.Animate();

        if (doorState == 0 && count == 4) {
            doorState = 1;
        }

        switch (doorState) {
            case 1:
                room.SetMaterial(8, 3, 0);
                room.SetMaterial(9, 3, 0);
                room.SetMaterial(10, 3, 0);
                room.SetMaterial(11, 3, 0);
                room.SetMaterial(8, 8, 0);
                room.SetMaterial(9, 8, 0);
                room.SetMaterial(10, 8, 0);
                room.SetMaterial(11, 8, 0);
                room.SetMaterial(12, 4, 0);
                room.SetMaterial(12, 5, 0);
                room.SetMaterial(12, 6, 0);
                room.SetMaterial(12, 7, 0);
                room.SetMaterial(13, 4, 4);
                room.SetMaterial(13, 7, 4);
                room.SetMaterial(7, 4, 0);
                room.SetMaterial(7, 5, 0);
                room.SetMaterial(7, 6, 0);
                room.SetMaterial(7, 7, 0);
                room.SetMaterial(6, 4, 4);
                room.SetMaterial(6, 7, 4);
                doorState++;
                break;
            case 2:
                room.SetMaterial(8, 2, 0);
                room.SetMaterial(9, 2, 0);
                room.SetMaterial(10, 2, 0);
                room.SetMaterial(11, 2, 0);
                room.SetMaterial(12, 2, 0);
                room.SetMaterial(7, 9, 0);
                room.SetMaterial(8, 9, 0);
                room.SetMaterial(9, 9, 0);
                room.SetMaterial(10, 9, 0);
                room.SetMaterial(11, 9, 0);
                room.SetMaterial(13, 4, 0);
                room.SetMaterial(13, 5, 0);
                room.SetMaterial(13, 6, 0);
                room.SetMaterial(13, 7, 0);
                room.SetMaterial(14, 4, 4);
                room.SetMaterial(14, 7, 4);
                room.SetMaterial(6, 4, 0);
                room.SetMaterial(6, 5, 0);
                room.SetMaterial(6, 6, 0);
                room.SetMaterial(6, 7, 0);
                room.SetMaterial(5, 4, 4);
                room.SetMaterial(5, 7, 4);
                doorState++;
                break;
            case 3:
                room.SetMaterial(8, 1, 0);
                room.SetMaterial(9, 1, 0);
                room.SetMaterial(10, 1, 0);
                room.SetMaterial(11, 1, 0);
                room.SetMaterial(8, 10, 0);
                room.SetMaterial(9, 10, 0);
                room.SetMaterial(10, 10, 0);
                room.SetMaterial(11, 10, 0);
                room.SetMaterial(14, 4, 0);
                room.SetMaterial(14, 5, 0);
                room.SetMaterial(14, 6, 0);
                room.SetMaterial(14, 7, 0);
                room.SetMaterial(15, 4, 4);
                room.SetMaterial(15, 7, 4);
                room.SetMaterial(5, 4, 0);
                room.SetMaterial(5, 5, 0);
                room.SetMaterial(5, 6, 0);
                room.SetMaterial(5, 7, 0);
                room.SetMaterial(4, 4, 4);
                room.SetMaterial(4, 7, 4);
                doorState++;
                break;
            case 4:
                Room temproom = room.rightRoom; // KeyTunnel Left
                temproom.SetMaterial(2, 3, 0);
                for (int a = 0; a < 8; a++) {
                    temproom.SetMaterial(8, a + 1, 0);
                    temproom.SetMaterial(12, a + 1, 0);
                    temproom.SetMaterial(16, a + 1, 0);
                }
                temproom = temproom.rightRoom; // KeyTunnel Right
                for (int a = 0; a < 8; a++) {
                    temproom.SetMaterial(3, a + 1, 0);
                    temproom.SetMaterial(7, a + 1, 0);
                    temproom.SetMaterial(11, a + 1, 0);
                }
                temproom = room.leftRoom; // MineField top right
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.SetMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = temproom.leftRoom; // MineField top left
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.SetMaterial(X, Y, 11);
                        }
                        if (temproom.RoomArray[Y][X] == 16) {
                            temproom.SetMaterial(X, Y, 0);
                        }
                    }
                }
                temproom = temproom.downRoom; // MineField bottom left
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.SetMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = temproom.rightRoom;  // MineField bottom right
                for (int Y = 0; Y < 12; Y++) {
                    for (int X = 0; X < 20; X++) {
                        if (temproom.RoomArray[Y][X] == 8) {
                            temproom.SetMaterial(X, Y, 11);
                        }
                    }
                }
                temproom = room.upRoom;
                temproom.SetMaterial(19, 5, 0);
                temproom.SetMaterial(19, 6, 0);
                temproom.SetMaterial(19, 7, 0);
                temproom = temproom.rightRoom;
                temproom = temproom.upRoom;
                temproom = temproom.leftRoom;
                temproom = temproom.leftRoom;
                temproom.SetMaterial(19, 5, 0);
                temproom.SetMaterial(19, 6, 0);
                doorState++;
                break;

        }
    }

}
