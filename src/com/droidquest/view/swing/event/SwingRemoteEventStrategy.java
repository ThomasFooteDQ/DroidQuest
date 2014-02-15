package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import com.droidquest.avatars.Remote;
import com.droidquest.operation.api.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event handling strategy for the Remote.
 */
public class SwingRemoteEventStrategy extends AbstractSwingPlayerEventStrategy<Remote> {
    public SwingRemoteEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final Remote player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;

                switch (event.getKeyCode()) {
                    case KeyEvent.VK_S:
                        op = getOperationFactory().createSwitchToSolderingPenOperation(player);
                        break;
                    case KeyEvent.VK_C:
                        op = getOperationFactory().createSwitchToGameCursorOperation(player);
                        break;
                    case KeyEvent.VK_P:
                        op = getOperationFactory().createSwitchToPaintbrushOperation(player);
                        break;
                    case KeyEvent.VK_SLASH:
                        op = getOperationFactory().createHelpOperation(player);
                        break;
                    case KeyEvent.VK_RIGHT:
                        op = getOperationFactory().createMoveOperation(player, Direction.Right,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                    case KeyEvent.VK_LEFT:
                        op = getOperationFactory().createMoveOperation(player, Direction.Left,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                    case KeyEvent.VK_UP:
                        op = getOperationFactory().createMoveOperation(player, Direction.Up,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                    case KeyEvent.VK_DOWN:
                        op = getOperationFactory().createMoveOperation(player, Direction.Down,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                    case KeyEvent.VK_SPACE:
                        op = getOperationFactory().createToggleRemoteOperation(player);
                        break;
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent event) {
                Operation op = null;
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        final Direction direction = DirectionUtil.getDirection(event.getKeyCode());
                        op = getOperationFactory().createMoveRepeatOperation(
                                player, direction, event.isControlDown() ? Distance.Nudge : Distance.Step);
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
    protected List<MouseListener> createMouseListeners(Remote player) {
        return Arrays.<MouseListener>asList(
                new DefaultMouseListener(getOperationFactory(), player, getView()),
                new RightClickMouseListener(getOperationFactory().createToggleRemoteOperation(player)));
    }
}
