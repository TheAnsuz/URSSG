package org.urssg.retrogine.display;

import java.awt.Graphics2D;

public interface RenderLayer {

	void draw(Graphics2D g, int width, int height);
}
