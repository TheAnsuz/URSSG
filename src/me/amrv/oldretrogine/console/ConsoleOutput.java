package me.amrv.oldretrogine.console;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public final class ConsoleOutput extends PrintStream {

	private final Console console;
	private final StringBuilder stream;

	protected ConsoleOutput(Console console) {
		super(new ConsoleNullOutputStream());
		this.console = console;
		stream = new StringBuilder();
	}

	public void print() {
		writeEditLine(System.lineSeparator());
	}
	
	@Override
	public void print(boolean b) {
		writeEditLine(String.valueOf(b));
	}

	@Override
	public void print(char c) {
		writeEditLine(String.valueOf(c));
	}

	@Override
	public void print(char[] s) {
		writeEditLine(String.valueOf(s));
	}

	@Override
	public void print(double d) {
		writeEditLine(String.valueOf(d));
	}

	@Override
	public void print(float f) {
		writeEditLine(String.valueOf(f));
	}

	@Override
	public void print(int i) {
		writeEditLine(String.valueOf(i));
	}

	@Override
	public void print(long l) {
		writeEditLine(String.valueOf(l));
	}

	@Override
	public void print(Object obj) {
		writeEditLine(String.valueOf(obj));
	}

	@Override
	public void print(String s) {
		writeEditLine(s);
	}

	@Override
	public PrintStream append(char c) {
		write(c);
		return this;
	}

	@Override
	public PrintStream append(CharSequence csq) {
		write(csq.toString());
		return this;
	}

	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		write(csq.toString().substring(start, end));
		return this;
	}

	@Override
	public void close() {
		console.setOpenState(false);
		stream.setLength(0);
		console.history.clear();
	}

	public boolean isClosed() {
		return !console.isOpen();
	}

	public void open() {
		console.setOpenState(true);
	}

	@Override
	public void println(boolean b) {
		write(String.valueOf(b));
		newLine();
	}

	@Override
	public void println(char c) {
		write(String.valueOf(c));
		newLine();
	}

	@Override
	public void println(char[] s) {
		write(String.valueOf(s));
		newLine();
	}

	@Override
	public void println(double d) {
		write(String.valueOf(d));
		newLine();
	}

	@Override
	public void println(float f) {
		write(String.valueOf(f));
		newLine();
	}

	@Override
	public void println(int i) {
		write(String.valueOf(i));
		newLine();
	}

	@Override
	public void println(long l) {
		write(String.valueOf(l));
		newLine();
	}

	@Override
	public void println(Object obj) {
		write(String.valueOf(obj));
		newLine();
	}

	@Override
	public void println(String s) {
		write(s);
		newLine();
	}
	
	public void println() {
		writeEditLine(System.lineSeparator());
	}

	private final void write(String str) {
		for (char c : str.toCharArray()) {
			write(c);
		}

	}

	private final void writeEditLine(String line) {
		for (char c : line.toCharArray())
			writeEditLine(c);
		console.history.repaint();
	}

	private final void writeEditLine(char c) {
		if (isClosed())
			return;
		if (c == '\n') {
			println(console.history.getEditLine());
			console.history.editLine().setLength(0);

		} else if (c == '\t') {
			console.history.editLine().append("    ");

		} else if (c == '\b') {
			console.history.editLine().deleteCharAt(console.history.editLine().length());
		} else
			console.history.editLine().append(c);
	}

	private final void write(char c) {
		if (isClosed())
			return;

		if (c == '\n')
			newLine();
		else if (c == '\t')
			stream.append("    ");
		else if (c == '\b')
			stream.deleteCharAt(stream.length());
		else
			stream.append(c);

	}

	private final void newLine() {
		if (isClosed() || stream.length() < 1)
			return;
		console.history.addLine(stream.toString());
		stream.setLength(0);
	}
}

class ConsoleNullOutputStream extends OutputStream {

	@Override
	public void write(int b) throws IOException {
		// doNothing()

	}

}
