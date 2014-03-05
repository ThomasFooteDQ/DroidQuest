package com.droidquest.operation.api.avatar;

/**
 * Enum for the distance a move should cover.
 */
public enum Distance {
    /** Single Pixel Move */
    Nudge("Nudge"),
    /** Full Step Move */
    Step("Move");

    private final String label;

    private Distance(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
