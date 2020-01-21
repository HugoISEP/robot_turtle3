package grid;

public class Grid {

    String[][] name;
    private Case[][] gameBoard;

    public Grid() {
        gameBoard = new Case[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[j][i] = new Case();
            }
        }
    }

    public Case[][] getGameBoard() {
        return this.gameBoard;
    }

    public Case getCase(int x, int y) {
        return gameBoard[x][y];
    }


}