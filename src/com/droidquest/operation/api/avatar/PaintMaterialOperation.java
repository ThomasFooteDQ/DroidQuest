package com.droidquest.operation.api.avatar;

import com.droidquest.avatars.PaintBrush;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Makes a paint brush paint!
 */
public class PaintMaterialOperation extends OperationAdapter {
    private final PaintBrush paintBrush;

    public PaintMaterialOperation(PaintBrush paintBrush) {
        this.paintBrush = paintBrush;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        paintBrush.paintMaterial();
    }
}
