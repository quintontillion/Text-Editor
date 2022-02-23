package com.me;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class windows {
    String defaultloc="";
    public JTextArea text= new JTextArea();
    /**
     * this just creates the window
     */
    public windows(String loc) {
        //Creates the window
        JFrame jf = new JFrame("Text Editor Window");
        //Sets Size
        jf.setSize(1000,1000);
        //Sets default close operation because I normally forget
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Adds a controlled version of JPanel
        //Sets icon
        try {
            Image image = ImageIO.read(new File("src/assets/Main-Icon.png"));
            jf.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //the textfield of the file
        if (!(loc==null)) {
            //this is to make it load a file on default
            File location = new File(loc);
            try {
                //reads file
                Scanner read = new Scanner(location);
                String tot="";
                //while loop
                while (read.hasNextLine()) {
                    tot+=read.nextLine();
                }
                text.setText(tot);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        jf.add(text);
        //adds the menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        //item1 is for opening
        JMenuItem item1 = new JMenuItem("Open");
        item1.addActionListener(new Item1Listener());
        JMenuItem item2 = new JMenuItem("Save");
        item2.addActionListener(new Item2Listener());
        menu.add(item1);
        menu.add(item2);
        menubar.add(menu);
        jf.setJMenuBar(menubar);
        //Sets visible
        jf.setVisible(true);
    }
    //class for listener of the jmenu button
    public class Item1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileDialog chooser = new FileDialog((Frame) null,"Select file to open");
            chooser.setDirectory(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
            chooser.setMode(FileDialog.LOAD);
            chooser.setVisible(true);
            String file = chooser.getFile();
            System.out.println(file);
            chooser.dispose();
            if (!(file==null)) {
                defaultloc=chooser.getDirectory()+chooser.getFile();            }
         }
    }

    //class for listener of the jmenu button
    public class Item2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //gets the text in the text area
            String Stext = windows.this.text.getText();
            if (!(Stext==null)) {
                FileDialog chooser = new FileDialog((Frame) null,"Save file");
                chooser.setDirectory(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
                chooser.setMode(FileDialog.SAVE);
                chooser.setVisible(true);
                String file = chooser.getDirectory()+chooser.getFile();
                System.out.println(file);
                chooser.dispose();
                File file1 = new File(file);
                if (file1.exists()) {
                    if (file1.canWrite()) {
                        file1.delete();
                        try {
                            FileWriter writer = new FileWriter(file1);
                            writer.write(Stext);
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    try {
                        FileWriter writer = new FileWriter(file1);
                        writer.write(Stext);
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    public static void main(String args[]) {
        new windows(null);
    }
}