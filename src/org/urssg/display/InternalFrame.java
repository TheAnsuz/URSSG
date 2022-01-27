package org.urssg.display;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * Internal frame is the 1:1 scale image that will then be resized.
 * 
 * @author Adrian
 *
 */
public class InternalFrame extends JComponent {

	private static final long serialVersionUID = 6919774582044203793L;

	private Rectangle drawArea;
	protected InternalFrame(Dimension dim) {
		drawArea.setSize(dim);
	}
	
	
}
