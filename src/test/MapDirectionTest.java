package test;

import main.Moves.MapDirection;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    final MapDirection dir = MapDirection.EAST;
    final MapDirection dir2 = MapDirection.SOUTH;
    final MapDirection dir3 = MapDirection.WEST;
    final MapDirection dir4 = MapDirection.NORTH;

    @Test
    public void nextTest() {
        assertEquals(dir.next(), MapDirection.SOUTH);
        assertEquals(dir2.next(), MapDirection.WEST);
        assertEquals(dir3.next(), MapDirection.NORTH);
        assertEquals(dir4.next(), MapDirection.EAST);
    }

    @Test
    public void previousTest(){
        assertEquals(dir.previous(), MapDirection.NORTH);
        assertEquals(dir2.previous(), MapDirection.EAST);
        assertEquals(dir3.previous(), MapDirection.SOUTH);
        assertEquals(dir4.previous(), MapDirection.WEST);
    }



}