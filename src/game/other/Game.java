package game.other;

import card.Card;
import grid.Case;
import grid.Grid;
import grid.Jewel;
import wall.IceWall;
import wall.StoneWall;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean endGame;
    private int numberOfplayer;
    private int numberOfCurrentPlayer;
    private Player[] players;
    private Player winnerPlayer;
    private int actionChoice;
    private ArrayList<Case> departurePosition;
    private Grid grid;
    private StoneWall stoneWall;
    private Jewel jewel;

    public Game() {
        endGame = false;
        this.jewel = new Jewel();
        this.stoneWall = new StoneWall();
        this.grid = new Grid();
        this.departurePosition = new ArrayList<>();
        this.numberOfCurrentPlayer = 0;
        this.winnerPlayer = new Player();
        this.initialization();
        this.getCurrentPlayer().resetNbPlayer();
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getPlayer(int number) {
        return this.players[number];
    }

    public int getNumberOfplayer() {
        return this.numberOfplayer;
    }

    public boolean getEndGame() {
        return this.endGame;
    }

    public Player getWinnerPlayer() {
        return this.winnerPlayer;
    }

    public void setWinnerPlayer(Player player){
        this.winnerPlayer = player;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void setPlayer(int number, String name, ColorEnum color, OrientationEnum orientation) {
        this.players[number] = new Player(name, color, orientation);
    }

    public Player getCurrentPlayer() {
        return this.players[this.numberOfCurrentPlayer];
    }

    public int getActionChoice() {
        return this.actionChoice;
    }

    public Grid getGrid() {
        return this.grid;
    }

    public int getNumberOfCurrentPlayer() {
        return this.numberOfCurrentPlayer;
    }

    public ArrayList<Case> getDeparturePosition() {
        return this.departurePosition;
    }

    public void endTurn() {
        if (this.numberOfCurrentPlayer == this.numberOfplayer - 1) {
            this.numberOfCurrentPlayer = -1;
        }
        numberOfCurrentPlayer++;
    }

    public void initialization() {
        ArrayList<Integer> colorAlreadyTaken = new ArrayList<>();
        int nbJoueur;

        System.out.println("Combien de joueur êtes-vous ?");
        do {
            nbJoueur = scanner.nextInt();
            scanner.nextLine();
        } while (nbJoueur > 4 || nbJoueur < 2);
        this.numberOfplayer = nbJoueur;
        this.players = new Player[this.numberOfplayer];
        //Création de la liste de joueurs
        for (int i = 1; i <= nbJoueur; i++) {
            System.out.println("nom du joueur " + i + " :");
            String name = scanner.nextLine();
            System.out.println("1 : BLUE\n2 : RED\n3 : PINK\n4 : GREEN");
            System.out.println("couleur du joueur " + i + " :");
            int colorChoice = 1;
            boolean colorTest = false;
            do {
                if (colorChoice < 1 || colorChoice > 4) {
                    System.out.println("Votre choix ne correspond pas aux propositions");
                }
                if (colorTest == true) {
                    System.out.println("La couleur est déjà prise");
                }
                colorTest = false;
                colorChoice = scanner.nextInt();
                scanner.nextLine();
                for (int test : colorAlreadyTaken) {
                    if (test == colorChoice) {
                        colorTest = true;
                    }
                }
            } while (colorChoice < 1 || colorChoice > 4 || colorTest == true);
            ColorEnum color;
            OrientationEnum orientation;
            switch (colorChoice) {
                case 1:
                    color = ColorEnum.BLUE;
                    colorAlreadyTaken.add(colorChoice);
                    break;
                case 2:
                    color = ColorEnum.RED;
                    colorAlreadyTaken.add(colorChoice);
                    break;
                case 3:
                    color = ColorEnum.PINK;
                    colorAlreadyTaken.add(colorChoice);
                    break;
                case 4:
                    color = ColorEnum.GREEN;
                    colorAlreadyTaken.add(colorChoice);
                default:
                    color = ColorEnum.GREEN;
            }
            orientation = OrientationEnum.SOUTH;
            this.setPlayer(i - 1, name, color, orientation);
        }
        this.gameBoardInitialization();
    }

    private void gameBoardInitialization() {
        if (this.numberOfplayer == 2) {
            this.grid.getCase(1, 0).setContents(this.players[0]);
            this.departurePosition.add(0, this.grid.getCase(1, 0));
            this.grid.getCase(5, 0).setContents(this.players[1]);
            this.departurePosition.add(1, this.grid.getCase(5, 0));
            for (int y = 0; y < 8; y++) {
                this.grid.getCase(7, y).setContents(this.stoneWall);
            }
            this.grid.getCase(3, 7).setContents(this.jewel);
        }

        if (this.numberOfplayer == 3) {
            this.grid.getCase(0, 0).setContents(this.players[0]);
            this.departurePosition.add(0, this.grid.getCase(0, 0));
            this.grid.getCase(3, 0).setContents(this.players[1]);
            this.departurePosition.add(1, this.grid.getCase(3, 0));
            this.grid.getCase(6, 0).setContents(this.players[2]);
            this.departurePosition.add(2, this.grid.getCase(6, 0));
            for (int y = 0; y < 8; y++) {
                this.grid.getCase(7, y).setContents(this.stoneWall);
            }
            this.grid.getCase(0, 7).setContents(this.jewel);
            this.grid.getCase(3, 7).setContents(this.jewel);
            this.grid.getCase(6, 7).setContents(this.jewel);
        }

        if (this.numberOfplayer == 4) {
            this.grid.getCase(0, 0).setContents(this.players[0]);
            this.departurePosition.add(0, this.grid.getCase(0, 0));
            this.grid.getCase(2, 0).setContents(this.players[1]);
            this.departurePosition.add(1, this.grid.getCase(2, 0));
            this.grid.getCase(5, 0).setContents(this.players[2]);
            this.departurePosition.add(2, this.grid.getCase(5, 0));
            this.grid.getCase(7, 0).setContents(this.players[3]);
            this.departurePosition.add(3, this.grid.getCase(7, 0));

            this.grid.getCase(1, 7).setContents(this.jewel);
            this.grid.getCase(6, 7).setContents(this.jewel);
        }
    }

    public void gameBoardDisplay() {
        int playerCounter = 1;
        System.out.println("Press enter to continue....");
        scanner.nextLine();
        System.out.println("-----PLAYER " + (this.getNumberOfCurrentPlayer() + 1) + "-----");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    String display = this.getGrid().getCase(j, i).getContents().getClass().getName();
                    switch (display) {
                        case "game.other.Player":
                            System.out.printf("P");
                            break;
                        case "grid.Jewel":
                            System.out.printf("J");
                            break;
                        case "wall.StoneWall":
                            System.out.printf("S");
                            break;
                        case "wall.IceWall":
                            System.out.printf("I");
                            break;
                    }
                } catch (NullPointerException e) {
                    System.out.printf("_");
                }
            }
            System.out.println();
        }
        for (Player player : this.getPlayers()) {
            System.out.println("player " + playerCounter + " : " + player.getOrientation());
            playerCounter++;
        }
        System.out.println();
        System.out.println("YOUR HANDCARDS : ");
        for (int i = 0; i < this.getCurrentPlayer().getHandCards().size(); i++) {
            System.out.println(i + 1 + " : " + this.getCurrentPlayer().getHandCards().get(i).getName());
        }
        System.out.println();
    }

    public void makeChoice() {
        int choice;
        boolean returnChoice = false;
        System.out.println("YOUR CHOICE:\n1 : fill your program\n2 : play your program\n3 : build a wall");
        do {
            do {
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice > 3 || choice < 1);

            switch (choice) {
                case 1:
                    this.getCurrentPlayer().fillProgram();
                    returnChoice = true;
                    break;
                case 2:
                    this.playProgram();
                    returnChoice = true;
                    break;
                case 3:
                    returnChoice = buildWall();
                    break;
            }
        } while (returnChoice == false);
        if (getCurrentPlayer().getHandCards().size() != 0) {
            System.out.println("Do you want to discard some cards in your hands ?\n1 : yes\n2 : no");
            do {
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice > 2 || choice < 1);
            if (choice == 1) {
                this.getCurrentPlayer().putHandCardToDiscard();
            }
        }
    }

    public boolean buildWall() {
        IceWall iceWall = new IceWall();
        StoneWall stoneWall = new StoneWall();
        int choice;
        int choiceX;
        int choiceY;
        boolean correctPlacement = false;
        System.out.println("1 : build ice wall\n2 : build stone wall\n3 : return to menu");
        do {
            choice = this.scanner.nextInt();
            this.scanner.nextLine();
        } while (choice < 1 || choice > 2);

        if ((choice == 1 && this.players[this.numberOfCurrentPlayer].playIceWall()) || (choice == 2 && this.players[this.numberOfCurrentPlayer].playStoneWall())) {
            do {
                System.out.println("Enter the position: ");
                System.out.printf("x : ");
                do {
                    choiceX = this.scanner.nextInt();
                    this.scanner.nextLine();
                } while (choiceX > 7 || choiceX < 0);
                System.out.printf("y : ");
                do {
                    choiceY = this.scanner.nextInt();
                    this.scanner.nextLine();
                } while (choiceY > 7 || choiceX < 0);
                try {
                    String display = this.getGrid().getCase(choiceX, choiceY).getContents().getClass().getName();
                    switch (display) {
                        case "game.other.Player":
                            correctPlacement = false;
                            System.out.println("placement impossible, a player is on this case");
                            break;
                        case "grid.Jewel":
                            correctPlacement = false;
                            System.out.println("placement impossible, a jewel is on this case");
                            break;
                        case "wall.StoneWall":
                            correctPlacement = false;
                            System.out.println("placement impossible, a stone wall is on this case");
                            break;
                        case "wall.IceWall":
                            correctPlacement = false;
                            System.out.println("placement impossible, an ice wall is on this case");
                            break;
                    }
                } catch (NullPointerException e) {
                    if (choice == 1) {
                        this.grid.getCase(choiceX, choiceY).setContents(iceWall);
                    }
                    if (choice == 2) {
                        this.grid.getCase(choiceX, choiceY).setContents(stoneWall);
                    }
                    correctPlacement = true;
                }
            } while (correctPlacement == false);
            return true;
        }
        return false;
    }

    public void playProgram() {
        for (Card card : this.getCurrentPlayer().getPlayerProgram().getProgram()) {
            card.playCard(this, getCurrentPlayer());
            this.getCurrentPlayer().getPlayerDeck().addCardToDiscard(card);
        }
        this.getCurrentPlayer().getPlayerProgram().resetProgram();
    }


}

