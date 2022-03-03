package com.me;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveWindow {
    public Window window;
    public JFrame frame;
    public SaveWindow(Window win) {
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
