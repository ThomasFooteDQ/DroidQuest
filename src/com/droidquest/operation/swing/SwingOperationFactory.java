package com.droidquest.operation.swing;

import com.droidquest.Game;
import com.droidquest.avatars.LabCursor;
import com.droidquest.avatars.PaintBrush;
import com.droidquest.avatars.SolderingPen;
import com.droidquest.items.Handle;
import com.droidquest.items.Item;
import com.droidquest.items.SpyCam;
import com.droidquest.items.Train;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.KeyRepeatTracker;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.avatar.*;
import com.droidquest.operation.api.item.handle.HandleDropOperation;
import com.droidquest.operation.api.item.handle.HandleLeftOperation;
import com.droidquest.operation.api.item.handle.HandleRightOperation;
import com.droidquest.operation.api.item.spycam.ExitCameraOperation;
import com.droidquest.operation.api.item.train.ExitTrainOperation;
import com.droidquest.operation.swing.avatar.SwingLoadSmallChipOperation;
import com.droidquest.operation.swing.avatar.SwingSaveChipOperation;
import com.droidquest.operation.swing.util.OutputMemoryUsageOperation;
import com.droidquest.view.View;

/**
 * "Swing" environment implementation of the OperationFactory.
 */
public class SwingOperationFactory implements OperationFactory {
    private final Game game;
    private final KeyRepeatTracker keyRepeatTracker = new KeyRepeatTracker(500);

    public SwingOperationFactory(Game game) {
        this.game = game;
    }

    @Override
    public Operation createEnterItemOperation(Item avatar) {
        return new EnterItemOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createExitCameraOperation(SpyCam spyCam) {
        return new ExitCameraOperation(spyCam);
    }

    @Override
    public Operation createExitItemOperation(Item avatar) {
        return new ExitItemOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createExitTrainOperation(Train train) {
        return new ExitTrainOperation(train);
    }

    @Override
    public Operation createFlipCarriedDeviceOperation(Item avatar) {
        return new FlipCarriedDeviceOperation(avatar);
    }

    @Override
    public Operation createFlipPortOperation(SolderingPen solderingPen) {
        return new FlipPortOperation(solderingPen);
    }

    @Override
    public Operation createHandleDropOperation(Handle handle) {
        return new HandleDropOperation(handle);
    }

    @Override
    public Operation createHandleLeftOperation(Handle handle) {
        return new HandleLeftOperation(handle);
    }

    @Override
    public Operation createHandleRightOperation(Handle handle) {
        return new HandleRightOperation(handle);
    }

    @Override
    public Operation createHelpOperation(Item avatar) {
        return new HelpOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createLabSolderingPenOperation(Item currentAvatar) {
        return new SwitchToSolderingPenOperation(getCurrentLevel(), currentAvatar, createSaveChipOperation(currentAvatar));
    }

    @Override
    public Operation createLoadSmallChipOperation(Item avatar) {
        return new SwingLoadSmallChipOperation(avatar, getView());
    }

    @Override
    public Operation createMoveOperation(Item avatar, Direction direction, Distance distance) {
        return new MoveOperation(avatar, direction, distance, keyRepeatTracker);
    }

    @Override
    public Operation createMoveRepeatOperation(Item avatar, Direction direction, Distance distance) {
        return new MoveRepeatOperation(avatar, direction, distance, keyRepeatTracker);
    }

    @Override
    public Operation createOutputMemoryUsageOperation() {
        return new OutputMemoryUsageOperation();
    }

    @Override
    public Operation createPaintMaterialOperation(PaintBrush paintBrush) {
        return new PaintMaterialOperation(paintBrush);
    }

    @Override
    public Operation createPickUpItemOperation(Item avatar) {
        return new PickUpItemOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createReturnToGameCursorOperation() {
        return new ReturnToGameCursorOperation(getCurrentLevel());
    }

    @Override
    public Operation createRotateCarriedDeviceOperation(Item avatar, Rotation direction) {
        return new RotateCarriedDeviceOperation(avatar, direction);
    }

    private Operation createSaveChipOperation(Item currentAvatar) {
        return new SwingSaveChipOperation(currentAvatar, getView());
    }

    @Override
    public Operation createSetRoomOperation(Item avatar, Direction direction, boolean cheatRequired) {
        return new SetRoomOperation(getCurrentLevel(), avatar, direction, cheatRequired);
    }

    @Override
    public Operation createSwitchToGameCursorOperation(Item avatar) {
        return new SwitchToGameCursorOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createSwitchToSolderingPenOperation(Item currentAvatar) {
        return new SwitchToSolderingPenOperation(getCurrentLevel(), currentAvatar, null);
    }

    @Override
    public Operation createSwitchToPaintbrushOperation(Item avatar) {
        return new SwitchToPaintbrushOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createSwitchToRemoteOperation(Item avatar) {
        return new SwitchToRemoteOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createToggleHotStateOperation(LabCursor avatar) {
        return new ToggleHotStateOperation(avatar);
    }

    @Override
    public Operation createTogglePaintColorOperation(PaintBrush avatar) {
        return new TogglePaintColorOperation(avatar);
    }

    @Override
    public Operation createToggleRemoteOperation(Item avatar) {
        return new ToggleRemoteOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createToggleToolboxOperation(Item avatar) {
        return new ToggleToolboxOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createWirePortOperation(SolderingPen solderingPen) {
        return new WirePortOperation(solderingPen);
    }

    protected Level getCurrentLevel() {
        return game.getCurrentLevel();
    }

    protected View getView() {
        return game.getView();
    }
}
