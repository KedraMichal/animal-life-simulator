package main.Moves;

import main.Others.Vector2d;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        switch (this){
                case EAST:
                    return ("Wschód");
                case WEST:
                    return ("Zachód");
                case NORTH:
                    return ("Północ");
                case SOUTH:
                    return ("Południe");
            }
            return "";
    }

    public MapDirection next(){
        switch (this){
            case WEST:
                return MapDirection.NORTH;
            case NORTH:
                return MapDirection.EAST;
            case SOUTH:
                return MapDirection.WEST;
            default:
                return MapDirection.SOUTH;
        }
    }

    public MapDirection previous(){
        switch (this){
            case EAST:
                return MapDirection.NORTH;
            case NORTH:
                return MapDirection.WEST;
            case SOUTH:
                return MapDirection.EAST;
            default:
                return MapDirection.SOUTH;
        }

    }

    public Vector2d toUnitVector(){
        switch (this){
            case EAST:
                return new Vector2d(1, 0);
            case WEST:
                return new Vector2d(-1, 0);
            case NORTH:
                return new Vector2d(0, 1);
            case SOUTH:
                return new Vector2d(0, -1);
            default:
                return new Vector2d(0, 0);
        }
    }


}
