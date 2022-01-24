/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.GlyphVector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javax.swing.JPanel;

/**
 *
 * @author marruiad
 */
public class ConsolePanel extends JPanel {

    private static final char WIDTH_MODEL = '#';
    private transient final List<LineContext> lines;
    private StringBuilder editLine = new StringBuilder("> ");
    private int offset;
    private int maxLines;
    private int maxColumns;
    
    public ConsolePanel(Font font, int columns, int lines) {
        super(null, true);
        this.offset = 0;
        this.maxLines = lines;
        this.maxColumns = columns;
        this.lines = new ArrayList<>();
        setSizeInChars(columns, lines);
        super.setPreferredSize(super.getSize());
        super.setBackground(Color.BLACK);
        super.setForeground(Color.WHITE);
    }

    public void addLine(CharSequence text) {
        lines.add(new LineContext(text));
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
        repaint();
    }
    
    public FontMetrics getFontMetrics() {
        return super.getFontMetrics(super.getFont());
    }

    private void setSizeInChars(int columns, int lines) {
        super.setSize(
                getFontMetrics().charWidth(WIDTH_MODEL) * columns,
                (getFontMetrics().getAscent() + getFontMetrics().getDescent()) * lines
        );
    }

    @Override
    public synchronized void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(getBackground());
        g.fillRect(getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        g.setColor(getForeground());

        if (offset >= lines.size())
            return;

        /*
         * Line 0
         * Line 1
         * Line 2
         * Line 3
         * Line 4
         * Line 5 -- screen minimun
         * Line 6
         * Line 7
         * Line 8
         * Line 9
         * Line 10
         * Line 11
         * Line 12
         * Line 13
         * Line 14 -- screen maximun
         * Line 15
         * Line 16
         * 
         * EditLine
        */
        int position = getFontMetrics().getAscent();
        System.out.println("> " + lines.subList(offset, offset+maxLines-1).size());
        for (LineContext line : lines.subList(offset, Math.min(offset+maxLines-1, lines.size()))) {
            int width = 0;
            for (Object o : line.format()) {
                if (o == null)
                    continue;
                
                if (o instanceof Color)
                    g2.setColor((Color) o);
                else {
                    g2.drawGlyphVector(getGlyph(g2, o.toString()), width, position);
                    width += getFontMetrics().stringWidth(o.toString());
                }
            }
            position += getFontMetrics().getAscent() + getFontMetrics().getDescent();
        }
        // Let the console have one line between last printed line and the edit line
        position += getFontMetrics().getAscent() + getFontMetrics().getDescent();
        g.setColor(Color.WHITE);
        g2.drawGlyphVector(getGlyph(g2, editLine.toString()), 0, position);
    }

    private GlyphVector getGlyph(Graphics2D g2, String text) {
        return getFont().createGlyphVector(g2.getFontRenderContext(), text);
    }
    
    private static boolean isVaidChar(char c) {
        return (c >= 32 && c <= 126) || (c >= 161 && c <= 382);
    }

    
    
    private final class LineContext implements CharSequence {

        private final StringBuffer buffer;

        public LineContext() {
            buffer = new StringBuffer();
        }
        
        public LineContext(CharSequence text) {
            buffer = new StringBuffer(text);
        }
        
        public void append(CharSequence chars) {
            buffer.append(chars);
        }

        @Override
        public int length() {
            return buffer.length();
        }

        @Override
        public char charAt(int index) {
            return buffer.charAt(index);
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return buffer.subSequence(start, end);
        }

        @Override
        public IntStream chars() {
            return buffer.chars();
        }

        @Override
        public IntStream codePoints() {
            return buffer.codePoints();
        }

        public Object[] format() {
            List<Object> segments = new ArrayList<>();
            StringBuilder actual = new StringBuilder();
            segments.add(actual);
            for (char c : buffer.toString().toCharArray()) {
                if (isVaidChar(c)) {
                    actual.append(c);
                } else {
                    segments.add( c < 250 ? Color.RED : Color.GREEN );
                    actual = new StringBuilder();
                    segments.add(actual);
                }
            }
            return segments.toArray();
        }

    }
}
