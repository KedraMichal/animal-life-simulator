package main.MapElements;


import main.Maps.Jungle;
import main.Others.Vector2d;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Grass  implements IMapElement {
    final private Vector2d position;
    final private Jungle map;

    public Grass(Jungle map) {
        this.map = map;
        int xRandomPosition = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().getX());
        int yRandomPosition = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().getY());
        this.position = new Vector2d(xRandomPosition, yRandomPosition);
    }

    public Vector2d getPosition() {
        return position;
    }

    public Jungle getMap() {
        return map;
    }

    @Override
    public Color getColor() {
        return new Color(110, 235, 52);
    }
}