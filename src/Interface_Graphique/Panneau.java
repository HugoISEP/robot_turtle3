package Interface_Graphique;

import javafx.scene.layout.Background;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    private String Background;
    private boolean DessineTableau;

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        try {
            Image img = ImageIO.read(new File(this.getPanneauBackground()));
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DessineTableau == true){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    g2.setStroke(new BasicStroke(5));
                    g.setColor(Color.BLACK);
                    g.drawRect( 47*this.getWidth()/100 + 11*i*this.getHeight()/100 , 6*this.getHeight()/100 + 11*j*this.getHeight()/100, 11*this.getHeight()/100, 11*this.getHeight()/100);
                }
            }
        }
    }


    public void setBackground(String Background) {
        this.Background = "Image/" + Background;
    }

    public String getPanneauBackground() {
        return this.Background;
    }

    public void setDessineTableau(boolean b){
        this.DessineTableau = b;
    }
}