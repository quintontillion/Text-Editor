package com.me;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CheckFiles {
    public String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    public CheckFiles() {
        String fontloc = System.getenv("APPDATA")+"/JTE/FontChoice.txt";
        File loc = new File(fontloc);
        Scanner read = null;
        try {
            read = new Scanner(loc);
            String font=read.nextLine();
            int in=0;
            for (int i = 0; i < fonts.length; i++) {
                if (font.equals(fonts[i])) {
                    System.out.println("font found: "+fonts[i]);
                    in=i;
                    Window.Font=font;
                    break;
                }
            }
            if (!font.equals(fonts[in])) {
                System.out.println("Font not on system");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No font files found, creating");
            try {
                FileWriter writer = new FileWriter(loc);
                writer.write("Agency FB");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
