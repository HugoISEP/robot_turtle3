package game.other;

import card.Card;
import grid.Case;
import grid.Grid;
import grid.Jewel;
import wall.IceWall;
import wall.StoneWall;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean endGame;
    private int numberOfplayer;
    private int numberOfCurrentPlayer;
    private Player[] players;
    private Player winnerPlayer;
    private ArrayList<Case> departurePosition;
    private Grid grid;
    private ArrayList<Object> jewelsToReach = new ArrayList<>();
    private ArrayList<Case> casesAlreadyVisited = new ArrayList<>();

    public Game() {
        endGame = false;
        this.grid = new Grid();
        this.departurePosition = new ArrayList<>();
        this.winnerPlayer = new Player();
        this.initialization();
        this.numberOfCurrentPlayer = new Random().nextInt(numberOfplayer);
        this.getCurrentPlayer().resetNbPlayer();
    }

    public ArrayList<Object> getJewelsToReach(){
        return this.jewelsToReach;
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

    public void setWinnerPlayer(Player player) {
        this.winnerPlayer = player;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void setPlayer(int number, String name, ColorEnum color) {
        this.players[number] = new Player(name, color);
    }

    public Player getCurrentPlayer() {
        return this.players[this.numberOfCurrentPlayer];
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

        System.out.println("How many players are you ?");
        do {
            nbJoueur = scanner.nextInt();
            scanner.nextLine();
        } while (nbJoueur > 4 || nbJoueur < 2);
        this.numberOfplayer = nbJoueur;
        this.players = new Player[this.numberOfplayer];
        //Création de la liste de joueurs
        for (int i = 1; i <= nbJoueur; i++) {
            System.out.println("name of the player " + i + " :");
            String name = scanner.nextLine();
            System.out.println("1 : BLUE\n2 : RED\n3 : PINK\n4 : GREEN");
            System.out.println("color of the player " + i + " :");
            int colorChoice = 1;
            boolean colorTest = false;
            do {
                if (colorChoice < 1 || colorChoice > 4) {
                    System.out.println("Your choice doesn't match with the proposals");
                }
                if (colorTest == true) {
                    System.out.println("the color is already taken");
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
            this.setPlayer(i - 1, name, color);
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
                this.grid.getCase(7, y).setContents(new StoneWall());
            }
            this.grid.getCase(3, 7).setContents(new Jewel());
        }

        if (this.numberOfplayer == 3) {
            this.grid.getCase(0, 0).setContents(this.players[0]);
            this.departurePosition.add(0, this.grid.getCase(0, 0));
            this.grid.getCase(3, 0).setContents(this.players[1]);
            this.departurePosition.add(1, this.grid.getCase(3, 0));
            this.grid.getCase(6, 0).setContents(this.players[2]);
            this.departurePosition.add(2, this.grid.getCase(6, 0));
            for (int y = 0; y < 8; y++) {
                this.grid.getCase(7, y).setContents(new StoneWall());
            }
            this.grid.getCase(0, 7).setContents(new Jewel());
            this.grid.getCase(3, 7).setContents(new Jewel());
            this.grid.getCase(6, 7).setContents(new Jewel());
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

            this.grid.getCase(1, 7).setContents(new Jewel());
            this.grid.getCase(6, 7).setContents(new Jewel());
        }
    }

    public void gameBoardDisplay() {
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
            System.out.println("player " + (player.getNbOfPlayer() +1) + " : " + player.getOrientation() + "  |   position : (" + this.getPlayerPositionX(player)+","+this.getPlayerPositionY(player)+")");
        }
        System.out.println();
        System.out.println("YOUR WALLS : ");
        for (int i = 0; i < this.getCurrentPlayer().getWallsAvailable().size(); i++) {
            System.out.println(i + 1 + " : " + this.getCurrentPlayer().getWallsAvailable().get(i).getName());
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
        boolean actionPlayed = false;
        System.out.println("YOUR CHOICE:\n1 : fill your program\n2 : play your program\n3 : build a wall");
        do {
            do {
                choice = scanner.nextInt();
                scanner.nextLine();
            } while (choice > 3 || choice < 1);

            switch (choice) {
                case 1:
                    this.getCurrentPlayer().fillProgram();
                    actionPlayed = true;
                    break;
                case 2:
                    this.getCurrentPlayer().playProgram(this);
                    actionPlayed = true;
                    break;
                case 3:
                    if (this.getCurrentPlayer().canPlayStoneWall() || this.getCurrentPlayer().canPlayIceWall()){
                        this.getCurrentPlayer().buildWall(this);
                        actionPlayed = true;
                    } else {
                        System.out.println("You don't have any wall, chose another action");
                        actionPlayed = false;
                    }
                    break;
            }
        } while (actionPlayed == false);
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

    public int getPlayerPositionX(Player player) {
        int x = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getGrid().getCase(j, i).getContents() == player) {
                    x = j;
                }
            }
        }
        return x;
    }

    public int getPlayerPositionY(Player player) {
        int y = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getGrid().getCase(j, i).getContents() == player) {
                    y = i;
                }
            }
        }
        return y;
    }

    public void goToDeparturePosition(Player player) {
        this.getGrid().getCase(getPlayerPositionX(player), getPlayerPositionY(player)).setContents(null);
        player.setOrientation(OrientationEnum.SOUTH);
        this.getGrid().getCase(this.getDeparturePosition().get(player.getNbOfPlayer()).getPositionX(), this.getDeparturePosition().get(player.getNbOfPlayer()).getPositionY()).setContents(player);
    }

    public void accessToJewel(Case cas) {

        try {
            if (!this.casesAlreadyVisited.contains(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()))) {
                //si la case n'est pas dans la liste
                //si la case est en dehors du plateau on passe au catch
                try {
                    switch (this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()).getContents().getClass().getName()) {
                        case "game.other.Player":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "grid.Jewel":
                            this.jewelsToReach.add(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()).getContents());
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            //On ajoute le joyeau à la liste des joyeaux accessibles
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "wall.StoneWall":
                            //on ne rappelle pas la fonction
                            break;
                        case "wall.IceWall":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                    }
                } catch (NullPointerException e) {
                    this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                    accessToJewel(this.getGrid().getCase(cas.getPositionX() - 1, cas.getPositionY()));
                    //Si c'est une case vide
                    //on ajoute la case à la liste des cases visitées
                    //on rappelle la fonction
                }
            }

        } catch (Exception e) {
            //on est hors du plateau
        }

        try {
            if (!this.casesAlreadyVisited.contains(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1))) {
                //si la case n'est pas dans la liste
                try {
                    switch (this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1).getContents().getClass().getName()) {
                        case "game.other.Player":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "grid.Jewel":
                            this.jewelsToReach.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1).getContents());
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            //On ajoute le joyeau à la liste des joyeaux accessibles
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "wall.StoneWall":
                            //on ne rappelle pas la fonction
                            break;
                        case "wall.IceWall":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                    }
                } catch (NullPointerException e) {
                    this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                    accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() - 1));
                    //on ajoute la case à la liste des cases visitées
                    //on rappelle la fonction
                }
            }
        } catch (Exception e) {
            //on est hors du plateau
        }

        try {
            if (!this.casesAlreadyVisited.contains(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()))) {
                //si la case n'est pas dans la liste
                try {
                    switch (this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()).getContents().getClass().getName()) {
                        case "game.other.Player":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "grid.Jewel":
                            this.jewelsToReach.add(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()).getContents());
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            //On ajoute le joyeau à la liste des joyeaux accessibles
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "wall.StoneWall":
                            //on ne rappelle pas la fonction
                            break;
                        case "wall.IceWall":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                    }
                } catch (NullPointerException e) {
                    this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                    accessToJewel(this.getGrid().getCase(cas.getPositionX() + 1, cas.getPositionY()));
                    //on ajoute la case à la liste des cases visitées
                    //on rappelle la fonction
                }
            }

        } catch (Exception e) {
            //on est hors du plateau
        }

        try {
            if (!this.casesAlreadyVisited.contains(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1))) {
                //si la case n'est pas dans la liste
                try {
                    switch (this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1).getContents().getClass().getName()) {
                        case "game.other.Player":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "grid.Jewel":
                            this.jewelsToReach.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1).getContents());
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            //On ajoute le joyeau à la liste des joyeaux accessibles
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                        case "wall.StoneWall":
                            //on ne rappelle pas la fonction
                            break;
                        case "wall.IceWall":
                            this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                            //on ajoute la case à la liste des cases visitées
                            //on rappelle la fonction
                            break;
                    }
                } catch (NullPointerException e) {
                    this.casesAlreadyVisited.add(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                    accessToJewel(this.getGrid().getCase(cas.getPositionX(), cas.getPositionY() + 1));
                    //on ajoute la case à la liste des cases visitées
                    //on rappelle la fonction
                }
            }
        } catch (Exception e) {
            //on est hors du plateau
        }
    }

    public boolean playersCanAccessTojewel(){

        for (int i = 0; i < this.getNumberOfplayer(); i++){
            this.jewelsToReach.clear();
            this.casesAlreadyVisited.clear();
            accessToJewel(this.getGrid().getCase(getPlayerPositionX(this.getPlayer(i)), getPlayerPositionY(this.getPlayer(i))));
            if (this.getNumberOfplayer() == 2 && this.jewelsToReach.size()!=1){
                return false;
            }
            if(this.getNumberOfplayer() == 3 && this.jewelsToReach.size()!=3){
                return false;
            }
            if(this.getNumberOfplayer() == 4 && this.jewelsToReach.size()!=2){
                return false;
            }
        }
        return true;
    }
}




