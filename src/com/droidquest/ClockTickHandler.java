package com.droidquest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.droidquest.avatars.LabCursor;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.Spark;
import com.droidquest.devices.Device;
import com.droidquest.items.Item;
import com.droidquest.levels.Level;
import com.droidquest.materials.Material;
import com.droidquest.view.View;

/**
 * Main event loop handler.
 */
public class ClockTickHandler {
    private final Game game;

    public ClockTickHandler(Game game) {
        this.game = game;
    }

    /**
     * Main event handler.  Invoked every "clock tick" to animate the models and repaint the view.  By default, a clock tick
     * is 128ms.
     */
    public void handleClockTick() {
        if (game.isSuspended()) {
            return;
        }

        if (getLevel().portal != null)
        {
            String filename = getLevel().portal.levelName;
            boolean bringStuff = getLevel().portal.bringStuff;
            boolean initLevel = getLevel().portal.initLevel;
            int x = getLevel().player.x + getLevel().player.getWidth()/2;
            int y = getLevel().player.y + getLevel().player.getHeight()/2;
            getLevel().PlaySound(getLevel().currentViewer.room, Level.TELEPORTSOUND);
            boolean tempsound = game.isSoundEnabled();
            game.setSoundEnabled(false);
            //		     for (int a=0; a<560; a+=2)
            //		       {
            //			  int c = 255*a/560;
            //			  g.setColor(new Color(c,255-c,0));
            //			  g.drawRect(x-a-1,y-a-1,a*2+2,a*2+2);
            //			  g.setColor(Color.black);
            //			  g.drawRect(x-a,y-a,a*2,a*2);
            //			  long timeout = System.currentTimeMillis() + 1;
            //			  do {} while (System.currentTimeMillis() < timeout);
            //		       }
            //		     g.setColor(Color.black);
            //		     for (int a=0; a<560; a++)
            //		       {
            //			  g.drawRect(x-a,y-a,a*2,a*2);
            //			  long timeout = System.currentTimeMillis() + 1;
            //			  do {} while (System.currentTimeMillis() < timeout);
            //		       }
            if (bringStuff)
            {
                System.out.println("Saving carried items.");
                getLevel().WriteInventory();
            }

            FileInputStream f;
            try
            {
                f = new FileInputStream(filename);
                try {f.close();} catch (IOException ie){}
            }
            catch(FileNotFoundException ie)
            {
                // filename does not exist
                String classname = "com.droidquest.levels."+filename.substring(0,filename.length()-4);
                Constructor<? extends Level> constructor = null;
                try
                {
                    Class<? extends Level> newlevel = (Class<? extends Level>) Class.forName(classname);
                    constructor = newlevel.getConstructor(Game.class);
                    constructor.setAccessible(true);
                }
                catch (ClassNotFoundException ce) {
                    ce.printStackTrace();
                }
                catch (NoSuchMethodException ne) {
                    ne.printStackTrace();
                };
                try
                {
                    setLevel(constructor.newInstance(game));
                    game.saveLevel();
                }
                catch(InstantiationException ie2)
                {
                    System.out.println("Instantiation");
                    System.exit(0);
                }
                catch(IllegalAccessException ie2)
                {
                    System.out.println("Illegal Access");
                    System.exit(0);
                }
                catch(IllegalArgumentException ie2)
                {
                    System.out.println("Illegal Argument");
                    System.exit(0);
                }
                catch(InvocationTargetException ie2)
                {
                    System.out.println("Invocation Target");
                    Throwable t = ie2.getTargetException();
                    ie2.printStackTrace();
                    System.out.println(t.getClass());
                    System.exit(0);
                };
            }
            //		       {
            //		         look for a class that matches the name "filename" without the ".lvl"
            //		         if found, create it and save it, then load the file.
            //		       }

            System.out.println("Loading level " + filename);
            game.loadLevel(filename);
            if (initLevel)
            {
                System.out.println("Initializing Level");
                getLevel().Init();
            }
            if (bringStuff)
            {
                System.out.println("Loading carried items.");
                getLevel().LoadInventory();
            }
            x = getLevel().player.x + getLevel().player.getWidth()/2;
            y = getLevel().player.y + getLevel().player.getHeight()/2;

            //		     for (int a=560; a>0; a-=2)
            //		       {
            //			  int c = 255*a/560;
            //			  g.setColor(new Color(255-c,c,0));
            //			  g.drawRect(x-a-1,y-a-1,a*2+2,a*2+2);
            //			  g.setColor(Color.black);
            //			  g.drawRect(x-a,y-a,a*2,a*2);
            //			  long timeout = System.currentTimeMillis() + 1;
            //			  do {} while (System.currentTimeMillis() < timeout);
            //		       }
            //		     g.setColor(Color.black);
            //		     for (int a=560; a>0; a--)
            //		       {
            //			  g.drawRect(x-a,y-a,a*2,a*2);
            //			  long timeout = System.currentTimeMillis() + 1;
            //			  do {} while (System.currentTimeMillis() < timeout);
            //		       }
            game.setSoundEnabled(tempsound);
            getLevel().PlaySound(getLevel().currentViewer.room, Level.TRANSPORTSOUND);
        }
        electricity();
        for (int a = 0; a < getLevel().items.size(); a++)
        {
            Item item = (Item) getLevel().items.elementAt(a);
            item.Animate();
            if (item.room == getLevel().currentViewer.room)
                item.Decorate();
        }
        for (int a=0; a< getLevel().materials.size(); a++)
            ((Material) getLevel().materials.elementAt(a)).Animate();
        for (int a=0; a< getLevel().rooms.size(); a++)
        {
            Room room = (Room) getLevel().rooms.elementAt(a);
            for (int b=0; b<room.graphix.size(); b++)
            {
                Graphix graphix = (Graphix) room.graphix.elementAt(b);
                graphix.Animate();
            }
        }

        renderView();
        for (int a = 0; a< getLevel().sparks.size(); a++)
        {
            Spark spark = (Spark) getLevel().sparks.elementAt(a);
            spark.Age();
            if (spark.age>6)
            {
                getLevel().sparks.removeElement(spark);
                a--;
            }
        }
    }

