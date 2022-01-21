package me.amrv.engine.window;

import java.util.List;

import me.amrv.engine.Drawer;

public class WindowThread implements Runnable {

	private final WindowDrawer drawer;
	private final Thread thread;
	private int renderLatency = 16;
	private boolean paused;
	
	protected WindowThread(WindowDrawer drawer) {
		this.drawer = drawer;
		this.thread = new Thread(this);
	}
	
	protected void setLatency(int latency) {
		this.renderLatency = latency;
	}
	
	public int getLatency() {
		return this.renderLatency;
	}
	
	protected void start() {
		thread.start();
	}

	protected void pause(boolean pause) {
		this.paused = pause;
	}
	
	public boolean isPaused() {
		return this.paused;
	}
	
	protected void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void run() {

		while (true) {
			if (paused)
				continue;
			final int width = drawer.getBounding().width;
			final int height = drawer.getBounding().height;
			
			for (List<Drawer> list : drawer.drawers.values())
				list.forEach(drawer->{
					drawer.render(new WindowRender(this.drawer.getGraphics2D()), width, height);
				});

			
			drawer.updateGraphics();
			try {
				Thread.sleep(renderLatency);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
