package com.droidquest.view.api.event;

import java.util.Map;

import com.droidquest.Game;
import com.droidquest.event.LevelChangeEvent;
import com.droidquest.event.PlayerChangeEvent;
import com.droidquest.items.Item;
import com.google.common.eventbus.Subscribe;

/**
 * Generic event handler that listens for PlayerChangeEvents and activates the corresponding PlayerEventStrategy.
 */
public class PlayerEventHandler {
    private final Game game;
    private PlayerEventStrategy currentStrategy;
    private Map<Class<? extends Item>, PlayerEventStrategy> eventStrategies;

    public PlayerEventHandler(Game game, Map<Class<? extends Item>, PlayerEventStrategy> eventStrategies) {
        this.game = game;
        this.eventStrategies = eventStrategies;

        game.getEventBus().register(this);
        if (game.getCurrentLevel() != null) {
            activateStrategy(game.getCurrentLevel().getPlayer());
        }
    }

    @Subscribe
    public void onLevelChange(LevelChangeEvent levelChangeEvent) {
        if (currentStrategy != null) {
            currentStrategy.deactivate();
        }

        if (levelChangeEvent.getNewLevel() != null) {
            activateStrategy(levelChangeEvent.getNewLevel().getPlayer());
        }
    }

    @Subscribe
    public void onPlayerChange(PlayerChangeEvent event) {
        if (currentStrategy != null) {
            currentStrategy.deactivate();
        }

        activateStrategy(event.getNewPlayer());
    }

    private void activateStrategy(Item player) {
        if (player != null) {
            currentStrategy = eventStrategies.get(player.getClass());
            if (currentStrategy != null) {
                currentStrategy.activate(player);
            }
        }
    }
}
