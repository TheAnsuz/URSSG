package me.amrv.engine;

import me.amrv.engine.window.WindowRender;

public interface Drawer {
	
	void render(WindowRender render, int width, int height);
	
}
