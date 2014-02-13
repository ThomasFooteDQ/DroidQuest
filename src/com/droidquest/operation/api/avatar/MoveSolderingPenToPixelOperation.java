package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.operation.Operation;

/**
 * Operation which moves a SolderingPen to a given screen coordinate.
 */
public class MoveSolderingPenToPixelOperation extends MoveToPixelOperation implements Operation {
    public MoveSolderingPenToPixelOperation(SolderingPen solderingPen, int x, int y) {
        super(solderingPen, x, y);
    }

    protected int getDestinationX() {
        return x - 2;
    }

    protected int getDestinationY() {
        return y - 20;
    }
}
