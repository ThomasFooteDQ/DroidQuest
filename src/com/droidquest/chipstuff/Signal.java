package com.droidquest.chipstuff;

import java.io.Serializable;

public class Signal implements Serializable {
    transient private int index;
    private boolean[] value = new boolean[2];
    public boolean working;

    public Signal() {
        index = 0;
        working = true;
    }

    public void Flip() {
        index = 1 - index;
    }

    public boolean Get() {
        return value[index] && working;
    }

    public void Set(boolean v) {
        value[1 - index] = v && working;
    }
}
