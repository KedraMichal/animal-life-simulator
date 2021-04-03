package main.Others;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Genes {

    private int[] gensArrays = new int[32];

    public Genes(){
        for(int i=0; i<this.gensArrays.length; i++){
            this.gensArrays[i] = ThreadLocalRandom.current().nextInt(0,4);
        }
        Arrays.sort(this.gensArrays);
    }

    public Genes(int[] gensArrays){
        this.gensArrays = gensArrays;
        Arrays.sort(this.gensArrays);
    }

    public int[] getGensArrays() {
        return gensArrays;
    }
}
