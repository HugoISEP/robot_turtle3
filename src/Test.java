import game.other.Game;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playAgain = 0;
        Game game = new Game();
        do {
            do {
                game.gameBoardDisplay();
                game.makeChoice();
                game.getCurrentPlayer().drawCards();
                game.endTurn();
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
            } while (!game.getEndGame());
            game.replay();
            game.scoresDisplay();
            System.out.println("play again ?\n1 : yes\n2 : no");
            do {
                playAgain = scanner.nextInt();
            } while (playAgain > 2 || playAgain < 0);
        } while (playAgain == 1);
    }

}

