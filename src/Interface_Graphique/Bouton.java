package Interface_Graphique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Bouton extends JButton {
    private String name;
    private int x;
    private int y;
    private int width;
    private int height;
    private Font font;

    public int compteur;

    public Bouton(String name, int x, int y, int width, int height, int TailleTexte) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //Texte du bouton
        this.setText(this.name);
        this.font = new Font("Tahoma", Font.PLAIN, this.height / TailleTexte);
        this.setFont(font);

        //Taille et emplacement du bouton
        this.setBounds(this.x, this.y, this.width, this.height);

        //this.addMouseListener(this);
    }

    public void setNbrMurRestant(String NbrRestant){
        this.name = this.name + "(" + NbrRestant + ")";
    }

    public String getName() {
        return this.name;
    }

    public void MettreVisible(boolean b) {
        this.setVisible(b);
    }

    public void RendreCliquable(boolean b) {
        this.setEnabled(b);
    }


}


