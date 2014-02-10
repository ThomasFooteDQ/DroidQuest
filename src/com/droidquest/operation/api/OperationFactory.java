package com.droidquest.operation.api;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;

/**
 * Factory interface for creating operations.
 */
public interface OperationFactory {

    Operation createLabSolderingPenOperation(Item currentAvatar);

    Operation createLoadSmallChipOperation(Item avatar);

    Operation createSolderingPenOperation(Item currentAvatar);

    Operation createToggleRemoteOperation();

    Operation createToggleToolboxOperation(Item avatar);
}
