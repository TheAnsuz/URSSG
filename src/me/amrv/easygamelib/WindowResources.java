package me.amrv.easygamelib;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class WindowResources {

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	private static final int COLOR = new Color(255, 255, 255, 200).getRGB();

	private static BufferedImage defaultIcon;
	private static BufferedImage fillIcon;
	private static BufferedImage minimizeIcon;
	private static BufferedImage maximizeIcon;
	private static BufferedImage closeIcon;
	private static BufferedImage normalizeIcon;

	public static BufferedImage getDefaultIcon() {
		if (defaultIcon != null)
			return defaultIcon;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		for (int x = (int) (WIDTH * .20); x < WIDTH * .80; x++)
			for (int y = (int) (HEIGHT * .20); y < HEIGHT * .80; y++) {

				image.setRGB(x, y, COLOR);
			}

		defaultIcon = image;
		return image;
	}

	public static BufferedImage getFillIcon() {
		if (fillIcon != null)
			return fillIcon;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < WIDTH; x++)
			for (int y = 0; y < HEIGHT; y++) {

				image.setRGB(x, y, COLOR);
			}

		fillIcon = image;
		return image;
	}

	public static BufferedImage getMinimizeIcon() {
		if (minimizeIcon != null)
			return minimizeIcon;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		for (int x = (int) (WIDTH * .325); x < WIDTH * .675; x++)
			image.setRGB(x, (int) (HEIGHT * .65), COLOR);

		minimizeIcon = image;
		return image;
	}

	public static BufferedImage getMaximizeIcon() {
		if (maximizeIcon != null)
			return maximizeIcon;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		for (int x = (int) (WIDTH * .32); x <= WIDTH * .68; x++) {
			image.setRGB(x, (int) (HEIGHT * .32), COLOR);
			image.setRGB(x, (int) (HEIGHT * .68), COLOR);
		}
		for (int y = (int) (HEIGHT * .32); y <= HEIGHT * .68; y++) {
			image.setRGB((int) (WIDTH * .32), y, COLOR);
			image.setRGB((int) (WIDTH * .68), y, COLOR);
		}
		
		maximizeIcon = image;
		return image;
	}
	
	public static BufferedImage getCloseIcon() {
		if (closeIcon != null)
			return closeIcon;

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		for (int x = (int) (WIDTH * .35); x < WIDTH * .65; x++) {
			for (int y = (int) (HEIGHT * .35); y < HEIGHT * .65; y++) {
				if (x == y)
					image.setRGB(x, y, COLOR);
			}
		}

		closeIcon = image;
		return image;
	}

}
