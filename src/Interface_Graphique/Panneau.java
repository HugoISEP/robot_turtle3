package Interface_Graphique;

import game.other.ColorEnum;
import game.other.Game;
import game.other.OrientationEnum;
import javafx.scene.layout.Background;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
    private String Background;
    private boolean DessineTableau;
    private Game game;
    private String Image;

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        try {
            Image img = ImageIO.read(new File(this.getPanneauBackground()));
            //Pour une image de fond
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DessineTableau == true) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    g2.setStroke(new BasicStroke(5));
                    g.setColor(Color.BLACK);
                    g.drawRect(47 * this.getWidth() / 100 + 11 * i * this.getHeight() / 100, 6 * this.getHeight() / 100 + 11 * j * this.getHeight() / 100, 11 * this.getHeight() / 100, 11 * this.getHeight() / 100);
                    try {
                        String display = this.game.getGrid().getCase(j, i).getContents().getClass().getName();
                        switch (display) {
                            case "game.other.Player":
                                int f;
                                this.Image = "Image/";
                                for (f = 0; f < this.game.getNumberOfplayer(); f++) {
                                    if (this.game.getPlayer(f) == this.game.getGrid().getCase(j, i).getContents()) {
                                        break;
                                    }
                                }
                                if (this.game.getPlayer(f).getOrientation().equals(OrientationEnum.NORTH)) {
                                    this.Image = this.Image + "Nord";
                                }
                                if (this.game.getPlayer(f).getOrientation().equals(OrientationEnum.EAST)) {
                                    this.Image = this.Image + "Est";
                                }
                                if (this.game.getPlayer(f).getOrientation().equals(OrientationEnum.WEST)) {
                                    this.Image = this.Image + "Ouest";
                                }
                                if (this.game.getPlayer(f).getOrientation().equals(OrientationEnum.SOUTH)) {
                                    this.Image = this.Image + "Sud";
                                }
                                if (this.game.getPlayer(f).getColor().equals(ColorEnum.BLUE)) {
                                    this.Image = this.Image + "Blue";
                                }
                                if (this.game.getPlayer(f).getColor().equals(ColorEnum.PINK)) {
                                    this.Image = this.Image + "Pink";
                                }
                                if (this.game.getPlayer(f).getColor().equals(ColorEnum.RED)) {
                                    this.Image = this.Image + "Red";
                                }
                                if (this.game.getPlayer(f).getColor().equals(ColorEnum.GREEN)) {
                                    this.Image = this.Image + "Orange";
                                }
                                this.Image = this.Image + "Tortue.png";
                                try {
                                    Image img = ImageIO.read(new File(this.Image));
                                    g.drawImage(img, 47 * this.getWidth() / 100 + 11 * j * this.getHeight() / 100, 6 * this.getHeight() / 100 + 11 * i * this.getHeight() / 100, 11 * this.getHeight() / 100, 11 * this.getHeight() / 100, this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "grid.Jewel":
                                try {
                                    String Image = "Image/Joyau.png";
                                    Image img = ImageIO.read(new File(Image));
                                    g.drawImage(img, 47 * this.getWidth() / 100 + 11 * j * this.getHeight() / 100, 6 * this.getHeight() / 100 + 11 * i * this.getHeight() / 100, 11 * this.getHeight() / 100, 11 * this.getHeight() / 100, this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "wall.StoneWall":
                                try {
                                    String Image = "Image/pierre.png";
                                    Image img = ImageIO.read(new File(Image));
                                    g.drawImage(img, 47 * this.getWidth() / 100 + 11 * j * this.getHeight() / 100, 6 * this.getHeight() / 100 + 11 * i * this.getHeight() / 100, 11 * this.getHeight() / 100, 11 * this.getHeight() / 100, this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "wall.IceWall":
                                try {
                                    String Image = "Image/Glace.png";
                                    Image img = ImageIO.read(new File(Image));
                                    g.drawImage(img, 47 * this.getWidth() / 100 + 11 * j * this.getHeight() / 100, 6 * this.getHeight() / 100 + 11 * i * this.getHeight() / 100, 11 * this.getHeight() / 100, 11 * this.getHeight() / 100, this);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    } catch (NullPointerException e) { }
                }
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setBackground(String Background) {
        this.Background = "Image/" + Background;
    }

    public String getPanneauBackground() {
        return this.Background;
    }

    public void setDessineTableau(boolean b) {
        this.DessineTableau = b;
    }
}