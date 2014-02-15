package com.droidquest.view.swing.event;

import javax.swing.SwingUtilities;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import com.droidquest.avatars.SolderingPen;
import com.droidquest.operation.api.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event strategy for SolderingPen related events.
 */
public class SwingSolderingPenEventStrategy extends AbstractSwingPlayerEventStrategy<SolderingPen> {
    public SwingSolderingPenEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final SolderingPen player) {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                Operation op = null;
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        op = getOperationFactory().createMoveRepeatOperation(
                                player, DirectionUtil.getDirection(event.getKeyCode()), event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;

                switch (event.getKeyCode()) {
                    case KeyEvent.VK_C:
                        op = getOperationFactory().createSwitchToGameCursorOperation(player);
                        break;
                    case KeyEvent.VK_R:
                        op = getOperationFactory().createToggleRemoteOperation(player);
                        break;
                    case KeyEvent.VK_P:
                        op = getOperationFactory().createSwitchToPaintbrushOperation(player);
                        break;
                    case KeyEvent.VK_SLASH:
                        op = getOperationFactory().createHelpOperation(player);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        Direction direction = DirectionUtil.getDirection(event.getKeyCode());
                        op = getOperationFactory().createMoveOperation(player, direction,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                    case KeyEvent.VK_SPACE:
                        op = getOperationFactory().createWirePortOperation(player);
                        break;
                    case KeyEvent.VK_F:
                        op = getOperationFactory().createFlipPortOperation(player);
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
    protected List<MouseListener> createMouseListeners(final SolderingPen player) {
        return Collections.<MouseListener>singletonList(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                Operation op = null;
                if (SwingUtilities.isLeftMouseButton(event)) {
                    if (event.getClickCount()==1) {
                        op = getOperationFactory().createMoveSolderingPenToPixelOperation(player, event.getX(), event.getY());
                    }
                    else if (event.getClickCount()==2) {
                        op = getOperationFactory().createMoveSolderingPenDirectionalOperation(player, event.getX(), event.getY());
                    }
                } else if (SwingUtilities.isRightMouseButton(event)) {
                    op = getOperationFactory().createWirePortOperation(player);
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }
        });
    }
}
