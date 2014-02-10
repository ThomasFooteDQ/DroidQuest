package com.droidquest.operation.swing.mode;

import javax.swing.*;
import java.awt.FileDialog;

import com.droidquest.devices.SmallChip;
import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.view.View;

/**
 * Operation that saves the state of a chip.
 */
public class SwingSaveChipOperation implements Operation {
    private final View view;
    private final Item currentAvatar;

    public SwingSaveChipOperation(Item currentAvatar, View view) {
        this.currentAvatar = currentAvatar;
        this.view = view;
    }

    @Override
    public void execute() {
        Item carried = currentAvatar.getCarrying();
        if (!(carried instanceof SmallChip)) {
            return;
        }

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(getViewComponent());
        FileDialog fd = new FileDialog(parentFrame,"Save Chip", FileDialog.SAVE);
        fd.setDirectory("chips");
        fd.setVisible(true);

        System.out.println("Dialog returned with "
                + fd.getDirectory()
                + fd.getFile());
        if (fd.getFile() != null)
            ((SmallChip) carried).SaveChip(fd.getDirectory()+fd.getFile());
    }

    public JComponent getViewComponent() {
        return (JComponent) view;
    }
}
