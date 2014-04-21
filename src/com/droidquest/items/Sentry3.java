package com.droidquest.items;

import com.droidquest.Room;

public class Sentry3 extends Sentry {
    // This sentry guards the Sewer Grate room. It walks up and down from
    // position (2*28,2*32) to (2*28,8*32). It does not notice the player
    // unless the player passes below y=11*32. Then it grabs the player if
    // it goes above y=8*32. The Sentry drags the player down to y=11*32. If
    // the player ever leaves the room, the Sentry forgets about the player.

    // Behavior values:
    // 0=Move Down
    // 1=Move Up
    // 2=Attack
    // 3=Drag
    private int carryToX;
    private boolean smart = false; // knows about the player

    public Sentry3(int X, int Y, Room r) {
        super(X, Y, r);
    }

    public void Animate() {
        if (behavior < 3) {
            animation++;
        }
        if (animation == 4) {
            animation = 0;
        }
        if (animation == 3) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[animation].getImage();
        }

        if (level.player.room != room) {
            if (smart) {
                behavior = previousBehavior;
            }
            smart = false;
        }
        else if (level.player.y > 320) {
            carryToX = level.player.x;
            smart = true;
        }

        if (behavior < 2 && smart) {
            if (level.player.y <= 256) {
                previousBehavior = behavior;
                behavior = 2;
            }
        }

        switch (behavior) {
            case 0:
                if (y < 256) {
                    moveDown(8);
                }
                else {
                    behavior = 1;
                }
                if (x < 56) {
                    moveRight(8);
                }
                if (x > 56) {
                    moveLeft(8);
                }
                break;
            case 1:
                if (y > 64) {
                    moveUp(8);
                }
                else {
                    behavior = 0;
                }
                if (x < 56) {
                    moveRight(8);
                }
                if (x > 56) {
                    moveLeft(8);
                }
                break;
            case 2:
                if (level.player.room != room) {
                    behavior = previousBehavior;
                    break;
                }
                int dx = level.player.x - x;
                int dy = level.player.y - y;
                if (dx < -50) {
                    dx = -50;
                }
                if (dx > 50) {
                    dx = 50;
                }
                if (dy < -50) {
                    dy = -50;
                }
                if (dy > 50) {
                    dy = 50;
                }
                if (dx < 0) {
                    moveLeft(-dx);
                }
                if (dx > 0) {
                    moveRight(dx);
                }
                if (dy < 0) {
                    moveUp(-dy);
                }
                if (dy > 0) {
                    moveDown(dy);
                }
                if (dx == 0 && dy == 0) {
                    PicksUp(level.player);
                    behavior = 3;
                }
                break;
            case 3:
                if (y >= 320) {
                    Drops();
                    behavior = previousBehavior;
                    break;
                }
                else {
                    if (y < 312) {
                        moveDown(8);
                    }
                    else if (y < 320) {
                        moveDown(320 - y);
                    }
                    if (x < carryToX) {
                        moveRight(8);
                    }
                    if (x > carryToX) {
                        moveLeft(8);
                    }
                }
                break;
        }
    }

}

