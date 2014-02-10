package com.droidquest.operation.api;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

/**
 * Factory interface for creating operations.
 */
public interface OperationFactory {
    Operation createSolderingPenOperation(Item currentAvatar);

    Operation createLabSolderingPenOperation(Item currentAvatar);
}
