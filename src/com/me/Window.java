package com.me;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;

public class Window {
    public void Close() {
        Window.this.jf.dispose();
    }
    public void Save() {
        //gets the text in the text area
        String Stext = Window.this.text.getText();
        if (!(Stext.equals(null))) {
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
    public void CloseAndSave() {
        Save();
        jf.dispose();
    }
    String Loc="";
    public static JFrame jf;
    public static String Font="";
    public JFrame returnJFrame() {
        return jf;
    }
    String defaultloc="";
    public JTextArea text= new JTextArea();
    /**
     * class that creates the window
     */
    public Window(String loc, String name) {
        //Makes sure all the files are there
        new CheckFiles();
        text.setFont(java.awt.Font.decode(Font));
        System.out.println(Font);
        //Creates the window
        jf= new JFrame(name);
        //Sets Size
        jf.setSize(1000,1000);
        //Sets default close operation because I normally forget
        jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
            Loc =loc;
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
        //this menu is for the fonts and changing other aspects
        JMenu other = new JMenu("Other");
        //item1 is for opening
        JMenuItem item1 = new JMenuItem("Open");
        item1.addActionListener(new Item1Listener());
        JMenuItem item2 = new JMenuItem("Save");
        item2.addActionListener(new Item2Listener());
        JMenuItem newWin = new JMenuItem("New Window");
        newWin.addActionListener(new NewWindowListener());
        menu.add(item1);
        menu.add(item2);
        menu.add(newWin);
        //Font menu item
        JMenuItem font = new JMenuItem("Font");
        font.addActionListener(new FontListener());
        other.add(font);
        menubar.add(menu);
        menubar.add(other);
        jf.setJMenuBar(menubar);
        //Sets visible
        jf.setVisible(true);
        //sets close op
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                new SaveWindow(Window.this);
            }
        });
    }
    //jframe exit override
    //public class closeOp implements
    //class for listener of the jmenu button to look for files
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
                defaultloc=chooser.getDirectory()+chooser.getFile();
                Starter starter = new Starter(defaultloc,"Text Editor" + defaultloc);
            }
         }
    }

    //class for listener of the jmenu button
    public class Item2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           Save();
        }
    }
    //class for listener of the new window jmenu button
    public class NewWindowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Window(null, "Text Editor");
        }
    }
    //class listener for the font menu item
    public class FontListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FontWindow();
        }
    }
    public static void main(String args[]) {
        Window Windows = new Window(null, "Text Editor");
    }
}