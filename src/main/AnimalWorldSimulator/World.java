package main.AnimalWorldSimulator;


import main.MapElements.Animal;
import main.Maps.Jungle;
import main.Others.Vector2d;

public class World {

    public static void main(String[] args){
        int startingAnimals = 10;
        int grassSpawn = 1;
        int mapWidth = 20;
        int mapHeight = 20;
        MapSimulation mapS = new MapSimulation(new Jungle(new Vector2d(mapWidth, mapHeight)), startingAnimals, grassSpawn);
        mapS.runGame();
    }
}


