package me.amrv.terminal;

public interface TerminalCommand {

	/**
	 * Returns the label of the actual command, this is not case sensitive
	 * @return
	 */
	public String label();
	
	public boolean execute(String ...arguments);
	
	public default String success() {
		return "";
	}
	
	public default String error() {
		return "Error executing '" + label() + "' command";
	}
}
