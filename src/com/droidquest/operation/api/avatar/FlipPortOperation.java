package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.operation.Operation;

/**
 * Operation which flips the ports of that the soldering pen is operating on.  Yah - I'm not sure what it does either...
 */
public class FlipPortOperation implements Operation {
    private final SolderingPen solderingPen;

    public FlipPortOperation(SolderingPen solderingPen) {
        this.solderingPen = solderingPen;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        solderingPen.flipPort();
    }
}
