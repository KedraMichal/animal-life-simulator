package main.Moves;

import java.util.Random;

public enum MoveDirection {
    FORWARD, BACKWARD, RIGHT, LEFT;


    private static final MoveDirection[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static MoveDirection getRandomDirection()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
