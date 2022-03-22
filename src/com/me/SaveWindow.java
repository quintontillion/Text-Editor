package com.me;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class SaveWindow {

    public void savewin(Window win) {
        //creates the window
        window=win;
        JFrame jf = win.returnJFrame();
        frame = new JFrame("Closing Dialogue");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel label = new JLabel("do you want to save?");
        panel.add(label);
        JButton button1 = new JButton("yes");
        button1.addActionListener(new op1());
        button1.setSize(10, 10);
        panel.add(button1);
        JButton button2 = new JButton("no");
        button2.addActionListener(new op2());
        button2.setSize(10,10);
        panel.add(button2);
        frame.setSize(300,100);
        jf.setFocusableWindowState(false);
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);
    }
    public Window window;
    public JFrame frame;
    public SaveWindow(Window win) {
        System.out.println(win.Loc);
        if (!win.Loc.equals("")) {
            //reads file
            File location = new File(win.Loc);
            //makes a new scanner that is uninitialised
            Scanner read = null;
            //makes a string to collect the text from the file
            String tot="";
            try {
                //initiates the scanner with the file location to read the file
                read = new Scanner(location);
                //while loop
                while (read.hasNextLine()) {
                    //adds text to the string to collect all the text
                    tot+=read.nextLine();
                }
                //exceptions
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (!win.text.getText().equals(tot)) {
                savewin(win);
            }else {
                win.Close();
            }
        }else {
            if (!Objects.equals(win.text.getText(), "")) {
                savewin(win);
            }
            else {
                win.Close();
            }
        }
    }
    public class op1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveWindow.this.window.CloseAndSave();
            SaveWindow.this.frame.dispose();
        }
    }
    public class op2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SaveWindow.this.window.jf.dispose();
            SaveWindow.this.frame.dispose();
        }
    }
}
