package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements IWorldMap {

    int howManyGrass = 0;
    private List<Animal> animals = new ArrayList<>();
    List<Grass> grassPositions = new ArrayList<>();

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    public GrassField(int howManyGrass){
        this.howManyGrass = howManyGrass;
        for (int i = 0; i < howManyGrass; i++) { // TODO dodac ze rozne meijsca trawy
            int randomGrassPlace = ThreadLocalRandom.current().nextInt(0, (int) Math.floor(Math.sqrt(howManyGrass * 10)));
            int randomGrassPlace2 = ThreadLocalRandom.current().nextInt(0, (int) Math.floor(Math.sqrt(howManyGrass * 10)));
            this.grassPositions.add(new Grass(new Vector2d (randomGrassPlace, randomGrassPlace2)));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animalPlaced: animals) {
            if (animalPlaced.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        for (Animal animalPlaced: animals){
            if(animalPlaced.getPosition().equals(animal.getPosition())){
                return false;
            }
        }
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animalPlaced: animals){
            if(animalPlaced.getPosition().equals(position)){
                return true;
            }
        }
        for (Grass grassPlaced : grassPositions){
            if(grassPlaced.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animalPlaced : animals) {
            if (animalPlaced.getPosition().equals(position)) {
                return animalPlaced;
            }
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
            for (Animal animalPlaced : animals) {
                if (maxHeight < animalPlaced.getPosition().x){
                    maxHeight = animalPlaced.getPosition().x;
                }
                if (maxWidth < animalPlaced.getPosition().y){
                    maxWidth = animalPlaced.getPosition().y;
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
