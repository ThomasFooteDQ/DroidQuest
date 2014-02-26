package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import com.droidquest.items.SpyCam;
import com.droidquest.operation.api.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event handling strategy for the SpyCam.
 */
public class SwingSpyCamEventStrategy extends AbstractSwingPlayerEventStrategy<SpyCam> {
    public SwingSpyCamEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final SpyCam player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;

                switch (event.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        Direction direction = DirectionUtil.getDirection(event.getKeyCode());
                        op = getOperationFactory().createSetRoomOperation(player, direction, false);
                        break;
                    
                    case KeyEvent.VK_SPACE:
                        op = getOperationFactory().createExitCameraOperation(player);
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
    protected List<MouseListener> createMouseListeners(SpyCam player) {
        return Collections.<MouseListener>singletonList(
                new RightClickMouseListener(getOperationFactory().createExitCameraOperation(player)));
    }
}
