package main.Maps;

import main.Animal;
import main.Grass;
import main.MoveDirection;
import main.Vector2d;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapSimulation implements ActionListener {

    private int startingAnimals;
    private Jungle map;
    public JFrame frame;
    public MapRender mapRender;
    public Timer timer;

    public int getStartingAnimals() {
        return startingAnimals;
    }

    public Jungle getMap() {
        return map;
    }

    public MapSimulation(Jungle map, int startingAnimals ){
        this.map  = map;
        this.startingAnimals = startingAnimals;
        this.timer = new Timer(100, this);
    }

    public void runGame(){
            this.animalsRandomSpawn();
            this.map.spawnGrass(25);

            System.out.println("xd" + this.map.getGrassList());
            this.frame = new JFrame("Animal life simulator");
            this.frame.setSize(600, 600);
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            this.frame.setVisible(true);
            this.mapRender = new MapRender(map, this);
//        JPanel j2 = new JPanel();
//        this.frame.add(j2);
            this.frame.add(mapRender);
            this.timer.start();

    }

    //It will executed when timer finished counting
    @Override
    public void actionPerformed(ActionEvent e) {
        this.mapChangeAfterDay();
        this.frame.repaint();
    }

    public void mapChangeAfterDay(){
        this.animalsRandomMove();
        this.grassConsumption();
        this.animalsMinusLive();
        this.removeDeadAnimals();
        this.map.spawnGrass(2);
    }

    public void animalsRandomMove(){
        List<Animal> animalList = new ArrayList<>();
        for(Animal ani: this.map.getAnimals().values()){
            ani.move(MoveDirection.getRandomDirection());
            animalList.add(ani);
        }
        for (Animal ani: animalList) {
            ani.positionChanged(this.map);
        }
    }

    public void animalsRandomSpawn(){
        for(int i = 0; i<this.startingAnimals; i++) {
            int randomAnimalPlaceX = ThreadLocalRandom.current().nextInt(0, this.map.jungleUpperRight.x);
            int randomAnimalPlaceY = ThreadLocalRandom.current().nextInt(0, this.map.jungleUpperRight.y);
            Animal animalToAdd = new Animal(this.map, new Vector2d(randomAnimalPlaceX, randomAnimalPlaceY));
            if (this.map.place(animalToAdd)) {
                int a =0;
            }
        }
    }

    public void animalsMinusLive(){
        for(Animal ani: this.map.getAnimals().values()){
            ani.setAnimalLives(-1);
        }
    }

    public void removeDeadAnimals(){
        List<Animal> animalList = new ArrayList<>();
        if(!this.map.getAnimals().isEmpty()){
            for(Animal ani: this.map.getAnimals().values()){
                if(ani.getAnimalLives() == 0){
                    animalList.add(ani);
                }
            }
            for(Animal ani: animalList){
                this.map.getAnimals().remove(ani.getPosition());
            }
        }
    }

    public void grassConsumption(){
        List<Animal> animalList = new ArrayList<>();
        for (Grass grass: this.map.getGrassList()){
            Animal  strongestAnimal = null;
            int i = 0;
            for (Animal ani: this.map.getAnimals().values()){
                if (ani.getPosition().equals(grass.getPosition())){
                    if (i == 0) {
                        strongestAnimal = ani;
                        i += 1;
                    }
                    Animal animalOnPosition = ani;
                    if (animalOnPosition.getAnimalLives() >= strongestAnimal.getAnimalLives()){
                        strongestAnimal = ani;
                    }
                }
            }
            animalList.add(strongestAnimal);
            animalList.removeAll(Collections.singletonList(null));

    }
        if(!this.map.getAnimals().isEmpty()) {
            List<Grass> grassHelp = new ArrayList();
            for (Animal ani : animalList) {
                ani.setAnimalLives(10);
                for (Grass grassX: this.map.getGrassList()){
                    if (grassX.getPosition().equals(ani.getPosition())){
                        grassHelp.add(grassX);
                    }
                }
            }
            this.map.getGrassList().removeAll(grassHelp);
        }
}
}
