package test;

import main.MapElements.Animal;
import main.Maps.Jungle;
import main.Moves.MoveDirection;
import main.Others.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AnimalTest {

    @Test
    public void move() {
        Jungle jungle  = new Jungle();
        Animal ani = new Animal(jungle, new Vector2d(2, 2));
        ani.move(MoveDirection.FORWARD);
        assertEquals(ani.getPosition(), new Vector2d(2, 3));
        ani.move(MoveDirection.RIGHT);
        assertEquals(ani.getPosition(), new Vector2d(2, 3));
        ani.move(MoveDirection.FORWARD);
        assertEquals(ani.getPosition(), new Vector2d(3, 3));
        ani.move(MoveDirection.BACKWARD);
        assertEquals(ani.getPosition(), new Vector2d(2, 3));
    }
}