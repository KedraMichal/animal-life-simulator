package main;


import main.Maps.IMapElement;
import main.Maps.Jungle;
import java.util.concurrent.ThreadLocalRandom;

public class Grass  implements IMapElement {
    final private Vector2d position;
    final private Jungle map;

    public Grass(Jungle map) {
        this.map = map;
        int xRandomPosition = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().x);
        int yRandomPosition = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().y);
        this.position = new Vector2d(xRandomPosition, yRandomPosition);
    }

    public Vector2d getPosition() {
        return position;
    }

    public Jungle getMap() {
        return map;
    }



}