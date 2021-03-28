package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {

    private int howManyGrass = 0;
    private List<Grass> grassPositions = new ArrayList<>();

    public GrassField(int howManyGrass){
        this.howManyGrass = howManyGrass;
        for (int i = 0; i < howManyGrass; i++) { // TODO dodac ze rozne meijsca trawy
            int randomGrassPlace = ThreadLocalRandom.current().nextInt(0, (int) Math.floor(Math.sqrt(howManyGrass * 10)));
            int randomGrassPlace2 = ThreadLocalRandom.current().nextInt(0, (int) Math.floor(Math.sqrt(howManyGrass * 10)));
            this.grassPositions.add(new Grass(new Vector2d (randomGrassPlace, randomGrassPlace2)));
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Grass grassPlaced : grassPositions){
            if(grassPlaced.getPosition().equals(position)){
                return true;
            }
        }
        if (animals.containsKey(position)){
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        for (Grass grassPlaced : grassPositions) {
            if (grassPlaced.getPosition().equals(position)) {
                return grassPlaced;
            }
        }
        return null;
    }
        @Override
        public String toString() {
            int maxHeight = 0;
            int maxWidth = 0;
            for (Vector2d posi : animals.keySet()) {
                if (maxHeight < posi.x){
                    maxHeight = posi.x;
                }
                if (maxWidth < posi.y){
                    maxWidth = posi.y;
                }
            }
            for (Grass grassPlaced : grassPositions) {
                if (maxHeight < grassPlaced.getPosition().x){
                    maxHeight = grassPlaced.getPosition().x;
                }
                if (maxWidth < grassPlaced.getPosition().y){
                    maxWidth = grassPlaced.getPosition().y;
                }
            }

            MapVisualizer mapVis = new MapVisualizer(this);
            return mapVis.draw(new Vector2d(0,0), new Vector2d(maxWidth+1, maxHeight+1));
        }
    }
