package com.droidquest.view.api.event;

import com.droidquest.items.Item;

/**
 * Strategy class for event handlers.
 */
public interface PlayerEventStrategy<P extends Item> {
    /**
     * Activates the strategy.
     */
    void activate(P player);

    /**
     * Deactivates the strategy.
     */
    void deactivate();
}
