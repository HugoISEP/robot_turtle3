import card.BlueCard;
import game.other.ColorEnum;
import game.other.Game;
import game.other.OrientationEnum;
import game.other.Player;
import grid.Jewel;
import wall.StoneWall;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args){
        Game game = new Game();
        do {
            game.gameBoardDisplay();
            game.makeChoice();
            game.getCurrentPlayer().drawCards();
            game.endTurn();
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        } while (!game.getEndGame());
        System.out.println("-----PLAYER " + game.getWinnerPlayer().getNbOfPlayer() + " WIN THE GAME-----");
    }

}

