/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;

/**
 *
 * @author Jarda
 */
public class Tabulka extends JTable {
    
    
    public Tabulka (int radky, int sloupce){
        super(radky, sloupce);
        this.setRowHeight(30);
        this.setPreferredScrollableViewportSize(new Dimension(200, 200));
        
        for(int i = 0; i < sloupce; i++){
            this.getColumnModel().getColumn(i).setWidth(30);
        }
        
        this.setAutoResizeMode(AUTO_RESIZE_OFF);
        this.setBackground(Color.WHITE);
        this.setShowGrid(true);
       
    
    }
    
}
