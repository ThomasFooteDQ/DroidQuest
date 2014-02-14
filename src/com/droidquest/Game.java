package com.droidquest;

import com.droidquest.controller.ClockTickHandler;
import com.droidquest.levels.Level;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.api.View;
import com.droidquest.view.api.sound.SoundPlayer;
import com.google.common.eventbus.EventBus;

/**
 * Game Interface.  Game is a single context object containing game-wide variables and resources.
 */
public interface Game {
    Level getCurrentLevel();

    void setCurrentLevel(Level level);

    void loadLevel(String filename);

    void saveLevel();

    void saveLevel(String filename);

    boolean isSuspended();

    void suspend();

    void restart();

    SoundPlayer getSoundPlayer();

    void setSoundPlayer(SoundPlayer soundPlayer);

    ClockTickHandler getClockTickHandler();

    void setView(View view);

    View getView();

    OperationFactory getOperationFactory();

    void setOperationFactory(OperationFactory operationFactory);

    EventBus getEventBus();
}
