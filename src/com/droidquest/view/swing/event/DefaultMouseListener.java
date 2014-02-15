package com.droidquest.view.swing.event;

import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.swing.SwingView;

/**
 * The Default mouse listener used by most avatars moves the player to the click position on single click, and
 * moves the player in the direction of the click on a double click.
 */
public class DefaultMouseListener extends MouseAdapter {

    private final Item avatar;
    private final OperationFactory operationFactory;
    private final SwingView view;

    public DefaultMouseListener(OperationFactory operationFactory, Item avatar, SwingView view) {
        this.avatar = avatar;
        this.operationFactory = operationFactory;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Operation op = getMouseClickOperation(mouseEvent);
        if (op != null && op.canExecute()) {
            op.execute();
            mouseEvent.consume();
        }
    }

    protected Operation getMouseClickOperation(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Point clickPoint = view.toGameCoordSpace(e.getPoint());
            if (e.getClickCount() == 1) {
                return getOperationFactory().createMoveToPixelOperation(avatar, (int) clickPoint.getX(), (int) clickPoint.getY());
            } else if (e.getClickCount() == 2) {
                return getOperationFactory().createMoveDirectionalOperation(avatar, (int) clickPoint.getX(), (int) clickPoint.getY());
            }
        }
        return null;
    }

    public OperationFactory getOperationFactory() {
        return operationFactory;
    }
}
