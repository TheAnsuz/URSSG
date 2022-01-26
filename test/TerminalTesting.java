import me.amrv.terminal.Terminal;
import me.amrv.terminal.TerminalCommand;

public class TerminalTesting implements TerminalCommand {

	public static void main(String[] args) {
		Terminal t = new Terminal(System.out);
		System.out.println();
		t.addCommand(new TerminalTesting());
		t.execute("hola");
	}

	@Override
	public String label() {
		return "hola";
	}

	@Override
	public boolean execute(String... arguments) {
		return true;
	}
}
