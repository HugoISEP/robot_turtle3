package Interface_Graphique;

import javax.swing.*;
import java.util.ArrayList;

public class MenuDeroulant extends JComboBox {
    private int x;
    private int y;
    private int width;
    private int height;

    public MenuDeroulant(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.addItem(2);
        this.addItem(3);
        this.addItem(4);

        this.setBounds(this.x, this.y, this.width, this.height);
    }

    public Object getValue(){
        this.setEnabled(false);
        return this.getSelectedItem();
    }

}
