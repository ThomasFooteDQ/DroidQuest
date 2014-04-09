package com.droidquest.items;

import com.droidquest.Room;

public class SentryT2 extends Sentry {
    // This sentry guards a room from the toolbox being carried through it.
    // It walks up and down, and pounces and drags if the cursor is
    // carrying the toolbox.

    // Behavior values:
    // 0=Walk down
    // 1=Walk up
    // 2=Attack
    // 3=Drag back

    public SentryT2(int X, int Y, Room r) {
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

        if (level.player.room == room) {
            if (level.player.x >= 112) {
                if (level.player.carrying == level.toolbox) {
                    previousBehavior = behavior;
                    behavior = 2;
                }
            }
        }

        switch (behavior) {
            case 0:
                if (y < 256) {
                    MoveDown(4);
                }
                else {
                    behavior = 1;
                }
                if (x < 56) {
                    MoveRight(4);
                }
                if (x > 56) {
                    MoveLeft(4);
                }
                break;
            case 1:
                if (y > 64) {
                    MoveUp(4);
                }
                else {
                    behavior = 0;
                }
                if (x < 56) {
                    MoveRight(4);
                }
                if (x > 56) {
                    MoveLeft(4);
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
                    MoveLeft(-dx);
                }
                if (dx > 0) {
                    MoveRight(dx);
                }
                if (dy < 0) {
                    MoveUp(-dy);
                }
                if (dy > 0) {
                    MoveDown(dy);
                }
                if (dx == 0 && dy == 0) {
                    PicksUp(level.player);
                    behavior = 3;
                }
                break;
            case 3:
                if (x <= 56 && y >= 20 && y <= 30) {
                    Drops();
                    behavior = 1;
                    break;
                }
                else {
                    if (x > 56) {
                        MoveLeft(4);
                    }
                    if (y < 20) {
                        MoveDown(4);
                    }
                    if (y > 30) {
                        MoveUp(4);
                    }
                }
                break;
        }
    }

}