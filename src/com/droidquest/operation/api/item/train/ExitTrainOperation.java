package com.droidquest.operation.api.item.train;

import com.droidquest.items.Train;
import com.droidquest.operation.Operation;

/**
 * Somebody stop this train!
 */
public class ExitTrainOperation implements Operation {
    private final Train train;

    public ExitTrainOperation(Train train) {
        this.train = train;
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        train.exitTrain();
    }
}
