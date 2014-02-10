package com.droidquest.operation.swing;

import com.droidquest.Game;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.operation.Operation;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.operation.api.mode.SolderingPenOperation;
import com.droidquest.operation.api.mode.ToggleRemoteOperation;
import com.droidquest.operation.api.mode.ToggleToolboxOperation;
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
    public Operation createLabSolderingPenOperation(Item currentAvatar) {
        return new SolderingPenOperation(getCurrentLevel(), currentAvatar, createSaveChipOperation(currentAvatar));
    }

    @Override
    public Operation createLoadSmallChipOperation(Item avatar) {
        return new SwingLoadSmallChipOperation(avatar, getView());
    }

    private Operation createSaveChipOperation(Item currentAvatar) {
        return new SwingSaveChipOperation(currentAvatar, getView());
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
