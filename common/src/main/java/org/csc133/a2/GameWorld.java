/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    private static GameWorld gameworld;

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
    private int totalFireArea = 0;

    private final int MAX_FUEL = 25000;
    public final int MAX_WATER = 1000;

    private GameWorld() {}

    public static GameWorld getGameWorld(){
       if (gameworld == null){
           gameworld = new GameWorld();
       }
       return gameworld;
    }

    public void init() {

        gameWon = false;
        numFiresOut = 0;
        gameIsOver = false;

        rand = new Random();

        river = new River(worldSize);
        helipad = new Helipad(worldSize);
        helicopter = new Helicopter(worldSize, helipad.getCenter(), MAX_FUEL);

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
            Fire tmpFire = new Fire(building1.getLocation(),
                    building1.getDimension(), rand);
            fires.add(tmpFire);
            building1.setFireInBuilding(tmpFire);
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            Fire tmpFire = new Fire(building2.getLocation(),
                    building2.getDimension(), rand);
            fires.add(tmpFire);
            building2.setFireInBuilding(tmpFire);
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            Fire tmpFire = new Fire(building3.getLocation(),
                    building3.getDimension(), rand);
            fires.add(tmpFire);
            building3.setFireInBuilding(tmpFire);
        }


        gameObjects = new ArrayList<>();
        gameObjects.add(river);
        gameObjects.add(helipad);
        gameObjects.add(buildings);
        gameObjects.add(fires);
        gameObjects.add(helicopter);

    }

    void tick() {
        helicopter.move();
        helicopter.loseFuel();
        for (int i = 0; i < fires.size(); i++) {
            Fire tmpFire = (Fire) fires.getGameObjects().get(i);
            if (rand.nextInt(60) == 0) {
                tmpFire.grow();
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
                exit();
            }
        } else if (gameIsOver) {
            if (Dialog.show("Game Over!!", "You did not win :(", "play again!",
                    "I'm done playing")) {
                init();
            } else {
                exit();
            }
        }
    }

    public void exit() {
        Display.getInstance().exitApplication();
    }

    public ArrayList<GameObject> getGameObjectCollection(){
        return gameObjects;
    }

    public void turnLeft(){helicopter.steerLeft();}

    public void turnRight(){helicopter.steerRight();}

    public void accelerate(){
        if (helicopter.canSpeedUp()) {
            helicopter.updateCurrentSpeed(1);
        }
    }

    public void brake(){
        if (helicopter.getCurrentSpeed() > 0) {
            helicopter.updateCurrentSpeed(-1);
        }
    }

    public void setDimension(Dimension worldSize) {this.worldSize = worldSize;}

    //DOES NOT WORK, NEEDS FIXING
    //implement extinguished state when fire is out
    public void fight() {
        int amtWater = helicopter.getCurrentWater();
        for (int i = 0; i < fires.size(); i++) {
            Fire tmpFire = (Fire) fires.getGameObjects().get(i);
            if (tmpFire.helicopterInRange(helicopter)) {
                tmpFire.shrink(amtWater / 10);
                //if the fire is out, remove it from fires and objects
                if (tmpFire.getSize() <= 0){
                    fires.remove(tmpFire);
                }
            }
        }
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
        return String.valueOf(helicopter.getHeading());
    }

    public String getHelicopterSpeed() {
        return String.valueOf(helicopter.getCurrentSpeed());
    }

    public String getHelicopterFuel() {
        return String.valueOf(helicopter.getFuelLevel());
    }

    public String getNumFires() {return String.valueOf(fires.size());}

    public String getTotalFireSize() {return String.valueOf(totalFireArea);}

    public String getTotalDamage() {return "ADD ME";}

    public String getLoss() {return "ADD ME";}
}
