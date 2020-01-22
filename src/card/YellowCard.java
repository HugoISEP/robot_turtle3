package card;

import game.other.Game;
import game.other.OrientationEnum;

public class YellowCard extends Card {

    public YellowCard() {
        this.name = "YellowCard";
    }

    public void playCard(Game game) {
        if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.SOUTH)) {
            game.getCurrentPlayer().setOrientation(OrientationEnum.EAST);
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.EAST)) {
            game.getCurrentPlayer().setOrientation(OrientationEnum.NORTH);
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.NORTH)) {
            game.getCurrentPlayer().setOrientation(OrientationEnum.WEST);
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.WEST)) {
            game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
        }
    }

    public String getName() {
        return this.name;
    }
}
