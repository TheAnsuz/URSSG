package me.amrv.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// Configurar para que lea del input stream
// Hacer que puedas sobreescribir comandos ya creados
// Crear un display para la terminal aparte
// Hacer que la obtencion de texto se realice en otro thread

public class Terminal {

	private InputStream in;
	private OutputStream out;
	private TerminalInputReader readerThread;
	private final ArrayList<TerminalCommand> commands = new ArrayList<>();

	public Terminal() {
		this(null, null);
	}

	public Terminal(OutputStream out) {
		this(null, out);
	}

	public Terminal(InputStream in) {
		this(in, null);
	}

	public Terminal(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
		readerThread = new TerminalInputReader();
		readerThread.setIn();
	}

	public InputStream getInput() {
		return in;
	}

	public void setInput(InputStream in) {
		this.in = in;
		readerThread.setIn();
		
	}

	public OutputStream getOutput() {
		return out;
	}

	public void setOutput(OutputStream out) {
		this.out = out;
	}

	public boolean hasInput() {
		return in != null;
	}

	public boolean hasOutput() {
		return out != null;
	}

	public boolean addCommand(TerminalCommand cmd) {
		if (this.commands.contains(cmd))
			return false;
		return this.commands.add(cmd);
	}

	public boolean removeCommand(TerminalCommand cmd) {
		return this.commands.remove(cmd);
	}
	
	public void clearCommands() {
		commands.clear();
	}

	public boolean removeCommandByName(String label) {
		TerminalCommand command = null;
		for (TerminalCommand cmd : commands) {
			if (cmd.label().equalsIgnoreCase(label)) {
				command = cmd;
				break;
			}
		}
		if (command == null)
			return false;
		
		return this.commands.remove(command);
	}
	
	public void execute(String label, String... arguments) {
		if (label == null)
			return;
		if (arguments == null)
			arguments = new String[0];

		for (TerminalCommand cmd : commands) {
			if (!label.equalsIgnoreCase(cmd.label()))
				continue;

			print(cmd.execute(arguments) ? cmd.success() : cmd.error());
			break;
		}
	}

	private void print(String str) {
		if (!hasOutput() || str == null)
			return;

		try {
			out.write(str.getBytes());
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, "Terminal output error : " + e.getMessage());
		}
	}

	private String[] parseString(String text) {
		if (text == null)
			return new String[0];
		return text.split("\\s");

	}

	final class TerminalInputReader implements Runnable {

		private final Thread thread;
		private boolean running;
		private boolean paused;
		private Scanner scan;

		TerminalInputReader() {
			this.thread = new Thread(this);
			this.running = false;
			this.paused = false;
		}

		private synchronized final void setIn() {
			if (in == null)
				return;
			scan = new Scanner(in);
		}

		boolean isPaused() {
			return this.paused;
		}

		synchronized void setPaused(boolean paused) {
			this.paused = paused;
		}

		void start() {
			if (running)
				return;

			running = true;
			this.thread.start();
		}

		void stop() {
			if (!running)
				return;

			try {
				running = false;
				this.thread.join();
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.SEVERE, "Terminal input error : " + e.getMessage());
			}
		}

		@Override
		public void run() {

			while (running) {
				if (!hasInput() || paused)
					continue;

				if (scan == null || !scan.hasNext())
					continue;

				final String[] line = parseString(scan.nextLine());
				
				if (line.length > 1)
					execute(line[0], Arrays.copyOfRange(line, 1, line.length));
				
				else if (line.length == 1)
					execute(line[0]);

			}
			stop();
		}

	}

}
