package main;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {

    public MoveDirection[] parser(String[] arr){
        int arrLen = arr.length;
        MoveDirection[] movesArr = new MoveDirection[arrLen];
        int i = 0;
        for (String word: arr){
            switch(word){
                case "f": case "forward":
                    movesArr[i] = MoveDirection.FORWARD;
                    i += 1;
                    break;
                case "b": case "backward":
                    movesArr[i] = MoveDirection.BACKWARD;
                    i += 1;
                    break;
                case "r": case "right":
                    movesArr[i] = MoveDirection.RIGHT;
                    i += 1;
                    break;
                case "l": case "left":
                    movesArr[i] = MoveDirection.LEFT;
                    i += 1;
                    break;
                default:
                    throw new IllegalArgumentException(word + " is not legal move specification");
//                    break;
            }

        }

        return Arrays.stream(movesArr).filter(Objects::nonNull).toArray(MoveDirection[]::new);
    }


}
