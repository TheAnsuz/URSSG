package me.amrv.oldretrogine.console;

public interface ConsoleCommand {

	String label();
	
	boolean command(String... args);
	
	default String errorMessage() {
		return "Error executing '" + label() + "' command.";
	}

}
