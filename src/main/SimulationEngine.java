package main;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap  map;
    private Vector2d[] positions;

    public SimulationEngine(MoveDirection[] moves, IWorldMap  map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        this.positions = positions;
        for (Vector2d pos: this.positions){
            Animal ani = new Animal(this.map, pos);
            this.map.place(ani);
        }
    }

    @Override
    public void run() {
        int howManyAnimals = this.map.getAnimals().size();
        int i = 0;
        for (MoveDirection move: this.moves){
            Animal ani_i = this.map.getAnimals().get(i);
            ani_i.move(move);
            i += 1;
            if (i == howManyAnimals){
                i = 0;
            }
        }


    }
}
