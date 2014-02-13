package com.droidquest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.droidquest.controller.ClockTickHandler;
import com.droidquest.event.LevelChangeEvent;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;
import com.droidquest.operation.api.OperationFactory;
import com.droidquest.view.api.sound.SoundPlayer;
import com.google.common.eventbus.EventBus;

/**
 * Singleton context object containing game-wide variables and resources.
 */
public class GameImpl implements Game {
    private Level currentLevel;
    private boolean suspended;
    private ClockTickHandler clockTickHandler;
    private SoundPlayer soundPlayer;
    private RoomDisplay view;
    private OperationFactory operationFactory;
    private EventBus eventBus = new EventBus();

    public GameImpl() {
        clockTickHandler = new ClockTickHandler(this);
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void setCurrentLevel(Level level) {
        Level oldLevel = currentLevel;
        this.currentLevel = level;

        getEventBus().post(new LevelChangeEvent(this, oldLevel, level));
    }

    @Override
    public void loadLevel(String filename)
    {
        suspend();

        try {
            getCurrentLevel().Empty();
            setCurrentLevel(new Level(this));
            Item.level = getCurrentLevel();
            Room.level = getCurrentLevel();
            Material.level = getCurrentLevel();

            // Add flags for loading Object inventories or running Init()
            try
            {
                FileInputStream in = new FileInputStream(filename);
                ObjectInputStream s = new ObjectInputStream(in);
                getCurrentLevel().readObject(s);
                s.close();
                in.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File Not Found");
                return;
            }
            catch (IOException e)
            {
                System.out.println("IO Exception");
                System.out.println(e.getMessage());
                e.printStackTrace();
                return;
            }

            if (getCurrentLevel().remote != null)
            {
                if (getCurrentLevel().electricity)
                {
                    getCurrentLevel().remote.x = 28;
                    getCurrentLevel().remote.y = -20;
                    getCurrentLevel().remote.carriedBy = getCurrentLevel().getPlayer();
                    getCurrentLevel().remote.room = getCurrentLevel().getPlayer().room;
                }
                else // Electricity is off
                {
                    getCurrentLevel().remote.carriedBy = null;
                    getCurrentLevel().remote.room = null;
                }
            }
        } finally {
            restart();
        }
    }

    @Override
    public void saveLevel()
    {
        String temp = getCurrentLevel().getClass().toString();
        System.out.println("Class name is " + temp);
        String[] path = temp.split("\\.");
        for (int a=0; a< path.length; a++)
            System.out.println(a + " = " + path[a]);
        //	String filename = temp.substring(6);
        String filename = path[path.length-1];
        saveLevel(filename + ".lvl");
    }

    @Override
    public void saveLevel(String filename)
    {
        System.out.println("Saving level " + filename);
        try
        {
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream s = new ObjectOutputStream(out);
            getCurrentLevel().writeObject(s);
            s.flush();
            s.close();
            out.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        catch (IOException e)
        {
            System.out.println("IO Exception");
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return true if the game action and animations are currently suspended.
     */
    @Override
    public boolean isSuspended() {
        return suspended;
    }

    /**
     * Set true to temporarily suspend the game.
     * @param suspended true if the game should be suspended, false to restart the world
     */
    private void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    @Override
    public void suspend() {
        setSuspended(true);
    }

    @Override
    public void restart() {
        setSuspended(false);
    }

    @Override
    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

    @Override
    public void setSoundPlayer(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }

    @Override
    public ClockTickHandler getClockTickHandler() {
        return clockTickHandler;
    }

    @Override
    public void setView(RoomDisplay view) {
        this.view = view;
    }

    @Override
    public RoomDisplay getView() {
        return view;
    }

    @Override
    public OperationFactory getOperationFactory() {
        return operationFactory;
    }

    @Override
    public void setOperationFactory(OperationFactory operationFactory) {
        this.operationFactory = operationFactory;
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }
}
