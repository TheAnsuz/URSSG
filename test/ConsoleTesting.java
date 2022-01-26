
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

//		// Im not dealing with the internal console overflows
//		StringBuilder line = new StringBuilder();
//		for (int i = Character.MIN_CODE_POINT; i <= Character.MAX_HIGH_SURROGATE; i++) {
//			if (i % 10000 == 0)
//				scan.nextLine();
//
//			if (line.length() > 190) {
//				System.out.println(line.toString());
//				line.setLength(0);
//			} else
//				line.append(" | " + i + "-" + (char) i);
//		}
//    	scan.nextLine();
        Console cmd = new Console();

//    	for (int i = 0; i < 32; i++)
//    		cmd.panel.addLine("line " + i + " from default FOR");
        String txt = "";
        while (true) {
            if (!scan.hasNext())
                continue;

            txt = scan.nextLine();

            if (txt.startsWith(".") && txt.length() > 1) {
                cmd.panel.addLine(txt.substring(1).replaceAll("&1", (char) (Character.MAX_HIGH_SURROGATE + 1) + "")
                        .replaceAll("&2", (char) (Character.MAX_HIGH_SURROGATE + 2) + "")
                        .replaceAll("&3", (char) (Character.MAX_HIGH_SURROGATE + 3) + "")
                        .replaceAll("&4", (char) (Character.MAX_HIGH_SURROGATE + 4) + "")
                        .replaceAll("&5", (char) (Character.MAX_HIGH_SURROGATE + 5) + "")
                        .replaceAll("&6", (char) (Character.MAX_HIGH_SURROGATE + 6) + "")
                        .replaceAll("&7", (char) (Character.MAX_HIGH_SURROGATE + 7) + ""));
//                cmd.panel.addLine(txt.replaceAll("(?<!\\\\)\\\\red", ((char) 0) +"")
//                .replaceAll("(?<!\\\\)\\\\green", ((char) 500) +""));
            }
        }

    }
}
