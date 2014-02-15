package com.droidquest.view.swing.event;

import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationFactory;

/**
 * Mouse event handler that runs the Pick Up operation on right click
 */
public class PickUpOnRightClickMouseListener extends RightClickMouseListener {
    public PickUpOnRightClickMouseListener(Item avatar, OperationFactory operationFactory) {
        super(operationFactory.createPickUpItemOperation(avatar));

    }
}
