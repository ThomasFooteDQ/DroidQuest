package com.droidquest.view.swing.event;

import static com.droidquest.operation.swing.util.DirectionUtil.getDirection;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.droidquest.avatars.LabCursor;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event handler code for the LabCursor.
 */
public class SwingLabCursorEventStrategy extends AbstractSwingPlayerEventStrategy<LabCursor> {
    
    public SwingLabCursorEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }
    
    @Override
    protected KeyListener createKeyEventHandler(final LabCursor player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;
                switch (event.getKeyCode()) {
                case KeyEvent.VK_L:
                    op = getOperationFactory().createLoadSmallChipOperation(player);
                    break;
                case KeyEvent.VK_H:
                    op = getOperationFactory().createToggleHotStateOperation(player);
                    break;
                case KeyEvent.VK_S:
                    op = getOperationFactory().createLabSolderingPenOperation(player);
                    break;
                case KeyEvent.VK_R:
                    op = getOperationFactory().createToggleRemoteOperation(player);
                    break;
                case KeyEvent.VK_P:
                    op = getOperationFactory().createSwitchToPaintbrushOperation(player);
                    break;
                case KeyEvent.VK_T:
                    op = getOperationFactory().createToggleToolboxOperation(player);
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
                    op = getOperationFactory().createPickUpItemOperation(player);
                    break;
                case KeyEvent.VK_CLOSE_BRACKET:
                    op = getOperationFactory().createRotateCarriedDeviceOperation(player, Rotation.Clockwise);
                    break;
                case KeyEvent.VK_OPEN_BRACKET:
                    op = getOperationFactory().createRotateCarriedDeviceOperation(player, Rotation.CounterClockwise);
                    break;
                case KeyEvent.VK_E:
                    op = getOperationFactory().createEnterItemOperation(player);
                    break;
                case KeyEvent.VK_X:
                    op = getOperationFactory().createExitItemOperation(player);
                    break;
                case KeyEvent.VK_F:
                    op = getOperationFactory().createFlipCarriedDeviceOperation(player);
                    break;
                case KeyEvent.VK_M:
                    op = getOperationFactory().createOutputMemoryUsageOperation();
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
                        op = getOperationFactory().createMoveRepeatOperation(
                                player, getDirection(event.getKeyCode()), event.isControlDown() ? Distance.Nudge : Distance.Step);
                        break;
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                }
            }
        };
    }
}
