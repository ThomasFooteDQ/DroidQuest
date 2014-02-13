package com.droidquest.operation.swing.util;

import java.awt.event.KeyEvent;

import com.droidquest.operation.api.avatar.Direction;

/**
 * Utilities for converting between Swing KeyEvents and Direction enums.
 */
public class DirectionUtil {
    public static Direction getDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                return Direction.Right;
            case KeyEvent.VK_LEFT:
                return Direction.Left;
            case KeyEvent.VK_UP:
                return Direction.Up;
            case KeyEvent.VK_DOWN:
                return Direction.Down;
        }

        throw new IllegalArgumentException("Unknown direction key: " + keyCode);
    }
}
