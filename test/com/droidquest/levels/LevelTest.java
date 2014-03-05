package com.droidquest.levels;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.droidquest.Game;
import com.droidquest.avatars.GameCursor;
import com.droidquest.event.PlayerChangeEvent;
import com.droidquest.view.api.sound.SoundPlayer;
import com.google.common.eventbus.EventBus;

public class LevelTest {
    @Mock private Game game;
    @Mock private SoundPlayer soundPlayer;
    @Mock private EventBus eventBus = new EventBus();
    private Level level;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(game.getEventBus()).thenReturn(eventBus);
        when(game.getSoundPlayer()).thenReturn(soundPlayer);

        level = new Level(game);
    }

    @Test
    public void testPlayerEvents() {
        GameCursor avatar = new GameCursor(0, 0, null);
        level.setPlayer(avatar);

        verify(eventBus, times(1)).post(isA(PlayerChangeEvent.class));
    }
}
