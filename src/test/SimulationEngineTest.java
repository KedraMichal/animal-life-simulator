package test;

import main.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    public void runTest(){
        String[] arg = new String[]{"f", "b", "r", "l", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parser(arg);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.getAnimals().get(0).getPosition(), new Animal(map, new Vector2d(2,3)).getPosition());
    }

}