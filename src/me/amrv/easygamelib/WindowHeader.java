package me.amrv.easygamelib;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class WindowHeader extends JPanel implements MouseMotionListener {

	private static final long serialVersionUID = -3675596437700227241L;
	private final Window base;
	private final WindowButton icon;
	private final JPanel container;
	private final WindowButton minimize;
	private final WindowButton changeState;
	private final WindowButton close;

	protected WindowHeader(Window base) {
		this.base = base;
		container = new JPanel();
		container.setOpaque(false);
		container.setLayout(new BorderLayout());
		container.setBackground(null);
		this.setBackground(Color.GRAY);
		this.setLayout(new BorderLayout());

		this.addMouseMotionListener(this);

		icon = new WindowButton(0, this.getBackground());
		minimize = new WindowButton(1, this.getBackground());
		changeState = new WindowButton(2, this.getBackground());
		close = new WindowButton(3, this.getBackground());

		this.add(icon, BorderLayout.LINE_START);
		container.add(minimize, BorderLayout.LINE_START);
		container.add(changeState, BorderLayout.CENTER);
		container.add(close, BorderLayout.LINE_END);
		this.add(container, BorderLayout.LINE_END);
	}

	public void setBackgroundColor(Color color) {
		this.setBackground(color);
	}

	private int oldX = 0;
	private int oldY = 0;

	@Override
	public void mouseDragged(MouseEvent e) {
		int desplazamientoX = e.getX() + oldX;
		int desplazamientoY = e.getY() + oldY;

		System.out.println("Relative: " + e.getX() + "x " + e.getY() + "y");
		System.out.println("Total:    " + e.getXOnScreen() + "x " + e.getYOnScreen() + "y");
		System.out.println("Desplaz.: " + desplazamientoX + "x " + desplazamientoY + "y");
		this.base.base.setLocation(e.getXOnScreen() + desplazamientoX, e.getYOnScreen() + desplazamientoY);

		oldX = e.getX();
		oldY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
