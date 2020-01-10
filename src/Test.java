import game.other.Game;

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

