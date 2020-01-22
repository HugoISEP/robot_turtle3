package card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayDeque<Card> deck;
    private ArrayList<Card> deck_for_shuffle;
    private ArrayList<Card> discard;

    public Deck() {
        deck = new ArrayDeque<>();
        deck_for_shuffle = new ArrayList<>();  //permet juste de mélanger les cartes
        discard = new ArrayList<>();           //la défausse
        //création du deck
        for (int i = 0; i < 18; i++) {
            this.deck_for_shuffle.add(new BlueCard());
        }

        for (int i = 0; i < 8; i++) {
            this.deck_for_shuffle.add(new YellowCard());
        }

        for (int i = 0; i < 8; i++) {
            this.deck_for_shuffle.add(new PurpleCard());
        }

        for (int i = 0; i < 3; i++) {
            this.deck_for_shuffle.add(new LaserCard());
        }
        shuffleDeck();
    }

    public ArrayList<Card> getDeck_for_shuffle() {
        return this.deck_for_shuffle;
    }

    public ArrayDeque<Card> getDeck() {
        return this.deck;
    }

    public ArrayList<Card> getDiscard() {
        return this.discard;
    }

    public void setDeck_for_shuffle(ArrayList<Card> deck_for_shuffle) {
        this.deck_for_shuffle = new ArrayList<>(deck_for_shuffle);
    }

    public void addCardToDiscard(Card card) {
        this.discard.add(card);
    }

    //quand le deck est vide, on le remplit à partir de la défausse
    public void fillDeck() {
        this.deck_for_shuffle.clear();
        for (int i = 0; i < this.discard.size(); i++) {
            this.deck_for_shuffle.add(this.discard.get(i));
        }
        this.discard.clear();
        shuffleDeck();
    }

    private void shuffleDeck() {
        Collections.shuffle(this.deck_for_shuffle);
        //on transfert le deck de cartes mélangées à la pile (ArrayDeque) deck de cartes.
        for (int i = 0; i < this.deck_for_shuffle.size(); i++) {
            this.deck.addFirst(this.deck_for_shuffle.get(i));
        }
    }

    public Card pickACard() {
        //on vérifie que le deck n'est pas vide sinon on le rempli à partir de la défausse
        if (this.deck.peekFirst() == null) {
            fillDeck();
        }
        return this.deck.pollFirst();
    }


}