/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.urssg.retrogine.ios;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author marruiad
 */
public class ResourceReader {

    private static final int PRIMARY = Color.BLACK.getRGB();
    private static final int SECONDARY = new Color(168, 52, 235).getRGB();

    private static BufferedImage getDefaultImage(int width, int height) {
        final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                img.setRGB(x, y, x / 8 % 2 == 0 ? (y / 8 % 2 == 0 ? PRIMARY : SECONDARY) : (y / 8 % 2 == 0 ? SECONDARY : PRIMARY));
            }

        return img;
    }

    private static boolean checkWriteFile(File file) {
        if (file.exists() && file.canRead())
            return true;

        new File(file.getPath()).mkdirs();
        try {
            file.createNewFile();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ResourceReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private static boolean checkReadFile(File file) {
        if (file.exists() && file.canRead())
            return true;

//        System.err.println(file.getParent());
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ResourceReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Obtiene una imagen desde los recursos del programa, para que funcione si
     * la imagen esta en {@code res\assets\image.png} la carpeta res debe de
     * estar en el subnivel del proyecto y debe de ser una carpeta de recursos,
     * no una normal, luego con declarar que el camino es
     * {@code assets/image.png} deberia cargar sin problemas.
     *
     * @param sourcePath el camino desde una carpeta de recursos
     * @param width la anchura que va a tener
     * @param height la altura que va a tener
     * @return la imagen cargada o una imagen por defecto en caso de error
     */
    public static BufferedImage loadImage(String sourcePath, int width, int height) {
        final InputStream stream = ResourceReader.class.getClassLoader().getResourceAsStream(sourcePath);
        try {
            final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            img.createGraphics().drawImage(ImageIO.read(stream).getScaledInstance(width, height, Image.SCALE_REPLICATE), 0, 0, null);
            return img;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return getDefaultImage(width, height);
        }
    }

    public static String[] readFile(File file) {
        if (!checkReadFile(file))
            return new String[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return new String[0];
        }
    }

    public static boolean writeFile(File file, String[] content) {
        if (!checkReadFile(file))
            return false;

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(file))) {
            for (String s : content)
                reader.write(s == null ? "" : s);
            return true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    public static AudioInputStream readAudioFile(String sourcePath) {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(ResourceReader.class.getClassLoader().getResourceAsStream(sourcePath));
            return stream;
        } catch (UnsupportedAudioFileException | IOException x) {
            x.printStackTrace();
            return null;
        }
    }

    public static AudioInputStream readAudioFile(File file) {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(file);
            return stream;
        } catch (UnsupportedAudioFileException | IOException x) {
            x.printStackTrace();
            return null;
        }
    }

}
