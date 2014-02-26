package com.droidquest.operation.api.item;

import java.awt.Color;

import com.droidquest.items.Item;
import com.droidquest.operation.api.OperationAdapter;

/**
 * Operation which sets the outline flag on an item.
 */
public class OutlineItemOperation extends OperationAdapter {
    private final Item item;

    public OutlineItemOperation(Item item) {
        this.item = item;
    }

    @Override
    public boolean canExecute() {
        return item != null;
    }

    @Override
    public void execute() {
        item.setOutline(Color.white);
    }
}
