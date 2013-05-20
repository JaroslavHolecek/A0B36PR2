/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maticovy_kalkulator;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Jarda
 */
public class Tabulka extends JTable {//Tabulka upravena pro potreby kalkulatoru

    int sloupce;
    int radky;
    TableModel tm;
    int j;

    public Tabulka(TableModel tm, int j) {
        super(tm);
        this.tm = tm;
        this.j = j;
        sloupce = tm.getColumnCount();
        radky = tm.getRowCount();
        this.setPreferredScrollableViewportSize(new Dimension(180, 175));
        this.setAutoResizeMode(AUTO_RESIZE_OFF);
        this.setBackground(Color.WHITE);
        this.setShowGrid(true);
        this.getTableHeader().setReorderingAllowed(false);
        this.setTableHeader(null);
        if (j == 0) {
            this.setRowHeight(30);

            for (int i = 0; i < sloupce; i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(30);
            }
        } else {
            this.setRowHeight(30);

            for (int i = 0; i < sloupce; i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(60);
            }
        }
    }

    public Matice getMatice(int radky, int sloupce) {//Vraci matici, ktera je ulozena v tabulce

        Matice m = new Matice(radky, sloupce);
        double cislo;
        for (int i = 0; i < radky; i++) {
            for (int k = 0; k < sloupce; k++) {

                cislo = Double.parseDouble(this.tm.getValueAt(i, k).toString());
                m.setCislo(cislo, i, k);
            }
        }
        return m;
    }

    public void setMatice(Matice m) {//Zapise matici do tabulky
        double cislo;
        Object o;

        for (int i = 0; i < m.getRadky(); i++) {
            for (int k = 0; k < m.getSloupce(); k++) {
                cislo = m.getCislo(i, k);
                o = (Object) cislo;
                this.tm.setValueAt(o, i, k);
            }
        }

    }
}
