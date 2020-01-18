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

    public boolean playIceWall() {
        boolean test = false;
        for (int i = 0; i < this.getWallsAvailable().size(); i++) {
            if (this.getWallsAvailable().get(i).getName().equals("ice wall")) {
                test = true;
                this.getWallsAvailable().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean playStoneWall() {
        boolean test = false;
        for (int i = 0; i < this.getWallsAvailable().size(); i++) {
            if (this.getWallsAvailable().get(i).getName().equals("stone wall")) {
                test = true;
                this.getWallsAvailable().remove(i);
                return true;
            }
        }
        return false;
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


