package main.AnimalWorldSimulator;

import main.MapElements.Animal;
import main.MapElements.Grass;
import main.Maps.Jungle;
import main.Others.Vector2d;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapSimulation implements ActionListener {

    private final int startingAnimals;
    private final Jungle map;
    private JFrame frame;
    private MapRender mapRender;
    private final Timer timer;
    private final int grassSpawn;

    public MapSimulation(Jungle map, int startingAnimals, int grassSpawn){
        this.map  = map;
        this.startingAnimals = startingAnimals;
        this.timer = new Timer(10, this);
        this.grassSpawn = grassSpawn;
    }

    public void runGame(){
            this.animalsRandomSpawn();
            this.map.spawnGrass(10);
            this.frame = new JFrame("Animal life simulator");
            this.frame.setSize(550, 550);
            this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.frame.setLocationRelativeTo(null);
            this.frame.setVisible(true);
            this.frame.setBackground(this.map.getColor());
            this.mapRender = new MapRender(map);
            this.frame.add(mapRender);
            this.timer.start();
    }

    //execute when timer finish counting
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
        this.map.spawnGrass(grassSpawn);
    }

    private void animalsMove(){
        for(Animal ani: this.map.getAnimals()){
            ani.move(ani.moveBasedOnGenes());
        }
    }

    private void animalsRandomSpawn(){
        for(int i = 0; i<this.startingAnimals; i++) {
            int randomAnimalPlaceX = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().getX());
            int randomAnimalPlaceY = ThreadLocalRandom.current().nextInt(0, this.map.getJungleUpperRight().getY());
            Animal animalToAdd = new Animal(this.map, new Vector2d(randomAnimalPlaceX, randomAnimalPlaceY));
            this.map.place(animalToAdd);
        }
    }

    private void animalsMinusLive(){
        for(Animal ani: this.map.getAnimals()){
            ani.setAnimalLive(-1);
        }
    }

    private void removeDeadAnimals(){
        List<Animal> animalList = new ArrayList<>();
        if(!this.map.getAnimals().isEmpty()){
            for(Animal ani: this.map.getAnimals()){
                if(ani.getAnimalLive() < 0){
                    animalList.add(ani);
                }
            }
            for(Animal ani: animalList){
                this.map.getAnimals().remove(ani);
            }
        }
    }

    private void reproduceAnimals(){
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
                    if (animalsOnThisPosition.get(0).getAnimalLive() > 30 && animalsOnThisPosition.get(1).getAnimalLive() > 30){
                        Animal producedAnimal = new Animal(this.map, animalsOnThisPosition.get(0).getPosition(), animalsOnThisPosition.get(0).crossAnimal(animalsOnThisPosition.get(1)));
                        producedAnimals.add(producedAnimal);
                        parents.add(animalsOnThisPosition.get(0));
                        parents.add(animalsOnThisPosition.get(1));
                    }
                }
            }
            }
        for (int i=0; i<parents.size(); i=i+2){
            parents.get(i).lowerEnergyAfterReproduce();
            parents.get(i+1).lowerEnergyAfterReproduce();
        }
        for (Animal ani: producedAnimals) {
            this.map.place(ani);
        }
        parents.clear();

    }

    private void grassConsumption(){
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
                    if (animalOnPosition.getAnimalLive() >= strongestAnimal.getAnimalLive()){
                        strongestAnimal = ani;
                    }
                }
            }
            animalList.add(strongestAnimal);
            animalList.removeAll(Collections.singletonList(null));

    }
        if(!this.map.getAnimals().isEmpty()) {
            List<Grass> grassHelp = new ArrayList<>();
            for (Animal ani : animalList) {
                ani.setAnimalLive(10);
                for (Grass grassX: this.map.getGrassList()){
                    if (grassX.getPosition().equals(ani.getPosition())){
                        grassHelp.add(grassX);
                    }
                }
            }
            this.map.getGrassList().removeAll(grassHelp);
        }
    }

    public int getStartingAnimals() {
        return startingAnimals;
    }

    public Jungle getMap() {
        return map;
    }
}
