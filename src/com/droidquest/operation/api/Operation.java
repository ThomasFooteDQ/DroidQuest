package com.droidquest.operation.api;

/**
 * Common interface for Operations.  Operations are commands that change the state of the game's model.
 */
public interface Operation {
    /**
     * Returns a displayable name for this operation
     */
    String getName();

    /**
     * Returns the icon's filename
     */
    String getIconFilename();

    /**
     * Returns true if the operation can currently be executed.
     */
    public boolean canExecute();

    /**
     * Execute the operation.
     */
    public void execute();
}
