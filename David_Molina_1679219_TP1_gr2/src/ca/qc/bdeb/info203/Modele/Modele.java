/**
 * La classe Modele est la partie «Modèle» du patron observateur qui contrôle le programme.
 * Il n'y a aucune compsante graphique dans cette classe, à part les JOptionPane.
 * Cette classe s'occupe des calculs et de très peu de messages graphiques.
 */
package ca.qc.bdeb.info203.Modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author 1679219
 */
public class Modele extends Observable {

    /**
     * temps alloué pour trouver les mots.
     */
    private int temps = 180;
    /**
     * le temps en format 0:00.
     */
    private String rebours = " : ";
    /**
     * le nombre de lettres dans l'alphabet.
     */
    private static final double LETTRESALAPHABET = 26;
    /**
     * le nombre de colonnes initiales.
     */
    private int colonnes = 4;
    /**
     * le nombre de rangées initiales.
     */
    private int rangees = 4;
    /**
     * le mot saisi.
     */
    private String motSaisiTotal = "";
    /**
     * la liste de mots
     */
    private DefaultListModel<String> listeMots = new DefaultListModel<String>();
    /**
     * le nomre de points inital.
     */
    private int points = 0;
    /**
     * vérificateur si le mot exsite dans le dictionnaire.
     */
    private boolean verificateur = false;
    /**
     * vérificateur si le mot n'as pas déjà été enregistré.
     */
    private boolean siCopie = false;

    /**
     * matBool[][] est la matrice de booléens qui nous permettera de changer l'état des boutons dans la classe «Fenetre» (la partie «Vue»).
     */
    private boolean[][] matBool = new boolean[this.colonnes][this.rangees];

    public Modele() {
    }

