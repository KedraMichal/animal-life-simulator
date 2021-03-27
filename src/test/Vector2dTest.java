package test;


import main.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    final Vector2d vec1 = new Vector2d(2,-22);
    final Vector2d vec2 = new Vector2d(0,-22);
    final Vector2d vec3 = new Vector2d(2,-22);
    final Vector2d vec4 = new Vector2d(3,-21);

    @Test
    public void equalsTest(){
        assertTrue(vec1.equals(vec3));
        assertFalse(vec1.equals(vec2));
    }

    @Test
    public void toStringTest(){
        assertEquals(vec1.toString(), "(2, -22)");
    }

    @Test
    public void precedesTest(){
        assertTrue(vec1.precedes(vec1));
        assertFalse(vec4.precedes(vec1));
    }

    @Test
    public void followsTest(){
        assertTrue(vec1.follows(vec1));
        assertTrue(vec4.follows(vec1));
    }

    @Test
    public void upperRightTest(){
        assertEquals(vec1.upperRight(vec2), new Vector2d(2, -22));
    }

    @Test
    public void lowerLeftTest(){
        assertEquals(vec1.lowerLeft(vec2), new Vector2d(0, -22));
    }

    @Test
    public void addTest(){
        assertEquals(vec1.add(vec2), new Vector2d(2, -44));
    }

    @Test
    public void subtractTest(){
        assertEquals(vec1.subtract(vec2), new Vector2d(2, 0));
    }

    @Test
    public void oppositeTest(){
        assertEquals(vec1.opposite(), new Vector2d(-2, 22));
    }



}