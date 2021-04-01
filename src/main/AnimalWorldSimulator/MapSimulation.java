package main.AnimalWorldSimulator;

import main.MapElements.Animal;
import main.MapElements.Grass;
import main.Maps.Jungle;
import main.Others.Vector2d;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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
        this.timer = new Timer(1, this);
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
        this.animalsMove();
        this.grassConsumption();
        this.animalsMinusLive();
        this.reproduceAnimals();
        this.removeDeadAnimals();
        this.map.spawnGrass(1);

        for (Animal ani: this.map.getAnimals()){
            System.out.println(ani.getAnimalLives());
        }
    }

    public void animalsMove(){
        for(Animal ani: this.map.getAnimals()){
            ani.move(ani.moveBasedOnGenes());
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
        for(Animal ani: this.map.getAnimals()){
            ani.setAnimalLives(-1);
        }
    }

    public void removeDeadAnimals(){
        List<Animal> animalList = new ArrayList<>();
        if(!this.map.getAnimals().isEmpty()){
            for(Animal ani: this.map.getAnimals()){
                if(ani.getAnimalLives() < 0){
                    animalList.add(ani);
                }
            }
            for(Animal ani: animalList){
                this.map.getAnimals().remove(ani);
            }
        }
    }

    public void reproduceAnimals(){
        Set<Vector2d> animalPositionsUnique = new HashSet<>();
        for (Animal ani: this.map.getAnimals()){
            animalPositionsUnique.add(ani.getPosition());
        }
        List<Animal> producedAnimals = new ArrayList<>();
        List<Animal> parents = new ArrayList<>();
        for (Vector2d posit: animalPositionsUnique){
            List<Animal> animalsOnThisPosition = new ArrayList<>();

            for (Animal ani: this.map.getAnimals()){
                if (ani.getPosition().equals(posit)){
                    animalsOnThisPosition.add(ani);
                }
                Collections.sort(animalsOnThisPosition);
                if (animalsOnThisPosition.size() > 1){
                    if (animalsOnThisPosition.get(0).getAnimalLives() > 10 && animalsOnThisPosition.get(1).getAnimalLives() > 10){
                        Animal producedAnimal = new Animal(this.map, animalsOnThisPosition.get(0).getPosition());
                        producedAnimals.add(producedAnimal);
                        parents.add(animalsOnThisPosition.get(0));
                        parents.add(animalsOnThisPosition.get(1));
                    }
                }
            }
            }
        for (int i=0; i<parents.size(); i=i+2){
            System.out.println(parents.get(i).getAnimalLives());
            parents.get(i).lowerEnergyAfterReproduce();
            System.out.println(parents.get(i).getAnimalLives());
            parents.get(i+1).lowerEnergyAfterReproduce();
        }
        for (Animal ani: producedAnimals) {
            this.map.place(ani);
        }
        parents.clear();

    }

    public void grassConsumption(){
        List<Animal> animalList = new ArrayList<>();
        for (Grass grass: this.map.getGrassList()){
            Animal  strongestAnimal = null;
            int i = 0;
            for (Animal ani: this.map.getAnimals()){
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
                ani.setAnimalLives(100);
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
