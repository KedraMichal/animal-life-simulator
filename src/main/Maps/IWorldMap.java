package main.Maps;

import main.MapElements.Animal;
import main.Others.Vector2d;

import java.util.List;

public interface IWorldMap {

    default List<Animal>  getAnimals(){
         return null;
     };
    boolean canMoveTo(Vector2d position);
    boolean place(Animal animal);
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);
}
