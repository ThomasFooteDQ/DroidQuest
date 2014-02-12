package com.droidquest.operation.api.avatar;

/**
 * Rotation direction enum
 */
public enum Rotation {
    Clockwise(1),
    CounterClockwise(-1);

    private final int direction;

    private Rotation(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
