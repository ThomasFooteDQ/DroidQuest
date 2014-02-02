package com.droidquest.devices;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChipText extends JDialog 
{
JTextArea textarea;
JTextField textfield;
GenericChip myChip;

public ChipText(GenericChip gc) 
  {
	myChip=gc;
	setModal(false);
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());
	contentPane.setSize(new Dimension(250,300));
	
	textarea = new JTextArea("This is a test.");
	textarea.setFont(new Font("Serif", Font.PLAIN, 16));
	textarea.setLineWrap(true);
	textarea.setWrapStyleWord(true);
	
	JScrollPane scrollpane = new JScrollPane(textarea);
	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scrollpane.setPreferredSize(new Dimension(250,250));
	
	JPanel buttonpanel = new JPanel();
	buttonpanel.setLayout(new FlowLayout());
	
	JButton saveButton = new JButton("Save");
	JButton restoreButton = new JButton("Restore");
	
	textfield = new JTextField(3);
	
	buttonpanel.add(saveButton);
	buttonpanel.add(restoreButton);
	buttonpanel.add(textfield);
	
	contentPane.add(buttonpanel, BorderLayout.NORTH);
	contentPane.add(scrollpane, BorderLayout.CENTER);
	pack();
	
	saveButton.addMouseListener(new MouseAdapter() {
	   public void mouseReleased(MouseEvent e) {
	      myChip.description = textarea.getText();
	      myChip.label = textfield.getText();
	      myChip.GenerateIcons();
	   }
	});
	
	restoreButton.addMouseListener(new MouseAdapter() {
	   public void mouseReleased(MouseEvent e) {
	      textarea.setText(myChip.description);
	      textfield.setText(myChip.label);
	   }
	});
	
  }

public void setEditable(boolean editable) 
  {
	textarea.setEditable(editable);
  }

public void setText(String text, String label) 
  {
	textarea.setText(text);
	textfield.setText(label);
  }


}