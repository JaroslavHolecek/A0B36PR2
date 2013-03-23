/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

/**
 *
 * @author Jarda
 */
public class Seznam {
    
    private Prvek prvni = new Prvek();
    private Prvek posledni = new Prvek();
    private int velikost;
    
    private class Prvek {// Trida reprezentujici jednotlive prvky seznamu
        Matice matice;
        Prvek dalsi;
        Prvek predchozi;
        
        public Prvek(){
            prvni.matice = null;
        }
        
        public Prvek(Matice a){
            this.matice = a;
        }
    }
    
    public Seznam(){
        this.velikost = 0;
        
        this.prvni.dalsi = this.posledni;
        this.posledni.predchozi = this.prvni;
    }
    
    public void pridejMatici(Matice a){//pridava matici(Prvek) na konec seznamu
        Prvek novy = new Prvek(a);
        
        novy.predchozi = this.posledni.predchozi;
        novy.dalsi = this.posledni;
        
        velikost++;
    }
    
    public void smazMatici(int ktera){
        Prvek pom1 = prvni;
        Prvek pom2;
        
        for(int i = 0; i < ktera - 1; i++){
            pom1 = pom1.dalsi;
        }
        
        pom2 = pom1.dalsi.dalsi;
        
        pom2.predchozi = pom1;
        pom1.dalsi = pom2;
    }
    
    public int getVelikost(){//Vraci mnozstvi Prvku v seznamu (bez prvniho a posledniho
        return this.velikost;
    }
    
    public Matice getMatice(int ktery) {
        if(ktery > this.velikost || ktery <= 0){
           return new Matice(0,0);            
        
        }
        
        Prvek aktualni = this.prvni;
        
        for(int i = 0; i < ktery; i++){
            aktualni = aktualni.dalsi;
        }
        
        return aktualni.matice;
       
    }
    
}
