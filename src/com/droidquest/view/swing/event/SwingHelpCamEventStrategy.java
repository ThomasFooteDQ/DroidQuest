package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.List;

import com.droidquest.avatars.HelpCam;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.swing.SwingView;

/**
 * Swing specific event strategy for the HelpCam avatar.
 */
public class SwingHelpCamEventStrategy extends AbstractSwingPlayerEventStrategy<HelpCam> {
    public SwingHelpCamEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(HelpCam player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;
                if (event.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    op = getOperationFactory().createReturnToGameCursorOperation();
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }
        };
    }

    @Override
    protected List<MouseListener> createMouseListeners(HelpCam player) {
        return Collections.<MouseListener>singletonList(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                getOperationFactory().createReturnToGameCursorOperation().execute();
                mouseEvent.consume();
            }
        });
    }
}
