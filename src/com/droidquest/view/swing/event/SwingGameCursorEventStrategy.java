package com.droidquest.view.swing.event;

import javax.swing.KeyStroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import com.droidquest.avatars.GameCursor;
import com.droidquest.items.Item;
import com.droidquest.operation.api.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;
import com.droidquest.operation.swing.util.DirectionUtil;
import com.droidquest.view.swing.SwingView;
import com.droidquest.view.swing.control.ButtonPanel;
import com.droidquest.view.swing.control.CursorPad;
import com.droidquest.view.swing.control.OperationButton;

/**
 * Swing handler for GameCursor active events.
 */
public class SwingGameCursorEventStrategy extends AbstractSwingPlayerEventStrategy<GameCursor> {

    private CursorPad cursorPad;
    private ButtonPanel toolButtons;
    private ButtonPanel actionButtons;

    public SwingGameCursorEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected void addComponents(Item player) {
        toolButtons = new ButtonPanel("Tools");
        toolButtons.add(new OperationButton(
                getOperationFactory().createSwitchToSolderingPenOperation(player), KeyStroke.getKeyStroke('S')));
        toolButtons.add(new OperationButton(
                getOperationFactory().createToggleRemoteOperation(player), KeyStroke.getKeyStroke('R')));
        toolButtons.add(new OperationButton(
                getOperationFactory().createToggleToolboxOperation(player), KeyStroke.getKeyStroke('T')));

        actionButtons = new ButtonPanel("Actions");
        actionButtons.add(new OperationButton(
                getOperationFactory().createPickUpItemOperation(player), KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)));
        actionButtons.add(new OperationButton(
                getOperationFactory().createEnterItemOperation(player), KeyStroke.getKeyStroke('E')));
        actionButtons.add(new OperationButton(
                getOperationFactory().createExitItemOperation(player), KeyStroke.getKeyStroke('X')));

        cursorPad = new CursorPad(getOperationFactory(), player);

        getView().getControlPanel().add(toolButtons);
        getView().getControlPanel().add(actionButtons);
        getView().getControlPanel().add(cursorPad);

        getView().getControlPanel().revalidate();
    }

    @Override
    protected void removeComponents() {
        super.removeComponents();

        getView().getControlPanel().removeAll();
        toolButtons = null;
        actionButtons = null;
        cursorPad = null;
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
