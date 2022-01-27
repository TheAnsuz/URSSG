package me.amrv.oldretrogine.console;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class ConsoleTextPanel extends JComponent implements MouseWheelListener {

	private static final long serialVersionUID = -7473209544115635593L;
	private final List<String> lines = new ArrayList<String>();
	private StringBuilder lastLine = new StringBuilder();
	private int maxLines;
	private int columnWidth;
	private int offset = 0;
	public static final RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);
	static {
		renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}

	public ConsoleTextPanel(int width, int displayLines) {
		this.maxLines = displayLines;
		this.columnWidth = width;
		this.addMouseWheelListener(this);
	}

	protected void setEditLine(String text) {
		
		lastLine = new StringBuilder(text);
		repaint();
	}

	protected StringBuilder editLine() {
		return lastLine;
	}
	
	public String getEditLine() {
		return lastLine.toString();
	}

	public void addLine(String text) {
		final FontMetrics m = this.getGraphics().getFontMetrics();
		String actual = "";
		int width = 0;
		for (char c : text.toCharArray()) {
			if (width + m.charWidth(c) > columnWidth) {
				// Empty cache and create line
				line(actual);
				actual = "";
				width = 0;
			}
			// Continue itinerating through string characters
			if (Character.isWhitespace(c) && width < 1)
				continue;

			actual += c;
			width += m.charWidth(c);

		}
		if (actual.trim().length() > 0)
			line(actual);

		if (offset + maxLines <= lines.size() + ((lastLine.length() > 0) ? 1 : 0))
			offset = lines.size() - maxLines + ((lastLine.length() > 0) ? 2 : 1);

		repaint();
	}

	private void line(String text) {
		lines.add(text);
	}

	public void clear() {
		lines.clear();
		offset = 0;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHints(renderingHints);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground());

		// top (leading) - bottom (trailing)
		final int height = g.getFontMetrics().getHeight() - g.getFontMetrics().getLeading();

		int position = g.getFontMetrics().getAscent();
//		g.setColor(Color.RED);
//		g.drawRect(0, 0, 100, ascent);

		g.setColor(getForeground());
		for (int i = 0; i < maxLines - ((lastLine.length() > 0) ? 1 : 0); i++) {

//			System.out.println(lines.size() + ".size < " + i + ".i + " + offset + ".off");
			
			if (lines.size() <= i + offset)
				break;

			g.drawString(lines.get(i + offset), 0, position);
			position += height;
		}
//		System.out.println("===================================================");
//		for (String s : lines)
//			System.out.println("'"+s+"'");
//		System.out.println("LL > '" + lastLine + "'");
//		System.out.println("===================================================");
		g.setColor(Color.YELLOW);
		if (lastLine.length() > 0)
			g.drawString(lastLine.toString(), 0, position);

		g.setColor(Color.WHITE);
//		g.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() > 0) {
			if (offset + maxLines <= lines.size() + ((lastLine.length() > 0) ? 1 : 0)) {
				offset++;
				repaint();
			}
		} else if (e.getWheelRotation() < 0) {
			if (offset > 0) {
				offset--;
				repaint();
			}
		}

		e.consume();

	}

}
