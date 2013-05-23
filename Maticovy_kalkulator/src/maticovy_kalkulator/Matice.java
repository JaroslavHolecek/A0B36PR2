/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Jarda
 */
public class Matice {// Trida reprezentujici jednotlive matice a metody, ktere lze s maticemi provadet

    private int radky;// Pocet radku matice
    private int sloupce;// Pocet sloupcu matice
    private double[][] matice;// Reprezentace samotne matice 

    public Matice(int radky, int sloupce) {//Vytvori matici o zadanem poctu radku a sloupcu, pokud jsou tyto hodnoty zaporne, tak jejich absolutni hodnotu
        this.radky = Math.abs(radky);
        this.sloupce = Math.abs(sloupce);
        this.matice = new double[this.radky][this.sloupce];
    }

    public void setCislo(double cislo, int radek, int sloupec) {//Nastavi cislo na zadane souradnice
        try {
            if ((radek < 0 || radek >= this.radky) || (sloupec < 0 || sloupec >= this.sloupce)) {
            } else {
                this.matice[radek][sloupec] = cislo;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public int getRadky() {//Vrati pocet radku matice
        return this.radky;
    }

    public int getSloupce() {//Vrati pocet sloupcu matice
        return this.sloupce;
    }

    public double getCislo(int radek, int sloupec) throws Exception {//Vrati cislo na zadane pozici
        try {
            if (radek < 0 || radek >= this.radky || sloupec < 0 || sloupec >= this.sloupce) {//Pokud je zadana pozice mimo matici vrati NaN a vypise chybovou hlasku

                return (double) 0 / 0;
            }
            return this.matice[radek][sloupec];
        } catch (Exception e) {
            throw e;
        }
    }

    public Matice getMatice() {// Vrati kompetni matici
        return this;
    }

    public String toString(int desMista) {//Prevede matici na String
        String s = new String();
        try {
            if (this.radky == 0 || this.sloupce == 0) {//Pokud ma matice nulovy pocet radku, nebo sloupcu vypise se chybova hlaska

                return s;
            } else {
                for (int i = 0; i < this.radky; i++) {//Prochazi matici
                    for (int j = 0; j < this.sloupce; j++) {
                        s += String.format("%." + desMista + "f", this.matice[i][j]);
                        if (j + 1 < this.sloupce) {// Jeslize je cislo posledni na redku nevypise se za nim mezera
                            s += " ";
                        }
                    }
                    s += String.format("%n");//Odradkuje radek matice
                }
                return s;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean stavScitani(Matice a) {// True jestlize jde matice secit jinak false
        if (this.radky == a.radky && this.sloupce == a.sloupce) {
            return true;
        } else {
            return false;
        }
    }

    public Matice secti(Matice a) throws Exception {//Secte dve matice
        Matice vysledek = new Matice(this.radky, this.sloupce);
        try {
            for (int i = 0; i < this.radky; i++) {//Prochazi matice a scita jejich cleny
                for (int j = 0; j < this.sloupce; j++) {
                    vysledek.matice[i][j] = this.matice[i][j] + a.matice[i][j];
                }
            }
            return vysledek;
        } catch (Exception e) {
            throw e;
        }
    }

    public Matice odecti(Matice a) {//Odecte dve matice
        Matice vysledek = new Matice(this.radky, this.sloupce);
        try {
            for (int i = 0; i < this.radky; i++) {//Prochazi matice a scita jejich cleny
                for (int j = 0; j < this.sloupce; j++) {
                    vysledek.matice[i][j] = this.matice[i][j] - a.matice[i][j];
                }
            }
            return vysledek;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean stavNasobeni(Matice a) {
        if (this.sloupce == a.radky) {
            return true;
        } else {
            return false;
        }
    }

    public Matice vynasobZprava(Matice a) {//Vynasobi matici Matici v argumentu zprava
        Matice vysledek = new Matice(this.radky, a.sloupce);//Vysledna matice
        double mezivysledek = 0;//Pomocna promena
        try {
            for (int i = 0; i < this.radky; i++) {//Prochazeni maticemi
                for (int j = 0; j < a.sloupce; j++) {
                    for (int k = 0; k < this.sloupce; k++) {//Pocita vysledek secteni vsech nasobenych dvojic
                        mezivysledek += this.matice[i][k] * a.matice[k][j];
                    }
                    vysledek.matice[i][j] = mezivysledek;
                    mezivysledek = 0;
                }
            }
            return vysledek;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean stavDeterminant() {
        if (this.radky > 4) {
            return false;
        } else {
            return true;
        }
    }

    public double determinant() throws Exception {//Vrati determinant matice, pro singularni, nebo obdelnikovou matici vrati 0
        double determinant = 0;
        double pom;//pomocna promena
        double pamet0;//pomocna promena
        double pamet1;//pomocna promena
        double pamet2;//pomocna promena

        try {
            if ((this.radky != this.sloupce)) {//Pokud je matice obdelnikova vrati 0
                determinant = 0;
            } else {
                for (int i = 0; i < this.sloupce; i++) {//Prochazeni prvniho radku
                    pom = this.matice[0][i];//Nastavuje pomocnou promenou na hodnotu aktualniho cisla
                    pamet0 = pom;
                    if (this.radky == 1) {//pokud ma matice 1 radek vrati hodnotu determinantu jako toto cislo 
                        determinant = pom;//Prida pomocnou promenou do hodnoty determinantu
                    } else {
                        for (int j = 0; j < this.sloupce; j++) {//Prochazeni druheho radku
                            if (j == i) {//Kontroluje zda aktualni cislo neni ve stejnem sloupci jako predchozi
                                continue;
                            }
                            pom = pamet0;//Nastavi pomocnou promenou na hodnotu do predchoziho radku

                            if (j < i) {
                                pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko

                            }
                            pom = pom * this.matice[1][j];//Prida aktualni cislo jako nasobek
                            pamet1 = pom;
                            if (this.radky == 2) {//Pokud ma matice dva radky prida pomocnou promenou do hodnoty determinantu
                                determinant += pom;

                            } else {

                                for (int k = 0; k < this.sloupce; k++) {

                                    if ((k == i) || (k == j)) {//Kontroluje, zda neni aktualni cislo ve stejnem sloupci jako nektere z predchozich
                                        continue;
                                    }
                                    pom = pamet1;//Nastavi pomocnou promenou na hodnotu do predchoziho radku
                                    if (k < i) {
                                        pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko

                                    }
                                    if (k < j) {
                                        pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko

                                    }
                                    pom = pom * this.matice[2][k];//Prida aktualni cislo jako nasobek
                                    pamet2 = pom;//Ulozi hodnotu pomocne promene pro treti radek 
                                    if (this.radky == 3) {//Pokud ma matice tri radky prida pomocnou promenou do hodnoty determinantu
                                        determinant += pom;

                                    } else {

                                        for (int l = 0; l < this.sloupce; l++) {//Prochazeni ctvrteho radku

                                            if ((l == i) || (l == j) || (l == k)) {//Kontroluje, zda neni aktualni cislo ve stejnem sloupci jako predchozi
                                                continue;
                                            }
                                            pom = pamet2;//Nastavi pomocnou promenou na hodnotu do predchoziho radku
                                            if (l < i) {
                                                pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko
                                            }
                                            if (l < j) {
                                                pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko

                                            }
                                            if (l < k) {
                                                pom = pom * (-1);//Pokud je cislo s predchozim "left-down" zmeni znamenko

                                            }
                                            pom = pom * this.matice[3][l];//Prida aktualni cislo jako nasobek
                                            determinant += pom;

                                        }
                                    }
                                }
                            }

                        }
                    }

                }

            }
            return determinant;
        } catch (Exception e) {
            throw e;
        }
    }

    public void transponuj() throws Exception {//Ztransponuje matici
        Matice pom = new Matice(this.sloupce, this.radky);//Vytvori pomocnou matici
        int pom1;
        try {
            for (int i = 0; i < this.radky; i++) {//Pomocnou matici nastavi jako transponovanou k zadane matici
                for (int j = 0; j < this.sloupce; j++) {
                    pom.matice[j][i] = this.matice[i][j];
                }
            }
            pom1 = this.radky;//Prehodi hodnotu radku a sloupcu v transponovane matici
            this.radky = this.sloupce;
            this.sloupce = pom1;
            this.matice = new double[pom.getRadky()][pom.getSloupce()];
            for (int i = 0; i < this.getRadky(); i++) {// Nastavi pomocnou jako matici this
                for (int j = 0; j < this.getSloupce(); j++) {
                    this.setCislo(pom.getCislo(i, j), i, j);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void odstranRadek(int ktery) {//Odstrani z matice zadany radek(tj. matice ma o jeden radek mene

        if (ktery < 0 || ktery >= this.radky) {// Zkontroluje, zda je zadany radek v matici
            System.out.println("Tento radek je mimo rozsah matice.");
            System.out.println("Zadny radek neni odstranen");
        } else {

            Matice pom = new Matice(this.radky - 1, this.sloupce);//Pomocna matice (o radek mensi nez stavajici
            for (int i = 0; i < this.radky; i++) {//Zkopiruje stavajici matici, mimo zadaneho radku do pomocne
                if (i == ktery) {
                    continue;
                }
                if (i < ktery) {
                    System.arraycopy(this.matice[i], 0, pom.matice[i], 0, this.sloupce);
                } else {
                    System.arraycopy(this.matice[i], 0, pom.matice[i - 1], 0, this.sloupce);
                }
            }
            this.radky--;
            this.matice = new double[this.radky][this.sloupce];//Zmensi souceslou matici o jeden radek
            this.matice = pom.matice;//Zkopiruje pomocnou matici do stavajici
        }
    }

    public int najdiNulovy() {                  //Vrati index nuloveho radku
        boolean jenula;                           //Pokud neni zadny nulovy, vrati -1
        for (int i = 0; i < this.radky; i++) {//Prochazeni matice
            jenula = true;//Indikuje, zda je v radku nejake nenulove cislo
            for (int j = 0; j < this.sloupce; j++) {
                if (matice[i][j] != 0) {//Zmeni indikator ne false pro nenulove cislo
                    jenula = false;
                    break;
                }
            }
            if (jenula == true) {//Pokud je cely radek nulovy vrati jeho index
                return i;
            }

        }
        return -1;//Pokud neni zadny radek nulovy vrati -!
    }

    public void vynasobRadek(double nasobek, int ktery) {  //Vrati matici s danym radkem, vynasobenym danym nasobkem
        for (int j = 0; j < this.sloupce; j++) {
            this.matice[ktery][j] = nasobek * this.matice[ktery][j];
        }
    }

    public void prictiNasobekRadku(double nasobek, int ktereho, int kam) { //Upravy matici prictenim nasobku daneho radku k dalsimu radku
        for (int j = 0; j < this.sloupce; j++) {
            this.matice[kam][j] += nasobek * this.matice[ktereho][j];
        }
    }

    public void prohodRadky(int prvni, int druhy) {//Prohodi dva dane radky
        Matice pom = new Matice(1, this.sloupce);
        for (int j = 0; j < this.sloupce; j++) {
            pom.matice[1][j] = this.matice[prvni][j];
            this.matice[prvni][j] = this.matice[druhy][j];
            this.matice[druhy][j] = pom.matice[1][j];
        }
    }

    public void gauss() throws Exception {//Upravy matici do doniho trojuhelnikoveho tvaru s 1 na hlavni diagonale

        try {

            for (int i = 0; i < this.radky; i++) {//Prochazeni matice

                if (i == 0) {
                    while (najdiNulovy() != -1) {//Hleda a odstranuje nulove radky
                        odstranRadek(najdiNulovy());
                    }
                }


                for (int k = 1; k + i < this.radky; k++) {//Pokud je na hlavni diagonale 0, prohodi tento radek s nasledujicim
                    if (this.matice[i][i] == 0) {
                        prohodRadky(i, k + i);
                    } else {
                        break;
                    }
                }

                if (this.matice[i][i] != 1) {//Upravy radek tak, aby na hlavni diagonale byla 1
                    vynasobRadek(1 / this.matice[i][i], i);

                }

                for (int j = 1; i + j < this.radky; j++) {//Upravy kazdy radek pod stavajicim tak, ze pod prvnim nenulovym cislem na stavajicim radku je 0
                    prictiNasobekRadku(-this.matice[i + j][i], i, i + j);
                }

                while (najdiNulovy() != -1) {//Hleda a odstranuje nulove radky
                    odstranRadek(najdiNulovy());

                }
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean stavInversni() throws Exception {
        try {
            if (this.determinant() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Matice inversni() throws Exception {//Vrati k dane matici matici inversni

        try {
            Matice pom = new Matice(this.radky, this.sloupce);//Vytvori pomocnou matici
            for (int i = 0; i < this.radky; i++) {
                System.arraycopy(this.matice[i], 0, pom.matice[i], 0, this.sloupce);
            }
            Matice invers = new Matice(this.radky, this.sloupce);//Vytvori jednotkovou matici a pomoci GEM ji upravy na inversni k zadane matici
            for (int i = 0; i < invers.radky; i++) {
                for (int j = 0; j < invers.sloupce; j++) {
                    if (i == j) {
                        invers.matice[i][j] = 1;
                    } else {
                        invers.matice[i][j] = 0;
                    }
                }
            }
            for (int dvakrat = 0; dvakrat < 2; dvakrat++) {//Upravy Gaussem, transponuje, znovu upravi Gaussem a znovu transponuje
                for (int i = 0; i < pom.radky; i++) {//Upravy stejne jako v Gauss(), zaroven upravuje jednotkovou matici stejnymi upravami
                    for (int k = 1; k + i < pom.radky; k++) {
                        if (pom.matice[i][i] == 0) {
                            invers.prohodRadky(i, k + i);
                            pom.prohodRadky(i, k + i);
                        } else {
                            break;
                        }
                    }
                    if (pom.matice[i][i] != 1) {
                        invers.vynasobRadek(1 / pom.matice[i][i], i);
                        pom.vynasobRadek(1 / pom.matice[i][i], i);
                    }
                    for (int j = 1; i + j < pom.radky; j++) {
                        invers.prictiNasobekRadku(-pom.matice[i + j][i], i, i + j);
                        pom.prictiNasobekRadku(-pom.matice[i + j][i], i, i + j);
                    }
                }
                invers.transponuj();
                pom.transponuj();
            }
            return invers;
        } catch (Exception e) {
            throw e;
        }

    }

    public void ulozMatici(String nazev) throws IOException {
        FileOutputStream fos = new FileOutputStream(nazev + ".txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeInt(this.radky);//Ulozi Matici jako dva Integery (Sloupce a Radky) a Sloupce*Radky Doubleu
        dos.writeInt(this.sloupce);

        for (int i = 0; i < this.radky; i++) {
            for (int j = 0; j < this.sloupce; j++) {
                dos.writeDouble(this.matice[i][j]);
            }
        }

        dos.close();
        fos.close();
    }

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
            System.out.println("Soubor neexistuje.");
            throw e;
        }

    }
}
