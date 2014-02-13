package com.droidquest.operation.api;

import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.items.Handle;
import com.droidquest.items.Item;
import com.droidquest.items.SpyCam;
import com.droidquest.items.Train;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.avatar.Direction;
import com.droidquest.operation.api.avatar.Distance;
import com.droidquest.operation.api.avatar.Rotation;

/**
 * Factory interface for creating operations.
 */
public interface OperationFactory {

    Operation createEnterItemOperation(Item avatar);

    Operation createExitCameraOperation(SpyCam spyCam);

    Operation createExitItemOperation(Item item);

    Operation createExitTrainOperation(Train train);

    Operation createFlipCarriedDeviceOperation(Item avatar);

    Operation createFlipPortOperation(SolderingPen solderingPen);

    Operation createHandleDropOperation(Handle handle);

    Operation createHandleLeftOperation(Handle handle);

    Operation createHandleRightOperation(Handle handle);

    Operation createHelpOperation(Item avatar);

    Operation createLabSolderingPenOperation(Item currentAvatar);

    Operation createLoadSmallChipOperation(Item avatar);

    Operation createMoveOperation(Item avatar, Direction direction, Distance distance);

    Operation createMoveRepeatOperation(Item avatar, Direction direction, Distance distance);

    Operation createOutlineItemOperation(Item item);

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