    private void electricity()
    {
        if (getLevel().electricity == false)
            return;

        for (int a=0; a<getLevel().items.size(); a++)
        {
            Item item = (Item) getLevel().items.elementAt(a);
            if (item.isDevice())
            {
                Device device = (Device) item;
                for (int b=0; b<device.ports.length; b++)
                {
                    Wire wire = device.ports[b].myWire;
                    if (wire != null)
                    {
                        if (wire.inPort != null && wire.outPort!=null)
                        {
                            wire.inPort.value = wire.outPort.value;
                        }
                    }
                    else if (device.ports[b].type == Port.TYPE_INPUT)
                    {
                        device.ports[b].value = false;
                        if (getLevel().gameCursor instanceof LabCursor)
                            if (device.room == getLevel().gameCursor.room)
                                if (device.ports[b].x+device.x >= getLevel().gameCursor.x
                                        && device.ports[b].x+device.x <= getLevel().gameCursor.x + getLevel().gameCursor.getWidth()
                                        && device.ports[b].y+device.y >= getLevel().gameCursor.y
                                        && device.ports[b].y+device.y <= getLevel().gameCursor.y + getLevel().gameCursor.getHeight())
                                    if (((LabCursor)getLevel().gameCursor).hot)
                                        device.ports[b].value = true;
                    }
                }
            }
        }

        for (int a=0; a<getLevel().items.size(); a++)
        {
            Item item = (Item) getLevel().items.elementAt(a);
            if (item.isDevice())
            {
                Device device = (Device) item;
                device.Function();
            }
        }


        boolean nodeChanged;
        int counter=0;
        do
        {
            nodeChanged=false;
            for (int a=0; a<getLevel().items.size(); a++)
            {
                Item item = (Item) getLevel().items.elementAt(a);
                if (item.isDevice())
                {
                    Device device = (Device) item;
                    for (int b=0; b<device.ports.length; b++)
                    {
                        Wire wire = device.ports[b].myWire;
                        if (wire != null)
                        {
                            if (wire.inPort != null && wire.outPort!=null)
                            {
                                wire.inPort.value = wire.outPort.value;
                            }
                        }
                        else if (device.ports[b].type == Port.TYPE_INPUT)
                        {
                            device.ports[b].value = false;
                            if (getLevel().gameCursor instanceof LabCursor)
                                if (device.room == getLevel().gameCursor.room)
                                    if (device.ports[b].x+device.x >= getLevel().gameCursor.x
                                            && device.ports[b].x+device.x <= getLevel().gameCursor.x + getLevel().gameCursor.getWidth()
                                            && device.ports[b].y+device.y >= getLevel().gameCursor.y
                                            && device.ports[b].y+device.y <= getLevel().gameCursor.y + getLevel().gameCursor.getHeight())
                                        if (((LabCursor)getLevel().gameCursor).hot)
                                            device.ports[b].value = true;
                        }
                    }
                    if (device.isNode())
                    {
                        if (device.Function())
                            nodeChanged=true;

                    }
                }
            }
            counter++;
        }
        while (nodeChanged && counter<1000);

    }

    public void renderView() {
        if (getView() != null) {
            getView().render();
        }
    }

    private View getView() {
        return game.getView();
    }

    private Level getLevel() {
        return game.getCurrentLevel();
    }

    private void setLevel(Level level) {
        game.setCurrentLevel(level);
    }
}
