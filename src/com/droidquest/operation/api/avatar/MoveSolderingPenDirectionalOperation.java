package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.operation.Operation;

/**
 * Marches soldering pens in a specific direction
 */
public class MoveSolderingPenDirectionalOperation extends MoveDirectionalOperation implements Operation {
    public MoveSolderingPenDirectionalOperation(SolderingPen solderingPen, int x, int y) {
        super(solderingPen, x, y);
    }

    protected int getDeltaX() {
        return x - 2 - avatar.getX();
    }

    private int getDeltaY() {
        return y - 20 - avatar.getY();
    }
}
