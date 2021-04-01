package main.AnimalWorldSimulator;


import main.MapElements.Animal;
import main.Maps.Jungle;

public class World {

    public static void main(String[] args){
        MapSimulation mapS = new MapSimulation(new Jungle(), 10);
        mapS.runGame();

//
//
//        Animal ani = new Animal(new Jungle());
//        System.out.println(ani.moveBasedOnGenes());
//
//        Animal ani2 = new Animal(new Jungle());
//
//        int[] res = ani.crossAnimal(ani2).getGensArrays();
//        System.out.println("########");
//        for (int an :res){
//            System.out.println(an);
//        }




//        try {
//            String[] arg = new String[]{"f", "b", "r", "l", "f"};
//            MoveDirection[] directions = new OptionsParser().parser(arg);
//            IWorldMap map = new GrassField(10);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//            // 2,3 3,3  - 2,3E 3,3W  2,3E
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
//
//            System.out.println(map);
//            for (Animal an : map.getAnimals().values()) {
//                System.out.println(an.getPosition());
//            }
//        }
//        catch (IllegalArgumentException exception){
//            System.out.println(exception);
//        }

}
}


