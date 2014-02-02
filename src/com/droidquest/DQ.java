package com.droidquest;

//This is the source code for DroidQuest 2.7. Copyright 2003 by Thomas Foote.

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import com.droidquest.avatars.GameCursor;
import com.droidquest.chipstuff.Port;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.Spark;
import com.droidquest.decorations.TextBox;
import com.droidquest.devices.ANDGate;
import com.droidquest.devices.Device;
import com.droidquest.devices.FlipFlop;
import com.droidquest.devices.NOTGate;
import com.droidquest.devices.Node;
import com.droidquest.devices.ORGate;
import com.droidquest.devices.PortDevice;
import com.droidquest.devices.XORGate;
import com.droidquest.items.Item;
import com.droidquest.items.ToolBox;
import com.droidquest.levels.Level;
import com.droidquest.levels.MainMenu;
import com.droidquest.materials.Material;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.Date;
import java.lang.Integer;
import java.util.Random;
import java.applet.*;
import java.net.URL;
import java.net.MalformedURLException;

public class DQ extends JFrame implements ActionListener 
{
	RoomDisplay myRoom;

	public DQ () 
	{
		// Constructor
		super("DroidQuest");
		setSize(560+8,384+27+24);
		addWindowListener( new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{ setVisible(false); dispose(); System.exit(0); }
		});	
		
		setIconImage(new ImageIcon("images/helper0.gif").getImage());

		Container contentPane = getContentPane();
		myRoom = new RoomDisplay();
		myRoom.dq=this;

		addFocusListener(new FocusAdapter() 
		{
			public void focusGained(FocusEvent e)
			{
				myRoom.requestFocus();
			}
		});

		contentPane.add(myRoom);
		myRoom.setLocation(0,0);

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

		menuItemSave = new JMenuItem("Save Level",KeyEvent.VK_S);
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

		try
		{
			System.setErr(System.out);
		}
		catch (SecurityException e) {}
	}

	public static void main(String[] args) 
	{
		DQ dq = new DQ();
		GraphicsConfiguration gc = dq.getGraphicsConfiguration();
		Rectangle bounds = gc.getBounds();
		dq.setLocation(bounds.x + (bounds.width - 568)/2,
				bounds.y + (bounds.height - 435)/2 );
		dq.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand() == "Save Level") 
		{
			FileDialog fd = new FileDialog(this,"Save Level", FileDialog.SAVE);
			fd.setDirectory("ROlevels");
			fd.show();
			System.out.println("Dialog returned with " 
					+ fd.getDirectory()
					+ fd.getFile());
			if (fd.getFile() != null)
				myRoom.SaveLevel(fd.getDirectory()+fd.getFile());
		}

		if (e.getActionCommand() == "Main Menu") 
		{
			int n = JOptionPane.showConfirmDialog(this,"Do you want to quit this level?",
					"return to Main Menu", JOptionPane.YES_NO_OPTION);
			if (n==0)
			{
				myRoom.level.Empty();
				myRoom.level = new MainMenu(myRoom);
				myRoom.level.Init();
			}
		}

		if (e.getActionCommand() == "Sound") 
		{
			myRoom.useSounds = ((JCheckBoxMenuItem)e.getSource()).getState();
			if (myRoom.useSounds==false)
			{
				Set<String> keys = myRoom.level.sounds.keySet();
				Iterator<String> iterator = keys.iterator();
				while (iterator.hasNext()) {
					String soundFile = iterator.next();
					SoundClip soundClip = myRoom.level.sounds.get(soundFile);
					soundClip.audioClip.stop();
				}
//				for (int a=0; a<myRoom.level.sounds.size(); a++)
//				{
//					SoundClip sc = (SoundClip) myRoom.level.sounds.elementAt(a);
//					sc.audioClip.stop();
//				}
			}
		}

		if (e.getActionCommand() == "Exit") 
		{ setVisible(false); dispose(); System.exit(0); }

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





