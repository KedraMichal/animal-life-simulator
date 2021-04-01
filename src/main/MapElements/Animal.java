package main.MapElements;

import main.*;
import main.Maps.IWorldMap;
import main.Moves.MapDirection;
import main.Moves.MoveDirection;
import main.Others.Genes;
import main.Others.Vector2d;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IMapElement, Comparable<Animal> {

    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private int animalLives;
    private static final int startingLive = 10;
    private Genes genes = new Genes();

    public Genes getGenes() {
        return genes;
    }

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

//    public void positionChanged(IPositionChangeObserver observer){
//        for (IPositionChangeObserver observerX: observers){
//            Iterator<Vector2d> it = this.map.getAnimals().keySet().iterator();
//            List<Vector2d> saved1 = new ArrayList<>();
//            List<Vector2d> saved2 = new ArrayList<>();
//            while (it.hasNext()) {
//                Vector2d key = it.next();
//                if (!this.map.getAnimals().get(key).getPosition().equals(key)){
//                    saved1.add(key);
//                    saved2.add(this.map.getAnimals().get(key).getPosition());
//                }
//            }
//            for(int i=0; i <saved1.size(); i++){
//                this.map.positionChanged(saved1.get(i), saved2.get(i));
//            }
//        }
//    }


    public IWorldMap getMap() {
        return map;
    }

    public int getAnimalLives() {
        return animalLives;
    }

    public void setAnimalLives(int howManyLives) {
        this.animalLives += howManyLives;
    }

    public Color getColor(){
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

    public void lowerEnergyAfterReproduce(){
        int energyMinus =  (int) (Math.floor(this.getAnimalLives() * 0.75));
        this.setAnimalLives(-energyMinus);
    }

    public Genes crossAnimal(Animal a){
        int randomGenesCut = ThreadLocalRandom.current().nextInt(1,31);
        System.out.println(randomGenesCut);
        System.out.println(this.getGenes().getGensArrays());
        for (int an :this.getGenes().getGensArrays()){
            System.out.println(an);
        }
        int[] parent1Gens = Arrays.copyOfRange(this.getGenes().getGensArrays(), 1, randomGenesCut);
        int[] parent2Gens = Arrays.copyOfRange(this.getGenes().getGensArrays(), randomGenesCut, 31);
        int[] newGens = Arrays.copyOf(parent1Gens, parent1Gens.length + parent2Gens.length);
        System.arraycopy(parent2Gens, 0, newGens, parent1Gens.length, parent2Gens.length);

        return new Genes(newGens);
    }

    public MoveDirection moveBasedOnGenes() {
        int moveForwardCounter = 0;
        int moveBackwardCounter = 0;
        int turnLeftCounter = 0;
        int turnRightCounter = 0;
        for (int gen : this.getGenes().getGensArrays()) {
            if (gen == 0) {
                moveForwardCounter += 1;
            } else if (gen == 1) {
                moveBackwardCounter++;
            } else if (gen == 2) {
                turnLeftCounter++;
            } else {
                turnRightCounter++;
            }
        }
        System.out.println(moveBackwardCounter);
        float probabilityForward = (float) moveForwardCounter / 32;
        float probabilityBackward = (float) moveBackwardCounter / 32;
        float probabilityLeft = (float) turnLeftCounter / 32;
        float probabilityRight = (float) turnRightCounter / 32;

        float randomProb = (float) Math.random();
        if(randomProb<probabilityForward) return MoveDirection.FORWARD;
        else if(randomProb<probabilityForward+probabilityBackward) return MoveDirection.BACKWARD;
        else if (randomProb<probabilityForward+probabilityBackward+probabilityLeft) return MoveDirection.LEFT;
        else return MoveDirection.RIGHT;
    }



    @Override
    public int compareTo(Animal a) {
            if (this.getAnimalLives() == a.getAnimalLives()){
                return 0 ;
            }
            else if (this.getAnimalLives() > a.getAnimalLives()){
                return 1;
            }
            return 0;
    }
}


