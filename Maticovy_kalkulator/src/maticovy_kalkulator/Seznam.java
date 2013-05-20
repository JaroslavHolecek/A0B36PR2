/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

/**
 *
 * @author Jarda
 */
public class Seznam {//Oboustranny seznam - prvky pridava na konec, ale "pocita" zpredu 

    private Prvek prvni = new Prvek();
    private Prvek posledni = new Prvek();
    private int velikost;

    private class Prvek {// Trida reprezentujici jednotlive prvky seznamu

        Matice matice;
        Prvek dalsi;
        Prvek predchozi;

        public Prvek() {
            //posledni.matice = null;
        }

        public Prvek(Matice a) {
            this.matice = a;
        }
    }

    public Seznam() {
        this.velikost = 0;
        this.prvni.dalsi = this.posledni;
        this.posledni.predchozi = this.prvni;
    }

    public int pridejMatici(Matice a) {//pridava matici(Prvek) na konec seznamu
        Prvek novy = new Prvek(a);

        novy.predchozi = this.posledni.predchozi;
        novy.dalsi = this.posledni;
        this.posledni.predchozi.dalsi = novy;
        this.posledni.predchozi = novy;

        velikost++;
        return velikost;
    }

    public int getVelikost() {//Vraci mnozstvi Prvku v seznamu (bez prvniho a posledniho
        return this.velikost;
    }

    public Matice getMatice(int ktera) throws Exception {

        Prvek aktualni = this.prvni;

        try {
            if (ktera < 0) {
                throw new SeznamoveVyjimky(ktera, this, String.format("Matice jsou v seznamu cislovany prirozenymi cisly od 1 %n Vase volba %.0f", ktera));
            } else if (ktera > this.getVelikost()) {
                throw new SeznamoveVyjimky(ktera, this, String.format("Zadana pozice %.0f je mimo rozsah. V seznamu je %.0f matic", ktera, this.getVelikost()));
            } else {
                for (int i = 0; i < ktera; i++) {
                    aktualni = aktualni.dalsi;
                }
                return aktualni.matice;
            }
        } catch (SeznamoveVyjimky a) {
            return new Matice(0, 0);
        }
    }
}
