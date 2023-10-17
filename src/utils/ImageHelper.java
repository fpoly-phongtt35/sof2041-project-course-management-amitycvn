/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class ImageHelper {

    public static ImageIcon getAppIcon() {
        URL url = ImageHelper.class.getResource("/icon/fpt.png");
        return new ImageIcon(url);
    }

    public static void save(File src) {
        try {
            File dir = new File("logos", src.getName());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            Path source = Paths.get(src.getAbsolutePath());
            Path destination = Paths.get(dir.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(174, 222, Image.SCALE_DEFAULT));
    }
    
}
