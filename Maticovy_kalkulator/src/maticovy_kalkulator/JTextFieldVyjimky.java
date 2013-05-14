/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

/**
 *
 * @author Jarda
 */
public class JTextFieldVyjimky extends Exception {
    String s;
    
    public JTextFieldVyjimky(String zprava){
        this.s = zprava;
    }
    
    @Override
    public String toString(){
        return s;
    }
}
