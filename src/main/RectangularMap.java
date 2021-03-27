package main;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    int width;
    int height;
    private List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;

    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        for (Animal animalPlaced: animals) {
            if (animalPlaced.getPosition().equals(position)) {
                return false;
            }
        }
         if (position.x > width || position.x < 0 || position.y > height || position.y < 0){
             return false;
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
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animalPlaced : animals) {
            if (animalPlaced.getPosition().equals(position)) {
                return animalPlaced;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        MapVisualizer mapVis = new MapVisualizer(this);
        return mapVis.draw(new Vector2d(0,0), new Vector2d(width, height));
    }
}
