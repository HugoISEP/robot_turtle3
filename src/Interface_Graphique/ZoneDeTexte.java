package Interface_Graphique;

import javax.swing.*;

public class ZoneDeTexte extends JTextField {
    private int NbrChar;
    private int x;
    private int y;
    private int width;
    private int height;

    public ZoneDeTexte(int NbrChar,int x, int y, int width, int height){
        this.NbrChar = NbrChar;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.setColumns(this.NbrChar);
        this.setVisible(true);
        setBounds(this.x,this.y,this.width,this.height);

    }

    public String getZoneDeTexte(){
        return this.getText();
    }
}
