package com.droidquest.view.swing.control;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import com.droidquest.operation.api.Operation;

/**
 * Standard toolbar button for Operations
 */
public class OperationButton extends JButton {
    public OperationButton(Operation operation) {
        this(operation, null);
    }

    public OperationButton(Operation operation, KeyStroke acceleratorKey) {
        super(new OperationActionAdapter(operation, acceleratorKey));

        setPreferredSize(new Dimension(40, 40));
        setFocusable(false);
        if (operation.getIconFilename() != null) {
            setIcon(new ImageIcon(operation.getIconFilename()));
        } else {
            if (acceleratorKey.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                setText(Character.toString(acceleratorKey.getKeyChar()));
            }
        }

        if (acceleratorKey != null) {
            // get Accelerator text
            String acceleratorText = "";
            if (acceleratorKey != null) {
                int modifiers = acceleratorKey.getModifiers();
                if (modifiers > 0) {
                    acceleratorText = KeyEvent.getKeyModifiersText(modifiers);
                    acceleratorText += "+";
                }
                if (acceleratorKey.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    acceleratorText += acceleratorKey.getKeyChar();
                } else {
                    acceleratorText += KeyEvent.getKeyText(acceleratorKey.getKeyCode());
                }
            }

            setToolTipText(getToolTipText() + "     " + acceleratorText);
        }
    }
}
