package me.amrv.easygamelib;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class WindowButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 8225276641174393419L;

	private Color hoverColor = Color.DARK_GRAY;
	private Color normalColor = Color.BLACK;
	private Color clickColor = Color.RED;

	/*
	 * icon = new WindowButton(0); minimize = new WindowButton(1); changeState = new
	 * WindowButton(2); close = new WindowButton(3);
	 */
	protected WindowButton(int type, Color normalColor) {
		this.setBorder(null);
		this.setMargin(null);
		this.normalColor = normalColor;
		this.setBackground(normalColor);
		this.addMouseListener(this);
		this.setEnabled(false);
		// change es cualquier estado del boton
		// accion es al soltar

		if (type == 0)
			this.setIcon(new ImageIcon(WindowResources.getDefaultIcon()));
		else if (type == 1)
			this.setIcon(new ImageIcon(WindowResources.getMinimizeIcon()));
		else if (type == 2)
			this.setIcon(new ImageIcon(WindowResources.getMaximizeIcon()));
		else if (type == 3)
			this.setIcon(new ImageIcon(WindowResources.getCloseIcon()));
		else
			this.setIcon(new ImageIcon(WindowResources.getFillIcon()));

	}

	public void setIcon(Image image) {
		this.setIcon(new ImageIcon(image));
	}

	public void setNormalColorBackground(Color normal) {
		normalColor = normal;
	}

	public void setHoverColorBackground(Color hover) {
		hoverColor = hover;
	}

	public void setClickColorBackground(Color click) {
		clickColor = click;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.setBackground(clickColor);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.getBackground() != normalColor)
			this.setBackground(hoverColor);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(hoverColor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(normalColor);
	}

}
