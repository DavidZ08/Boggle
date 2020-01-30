/**
 * La classe Fenetre est la partie «Vue» du patron observateur qui contrôle ce programme.
 * Elle contient les compsantes graphique, comme les JButtons,JMenus,etc.
 * Elle n'effectue aucun calcul.
 */
package ca.qc.bdeb.info203.Vue;

import ca.qc.bdeb.info203.Modele.Modele;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author 1679219
 */
public class Fenetre extends JFrame implements Observer {

    /**
     * pnlPrincipal est le panneau qui regroupe tous les autres panneaux.
     */
    private JPanel pnlPrincipal = new JPanel(new BorderLayout());
    /**
     * pnlAireJeu est le panneau qui contient les JButtons qui représentent les
     * dés.
     */
    private JPanel pnlAireJeu = new JPanel(new GridLayout(4, 4));
    /**
     * pnlTempsScore est le panneau qui contient le décompte et le score.
     */
    private JPanel pnlTempsScore = new JPanel(new FlowLayout(FlowLayout.CENTER));
    /**
     * pnlValidations est le panneau qui contient le bouton d'annulation de
     * saisie et le bouton pour envoyer le mot saisi à la classe Modele.
     */
    private JPanel pnlValidations = new JPanel(new GridLayout(1, 2));
    /**
     * pnlMotSaisis est le panneau qui contient le mot en cours de saisissement.
     */
    private JPanel pnlMotsSaisis = new JPanel(new BorderLayout());

    /**
     * mnuBar est le JMenuBar qui contient les JMenu Fichier et Aide.
     */
    private JMenuBar mnuBar = new JMenuBar();
    /**
     * mnuFichier est le JMenu qui contient les JMenus Nouvelle partie, Options.
     * et Quitter
     */
    private JMenu mnuFichier = new JMenu("Fichier");
    /**
     * mnuAide est le JMenu qui contient le JMenuItem mnuAPropos.
     */
    private JMenu mnuAide = new JMenu("Aide");
    /**
     * mnuOptions est le JMenu qui contient les JMenuItem mnuOptions1
     * mnuOptions2 mnuOptions3 mnuOptions4 qui permettent de changer les
     * colonnes et les rangées.
     */
    private JMenu mnuOptions = new JMenu("Options");
    /**
     * mnuNouvellePartie est le JMenuItem qui permet de réinitialiser la partie.
     */
    private JMenuItem mnuNouvellePartie = new JMenuItem("Nouvelle partie");
    /**
     * mnuQuitter est le JMenuItem qui permet de quitter le programme.
     */
    private JMenuItem mnuQuitter = new JMenuItem("Quitter");
    /**
     * mnuOption1 est le JMenuItem qui permet de changer la configuration à 4x4.
     */
    private JMenuItem mnuOption1 = new JMenuItem("4 x 4");
    /**
     * mnuOption1 est le JMenuItem qui permet de changer la configuration à 5x5.
     */
    private JMenuItem mnuOption2 = new JMenuItem("5 x 5");
    /**
     * mnuOption1 est le JMenuItem qui permet de changer la configuration à 6x6.
     */
    private JMenuItem mnuOption3 = new JMenuItem("6 x 6");
    /**
     * mnuOption1 est le JMenuItem qui permet de changer la configuration à 7x7.
     */
    private JMenuItem mnuOption4 = new JMenuItem("7 x 7");
    /**
     * mnuAPrpos est le JMenuItem qui permet de montrer des informations sur
     * l'auteur.
     */
    private JMenuItem mnuAPropos = new JMenuItem("À propos");

    /**
     * lblTemps est le JLabel qui montre le temps restant à l'utilisateur.
     */
    private JLabel lbltemps = new JLabel("");
    /**
     * lblScore est le JLabel qui montre le score à l'utilisateur.
     */
    private JLabel lblScore = new JLabel("Score");
    /**
     * lblMotEnSaisie est le JLabel qui montre le mot en saisie à l'utilisateur.
     */
    private JLabel lblMotEnSaisie = new JLabel("");

