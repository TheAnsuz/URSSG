package me.amrv.easygamelib;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	protected final JFrame base;
	private final WindowHeader header;
	private final WindowBody body;
	
	public Window(String title, Dimension size) {
		base = new JFrame(title);
		header = new WindowHeader(this);
		body = new WindowBody();
		base.setSize(size);
		base.setPreferredSize(size);
		base.setLocationRelativeTo(null);
		base.setUndecorated(true);
		
		base.setLayout(new BorderLayout());
		base.add(header, BorderLayout.PAGE_START);
		base.add(body);
		base.setVisible(true);
	}
	
	public WindowHeader getHeader() {
		return header;
	}

}
