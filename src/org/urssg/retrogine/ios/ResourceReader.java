/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.urssg.retrogine.ios;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author marruiad
 */
public class ResourceReader {

    private static final int PRIMARY = Color.BLACK.getRGB();
    private static final int SECONDARY = new Color(168, 52, 235).getRGB();

    public static BufferedImage getDefaultImage(int width, int height) {
        final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                img.setRGB(x, y, x / 8 % 2 == 0 ? (y / 8 % 2 == 0 ? PRIMARY : SECONDARY) : (y / 8 % 2 == 0 ? SECONDARY : PRIMARY));
            }

        return img;
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
            final BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            img.createGraphics().drawImage(ImageIO.read(stream).getScaledInstance(width, height, Image.SCALE_REPLICATE), 0,0, null);
            return img;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return getDefaultImage(width, height);
        }
    }
    
    

}
