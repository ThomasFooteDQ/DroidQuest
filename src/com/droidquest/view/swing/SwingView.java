package com.droidquest.view.swing;

import javax.swing.*;

import com.droidquest.view.api.View;

/**
 * Swing specific View subclass.
 */
public interface SwingView extends View {
    public JComponent getComponent();
}
