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
        for (Animal animalPlaced : animals) {
            if (animalPlaced.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        for (Animal animalPlaced : animals) {
            if (animalPlaced.getPosition().equals(animal.getPosition())) {
                throw new IllegalArgumentException("Position: " + animal.getPosition() + " is already taken");
            }
        }
        this.animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animalPlaced : animals) {
            if (animalPlaced.getPosition().equals(position)) {
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
}