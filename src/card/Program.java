package card;

import game.other.Game;

import java.util.ArrayDeque;

public class Program {
    private ArrayDeque<Card> program;

    public Program() {
        this.program = new ArrayDeque<>();
    }

    public ArrayDeque<Card> getProgram() {
        return this.program;
    }

    public void resetProgram(){
        this.program = new ArrayDeque<>();
    }

    public void addACard(Card card) {
        this.program.addLast(card);
    }

}

