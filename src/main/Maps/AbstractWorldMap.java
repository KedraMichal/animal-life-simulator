package main.Maps;

import main.MapElements.Animal;
import main.Others.Vector2d;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    protected List<Animal> animals = new LinkedList<>();

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
//        if (animals.containsKey(position)){
//            return false;
//        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
//        for (Vector2d position : animals.keySet()) {
//            if (position.equals(animal.getPosition())) {
//                throw new IllegalArgumentException("Position: " + position + " is already taken");
//            }
//        }
//        this.animals.put(animal.getPosition(), animal);
//        animal.addObserver(this);

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
//        if (animals.containsKey(position)){
//            return true;
//        };
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
//        if (animals.containsKey(position)){
//            return animals.get(position);
//        }
        return null;
    }

//    @Override
//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        Animal animal = this.animals.get(oldPosition);
//        this.animals.remove(oldPosition);
//        this.animals.put(newPosition, animal);
//    }
}
