/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

/**
 *
 * @author Jarda
 */
public class Matice {// Trida reprezentujici jednotlive matice a metody, ktere lze s maticemi provadet
// Oznacene radky je treba prepsat pro pouziti v grafickem prostredi    
    
    private int radky;// Pocet radku matice
    private int sloupce;// Pocet sloupcu matice
    private double[][] matice;// Reprezentace samotne matice 

    public Matice(int radky, int sloupce) {//Vytvori matici o zadanem poctu radku a sloupcu, pokud jsou tyto hodnoty zaporne, tak jejich absolutni hodnotu
        this.radky = Math.abs(radky);
        this.sloupce = Math.abs(sloupce);
        this.matice = new double[this.radky][this.sloupce];
    }
    
    public void setCislo(double cislo, int radek, int sloupec) {//Nastavi cislo na zadane souradnice
        if ((radek < 0 || radek >= this.radky) || (sloupec < 0 || sloupec >= this.sloupce)) {
            System.out.println("Zadana pozice je mimo rozsah.");
        } else {
            this.matice[radek][sloupec] = cislo;
        }
    }
    
    public int getRadky() {//Vrati pocet radku matice
        return this.radky;
    }

    public int getSloupce() {//Vrati pocet sloupcu matice
        return this.sloupce;
    }

    public double getCislo(int radek, int sloupec) {//Vrati cislo na zadane pozici
        if (radek < 0 || radek >= this.radky || sloupec < 0 || sloupec >= this.sloupce) {//Pokud je zadana pozice mimo matici vrati NaN a vypise chybovou hlasku
            System.out.println("Zadana pozice je mimo rozsah matice.");
            return (double)0/0;
        }
        return this.matice[radek][sloupec];
    }
    
    public Matice getMatice() {// Vrati kompetni matici
        return this;
    }
    
    public String toString(int desMista) {//Prevede matici na String
        String s = new String();
        if (this.radky == 0 || this.sloupce == 0) {//Pokud ma matice nulovy pocet radku, nebo sloupcu vypise se chybova hlaska
           s = "Nic k vypsani";
           return s;
        } else {
            for (int i = 0; i < this.radky; i++) {//Prochazi matici
                for (int j = 0; j < this.sloupce; j++) {
                    s += String.format("%."+desMista+"f", this.matice[i][j]);
                    if(j++ < this.sloupce){// Jeslize je cislo posledni na redku nevypise se za nim mezera
                        s += " ";
                    }
                }
                s += String.format("%n");//Odradkuje radek matice
            }
     return s;
        }
    }
    
    public boolean stavScitani(Matice a){// True jestlize jde matice secit jinak false
        if(this.radky == a.radky && this.sloupce == a.sloupce){
            return true;
        }else{
            return false;
        }
    }
    
    public Matice secti(Matice a) {//Secte dve matice
        Matice vysledek = new Matice(this.radky, this.sloupce);
        for (int i = 0; i < this.radky; i++) {//Prochazi matice a scita jejich cleny
            for (int j = 0; j < this.radky; j++) {
                vysledek.matice[i][j] = this.matice[i][j] + a.matice[i][j];
            }
        }
        return vysledek;
    }
    
    public boolean stavNasobeni(Matice a){
        if(this.sloupce == a.radky){
            return true;
        }else{
            return false;
        }
    }
    
    public Matice vynasobZprava(Matice a) {//Vynasobi matici Matici v argumentu zprava
        Matice vysledek = new Matice(this.radky, a.sloupce);//Vysledna matice
        double mezivysledek = 0;//Pomocna promena
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
    }
    
    public double determinant() {//Vrati determinant matice, pro singularni, nebo obdelnikovou matici vrati 0
        double determinant = 0;
        double pom;//pomocna promena
        double pamet0;//pomocna promena
        double pamet1;//pomocna promena
        double pamet2;//pomocna promena

        if ((this.radky != this.sloupce) || this.radky == 0 || this.sloupce == 0) {//Pokud je matice obdelnikoa, nebo s nulovym poctem radku nebo sloupcu vrati 0
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
    }
    
    public void transponuj() {//Ztransponuje matici
        Matice pom = new Matice(this.sloupce, this.radky);//Vytvori pomocnou matici
        int pom1;
        for (int i = 0; i < this.radky; i++) {//Pomocnou matici nastavi jako transponovanou k zadane matici
            for (int j = 0; j < this.sloupce; j++) {
                pom.matice[j][i] = this.matice[i][j];
            }
        }
        pom1 = this.radky;//Prehodi hodnotu radku a sloupcu v transponovane matici
        this.radky = this.sloupce;
        this.sloupce = pom1;
        this.matice = new double[pom.getRadky()][pom.getSloupce()];
        for(int i=0;i<this.getRadky();i++){// Nastavi pomocnou jako matici this
            for(int j=0;j<this.getSloupce();j++){
                this.setCislo(pom.getCislo(i, j), i, j);
            }
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

    public void gauss() {//Upravy matici do doniho trojuhelnikoveho tvaru s 1 na hlavni diagonale


        
        for (int i = 0; i < this.radky; i++) {//Prochazeni matice

            if(i==0){
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

    }
    
    public boolean stavInversni(){
        if(this.determinant() == 0){
            return false;
        }else{
            return true;
        }
    }
    
    public Matice inversni() {//Vrati k dane matici matici inversni
        
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
        }
    
    
}
