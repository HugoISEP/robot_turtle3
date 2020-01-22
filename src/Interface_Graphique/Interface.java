package Interface_Graphique;

import game.other.ColorEnum;
import game.other.Game;
import wall.IceWall;
import wall.StoneWall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class Interface extends JFrame {

    //Creation d'un faux pour interface tableau
    private int[][] gameBoard;
    private boolean CliqueSouris = true;
    public Game game = new Game();
    public boolean defausser;
    public byte Carte1Prise = 0;
    public byte Carte2Prise = 0;
    public byte Carte3Prise = 0;
    public byte Carte4Prise = 0;
    public byte choice;

    //Recuperation des données utiles
    private Dimension dimensionEcran = Toolkit.getDefaultToolkit().getScreenSize();

    //Creation des différentes feneteres
    private Fenetre accueil = new Fenetre("Fenetre d'accueil", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight(), "Accueil.jpg");
    private Fenetre RegleDuJeu = new Fenetre("Regle du Jeu", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight(), "FondRegle.png");
    private Fenetre principal = new Fenetre("Fenetre Principal", (int) dimensionEcran.getWidth(), (int) dimensionEcran.getHeight(), "Principal.jpeg");
    private Fenetre NombreJoueurs = new Fenetre("Nombre de joueurs", (int) dimensionEcran.getHeight()/2, (int) dimensionEcran.getHeight()/2, "fond.jpg");
    private Fenetre ChoisirCoup = new Fenetre("Choisir son mouvement", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight() /2, "ImageFond.jpg");
    private Fenetre Defausse = new Fenetre("Defausser des cartes", (int) dimensionEcran.getHeight(), (int) dimensionEcran.getHeight()/2, "ImageFond.jpg");
    private Fenetre Victoire = new Fenetre("Victoire", (int) dimensionEcran.getHeight()/2, (int) dimensionEcran.getHeight()/2, "TortueDab.jpg");
    private Fenetre Score = new Fenetre("Score", (int) dimensionEcran.getHeight()/2, (int) dimensionEcran.getHeight()/2, "TortueDab.jpg");

    //Creation des boutons
    private Bouton Play = new Bouton("Jouer", 25 * accueil.getWidthPercentage(), 31 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton Rules = new Bouton("Règles du jeu", 25 * accueil.getWidthPercentage(), 46 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton Exit = new Bouton("Quitter", 25 * accueil.getWidthPercentage(), 61 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton RetourMenu = new Bouton("Retour au menu", 75 * RegleDuJeu.getHeightPercentage(), 5 * RegleDuJeu.getHeightPercentage(), 20 * RegleDuJeu.getWidthPercentage(), 7 * RegleDuJeu.getHeightPercentage(), 4);
    private Bouton LancementJeu = new Bouton("Valider", 30 * NombreJoueurs.getWidthPercentage(), 90 * NombreJoueurs.getHeightPercentage(), 40 * NombreJoueurs.getWidthPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 1);
    private Bouton Menu = new Bouton("Menu", 6 * NombreJoueurs.getHeightPercentage(), 6 * NombreJoueurs.getHeightPercentage(), 20 * NombreJoueurs.getHeightPercentage(), 5 * NombreJoueurs.getHeightPercentage(), 2);
    private Bouton RetourAuJeu = new Bouton("Retour au jeu", 25 * accueil.getWidthPercentage(), 31 * accueil.getHeightPercentage(), 50 * accueil.getWidthPercentage(), 7 * accueil.getHeightPercentage(), 2);
    private Bouton RemplirDeck = new Bouton("Remplir Programme", 2*ChoisirCoup.getWidth()/7, 25 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
    private Bouton ExecuterDeck = new Bouton("Executer Programme", 4*ChoisirCoup.getWidth()/7, 25 * ChoisirCoup.getHeightPercentage(), ChoisirCoup.getWidth()/7, ChoisirCoup.getWidth()/7, 12);
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
    private Bouton FaireSonChoix = new Bouton("J'ai fais mon choix",9*principal.getWidthPercentage(),90*principal.getHeightPercentage(),32*principal.getWidthPercentage(),5*principal.getHeightPercentage(),2);
    private Bouton Exit2 = new Bouton("Quitter", 25 * Victoire.getWidthPercentage(), 50 * Victoire.getHeightPercentage(), 50 * Victoire.getWidthPercentage(), 10 * Victoire.getHeightPercentage(), 2);
    private Bouton Continuer = new Bouton("Continuer", 25 * Victoire.getWidthPercentage(), 70 * Victoire.getHeightPercentage(), 50 * Victoire.getWidthPercentage(), 10 * Victoire.getHeightPercentage(), 2);
    private Bouton Rejouer = new Bouton("Rejouer", 25 * Score.getWidthPercentage(), 65 * Score.getHeightPercentage(), 50 * Score.getWidthPercentage(), 10 * Score.getHeightPercentage(), 2);
    private Bouton Exit3 = new Bouton("Quitter", 25 * Score.getWidthPercentage(), 85 * Score.getHeightPercentage(), 50 * Score.getWidthPercentage(), 10 * Score.getHeightPercentage(), 2);



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
    private Label TourJoueur = new Label(" est en train de jouer",0*principal.getWidthPercentage(),10*principal.getHeightPercentage(),45*principal.getWidthPercentage(),8*principal.getHeightPercentage(),2);
    private Label ScoreJoueur1 = new Label("",10*Score.getWidthPercentage(),5*Score.getHeightPercentage(),80*Score.getWidthPercentage(),20*Score.getHeightPercentage(),3);
    private Label ScoreJoueur2 = new Label("",10*Score.getWidthPercentage(),25*Score.getHeightPercentage(),80*Score.getWidthPercentage(),20*Score.getHeightPercentage(),3);
    private Label ScoreJoueur3 = new Label("",10*Score.getWidthPercentage(),45*Score.getHeightPercentage(),80*Score.getWidthPercentage(),20*Score.getHeightPercentage(),3);
    private Label ScoreJoueur4 = new Label("",10*Score.getWidthPercentage(),65*Score.getHeightPercentage(),80*Score.getWidthPercentage(),20*Score.getHeightPercentage(),3);

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

        RemplirDeck.addActionListener(new RemplirSonProgramme());
        ExecuterDeck.addActionListener(new ExecuterSonDeck());
        MurGlace.addActionListener(new PlacerMur());
        MurPierre.addActionListener(new PlacerMur());

        Carte1.addActionListener(new ChoixCarte());
        Carte2.addActionListener(new ChoixCarte());
        Carte3.addActionListener(new ChoixCarte());
        Carte4.addActionListener(new ChoixCarte());
        Carte5.addActionListener(new ChoixCarte());
        FinChoix.addActionListener(new FinDuChoix());
        FaireSonChoix.addActionListener(new FaireSonChoix());

        Oui.addActionListener(new Defausser());
        Non.addActionListener(new Defausser());

        Exit2.addActionListener(new ExitAction());
        Continuer.addActionListener(new ContinuerLaPartie());

        Exit3.addActionListener(new ExitAction());
        Rejouer.addActionListener(new RecommercerPartie());

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
        principal.AjouterBoutons(FaireSonChoix);

        ChoisirCoup.AjouterBoutons(RemplirDeck);
        ChoisirCoup.AjouterBoutons(ExecuterDeck);
        ChoisirCoup.AjouterBoutons(MurGlace);
        ChoisirCoup.AjouterBoutons(MurPierre);
        ChoisirCoup.AjouterLabels(ChoixJoueur);

        Defausse.AjouterBoutons(Oui);
        Defausse.AjouterBoutons(Non);
        Defausse.AjouterLabels(ChoixJoueurDefausse);

        Victoire.AjouterLabels(Vainqueur);
        Victoire.AjouterBoutons(Continuer);
        Victoire.AjouterBoutons(Exit2);

        Score.AjouterBoutons(Rejouer);
        Score.AjouterBoutons(Exit3);
        Score.AjouterLabels(ScoreJoueur1);
        Score.AjouterLabels(ScoreJoueur2);
        Score.AjouterLabels(ScoreJoueur3);
        Score.AjouterLabels(ScoreJoueur4);

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
        Victoire.AfficherBoutons();

        Score.AfficherBoutons();
        Score.AfficherLabels();

        //Lancement du Jeu
        accueil.FaireApparaitre();

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

            game.setNumberOfplayer(NBJoueurs);
            if (game.getNumberOfplayer()>= 2){
                game.AddPlayer(joueur1, ColorEnum.BLUE);
                game.AddPlayer(joueur2, ColorEnum.RED);
            }
            if (game.getNumberOfplayer()>= 3){
                game.AddPlayer(joueur3, ColorEnum.PINK);
            }
            if (game.getNumberOfplayer()>=4){
                game.AddPlayer(joueur4, ColorEnum.GREEN);
            }
            game.Initilisation();
            principal.FaireApparaitre();
            MainFunction();
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

    class RemplirSonProgramme implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChoisirCoup.dispose();
            principal.AfficherCartes(true);
            principal.reload();
            FinChoix.MettreVisible(true);
            principal.repaint();
        }
    }

    class ExecuterSonDeck implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            ChoisirCoup.dispose();
            game.getCurrentPlayer().playProgram(game);
            principal.setPannelGame(game);
            Defausse.FaireApparaitre();
        }
    }

    class PlacerMur implements ActionListener, MouseListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == MurGlace){
                choice = 1;
            }
            else if (e.getSource() == MurPierre) {
                choice = 2;
            }
            ChoisirCoup.dispose();
            CliqueSouris = true;
            principal.addMouseListener(this);
        }
        public void mouseClicked(MouseEvent e) {
            if(CliqueSouris == false){
            }
            else{
                CliqueSouris = false;
                int x = e.getX();
                int y = e.getY();
                for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                        if (e.getX() > 47*principal.getWidth()/100 + 11*j*principal.getHeight()/100 && e.getX() < 47*principal.getWidth()/100 + 11*(j+1)*principal.getHeight()/100 && e.getY() > 6*principal.getHeight()/100 + 11*i*principal.getHeight()/100 && e.getY() < 6*principal.getHeight()/100 + 11*(i+1)*principal.getHeight()/100){
                            verifMur(j,i);
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

    public void verifMur(int X, int Y){
        boolean correctPlacement = false;
        try {
            String display = game.getGrid().getCase(X, Y).getContents().getClass().getName();
            switch (display) {
                case "game.other.Player":
                    correctPlacement = false;
                    break;
                case "grid.Jewel":
                    correctPlacement = false;
                    break;
                case "wall.StoneWall":
                    correctPlacement = false;
                    break;
                case "wall.IceWall":
                    correctPlacement = false;
                    break;
            }
        } catch (NullPointerException e) {
            if (choice == 1) {
                game.getGrid().getCase(X, Y).setContents(new IceWall());
                game.getCurrentPlayer().takeIceWall();
                correctPlacement = true;
            }
            if (choice == 2) {
                game.getGrid().getCase(X, Y).setContents(new StoneWall());
                if (game.playersCanAccessTojewel()) {
                    game.getCurrentPlayer().takeStoneWall();
                    correctPlacement = true;
                } else {
                    game.getGrid().getCase(X, Y).setContents(null);
                    correctPlacement = false;
                }
            }
        }
        if (!correctPlacement){
            CliqueSouris=true;
        }
        else {
            CliqueSouris = false;
            principal.setPannelGame(game);
            principal.reload();
            Defausse.FaireApparaitre();
        }
    }

    class Defausser implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            Defausse.dispose();
            if (e.getSource() == Oui){
                defausser = true;
                ChargerCartes();
                principal.AfficherCartes(true);
                FinChoix.MettreVisible(true);
            } else if (e.getSource() == Non){
                FinTour();
            }
        }
    }

    class ChoixCarte implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent Carte) {
            if (!defausser) {
                if (Carte.getSource() == Carte1) {
                    Carte1.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerProgram().addACard(game.getCurrentPlayer().getHandCards().remove(0));
                    Carte1Prise = 1;
                } else if (Carte.getSource() == Carte2) {
                    Carte2.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerProgram().addACard(game.getCurrentPlayer().getHandCards().remove((1 - Carte1Prise)));
                    Carte2Prise = 1;
                } else if (Carte.getSource() == Carte3) {
                    Carte3.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerProgram().addACard(game.getCurrentPlayer().getHandCards().remove((2 - Carte1Prise - Carte2Prise)));
                    Carte3Prise = 1;
                } else if (Carte.getSource() == Carte4) {
                    Carte4.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerProgram().addACard(game.getCurrentPlayer().getHandCards().remove((3 - Carte1Prise - Carte2Prise - Carte3Prise)));
                    Carte4Prise = 1;
                } else if (Carte.getSource() == Carte5) {
                    Carte5.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerProgram().addACard(game.getCurrentPlayer().getHandCards().remove((4 - Carte1Prise - Carte2Prise - Carte3Prise - Carte4Prise)));
                }
            }
            else{
                if (Carte.getSource() == Carte1) {
                    Carte1.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerDeck().getDiscard().add(game.getCurrentPlayer().getHandCards().remove(0));
                    Carte1Prise = 1;
                } else if (Carte.getSource() == Carte2) {
                    Carte2.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerDeck().getDiscard().add(game.getCurrentPlayer().getHandCards().remove(1 - Carte1Prise));
                    Carte2Prise = 1;
                } else if (Carte.getSource() == Carte3) {
                    Carte3.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerDeck().getDiscard().add(game.getCurrentPlayer().getHandCards().remove(2 - Carte1Prise - Carte2Prise));
                    Carte3Prise = 1;
                } else if (Carte.getSource() == Carte4) {
                    Carte4.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerDeck().getDiscard().add(game.getCurrentPlayer().getHandCards().remove(3 - Carte1Prise - Carte2Prise - Carte3Prise));
                    Carte4Prise = 1;
                } else if (Carte.getSource() == Carte5) {
                    Carte5.MettreVisible(false);
                    game.getCurrentPlayer().getPlayerDeck().getDiscard().add(game.getCurrentPlayer().getHandCards().remove(4 - Carte1Prise - Carte2Prise - Carte3Prise - Carte4Prise));
                }
            }
        }
    }

    class FinDuChoix implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            principal.EnleverCartes();
            principal.reload();
            if(!defausser){
                Defausse.FaireApparaitre();
            }
            else {
                defausser = false;
                FinChoix.MettreVisible(false);
                FinTour();
            }
        }
    }

    public void FinTour(){
        game.getCurrentPlayer().drawCards();
        if (game.getPlayerHasFinish()){
            Vainqueur.setQuestion(game.getCurrentPlayer().getName());
            Victoire.FaireApparaitre();
        } else {
            FinTour2();
        }
    }

    public void FinTour2(){
        game.endTurn();
        if (!game.getEndGame()){
            MainFunction();
        }
        else{
            principal.setPannelGame(game);
            game.replay();
            if (game.getNumberOfplayer() >= 2){
                ScoreJoueur1.setContenu("Score de " + game.getPlayer(0).getName() + " : " + game.getPlayer(0).getScore());
                ScoreJoueur2.setContenu("Score de " +game.getPlayer(1).getName() + " : " + game.getPlayer(1).getScore());
            }
            if (game.getNumberOfplayer() >= 3){
                ScoreJoueur3.setContenu("Score de " +game.getPlayer(2).getName() + " : " + game.getPlayer(2).getScore());
            }
            if (game.getNumberOfplayer() >= 4){
                ScoreJoueur4.setContenu("Score de " +game.getPlayer(3).getName() + " : " + game.getPlayer(3).getScore());
            }
            Score.reload();
            Score.FaireApparaitre();
        }
    }

    class FaireSonChoix implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ChoixCoup();
        }
    }

    public void ChoixCoup(){
        FaireSonChoix.MettreVisible(false);
        FinChoix.MettreVisible(false);
        RemplirDeck.RendreCliquable(true);
        ExecuterDeck.RendreCliquable(true);
        if (!game.getCurrentPlayer().canPlayIceWall()){
            MurGlace.RendreCliquable(false);
        } else {
            MurGlace.RendreCliquable(true);
        }
        if (!game.getCurrentPlayer().canPlayStoneWall()){
            MurPierre.RendreCliquable(false);
        } else {
            MurPierre.RendreCliquable(true);
        }
        ChoisirCoup.FaireApparaitre();
    }

    public void AfficherCartes(){
        TourJoueur.setQuestion(game.getCurrentPlayer().getName()+" (" + game.getCurrentPlayer().getColorName() + ")");
        ChoixJoueur.setQuestion(game.getCurrentPlayer().getName());
        ChoixJoueurDefausse.setQuestion(game.getCurrentPlayer().getName());
        ChargerCartes();
        FinChoix.MettreVisible(false);
        FaireSonChoix.MettreVisible(true);
        principal.AfficherCartes(false);
    }

    public void MainFunction(){
        principal.setPannelGame(game);
        AfficherCartes();
    }

    public void ChargerCartes(){
        if (game.getCurrentPlayer().getHandCards().size() >= 1){
            Carte1.MettreCarte(game.getCurrentPlayer().getHandCards().get(0).getName() + ".png");
            Carte1.MettreVisible(true);
        } else {
            Carte1.MettreVisible(false);
        }
        if (game.getCurrentPlayer().getHandCards().size() >= 2){
            Carte2.MettreCarte(game.getCurrentPlayer().getHandCards().get(1).getName() + ".png");
            Carte2.MettreVisible(true);
        } else {
            Carte2.MettreVisible(false);
        }
        if (game.getCurrentPlayer().getHandCards().size() >= 3){
            Carte3.MettreCarte(game.getCurrentPlayer().getHandCards().get(2).getName() + ".png");
            Carte3.MettreVisible(true);
        } else {
            Carte3.MettreVisible(false);
        }
        if (game.getCurrentPlayer().getHandCards().size() >= 4){
            Carte4.MettreCarte(game.getCurrentPlayer().getHandCards().get(3).getName() + ".png");
            Carte4.MettreVisible(true);
        } else {
            Carte4.MettreVisible(false);
        }
        if (game.getCurrentPlayer().getHandCards().size() >= 5){
            Carte5.MettreCarte(game.getCurrentPlayer().getHandCards().get(4).getName() + ".png");
            Carte5.MettreVisible(true);
        } else {
            Carte5.MettreVisible(false);
        }
        Carte1Prise = 0;
        Carte2Prise = 0;
        Carte3Prise = 0;
        Carte4Prise = 0;
        principal.reload();
    }

    class ContinuerLaPartie implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            Victoire.dispose();
            FinTour2();
        }
    }

    class RecommercerPartie implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            Score.dispose();
            principal.reload();
            MainFunction();
        }
    }
}

//Afficher le nombre de mur restant d'un joueur : MurPierre.setText