package com.droidquest.view.swing;

import javax.swing.*;

import java.awt.Point;

import com.droidquest.view.api.View;

/**
 * Swing specific View subclass.
 */
public interface SwingView extends View {
    Point toGameCoordSpace(Point swingCoordPoint);

    public JComponent getRoomPanel();

    public JComponent getControlPanel();
}
