package com.droidquest.materials.switches;

import com.droidquest.items.Item;

public class SwitchA extends Switch {
    // Classes SwitchA & SwitchB are used in the double switch puzzle in
    // Level 3. SwitchA simply reacts to being touched and turns its value
    // to true. SwitchB does the same, but also looks for the existence of
    // SwitchA and polls its value. When both are true, SwitchB removes the
    // Sentry and changes the textbox to say "KLAATA BARADA NIKTU", or
    // something like that.

    public SwitchA() {
        super(ROT_LEFT);
    }

    public void TouchedByItem(Item item) {
        value = true;
    }

}