    /**
     * Timer qui fait un décompte jusqu'à 0.
     */
    private Timer tmrMinuteur = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int minutes = temps / 60;
            int secondes = temps % 60;
            rebours = String.format("%d : %02d", (minutes), (secondes));
            temps--;
            maj();
            if (temps == -1) {
                tmrMinuteur.stop();
            }
        }
    });

    /**
     * Mise à jour des informations
     */
    private void maj() {
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @return le temps en format 0:00.
     */
    public String getRebours() {
        return rebours;
    }

    /**
     *
     * @return le minuteur
     */
    public Timer getMinuteur() {
        return tmrMinuteur;
    }

    /**
     *
     * @return le temps restant.
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Génère un chiffre aléatoire double.
     *
     * @param min minimum voulu.
     * @param max maximum voulu.
     * @return le chiffre alétoire entre le minimum et le maximum.
     */
    private double genererNombreAleatoireDouble(double min, double max) {
        double r = Math.random() * (max - min);
        return r;
    }

    /**
     * Génère un chiffre aléatoire int.
     *
     * @param min minimum voulu.
     * @param max maximum voulu.
     * @return le chiffre aléatoire entre le minimum et maximum.
     */
    private int genererNombreAleatoireInt(double min, double max) {
        int r = (int) (Math.random() * (max - min) + 1);
        return r;
    }

    /**
     * Génère un lettre avec des probabilités différentes.
     *
     * @return la lettre créée.
     */
    public String creerLettreString() {
        String s = null;
        //Crée une variable temporaire pour la comparer à chacun des ifs
        double temp = genererNombreAleatoireDouble(0, LETTRESALAPHABET);
        //début de la range && fin de la range  e.g (0 à pourcentageDecimal(LETTRESALAPHABET, 0.01))
        if (temp < pourcentageDecimal(LETTRESALAPHABET, 0.1)) {
            int choixAleatoire = genererNombreAleatoireInt(1, 3);
            switch (choixAleatoire) {
                case 1: {
                    s = ("K");
                    break;
                }
                case 2: {
                    s = ("W");
                    break;
                }
                default: {
                    s = ("Z");
                    break;
                }
            }
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 0.4)) {
            s = ("Y");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 0.8)) {
            s = ("X");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 1.3)) {
            s = ("J");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 2.0)) {
            s = ("H");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 2.9)) {
            int choixAleatoire2 = genererNombreAleatoireInt(1, 2);
            switch (choixAleatoire2) {
                case 1: {
                    s = ("B");
                    break;
                }
                default: {
                    s = ("G");
                    break;
                }
            }
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 4.0)) {
            s = ("F");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 5.4)) {
            s = ("QU");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 7.0)) {
            s = ("V");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 10.0)) {
            int choixAleatoire3 = genererNombreAleatoireInt(1, 2);
            switch (choixAleatoire3) {
                case 1: {
                    s = ("M");
                    break;
                }
                default: {
                    s = ("P");
                    break;
                }
            }
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 13.4)) {
            s = ("C");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 17.1)) {
            s = ("D");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 22.4)) {
            s = ("O");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 27.9)) {
            s = ("L");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 34.2)) {
            s = ("U");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 40.8)) {
            s = ("R");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 47.9)) {
            s = ("N");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 55.1)) {
            s = ("T");
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 62.9)) {
            int choixAleatoire4 = genererNombreAleatoireInt(1, 2);
            switch (choixAleatoire4) {
                case 1: {
                    s = ("S");
                    break;
                }
                default: {
                    s = ("I");
                    break;
                }
            }
        } else if (temp < pourcentageDecimal(LETTRESALAPHABET, 71.0)) {
            s = ("A");
        } else {
            s = ("E");
        }
        return s;
    }

    /**
     *
     * @param base le chiffre sur lequel on mets un pourcentage.
     * @param pourcentageDecimal le pourcentage décimal (e.g 0.1 = 10%).
     * @return le pourcentage de la base.
     */
    private double pourcentageDecimal(double base, double pourcentageDecimal) {
        return base * (pourcentageDecimal / 100);
    }

    /**
     * Change le nombre de colonnes et rangées de cette classe.
     *
     * @param colonnes le nombre de colonnes voulues.
     * @param rangees le nombre de rangées voulues.
     */
    public void changerColonnesRangeesModele(int colonnes, int rangees) {
        this.colonnes = colonnes;
        this.rangees = rangees;
        this.matBool = new boolean[this.colonnes][this.rangees];
        maj();
    }

    /**
     *
     * @return le nombre de colonnes.
     */
    public int getColonnes() {
        return colonnes;
    }

    /**
     *
     * @return le nombre de rangees.
     */
    public int getRangees() {
        return rangees;
    }

    /**
     * Change le temps à 180 secondes.
     *
     */
    public void resetTemps() {
        this.temps = 180;
    }

    /**
     * Réinitialise le jeu du côté de la classe Modele.
     */
    public void reinitialiser() {
        this.colonnes = 4;
        this.rangees = 4;
        reinitialiserSaisie();
        this.temps = 180;
        this.listeMots.clear();
        this.points = 0;
        maj();
    }

    /**
     * Ajoute des lettres au mot à saisir.
     *
     * @param lettre la lettre à ajouter.
     */
    public void ajouterLettre(String lettre) {
        motSaisiTotal += lettre;
        maj();
    }

    /**
     * Réinitialise la saisie.
     */
    public void reinitialiserSaisie() {
        motSaisiTotal = "";
        maj();
    }

    /**
     *
     * @return le mot saisi.
     */
    public String getMotSaisi() {
        return motSaisiTotal;
    }

    /**
     * Enregistre le mot dans une DefaultListModel.
     *
     * @param mot le mot à enregistrer.
     */
    public void enregistrerMot(String mot) {
        this.listeMots.addElement(mot);
        maj();
    }

    /**
     *
     * @return la liste de mots.
     */
    public DefaultListModel<String> getListeMots() {
        return listeMots;
    }

    /**
     * Vérifie si le mot en paramètre existe dans le dictionnaire. Si il existe,
     * la méthode appelle compterPoints(), une méthode qui compte les points. Si
     * il n'existe pas, la méthode appelle ecritureDictionnaire(), une méthode
     * qui permet l'écriture dans le dicitonnaire.
     *
     * @param motSaisi le mot à vérifier dans le dictionnaire.
     */
    public boolean verifierValidite(String motSaisi) {
        try {
            String s;
            BufferedReader lecteur = new BufferedReader(new FileReader("liste_francais.txt"));
            s = lecteur.readLine();
            motSaisi = motSaisi.toLowerCase();
            try {
                do {
                    if (motSaisi.equals(s) && motSaisi.length() >= 3) {
                        this.verificateur = true;
                    }
                    s = lecteur.readLine();
                } while (s != null);
                lecteur.close();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Problème de lecture du fichier", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Fichier inexistant", "Avertissement", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ioe2) {
            JOptionPane.showMessageDialog(null, "Problème de lecture du fichier", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
        return this.verificateur;

    }

    /**
     * Compte les points marqués avec le .length() d'un mot.
     *
     * @param motSaisi le mot pour compter les points.
     */
    public void compterPoints(String motSaisi) {
        switch (motSaisi.length()) {
            case 0: {
                this.points += 0;
                break;
            }
            case 1: {
                this.points += 0;
                break;
            }
            case 2: {
                this.points += 0;
                break;
            }
            case 3: {
                this.points += 1;
                break;
            }
            case 4: {
                this.points += 1;
                break;
            }
            case 5: {
                this.points += 2;
                break;
            }
            case 6: {
                this.points += 3;
                break;
            }
            case 7: {
                this.points += 5;
                break;
            }
            default: {
                this.points += 11;
                break;
            }
        }
        maj();
    }

    /**
     * Permet d'écrire un mot inconnu de 3 lettres et plus dans le fichier.
     * liste_francais.txt (le dictionnaire).
     *
     * @param motSaisi le mot à écrire dans le dictionnaire.
     */
    public void ecritureDictionnaire(String motSaisi) {
        int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous écrire ce mot dans le dictionnaire?", "Avertissment", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if (motSaisi.length() >= 3 && reponse == JOptionPane.YES_NO_OPTION) {
            try {
                PrintWriter ecrivain = new PrintWriter(new FileWriter("liste_francais.txt", true));
                ecrivain.println(motSaisi);
                ecrivain.close();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Fichier inexistant", "Avertissement", JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Problème de lecture du fichier", "Avertissement", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            reinitialiserSaisie();
        }
    }

    /**
     * Vérifie si un mot est déjà enregistré dans la liste.
     *
     * @param mot le mot à vérifier.
     * @return siCopie, un boolean qui indique la présence du mot à vérifier.
     */
    public boolean verfierMotListe(String mot) {
        for (int i = 0; i < this.listeMots.size(); i++) {
            if (this.listeMots.get(i).equals(mot)) {
                this.siCopie = true;
            } else {
                this.siCopie = false;
            }
        }
        return this.siCopie;
    }

    /**
     *
     * @return les points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Cette méthode permet de détecter les boutons adjacents : prenons les
     * coordonnées (3,1) et (3,2), la soustraction en x donne 0 et en y, elle
     * donne 1. Ce qui signifie que ces deux cases sont adjacentes. Une case
     * peut-être adjacente à un autre si les différents soustractions(en x et en
     * y) donnes ces différences: 0;1, 1;0, 1;1, et 0,0
     *
     * @param colonnes la coordonnée du bouton cliqué en x.
     * @param rangees la coordonnée du bouton cliqué en y.
     */
    public void verfierSiAdjacent(int colonnes, int rangees) {
        remplirMatBool();
        for (int i = 0; i < matBool.length; i++) {
            for (int j = 0; j < matBool.length; j++) {
                if ((Math.abs(rangees - i) == 0) && (Math.abs(colonnes - j) == 1)) {
                    matBool[i][j] = true;
                }
                if ((Math.abs(rangees - i) == 1) && (Math.abs(colonnes - j) == 0)) {
                    matBool[i][j] = true;
                }
                if ((Math.abs(rangees - i) == 1) && (Math.abs(colonnes - j) == 1)) {
                    matBool[i][j] = true;
                }
                if ((Math.abs(rangees - i) == 0) && (Math.abs(colonnes - j) == 0)) {
                    matBool[i][j] = true;
                }
            }
        }
    }

    /**
     * Cette méthode remplit le tableau de booléens en «false» pour éviter les.
     * «NullPointerException(s)»
     */
    private void remplirMatBool() {
        for (int i = 0; i < this.colonnes; i++) {
            for (int j = 0; j < this.rangees; j++) {
                this.matBool[i][j] = false;
            }
        }
    }

    /**
     *
     * @return la matrice de booléens.
     */
    public boolean[][] getMatBool() {
        return matBool;
    }

}
