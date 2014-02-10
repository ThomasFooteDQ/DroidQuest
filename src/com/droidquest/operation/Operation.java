package com.droidquest.operation;

/**
 * Common interface for Operations.  Operations are commands that change the state of the game's model.
 */
public interface Operation {
    /**
     * Execute the operation.
     */
    public void execute();
}
