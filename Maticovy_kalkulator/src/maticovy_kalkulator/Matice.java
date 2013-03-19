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
    
}
