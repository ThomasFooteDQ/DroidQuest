package com.droidquest.operation.api;

/**
 * Helper class for creating Operations
 */
public abstract class OperationAdapter implements Operation {
    private final String name;
    private final String iconFilename;

    public OperationAdapter() {
        this("", null);
    }

    public OperationAdapter(String name) {
        this(name, null);
    }

    public OperationAdapter(String name, String iconFilename) {
        this.name = name;
        this.iconFilename = iconFilename;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIconFilename() {
        return iconFilename;
    }

    @Override
    public boolean canExecute() {
        return false;
    }

    @Override
    public void execute() {
    }
}
