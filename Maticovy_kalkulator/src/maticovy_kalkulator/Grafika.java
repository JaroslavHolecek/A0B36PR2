/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jarda
 */
public class Grafika extends JFrame {//Samotne zpracovani kalkulatoru
    //po graficke strance i funkcni - pomoci vnitrnich trid

    Tlacitka plus;           //Komponenty kalkulatoru
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
    JLabel radkyRovnase;
    JLabel sloupceRovnase;
    JPanel prvni;
    JPanel druha;
    JPanel vysledek;
    JLabel operator;
    JPanel operace;
    FlowLayout pravy;
    FlowLayout levy;
    Tabulka prava;
    TableModel tm1;
    Tabulka leva;
    TableModel tm2;
    Tabulka vysledna;
    TableModel tm3;
    Matice m1;    //Matice vyuzivane pro vypocty
    Matice m2;
    Matice m3;
    Seznam seznam = new Seznam();  //Seznam pro ukladani matic

    public Matice nactiMatici(String s) throws IOException { //Nacita matici ze souboru zadaneho nazvu

        try {
            FileInputStream fis = new FileInputStream(s + ".txt");
            DataInputStream dis = new DataInputStream(fis);
            int a = dis.readInt();
            int b = dis.readInt();
            Matice nova = new Matice(a, b);//Po precteni poctu Radku a Sloupcu vytvori Matici

            for (int i = 0; i < a; i++) {//Nacita do Matice ulozene hodnoty
                for (int j = 0; j < b; j++) {
                    nova.setCislo(dis.readDouble(), i, j);
                }
            }
            dis.close();
            fis.close();
            return nova;
        } catch (FileNotFoundException e) {
            throw e;
        }

    }

    class NaslouchacOperace implements ActionListener {//Listener pro zadavani operaci s maticemi/matici

        @Override
        public void actionPerformed(ActionEvent e) {
            Tlacitka t = (Tlacitka) e.getSource();
            t.op.setText(t.popis);
        }
    }

    class NaslouchacPotvrzeni implements ActionListener {//Listener pro vytvoreni tabulky ze zadanych hodnot radku a sloupcu

