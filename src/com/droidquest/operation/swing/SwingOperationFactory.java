package com.droidquest.operation.swing;

import com.droidquest.Game;
import com.droidquest.avatars.LabCursor;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.PickUpItemOperation;
import com.droidquest.operation.api.mode.HelpOperation;
import com.droidquest.operation.api.mode.SwitchToGameCursorOperation;
import com.droidquest.operation.api.mode.SwitchToPaintbrushOperation;
import com.droidquest.operation.api.mode.SwitchToRemoteOperation;
import com.droidquest.operation.api.mode.SwitchToSolderingPenOperation;
import com.droidquest.operation.api.mode.ToggleHotStateOperation;
import com.droidquest.operation.api.mode.ToggleRemoteOperation;
import com.droidquest.operation.api.mode.ToggleToolboxOperation;
import com.droidquest.operation.api.move.Direction;
import com.droidquest.operation.api.move.Distance;
import com.droidquest.operation.api.move.EnterItemOperation;
import com.droidquest.operation.api.move.ExitItemOperation;
import com.droidquest.operation.api.move.FlipCarriedDeviceOperation;
import com.droidquest.operation.api.move.MoveOperation;
import com.droidquest.operation.api.move.RotateCarriedDeviceOperation;
import com.droidquest.operation.api.move.Rotation;
import com.droidquest.operation.api.move.SetRoomOperation;
import com.droidquest.operation.swing.mode.SwingLoadSmallChipOperation;
import com.droidquest.operation.swing.mode.SwingSaveChipOperation;
import com.droidquest.view.View;

/**
 * "Swing" environment implementation of the OperationFactory.
 */
public class SwingOperationFactory implements OperationFactory {
    private final Game game;

    public SwingOperationFactory(Game game) {
        this.game = game;
    }

    @Override
    public Operation createEnterItemOperation(Item avatar) {
        return new EnterItemOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createExitItemOperation(Item avatar) {
        return new ExitItemOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createFlipCarriedDeviceOperation(Item avatar) {
        return new FlipCarriedDeviceOperation(avatar);
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
        return new MoveOperation(avatar, direction, distance);
    }

    @Override
    public Operation createOutputMemoryUsageOperation() {
        return new OutputMemoryUsageOperation();
    }

    @Override
    public Operation createPickUpItemOperation(Item avatar) {
        return new PickUpItemOperation(getCurrentLevel(), avatar);
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
    public Operation createToggleRemoteOperation() {
        return new ToggleRemoteOperation(getCurrentLevel());
    }

    @Override
    public Operation createToggleToolboxOperation(Item avatar) {
        return new ToggleToolboxOperation(getCurrentLevel(), avatar);
    }

    protected Level getCurrentLevel() {
        return game.getCurrentLevel();
    }

    protected View getView() {
        return game.getView();
    }
}
