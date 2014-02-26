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

        view.getViewPanel().addKeyListener(keyEventHandler);
        for (MouseListener mouseListener : mouseListeners) {
            view.getRoomPanel().addMouseListener(mouseListener);
        }

        addComponents(player);
    }

    @Override
    public void deactivate() {
        view.getViewPanel().removeKeyListener(keyEventHandler);
        for (MouseListener mouseListener : mouseListeners) {
            view.getRoomPanel().removeMouseListener(mouseListener);
        }

        removeComponents();
    }

    protected OperationFactory getOperationFactory() {
        return operationFactory;
    }

    protected SwingView getView() {
        return view;
    }

    protected void addComponents(Item player) {
        view.getControlPanel().revalidate();
        view.getControlPanel().repaint();
    }

    protected void removeComponents() {
        view.getControlPanel().removeAll();
        view.getControlPanel().revalidate();
        view.getControlPanel().repaint();
    }

    protected abstract KeyListener createKeyEventHandler(P player);

    protected abstract List<MouseListener> createMouseListeners(P player);
}