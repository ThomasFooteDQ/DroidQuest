package com.droidquest.operation.swing;

import com.droidquest.Game;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.PickUpItemOperation;
import com.droidquest.operation.api.mode.HelpOperation;
import com.droidquest.operation.api.mode.SolderingPenOperation;
import com.droidquest.operation.api.mode.ToggleRemoteOperation;
import com.droidquest.operation.api.mode.ToggleToolboxOperation;
import com.droidquest.operation.api.move.Direction;
import com.droidquest.operation.api.move.Distance;
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
    public Operation createFlipCarriedDeviceOperation(Item avatar) {
        return new FlipCarriedDeviceOperation(avatar);
    }

    @Override
    public Operation createHelpOperation(Item avatar) {
        return new HelpOperation(getCurrentLevel(), avatar);
    }

    @Override
    public Operation createLabSolderingPenOperation(Item currentAvatar) {
        return new SolderingPenOperation(getCurrentLevel(), currentAvatar, createSaveChipOperation(currentAvatar));
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
    public Operation createSolderingPenOperation(Item currentAvatar) {
        return new SolderingPenOperation(getCurrentLevel(), currentAvatar, null);
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
