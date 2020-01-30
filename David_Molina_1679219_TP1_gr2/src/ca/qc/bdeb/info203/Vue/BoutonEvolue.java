/*
 *Ceci une classe qui hérite de la classe JButton.
 * Cette classe permet de retenir les coordonnées des boutons.
 */
package ca.qc.bdeb.info203.Vue;

import javax.swing.JButton;

/**
 *
 * @author 1679219
 */
public class BoutonEvolue extends JButton {

    /**
     * la coordonnée en x.
     */
    private int x;
    /**
     * la coordonnée en y.
     */
    private int y;

    /**
     * Le constructeur de BoutonEvolue.
     */
    public BoutonEvolue() {
    }

    /**
     * 
     * @return la coordonnée en x.
     */
    public int getrangees() {
        return x;
    }

    /**
     * 
     * @return la coordonnée en y.
     */
    public int getcolonnes() {
        return y;
    }

    /**
     * Permet de donner des coordonnées au boutons.
     * @param x la coordonnée en x.
     * @param y  la coordonnée en y.
     */
    public void setRangeesColonnes(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
