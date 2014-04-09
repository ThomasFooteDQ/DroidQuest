package com.droidquest.avatars;

import com.droidquest.Room;
import com.droidquest.devices.Device;
import com.droidquest.devices.GenericChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.GenericRobot;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class LabCursor extends Item {
    public boolean hot;



    public LabCursor(int X, int Y, Room r) {
        x = X;
        y = Y;
        hot = false;
        room = r;
        width = 28;
        height = 32;
        GenerateIcons();
    }

    public void GenerateIcons() {
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to LabCursor Image");
            return;
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to LabCursor Image");
            return;
        }
        g.setColor(new Color(255, 128, 0));
        g.fillRect(0, 0, width, height);
        if (hot) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }
    }

    public boolean CanBePickedUp(Item i) {
        return !(i instanceof GenericRobot);
    }

    public boolean KeyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_L) {
            if (carrying != null) {
                if (carrying instanceof SmallChip) {
                    FileDialog fd = new FileDialog(level.roomdisplay.dq, "Load Chip", FileDialog.LOAD);
                    fd.setDirectory("chips");
                    fd.show();
                    System.out.println("Dialog returned with "
                            + fd.getDirectory()
                            + fd.getFile());
                    if (fd.getFile() != null) {
                        ((SmallChip) carrying).Empty();
                        ((SmallChip) carrying).LoadChip(fd.getDirectory() + fd.getFile());
                    }
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_H) {
            hot = !hot;
            if (hot) {
                currentIcon = icons[1].getImage();
            }
            else {
                currentIcon = icons[0].getImage();
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (level.solderingPen == null) {
                return false;
            }
            if (carrying != null) {
                if (carrying instanceof SmallChip) {
                    FileDialog fd = new FileDialog(level.roomdisplay.dq, "Save Chip", FileDialog.SAVE);
                    fd.setDirectory("chips");
                    fd.show();
                    System.out.println("Dialog returned with "
                            + fd.getDirectory()
                            + fd.getFile());
                    if (fd.getFile() != null) {
                        ((SmallChip) carrying).SaveChip(fd.getDirectory() + fd.getFile());
                    }
                    return false;
                }
            }
            if (carrying != null) {
                Drops();
            }
            level.solderingPen.x = x;
            level.solderingPen.y = y;
            level.solderingPen.room = room;
            room = null;
            if (level.currentViewer == level.player) {
                level.currentViewer = level.solderingPen;
            }
            level.player = level.solderingPen;
            if (level.remote != null) {
                if (level.remote.carriedBy != null) {
                    level.remote.carriedBy = level.player;
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_R) {
            if (level.remote == null) {
                return false;
            }
            if (level.remote.carriedBy == null) { // Summon Remote
                level.remote.x = 28;
                level.remote.y = -20;
                level.remote.carriedBy = level.player;
                level.remote.room = level.player.room;
                level.electricity = true;
            }
            else { // Hide Remote
                level.remote.carriedBy = null;
                level.remote.room = null;
                level.electricity = false;
            }
//	     if (carrying != null)
//	       Drops();
//	     level.remote.x = x;
//	     level.remote.y = y;
//	     level.remote.room = room;
//	     room = null;
//	     if (level.currentViewer == level.player)
//	       level.currentViewer=level.remote;
//	     level.player = level.remote;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            if (level.paintbrush == null) {
                return false;
            }
            if (carrying != null) {
                Drops();
            }
            level.paintbrush.x = (x / 28) * 28;
            level.paintbrush.y = (y / 32) * 32;
            level.paintbrush.room = room;
            room = null;
            if (level.currentViewer == level.player) {
                level.currentViewer = level.paintbrush;
            }
            level.player = level.paintbrush;
        }
        else if (e.getKeyCode() == KeyEvent.VK_T) {
            if (level.toolbox == null) {
                return false;
            }
            if (level.toolbox.room != room) {
                // Summon Toolbox
                if (carrying != null) {
                    return false;
                }
                if (((ToolBox) level.toolbox).open) {
                    ((ToolBox) level.toolbox).Toggle();
                }
                level.toolbox.room = room;
                level.toolbox.x = x + 28;
                level.toolbox.y = y + 6;
                PicksUp(level.toolbox);
            }
            else {
                ((ToolBox) level.toolbox).Toggle();
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_SLASH) {
            if (carrying != null) {
                if (carrying instanceof GenericChip) {
                    ((GenericChip) carrying).ShowText(true);
                    return false;
                }
            }
            if (level.helpCam == null) {
                return false;
            }
            level.player = level.helpCam;
            level.currentViewer = level.helpCam;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (carriedBy == null) {
                MoveRight(e.isControlDown());
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (carriedBy == null) {
                MoveLeft(e.isControlDown());
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (carriedBy == null) {
                MoveUp(e.isControlDown());
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (carriedBy == null) {
                MoveDown(e.isControlDown());
            }
            repeating = 0;
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (carrying != null) {
                Drops();
            }
            else {
                Item item = level.FindNearestItem(level.gameCursor);
                if (item != null) {
                    if (item.CanBePickedUp(level.gameCursor)) {
                        PicksUp(item);
                    }
                }
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
            if (carrying != null) {
                if (carrying.isDevice()) {
                    ((Device) carrying).rotate(1);
                }
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
            if (carrying != null) {
                if (carrying.isDevice()) {
                    ((Device) carrying).rotate(-1);
                }
            }
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            Item item = level.FindNearestItem(this);
            if (item != null) {
                if (item.InternalRoom != null) {
                    if (Overlaps(item)) {
                        if (!item.OverWall()) {
                            int newX = 280; // 10 * 28
                            int newY = 176; // 5.5 * 32
                            x = newX;
                            y = newY;
                            SetRoom(item.InternalRoom);
                        }
                    }
                }
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            if (room != null && room.portalItem != null) {
                Dimension d = room.portalItem.GetXY();
                int newX = d.width
                        + room.portalItem.getWidth() / 2
                        - width / 2;
                int newY = d.height
                        + room.portalItem.getHeight() / 4 * 2
                        - height / 2;
                x = newX;
                y = newY;
                SetRoom(room.portalItem.room);
                level.currentViewer = level.player;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_F) {
            if (carrying != null) {
                if (carrying instanceof Device) {
                    ((Device) carrying).flip();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_M) {
            Runtime runtime = Runtime.getRuntime();
            long freemem = runtime.freeMemory();
            long totalmem = runtime.totalMemory();
            System.out.println("Total Memory = " + totalmem
                    + ", (" + totalmem / 1024 + "K), ("
                    + totalmem / 1024 / 1024 + "M)");
            System.out.println("Free Memory = " + freemem
                    + ", (" + freemem / 1024 + "K), ("
                    + freemem / 1024 / 1024 + "M)");
        }

        return false;
    }

    public boolean KeyDown(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            repeating++;
            if (repeating > 10) {
                if (carriedBy == null) {
                    MoveRight(e.isControlDown());
                }
                return true;
            }
            return false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            repeating++;
            if (repeating > 10) {
                if (carriedBy == null) {
                    MoveLeft(e.isControlDown());
                }
                return true;
            }
            return false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            repeating++;
            if (repeating > 10) {
                if (carriedBy == null) {
                    MoveUp(e.isControlDown());
                }
                return true;
            }
            return false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            repeating++;
            if (repeating > 10) {
                if (carriedBy == null) {
                    MoveDown(e.isControlDown());
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void MoveUp(boolean nudge) {
        Item item = level.FindNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.UpEnterOverlap(this)) {
                    int newX = 280; // 10 * 28
                    int newY = 320; // 10 * 32
                    x = newX;
                    y = newY;
                    SetRoom(item.InternalRoom);
                }
            }
        }
        super.MoveUp(nudge);
    }

    public void MoveDown(boolean nudge) {
        Item item = level.FindNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.DownEnterOverlap(this)) {
                    int newX = 280; // 10 * 28
                    int newY = 0; //  0 * 32
                    x = newX;
                    y = newY;
                    SetRoom(item.InternalRoom);
                }
            }
        }
        super.MoveDown(nudge);
    }

    public void MoveLeft(boolean nudge) {
        Item item = level.FindNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.LeftEnterOverlap(this)) {
                    int newX = 532; // 19 * 28
                    int newY = 176; // 5.5 * 32
                    x = newX;
                    y = newY;
                    SetRoom(item.InternalRoom);
                }
            }
        }
        super.MoveLeft(nudge);
    }

    public void MoveRight(boolean nudge) {
        Item item = level.FindNearestItem(this);
        if (item != null) {
            if (item.InternalRoom != null) {
                if (item.RightEnterOverlap(this)) {
                    int newX = 0; // 0 * 28
                    int newY = 176; // 5.5 * 32
                    x = newX;
                    y = newY;
                    SetRoom(item.InternalRoom);
                }
            }
        }
        super.MoveRight(nudge);
    }

}