    /**
     * btnEnvoyer est le JButton qui permet d'envoyer le mot saisi à la classe.
     * Modele
     */
    private JButton btnEnvoyer = new JButton("Envoyer");
    /**
     * btnAnnuler est le JButton qui permet d'annuler la saisie d'un mot.
     */
    private JButton btnAnnuler = new JButton("Annuler");

    /**
     * modele est l'instance unique de la classe Modele dans la classe.
     * Fenêtre(Vue)
     */
    private Modele modele;

    /**
     * colonnes est le nombre de colonnes que le jeu a initialement.
     */
    private int colonnes = 4;
    /**
     * rangees est le nombre de rangées que le jeu a initialement.
     */
    private int rangees = 4;

    /**
     * listeMots est le DefaultListModel que la JList modeleList utilisera.
     */
    private DefaultListModel listeMots = new DefaultListModel();
    /**
     * modeleList est la JList que le JScrollPane scrPaneMots utilisera.
     */
    private JList modeleListe = new JList(listeMots);
    /**
     * scrPaneMot est le JScrollPane qui fera en sorte qu'on puisse dérouler la
     * JList modeleList.
     */
    private JScrollPane scrPaneMots = new JScrollPane(modeleListe);

    /**
     * listeBoutons est l'ArrayList de JButton qui contient les JButton créés
     * pour le jeu.
     */
    private ArrayList<BoutonEvolue> listeBoutons = new ArrayList<>();

    /**
     * mat[][] est la matrice de BoutonEvolue qui permetteras de retrouver la positions des boutons dans la matrice.
     */
    private BoutonEvolue[][] mat = new BoutonEvolue[colonnes][rangees];

    /**
     * Ceci est le constructeur qui permet de construire la fenêtre et d'interréagir avec elle.
     * @param modele l'instance de la classe Modele qu'on donne au constructeur pour établir le lien entre la «Vue» et le «Modèle»
     * @throws HeadlessException .
     */
    public Fenetre(Modele modele) throws HeadlessException {
        this.modele = modele;
        modele.addObserver(this);
        this.setTitle("Boggle");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mnuFichier.add(mnuNouvellePartie);
        commencerNouvellePartie();

        mnuOptions.add(mnuOption1);
        actionOption1(modele);

        mnuOptions.add(mnuOption2);
        actionOption2(modele);

        mnuOptions.add(mnuOption3);
        actionOption3(modele);

        mnuOptions.add(mnuOption4);
        actionOption4(modele);

        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        quitter();

        mnuBar.add(mnuFichier);
        mnuAide.add(mnuAPropos);
        montrerInfos();
        mnuBar.add(mnuAide);
        this.setJMenuBar(mnuBar);
        pnlAireJeu = creerAireJeu(creerBoutons(4, 4), 4, 4, pnlAireJeu);
        pnlPrincipal.add(pnlAireJeu);

        modele.getMinuteur().start();
        pnlTempsScore.add(lbltemps);
        pnlTempsScore.add(lblScore);

        pnlPrincipal.add(pnlTempsScore, BorderLayout.NORTH);

        pnlValidations.add(btnAnnuler);
        annulerSaisie();
        pnlValidations.add(btnEnvoyer);
        enregistrerMot();

        pnlPrincipal.add(pnlValidations, BorderLayout.SOUTH);

        scrPaneMots.setPreferredSize(new Dimension(200, 700));
        pnlMotsSaisis.add(lblMotEnSaisie, BorderLayout.NORTH);
        pnlMotsSaisis.add(scrPaneMots, BorderLayout.CENTER);
        pnlPrincipal.add(pnlMotsSaisis, BorderLayout.EAST);
        this.add(pnlPrincipal);

        this.setVisible(true);
    }

