package com.droidquest.items;

import javax.swing.ImageIcon;

import com.droidquest.Room;

public class TrashCollector extends Item {
    private int animationState = 0;
    private int behavior = 0;
    // 0 = Patrol Left to Room 31
// 1 = Patrol Up to Room 5
// 2 = Patrol Right to Room 11
// 3 = Patrol Down to Room 17
// 4 = Patrol Left to Room 19
// 5 = Go up to Room 58
// 6 = Go down to Room 19
// 7 = Prowl (approach until get item)
    private int previousBehavior;
    private int gotoX;
    private int gotoY;
    private Room gotoRoom;
    private Item target = null;

    public TrashCollector(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 28;
        height = 32;
        grabbable = false;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon("images/trashcollector0.gif");
        icons[1] = new ImageIcon("images/trashcollector1.gif");
        currentIcon = icons[0].getImage();
    }

    public void Animate() {
        animationState = 1 - animationState;
        currentIcon = icons[animationState].getImage();

        if (gotoRoom == null) // Sets destination room
        {
            gotoX = 266;
            gotoY = 176;
            switch (behavior) {
                case 0:
                    gotoRoom = level.rooms.elementAt(31);
                    break;
                case 1:
                    gotoRoom = level.rooms.elementAt(5);
                    break;
                case 2:
                    gotoRoom = level.rooms.elementAt(11);
                    break;
                case 3:
                    gotoRoom = level.rooms.elementAt(17);
                    break;
                case 4:
                    gotoRoom = level.rooms.elementAt(19);
                    break;
                case 5:
                    gotoRoom = level.rooms.elementAt(58);
                    break;
                case 6:
                    gotoRoom = level.rooms.elementAt(19);
                    break;
            }
        }

        if (behavior < 5) // Check for items in hallway
        {
            for (int a = 0; a < level.items.size(); a++) {
                Item item = level.items.elementAt(a);
                if (item != this && item != level.player) {
                    if (item.room == room && item.carriedBy == null) {
                        previousBehavior = behavior;
                        behavior = 7;
                        target = item;
                        a = level.items.size();
                    }
                }
            }
        }

        switch (behavior) {
            case 0: // Move Left to Room 31
                if (room != gotoRoom) {
                    moveLeft(4);
                    if (y != 176) {
                        int diff = Math.abs(176 - y);
                        int dir = diff / (176 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        gotoRoom = null;
                        behavior = 1;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 1: // Move Up to Room 5
                if (room != gotoRoom) {
                    moveUp(4);
                    if (x != 266) {
                        int diff = Math.abs(266 - y);
                        int dir = diff / (266 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        gotoRoom = null;
                        behavior = 2;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 2: // Move Right to Room 11
                if (room != gotoRoom) {
                    moveRight(4);
                    if (y != 176) {
                        int diff = Math.abs(176 - y);
                        int dir = diff / (176 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        gotoRoom = null;
                        behavior = 3;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 3: // Move Down to Room 17
                if (room != gotoRoom) {
                    moveDown(4);
                    if (x != 266) {
                        int diff = Math.abs(266 - y);
                        int dir = diff / (266 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        gotoRoom = null;
                        behavior = 4;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 4: // Move Left to Room 19
                if (room != gotoRoom) {
                    moveLeft(4);
                    if (y != 176) {
                        int diff = Math.abs(176 - y);
                        int dir = diff / (176 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        int holdings = 0;
                        Room purge = level.rooms.elementAt(60);
                        for (int a = 0; a < level.items.size(); a++) {
                            Item item = level.items.elementAt(a);
                            if (item.room == purge) {
                                holdings++;
                            }
                        }
                        if (holdings == 0) {
                            behavior = 0;
                        }
                        else {
                            behavior = 5;
                        }
                        gotoRoom = null;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 5: // Move Up to Room 58
                if (room != gotoRoom) {
                    moveUp(4);
                    if (x != 266) {
                        int diff = Math.abs(266 - y);
                        int dir = diff / (266 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        Room purge = level.rooms.elementAt(60);
                        for (int a = 0; a < level.items.size(); a++) {
                            Item item = level.items.elementAt(a);
                            if (item.room == purge) {
                                item.room = room;
                            }
                        }
                        gotoRoom = null;
                        behavior = 6;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 6: // Move Down to Room 19
                if (room != gotoRoom) {
                    moveDown(4);
                    if (x != 266) {
                        int diff = Math.abs(266 - y);
                        int dir = diff / (266 - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                }
                else {
                    if (x == gotoX && y == gotoY) {
                        gotoRoom = null;
                        behavior = 0;
                    }
                    if (x != gotoX) {
                        int diff = Math.abs(gotoX - x);
                        int dir = diff / (gotoX - x);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveRight(diff * dir);
                    }
                    if (y != gotoY) {
                        int diff = Math.abs(gotoY - y);
                        int dir = diff / (gotoY - y);
                        if (diff > 4) {
                            diff = 4;
                        }
                        moveDown(diff * dir);
                    }
                }
                break;
            case 7: // Approach item
                if (target.room == room && target.carriedBy == null) {
                    if (Overlaps(target)) {
                        target.room = level.rooms.elementAt(60);
                        behavior = previousBehavior;
                    }
                    else {
                        if (x != target.x) {
                            int diff = Math.abs(target.x - x);
                            int dir = diff / (target.x - x);
                            if (diff > 8) {
                                diff = 8;
                            }
                            moveRight(diff * dir);
                        }
                        if (y != target.y) {
                            int diff = Math.abs(target.y - y);
                            int dir = diff / (target.y - y);
                            if (diff > 8) {
                                diff = 8;
                            }
                            moveDown(diff * dir);
                        }
                    }
                }
                else {
                    behavior = previousBehavior;
                }
                break;
        }
    }

}

