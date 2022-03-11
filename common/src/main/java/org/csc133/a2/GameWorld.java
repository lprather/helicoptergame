/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    private River river;
    private ArrayList<GameObject> gameObjects;
    private Dimension worldSize;
    private Helipad helipad;
    private Building building1;
    private Building building2;
    private Building building3;
    private Group buildings;
    private Group fires;
    private Random rand;
    private Helicopter helicopter;
    private boolean gameIsOver;
    private int numFiresOut;
    private boolean gameWon;

    public final int MAX_WATER = 1000;

    public GameWorld() {}

    public void init() {

        gameWon = false;
        numFiresOut = 0;
        gameIsOver = false;

        rand = new Random();

        river = new River(worldSize);
        helipad = new Helipad(worldSize);
        helicopter = new Helicopter(worldSize, helipad.getCenter());

        building1 = new Building(worldSize,
                new Point2D(worldSize.getWidth()/7, worldSize.getHeight()/22),
                new Dimension(5*worldSize.getWidth()/7,
                        worldSize.getHeight()/9)); //above river
        building2 = new Building(worldSize,
                new Point2D(worldSize.getWidth()/16,
                        worldSize.getHeight()/2.25),
                new Dimension(worldSize.getWidth()/10,
                        (int)(worldSize.getHeight()/2.5))); //left
        building3 = new Building(worldSize,
                new Point2D(13*worldSize.getWidth()/16,
                        worldSize.getHeight()/2.25),
                new Dimension(worldSize.getWidth()/10,
                        worldSize.getHeight()/3)); //right

        buildings = new Group();
        buildings.add(building1);
        buildings.add(building2);
        buildings.add(building3);

        fires = new Group();
        for (int i = rand.nextInt(2); i <= 2; i++){
            fires.add(new Fire(building1.getLocation(),
                    building1.getDimension(), rand));
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            fires.add(new Fire(building2.getLocation(),
                    building2.getDimension(), rand));
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            fires.add(new Fire(building3.getLocation(),
                    building3.getDimension(), rand));
        }

        gameObjects = new ArrayList<>();
        gameObjects.add(river);
        gameObjects.add(helipad);
        gameObjects.add(buildings);
        gameObjects.add(fires);
        gameObjects.add(helicopter);

    }

    void draw(Graphics g) {}

    void tick() {
        helicopter.move();
        helicopter.loseFuel();
        if (rand.nextInt(40) == 0) {
            for (int i = 0; i < 3; i++) {
                //if the fire size is not larger than 0
                //set it off-screen and tell game it's out
                /*if (fires[i].grow() == -1 && fires[i].getXCoord() >= 0) {
                    fires[i] = new Fire(-1000, -1000);
                    numFiresOut++;
                }*/
            }
        }
        if (helicopter.hasLanded(helipad.getCenter(),
                helipad) && helicopter.hasMoved() && numFiresOut == 3) {
            gameIsOver = true;
            gameWon = true;
        } else if (helicopter.getFuelLevel() <= 0) {
            gameIsOver = true;
        }
        if (gameIsOver && gameWon) {
            if (Dialog.show("Game Over!!", "Your score is " +
                            helicopter.getFuelLevel(), "play again!",
                    "I'm done playing")) {
                init();
            } else {
                quit();
            }
        } else if (gameIsOver) {
            if (Dialog.show("Game Over!!", "You did not win :(", "play again!",
                    "I'm done playing")) {
                init();
            } else {
                quit();
            }
        }
    }

    public void quit() {
        Display.getInstance().exitApplication();
    }

    public ArrayList<GameObject> getGameObjectCollection(){
        return gameObjects;
    }

    public void handleInputKey(int input) {
        if (input == -91) { //speed increase
            if (helicopter.canSpeedUp()) {
                helicopter.updateCurrentSpeed(1);
            }
        } else if (input == -92) { //speed decrease
            if (helicopter.getCurrentSpeed() > 0) {
                helicopter.updateCurrentSpeed(-1);
            }
        } else if (input == -93) { //turn left
            helicopter.steerLeft();
        } else if (input == -94) { //turn right
            helicopter.steerRight();
        }
    }

    public void setDimension(Dimension worldSize) {
        this.worldSize = worldSize;
    }

    public void fightFire() {
        int amtWater = helicopter.getCurrentWater();
        /*for (int i = 0; i < fires.size(); i++) {
            if (fire.helicopterInRange(helicopter) && fire.getSize() > 0) {
                fire.shrink(amtWater / 10);
            }
        }*/
        helicopter.updateWaterLevel(-1);
    }

    public void drink() {
        if (river.getLowerRiverBound() > helicopter.getHVertLocation() &&
                helicopter.getHVertLocation() > river.getUpperRiverBound() &&
                helicopter.getCurrentSpeed() <= 2) {
            if (helicopter.getCurrentWater() < MAX_WATER) {
                helicopter.updateWaterLevel(1);
            }
        }
    }

    public String getHelicopterHeading() {
        return " " + helicopter.getHeading();
    }

    public String getHelicopterSpeed() {
        return " " + helicopter.getCurrentSpeed();
    }

    public String getHelicopterFuel() {
        return " " + helicopter.getFuelLevel();
    }

    public String getNumFires() {
        return " " + fires.size();
    }

    public String getTotalFireSize() {
        return "ADD ME";
    }

    public String getTotalDamage() {
        return "ADD ME";
    }

    public String getLoss() {
        return "ADD ME";
    }
}
