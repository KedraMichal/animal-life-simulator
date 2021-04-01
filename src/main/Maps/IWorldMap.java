package main.Maps;

import main.MapElements.Animal;
import main.IPositionChangeObserver;
import main.Others.Vector2d;

import java.util.List;

public interface IWorldMap extends IPositionChangeObserver {

    default List<Animal>  getAnimals(){
         return null;
     };
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    boolean canMoveTo(Vector2d position);
    boolean place(Animal animal);
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);
}
