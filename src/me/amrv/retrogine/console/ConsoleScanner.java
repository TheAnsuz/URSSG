package me.amrv.retrogine.console;

import java.util.ArrayList;
import java.util.List;

public class ConsoleScanner implements Runnable {

	private final ConsoleInput scan;
	private final Thread thread;
	private boolean running;
	private static final List<ConsoleCommand> commands = new ArrayList<>();
	private static String commandPrefix = "/";
	private static String OPEN = "'";
	private static String CLOSE = "'";
	private Console console;

	protected ConsoleScanner(Console console) {
		this.console = console;
		this.scan = console.in;
		thread = new Thread(this);
		thread.setDaemon(true);
		this.running = false;
	}

	protected synchronized void addCommand(ConsoleCommand cmd, boolean priority) {
		if (commands.contains(cmd))
			return;
		
		if (priority)
			commands.add(0, cmd);
		else
			commands.add(cmd);
	}
	
	protected synchronized void removeCommand(ConsoleCommand cmd) {
		if (commands.contains(cmd))
			commands.remove(cmd);
	}
	
	protected synchronized void start() {
		if (running)
			return;

		running = true;
		thread.start();
	}

	protected synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (running) {
			if (scan.hasNext()) {
				String line = scan.readString();

				if (line.startsWith(commandPrefix)) {
					promptCommand(line);

				} else {
					promptText(line);

				}
			}
		}
	}

	private void promptText(String text) {
		console.history.addLine(text);
	}

	private void promptCommand(String raw) {
		final String[] arguments = raw.split("\\s+");

		for (ConsoleCommand cmd : commands) {
			if (cmd.label() == null)
				continue;
			
			if (arguments[0].equalsIgnoreCase(cmd.label()))
				if (cmd.command(arguments)) {
					// Not planned to add anything after completing a command
				} else
					console.err.println(cmd.errorMessage());
		}

	}

//	private String[] processCommand(String raw) {
//		String[] before = raw.split("\\s+");
//		List<String> after = new ArrayList<>();
//
//		int position = 0;
//		boolean enclosed = false;
//		for (String line : before) {
//			if (line.startsWith(OPEN))
//				enclosed = true;
//			if (line.endsWith(CLOSE))
//				enclosed = false;
//
//			after.set(position, after.get(position) + line);
//
//			if (!enclosed)
//				position++;
//		}
//
//		return (String[]) after.toArray();
//	}
}
