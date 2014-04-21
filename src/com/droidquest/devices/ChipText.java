package com.droidquest.devices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChipText extends JDialog {
    private JTextArea textarea;
    private JTextField textfield;
    private GenericChip myChip;

    public ChipText(GenericChip gc) {
        myChip = gc;
        setModal(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setSize(new Dimension(250, 300));

        textarea = new JTextArea("This is a test.");
        textarea.setFont(new Font("Serif", Font.PLAIN, 16));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textarea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Save");
        JButton restoreButton = new JButton("Restore");

        textfield = new JTextField(3);

        buttonPanel.add(saveButton);
        buttonPanel.add(restoreButton);
        buttonPanel.add(textfield);

        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
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

    public void setEditable(boolean editable) {
        textarea.setEditable(editable);
    }

    public void setText(String text, String label) {
        textarea.setText(text);
        textfield.setText(label);
    }


}