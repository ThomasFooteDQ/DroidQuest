package com.droidquest.view.swing.event;

import java.awt.event.KeyListener;

import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.api.event.PlayerEventStrategy;
import com.droidquest.view.swing.SwingView;

/**
 * Abstract base class to help create Swing PlayerEventStrategy implementations.
 *
 * <P> The avatar class
 */
public abstract class AbstractSwingPlayerEventStrategy<P extends Item> implements PlayerEventStrategy<P> {
    protected final OperationFactory operationFactory;
    protected final SwingView view;
    private KeyListener keyEventHandler;

    public AbstractSwingPlayerEventStrategy(OperationFactory operationFactory, SwingView view) {
        this.operationFactory = operationFactory;
        this.view = view;
    }

    @Override
    public void activate(P player) {
        keyEventHandler = createKeyEventHandler(player);

        view.getComponent().addKeyListener(keyEventHandler);
    }

    @Override
    public void deactivate() {
        view.getComponent().removeKeyListener(keyEventHandler);
    }

    protected OperationFactory getOperationFactory() {
        return operationFactory;
    }

    protected abstract KeyListener createKeyEventHandler(P player);
}