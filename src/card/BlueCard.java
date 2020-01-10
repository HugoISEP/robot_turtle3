package card;

import game.other.Game;
import game.other.OrientationEnum;
import game.other.Player;
import grid.Grid;

public class BlueCard extends Card {

    public BlueCard() {
        this.name = "blue card";
    }

    public void playCard(Game game, Player player) {
        int x = 0, y = 0;
        //on récupère les coordonnées du joueur actuel
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (game.getGrid().getCase(j, i).getContents() == player){
                    x = game.getGrid().getCase(j , i).getPositionX();
                    y = game.getGrid().getCase(j , i).getPositionY();
                }
            }
        }

        //conditions si le joueur va sortir du plateau
        if (x == 7 && player.getOrientation().equals(OrientationEnum.EAST)){
            player.setOrientation(OrientationEnum.WEST);
        }

        else if (x == 0 && player.getOrientation().equals(OrientationEnum.WEST)){
            player.setOrientation(OrientationEnum.EAST);
        }

        else if (y == 0 && player.getOrientation().equals(OrientationEnum.NORTH)){
            player.setOrientation(OrientationEnum.SOUTH);
        }

        else if (y == 7 && player.getOrientation().equals(OrientationEnum.SOUTH)){
            player.setOrientation(OrientationEnum.NORTH);
        }


        else if (player.getOrientation().equals(OrientationEnum.EAST)){
            try {
                //on regarde ce qu'il y a dans la case ou la tortue veut aller
                String display = game.getGrid().getCase(x + 1, y).getContents().getClass().getName();
                switch(display){
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++){
                            if (game.getPlayer(i) == game.getGrid().getCase(x + 1, y).getContents()){
                                game.getGrid().getCase(x + 1, y).setContents(null);
                                game.getGrid().getCase(x, y).setContents(null);
                                game.getDeparturePosition().get(i).setContents(game.getPlayer(i));
                                game.getPlayer(i).setOrientation(OrientationEnum.SOUTH);
                                game.getDeparturePosition().get(game.getNumberOfCurrentPlayer()).setContents(player);
                                game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.getGrid().getCase(x + 1, y).setContents(player);
                        game.setEndGame(true);
                        game.setWinnerPlayer(game.getCurrentPlayer());
                        break;
                    case "wall.StoneWall":
                        player.setOrientation(OrientationEnum.WEST);
                        break;
                    case "wall.IceWall":
                        player.setOrientation(OrientationEnum.WEST);
                        break;
                }
            } catch (NullPointerException e){
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x + 1, y).setContents(player);
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.WEST)){
            try {
                String display = game.getGrid().getCase(x - 1, y).getContents().getClass().getName();
                switch(display){
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++){
                            if (game.getPlayer(i) == game.getGrid().getCase(x - 1, y).getContents()){
                                game.getGrid().getCase(x - 1, y).setContents(null);
                                game.getGrid().getCase(x, y).setContents(null);
                                game.getDeparturePosition().get(i).setContents(game.getPlayer(i));
                                game.getPlayer(i).setOrientation(OrientationEnum.SOUTH);
                                game.getDeparturePosition().get(game.getNumberOfCurrentPlayer()).setContents(player);
                                game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.getGrid().getCase(x - 1, y).setContents(player);
                        game.setEndGame(true);
                        game.setWinnerPlayer(game.getCurrentPlayer());
                        break;
                    case "wall.StoneWall":
                        player.setOrientation(OrientationEnum.EAST);
                        break;
                    case "wall.IceWall":
                        player.setOrientation(OrientationEnum.EAST);
                        break;
                }
            } catch (NullPointerException e){
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x - 1, y).setContents(player);
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.NORTH)){
            try {
                String display = game.getGrid().getCase(x, y - 1).getContents().getClass().getName();
                switch(display){
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++){
                            if (game.getPlayer(i) == game.getGrid().getCase(x, y - 1).getContents()){
                                game.getGrid().getCase(x, y - 1).setContents(null);
                                game.getGrid().getCase(x, y).setContents(null);
                                game.getDeparturePosition().get(i).setContents(game.getPlayer(i));
                                game.getPlayer(i).setOrientation(OrientationEnum.SOUTH);
                                game.getDeparturePosition().get(game.getNumberOfCurrentPlayer()).setContents(player);
                                game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.getGrid().getCase(x, y - 1).setContents(player);
                        game.setEndGame(true);
                        game.setWinnerPlayer(game.getCurrentPlayer());
                        break;
                    case "wall.StoneWall":
                        player.setOrientation(OrientationEnum.SOUTH);
                        break;
                    case "wall.IceWall":
                        player.setOrientation(OrientationEnum.SOUTH);
                        break;
                }
            } catch (NullPointerException e){
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x, y - 1).setContents(player);
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.SOUTH)){
            try {
                String display = game.getGrid().getCase(x, y + 1).getContents().getClass().getName();
                switch(display){
                    case "game.other.Player":
                        //remettre à la case du début
                        for (int i = 0; i < game.getNumberOfplayer(); i++){
                            if (game.getPlayer(i) == game.getGrid().getCase(x, y + 1).getContents()){
                                game.getGrid().getCase(x, y + 1).setContents(null);
                                game.getGrid().getCase(x, y).setContents(null);
                                game.getDeparturePosition().get(i).setContents(game.getPlayer(i));
                                game.getPlayer(i).setOrientation(OrientationEnum.SOUTH);
                                game.getDeparturePosition().get(game.getNumberOfCurrentPlayer()).setContents(player);
                                game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                            }
                        }
                        break;
                    case "grid.Jewel":
                        //vainqueur
                        game.getGrid().getCase(x, y).setContents(null);
                        game.getGrid().getCase(x, y + 1).setContents(player);
                        game.setEndGame(true);
                        game.setWinnerPlayer(game.getCurrentPlayer());
                        break;
                    case "wall.StoneWall":
                        player.setOrientation(OrientationEnum.NORTH);
                        break;
                    case "wall.IceWall":
                        player.setOrientation(OrientationEnum.NORTH);
                        break;
                }
            } catch (NullPointerException e){
                game.getGrid().getCase(x, y).setContents(null);
                game.getGrid().getCase(x, y + 1).setContents(player);
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
