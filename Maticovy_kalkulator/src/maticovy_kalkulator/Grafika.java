/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Jarda
 */
public class Grafika extends JFrame {

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
    Tlacitka rovnase;
    Tlacitka potvrd1;
    Tlacitka potvrd2;
    
    NacitaciOkna radkyPrvni;
    NacitaciOkna sloupcePrvni;
    NacitaciOkna radkyDruhe;
    NacitaciOkna sloupceDruhe;
    
    JLabel prvni;
    JLabel druha;
    JLabel vysledek;
    JLabel operator;
    JLabel operace;
    
    FlowLayout pravy;
    FlowLayout levy;
    
    Tabulka prava;
    Tabulka leva;

    class NaslouchacOperace implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Tlacitka t = (Tlacitka)e.getSource();
            t.displej.setText(t.popis);
        }
        
    }
    
    class NaslouchacPotvrzeni implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Tlacitka t = (Tlacitka)e.getSource();
            int pocet;
            JTable tabulka;
            GridLayout mrizka;
            FlowLayout tab;
            
                                  
            try{
                System.out.println("Potvrzeni");
                
            if(t.displej.getName().equals(prvni.getName())){
                prvni.removeAll();
                leva = new Tabulka(radkyPrvni.toInt(),sloupcePrvni.toInt());
                for(int i = 0; i < sloupcePrvni.toInt();i++){
                    leva.getColumnModel().getColumn(i).setPreferredWidth(30);
                }
                leva.setRowHeight(30);
                leva.getTableHeader().setReorderingAllowed(false);
                leva.setTableHeader(null);
        
                JScrollPane sp1= new JScrollPane(leva);
                prvni.add(sp1);
            }else{
                druha.removeAll();
                prava = new Tabulka(radkyDruhe.toInt(),sloupceDruhe.toInt());
                for(int i = 0; i < sloupceDruhe.toInt();i++){
                    prava.getColumnModel().getColumn(i).setPreferredWidth(30);
                }
                prava.setRowHeight(30);
                prava.getTableHeader().setReorderingAllowed(false);
                prava.setTableHeader(null);
        
                JScrollPane sp2 = new JScrollPane(prava);
                druha.add(sp2);
                
                Maticovy_kalkulator.kalkulacka.repaint();
                
            }
            }catch(JTextFieldVyjimky v){
                JOptionPane.showMessageDialog(null, v.toString(), "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }

    public Grafika() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Maticova kalkulacka");
        this.setBounds(50, 50, 800, 500);
        Container hlavni = getContentPane();
        hlavni.setLayout(null);
        hlavni.setBackground(Color.GRAY);



        radkyPrvni = new NacitaciOkna();
        this.radkyPrvni.setBounds(10, 70, 30, 30);
        this.radkyPrvni.setBackground(Color.YELLOW);
        hlavni.add(radkyPrvni);

        sloupcePrvni = new NacitaciOkna();
        this.sloupcePrvni.setBounds(10, 110, 30, 30);
        this.sloupcePrvni.setBackground(Color.YELLOW);
        hlavni.add(sloupcePrvni);
        
        radkyDruhe = new NacitaciOkna();
        this.radkyDruhe.setBounds(260, 70, 30, 30);
        this.radkyDruhe.setBackground(Color.YELLOW);
        hlavni.add(radkyDruhe);

        sloupceDruhe = new NacitaciOkna();
        this.sloupceDruhe.setBounds(260, 110, 30, 30);
        this.sloupceDruhe.setBackground(Color.YELLOW);
        hlavni.add(sloupceDruhe);
        
        prvni = new JLabel();
        prvni.setName("Prvni");
        prvni.setLayout(new GridLayout());
        prvni.setBounds(50, 70, 200, 200);
        Tabulka zakladni1 = new Tabulka(0,0);
        prvni.add(zakladni1);
        hlavni.add(prvni);        

        
        druha = new JLabel();
        druha.setName("Druha");
        druha.setLayout(new GridLayout());
        druha.setBounds(300, 70, 200, 200);
        Tabulka zakladni2 = new Tabulka(0,0);
        druha.add(zakladni2);
        hlavni.add(druha);
        
        potvrd1 = new Tlacitka("OK", prvni, 0, 0);
        this.potvrd1.setBounds(10, 30, 30, 30);
        this.potvrd1.setBackground(Color.GREEN);
        this.potvrd1.addActionListener(new NaslouchacPotvrzeni());
        hlavni.add(potvrd1);
        
        potvrd2 = new Tlacitka("OK", druha, 0, 0);
        this.potvrd2.setBounds(260, 30, 30, 30);
        this.potvrd2.setBackground(Color.GREEN);
        this.potvrd2.addActionListener(new NaslouchacPotvrzeni());
        hlavni.add(potvrd2);

        vysledek = new JLabel();
        vysledek.setName("Vysledek");
        vysledek.setLayout(new GridLayout());
        vysledek.setBounds(550, 70, 200, 200);
        Tabulka zakladni3 = new Tabulka(0,0);
        vysledek.add(zakladni3);
        hlavni.add(vysledek); 

        operator = new JLabel();
        operator.setBounds(270, 150, 30, 30);
        operator.setText("Op");
        hlavni.add(operator);

        operace = new JLabel();
        operace.setLayout(new GridLayout(2, 3));
        operace.setBounds(210, 330, 120, 60);
        hlavni.add(operace);


        rovnase = new Tlacitka("=", vysledek, 0, 0);
        rovnase.setBounds(510, 155, 30, 30);
        hlavni.add(rovnase);

        seznam1nacti = new Tlacitka("Nacist ze seznamu", prvni, 1, 1);
        seznam1nacti.setBounds(110, 10, 80, 20);
        seznam1nacti.setText("Nacist ze seznamu");
        hlavni.add(seznam1nacti);

        seznam1uloz = new Tlacitka("Ulozit do seznamu", prvni, 2, 1);
        seznam1uloz.setBounds(110, 280, 80, 20);
        seznam1uloz.setText("Ulozit do seznamu");
        hlavni.add(seznam1uloz);

        soubor1nacti = new Tlacitka("Nacist ze souboru", prvni, 1, 2);
        soubor1nacti.setBounds(110, 35, 80, 20);
        soubor1nacti.setText("Nacist ze souboru");
        hlavni.add(soubor1nacti);

        soubor1uloz = new Tlacitka("Ulozit do souboru", prvni, 2, 2);
        soubor1uloz.setBounds(110, 305, 80, 20);
        soubor1uloz.setText("Ulozit do souboru");
        hlavni.add(soubor1uloz);

        seznam2nacti = new Tlacitka("Nacist ze seznamu", druha, 1, 1);
        seznam2nacti.setBounds(360, 10, 80, 20);
        seznam2nacti.setText("Nacist ze seznamu");
        hlavni.add(seznam2nacti);

        seznam2uloz = new Tlacitka("Ulozit do seznamu", druha, 2, 1);
        seznam2uloz.setBounds(360, 280, 80, 20);
        seznam2uloz.setText("Ulozit do seznamu");
        hlavni.add(seznam2uloz);

        soubor2nacti = new Tlacitka("Nacist ze souboru", druha, 1, 2);
        soubor2nacti.setBounds(360, 35, 80, 20);
        soubor2nacti.setText("Nacist ze souboru");
        hlavni.add(soubor2nacti);

        soubor2uloz = new Tlacitka("Ulozit do souboru", druha, 2, 2);
        soubor2uloz.setBounds(360, 305, 80, 20);
        soubor2uloz.setText("Ulozit do souboru");
        hlavni.add(soubor2uloz);

        seznam3uloz = new Tlacitka("Ulozit do seznamu", vysledek, 2, 1);
        seznam3uloz.setBounds(610, 280, 80, 20);
        seznam3uloz.setText("Ulozit do seznamu");
        hlavni.add(seznam3uloz);

        soubor3uloz = new Tlacitka("Ulozit do souboru", vysledek, 2, 2);
        soubor3uloz.setBounds(610, 305, 80, 20);
        soubor3uloz.setText("Ulozit do souboru");
        hlavni.add(soubor3uloz);

        plus = new Tlacitka("+", operator, 0, 0);
        plus.addActionListener(new NaslouchacOperace());
        operace.add(plus, 0);

        minus = new Tlacitka("-", operator, 0, 0);
        minus.addActionListener(new NaslouchacOperace());
        operace.add(minus, 1);

        nasobeni = new Tlacitka("X", operator, 0, 0);
        nasobeni.addActionListener(new NaslouchacOperace());
        operace.add(nasobeni, 2);

        gauss = new Tlacitka("GEM", operator, 0, 0);
        gauss.addActionListener(new NaslouchacOperace());
        operace.add(gauss, 3);

        invers = new Tlacitka("INV", operator, 0, 0);
        invers.addActionListener(new NaslouchacOperace());
        operace.add(invers, 4);

        transp = new Tlacitka("T", operator, 0, 0);
        transp.addActionListener(new NaslouchacOperace());
        operace.add(transp, 5);


    }
}