        @Override
        public void actionPerformed(ActionEvent e) {
            Tlacitka t = (Tlacitka) e.getSource();
            try {

                if (t.displej.getName().equals(prvni.getName())) {

                    prvni.removeAll();

                    tm1 = new DefaultTableModel(radkyPrvni.toInt(), sloupcePrvni.toInt());
                    leva = new Tabulka(tm1, 0);

                    JScrollPane sp1 = new JScrollPane(leva);
                    prvni.add(sp1);
                } else {
                    druha.removeAll();

                    tm2 = new DefaultTableModel(radkyDruhe.toInt(), sloupceDruhe.toInt());
                    prava = new Tabulka(tm2, 0);

                    JScrollPane sp2 = new JScrollPane(prava);
                    druha.add(sp2);

                }
            } catch (JTextFieldVyjimky v) {
                JOptionPane.showMessageDialog(null, v.toString(), "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class NaslouchacRovnase implements ActionListener {//Listener pro vypocet ze zadanych matic/matice a operace

        String op;

        @Override
        public void actionPerformed(ActionEvent ae) {
            op = operator.getText();

            try {
                switch (op) {
                    case "+"://Pro scitani matic
                        if (!radkyPrvni.getText().equals(radkyDruhe.getText()) || !sloupcePrvni.getText().equals(sloupceDruhe.getText())) {
                            JOptionPane.showMessageDialog(null, "Tyto matice nelze secist", "Chyba", JOptionPane.OK_CANCEL_OPTION);
                        } else {
                            vysledek.removeAll();

                            radkyRovnase.setText(radkyPrvni.getText());
                            sloupceRovnase.setText(sloupcePrvni.getText());

                            m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                            m2 = prava.getMatice(radkyDruhe.toInt(), sloupceDruhe.toInt());

                            m3 = m1.secti(m2);

                            tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                            vysledna = new Tabulka(tm3, 1);
                            vysledna.setMatice(m3);

                            JScrollPane sp3 = new JScrollPane(vysledna);
                            vysledek.add(sp3);
                        }
                        break;
                    case "-"://Pro odecitani matic
                        if (!radkyPrvni.getText().equals(radkyDruhe.getText()) || !sloupcePrvni.getText().equals(sloupceDruhe.getText())) {
                            JOptionPane.showMessageDialog(null, "Tyto matice nelze odecist", "Chyba", JOptionPane.OK_CANCEL_OPTION);
                        } else {
                            vysledek.removeAll();

                            radkyRovnase.setText(radkyPrvni.getText());
                            sloupceRovnase.setText(sloupcePrvni.getText());

                            m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                            m2 = prava.getMatice(radkyDruhe.toInt(), sloupceDruhe.toInt());

                            m3 = m1.odecti(m2);

                            tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                            vysledna = new Tabulka(tm3, 1);
                            vysledna.setMatice(m3);

                            JScrollPane sp3 = new JScrollPane(vysledna);
                            vysledek.add(sp3);
                        }
                        break;
                    case "X"://Pro nasobeni matic
                        if (!sloupcePrvni.getText().equals(radkyDruhe.getText())) {
                            JOptionPane.showMessageDialog(null, "Tyto matice nelze vynasobit", "Chyba", JOptionPane.OK_CANCEL_OPTION);
                        } else {
                            vysledek.removeAll();

                            radkyRovnase.setText(radkyPrvni.getText());
                            sloupceRovnase.setText(sloupceDruhe.getText());

                            m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                            m2 = prava.getMatice(radkyDruhe.toInt(), sloupceDruhe.toInt());

                            m3 = m1.vynasobZprava(m2);

                            tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                            vysledna = new Tabulka(tm3, 1);
                            vysledna.setMatice(m3);

                            JScrollPane sp3 = new JScrollPane(vysledna);
                            vysledek.add(sp3);
                        }
                        break;
                    case "GEM"://Pro Gaussovu eliminacni metodu na prvni(leva) matici

                        vysledek.removeAll();

                        radkyRovnase.setText(radkyPrvni.getText());
                        sloupceRovnase.setText(sloupcePrvni.getText());

                        m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                        m3 = m1;
                        m3.gauss();

                        tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                        vysledna = new Tabulka(tm3, 1);
                        vysledna.setMatice(m3);

                        JScrollPane sp3 = new JScrollPane(vysledna);
                        vysledek.add(sp3);

                        break;
                    case "INV"://Pro vypocet inversni matice k prvni (leve) matici

                        if (!radkyPrvni.getText().equals(sloupcePrvni.getText())) {
                            JOptionPane.showMessageDialog(null, "K teto matici neexistuje inversni", "Chyba", JOptionPane.OK_CANCEL_OPTION);
                        } else {
                            vysledek.removeAll();

                            radkyRovnase.setText(radkyPrvni.getText());
                            sloupceRovnase.setText(sloupcePrvni.getText());

                            m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                            m3 = m1.inversni();

                            tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                            vysledna = new Tabulka(tm3, 1);
                            vysledna.setMatice(m3);

                            JScrollPane sp3i = new JScrollPane(vysledna);
                            vysledek.add(sp3i);
                        }
                        break;
                    case "T"://Pro vypocet transponovane matice k prvni (leve) matici

                        vysledek.removeAll();

                        radkyRovnase.setText(sloupcePrvni.getText());
                        sloupceRovnase.setText(radkyPrvni.getText());

                        m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());

                        m3 = m1;
                        m3.transponuj();

                        tm3 = new DefaultTableModel(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                        vysledna = new Tabulka(tm3, 1);
                        vysledna.setMatice(m3);

                        JScrollPane sp3i = new JScrollPane(vysledna);
                        vysledek.add(sp3i);

                        break;
                }

            } catch (JTextFieldVyjimky v) {
                JOptionPane.showMessageDialog(null, v.toString(), "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Chyba v udaji", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "Nelze provest.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
                                
        }
    }

    public class NaslouchacUlozeniDoSeznamu implements ActionListener {//Listener pro ukladani do seznamu

        @Override
        public void actionPerformed(ActionEvent ae) {
            Tlacitka t = (Tlacitka) ae.getSource();
            String s = t.displej.getName();
            int i;

            try {
                switch (s) {
                    case "Prvni":
                        m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());
                        i = seznam.pridejMatici(m1);
                        JOptionPane.showMessageDialog(null, "Matice ulozena jako " + i + ". prvek seznamu.", "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    case "Druha":
                        m2 = prava.getMatice(radkyDruhe.toInt(), sloupceDruhe.toInt());
                        i = seznam.pridejMatici(m2);
                        JOptionPane.showMessageDialog(null, "Matice ulozena jako " + i + ". prvek seznamu.", "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    default:
                        m3 = vysledna.getMatice(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                        i = seznam.pridejMatici(m3);
                        JOptionPane.showMessageDialog(null, "Matice ulozena jako " + i + ". prvek seznamu.", "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;

                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nezadano cislo", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (JTextFieldVyjimky v) {
                JOptionPane.showMessageDialog(null, v.toString(), "Chyba", JOptionPane.ERROR_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Chyba v udaji", "Chyba", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public class NaslouchacNacteniZeSeznamu implements ActionListener {//Listenr pro nacitani ze seznamu

        @Override
        public void actionPerformed(ActionEvent ae) {
            Tlacitka t = (Tlacitka) ae.getSource();
            String s = t.displej.getName();
            int cislo = Integer.parseInt(JOptionPane.showInputDialog(null, "Zadej cislo matice:", "Nacteni ze seznamu", JOptionPane.QUESTION_MESSAGE));

            try {
                switch (s) {
                    case "Prvni":
                        prvni.removeAll();
                        m1 = seznam.getMatice(cislo);
                        radkyPrvni.setText(Integer.toString(m1.getRadky()));
                        sloupcePrvni.setText(Integer.toString(m1.getSloupce()));
                        tm1 = new DefaultTableModel(m1.getRadky(), m1.getSloupce());
                        leva = new Tabulka(tm1, 0);
                        leva.setMatice(m1);

                        JScrollPane sp1 = new JScrollPane(leva);
                        prvni.add(sp1);
                        JOptionPane.showMessageDialog(null, "Matice nactena", "Nacteni", JOptionPane.OK_CANCEL_OPTION);
                        break;

                    case "Druha":
                        druha.removeAll();
                        m2 = seznam.getMatice(cislo);
                        radkyDruhe.setText(Integer.toString(m2.getRadky()));
                        sloupceDruhe.setText(Integer.toString(m2.getSloupce()));
                        tm2 = new DefaultTableModel(m2.getRadky(), m2.getSloupce());
                        prava = new Tabulka(tm2, 0);
                        prava.setMatice(m2);

                        JScrollPane sp2 = new JScrollPane(prava);
                        druha.add(sp2);
                        JOptionPane.showMessageDialog(null, "Matice nactena", "Nacteni", JOptionPane.OK_CANCEL_OPTION);
                        break;

                }
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nezadano cislo", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (SeznamoveVyjimky sv) {
                JOptionPane.showMessageDialog(null, sv.vypis, "Chyba", JOptionPane.ERROR_MESSAGE);
            }  catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Chyba pri nacitani ze seznamu.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class NaslouchacUlozeniDoSouboru implements ActionListener {//Listener pro ukladani do souboru

        @Override
        public void actionPerformed(ActionEvent ae) {
            Tlacitka t = (Tlacitka) ae.getSource();
            String s = t.displej.getName();
            String nazev = JOptionPane.showInputDialog(null, "Zadej nazev matice:", "Ulozeni do souboru", JOptionPane.QUESTION_MESSAGE);

            try {
                switch (s) {
                    case "Prvni":
                        m1 = leva.getMatice(radkyPrvni.toInt(), sloupcePrvni.toInt());
                        m1.ulozMatici(nazev);
                        JOptionPane.showMessageDialog(null, "Matice ulozena pod nazvem: " + nazev, "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    case "Druha":
                        m2 = prava.getMatice(radkyDruhe.toInt(), sloupceDruhe.toInt());
                        m2.ulozMatici(nazev);
                        JOptionPane.showMessageDialog(null, "Matice ulozena pod nazvem: " + nazev, "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    default:
                        m3 = vysledna.getMatice(Integer.parseInt(radkyRovnase.getText()), Integer.parseInt(sloupceRovnase.getText()));
                        m3.ulozMatici(nazev);
                        JOptionPane.showMessageDialog(null, "Matice ulozena pod nazvem: " + nazev, "Ulozeni", JOptionPane.OK_CANCEL_OPTION);
                        break;

                }
            }catch (NumberFormatException v) {
                JOptionPane.showMessageDialog(null, "Nezadano cislo", "Chyba", JOptionPane.ERROR_MESSAGE);
            }catch (NullPointerException v) {
                JOptionPane.showMessageDialog(null, "Chyba v udaji", "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (JTextFieldVyjimky v) {
                JOptionPane.showMessageDialog(null, v.toString(), "Chyba", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Chyba pri ukladani do souboru.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public class NaslouchacNacteniZeSouboru implements ActionListener {//Listenr pro nacitani ze souvoru

        @Override
        public void actionPerformed(ActionEvent ae) {
            Tlacitka t = (Tlacitka) ae.getSource();
            String s = t.displej.getName();
            String nazev = JOptionPane.showInputDialog(null, "Zadej nazev matice:", "Nacteni ze souboru", JOptionPane.QUESTION_MESSAGE);

            try {
                switch (s) {
                    case "Prvni":
                        prvni.removeAll();
                        m1 = nactiMatici(nazev);
                        radkyPrvni.setText(Integer.toString(m1.getRadky()));
                        sloupcePrvni.setText(Integer.toString(m1.getSloupce()));
                        tm1 = new DefaultTableModel(m1.getRadky(), m1.getSloupce());
                        leva = new Tabulka(tm1, 0);
                        leva.setMatice(m1);

                        JScrollPane sp1 = new JScrollPane(leva);
                        prvni.add(sp1);
                        JOptionPane.showMessageDialog(null, "Matice nactena", "Nacteni", JOptionPane.OK_CANCEL_OPTION);
                        break;
                    case "Druha":
                        druha.removeAll();
                        m2 = nactiMatici(nazev);
                        radkyDruhe.setText(Integer.toString(m2.getRadky()));
                        sloupceDruhe.setText(Integer.toString(m2.getSloupce()));
                        tm2 = new DefaultTableModel(m2.getRadky(), m2.getSloupce());
                        prava = new Tabulka(tm2, 0);
                        prava.setMatice(m2);

                        JScrollPane sp2 = new JScrollPane(prava);
                        druha.add(sp2);
                        JOptionPane.showMessageDialog(null, "Matice nactena", "Nacteni", JOptionPane.OK_CANCEL_OPTION);
                        break;

                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Chyba pri nacitani ze souboru - soubor neexistuje.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Grafika() {//Samotne telo kalkulatoru

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Maticova kalkulacka");
        this.setBounds(50, 50, 800, 500);
        Container hlavni = getContentPane();
        hlavni.setLayout(null);
        hlavni.setBackground(Color.GRAY);


        //Jednotlive komponenty s prirazenymi vlastnostmi
        radkyPrvni = new NacitaciOkna();                //Okna pro nacitani poctu radek a sloupcu
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

        prvni = new JPanel();                           //Okna ve kterych se nachazi tabulky
        prvni.setName("Prvni");
        prvni.setLayout(new FlowLayout());
        prvni.setBackground(Color.DARK_GRAY);
        prvni.setBounds(50, 70, 200, 200);
        hlavni.add(prvni);


        druha = new JPanel();
        druha.setName("Druha");
        druha.setLayout(new FlowLayout());
        druha.setBackground(Color.DARK_GRAY);
        druha.setBounds(300, 70, 200, 200);
        hlavni.add(druha);

        potvrd1 = new Tlacitka("OK", prvni, null, 0, 0);             //Tlacitka pro vytvoreni tabulek ze zadanych radku a sloupcu
        this.potvrd1.setBounds(10, 30, 30, 30);
        this.potvrd1.setBackground(Color.GREEN);
        this.potvrd1.addActionListener(new NaslouchacPotvrzeni());
        hlavni.add(potvrd1);

        potvrd2 = new Tlacitka("OK", druha, null, 0, 0);
        this.potvrd2.setBounds(260, 30, 30, 30);
        this.potvrd2.setBackground(Color.GREEN);
        this.potvrd2.addActionListener(new NaslouchacPotvrzeni());
        hlavni.add(potvrd2);

        vysledek = new JPanel();                                    //Okno ve kterem je umistena tabuka vysledku
        vysledek.setName("Vysledek");
        vysledek.setLayout(new FlowLayout());
        vysledek.setBounds(550, 70, 200, 200);
        hlavni.add(vysledek);

        radkyRovnase = new JLabel();                                //Okna indikujici pocet radek a sloupcu vysledne matice
        radkyRovnase.setBounds(510, 70, 30, 30);
        radkyRovnase.setBackground(Color.ORANGE);
        radkyRovnase.setHorizontalAlignment(JLabel.RIGHT);
        hlavni.add(radkyRovnase);

        sloupceRovnase = new JLabel();
        sloupceRovnase.setBounds(510, 110, 30, 30);
        sloupceRovnase.setBackground(Color.ORANGE);
        sloupceRovnase.setHorizontalAlignment(JLabel.RIGHT);
        hlavni.add(sloupceRovnase);

        operator = new JLabel();                                //Okno indikujici zvolenou operaci s matici/maticemi
        operator.setBounds(270, 150, 30, 30);
        operator.setText("Op");
        hlavni.add(operator);

        operace = new JPanel();                                 //Okno ve kterem jsou tlacitka pro zadavani operaci
        operace.setLayout(new GridLayout(2, 3));
        operace.setBounds(210, 330, 120, 60);
        hlavni.add(operace);


        rovnase = new Tlacitka("=", vysledek, null, 0, 0);           //Tlacitko spoustejici vypocet
        rovnase.setBounds(510, 155, 30, 30);
        this.rovnase.addActionListener(new NaslouchacRovnase());
        hlavni.add(rovnase);

        seznam1nacti = new Tlacitka("Nacist ze seznamu", prvni, null, 1, 1);         //Tlacitka pro ukaldani a nacitani ze seznamu/souboru
        seznam1nacti.setBounds(110, 10, 80, 20);
        seznam1nacti.setText("Nacist ze seznamu");
        seznam1nacti.addActionListener(new NaslouchacNacteniZeSeznamu());
        hlavni.add(seznam1nacti);

        seznam1uloz = new Tlacitka("Ulozit do seznamu", prvni, null, 2, 1);
        seznam1uloz.setBounds(110, 280, 80, 20);
        seznam1uloz.setText("Ulozit do seznamu");
        seznam1uloz.addActionListener(new NaslouchacUlozeniDoSeznamu());
        hlavni.add(seznam1uloz);

        soubor1nacti = new Tlacitka("Nacist ze souboru", prvni, null, 1, 2);
        soubor1nacti.setBounds(110, 35, 80, 20);
        soubor1nacti.setText("Nacist ze souboru");
        soubor1nacti.addActionListener(new NaslouchacNacteniZeSouboru());
        hlavni.add(soubor1nacti);

        soubor1uloz = new Tlacitka("Ulozit do souboru", prvni, null, 2, 2);
        soubor1uloz.setBounds(110, 305, 80, 20);
        soubor1uloz.setText("Ulozit do souboru");
        soubor1uloz.addActionListener(new NaslouchacUlozeniDoSouboru());
        hlavni.add(soubor1uloz);

        seznam2nacti = new Tlacitka("Nacist ze seznamu", druha, null, 1, 1);
        seznam2nacti.setBounds(360, 10, 80, 20);
        seznam2nacti.setText("Nacist ze seznamu");
        seznam2nacti.addActionListener(new NaslouchacNacteniZeSeznamu());
        hlavni.add(seznam2nacti);

        seznam2uloz = new Tlacitka("Ulozit do seznamu", druha, null, 2, 1);
        seznam2uloz.setBounds(360, 280, 80, 20);
        seznam2uloz.setText("Ulozit do seznamu");
        seznam2uloz.addActionListener(new NaslouchacUlozeniDoSeznamu());
        hlavni.add(seznam2uloz);

        soubor2nacti = new Tlacitka("Nacist ze souboru", druha, null, 1, 2);
        soubor2nacti.setBounds(360, 35, 80, 20);
        soubor2nacti.setText("Nacist ze souboru");
        soubor2nacti.addActionListener(new NaslouchacNacteniZeSouboru());
        hlavni.add(soubor2nacti);

        soubor2uloz = new Tlacitka("Ulozit do souboru", druha, null, 2, 2);
        soubor2uloz.setBounds(360, 305, 80, 20);
        soubor2uloz.setText("Ulozit do souboru");
        soubor2uloz.addActionListener(new NaslouchacUlozeniDoSouboru());
        hlavni.add(soubor2uloz);

        seznam3uloz = new Tlacitka("Ulozit do seznamu", vysledek, null, 2, 1);
        seznam3uloz.setBounds(610, 280, 80, 20);
        seznam3uloz.setText("Ulozit do seznamu");
        seznam3uloz.addActionListener(new NaslouchacUlozeniDoSeznamu());
        hlavni.add(seznam3uloz);

        soubor3uloz = new Tlacitka("Ulozit do souboru", vysledek, null, 2, 2);
        soubor3uloz.setBounds(610, 305, 80, 20);
        soubor3uloz.setText("Ulozit do souboru");
        soubor3uloz.addActionListener(new NaslouchacUlozeniDoSouboru());
        hlavni.add(soubor3uloz);

        plus = new Tlacitka("+", null, operator, 0, 0);                      //Tlacitka pro zadavani operaci
        plus.addActionListener(new NaslouchacOperace());
        operace.add(plus, 0);

        minus = new Tlacitka("-", null, operator, 0, 0);
        minus.addActionListener(new NaslouchacOperace());
        operace.add(minus, 1);

        nasobeni = new Tlacitka("X", null, operator, 0, 0);
        nasobeni.addActionListener(new NaslouchacOperace());
        operace.add(nasobeni, 2);

        gauss = new Tlacitka("GEM", null, operator, 0, 0);
        gauss.addActionListener(new NaslouchacOperace());
        operace.add(gauss, 3);

        invers = new Tlacitka("INV", null, operator, 0, 0);
        invers.addActionListener(new NaslouchacOperace());
        operace.add(invers, 4);

        transp = new Tlacitka("T", null, operator, 0, 0);
        transp.addActionListener(new NaslouchacOperace());
        operace.add(transp, 5);

    }
}
