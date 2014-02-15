package com.droidquest.view.swing.control;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
        setOpaque(false);
        setBorder(BorderFactory.createEtchedBorder());

        up = new OperationButton("Move Up", factory.createMoveOperation(avatar, Direction.Up, Distance.Step));
        down = new OperationButton("Move Down", factory.createMoveOperation(avatar, Direction.Down, Distance.Step));
        left = new OperationButton("Move Left", factory.createMoveOperation(avatar, Direction.Left, Distance.Step));
        right = new OperationButton("Move Right", factory.createMoveOperation(avatar, Direction.Right, Distance.Step));

        nudgeUp = new OperationButton("Nudge Up", factory.createMoveOperation(avatar, Direction.Up, Distance.Nudge));
        nudgeDown = new OperationButton("Nudge Down", factory.createMoveOperation(avatar, Direction.Down, Distance.Nudge));
        nudgeLeft = new OperationButton("Nudge Left", factory.createMoveOperation(avatar, Direction.Left, Distance.Nudge));
        nudgeRight = new OperationButton("Nudge Right", factory.createMoveOperation(avatar, Direction.Right, Distance.Nudge));

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
