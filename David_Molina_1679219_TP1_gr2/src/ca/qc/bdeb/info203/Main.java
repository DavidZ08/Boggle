/**
 * Ceci est la classe «Main» du programme, elle s'occupe d'éxécuter les commandes.
 */
package ca.qc.bdeb.info203;

import ca.qc.bdeb.info203.Modele.Modele;
import ca.qc.bdeb.info203.Vue.Fenetre;

/**
 *
 * @author 1679219
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Modele modele = new Modele();
        Fenetre fenetre = new Fenetre(modele);
    }
    
}
