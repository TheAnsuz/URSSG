package engine;

import java.awt.Color;

import me.amrv.engine.Drawer;
import me.amrv.engine.window.WindowRender;

public class DrawTesting implements Drawer{

	@Override
	public void render(WindowRender render, int width, int height) {
		render.setColor(Color.WHITE);
		render.drawLine(0, 0, width, height);
		
	}

}
