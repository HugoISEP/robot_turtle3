package Interface_Graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Carte extends Bouton {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private Image Background;
    private ImageObserver imageObserver;

    public Carte(String name, int x, int y, int width, int height, int TailleTexte) {
        super(name, x, y, width, height, TailleTexte);
    }

    public void paint( Graphics g ) {
        super.paint( g );
        g.drawImage(this.Background,  0 , 0 , getWidth() , getHeight() , this.imageObserver);
    }

    public void MettreCarte(String NomCarte){
        ImageIcon icon = new ImageIcon("Image/" + NomCarte);
        this.Background = icon.getImage();
        this.imageObserver = icon.getImageObserver();
    }
}
