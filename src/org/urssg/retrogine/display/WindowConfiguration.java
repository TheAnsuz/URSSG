package org.urssg.retrogine.display;

import java.awt.Dimension;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

public class WindowConfiguration {

	private String title;
	private Dimension size;
	private Dimension internalSize;
	private boolean doubleBuffer;
	private boolean queueRendering;
	private boolean manualResizing;
	private RenderingHints renderSettings;
	private List<RenderLayer> layers = new ArrayList<>();

	public static WindowConfiguration defaultConfig() {
		return new WindowConfiguration().setTitle("Window");
	}

	public WindowConfiguration() {
		title = "";
		size = new Dimension(640, 480);
		internalSize = new Dimension(210, 160);
		doubleBuffer = true;
		queueRendering = false;
		renderSettings = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
		manualResizing = true;
	}

	public WindowConfiguration setDoubleBuffering(boolean doubleBuffer) {
		this.doubleBuffer = doubleBuffer;
		return this;
	}
	
	public WindowConfiguration setQueuedRendering(boolean enabled) {
		this.queueRendering = enabled;
		return this;
	}
	
	public WindowConfiguration setRenderQuality(boolean highQuality) {
		renderSettings.put(RenderingHints.KEY_RENDERING, highQuality ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_COLOR_RENDER_SPEED);
		return this;
	}
	
	public WindowConfiguration enableManualResizing(boolean enabled) {
		this.manualResizing = enabled;
		return this;
	}
	
	public WindowConfiguration setTitle(String title) {
		this.title = title;
		return this;
	}

	public WindowConfiguration setSize(Dimension size) {
		this.size = size;
		return this;
	}

	public WindowConfiguration setSize(int width, int height) {
		this.size = new Dimension(width, height);
		return this;
	}

	public WindowConfiguration setInternalSize(Dimension size) {
		this.internalSize = size;
		return this;
	}

	public WindowConfiguration setInternalSize(int width, int height) {
		this.internalSize = new Dimension(width, height);
		return this;
	}

	public WindowConfiguration addRenderLayer(RenderLayer layer) {
		if (!layers.contains(layer))
			layers.add(layer);
		return this;
	}

	// Getters
	public String getTitle() {
		return title;
	}

	public Dimension getSize() {
		return size;
	}

	public Dimension getInternalSize() {
		return internalSize;
	}

	public RenderLayer[] getRenderLayers() {
		return layers.toArray(new RenderLayer[0]);
	}

	public boolean isDoubleBuffer() {
		return this.doubleBuffer;
	}

	public boolean isQueueRendering() {
		return this.queueRendering;
	}

	public boolean isManualResizing() {
		return this.manualResizing;
	}

	public RenderingHints getRenderSettings() {
		return this.renderSettings;
	}
	
	
}
