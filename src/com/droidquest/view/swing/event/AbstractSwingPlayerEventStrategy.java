package com.droidquest.view.swing.event;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;

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
    private final OperationFactory operationFactory;
    private final SwingView view;
    private KeyListener keyEventHandler;
    private List<MouseListener> mouseListeners;

    public AbstractSwingPlayerEventStrategy(OperationFactory operationFactory, SwingView view) {
        this.operationFactory = operationFactory;
        this.view = view;
    }

    @Override
    public void activate(P player) {
        keyEventHandler = createKeyEventHandler(player);
        mouseListeners = createMouseListeners(player);

        view.getComponent().addKeyListener(keyEventHandler);
        for (MouseListener mouseListener : mouseListeners) {
            view.getComponent().addMouseListener(mouseListener);
        }
    }

    @Override
    public void deactivate() {
        view.getComponent().removeKeyListener(keyEventHandler);
        for (MouseListener mouseListener : mouseListeners) {
            view.getComponent().removeMouseListener(mouseListener);
        }
    }

    protected OperationFactory getOperationFactory() {
        return operationFactory;
    }

    protected abstract KeyListener createKeyEventHandler(P player);

    protected abstract List<MouseListener> createMouseListeners(P player);

    public SwingView getView() {
        return view;
    }
}