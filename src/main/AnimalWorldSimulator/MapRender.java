package main.AnimalWorldSimulator;

import main.MapElements.Animal;
import main.MapElements.Grass;
import main.Maps.Jungle;

import javax.swing.*;
import java.awt.*;

public class MapRender extends JPanel {

    private final Jungle map;
    private final int widthScale;
    private final int heightScale;

    public MapRender(Jungle map) {
        this.map = map;
        this.setSize(500,500);
        this.widthScale = this.getWidth() / this.map.getJungleUpperRight().getX();
        this.heightScale = this.getHeight()/ this.map.getJungleUpperRight().getY();
    }

    // when JFrame needs to be refreshed, the JPanel instance's paintComponent() method is called.
    @Override
    public void paintComponent(Graphics g) {
        paintGrass(g);
        paintAnimals(g);
    }

    private void paintAnimals(Graphics g){
        for (Animal a : this.map.getAnimals()) {
            int y = (a.getPosition().getY()) * widthScale;
            int x = (a.getPosition().getX()) * heightScale;
            g.setColor(a.getColor());
            g.fillOval(x, y, (int) Math.floor(widthScale * 0.6), (int) Math.floor(heightScale * 0.6));
        }
    }

    private void paintGrass(Graphics g){
        for (Grass a : this.map.getGrassList()) {
            int y = (a.getPosition().getY()) * widthScale;
            int x = (a.getPosition().getX()) * heightScale;
            g.setColor(a.getColor());
            g.fillRect(x, y, (int) Math.floor(widthScale * 0.6), (int) Math.floor(heightScale * 0.6));
        }
    }
}