    /**
     * Permet d'annuler la saisie d'un mot en cliquant sur le JButton btnAnnuler
     */
    private void annulerSaisie() {
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < colonnes * rangees; i++) {
                    if (!listeBoutons.get(i).isEnabled()) {
                        listeBoutons.get(i).setEnabled(true);
                    }
                }
                modele.reinitialiserSaisie();
            }
        });
    }

    /**
     * Permet de commencer une nouvelle partie en cliquant sur le JMenuItem
     * mnuNouvellePartie (Fichier>Nouvelle Partie).
     */
    private void commencerNouvellePartie() {
        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                reinitialiser();
            }
        });
    }

    /**
     * Permet de montrer des informations sur l'auteur en cliquant sur le
     * JMenuItem mnuAPropos (Aide>À Propos).
     */
    private void montrerInfos() {
        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mnuAPropos, "David Molina, 15 Octobre 2017", "Informations", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Permet de fermer le programme en cliquant sur le JMenuItem mnuQuitter
     * (Fichier>Quitter).
     */
    private void quitter() {
        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Permet de changer les colonnes et les rangées du jeu à 4x4 en cliquant
     * sur le JMenuItem mnuOption1 (Fichier>Options>4x4). Cette méthode change
     * les colonnes et les rangées dans la classe Modele. Par la suite, elle
     * crée le nombre de boutons approprié. Finalement, elle met ces boutons
     * dans le nouveau pnlAireJeu.
     *
     * @param modele l'instance de la classe Modele.
     */
    private void actionOption1(Modele modele) {
        mnuOption1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(mnuOption1, "En êtes-vous sûr?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    modele.changerColonnesRangeesModele(4, 4);
                    passerANouvellePartie();
                }
            }
        });
    }

    /**
     * Permet de passer à une nouvelle partie après avoir changer les colonnes
     * et les rangées.
     */
    private void passerANouvellePartie() {
        mat = new BoutonEvolue[colonnes][rangees];
        listeBoutons = creerBoutons(modele.getColonnes(), modele.getRangees());
        creerAireJeuNouveau(listeBoutons, modele.getColonnes(), modele.getRangees(), pnlAireJeu);
        modele.resetTemps();
    }

    /**
     * Permet de changer les colonnes et les rangées du jeu à 5x5 en cliquant
     * sur le JMenuItem mnuOption1 (Fichier>Options>5x5). Cette méthode change
     * les colonnes et les rangées dans la classe Modele. Par la suite, elle
     * crée le nombre de boutons approprié. Finalement, elle met ces boutons
     * dans le nouveau pnlAireJeu.
     *
     * @param modele l'instance de la classe Modele.
     */
    private void actionOption2(Modele modele) {
        mnuOption2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(mnuOption2, "En êtes-vous sûr?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    modele.changerColonnesRangeesModele(5, 5);
                    passerANouvellePartie();
                }
            }
        });
    }

    /**
     * Permet de changer les colonnes et les rangées du jeu à 6x6 en cliquant
     * sur le JMenuItem mnuOption1 (Fichier>Options>6x6). Cette méthode change
     * les colonnes et les rangées dans la classe Modele. Par la suite, elle
     * crée le nombre de boutons approprié. Finalement, elle met ces boutons
     * dans le nouveau pnlAireJeu.
     *
     * @param modele l'instance de la classe Modele.
     */
    private void actionOption3(Modele modele) {
        mnuOption3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(mnuOption3, "En êtes-vous sûr?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    modele.changerColonnesRangeesModele(6, 6);
                    passerANouvellePartie();
                }
            }
        });
    }

    /**
     * Permet de changer les colonnes et les rangées du jeu à 7x7 en cliquant
     * sur le JMenuItem mnuOption1 (Fichier>Options>7x7). Cette méthode change
     * les colonnes et les rangées dans la classe Modele. Par la suite, elle
     * crée le nombre de boutons approprié. Finalement, elle met ces boutons
     * dans le nouveau pnlAireJeu.
     *
     * @param modele l'instance de la classe Modele.
     */
    private void actionOption4(Modele modele) {
        mnuOption4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(mnuOption4, "En êtes-vous sûr?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == 0) {
                    modele.changerColonnesRangeesModele(7, 7);
                    passerANouvellePartie();
                }
            }
        });
    }

    /**
     * Crée les boutons du jeu.
     *
     * @param colonnes les colonnes voulues.
     * @param rangees les rangées voulues.
     * @return la liste de boutons.
     */
    private ArrayList<BoutonEvolue> creerBoutons(int colonnes, int rangees) {
        for (int i = 0; i < colonnes * rangees; i++) {
            BoutonEvolue btnBouton = new BoutonEvolue();
            btnBouton.setText(modele.creerLettreString());
            bloquerBouton(btnBouton);
            saisirLettre(btnBouton);
            listeBoutons.add(btnBouton);
        }
        return listeBoutons;
    }

    /**
     * Permet de bloquer un bouton après l'avoir cliqué.
     *
     * @param btnBouton le bouton cliqué.
     */
    private void bloquerBouton(BoutonEvolue btnBouton) {
        btnBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBouton.setEnabled(false);
                modele.verfierSiAdjacent(btnBouton.getcolonnes(), btnBouton.getrangees());
                changerAdjacence();
            }
        });
    }

    /**
     * Cette méthode permet d'activer les boutons qui sont adjacents au bouton cliqué et, elle désactive ceux qui ne sont pas adjacents.
     */
    private void changerAdjacence() {
        boolean[][] matTemp = modele.getMatBool();
        for (int i = 0; i < colonnes; i++) {
            for (int j = 0; j < rangees; j++) {
                mat[i][j].setEnabled(matTemp[i][j]);
            }
        }
    }

    /**
     * Crée l'aire de jeu initiale en déversant une ArrayList de BoutonEvolue dans une matrice de BoutonEvolue.
     *
     * @param listeBoutons la liste de boutons.
     * @param colonnes les colonnes voulues.
     * @param rangees les rangees voulues.
     * @param pnlAireJeu l'aire de jeu.
     * @return le panneau d'aire de jeu.
     */
    private JPanel creerAireJeu(ArrayList<BoutonEvolue> listeBoutons, int colonnes, int rangees, JPanel pnlAireJeu) {
        this.mat = new BoutonEvolue[colonnes][rangees];
        int k = 0;
        for (int i = 0; i < colonnes; i++) {
            for (int j = 0; j < rangees; j++) {
                this.mat[i][j] = listeBoutons.get(k);
                this.mat[i][j].setRangeesColonnes(i, j);
                k++;
                this.pnlAireJeu.add(mat[i][j]);
            }
        }
        return pnlAireJeu;
    }

    /**
     * Crée la nouvelle aire de jeu (après avoir changé les colonnes et rangées) en déversant une ArrayList de BoutonEvolue dans une matrice de BoutonEvolue.
     *
     * @param listeBoutons la liste de boutons.
     * @param colonnes les colonnes voulues.
     * @param rangees les rangées voulues.
     * @param pnlAireJeu le panneau d'aire de jeu.
     */
    private void creerAireJeuNouveau(ArrayList<BoutonEvolue> listeBoutons, int colonnes, int rangees, JPanel pnlAireJeu) {
        this.pnlAireJeu.removeAll();
        this.pnlAireJeu.setLayout(new GridLayout(colonnes, rangees));
        this.mat = new BoutonEvolue[colonnes][rangees];
        int k = 0;
        for (int i = 0; i < colonnes; i++) {
            for (int j = 0; j < rangees; j++) {
                this.mat[i][j] = listeBoutons.get(k);
                this.mat[i][j].setRangeesColonnes(i, j);
                k++;
                this.pnlAireJeu.add(mat[i][j]);
            }
        }
        this.pnlAireJeu.revalidate();
        this.pnlAireJeu.repaint();
    }

    /**
     * Change le temps sur le JLabel lblTemps et quand il atteint 0, il offre un
     * choix à l'utilisateur.
     */
    private void changerTemps() {
        this.lbltemps.setText(modele.getRebours());
        if (modele.getTemps() == -1) {
            modele.resetTemps();
            int reponse = JOptionPane.showConfirmDialog(pnlTempsScore, "Le compte à rebours est terminé! \n oui pour recommencer \n non pour quitter", "Avertissment", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (reponse == JOptionPane.YES_OPTION) {
                reinitialiser();
            }
            if (reponse == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
    }

    /**
     * Change les colonnes et les rangées de la classe Fenetre en prenant celles
     * de la classe Modele
     */
    private void changerColonnesRangees() {
        this.colonnes = modele.getColonnes();
        this.rangees = modele.getRangees();
    }

    /**
     * Change le texte dans les boutons et les prépare pour une autre partie.
     *
     * @param listeBoutons
     */
    private void changerBoutons(ArrayList<BoutonEvolue> listeBoutons) {
        for (int i = 0; i < colonnes * rangees; i++) {
            listeBoutons.get(i).setText(modele.creerLettreString());
            listeBoutons.get(i).setEnabled(true);
        }
        for (int i = 0; i < colonnes * rangees; i++) {
            bloquerBouton(listeBoutons.get(i));
        }
    }

    /**
     * Change le JLabel lblMotEnSaisie à partir du mot en saisie dans la classe
     * Modele.
     */
    private void changerMotEnSaisie() {
        lblMotEnSaisie.setText(modele.getMotSaisi());
    }

    /**
     * Ajoute un mot dans la DefaultListModel listeMots à partir du mot saisie
     * dans la classe Modele.
     */
    private void changerListeMots() {
        this.listeMots.addElement(modele.getMotSaisi());
    }

    /**
     * Réinitialise la partie(temps,score,texte dans les boutons et liste de
     * mots trouvés).
     */
    private void reinitialiser() {
        modele.reinitialiser();
        changerBoutons(listeBoutons);
        listeMots.clear();
    }

    /**
     * Mets à jour les composantes graphique à partir des informations de la
     * classe Modele.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        changerTemps();
        changerColonnesRangees();
        changerMotEnSaisie();
        changerScore();
    }

    /**
     * Envoie la lettre saisie au Modele.
     *
     * @param btnBouton le bouton cliqué.
     */
    private void saisirLettre(BoutonEvolue btnBouton) {
        btnBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                modele.ajouterLettre(btnBouton.getText());
            }
        });
    }

    /**
     * Cette méthode vérifie si ce mot n'a pas déjà été enregistré, Si il n'a
     * pas été enregistré, la méthode vérifie la validité du mot, l'enregistre
     * et compte les points.Si c'est un mot inconnu, il vérifie sa validité et
     * offre de l'écrire dans le dictionnaire. Si il a été enregistré, la
     * méthode annule la saisie. Finalement, la méthode débloque les boutons
     * bloqués durant la saisie.
     */
    private void enregistrerMot() {
        btnEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!modele.verfierMotListe(lblMotEnSaisie.getText())) {
                    if (modele.verifierValidite(lblMotEnSaisie.getText())) {
                        modele.enregistrerMot(lblMotEnSaisie.getText());
                        changerListeMots();
                        modele.compterPoints(lblMotEnSaisie.getText());
                        modele.reinitialiserSaisie();
                    } else {
                        modele.ecritureDictionnaire(lblMotEnSaisie.getText());
                        if (!"".equals(modele.getMotSaisi())) {
                            changerListeMots();
                            modele.compterPoints(lblMotEnSaisie.getText());
                            modele.reinitialiserSaisie();
                        }
                    }
                } else {
                    JOptionPane.showConfirmDialog(pnlPrincipal, "Vous avez déjà entré ce mot", "Avertissement", JOptionPane.WARNING_MESSAGE);
                    modele.reinitialiserSaisie();
                    annulerSaisie();
                }
                for (int i = 0; i < colonnes * rangees; i++) {
                    if (!listeBoutons.get(i).isEnabled()) {
                        listeBoutons.get(i).setEnabled(true);
                    }

                }
            }
        });
    }

    /**
     * Cette méthode change le JLabel lblScore à partir du score du Modele.
     */
    private void changerScore() {
        this.lblScore.setText("Score: " + Integer.toString(modele.getPoints()));
    }

}
