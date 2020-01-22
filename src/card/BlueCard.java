package card;

import game.other.Game;
import game.other.OrientationEnum;

public class BlueCard extends Card {

    public BlueCard() {
        this.name = "BlueCard";
    }

    public void playCard(Game game) {
        //on récupère les coordonnées du joueur
        int x = game.getPlayerPositionX(game.getCurrentPlayer()), y = game.getPlayerPositionY(game.getCurrentPlayer());

        //conditions si le joueur va sortir du plateau
        if ((x == 7 && game.getCurrentPlayer().getOrientation().equals(OrientationEnum.EAST)) || (x == 0 && game.getCurrentPlayer().getOrientation().equals(OrientationEnum.WEST)) || (y == 0 && game.getCurrentPlayer().getOrientation().equals(OrientationEnum.NORTH)) || (y == 7 && game.getCurrentPlayer().getOrientation().equals(OrientationEnum.SOUTH))) {
            game.goToDeparturePosition(game.getCurrentPlayer());
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.EAST)) {
            try {
                //on regarde ce qu'il y a dans la case ou la tortue veut aller
                String display = game.getGrid().getCase(x + 1, y).getContents().getClass().getName();
                switch (display) {
                    case "game.other.Player":
                        //remettre à la case du début
                        //recherche du joueur sur la case d'après
                        for (int i = 0; i < game.getNumberOfplayer(); i++) {
                            if (game.getPlayer(i) == game.getGrid().getCase(x + 1, y).getContents()) {
                                game.goToDeparturePosition(game.getPlayer(i));
                                game.goToDeparturePosition(game.getCurrentPlayer());
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.playerHasWon();
                        break;
                    case "wall.StoneWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.WEST);
                        break;
                    case "wall.IceWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.WEST);
                        break;
                }
            } catch (NullPointerException e) {
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x + 1, y).setContents(game.getCurrentPlayer());
            }
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.WEST)) {
            try {
                String display = game.getGrid().getCase(x - 1, y).getContents().getClass().getName();
                switch (display) {
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++) {
                            if (game.getPlayer(i) == game.getGrid().getCase(x - 1, y).getContents()) {
                                game.goToDeparturePosition(game.getPlayer(i));
                                game.goToDeparturePosition(game.getCurrentPlayer());
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.playerHasWon();
                        break;
                    case "wall.StoneWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.EAST);
                        break;
                    case "wall.IceWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.EAST);
                        break;
                }
            } catch (NullPointerException e) {
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x - 1, y).setContents(game.getCurrentPlayer());
            }
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.NORTH)) {
            try {
                String display = game.getGrid().getCase(x, y - 1).getContents().getClass().getName();
                switch (display) {
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++) {
                            if (game.getPlayer(i) == game.getGrid().getCase(x, y - 1).getContents()) {
                                game.goToDeparturePosition(game.getPlayer(i));
                                game.goToDeparturePosition(game.getCurrentPlayer());
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.playerHasWon();
                        break;
                    case "wall.StoneWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                        break;
                    case "wall.IceWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                        break;
                }
            } catch (NullPointerException e) {
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x, y - 1).setContents(game.getCurrentPlayer());
            }
        } else if (game.getCurrentPlayer().getOrientation().equals(OrientationEnum.SOUTH)) {
            try {
                String display = game.getGrid().getCase(x, y + 1).getContents().getClass().getName();
                switch (display) {
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++) {
                            if (game.getPlayer(i) == game.getGrid().getCase(x, y + 1).getContents()) {
                                game.goToDeparturePosition(game.getPlayer(i));
                                game.goToDeparturePosition(game.getCurrentPlayer());
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.playerHasWon();
                        break;
                    case "wall.StoneWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.NORTH);
                        break;
                    case "wall.IceWall":
                        game.getCurrentPlayer().setOrientation(OrientationEnum.NORTH);
                        break;
                }
            } catch (NullPointerException e) {
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x, y + 1).setContents(game.getCurrentPlayer());
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
