/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.urssg.retrogine.ios;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author marruiad
 */
public class ImageReader {

    private static final int PRIMARY = Color.BLACK.getRGB();
    private static final int SECONDARY = new Color(168, 52, 235).getRGB();
    private static final int BORDER = Color.CYAN.getRGB();

    public static BufferedImage getDefaultImage(int width, int height) {
        final BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                img.setRGB(x, y, x / 8 % 2 == 0 ? (y / 8 % 2 == 0 ? PRIMARY : SECONDARY) : (y / 8 % 2 == 0 ? SECONDARY : PRIMARY));
            }

        return img;
    }

}
