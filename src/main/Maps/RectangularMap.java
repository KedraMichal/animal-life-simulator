package main.Maps;

import main.MapVisualizer;
import main.Maps.AbstractWorldMap;
import main.Vector2d;

public class RectangularMap extends AbstractWorldMap {

    final private int width;
    final private int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) { // TODO super
        if (animals.containsKey(position)){
            return false;
        }
         if (position.x > width || position.x < 0 || position.y > height || position.y < 0){
             return false;
         }
        return true;
    }

    @Override
    public String toString() {
        MapVisualizer mapVis = new MapVisualizer(this);
        return mapVis.draw(new Vector2d(0,0), new Vector2d(width, height));
    }
}
