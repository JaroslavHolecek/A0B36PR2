/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;

/**
 *
 * @author Jarda
 */
public class Grafika extends JFrame implements ActionListener {

    Tlacitka plus;
    Tlacitka minus;
    Tlacitka nasobeni;
    Tlacitka gauss;
    Tlacitka invers;
    Tlacitka transp;
    
    Tlacitka seznam1uloz;
    Tlacitka seznam2uloz;
    Tlacitka seznam3uloz;
    Tlacitka seznam1nacti;
    Tlacitka seznam2nacti;
    Tlacitka seznam3nacti;
    
    Tlacitka soubor1uloz;
    Tlacitka soubor2uloz;
    Tlacitka soubor3uloz;
    Tlacitka soubor1nacti;
    Tlacitka soubor2nacti;
    Tlacitka soubor3nacti;
    
    JLabel operator;
    
    public Grafika (){
        GridBagLayout hlavni = new GridBagLayout();
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Maticova kalkulacka");
        this.setBounds(100, 100, 300, 300);
        this.setLayout(hlavni);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
