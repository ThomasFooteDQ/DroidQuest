package com.droidquest.operation.api;

import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.avatars.SolderingPen;
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

    Operation createFlipPortOperation(SolderingPen solderingPen);

    Operation createHelpOperation(Item avatar);

    Operation createLabSolderingPenOperation(Item currentAvatar);

    Operation createLoadSmallChipOperation(Item avatar);

    Operation createMoveOperation(Item avatar, Direction direction, Distance distance);

    Operation createOutputMemoryUsageOperation();

    Operation createPaintMaterialOperation(PaintBrush paintBrush);

    Operation createPickUpItemOperation(Item avatar);

    Operation createReturnToGameCursorOperation();

    Operation createRotateCarriedDeviceOperation(Item avatar, Rotation direction);

    Operation createSetRoomOperation(Item avatar, Direction direction, boolean cheatRequired);

    Operation createSwitchToGameCursorOperation(Item avatar);

    Operation createSwitchToPaintbrushOperation(Item avatar);

    Operation createSwitchToRemoteOperation(Item avatare);

    Operation createSwitchToSolderingPenOperation(Item currentAvatar);

    Operation createToggleHotStateOperation(LabCursor avatar);

    Operation createTogglePaintColorOperation(PaintBrush avatar);

    Operation createToggleRemoteOperation(Item avatar);

    Operation createToggleToolboxOperation(Item avatar);

    Operation createWirePortOperation(SolderingPen solderingPen);
}
