package main.Maps;

import main.MapElements.Animal;
import main.MapElements.Grass;
import main.Others.Vector2d;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Jungle extends AbstractWorldMap {


    private final Vector2d jungleLowerLeft = new Vector2d(0,0 );
    private Vector2d jungleUpperRight= new Vector2d(20,20 );
    private final List<Animal> animals = new LinkedList<>();
    private final List<Grass> grassList = new ArrayList<>();

    public Jungle(){}

    public Jungle(Vector2d jungleUpperRight){
        this.jungleUpperRight = jungleUpperRight;
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.getX() > this.jungleUpperRight.getX()-1 || position.getX() < 0 || position.getY() > this.jungleUpperRight.getY()-1 || position.getY() < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        this.animals.add(animal);
        return true;
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

    public Color getColor(){
        return new Color(99, 219, 7);
    }
}
