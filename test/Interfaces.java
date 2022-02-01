
import java.util.stream.IntStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marruiad
 */
public class Interfaces implements CharSequence {

    public static void main(String[] args) {
        System.out.println("> " + true + "< ");
    }
    
    @Override
    public int length() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char charAt(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream chars() {
        return CharSequence.super.chars(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IntStream codePoints() {
        return CharSequence.super.codePoints(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
