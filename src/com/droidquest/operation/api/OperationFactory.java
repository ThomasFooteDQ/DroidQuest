package com.droidquest.operation.api;

import com.droidquest.items.Item;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.move.Direction;
import com.droidquest.operation.api.move.Distance;
import com.droidquest.operation.api.move.Rotation;

/**
 * Factory interface for creating operations.
 */
public interface OperationFactory {

    Operation createEnterItemOperation(Item avatar);

    Operation createExitItemOperation(Item item);

    Operation createFlipCarriedDeviceOperation(Item avatar);

    Operation createHelpOperation(Item avatar);

    Operation createLabSolderingPenOperation(Item currentAvatar);

    Operation createLoadSmallChipOperation(Item avatar);

    Operation createMoveOperation(Item avatar, Direction direction, Distance distance);

    Operation createPickUpItemOperation(Item avatar);

    Operation createRotateCarriedDeviceOperation(Item avatar, Rotation direction);

    Operation createSetRoomOperation(Item avatar, Direction direction, boolean cheatRequired);

    Operation createSolderingPenOperation(Item currentAvatar);

    Operation createToggleRemoteOperation();

    Operation createToggleToolboxOperation(Item avatar);
}
