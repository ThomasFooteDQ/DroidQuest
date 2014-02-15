package com.droidquest.operation.swing.util;

import com.droidquest.operation.api.OperationAdapter;

/**
 * Debug operation that outputs current memory usage.
 */
public class OutputMemoryUsageOperation extends OperationAdapter {
    @Override
    public boolean canExecute() {
        return true;
    }

    @Override
    public void execute() {
        Runtime runtime = Runtime.getRuntime();
        long freemem = runtime.freeMemory();
        long totalmem = runtime.totalMemory();
        System.out.println("Total Memory = "+ totalmem
                + ", (" + totalmem/1024 + "K), ("
                + totalmem/1024/1024 + "M)");
        System.out.println("Free Memory = "+ freemem
                + ", (" + freemem/1024 + "K), ("
                + freemem/1024/1024 + "M)");
    }
}
