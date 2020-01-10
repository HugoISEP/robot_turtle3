package card;

import game.other.Game;
import game.other.OrientationEnum;
import game.other.Player;
import grid.Grid;

public class PurpleCard extends Card {

    public PurpleCard() {
        this.name = "purple card";
    }

    public void playCard(Game game, Player player) {
        if (player.getOrientation().equals(OrientationEnum.SOUTH)){
            player.setOrientation(OrientationEnum.WEST);
        }
        else if (player.getOrientation().equals(OrientationEnum.EAST)){
            player.setOrientation(OrientationEnum.SOUTH);
        }
        else if (player.getOrientation().equals(OrientationEnum.NORTH)){
            player.setOrientation(OrientationEnum.EAST);
        }
        else if (player.getOrientation().equals(OrientationEnum.WEST)){
            player.setOrientation(OrientationEnum.NORTH);
        }
    }

    public String getName() {
        return this.name;
    }
}
