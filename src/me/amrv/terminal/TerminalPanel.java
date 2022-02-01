package me.amrv.terminal;

import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JComponent;

// El tama�o debe de poder cambiarse en pixeles y caracteres
// 

public class TerminalPanel extends JComponent {

	private static final long serialVersionUID = -3836678159417978463L;
	private static final char WIDTH_MODEL = '0';

	public TerminalPanel() {
		this(new Font(Font.DIALOG_INPUT, Font.PLAIN, 14), 121, 36);
	}

	public TerminalPanel(Font font) {
		this(font, 121, 36);
	}

	public TerminalPanel(int columns, int lines) {
		this(new Font(Font.DIALOG_INPUT, Font.PLAIN, 14), columns, lines);
	}

	public TerminalPanel(Font font, int columns, int lines) {
		if (font == null)
			throw new NullPointerException("Font canot be null");

		super.setFocusable(true); // Allows key events to be launched
		super.setFont(font);
		this.setSizeInChars(columns, lines);
		this.setPreferredSize(this.getSize());
	}

	public FontMetrics getFontMetrics() {
        return this.getFontMetrics(this.getFont());
    }

    // Not using a monospaced font may cause errors on the width calculation
    // however as a preset character is used, this is more accurate
    private void setSizeInChars(int columns, int lines) {
        final int width = getFontMetrics().charWidth(WIDTH_MODEL) * columns;
        final int height = (getFontMetrics().getAscent() + getFontMetrics().getDescent()) * lines;
        super.setSize(width, height);
    }
	
}
