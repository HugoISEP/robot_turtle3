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
        BlueCard blueCard = new BlueCard();
        Game game = new Game();
        /*
        do {
            game.gameBoardDisplay();
            game.makeChoice();
            game.getCurrentPlayer().drawCards();
            game.endTurn();
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        } while (!game.getEndGame());
        System.out.println("-----PLAYER " + game.getWinnerPlayer().getNbOfPlayer() + " WIN THE GAME-----");
         */
        game.getGrid().getCase(6,3).setContents(new StoneWall());
        game.getGrid().getCase(5,4).setContents(new StoneWall());
        game.getGrid().getCase(5,5).setContents(new StoneWall());
        game.getGrid().getCase(5,6).setContents(new StoneWall());
        game.getGrid().getCase(6,7).setContents(new StoneWall());

        game.gameBoardDisplay();
        game.turtleAccessToJewel(game.getGrid().getCase(1,0));
        for(Object jewel : game.getJewelsToReach()) {
            System.out.println(jewel.getClass().getName());
        }
        System.out.println(game.getJewelsToReach().get(0) == game.getJewelsToReach().get(0));

    }

}

