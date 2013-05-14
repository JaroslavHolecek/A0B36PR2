/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

/**
 *
 * @author Jarda
 */
public class SeznamoveVyjimky extends Exception {
        int pozice;
        Seznam seznam;
        String vypis;
        
    SeznamoveVyjimky(int pozice, Seznam seznam, String vypis){
        this.pozice = pozice;
        this.seznam = seznam;
        this.vypis = vypis;
        }
            
    @Override
    public String toString(){
        return vypis; 
    }
}
    

