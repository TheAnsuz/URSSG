
import java.io.File;
import org.urssg.retrogine.ios.ResourceReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marruiad
 */
public class IOSTest {
    
    public static void main(String[] args) {
        File file = new File("test/files/archivo.txt");
        System.out.println(file.getAbsolutePath());
        String[] contenido = ResourceReader.readFile(file);
        
        for (String line : contenido) {
            System.out.println("> " + line);
        }
    }
}
