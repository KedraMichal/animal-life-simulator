package main.Others;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x || this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d upperRight(Vector2d other) {
        int upperX = Math.max(this.x, other.x);
        int upperY = Math.max(this.y, other.y);

        return new Vector2d(upperX, upperY);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int lowerX = Math.min(this.x, other.x);
        int lowerY = Math.min(this.y, other.y);

        return new Vector2d(lowerX, lowerY);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Vector2d){
            Vector2d oth = (Vector2d) other;
            if (this.x == oth.x && this.y == oth.y){
                return true;
            }

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
