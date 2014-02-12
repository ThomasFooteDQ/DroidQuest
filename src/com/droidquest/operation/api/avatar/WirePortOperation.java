package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.operation.Operation;

/**
 * Connects a port with the soldering pen.
 */
public class WirePortOperation implements Operation {
    private final SolderingPen solderingPen;

    public WirePortOperation(SolderingPen solderingPen) {
        this.solderingPen = solderingPen;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        solderingPen.WirePort();
    }
}
