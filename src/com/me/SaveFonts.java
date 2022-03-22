package com.me;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFonts {
    public SaveFonts(String font) {
        File locO = new File(System.getenv("APPDATA")+"/JTE/FontChoice.txt");
        locO.delete();
        File loc = new File(System.getenv("APPDATA")+"/JTE/FontChoice.txt");
        try {
            FileWriter writer = new FileWriter(loc);
            writer.write(font);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
