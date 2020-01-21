package card;

import java.util.ArrayDeque;

public class Program {
    private ArrayDeque<Card> program;

    public Program() {
        this.program = new ArrayDeque<>();
    }

    public ArrayDeque<Card> getProgram() {
        return this.program;
    }

    public void resetProgram() {
        this.program.clear();
    }

    public void addACard(Card card) {
        this.program.addLast(card);
    }

}

