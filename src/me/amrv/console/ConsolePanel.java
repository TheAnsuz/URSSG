/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author marruiad
 */
public class ConsolePanel extends JComponent {

    private static final long serialVersionUID = 8212460406013016074L;
    private static final char WIDTH_MODEL = '#';
    private static final int marginLeft = 1;
    private static final String defaultEdit = "> ";
    private static final long cursorUpdate = 500;

    private ConsoleStyle style = new DefaultConsoleStyle();
    private transient final List<String> lines = new ArrayList<>();
    private StringBuilder editLine = new StringBuilder(defaultEdit);
    private int offset = 0;
    private int maxLines;
    private int maxColumns;

    public ConsolePanel(Font font, int columns, int lines) {
        super();
        ConsolePanelEventHandler handler = new ConsolePanelEventHandler();
        super.setFont(font);
        this.setSizeInChars(columns, lines);
        super.setPreferredSize(super.getSize());
        this.maxLines = lines;
        this.maxColumns = columns;
        this.style.loadModel(getFontMetrics().charWidth(WIDTH_MODEL),
                (getFontMetrics().getAscent() + getFontMetrics().getDescent()));
        this.setFocusable(true);
        this.addMouseWheelListener(handler);
        this.addKeyListener(handler);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void setConsoleStyle(ConsoleStyle style) {
        style.loadModel(getFontMetrics().charWidth(WIDTH_MODEL),
                (getFontMetrics().getAscent() + getFontMetrics().getDescent()));
        this.style = style;
    }

    public void addLine(CharSequence text) {
        lines.add(text.toString());
        repaint();
    }

    public void setLastLine(CharSequence text) {
        editLine.setLength(0);
        editLine.append(text);
        repaint();
    }

    public void addLastLine(CharSequence text) {
        editLine.append(text);
        repaint();
    }

    public void removeLastLine() {
        editLine.setLength(0);
        editLine.append(defaultEdit);
        repaint();
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

    @Override
    public void paint(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(style.rendering);
        this.paint(g2);
    }

    private synchronized void paint(Graphics2D g) {
        g.setColor(style.background);
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        g.setColor(style.foreground);

        final int increment = getFontMetrics().getAscent() + getFontMetrics().getDescent();
        int position = getFontMetrics().getAscent();

        for (int i = 0; i < maxColumns; i++) {
            if (i + offset >= lines.size())
                break;

            paintLine(g, lines.get(i + offset), marginLeft, position);
            position += increment;
        }
        g.setColor(style.foreground);
        position += increment;
        g.drawString(editLine.toString(), marginLeft, position);
    }

    private void paintLine(Graphics2D g, String line, int x, int y) {
        StringBuilder actual = new StringBuilder();
        int width = x;
//        System.out.print(line + " > ");
        for (char c : line.toCharArray()) {
//            System.out.println(">>> " + c + "(" + Integer.valueOf(c) + ") ");
            if (c > Character.MIN_CODE_POINT && c < Character.MAX_HIGH_SURROGATE)
                // Write character as normal
                actual.append(c);
            else if (c == 0) {
                // works as a buffer clearer
                width += print(g, actual.toString(), width, y);
                actual.setLength(0);
            } else {
                // Change output color model
                if (style.model.contains(c))
                    g.setColor(style.model.getColorModel(c));
            }
        }
        if (actual.length() > 0)
            print(g, actual.toString(), width, y);
//        System.out.println();
    }

    // returns the width of the inserted sequence
    private int print(Graphics2D g, String chars, int x, int y) {
        g.drawString(chars, x, y);
        return getFontMetrics().stringWidth(chars);
    }

    class ConsolePanelCursorProcesser implements Runnable {

        private final Thread thread;
        private long updateT = 500;

        ConsolePanelCursorProcesser() {
            thread = new Thread(this);

        }

        @Override
        public void run() {
            // TODO Auto-generated method stub

        }

    }

    class ConsolePanelEventHandler implements MouseWheelListener, KeyListener {

        private boolean isValidCharacter(char c) {
            return (c > 31 && c < 127) || (c > 160 && c < 256);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            final int units = e.getScrollAmount() * e.getWheelRotation();

//			offset = units < 0 ? (offset + units < 0 ? 0 : offset + units) : (offset + units > lines.size() - maxLines + 2 ? lines.size() - maxLines + 2 : offset + units);
            offset = units < 0 ? Math.max(offset + units, 0) : Math.min(offset + units, lines.size() - maxLines + 2);

            repaint();
            e.consume();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (isValidCharacter(e.getKeyChar())) {
                editLine.append(e.getKeyChar());
                repaint();
                return;
            }

            switch (e.getKeyChar()) {
                case 8: // Backspace
                    if (editLine.length() > defaultEdit.length())
                        editLine.deleteCharAt(editLine.length() - 1);
                    break;
                case 127: // Suprimir <-
                    if (editLine.length() > defaultEdit.length())
                        editLine.delete(editLine.lastIndexOf(" ") < 0 ? defaultEdit.length() : (editLine.lastIndexOf(" ") + 1 >= editLine.length() ? editLine.length() - 1 : editLine.lastIndexOf(" ") + 1), editLine.length());
                    break;
                case 22: // Pegar
				try {
                    editLine.append(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor));
                } catch (HeadlessException | UnsupportedFlavorException | IOException e1) {
                    // Not a valid string to get from clipborard
                }
                break;
                case 10: // Intro
                    if (editLine.indexOf(defaultEdit) > 0)
                        editLine.delete(editLine.indexOf(defaultEdit), editLine.length());

                    addLine(editLine.toString());
                    editLine.setLength(0);
                    editLine.append(defaultEdit);
                    break;
                case 1:
                    editLine.append((Character.MAX_HIGH_SURROGATE + 2));
                    break;
                case 19:
                    editLine.append((Character.MAX_HIGH_SURROGATE + 3));
                    break;
                case 4:
                    editLine.append((Character.MAX_HIGH_SURROGATE + 4));
                    break;
                default:
                    System.out.println("Character not valid " + Integer.valueOf(e.getKeyChar()) + " " + e.getKeyChar() + " "
                            + KeyEvent.getKeyText(e.getKeyChar()));
            }

            repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}
