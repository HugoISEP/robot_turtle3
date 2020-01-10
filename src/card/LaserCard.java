package card;

import game.other.Game;
import game.other.OrientationEnum;
import game.other.Player;
import grid.Grid;

public class LaserCard extends Card {

    public LaserCard() {
        this.name = "laser card";
    }

    public void playCard(Game game, Player player) {
        int x = 0, y = 0;
        String display;
        boolean laserEnd = false, laserReflect = false;
        //on récupère les coordonnées du joueur
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.getGrid().getCase(j, i).getContents() == player) {
                    x = game.getGrid().getCase(j, i).getPositionX();
                    y = game.getGrid().getCase(j, i).getPositionY();
                }
            }
        }

        if (player.getOrientation().equals(OrientationEnum.NORTH)) {
            if (y != 0) {
                //on parcours la grille à partir de la case au dessus du joueur
                for (int i = y-1; i >= 0; i--) {
                    try {
                        //Si le laser est réfléchi
                        //on récupère le contenu de la case dans display
                        if (laserReflect == true) {
                            display = game.getGrid().getCase(x, 7 - i).getContents().getClass().getName();
                        } else {
                            display = game.getGrid().getCase(x, i).getContents().getClass().getName();
                        }
                        switch (display) {
                            case "game.other.Player":
                                for (int j = 0; j < game.getNumberOfplayer(); j++) {
                                    if (laserReflect == true) {
                                        //retourne à sa case départ si plus de 2 joueurs
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, 7 - i).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(x, 7 - i).setContents(null);
                                            game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        //s'il y a deux joueurs demi-tour
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, 7 - i).getContents()) && game.getNumberOfplayer() == 2) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    }
                                    //Si le laser n'est pas réfléchi
                                    else {
                                        //retourne à sa case départ si plus de 2 joueurs
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, i).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(x, i).setContents(null);
                                            game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        //s'il y a deux joueurs demi-tour
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, i).getContents()) && game.getNumberOfplayer() == 2) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    }
                                }
                                i = -1;
                                break;
                            case "grid.Jewel":
                                laserReflect = true;
                                i = 7 - i;
                                break;
                            case "wall.StoneWall":
                                i = -1;
                                break;
                            case "wall.IceWall":
                                game.getGrid().getCase(x, i).setContents(null);
                                i = -1;
                                break;
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.SOUTH)) {
            if (y < 7) {

                for (int i = y + 1; i <= 7; i++) {
                    try {
                        if (laserReflect == true) {
                            display = game.getGrid().getCase(x, 7 - i).getContents().getClass().getName();
                        } else {
                            display = game.getGrid().getCase(x, i).getContents().getClass().getName();
                        }
                        switch (display) {
                            case "game.other.Player":
                                for (int j = 0; j < game.getNumberOfplayer(); j++) {
                                    if (laserReflect == true) {
                                        //retourne à sa case ou change de sens
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, 7 - i).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(x, 7 - i).setContents(null);
                                            game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, 7 - i).getContents()) && game.getNumberOfplayer() == 2) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    } else {
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, i).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(x, i).setContents(null);
                                            game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(x, i).getContents()) && game.getNumberOfplayer() == 3) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    }
                                }
                                i = 8;
                                break;
                            case "grid.Jewel":
                                laserReflect = true;
                                i = 7 - i;
                                break;
                            case "wall.StoneWall":
                                i = 8;
                                break;
                            case "wall.IceWall":
                                game.getGrid().getCase(x, i).setContents(null);
                                i = 8;
                                break;
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.WEST)) {
            if (x != 0) {

                for (int i = x -1; i >= 0; i++) {
                    try {
                        if (laserReflect == true) {
                            display = game.getGrid().getCase(7 - i, y).getContents().getClass().getName();
                        } else {
                            display = game.getGrid().getCase(i, y).getContents().getClass().getName();
                        }
                        switch (display) {
                            case "game.other.Player":
                                for (int j = 0; j < game.getNumberOfplayer(); j++) {
                                    if (laserReflect == true) {
                                        //retourne à sa case ou change de sens
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(7-i, y).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(7-i, y).setContents(null);
                                            game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(7-i, y).getContents()) && game.getNumberOfplayer() == 2) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(i).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(i).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    } else {
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(i, y).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(i, y).setContents(null);
                                            game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(i, y).getContents()) && game.getNumberOfplayer() == 3) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    }
                                }
                                i = - 1;
                                break;
                            case "grid.Jewel":
                                laserReflect = true;
                                i = 7 - i;
                                break;
                            case "wall.StoneWall":
                                i = - 1;
                                break;
                            case "wall.IceWall":
                                game.getGrid().getCase(x, i).setContents(null);
                                i = - 1;
                                break;
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        else if (player.getOrientation().equals(OrientationEnum.EAST)) {
            if (x < 7) {

                for (int i = x + 1; i <= 7; i++) {
                    try {
                        if (laserReflect == true) {
                            display = game.getGrid().getCase(x, 7 - i).getContents().getClass().getName();
                        } else {
                            display = game.getGrid().getCase(x, i).getContents().getClass().getName();
                        }
                        switch (display) {
                            case "game.other.Player":
                                for (int j = 0; j < game.getNumberOfplayer(); j++) {
                                    if (laserReflect == true) {
                                        //retourne à sa case ou change de sens
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(7-i, y).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(7-i, y).setContents(null);
                                            game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(7-i, y).getContents()) && game.getNumberOfplayer() == 2) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    } else {
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(i, y).getContents()) && game.getNumberOfplayer() > 2) {
                                            game.getGrid().getCase(i, y).setContents(null);
                                            game.getCurrentPlayer().setOrientation(OrientationEnum.SOUTH);
                                            game.getDeparturePosition().get(j).setContents(game.getPlayer(j));
                                        }
                                        if (game.getPlayer(j).equals(game.getGrid().getCase(i, y).getContents()) && game.getNumberOfplayer() == 3) {
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.NORTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.SOUTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.EAST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.WEST);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.SOUTH)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.NORTH);
                                            }
                                            if (game.getPlayer(j).getOrientation().equals(OrientationEnum.WEST)) {
                                                game.getPlayer(j).setOrientation(OrientationEnum.EAST);
                                            }
                                        }
                                    }
                                }
                                i = 8;
                                break;
                            case "grid.Jewel":
                                laserReflect = true;
                                i = 7 - i;
                                break;
                            case "wall.StoneWall":
                                i = 8;
                                break;
                            case "wall.IceWall":
                                game.getGrid().getCase(x, i).setContents(null);
                                i = 8;
                                break;
                        }
                    } catch (NullPointerException e) {
                    }
                }
            }
        }
    }

    public String getName() {
        return this.name;
    }
}
