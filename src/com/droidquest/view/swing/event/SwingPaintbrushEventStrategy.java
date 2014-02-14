package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.droidquest.avatars.PaintBrush;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event handler for PaintBrush avatar events.
 */
public class SwingPaintBrushEventStrategy extends AbstractSwingPlayerEventStrategy<PaintBrush> {
    public SwingPaintBrushEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final PaintBrush player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_C:
                        op = getOperationFactory().createSwitchToGameCursorOperation(player);
                        break;
                    case KeyEvent.VK_S:
                        op = getOperationFactory().createSwitchToSolderingPenOperation(player);
                        break;
                    case KeyEvent.VK_R:
                        op = getOperationFactory().createSwitchToRemoteOperation(player);
                        break;
                    case KeyEvent.VK_SLASH:
                        op = getOperationFactory().createHelpOperation(player);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        op = getMoveOperation(event, DirectionUtil.getDirection(event.getKeyCode()));
                        break;
                    case KeyEvent.VK_P:
                        op = getOperationFactory().createTogglePaintColorOperation(player);
                        break;
                    case KeyEvent.VK_SPACE:
                        op = getOperationFactory().createPaintMaterialOperation(player);
                        break;
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }

            private Operation getMoveOperation(KeyEvent e, Direction direction) {
                Operation op;
                if (e.isShiftDown()) {
                    op = getOperationFactory().createSetRoomOperation(player, direction, false);
                } else {
                    op = getOperationFactory().createMoveOperation(player,
                            Direction.Right, e.isControlDown() ? Distance.Nudge : Distance.Step);
                }
                return op;
            }
        };
    }
}
