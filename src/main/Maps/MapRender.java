package main.Maps;

import main.Animal;
import main.Grass;

import javax.swing.*;
import java.awt.*;

public class MapRender extends JPanel {

    public Jungle map;
    public MapSimulation simulation;

    public MapRender(Jungle map, MapSimulation simulation) {
        this.map = map;
        this.simulation = simulation;
        this.setSize(500,500);
    }

    // when JFrame needs to be refreshed, the JPanel instance's paintComponent() method is called.
    @Override
    protected void paintComponent(Graphics g) {
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = width / this.map.jungleUpperRight.x;
        int heightScale = height / this.map.jungleUpperRight.y;

        g.setColor(new Color(170, 224, 103));
        g.fillRect(0, 0, width, height);
        System.out.println("xdd");

        for (Animal a : this.map.getAnimals().values()) {
            g.setColor(a.setColor());
            int y = (a.getPosition().y) * widthScale;
            int x = (a.getPosition().x) * heightScale;
            g.fillOval(x, y, (int) Math.floor(widthScale * 0.6), (int) Math.floor(heightScale * 0.6));
        }


        for (Grass a : this.map.getGrassList()) {
            g.setColor(Color.green);
            g.setColor(new Color(170, 244, 53));
            int y = (a.getPosition().y) * widthScale;
            int x = (a.getPosition().x) * heightScale;
            g.fillRect(x, y, (int) Math.floor(widthScale * 0.6), (int) Math.floor(heightScale * 0.6));
        }
    }
}


