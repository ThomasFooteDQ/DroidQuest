package com.droidquest.operation.api;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.move.Direction;
import com.droidquest.operation.api.move.Distance;

/**
 * Factory interface for creating operations.
 */
public interface OperationFactory {

    Operation createHelpOperation(Item avatar);

    Operation createLabSolderingPenOperation(Item currentAvatar);

    Operation createLoadSmallChipOperation(Item avatar);

    Operation createMoveOperation(Item avatar, Direction direction, Distance distance);

    Operation createSetRoomOperation(Item avatar, Direction direction, boolean cheatRequired);

    Operation createSolderingPenOperation(Item currentAvatar);

    Operation createToggleRemoteOperation();

    Operation createToggleToolboxOperation(Item avatar);
}
