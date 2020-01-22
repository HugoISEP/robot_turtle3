package game.other;

import grid.Case;
import grid.Grid;
import grid.Jewel;
import wall.StoneWall;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    private static boolean endGame;
    private int numberOfplayer;
    private int numberOfCurrentPlayer;
    private boolean playerHasFinished;
    private ArrayList<Player> players;
    private ArrayList<Player> winnerPlayers;
    private ArrayList<Case> departurePosition;
    private Grid grid;
    private ArrayList<Object> jewelsToReach;
    private ArrayList<Case> casesAlreadyVisited;

    public Game() {
        this.endGame = false;
        this.grid = new Grid();
        this.departurePosition = new ArrayList<>();
        this.players = new ArrayList<>();
        this.jewelsToReach = new ArrayList<>();
        this.casesAlreadyVisited = new ArrayList<>();
        this.winnerPlayers = new ArrayList<>();
    }

    public void Initilisation(){
        this.gameBoardInitialization();
        this.numberOfCurrentPlayer = new Random().nextInt(this.numberOfplayer);
        this.getCurrentPlayer().resetNbPlayer();
    }

    public boolean getPlayerHasFinish(){
        return this.playerHasFinished;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void AddPlayer(String name,ColorEnum color){
        this.players.add(new Player(name,color));
    }

    public Player getPlayer(int number) {
        return this.players.get(number);
    }

    public int getNumberOfplayer() {
        return this.numberOfplayer;
    }

    public boolean getEndGame() {
        return this.endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void setNumberOfplayer(int numberOfplayer) {
        this.numberOfplayer = numberOfplayer;
    }

    public Player getCurrentPlayer() {
        return this.players.get(this.numberOfCurrentPlayer);
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
        if (this.playerHasFinished) {
            this.players.remove(this.getNumberOfCurrentPlayer()); //on enlève le joueur de la liste de joueur en train de jouer
            this.numberOfCurrentPlayer--; //pour ne pas sauter le tour du joueur prochain
            this.playerHasFinished = false;
        }
        if (this.numberOfCurrentPlayer == this.players.size() - 1) {
            this.numberOfCurrentPlayer = -1;
        }
        numberOfCurrentPlayer++;
    }

    public void replay() {
        this.setEndGame(false);
        //on remet les joueurs gagnants dans la liste des joueurs pouvant jouer
        for (int i = 0; i < this.winnerPlayers.size(); i++) {
            this.players.add(this.winnerPlayers.get(i));
        }

        //on créer un nouveau deck, on met à 0 le programme du joueur et il pioche 5 cartes
        for (int i = 0; i < this.players.size(); i++){
            this.players.get(i).playerInitialisation();
        }

        //on enleve le contenu de toutes les cases
        for (int i = 0; i < 8; i++){
            for (int j = 0; j<8; j++){
                this.getGrid().getCase(j,i).setContents(null);
            }
        }

        //On remet à 0 les deux listes permettant de verifier si la pose d'un mur est possible
        this.jewelsToReach.clear();
        this.casesAlreadyVisited.clear();
        this.playerHasFinished = false;
        this.winnerPlayers.clear();

        //on initialie le plateau
        gameBoardInitialization();
    }

    private void gameBoardInitialization() {
        if (this.numberOfplayer == 2) {
            this.grid.getCase(1, 0).setContents(this.players.get(0));
            this.departurePosition.add(0, this.grid.getCase(1, 0));
            this.grid.getCase(5, 0).setContents(this.players.get(1));
            this.departurePosition.add(1, this.grid.getCase(5, 0));
            for (int y = 0; y < 8; y++) {
                this.grid.getCase(7, y).setContents(new StoneWall());
            }
            this.grid.getCase(3, 7).setContents(new Jewel());
        }

        if (this.numberOfplayer == 3) {
            this.grid.getCase(0, 0).setContents(this.players.get(0));
            this.departurePosition.add(0, this.grid.getCase(0, 0));
            this.grid.getCase(3, 0).setContents(this.players.get(1));
            this.departurePosition.add(1, this.grid.getCase(3, 0));
            this.grid.getCase(6, 0).setContents(this.players.get(2));
            this.departurePosition.add(2, this.grid.getCase(6, 0));
            for (int y = 0; y < 8; y++) {
                this.grid.getCase(7, y).setContents(new StoneWall());
            }
            this.grid.getCase(0, 7).setContents(new Jewel());
            this.grid.getCase(3, 7).setContents(new Jewel());
            this.grid.getCase(6, 7).setContents(new Jewel());
        }

        if (this.numberOfplayer == 4) {
            this.grid.getCase(0, 0).setContents(this.players.get(0));
            this.departurePosition.add(0, this.grid.getCase(0, 0));
            this.grid.getCase(2, 0).setContents(this.players.get(1));
            this.departurePosition.add(1, this.grid.getCase(2, 0));
            this.grid.getCase(5, 0).setContents(this.players.get(2));
            this.departurePosition.add(2, this.grid.getCase(5, 0));
            this.grid.getCase(7, 0).setContents(this.players.get(3));
            this.departurePosition.add(3, this.grid.getCase(7, 0));

            this.grid.getCase(1, 7).setContents(new Jewel());
            this.grid.getCase(6, 7).setContents(new Jewel());
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

    public void playerHasWon() {
        this.winnerPlayers.add(this.getCurrentPlayer());
        if (this.numberOfplayer == 4) {
            switch (this.winnerPlayers.size()) {
                case 1:
                    this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 3));
                    break;
                case 2:
                    this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 2));
                    break;
                case 3:
                    this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 1));
                    this.setEndGame(true);
                    break;
            }
        } else if (this.numberOfplayer == 3) {
            switch (this.winnerPlayers.size()) {
                case 1:
                    this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 2));
                    break;
                case 2:
                    this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 1));
                    this.setEndGame(true);
                    break;
            }
        } else {
            this.getCurrentPlayer().setScore((this.getCurrentPlayer().getScore() + 1));
            this.setEndGame(true);
        }
        this.playerHasFinished = true;
        this.getCurrentPlayer().getPlayerProgram().resetProgram();
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

    public boolean playersCanAccessTojewel() {
        //On regarde si chaque joueur peut atteindre le joyau
        for (int i = 0; i < this.players.size(); i++) {
            //on regarde la case ou le joueur se trouve et sa case départ
            for (int j = 0; j < 2; j++) {
                this.jewelsToReach.clear();
                this.casesAlreadyVisited.clear();
                if (j % 2 == 0) {
                    accessToJewel(this.getGrid().getCase(getPlayerPositionX(this.getPlayer(i)), getPlayerPositionY(this.getPlayer(i))));
                } else {
                    accessToJewel(this.getGrid().getCase(this.getDeparturePosition().get(this.getPlayer(i).getNbOfPlayer()).getPositionX(), this.getDeparturePosition().get(this.getPlayer(i).getNbOfPlayer()).getPositionY()));
                }
                if (this.getNumberOfplayer() == 2 && this.jewelsToReach.size() != 1) {
                    return false;
                }
                if (this.getNumberOfplayer() == 3 && this.jewelsToReach.size() != 3) {
                    return false;
                }
                if (this.getNumberOfplayer() == 4 && this.jewelsToReach.size() != 2) {
                    return false;
                }
            }
        }
        return true;
    }
}