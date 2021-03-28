package test;

import main.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    public void runTest(){
        String[] arg = new String[]{"f", "b", "r", "l", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parser(arg);
        IWorldMap map = new RectangularMap(6, 6);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Set<Vector2d> set1 = new HashSet<>();
        set1.add(new Vector2d(2,3));
        set1.add(new Vector2d(3,3));
        Set<Vector2d> set2 = new HashSet<>();
        for (Vector2d posit: map.getAnimals().keySet()){
            set2.add(posit);
        }
        assertEquals(set1, set2);

    }

}