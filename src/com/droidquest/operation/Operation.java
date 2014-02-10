package com.droidquest.operation;

/**
 * Common interface for Operations.  Operations are commands that change the state of the game's model.
 */
public interface Operation {
    /**
     * Returns true if the operation can currently be executed.
     */
    public boolean canExecute();

    /**
     * Execute the operation.
     */
    public void execute();
}
