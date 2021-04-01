package main;

import main.Maps.IMapElement;
import main.Maps.IWorldMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Animal implements IMapElement {

    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private int animalLives;
    private static final int startingLive = 10;


    public Animal(IWorldMap map){
        this.map = map;
        this.animalLives = 10;
    }


    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.animalLives = 10;
    }


    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public void move(MoveDirection moveDir) {
        switch (moveDir) {
            case FORWARD:
                this.moveForward();
                break;
            case BACKWARD:
                this.direction = this.direction.next().next();
                this.moveForward();
                this.direction = this.direction.next().next();
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
        }
    }

    private void moveForward() {
        switch (this.direction) {
            case NORTH:
                if(this.map.canMoveTo(this.position.add(new Vector2d(0, 1)))) {
                    this.position = this.position.add(new Vector2d(0, 1));
                    }
                break;
            case SOUTH:
                if (this.map.canMoveTo(this.position.add(new Vector2d(0, -1)))) {
                    this.position = this.position.add(new Vector2d(0, -1));
                }
                break;
            case WEST:
                if (this.map.canMoveTo(this.position.add(new Vector2d(-1, 0)))) {
                        this.position = this.position.add(new Vector2d(-1, 0));
                }
                break;
            case EAST:
                if (this.map.canMoveTo(this.position.add(new Vector2d(1, 0)))) {
                        this.position = this.position.add(new Vector2d(1, 0));
                    }
                break;
        }
    }

    @Override
    public String toString() { // TODO zmienione z org
        switch (this.direction){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            default:
                return "E";
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(IPositionChangeObserver observer){
        for (IPositionChangeObserver observerX: observers){
            Iterator<Vector2d> it = this.map.getAnimals().keySet().iterator();
            List<Vector2d> saved1 = new ArrayList<>();
            List<Vector2d> saved2 = new ArrayList<>();
            while (it.hasNext()) {
                Vector2d key = it.next();
                if (!this.map.getAnimals().get(key).getPosition().equals(key)){
                    saved1.add(key);
                    saved2.add(this.map.getAnimals().get(key).getPosition());
                }
            }
            for(int i=0; i <saved1.size(); i++){
                this.map.positionChanged(saved1.get(i), saved2.get(i));
            }
        }
    }


    public IWorldMap getMap() {
        return map;
    }

    public int getAnimalLives() {
        return animalLives;
    }

    public void setAnimalLives(int howManyLives) {
        this.animalLives += howManyLives;
    }

    public Color setColor(){
        if (0.75 * startingLive < this.animalLives) {
            return new Color(102, 0, 0);
        }
        if (0.5 * startingLive < this.animalLives) {
            return new Color(204, 0, 0);
        }
        if (0.25 * startingLive < this.animalLives) {
            return new Color(255, 77, 77);
        }
        return new Color(255, 153, 153);

    }



}


