package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import com.droidquest.items.Handle;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event handler class for the Handle item.
 */
public class SwingHandleEventStrategy extends AbstractSwingPlayerEventStrategy<Handle> {
    public SwingHandleEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final Handle player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;

                switch (event.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        op = getOperationFactory().createHandleRightOperation(player);
                        break;
                    case KeyEvent.VK_LEFT:
                        op = getOperationFactory().createHandleLeftOperation(player);
                        break;
                    case KeyEvent.VK_SPACE:
                        op = getOperationFactory().createHandleDropOperation(player);
                        break;
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }
        };
    }

    @Override
    protected List<MouseListener> createMouseListeners(Handle player) {
        return Collections.<MouseListener>singletonList(
                new RightClickMouseListener(getOperationFactory().createHandleDropOperation(player)));
    }
}
