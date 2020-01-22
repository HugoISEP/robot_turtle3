package card;

import game.other.Game;
import game.other.OrientationEnum;

public class PurpleCard extends Card {

    public PurpleCard() {
        this.name = "PurpleCard";
    }

    public void playCard(Game gamer) {
        if (gamer.getCurrentPlayer().getOrientation().equals(OrientationEnum.SOUTH)) {
            gamer.getCurrentPlayer().setOrientation(OrientationEnum.WEST);
        } else if (gamer.getCurrentPlayer().getOrientation().equals(OrientationEnum.EAST)) {
            gamer.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
        } else if (gamer.getCurrentPlayer().getOrientation().equals(OrientationEnum.NORTH)) {
            gamer.getCurrentPlayer().setOrientation(OrientationEnum.EAST);
        } else if (gamer.getCurrentPlayer().getOrientation().equals(OrientationEnum.WEST)) {
            gamer.getCurrentPlayer().setOrientation(OrientationEnum.NORTH);
        }
    }

    public String getName() {
        return this.name;
    }
}
