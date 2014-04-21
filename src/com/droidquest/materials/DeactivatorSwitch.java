package com.droidquest.materials;

import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.items.Sentry;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;

public class DeactivatorSwitch extends Material {
    // This object deactivates the Sentry in the Hot Wires room. When
    // activated, it sets the counter at 20 seconds, sets all versions of
    // the HotWires material to value=false, sets the sentry program so that
    // it's not sensitive to the area, and updates the timer text display.
    // When the counter reaches 0, it resets everything back to normal.

    private boolean value;
    private Date timeout;
    private ImageIcon[] images;
    private transient Item sentry = null;
    private transient TextBox textbox = null;

    public DeactivatorSwitch() {
        super(true, false);
        value = false;
    }

    public void GenerateIcons() {
        images = new ImageIcon[2];
        for (int a = 0; a < 2; a++) {
            images[a] = new ImageIcon(new BufferedImage(28, 32, BufferedImage.TYPE_4BYTE_ABGR));
            Graphics g;
            try {
                g = images[a].getImage().getGraphics();
            }
            catch (NullPointerException e) {
                System.out.println("Could not get Graphics pointer to " + getClass() + " Image");
                return;
            }
            Graphics2D g2 = (Graphics2D) g;
            Color c;
            if (a == 0) {
                c = Color.white;
            }
            else {
                c = new Color(255, 128, 0);
            }

            g2.setColor(c);
            g.fillRect(0, 0, 28, 32);
            g2.setColor(Color.black);
            g.fillRect(4, 14, 20, 4);
            g.fillRect(8, 12, 12, 8);
            g.fillRect(12, 8, 4, 16);
            g2.setColor(c);
            g.fillRect(12, 14, 4, 4);
        }

        icon = images[0];
        if (value) {
            icon = images[1];
        }
    }

    public void TouchedByItem(Item item) {
        if (!value) {
            value = true;
            Date now = new Date();
            timeout = new Date(now.getTime() + 20000);
            // Find HotWires and set their value to false;
            for (int a = 0; a < level.materials.size(); a++) {
                Material mat = level.materials.elementAt(a);
                if (mat instanceof HotWires) {
                    HotWires hw = (HotWires) mat;
                    if (hw.wall != 0) {
                        hw.value = false;
                    }
                }
            }

            // Find Sentry and set it's progam to something else.
            if (sentry == null) {
                for (int a = 0; a < level.items.size(); a++) {
                    Item i = level.items.elementAt(a);
                    if (i instanceof Sentry && i.room == item.room.upRoom) {
                        sentry = i;
                    }
                }
            }
            ((Sentry) sentry).protect[3] = 0;

            // Find the TextBox
            if (textbox == null) {
                textbox = item.room.textBoxes.elementAt(0);
            }
        }
    }

    public void Animate() {
        icon = images[0];
        if (value) {
            icon = images[1];
            Date now = new Date();
            long timer = timeout.getTime() - now.getTime();
            if (timer > 0) {
                long seconds = Math.abs(timer / 1000) + 1;
                if (seconds < 10) {
                    textbox.textString = "0" + seconds;
                }
                else {
                    textbox.textString = "" + seconds;
                }
            }
            else {
                for (int a = 0; a < level.materials.size(); a++) {
                    Material mat = level.materials.elementAt(a);
                    if (mat instanceof HotWires) {
                        ((HotWires) mat).value = true;
                    }
                }
                ((Sentry) sentry).protect[3] = 11 * 32;
                value = false;
                textbox.textString = "00";
            }
        }
    }

}