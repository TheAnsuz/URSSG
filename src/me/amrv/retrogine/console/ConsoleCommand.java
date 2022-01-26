package me.amrv.retrogine.console;

public interface ConsoleCommand {

	String label();
	
	boolean command(String... args);
	
	default String errorMessage() {
		return "Error executing '" + label() + "' command.";
	}

}
