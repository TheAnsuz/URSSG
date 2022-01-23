package engine;

import java.awt.Color;

import me.amrv.engine.Drawer;
import me.amrv.engine.window.WindowRender;

public class DrawTesting implements Drawer {

	@Override
	public void render(WindowRender render, int width, int height) {
//		System.out.println("-> " + width+"w " + height);
		render.setColor(Color.WHITE);
		for (int i = 0; i < 1000; i++)
			render.drawLine(0, 0, width, height);

	}

}
