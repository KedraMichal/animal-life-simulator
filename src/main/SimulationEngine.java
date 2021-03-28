package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap  map;
    private Vector2d[] positions;
    private List<Animal> animalOrder = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap  map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        this.positions = positions;
        for (Vector2d pos: this.positions){
            Animal ani = new Animal(this.map, pos);
            if(this.map.place(ani)){
                this.animalOrder.add(ani);
            };
        }
    }

    @Override
    public void run() {
        int howManyAnimals = animalOrder.size();
        int i = 0;
        for (MoveDirection move: this.moves){
            this.animalOrder.get(i).move(move);
            this.animalOrder.get(i).positionChanged(this.map);
            i += 1;
            if (i == howManyAnimals){
                i = 0;
            }
        }


    }
}
