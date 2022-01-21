package me.amrv.engine;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;

public class WindowUtils {

	public static Rectangle getScreenBounds(GraphicsConfiguration configuration) {
		return configuration.getBounds();
	}

	public static Dimension getScreenPercent(GraphicsConfiguration configuration, float percent) {
		return new Dimension(Math.round(configuration.getBounds().width * (percent / 100f)),
				Math.round(configuration.getBounds().height * (percent / 100f)));
	}
}
