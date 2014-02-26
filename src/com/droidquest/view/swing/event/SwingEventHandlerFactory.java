package com.droidquest.view.swing.event;

import java.util.HashMap;
import java.util.Map;

import com.droidquest.Game;
import com.droidquest.avatars.GameCursor;
import com.droidquest.avatars.HelpCam;
import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.avatars.Remote;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.items.Handle;
import com.droidquest.items.Item;
import com.droidquest.items.SpyCam;
import com.droidquest.items.Train;
import com.droidquest.view.api.event.PlayerEventHandler;
import com.droidquest.view.api.event.PlayerEventStrategy;
import com.droidquest.view.swing.SwingView;

/**
 * Factory class that creates swing specific Event handler classes
 */
public class SwingEventHandlerFactory {

    private SwingEventHandlerFactory() {}

    public static PlayerEventHandler createPlayerEventHandler(Game game) {
        Map<Class<? extends Item>, PlayerEventStrategy> strategies = new HashMap<Class<? extends Item>, PlayerEventStrategy>();
        strategies.put(GameCursor.class,
                new SwingGameCursorEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(Handle.class,
                new SwingHandleEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(HelpCam.class,
                new SwingHelpCamEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(LabCursor.class,
                new SwingLabCursorEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(PaintBrush.class,
                new SwingPaintBrushEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(Remote.class,
                new SwingRemoteEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(SolderingPen.class,
                new SwingSolderingPenEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(SpyCam.class,
                new SwingSpyCamEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));
        strategies.put(Train.class,
                new SwingTrainEventStrategy(game.getOperationFactory(), (SwingView) game.getView()));


        return new PlayerEventHandler(game, strategies);
    }
}
