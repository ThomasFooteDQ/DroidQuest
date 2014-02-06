package com.droidquest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;

/**
 * Singleton context object containing game-wide variables and resources.
 */
public class Game {
    private static Game context = new Game();

    private Level currentLevel;
    private boolean suspended;
    private boolean soundEnabled = true;

    public static Game getInstance() {
        return context;
    }

    /**
     * Can't be instantiated.  @see getInstance();
     */
    private Game() {}

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void loadLevel(String filename)
    {
        suspend();

        try {
            getCurrentLevel().Empty();
            setCurrentLevel(new Level());
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
                    getCurrentLevel().remote.carriedBy = getCurrentLevel().player;
                    getCurrentLevel().remote.room = getCurrentLevel().player.room;
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

    public void suspend() {
        setSuspended(true);
    }

    public void restart() {
        setSuspended(false);
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }
}
