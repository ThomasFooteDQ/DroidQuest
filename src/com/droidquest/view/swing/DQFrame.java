package com.droidquest.view.swing;

import javax.swing.*;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.droidquest.Game;
import com.droidquest.RoomDisplay;
import com.droidquest.levels.Level;
import com.droidquest.levels.MainMenu;

public class DQFrame extends JFrame implements ActionListener
{
    public static final String SAVE_LEVEL = "Save Level";
    public static final String MAIN_MENU = "Main Menu";
    public static final String SOUND = "Sound";
    public static final String EXIT = "Exit";
    private final Game game;

    public DQFrame(Game game)
    {
        // Constructor
        super("DroidQuest");

        this.game = game;

        setSize(560+8,384+27+24);
        GraphicsConfiguration gc = getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        setLocation(bounds.x + (bounds.width - 568) / 2,
                bounds.y + (bounds.height - 435) / 2);

        addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            { setVisible(false); dispose(); System.exit(0); }
        });

        setIconImage(new ImageIcon("images/helper0.gif").getImage());

        Container contentPane = getContentPane();
        final RoomDisplay view = new RoomDisplay(game);
        game.setView(view);

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                ((SwingView) getGame().getView()).getComponent().requestFocus();
            }
        });

        contentPane.add(view);
        view.setLocation(0, 0);

        JMenuBar menuBar;
        JMenu fileMenu;
        JMenuItem menuItemSave;
        JMenuItem menuItemMain;
        JCheckBoxMenuItem menuItemSound;
        JMenuItem menuItemExit;

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        menuItemSave = new JMenuItem(SAVE_LEVEL,KeyEvent.VK_S);
        menuItemMain = new JMenuItem(MAIN_MENU, KeyEvent.VK_M);
        menuItemSound = new JCheckBoxMenuItem(SOUND, true);
        menuItemExit = new JMenuItem(EXIT, KeyEvent.VK_X);
        fileMenu.add(menuItemSave);
        fileMenu.add(menuItemMain);
        fileMenu.add(menuItemSound);
        fileMenu.add(menuItemExit);

        menuItemSave.addActionListener(this);
        menuItemMain.addActionListener(this);
        menuItemSound.addActionListener(this);
        menuItemExit.addActionListener(this);

        try
        {
            System.setErr(System.out);
        }
        catch (SecurityException e) {}
    }

    protected Game getGame() {
        return game;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(SAVE_LEVEL))
        {
            FileDialog fd = new FileDialog(this,"Save Level", FileDialog.SAVE);
            fd.setDirectory("ROlevels");
            fd.setVisible(true);
            System.out.println("Dialog returned with "
                    + fd.getDirectory()
                    + fd.getFile());
            if (fd.getFile() != null)
                game.saveLevel(fd.getDirectory() + fd.getFile());
        }

        if (e.getActionCommand().equals(MAIN_MENU))
        {
            int n = JOptionPane.showConfirmDialog(this,"Do you want to quit this level?",
                    "return to Main Menu", JOptionPane.YES_NO_OPTION);
            if (n==0)
            {
                game.getCurrentLevel().Empty();
                game.setCurrentLevel(new MainMenu(game));
                game.getCurrentLevel().Init();
            }
        }

        if (e.getActionCommand().equals(SOUND))
        {
            game.getSoundPlayer().setAudioEnabled(((JCheckBoxMenuItem) e.getSource()).getState());
        }

        if (e.getActionCommand().equals(EXIT))
        { setVisible(false); dispose(); System.exit(0); }

    }

    public Level getLevel() {
        return game.getCurrentLevel();
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//Updating Tutorial levels to 2.0
//
//ROTUT1 : Robot Anatomy
//ROTUT2 : Robot Wiring
//ROTUT3 : Sensors
//ROTUT4 : The Toolkit
//ROTUT5 : Robot Circuits
//ROTUT6 : Robot Teamwork
//ROTUT7 : Chip Design
//

//Text has the following embedded commands:
//"{BIG} "         : Switch to large font
//"{SML} "         : Switch to small font
//"{rrr,ggg,bbb} " : Switch color. rrr, ggg, bbb == 000-255
//"{BSP} "         : BackSpace, good for switching between BIG and SML
//
//Small characters are all 12 pixels wide, and Large charaacters are all 27 pixels wide.
//
//Undo Support
//Undo the folowing actions
//Summon Device
//Destroy Device
//Move Device
//Make Wire
//Delete Wire
//
//class GameAction
//{
//static int TYPE_BLANK = 0;
//static int TYPE_SUMMON_DEVICE = 1;
//static int TYPE_DESTROY_DEVICE = 2;
//static int TYPE_MOVE_DEVICE = 3;
//static int TYPE_MAKE_WIRE = 4;
//static int TYPE_DELETE_WIRE = 5;
//
//int type;
//Device device;
//int x;
//int y;
//Room room;
//Wire wire;
//
//public Action (int t, Device dev)
//{
//  type = t;
//  device = dev;
//  x=dev.x;
//  y=dev.y;
//  room = dev.room;
//}
//
//public Action (int t, Wire w)
//{
//  type = t;
//  wire = w;
//  room = w.fromPort.myDevice.room;
//}
//
//public void Reverse()
//{
//  switch (type)
//  {
//    case 1: // Destroy Device
//            // remove all wires
//            dev.Erase()
//            dev.level.items.removeElement(dev);
//            break;
//    case 2: // Re-summon Device
//
//            break;
//    case 3: // Move Device
//
//            break;
//    case 4: // Delete Wire
//
//            break;
//    case 5: // Remake Wire
//
//            break;
//  }
//  type=TYPE_BLANK;
//  dev=null;
//  x=0; y=0;
//  room=null;
//  wire=null;
//}
//
//}
//
//Room:MaterialsArray[][] references to materials, instead of indexes
//Initialize the Room Arrays when loading from inventories
//Can't board the subway train while carrying things...!
//
//
//Hot cursor makes input port true, but it doesn't show graphically.
//Add some way to show how much of a charge a Crystal has.
//Add {CENTER}, {LEFT}, & {RIGHT} to TextBoxes
//Give Rooms an array of Materials that's used instead of the RoomArray matrix.
//Make burners & tester put chips on even pixels
//
//Populate Levels 2-5
//
//Oscillator
//Gates
//Bus
//Clock Chip
//Delay
//One Shot Chip
//RS
//6 bit Counter
//Full Adder
//Count-to-N
//Monomer
//Wall hugger
//Stereo Recorder
//
//Game ideas:
//1) Classic Robot Odyssey
//2) Return to Robotropolis
//3) Adventure (Classic Atari game)
//4) Adventure Odyssey (Use robots to solve problems in the Adventure world)
//
//
//
//JAR file created with this command:
//% jar cmf0 manifest.txt DQ.jar *.class
//
//ZIP file created with these files:
//DQ.jar
//DQlogo.gif
//Readme.txt
//
//


