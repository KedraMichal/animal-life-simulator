package main;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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
