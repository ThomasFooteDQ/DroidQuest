package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;

/**
 * Swing handler for GameCursor active events.
 */
public class SwingGameCursorEventStrategy extends AbstractSwingPlayerEventStrategy<GameCursor> {

    public SwingGameCursorEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(GameCursor player) {
        return new SwingGameCursorEventStrategy.KeyEventHandler(player);
    }

    @Override
    protected List<MouseListener> createMouseListeners(GameCursor player) {
        return Arrays.<MouseListener>asList(
                new DefaultMouseListener(getOperationFactory(), player, getView()),
                new PickUpOnRightClickMouseListener(player, getOperationFactory()));
    }

    private class KeyEventHandler extends KeyAdapter {
        private final Item avatar;

        public KeyEventHandler(Item avatar) {
            this.avatar = avatar;
        }

        @Override
        public void keyReleased(KeyEvent event) {
            Operation op = null;

            switch (event.getKeyCode()) {
                case KeyEvent.VK_L:
                    op = getOperationFactory().createLoadSmallChipOperation(avatar);
                    break;
                case KeyEvent.VK_S:
                    op = getOperationFactory().createSwitchToSolderingPenOperation(avatar);
                    break;
                case KeyEvent.VK_R:
                    op = getOperationFactory().createToggleRemoteOperation(avatar);
                    break;
                case KeyEvent.VK_T:
                    op = getOperationFactory().createToggleToolboxOperation(avatar);
                    break;
                case KeyEvent.VK_SLASH:
                    op = getOperationFactory().createHelpOperation(avatar);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    Direction direction = DirectionUtil.getDirection(event.getKeyCode());
                    if (event.isShiftDown()) {
                        op = getOperationFactory().createSetRoomOperation(avatar, direction, true);
                    } else {
                        op = getOperationFactory().createMoveOperation(avatar, direction,
                                event.isControlDown() ? Distance.Nudge : Distance.Step);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    op = getOperationFactory().createPickUpItemOperation(avatar);
                    break;
                case KeyEvent.VK_CLOSE_BRACKET:
                    op = getOperationFactory().createRotateCarriedDeviceOperation(avatar, Rotation.Clockwise);
                    break;
                case KeyEvent.VK_OPEN_BRACKET:
                    op = getOperationFactory().createRotateCarriedDeviceOperation(avatar, Rotation.CounterClockwise);
                    break;
                case KeyEvent.VK_E:
                    op = getOperationFactory().createEnterItemOperation(avatar);
                    break;
                case KeyEvent.VK_X:
                    op = getOperationFactory().createExitItemOperation(avatar);
                    break;
                case KeyEvent.VK_F:
                    op = getOperationFactory().createFlipCarriedDeviceOperation(avatar);
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
                    final Direction direction = DirectionUtil.getDirection(event.getKeyCode());
                    op = getOperationFactory().createMoveRepeatOperation(
                            avatar, direction, event.isControlDown() ? Distance.Nudge : Distance.Step);
                    break;

                case KeyEvent.VK_SPACE:
                    op = getOperationFactory().createOutlineItemOperation(avatar);
                    break;
            }

            if (op != null && op.canExecute()) {
                op.execute();
                event.consume();
            }
        }
    }
}
