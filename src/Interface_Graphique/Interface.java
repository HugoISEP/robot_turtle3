import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Interface extends JFrame {

    //Creation d'un faux pour interface tableau
    private int[][] gameBoard;
    private boolean CliqueSouris = true;

    //Recuperation des données utiles
    private Dimension dimensionEcran = Toolkit.getDefaultToolkit().getScreenSize();

    //Creation des différentes feneteres
    private Fenetre accueil = new Fenetre("Fenetre d'accueil", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight(), "Accueil.jpg");
    private Fenetre RegleDuJeu = new Fenetre("Regle du Jeu", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight(), "Accueil.jpg");
    private Fenetre principal = new Fenetre("Fenetre Principal", (int) dimensionEcran.getWidth(), (int) dimensionEcran.getHeight(), "Principal.jpeg");
    private Fenetre NombreJoueurs = new Fenetre("Nombre de joueurs", (int) dimensionEcran.getHeight()/2, (int) dimensionEcran.getHeight()/2, "fond.jpg");
    private Fenetre ChoisirCoup = new Fenetre("Choisir son mouvement", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight() /2, "ImageFond.jpg");
    private Fenetre Defausse = new Fenetre("Defausser des cartes", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight()/2, "ImageFond.jpg");
    private Fenetre Victoire = new Fenetre("Victoire", (int) dimensionEcran.getHeight()/2, (int) dimensionEcran.getHeight()/2, "TortueDab.jpg");

    //Creation des boutons
    private Bouton Play = new Bouton("Jouer", 25 * accueil.getWidthPercentage(), 31 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton Rules = new Bouton("Règles du jeu", 25 * accueil.getWidthPercentage(), 46 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton Exit = new Bouton("Quitter", 25 * accueil.getWidthPercentage(), 61 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton RetourMenu = new Bouton("Retour au menu", 5 * RegleDuJeu.getHeightPercentage(), 5 * RegleDuJeu.getHeightPercentage(), 20 * RegleDuJeu.getWidthPercentage(), 7 * RegleDuJeu.getHeightPercentage(), 4);
    private Bouton LancementJeu = new Bouton("Valider", 30 * NombreJoueurs.getWidthPercentage(), 90 * NombreJoueurs.getHeightPercentage(), 40 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Bouton Menu = new Bouton("Menu", 6 * NombreJoueurs.getHeightPercentage(), 6 * NombreJoueurs.getHeightPercentage(), 20 * NombreJoueurs.getHeightPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 2);
    private Bouton RetourAuJeu = new Bouton("Retour au jeu", 25 * accueil.getWidthPercentage(), 31 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton RemplirDeck = new Bouton("Compléter Programme", ChoisirCoup.getWidth()/7, 25 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton ExecuterDeck = new Bouton("Executer Programme", 3*ChoisirCoup.getWidth()/7, 25 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton PoserMur = new Bouton("Poser un mur", 5*ChoisirCoup.getWidth()/7, 25 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton MurGlace = new Bouton("Mur de Glace", 2*ChoisirCoup.getWidth()/7, 65 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton MurPierre = new Bouton("Mur de Pierre", 4*ChoisirCoup.getWidth()/7, 65 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton Oui = new Bouton("Oui", 1*Defausse.getWidth()/5, 35 * Defausse.getHeightPercentage(), Defausse.getWidth()/5, Defausse.getWidth()/5, 6);
    private Bouton Non = new Bouton("Non", 3*Defausse.getWidth()/5, 35 * Defausse.getHeightPercentage(), Defausse.getWidth()/5, Defausse.getWidth()/5, 6);
    private Carte Carte1 = new Carte("", 5*principal.getWidthPercentage(),25*principal.getHeightPercentage(), 10*principal.getWidthPercentage(),25*principal.getHeightPercentage(),1);
    private Carte Carte2 = new Carte("", 20*principal.getWidthPercentage(),25*principal.getHeightPercentage(), 10*principal.getWidthPercentage(),25*principal.getHeightPercentage(),1);
    private Carte Carte3 = new Carte("", 35*principal.getWidthPercentage(),25*principal.getHeightPercentage(), 10*principal.getWidthPercentage(),25*principal.getHeightPercentage(),1);
    private Carte Carte4 = new Carte("", 13*principal.getWidthPercentage(),55*principal.getHeightPercentage(), 10*principal.getWidthPercentage(),25*principal.getHeightPercentage(),1);
    private Carte Carte5 = new Carte("", 28*principal.getWidthPercentage(),55*principal.getHeightPercentage(), 10*principal.getWidthPercentage(),25*principal.getHeightPercentage(),1);
    private Bouton FinChoix = new Bouton("J'ai fini mon choix",9*principal.getWidthPercentage(),90*principal.getHeightPercentage(),32*principal.getWidthPercentage(),5*principal.getHeightPercentage(),2);


    //Creation des labels
    private Label TexteNombreDeJoueurs = new Label("Nombre de joueurs : ", 15 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 55 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label TexteNombreDeJoueurs2 = new Label("Entrez le nom de chaque joueurs : ", 10 * NombreJoueurs.getWidthPercentage(), 20 * NombreJoueurs.getHeightPercentage(), 80 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label Joueur1 = new Label("Joueur 1 : ", 15 * NombreJoueurs.getWidthPercentage(), 35 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label Joueur2 = new Label("Joueur 2 : ", 15 * NombreJoueurs.getWidthPercentage(), 50 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label Joueur3 = new Label("Joueur 3 : ", 15 * NombreJoueurs.getWidthPercentage(), 65 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label Joueur4 = new Label("Joueur 4 : ", 15 * NombreJoueurs.getWidthPercentage(), 80 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Label ChoixJoueur = new Label(" , que voulez vous faire ?",10 * ChoisirCoup.getWidthPercentage(), 0 * ChoisirCoup.getHeightPercentage(), 80 * ChoisirCoup.getWidthPercentage(), 20 * ChoisirCoup.getHeightPercentage(), 2);
    private Label ChoixJoueurDefausse = new Label(" , voulez vous defaussez des cartes ?",10 * Defausse.getWidthPercentage(), 0 * Defausse.getHeightPercentage(), 80 * Defausse.getWidthPercentage(), 20 * Defausse.getHeightPercentage(), 3);
    private Label Vainqueur = new Label(" a gagné", 10*Victoire.getWidthPercentage(),5*Victoire.getHeightPercentage(),80*Victoire.getWidthPercentage(),20*Victoire.getHeightPercentage(),2);
    private Label TourJoueur = new Label(" est en train de jouer",5*principal.getWidthPercentage(),10*principal.getHeightPercentage(),35*principal.getWidthPercentage(),8*principal.getHeightPercentage(),2);

    //Creation des Zone de Texte
    private ZoneDeTexte NomJoueur1 = new ZoneDeTexte(10, 55 * NombreJoueurs.getWidthPercentage(), 35 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage());
    private ZoneDeTexte NomJoueur2 = new ZoneDeTexte(10, 55 * NombreJoueurs.getWidthPercentage(), 50 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage());
    private ZoneDeTexte NomJoueur3 = new ZoneDeTexte(10, 55 * NombreJoueurs.getWidthPercentage(), 65 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage());
    private ZoneDeTexte NomJoueur4 = new ZoneDeTexte(10, 55 * NombreJoueurs.getWidthPercentage(), 80 * NombreJoueurs.getHeightPercentage(), 30 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage());


    //Creation des Menus Deroulants
    private MenuDeroulant NombreDeJoueurs = new MenuDeroulant(65 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 10 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage());

    //Creation des différents parametres
    private int NBJoueurs;

    public void Initialisation() {
        //creation du tableau
        gameBoard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[j][i] = 0;
            }
        }
        RetourAuJeu.MettreVisible(false);


        //Mise en place des fonctions des boutons
        Play.addActionListener(new PlayAction());
        Rules.addActionListener(new RulesAction());
        Exit.addActionListener(new ExitAction());

        RetourMenu.addActionListener(new RetourMenu());

        NombreDeJoueurs.addActionListener(new ChoixNbrJoueurs());
        LancementJeu.addActionListener(new LancementduJeu());

        Menu.addActionListener(new OuvertureMenu());
        RetourAuJeu.addActionListener(new FermetureMenu());

        RemplirDeck.addActionListener(new RemplirSonDeck());
        ExecuterDeck.addActionListener(new ExecuterSonDeck());
        PoserMur.addActionListener(new ChoixDuMur());
        MurGlace.addActionListener(new PlacerMur());
        MurPierre.addActionListener(new PlacerMur());

        Carte1.addActionListener(new ChoixCarte());
        Carte2.addActionListener(new ChoixCarte());
        Carte3.addActionListener(new ChoixCarte());
        Carte4.addActionListener(new ChoixCarte());
        Carte5.addActionListener(new ChoixCarte());
        FinChoix.addActionListener(new FinDuChoix());

        Oui.addActionListener(new Defausser());
        Non.addActionListener(new Defausser());

        //Ajout de ses éléments au pannel de fenetre
        accueil.AjouterBoutons(Play);
        accueil.AjouterBoutons(Rules);
        accueil.AjouterBoutons(Exit);
        accueil.AjouterBoutons(RetourAuJeu);

        RegleDuJeu.AjouterBoutons(RetourMenu);

        NombreJoueurs.AjouterLabels(TexteNombreDeJoueurs);
        NombreJoueurs.AjouterMenuDeroulant(NombreDeJoueurs);
        NombreJoueurs.AjouterLabels(TexteNombreDeJoueurs2);
        NombreJoueurs.AjouterLabels(Joueur1);
        NombreJoueurs.AjouterLabels(Joueur2);
        NombreJoueurs.AjouterLabels(Joueur3);
        NombreJoueurs.AjouterLabels(Joueur4);
        NombreJoueurs.AjouterZoneDeTexte(NomJoueur1);
        NombreJoueurs.AjouterZoneDeTexte(NomJoueur2);
        NombreJoueurs.AjouterZoneDeTexte(NomJoueur3);
        NombreJoueurs.AjouterZoneDeTexte(NomJoueur4);
        NombreJoueurs.AjouterBoutons(LancementJeu);

        principal.AjouterBoutons(Menu);
        principal.AjouterLabels(TourJoueur);
        principal.AjouterCarte(Carte1);
        principal.AjouterCarte(Carte2);
        principal.AjouterCarte(Carte3);
        principal.AjouterCarte(Carte4);
        principal.AjouterCarte(Carte5);
        principal.AjouterBoutons(FinChoix);

        ChoisirCoup.AjouterBoutons(RemplirDeck);
        ChoisirCoup.AjouterBoutons(ExecuterDeck);
        ChoisirCoup.AjouterBoutons(PoserMur);
        ChoisirCoup.AjouterBoutons(MurGlace);
        ChoisirCoup.AjouterBoutons(MurPierre);
        ChoisirCoup.AjouterLabels(ChoixJoueur);

        Defausse.AjouterBoutons(Oui);
        Defausse.AjouterBoutons(Non);
        Defausse.AjouterLabels(ChoixJoueurDefausse);

        Victoire.AjouterLabels(Vainqueur);

        //Affichage des éléments de la fenetre
        accueil.AfficherBoutons();

        RegleDuJeu.AfficherBoutons();

        NombreJoueurs.AfficherLabels();
        NombreJoueurs.AfficherMenuDeroulant();

        principal.AfficherBoutons();
        principal.AfficherLabels();

        ChoisirCoup.AfficherBoutons();
        ChoisirCoup.AfficherLabels();

        Defausse.AfficherBoutons();
        Defausse.AfficherLabels();

        Victoire.AfficherLabels();

        //Lancement du Jeu
        //accueil.FaireApparaitre();
        //ChoixCoup();
        Defausse.FaireApparaitre();
        Victoire.FaireApparaitre();

        Carte1.MettreCarte("BlueCard.png");
        Carte2.MettreCarte("LaserCard.png");
        Carte3.MettreCarte("YellowCard.png");
        Carte4.MettreCarte("BlueCard.png");
        Carte5.MettreCarte("PurpleCard.png");
        TourJoueur.setQuestion("Hugo");
        //principal.FaireApparaitre();
    }


    //Implementation des utilisations des boutons
    class PlayAction implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            accueil.dispose();
            NombreJoueurs.FaireApparaitre();
        }
    }

    class RulesAction implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            RegleDuJeu.FaireApparaitre();
        }
    }

    class ExitAction implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class RetourMenu implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            RegleDuJeu.dispose();
        }
    }

    class ChoixNbrJoueurs implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            NBJoueurs = (int) NombreDeJoueurs.getValue();
            NombreJoueurs.AfficherZoneDeTexte(NBJoueurs);
            NombreJoueurs.AfficherBoutons();
            NombreJoueurs.reload();
        }

    }

    class LancementduJeu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            NombreJoueurs.dispose();
            String joueur1 = NomJoueur1.getZoneDeTexte();
            String joueur2 = NomJoueur2.getZoneDeTexte();
            String joueur3 = NomJoueur3.getZoneDeTexte();
            String joueur4 = NomJoueur4.getZoneDeTexte();
            principal.FaireApparaitre();
        }
    }

    class OuvertureMenu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Play.MettreVisible(false);
            RetourAuJeu.MettreVisible(true);
            accueil.FaireApparaitre();
        }
    }

    class FermetureMenu implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            accueil.dispose();
        }
    }

    class RemplirSonDeck implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChoisirCoup.dispose();
            principal.AfficherCartes();
            principal.repaint();
        }
    }

    class ExecuterSonDeck implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            ChoisirCoup.dispose();
            Defausse();
        }
    }

    class ChoixDuMur implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            RemplirDeck.RendreCliquable(false);
            ExecuterDeck.RendreCliquable(false);
            PoserMur.RendreCliquable(false);
            MurGlace.RendreCliquable(true);
            MurPierre.RendreCliquable(true);
        }
    }

    class PlacerMur implements ActionListener, MouseListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == MurGlace){
                System.out.println("Glace");
            }
            else if (e.getSource() == MurPierre) {
                System.out.println("Pierre");
            }
            ChoisirCoup.dispose();
            CliqueSouris = true;
            principal.addMouseListener(this);
        }
        public void mouseClicked(MouseEvent e) {
            if(CliqueSouris == false){
            }
            else{
                int x = e.getX();
                int y = e.getY();
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        if (e.getX() > 47*principal.getWidth()/100 + 11*i*principal.getHeight()/100 && e.getX() < 47*principal.getWidth()/100 + 11*(i+1)*principal.getHeight()/100 && e.getY() > 6*principal.getHeight()/100 + 11*j*principal.getHeight()/100 && e.getY() < 6*principal.getHeight()/100 + 11*(j+1)*principal.getHeight()/100){
                            System.out.println(i);
                            System.out.println(j);
                            CliqueSouris = false;
                            ChoixCoup();
                        }
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }

    class Defausser implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            Defausse.dispose();
            if (e.getSource() == Oui){
                principal.AfficherBoutons();
                FinChoix.RendreCliquable(true);
            } else if (e.getSource() == Non){
                ChoixCoup();
            }
        }
    }

    class ChoixCarte implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent Carte) {
            if (Carte.getSource() == Carte1){
                Carte1.MettreVisible(false);
            } else if (Carte.getSource() == Carte2){
                Carte2.MettreVisible(false);
            } else if (Carte.getSource() == Carte3){
                Carte3.MettreVisible(false);
            } else if (Carte.getSource() == Carte4){
                Carte4.MettreVisible(false);
            } else if (Carte.getSource() == Carte5){
                Carte5.MettreVisible(false);
            }
        }
    }

    class FinDuChoix implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            principal.EnleverCartes();
            ChoisirCoup.FaireApparaitre();
        }
    }

    public void ChoixCoup(){
        FinChoix.RendreCliquable(false);
        ChoixJoueur.setQuestion("Hugo");//Prenom du Joueur qui joue
        RemplirDeck.RendreCliquable(true);
        ExecuterDeck.RendreCliquable(true);
        PoserMur.RendreCliquable(true);
        MurGlace.RendreCliquable(false);
        MurPierre.RendreCliquable(false);
        ChoisirCoup.FaireApparaitre();
    }

    public void Defausse(){

    }
}

//Afficher le nombre de mur restant d'un joueur : MurPierre.setText