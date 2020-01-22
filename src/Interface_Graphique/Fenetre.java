package Interface_Graphique;

import game.other.Game;
import javafx.scene.control.Menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class Fenetre extends JFrame implements ActionListener {
    private String name;
    private int width;
    private int height;
    private String Background;
    private Panneau pannel;
    private ArrayList<Bouton> ListeBouton = new ArrayList<>();
    private ArrayList<Label> ListeLabel = new ArrayList<>();
    private ArrayList<ZoneDeTexte> ListeZoneDeTexte = new ArrayList<>();
    private ArrayList<MenuDeroulant> ListeMenuDeroulant = new ArrayList<>();
    private ArrayList<Carte> ListeCarte = new ArrayList<>();

    public Fenetre(String name, int width,int height,String Background){
        //On definit les attributs de la fenetre
        this.name = name;
        this.width = width;
        this.height = height;
        this.Background = Background;

        //Définit un titre pour notre fenêtre
        this.setTitle(this.name);
        //Définit sa taille
        this.setSize(this.width, this.height);
        //Met la page au milieu de l'ecran
        this.setLocationRelativeTo(null);
        //Empecher le redimmensionnement de la page
        this.setResizable(true);
        //Avoir toujours sa page en premier plan
        this.setAlwaysOnTop(true); // a voir
        //Faire en sorte que les bords de la page (plus croix et tout le reste) disparraissent
        this.setUndecorated(true);

        //Instanciation d'un Panneau/contenu de notre Fenetre
        this.pannel = new Panneau();
        this.pannel.setBackground(this.Background);
        if (this.name == "Fenetre Principal"){
            this.pannel.setDessineTableau(true);
        }
        this.pannel.setLayout(null);
        this.setContentPane(this.pannel);
    }

    public void FaireApparaitre(){
        //Et enfin, la rendre visible
        this.setVisible(true);
    }

    public void AjouterBoutons(Bouton bouton){
        this.ListeBouton.add(bouton);
    }

    public void AfficherBoutons() {
        for (int i = 0; i < this.ListeBouton.size(); i++) {
            this.pannel.add(this.ListeBouton.get(i));
        }
    }

    public void AjouterLabels(Label label){
        this.ListeLabel.add(label);
    }

    public void AfficherLabels() {
        for (int i = 0; i < this.ListeLabel.size(); i++) {
            this.pannel.add(this.ListeLabel.get(i));
        }
    }

    public void AjouterZoneDeTexte(ZoneDeTexte zonetxt){
        this.ListeZoneDeTexte.add(zonetxt);
    }

    public void AfficherZoneDeTexte(int Nbr) {
        for (int i = 0; i < Nbr; i++) {
            this.pannel.add(this.ListeZoneDeTexte.get(i));
        }
    }

    public void AjouterMenuDeroulant(MenuDeroulant menu){
        this.ListeMenuDeroulant.add(menu);
    }

    public void AfficherMenuDeroulant() {
        for (int i = 0; i < this.ListeMenuDeroulant.size(); i++) {
            this.pannel.add(this.ListeMenuDeroulant.get(i));
        }
    }

    public void AjouterCarte(Carte carte){
        this.ListeCarte.add(carte);
    }

    public void AfficherCartes(Boolean b) {
        for (int i = 0; i < this.ListeCarte.size(); i++) {
            this.ListeCarte.get(i).RendreCliquable(b);
            this.pannel.add(this.ListeCarte.get(i));
        }
    }

    public void EnleverCartes() {
        for (int i = 0; i < this.ListeCarte.size(); i++) {
            this.pannel.remove(this.ListeCarte.get(i));
        }
    }

    public void actionPerformed(ActionEvent accueil) {

    }

    public int getHeightPercentage() {
        return this.height/100;
    }

    public int getWidthPercentage() {
        return this.width/100;
    }

    public void reload(){
        this.repaint();
    }

    public void setPannelGame(Game game){
        this.pannel.setGame(game);
    }
}
