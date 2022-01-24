
import java.util.Scanner;
import me.amrv.console.Console;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author marruiad
 */
public class ConsoleTesting {

    public static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Console cmd = new Console();
        String txt = "";

        while (true) {
            if (!scan.hasNext())
                continue;
            
            txt = scan.nextLine();
            
            if (txt.startsWith(".")) {
                cmd.panel.addLine(txt.replaceAll("(?<!\\\\)\\\\red", ((char) 0) +"")
                .replaceAll("(?<!\\\\)\\\\green", ((char) 500) +""));
            }
        }

    }
}
