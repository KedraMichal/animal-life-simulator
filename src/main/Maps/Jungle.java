package main.Maps;

import main.Animal;
import main.Grass;
import main.MoveDirection;
import main.Vector2d;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Jungle extends AbstractWorldMap {


    Vector2d jungleLowerLeft = new Vector2d(0,0 );
    Vector2d jungleUpperRight= new Vector2d(20,20 );
    int animalsNumber;
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private List<Grass> grassList = new ArrayList<>();


    @Override
    public Map<Vector2d, Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (animals.containsKey(position)){
            return false;
        }
        if (position.x > this.jungleUpperRight.x-1 || position.x < 0 || position.y > this.jungleUpperRight.y-2 || position.y < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        for (Vector2d position : animals.keySet()) {
            if (position.equals(animal.getPosition())) {
                return false;
//                throw new IllegalArgumentException("Position: " + position + " is already taken");
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


    public Vector2d getJungleLowerLeft() {
        return jungleLowerLeft;
    }

    public Vector2d getJungleUpperRight() {
        return jungleUpperRight;
    }

    public List<Grass> getGrassList() {
        return grassList;
    }

    public void spawnGrass(int howManyGrass){
        int sizeNeeded = this.grassList.size() + howManyGrass;
        while(sizeNeeded > this.grassList.size()){
            Grass grassToAdd = new Grass(this);
            this.grassList.add(grassToAdd);
        }
    }
}
