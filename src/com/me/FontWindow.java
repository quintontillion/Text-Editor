package com.me;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FontWindow {
    Choice choice;
    public String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    JFrame frame;
    JPanel panel;
    public FontWindow() {
        //new window
        frame = new JFrame("Font Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        choice = new Choice();
        choice.setBounds(0, 0, 380, 36);
        String entire="";
        for (int i = 0; i < fonts.length; i++) {
            entire+=fonts[i]+"\n";
            choice.add(fonts[i]);
        }
        choice.addKeyListener(new panelListen());
        panel.add(choice);
        frame.setSize(400,300);
        //adds the apply button
        JButton apply = new JButton("New button");
        apply.setText("Apply");
        apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SaveFonts(choice.getSelectedItem());
                Window.Font=choice.getSelectedItem();
                Window.jf.repaint();
                System.out.println("done");
            }
        });
        apply.setBounds(0, 242, 85, 21);
        panel.add(apply);
        frame.setVisible(true);
    }
    class panelListen implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyPressed(KeyEvent e) {
        }
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyChar());
            if (Character.isLetter(e.getKeyChar())) {
                for (int i = 0;i<fonts.length;i++) {
                    if (Character.toLowerCase(fonts[i].charAt(0))==Character.toLowerCase(e.getKeyChar())) {
                        choice.select(i);
                        break;
                    }
                }
            }
        }
    }
}
