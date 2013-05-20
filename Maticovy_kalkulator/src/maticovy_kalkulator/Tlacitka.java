/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jarda
 */
public class Tlacitka extends JButton {//Tlacitka pouzita v kalkulatoru

    JPanel displej;
    JLabel op;
    String popis;
    int nacita_uklada; // 0 nic, 1 nacita, 2 uklada
    int seznam_soubor; // 0 nic, i seznam, 2 soubor

    public Tlacitka(String popis, JPanel displej, JLabel op, int nacita_uklada, int seznam_soubor) {
        super(popis);
        this.popis = popis;
        this.op = op;
        this.displej = displej;
        this.nacita_uklada = nacita_uklada;
        this.seznam_soubor = seznam_soubor;
    }
}
