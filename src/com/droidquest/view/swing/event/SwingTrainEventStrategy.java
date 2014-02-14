package com.droidquest.view.swing.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.droidquest.items.Train;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.swing.SwingView;

/**
 * Swing event strategy for Train events.  Alllll abooard!
 */
public class SwingTrainEventStrategy extends AbstractSwingPlayerEventStrategy<Train> {
    public SwingTrainEventStrategy(OperationFactory operationFactory, SwingView view) {
        super(operationFactory, view);
    }

    @Override
    protected KeyListener createKeyEventHandler(final Train player) {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                Operation op = null;
                if (event.getKeyCode() == KeyEvent.VK_SPACE) {
                    op = getOperationFactory().createExitTrainOperation(player);
                }

                if (op != null && op.canExecute()) {
                    op.execute();
                    event.consume();
                }
            }
        };
    }
}
