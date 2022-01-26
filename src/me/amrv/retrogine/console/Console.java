package me.amrv.retrogine.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Console {

	private static final char sizeChar = '#';
	private final JFrame window;
	protected final JTextField line;
	protected final ConsoleTextPanel history;
	private final ConsoleScanner scanner;
	public final ConsoleOutput out;
	public final ConsoleError err;
	public final ConsoleInput in;
	private static final EmptyBorder margin = new EmptyBorder(0,3,3,3);
	
	public Console() {
		this(null,121,36,null,null,null);
	}
	
	public Console(String name) {
		this(name,121,36,null,null,null);
	}
	
	public Console(String name, int columns, int lines, Font font, Color background, Color text) {
		// Validate data
		window = new JFrame((name == null) ? "Console" : name);
		window.pack();
		
		if (columns < 1)
			columns = 1;
		
		if (lines < 1)
			lines = 1;
		
		if (font == null)
			font = new Font(Font.DIALOG, Font.BOLD, 12);
		
		if (background == null)
			background = Color.BLACK;
		
		if (text == null)
			text = Color.WHITE;
		
		final ConsoleHandler handler = new ConsoleHandler(this);
		final JPanel container = new JPanel();

		// Calculations
		final FontMetrics metrics = window.getFontMetrics(font);
		
		// amount of characters
		int width = metrics.charWidth(sizeChar) * columns;
		width += window.getBounds().getWidth();
		width += margin.getBorderInsets().left + margin.getBorderInsets().right;

		// amount of lines
		int height = (metrics.getAscent()+metrics.getDescent())*(lines-1);
		// window margins
		height += margin.getBorderInsets().top + margin.getBorderInsets().bottom;
		// input box size
		height += metrics.getHeight();
//		// Add system margins
		height += window.getBounds().getHeight();

		// Container configuration
		container.setBorder(margin);
		container.setBackground(background);

		// Command input configuration
		line = new JTextField();
		line.setBorder(null);
		line.setMargin(null);
		line.setBackground(background);
		line.setForeground(text);
		line.setCaretColor(text);
		line.setSize(0, metrics.getHeight());
		line.setFont(font);
		line.setSelectedTextColor(background);
		line.setSelectionColor(Color.WHITE);
		line.addKeyListener(handler);

		// Command history configuration
		history = new ConsoleTextPanel(width, lines);
		history.setBorder(null);
		history.setBackground(background);
		history.setForeground(text);
		history.setFocusable(false);
		history.setFont(font);

		// Window configuration
		window.setResizable(false);
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setContentPane(container);
		window.setLayout(new BorderLayout());
		window.addWindowListener(handler);

		// Stream configuration
		out = new ConsoleOutput(this);
		err = new ConsoleError(this);
		in = new ConsoleInput(this);
		scanner = new ConsoleScanner(this);
		scanner.start();
		System.setIn(in);
		System.setErr(err);
		System.setOut(out);
		
		// Setup window
		window.add(history, BorderLayout.CENTER);
		window.add(line, BorderLayout.PAGE_END);
		window.setVisible(true);
	}

	public void addCommand(ConsoleCommand command) {
		scanner.addCommand(command, false);
	}
	
	public void removeCommand(ConsoleCommand command) {
		scanner.removeCommand(command);
	}
	
	public boolean isOpen() {
		return window.isVisible();
	}

	public void setOpenState(boolean open) {
		window.setVisible(open);
		if (open) {
			scanner.start();
			window.requestFocus();
		} else {
			scanner.stop();
			window.dispose();
		}
	}


}
