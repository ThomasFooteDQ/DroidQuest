package com.droidquest.avatars;

/**
 * Interface to support handling a common interface for player types.
 * Particularly, since SolderingPen needs to be a Device, this can
 * be used to provide a player avatar contract.
 */
public interface Avatar {

    /**
     * Handle change to game cursor.
     * @return boolean whether the change was handled.
     */
    public boolean handleGameCursor();

    /**
     * Handle change to solder pen.
     * @return boolean whether the change was handled.
     */
    public boolean handleSolderPen();

    /**
     * Handle opening / summoning the toolbox
     * @return boolean whether the change was handled.
     */
    public boolean handleToolbox();

    /**
     * Handle starting / stopping the remote.
     * @return boolean whether the change was handled.
     */
    public boolean handleRadio();

    /**
     * Handle rotating a Device object
     * @param direction -1 for counter clockwise, 1 for clockwise
     * @return boolean whether the change was handled.
     */
    public boolean handleRotateDevice(int direction);

    /**
     * Handle setting the cursor to hot.
     * @return boolean whether the change was handled.
     */
    public boolean handleHotCursor();

    /**
     * Handle transforming player to the paintbrush
     * @return boolean whether the change was handled.
     */
    public boolean handlePaintbrush();

    /**
     * Handle loading a small chip from a saved program.
     * @return boolean whether the change was handled.
     */
    public boolean handleLoadSmallChip();

    /**
     * Handle context specific help (including chip help)
     * @return boolean whether the change was handled.
     */
    public boolean handleHelp();

    /**
     * Handle entering an inner room (robot)
     * @return boolean whether the change was handled.
     */
    public boolean handleEnterRoom();

    /**
     * Handle exiting an inner room (robot)
     * @return boolean whether the change was handled.
     */
    public boolean handleExitRoom();

    /**
     * Handle flipping a device - either state or direction.
     * @return boolean whether the change was handled.
     */
    public boolean handleFlipDevice();

}
