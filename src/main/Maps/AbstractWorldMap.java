package main.Maps;

import main.Animal;
import main.IPositionChangeObserver;
import main.Vector2d;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (animals.containsKey(position)){
            return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        for (Vector2d position : animals.keySet()) {
            if (position.equals(animal.getPosition())) {
                throw new IllegalArgumentException("Position: " + position + " is already taken");
            }
        }
        this.animals.put(animal.getPosition(), animal);
        animal.addObserver(this);

        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position)){
            return true;
        };
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }
}
