package com.droidquest;

//This is the source code for DroidQuest 2.7. Copyright 2003 by Thomas Foote.

import com.droidquest.avatars.Avatar;
import com.droidquest.avatars.LabCursor;
import com.droidquest.levels.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class DQ extends JFrame implements ActionListener {
    private RoomDisplay myRoom;

    private JCheckBoxMenuItem menuToggleHot = null;
    private JMenuItem menuItemCursor = null;
    private JMenuItem menuItemSolderpen = null;
    private JMenuItem menuItemPaintbrush = null;
    private JCheckBoxMenuItem menuItemRadio = null;
    private JMenuItem menuItemToolbox = null;

    private JMenuItem menuRotateRight = null;
    private JMenuItem menuRotateLeft = null;
    private JMenuItem menuLoadChip = null;

    private JMenuItem menuFlipDevice = null;

    private DQ() {
        // Constructor
        super("DroidQuest");
        setSize(560 + 8, 384 + 27 + 24);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });

        setIconImage(new ImageIcon("images/helper0.gif").getImage());

        Container contentPane = getContentPane();
        myRoom = new RoomDisplay(this);

        addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                myRoom.requestFocus();
            }
        });

        contentPane.add(myRoom);
        myRoom.setLocation(0, 0);

        JMenuBar menuBar;
        JMenu fileMenu;
        JMenu avatarMenu;
        JMenu controlMenu;
        JMenu helpMenu;
        JMenuItem menuItemSave;
        JMenuItem menuItemMain;
        JCheckBoxMenuItem menuItemSound;
        JMenuItem menuItemExit;

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        menuItemSave = new JMenuItem("Save Level", KeyEvent.VK_S);
        menuItemMain = new JMenuItem("Main Menu", KeyEvent.VK_M);
        menuItemSound = new JCheckBoxMenuItem("Sound", true);
        menuItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        fileMenu.add(menuItemSave);
        fileMenu.add(menuItemMain);
        fileMenu.add(menuItemSound);
        fileMenu.add(menuItemExit);

        menuItemSave.addActionListener(this);
        menuItemMain.addActionListener(this);
        menuItemSound.addActionListener(this);
        menuItemExit.addActionListener(this);

        avatarMenu = new JMenu("Avatar");
        avatarMenu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(avatarMenu);


        menuItemCursor = new JRadioButtonMenuItem("Cursor");
        avatarMenu.add(menuItemCursor);
        menuItemCursor.addActionListener(this);

        menuItemSolderpen = new JRadioButtonMenuItem("Solderpen");
        avatarMenu.add(menuItemSolderpen);
        menuItemSolderpen.setEnabled(false);
        menuItemSolderpen.addActionListener(this);

        menuItemPaintbrush = new JRadioButtonMenuItem("Paintbrush");
        avatarMenu.add(menuItemPaintbrush);
        menuItemPaintbrush.setEnabled(false);
        menuItemPaintbrush.addActionListener(this);

        ButtonGroup menuItemAvatarButtonGroup = new ButtonGroup();
        menuItemAvatarButtonGroup.add(menuItemCursor);
        menuItemAvatarButtonGroup.add(menuItemSolderpen);
        menuItemAvatarButtonGroup.add(menuItemPaintbrush);
        menuItemCursor.setSelected(true);

        controlMenu = new JMenu("Controls");
        controlMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(controlMenu);


        menuItemToolbox = new JMenuItem("Toolbox");
        controlMenu.add(menuItemToolbox);
        menuItemToolbox.addActionListener(this);

        menuItemRadio = new JCheckBoxMenuItem("Radio");
        controlMenu.add(menuItemRadio);
        menuItemRadio.setSelected(false);
        menuItemRadio.setEnabled(false);
        menuItemRadio.addActionListener(this);

        menuRotateRight = new JMenuItem("Rotate Part Clockwise");
        controlMenu.add(menuRotateRight);
        menuRotateRight.setEnabled(false);
        menuRotateRight.addActionListener(this);

        menuRotateLeft = new JMenuItem("Rotate Part Counter-clockwise");
        controlMenu.add(menuRotateLeft);
        menuRotateLeft.setEnabled(false);
        menuRotateLeft.addActionListener(this);

        menuToggleHot = new JCheckBoxMenuItem("Hot Cursor", false);
        menuToggleHot.setEnabled(false);
        controlMenu.add(menuToggleHot);
        menuToggleHot.addActionListener(this);


        menuLoadChip = new JMenuItem("Load Chip");
        controlMenu.add(menuLoadChip);
        menuLoadChip.setEnabled(false);
        menuLoadChip.addActionListener(this);


        JMenuItem menuEnterRobot = new JMenuItem("Enter Robot");
        controlMenu.add(menuEnterRobot);
        menuEnterRobot.addActionListener(this);

        JMenuItem menuExitRobot = new JMenuItem("Exit Robot");
        controlMenu.add(menuExitRobot);
        menuExitRobot.addActionListener(this);

        menuFlipDevice = new JMenuItem("Flip Device/Wire");
        controlMenu.add(menuFlipDevice);
        menuFlipDevice.setEnabled(false);
        menuFlipDevice.addActionListener(this);

        menuBar.add(Box.createHorizontalGlue());

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);

        JMenuItem helpInfo = new JMenuItem("Help");
        helpMenu.add(helpInfo);
        helpInfo.addActionListener(this);

        try {
            System.setErr(System.out);
        }
        catch (SecurityException e) {
        }
    }

    public static void main(String[] args) {
        DQ dq = new DQ();
        GraphicsConfiguration gc = dq.getGraphicsConfiguration();
        Rectangle bounds = gc.getBounds();
        dq.setLocation(bounds.x + (bounds.width - 568) / 2,
                bounds.y + (bounds.height - 435) / 2);
        dq.setVisible(true);

    }

    public void setHotCursorSelected(boolean selected) {
        if (null != this.menuToggleHot) {
            this.menuToggleHot.setSelected(selected);
        }
    }

    public void setHotCursorEnabled(boolean enabled) {
        if (null != this.menuToggleHot) {
            this.menuToggleHot.setEnabled(enabled);
        }
    }

    public void setRotateEnabled(boolean enabled) {
        if (null != this.menuRotateRight && null != this.menuRotateLeft) {
            this.menuRotateRight.setEnabled(enabled);
            this.menuRotateLeft.setEnabled(enabled);
        }
    }

    public void setLoadChipEnabled(boolean enabled) {
        if (null != this.menuLoadChip) {
            this.menuLoadChip.setEnabled(enabled);
        }
    }

    public void setFlipDeviceEnabled(boolean enabled) {
        if(null != this.menuFlipDevice) {
            this.menuFlipDevice.setEnabled(enabled);
        }
    }

    public void setToolboxEnabled(boolean enabled) {
        if (null != this.menuItemToolbox) {
            this.menuItemToolbox.setEnabled(enabled);
        }
    }


    public void selectCursor() {
        if (null != this.menuItemCursor) {
            this.menuItemCursor.setSelected(true);
            if (null != myRoom && null != myRoom.level && myRoom.level.gameCursor instanceof LabCursor) {
                setHotCursorEnabled(true);
            }
            setToolboxEnabled(true);
        }
    }

    public void setSolderPenEnabled(boolean enabled) {
        if (null != this.menuItemSolderpen) {
            this.menuItemSolderpen.setEnabled(enabled);
        }
    }

    public void selectSolderpen() {
        if (null != this.menuItemSolderpen) {
            this.menuItemSolderpen.setSelected(true);
            this.setHotCursorEnabled(false);
            setToolboxEnabled(false);
            setFlipDeviceEnabled(true);
        }
    }

    public void setPaintbrushEnabled(boolean enabled) {
        if (null != this.menuItemPaintbrush) {
            this.menuItemPaintbrush.setEnabled(enabled);
            setFlipDeviceEnabled(false);
        }
    }

    public void selectPaintBrush() {
        if (null != this.menuItemPaintbrush) {
            this.menuItemPaintbrush.setSelected(true);
            this.setHotCursorEnabled(false);
            this.setToolboxEnabled(false);
        }
    }


    public void setRadioEnabled(boolean enabled) {
        if (null != this.menuItemRadio) {
            this.menuItemRadio.setEnabled(enabled);
        }
    }

    public void setRadioSelected(boolean selected) {
        if (null != this.menuItemRadio) {
            this.menuItemRadio.setSelected(selected);
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save Level")) {
            FileDialog fd = new FileDialog(this, "Save Level", FileDialog.SAVE);
            fd.setDirectory("ROlevels");
            fd.show();
            System.out.println("Dialog returned with "
                    + fd.getDirectory()
                    + fd.getFile());
            if (fd.getFile() != null) {
                myRoom.SaveLevel(fd.getDirectory() + fd.getFile());
            }
        }
        else if (e.getActionCommand().equals("Cursor")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleGameCursor();
            }
        }
        else if (e.getActionCommand().equals("Solderpen")) {
            // Handle Solderpen
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleSolderPen();
            }
        }
        else if (e.getActionCommand().equals("Paintbrush")) {
            // Handle Paintbrush
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handlePaintbrush();
            }
        }
        else if (e.getActionCommand().equals("Toolbox")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleToolbox();
            }
        }
        else if (e.getActionCommand().equals("Radio")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleRadio();
            }
        }
        else if (e.getActionCommand().equals("Rotate Part Clockwise")) {
            // Rotate a part clockwise
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleRotateDevice(1);
            }
        }
        else if (e.getActionCommand().equals("Rotate Part Counter-clockwise")) {
            // Rotate counter clockwise
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleRotateDevice(-1);
            }
        }
        else if (e.getActionCommand().equals("Hot Cursor")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleHotCursor();
            }
        }
        else if (e.getActionCommand().equals("Load Chip")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleLoadSmallChip();
            }
        }
        else if (e.getActionCommand().equals("Help")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleHelp();
            }
        }
        else if (e.getActionCommand().equals("Enter Robot")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleEnterRoom();
            }
        }
        else if (e.getActionCommand().equals("Exit Robot")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleExitRoom();
            }
        }
        else if (e.getActionCommand().equals("Flip Device/Wire")) {
            if (null != myRoom.level && null != myRoom.level.player && myRoom.level.player instanceof Avatar) {
                Avatar playerAvatar = (Avatar) myRoom.level.player;
                playerAvatar.handleFlipDevice();
            }
        }
        else if (e.getActionCommand().equals("Main Menu")) {
            int n = JOptionPane.showConfirmDialog(this, "Do you want to quit this level?",
                    "return to Main Menu", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                myRoom.level.Empty();
                myRoom.level = new MainMenu(myRoom);
                myRoom.level.Init();
                setHotCursorEnabled(false);
                setHotCursorSelected(false);
                setRotateEnabled(false);
                setLoadChipEnabled(false);
                setPaintbrushEnabled(false);
                setFlipDeviceEnabled(false);
                setToolboxEnabled(false);
                setSolderPenEnabled(false);
                setRadioSelected(false);
                setRadioEnabled(false);
                selectCursor();
            }
        }

        if (e.getActionCommand().equals("Sound")) {
            myRoom.useSounds = ((JCheckBoxMenuItem) e.getSource()).getState();
            if (!myRoom.useSounds) {
                Set<String> keys = myRoom.level.sounds.keySet();
                for (String soundFile : keys) {
                    SoundClip soundClip = myRoom.level.sounds.get(soundFile);
                    soundClip.audioClip.stop();
                }
            }
        }

        if (e.getActionCommand().equals("Exit")) {
            setVisible(false);
            dispose();
            System.exit(0);
        }

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





