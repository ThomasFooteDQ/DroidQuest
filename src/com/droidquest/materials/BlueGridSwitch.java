package com.droidquest.materials;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.ImageIcon;

import com.droidquest.Room;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;

public class BlueGridSwitch extends Material {
    // This object turns off the Blue grid (and HotWires), and opens the
    // doorway to the room above.

    private ImageIcon[] images;
    private boolean value = false;
    private int animationState = 0;
    private transient Room room = null;
    private Date timeout;
    private transient TextBox textbox = null;

    public BlueGridSwitch() {
        super(true, false);
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
        if (animationState == 0) {
            animationState = 1;
            room = item.room;
            timeout = new Date(new Date().getTime() + 20000);
            textbox = item.room.textBoxes.elementAt(0);
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
                textbox.textString = "00";
                animationState++;
            }

        }

        switch (animationState) {
            case 1:
                value = true;
                room.SetMaterial(2, 0, 0);
                room.upRoom.SetMaterial(2, 11, 0);
                for (int a = 0; a < level.materials.size(); a++) {
                    Material mat = level.materials.elementAt(a);
                    if (mat instanceof HotWires) {
                        HotWires hw = (HotWires) mat;
                        if (hw.wall == 0) {
                            hw.value = false;
                        }
                    }
                }
                for (int a = 0; a < 5; a++) {
                    Graphix gr = room.graphix.elementAt(a);
                    gr.filenames = new String[]{"whiteHorizontal.gif"};
                    gr.GenerateIcons();
                }
                for (int a = 0; a < 5; a++) {
                    Graphix gr = room.graphix.elementAt(a + 5);
                    gr.filenames = new String[]{"whiteVertical.gif"};
                    gr.GenerateIcons();
                }
                animationState++;
                break;
            case 2:
                room.SetMaterial(3, 0, 0);
                room.upRoom.SetMaterial(3, 11, 0);
                animationState++;
                break;
            case 3:
                room.SetMaterial(4, 0, 0);
                room.upRoom.SetMaterial(4, 11, 0);
                animationState++;
                break;
            case 4:
                room.SetMaterial(5, 0, 0);
                room.upRoom.SetMaterial(5, 11, 0);
                animationState++;
                break;
            case 5:
                break;
            case 6:
                room.SetMaterial(5, 0, 9);
                room.upRoom.SetMaterial(5, 11, 9);
                value = false;
                for (int a = 0; a < level.materials.size(); a++) {
                    Material mat = level.materials.elementAt(a);
                    if (mat instanceof HotWires) {
                        HotWires hw = (HotWires) mat;
                        if (hw.wall == 0) {
                            hw.value = true;
                        }
                    }
                }
                for (int a = 0; a < 5; a++) {
                    Graphix gr = room.graphix.elementAt(a);
                    gr.filenames = new String[]{"blueHorizontal.gif"};
                    gr.GenerateIcons();
                }
                for (int a = 0; a < 5; a++) {
                    Graphix gr = room.graphix.elementAt(a + 5);
                    gr.filenames = new String[]{"blueVertical.gif"};
                    gr.GenerateIcons();
                }
                animationState++;
                break;
            case 7:
                room.SetMaterial(4, 0, 9);
                room.upRoom.SetMaterial(4, 11, 9);
                animationState++;
                break;
            case 8:
                room.SetMaterial(3, 0, 9);
                room.upRoom.SetMaterial(3, 11, 9);
                animationState++;
                break;
            case 9:
                room.SetMaterial(2, 0, 9);
                room.upRoom.SetMaterial(2, 11, 9);
                animationState = 0;
                break;
        }
    }

}
