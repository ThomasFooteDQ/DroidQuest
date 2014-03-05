package com.droidquest.view.api.event;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.droidquest.Game;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.event.LevelChangeEvent;
import com.droidquest.event.PlayerChangeEvent;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.view.api.View;
import com.google.common.eventbus.EventBus;

public class PlayerEventHandlerTest {
    @Mock private Game game;
    @Mock private Level level;
    @Mock private View view;
    @Mock private PlayerEventStrategy gameCursorStrategy, solderingStrategy;
    private Map<Class<? extends Item>, PlayerEventStrategy> strategies;
    private GameCursor gameCursor;
    private EventBus eventBus;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        eventBus = new EventBus();
        when(game.getEventBus()).thenReturn(eventBus);
        when(game.getView()).thenReturn(view);

        gameCursor = new GameCursor(0, 0, null);

        strategies = new HashMap<Class<? extends Item>, PlayerEventStrategy>();
        strategies.put(GameCursor.class, gameCursorStrategy);
        strategies.put(SolderingPen.class, solderingStrategy);
    }

    @Test
    public void testInitialStateProcessing() throws Exception {
        // Given
        when(game.getCurrentLevel()).thenReturn(level);
        when(level.getPlayer()).thenReturn(gameCursor);

        // When
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);

        // Then
        verify(gameCursorStrategy).activate(gameCursor);
    }

    @Test
    public void testStrategiesAreSetToActiveWhenPlayerEventReceived() throws Exception {
        // Given
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);

        // When
        eventBus.post(new PlayerChangeEvent(level, null, gameCursor));

        // Then
        verify(gameCursorStrategy).activate(gameCursor);
    }

    @Test
    public void testExistingStrategiesIsDeactivatedWhenPlayerEventReceived() throws Exception {
        // Given
        final SolderingPen solderingPen = new SolderingPen();
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);

        // When
        eventBus.post(new PlayerChangeEvent(level, null, gameCursor));
        eventBus.post(new PlayerChangeEvent(level, gameCursor, solderingPen));

        // Then
        verify(gameCursorStrategy).activate(gameCursor);
        verify(gameCursorStrategy).deactivate();
        verify(solderingStrategy).activate(solderingPen);
    }

    @Test
    public void testUnknownPlayerTypesAreHandledGracefully() throws Exception {
        // Given
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);

        // When
        eventBus.post(new PlayerChangeEvent(level, null, new HelpCam(null)));

        // Then nothing bad should happen
    }

    @Test
    public void testStrategiesAreSetToActiveWhenLevelChangeEventReceived() throws Exception {
        // Given
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);
        when(level.getPlayer()).thenReturn(gameCursor);

        // When
        eventBus.post(new LevelChangeEvent(game, null, level));

        // Then
        verify(gameCursorStrategy).activate(gameCursor);
    }

    @Test
    public void testExistingStrategyIsDeactivatedWhenLevelChangeEventReceived() throws Exception {
        // Given
        PlayerEventHandler eventHandler = new PlayerEventHandler(game, strategies);
        final SolderingPen solderingPen = new SolderingPen();

        // When
        eventBus.post(new PlayerChangeEvent(level, null, gameCursor));
        when(level.getPlayer()).thenReturn(solderingPen);
        eventBus.post(new LevelChangeEvent(game, null, level));

        // Then
        verify(gameCursorStrategy).activate(gameCursor);
        verify(gameCursorStrategy).deactivate();
        verify(solderingStrategy).activate(solderingPen);
    }
}
