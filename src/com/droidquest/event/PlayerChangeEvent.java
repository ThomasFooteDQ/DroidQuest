package com.droidquest.event;

import java.util.EventObject;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;

/**
 * Event that is published when the current player avatar changes
 */
public class PlayerChangeEvent extends EventObject {
    private final Item oldPlayer;
    private final Item newPlayer;

    public PlayerChangeEvent(Level source, Item oldPlayer, Item newPlayer) {
        super(source);

        this.oldPlayer = oldPlayer;
        this.newPlayer = newPlayer;
    }

    public Item getOldPlayer() {
        return oldPlayer;
    }

    public Item getNewPlayer() {
        return newPlayer;
    }
}
