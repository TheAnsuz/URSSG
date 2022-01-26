package me.amrv.retrogine;

import java.util.Scanner;

import me.amrv.retrogine.console.Console;
import me.amrv.retrogine.console.ConsoleCommand;

public abstract class Game implements ConsoleCommand {

	private static Console console;
	public static final Scanner scan = new Scanner(System.in);
	public static Scanner cmd;

	public final static void main(String[] args) throws Exception {
		// El logger es lo primero
		console = new Console();
		// Los comandos no funcionan
		console.addCommand(new AMRVCMDTEST());
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "exit";
	}
	
	@Override
	public boolean command(String... args) {
		System.exit(0);
		return true;
	}
	
}
class AMRVCMDTEST implements ConsoleCommand {
	
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return "exit";
	}
	
	@Override
	public boolean command(String... args) {
		System.exit(0);
		return true;
	}
}
