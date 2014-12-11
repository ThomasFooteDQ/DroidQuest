package com.droidquest.avatars;

import com.droidquest.devices.Device;
import com.droidquest.devices.GenericChip;
import com.droidquest.devices.SmallChip;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Parent class to handle common Player functions.
 */
public class Player extends Item implements Avatar {

    private int keyRepeatRate = 5;
    private int shortcut_modifier = Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask();

    protected boolean handleSaveSmallChip() {
        return false;
    }

    public void handleRemote() {
        if (level.remote != null) {
            if (level.remote.carriedBy != null) {
                level.remote.carriedBy = level.player;
            }
        }
    }

    public boolean handleSolderPen() {
        if (level.solderingPen == null) {
            return false;
        }
        if (carrying != null) {
            if (handleSaveSmallChip()) {
                // Actually a save small chip command,
                // skip solder pen
                return true;
            }
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

        handleRemote();

        level.roomdisplay.dq.selectSolderpen();

        return true;
    }

    public boolean handleRadio() {
        if (level.remote == null) {
            return false;
        }
        if (level.remote.carriedBy == null) { // Summon Remote
            level.remote.x = 28;
            level.remote.y = -20;
            level.remote.carriedBy = level.player;
            level.remote.room = level.player.room;
            level.electricity = true;

            level.roomdisplay.dq.setRadioSelected(true);
        }
        else { // Hide Remote
            level.remote.carriedBy = null;
            level.remote.room = null;
            level.electricity = false;

            level.roomdisplay.dq.setRadioSelected(false);
        }
        return true;
    }

    public boolean handleHelp() {
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
        return true;
    }

    public boolean handleToolbox() {
        if (level.toolbox == null) {
            if (carrying != null) {
                Drops();
            }
            level.toolbox = new ToolBox(x, y + 8, room);
            level.items.addElement(level.toolbox);
            ((ToolBox) level.toolbox).Toggle();
            PicksUp(level.toolbox);
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
        return true;
    }


    public boolean handleLoadSmallChip() {
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
                return true;
            }
        }
        return false;
    }

    protected boolean handleTrain() {
        return false;
    }

    @Override
    public void PicksUp(Item item) {
        super.PicksUp(item);
        if (carrying instanceof SmallChip) {
            level.roomdisplay.dq.setLoadChipEnabled(true);
        }
        else {
            level.roomdisplay.dq.setLoadChipEnabled(false);

            if (carrying.isDevice()) {
                level.roomdisplay.dq.setRotateEnabled(true);
                level.roomdisplay.dq.setFlipDeviceEnabled(true);
            }
            else {
                level.roomdisplay.dq.setRotateEnabled(false);
                level.roomdisplay.dq.setFlipDeviceEnabled(false);
            }
        }
    }

    @Override
    public void Drops() {
        super.Drops();
        level.roomdisplay.dq.setRotateEnabled(false);
        level.roomdisplay.dq.setLoadChipEnabled(false);
        level.roomdisplay.dq.setFlipDeviceEnabled(false);
    }

    public boolean handlePickupDrop() {
        if (handleTrain()) {
            return false;
        }
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
        setOutline(false);
        return true;
    }

    // Default implementation doesn't do anything,
    // needed by GameCursor to set outline
    protected void setOutline(boolean outline) {
    }

    public boolean handleRotateDevice(int direction) {
        if (carrying != null) {
            if (carrying.isDevice()) {
                ((Device) carrying).rotate(direction);
            }
        }
        return true;
    }

    public boolean handleEnterRoom() {
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
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean handleExitRoom() {
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
            return true;
        }
        return false;
    }

    public boolean handleFlipDevice() {
        if (carrying != null) {
            if (carrying instanceof Device) {
                ((Device) carrying).flip();
            }
        }
        return true;
    }

    protected boolean handleMemory() {
        Runtime runtime = Runtime.getRuntime();
        long freemem = runtime.freeMemory();
        long totalmem = runtime.totalMemory();
        System.out.println("Total Memory = " + totalmem
                + ", (" + totalmem / 1024 + "K), ("
                + totalmem / 1024 / 1024 + "M)");
        System.out.println("Free Memory = " + freemem
                + ", (" + freemem / 1024 + "K), ("
                + freemem / 1024 / 1024 + "M)");
        return true;
    }

    protected boolean isCheatMode() {
        return false;
    }

    public boolean handleMoveDown(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null) {
                SetRoom(room.downRoom);
            }
        }
        if (carriedBy == null) {
            moveDown(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveUp(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null) {
                SetRoom(room.upRoom);
            }
        }
        if (carriedBy == null) {
            moveUp(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveLeft(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null) {
                SetRoom(room.leftRoom);
            }
        }
        if (carriedBy == null) {
            moveLeft(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleMoveRight(boolean isShiftDown, boolean isControlDown) {
        if (isCheatMode()) {
            if (isShiftDown && room != null) {
                SetRoom(room.rightRoom);
            }
        }
        if (carriedBy == null) {
            moveRight(isControlDown);
        }
        repeating = 0;
        return true;
    }

    public boolean handleHotCursor() {
        return false;
    }

    public boolean handlePaintbrush() {
        return false;
    }

    // Default assume that we are already a game cursor
    public boolean handleGameCursor() {
        return false;
    }

    public boolean KeyUp(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_L && handleLoadSmallChip()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_C && handleGameCursor()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_H && handleHotCursor()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S && handleSolderPen()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_R && handleRadio()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_P && handlePaintbrush()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_T && handleToolbox()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SLASH && handleHelp()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && handleMoveRight(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && handleMoveLeft(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && handleMoveUp(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && handleMoveDown(e.isShiftDown(), (e.getModifiers() & shortcut_modifier) > 0)) {
            return true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE && handlePickupDrop()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET && handleRotateDevice(1)) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET && handleRotateDevice(-1)) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_E && handleEnterRoom()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_X && handleExitRoom()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_F && handleFlipDevice()) {
            return false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_M && handleMemory()) {
            return false;
        }

        return false;
    }

    protected boolean handleRepeatRight(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveRight(isControlDown);
            }
            return true;
        }
        return false;

    }

    protected boolean handleRepeatLeft(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveLeft(isControlDown);
            }
            return true;
        }
        return false;
    }

    protected boolean handleRepeatUp(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveUp(isControlDown);
            }
            return true;
        }
        return false;
    }

    protected boolean handleRepeatDown(boolean isControlDown) {
        repeating++;
        if (repeating > getKeyRepeatRate()) {
            if (carriedBy == null) {
                moveDown(isControlDown);
            }
            return true;
        }
        return false;

    }

    protected boolean handleRepeatSpace() {
        if (level.player == level.gameCursor) {
            setOutline(true);
            return true;
        }
        return false;
    }

    public boolean KeyDown(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (handleRepeatRight((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (handleRepeatLeft((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (handleRepeatUp((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (handleRepeatDown((e.getModifiers() & shortcut_modifier) > 0)) {
                return true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (handleRepeatSpace()) {
                return false;
            }
        }
        return false;
    }


    public int getKeyRepeatRate() {
        return keyRepeatRate;
    }

    public void setKeyRepeatRate(int keyRepeatRate) {
        this.keyRepeatRate = keyRepeatRate;
    }

    @Override
    public void moveRight(boolean nudge) {
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
        super.moveRight(nudge);
    }

    @Override
    public void moveLeft(boolean nudge) {
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
        super.moveLeft(nudge);
    }

    @Override
    public void moveDown(boolean nudge) {
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
        super.moveDown(nudge);
    }

    @Override
    public void moveUp(boolean nudge) {
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
        super.moveUp(nudge);
    }

}
