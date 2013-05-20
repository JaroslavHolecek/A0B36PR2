/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jarda
 */
public class NacitaciOkna extends JTextField {//Okna do kterych uzivatel zadava udaje

    public NacitaciOkna() {
        super("0");
    }

    public int toInt() throws JTextFieldVyjimky {//Vraci udaj v okne
        String s = this.getText();
        try {
            int i = Integer.parseInt(s);
            if (Integer.parseInt(s) < 0) {
                throw new JTextFieldVyjimky(String.format("Zadano zaporne cislo. %nVase zadani: " + this.getText()));
            }

            return i;
        } catch (NumberFormatException e) {
            throw new JTextFieldVyjimky(String.format("Nezadano prirozene cislo. %nVase zadani: " + this.getText()));
        }

    }
}
