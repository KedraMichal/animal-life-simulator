package main;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Genes {

    private int[] gensArrays = new int[32];

    public Genes(){
        for(int i=0; i<this.gensArrays.length; i++){
            gensArrays[i] = ThreadLocalRandom.current().nextInt(0,4); //TODO poprawic indziej bound=4 daje 3
        }
        Arrays.sort(gensArrays);
    }

    public int[] getGensArrays() {
        return gensArrays;
    }
}
