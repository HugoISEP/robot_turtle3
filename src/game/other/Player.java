package game.other;

import card.Card;
import card.Deck;
import card.Program;
import wall.IceWall;
import wall.StoneWall;
import wall.Wall;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static int nbPlayer = 0;
    private int nbOfPlayer;
    private static Scanner scanner = new Scanner(System.in);
    private String name;
    private ColorEnum color;
    private int score;
    private OrientationEnum orientation;
    private Deck deck;
    private Program program;
    private ArrayList<Wall> wallsAvailable;
    private ArrayList<Card> handCards;

    public Player() {
        this.name = "";
        this.deck = new Deck();
        this.program = new Program();
        this.handCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.handCards.add(this.deck.pickACard());
        }
        this.wallsAvailable = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.wallsAvailable.add(new StoneWall());
        }
        for (int i = 0; i < 2; i++) {
            this.wallsAvailable.add(new IceWall());
        }
    }

    public Player(String name, ColorEnum color) {
        this.nbOfPlayer = nbPlayer++;
        this.name = name;
        this.color = color;
        this.orientation = OrientationEnum.SOUTH;
        this.deck = new Deck();
        this.program = new Program();
        this.handCards = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.handCards.add(this.deck.pickACard());
        }
        this.wallsAvailable = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            this.wallsAvailable.add(new StoneWall());
        }
        for (int i = 0; i < 2; i++) {
            this.wallsAvailable.add(new IceWall());
        }
    }

    public String getName() {
        return this.name;
    }

    public ColorEnum getColor() {
        return this.color;
    }

    public OrientationEnum getOrientation() {
        return this.orientation;
    }

    public Deck getPlayerDeck() {
        return this.deck;
    }

    public Program getPlayerProgram() {
        return this.program;
    }

    public int getScore() {
        return this.score;
    }

    public ArrayList<Wall> getWallsAvailable() {
        return this.wallsAvailable;
    }

    public void takeIceWall(){
        for(int i = 0; i<this.getWallsAvailable().size(); i++){
            if (this.getWallsAvailable().get(i).getName().equals("ice wall")){
                this.getWallsAvailable().remove(i);
                break;
            }
        }
    }

    public void takeStoneWall(){
        for(int i = 0; i<this.getWallsAvailable().size(); i++){
            if (this.getWallsAvailable().get(i).getName().equals("stone wall")){
                this.getWallsAvailable().remove(i);
                break;
            }
        }
    }

    public ArrayList<Card> getHandCards() {
        return this.handCards;
    }

    public int getNbOfPlayer() {
        return this.nbOfPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public void setOrientation(OrientationEnum orientation1) {
        this.orientation = orientation1;
    }

    public void setScore(int score1) {
        this.score = score1;
    }

    public void resetNbPlayer() {
        nbPlayer = 0;
    }

    public boolean canPlayIceWall() {
        for (int i = 0; i < this.getWallsAvailable().size(); i++) {
            if (this.getWallsAvailable().get(i).getName().equals("ice wall")) {
                return true;
            }
        }
        return false;
    }

    public boolean canPlayStoneWall() {
        boolean test = false;
        for (int i = 0; i < this.getWallsAvailable().size(); i++) {
            if (this.getWallsAvailable().get(i).getName().equals("stone wall")) {
                return true;
            }
        }
        return false;
    }

    public void buildWall(Game game) {
        int choice;
        int choiceX;
        int choiceY;
        boolean correctPlacement = false;

        if(this.canPlayIceWall() && this.canPlayStoneWall()){
            System.out.println("1 : build ice wall\n2 : build stone wall");
            do {
                choice = this.scanner.nextInt();
                this.scanner.nextLine();
            } while (choice < 1 || choice > 2);
        }
        else if (this.canPlayStoneWall()){
            System.out.println("You can only play a stone wall:");
            choice = 2;
        }
        else{
            System.out.println("You can only play an ice wall:");
            choice = 1;
        }

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
                String display = game.getGrid().getCase(choiceX, choiceY).getContents().getClass().getName();
                switch (display) {
                    case "game.other.Player":
                        correctPlacement = false;
                        System.out.println("impossible placement, a player is on this case");
                        break;
                    case "grid.Jewel":
                        correctPlacement = false;
                        System.out.println("impossible placement, a jewel is on this case");
                        break;
                    case "wall.StoneWall":
                        correctPlacement = false;
                        System.out.println("impossible placement, a stone wall is on this case");
                        break;
                    case "wall.IceWall":
                        correctPlacement = false;
                        System.out.println("impossible placement, an ice wall is on this case");
                        break;
                }
            } catch (NullPointerException e) {
                if (choice == 1) {
                    game.getGrid().getCase(choiceX, choiceY).setContents(new IceWall());
                    takeIceWall();
                    correctPlacement = true;
                }
                if (choice == 2) {
                    game.getGrid().getCase(choiceX, choiceY).setContents(new StoneWall());
                    if(game.playersCanAccessTojewel()){
                        takeStoneWall();
                        correctPlacement = true;
                    }
                    else {
                        System.out.println("impossible placement, the wall prevents the player from reaching the jewel");
                        game.getGrid().getCase(choiceX, choiceY).setContents(null);
                        correctPlacement = false;
                    }
                }

            }
        } while (correctPlacement == false);
    }

    public void fillProgram() {
        int choice;
        int counter;

        do {
            counter = 0;
            System.out.println("Choose your card to add to your deck (0 to stop)");
            for (Card card : this.handCards) {
                counter++;
                System.out.println(counter + " : " + card.getName());
            }
            do {
                choice = this.scanner.nextInt();
                this.scanner.nextLine();
            } while (choice > counter || choice < 0);
            if (choice != 0) {
                this.program.addACard(this.getHandCards().remove(choice - 1));
            }
        } while (choice != 0 && counter != 1);
    }

    public void playProgram(Game game){
        for (Card card : this.getPlayerProgram().getProgram()) {
            card.playCard(game);
            this.getPlayerDeck().addCardToDiscard(card);
        }
        this.getPlayerProgram().resetProgram();
    }


    public void putHandCardToDiscard() {
        int choice;
        int counter;

        do {
            counter = 0;
            System.out.println("Choose your card to add to your discard (0 to stop)");
            for (Card card : this.handCards) {
                counter++;
                System.out.println(counter + " : " + card.getName());
            }
            do {
                choice = this.scanner.nextInt();
                this.scanner.nextLine();
            } while (choice > counter || choice < 0);
            if (choice != 0) {
                this.deck.getDiscard().add(this.getHandCards().remove(choice - 1));
            }
            System.out.println(counter);
        } while (choice != 0 && counter != 1);
    }

    public void drawCards() {
        int numberOfHandCards = this.getHandCards().size();
        for (int i = 0; i < 5 - numberOfHandCards; i++) {
            if (this.getPlayerDeck().getDiscard().isEmpty() && this.getPlayerDeck().getDeck().isEmpty()){
                System.out.println("No more card in the deck");
            } else {
                this.getHandCards().add(this.deck.pickACard());
            }

        }
    }
}


