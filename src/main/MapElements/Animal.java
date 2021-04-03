package main.MapElements;

import main.*;
import main.Maps.IWorldMap;
import main.Moves.MapDirection;
import main.Moves.MoveDirection;
import main.Others.Genes;
import main.Others.Vector2d;

import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements IMapElement, Comparable<Animal> {

    private Vector2d position;
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;
    private int animalLive;
    private static final int startingLive = 150;
    private Genes genes = new Genes();


    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
        this.animalLive = startingLive;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, Genes genes){
        this.map = map;
        this.position = initialPosition;
        this.animalLive = 20;
        this.genes = genes;
    }

    public Genes getGenes() {
        return genes;
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

    public IWorldMap getMap() {
        return map;
    }

    public int getAnimalLive() {
        return animalLive;
    }

    public void setAnimalLive(int howManyLives) {
        this.animalLive += howManyLives;
    }

    public Color getColor(){
        if (0.75 * startingLive < this.animalLive) {
            return new Color(102, 0, 0);
        }
        if (0.5 * startingLive < this.animalLive) {
            return new Color(204, 0, 0);
        }
        if (0.25 * startingLive < this.animalLive) {
            return new Color(255, 77, 77);
        }
        return new Color(255, 153, 153);

    }

    public void lowerEnergyAfterReproduce(){
        int energyMinus =  (int) (Math.floor(this.getAnimalLive() * 0.75));
        this.setAnimalLive(-energyMinus);
    }

    public Genes crossAnimal(Animal a){
        int randomGenesCut = ThreadLocalRandom.current().nextInt(1,31);
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

        for (int gen : this.getGenes().getGensArrays()) {
            if (gen == 0) {
                moveForwardCounter++;
            } else if (gen == 1) {
                moveBackwardCounter++;
            } else if (gen == 2) {
                turnLeftCounter++;
            }
        }
        float probabilityForward = (float) moveForwardCounter / this.genes.getGensArrays().length;
        float probabilityBackward = (float) moveBackwardCounter / this.genes.getGensArrays().length;
        float probabilityLeft = (float) turnLeftCounter / this.genes.getGensArrays().length;

        float randomProb = (float) Math.random();
        if(randomProb<probabilityForward) return MoveDirection.FORWARD;
        else if(randomProb<probabilityForward+probabilityBackward) return MoveDirection.BACKWARD;
        else if (randomProb<probabilityForward+probabilityBackward+probabilityLeft) return MoveDirection.LEFT;
        else return MoveDirection.RIGHT;
    }

    @Override
    public int compareTo(Animal a) {
            if (this.getAnimalLive() == a.getAnimalLive()){
                return 0 ;
            }
            else if (this.getAnimalLive() > a.getAnimalLive()){
                return 1;
            }
            return 0;
    }
}


