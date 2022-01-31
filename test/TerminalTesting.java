import me.amrv.terminal.Terminal;
import me.amrv.terminal.TerminalCommand;
import me.amrv.terminal.TerminalPanel;

public class TerminalTesting implements TerminalCommand {

	public static void main(String[] args) throws Exception {
		try {
			TerminalPanel c = new TerminalPanel(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
