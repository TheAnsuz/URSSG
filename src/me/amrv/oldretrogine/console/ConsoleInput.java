package me.amrv.oldretrogine.console;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ConsoleInput extends InputStream {

	private ByteBuffer buffer;
	private Console console;
	
	protected ConsoleInput(Console console) {
		this.console = console;
		this.buffer = ByteBuffer.allocate(0);
	}

	public void set(String str) {
		buffer = ByteBuffer.wrap(str.getBytes(), 0, str.length());
	}
	
	protected void add(String str) {
		buffer.put(str.getBytes());
	}
	
	@Override
	public int read() throws IOException {
		return buffer.hasRemaining() ? buffer.getInt() : -1;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		len = Math.min(buffer.remaining(), Math.min(b.length, len));
		buffer.get(b, off, len);
		return len;
	}
	
	public boolean hasNext() {
		return buffer.remaining() > -1;
	}
	
	@Override
	public int available() {
		return buffer.remaining();
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		return read(b,0,b.length);
	}
	
	public String readString() {
		byte[] ba = buffer.array();
		consume();
		return new String(ba);
	}
	
	public void consume() {
		buffer = ByteBuffer.allocate(0);
	}
	
}
