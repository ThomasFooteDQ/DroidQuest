package com.droidquest.view.swing.control;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;

/**
 * Holds "buttons" for moving the player in the four cardinal cursor directions.
 */
public class CursorPad extends JPanel {
    private JButton up, left, down, right;
    private JButton nudgeUp, nudgeLeft, nudgeDown, nudgeRight;

    public CursorPad(OperationFactory factory, Item avatar) {
        setBorder(BorderFactory.createEtchedBorder());

        up = new OperationButton(factory.createMoveOperation(avatar, Direction.Up, Distance.Step),
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true));
        down = new OperationButton(factory.createMoveOperation(avatar, Direction.Down, Distance.Step),
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true));
        left = new OperationButton(factory.createMoveOperation(avatar, Direction.Left, Distance.Step),
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true));
        right = new OperationButton(factory.createMoveOperation(avatar, Direction.Right, Distance.Step),
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true));

        nudgeUp = new OperationButton(factory.createMoveOperation(avatar, Direction.Up, Distance.Nudge),
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK, true));
        nudgeDown = new OperationButton(factory.createMoveOperation(avatar, Direction.Down, Distance.Nudge),
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK, true));
        nudgeLeft = new OperationButton(factory.createMoveOperation(avatar, Direction.Left, Distance.Nudge),
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_DOWN_MASK, true));
        nudgeRight = new OperationButton(factory.createMoveOperation(avatar, Direction.Right, Distance.Nudge),
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK, true));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        add(up, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(nudgeUp, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(nudgeDown, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        add(down, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(left, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(nudgeLeft, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(nudgeRight, gbc);
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(right, gbc);
    }
}
