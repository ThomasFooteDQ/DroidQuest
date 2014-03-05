package com.droidquest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.droidquest.event.LevelChangeEvent;
import com.droidquest.levels.Level;
import com.droidquest.view.api.sound.SoundPlayer;
import com.google.common.eventbus.Subscribe;

public class GameTest {
    @Mock
    private SoundPlayer soundPlayer;
    private Game game;
    private List<EventObject> events;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        game = new GameImpl();
        game.setSoundPlayer(soundPlayer);

        events = new ArrayList<EventObject>();
    }

    @Test
    public void verifyThatChangingLevelGeneratesChangeEvent() {
        game.getEventBus().register(this);

        final Level level = new Level(game);
        game.setCurrentLevel(level);

        assertEquals("Should have received a LevelChangeEvent", 1, events.size());
        assertEquals(game, events.get(0).getSource());
    }

    @Subscribe
    public void onEvent(LevelChangeEvent event) {
        events.add(event);
    }
}
