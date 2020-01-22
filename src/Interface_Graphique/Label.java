package Interface_Graphique;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private String contenu;
    private int x;
    private int y;
    private int width;
    private int height;

    public Label(String contenu, int x, int y , int width, int height, int TailleTexte){
        this.contenu = contenu;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //this.couleur = couleur;

        this.setText(this.contenu);

        //Définition d'une police d'écriture
        Font font = new Font("Tahoma",Font.PLAIN,this.height/TailleTexte);
        //On l'applique au JLabel
        this.setFont(font);
        //Changement de la couleur du texte
        this.setForeground(Color.BLACK);
        //On modifie l'alignement du texte grâce aux attributs statiques
        //de la classe JLabel
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBounds(this.x,this.y,this.width,this.height);

        //this.setHorizontalAlignment(JLabel.CENTER);
        //this.setForeground(this.couleur);
    }

    public void setContenu(String contenu) {
        this.setText(contenu);
    }

    public void setQuestion(String prenom){
        this.setText(prenom + this.contenu);
    }
}
