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
    private String gameIsOver;
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
        gameIsOver = "";

        rand = new Random();

        //place river, helipad, and helicopter
        river = new River(worldSize);
        helipad = new Helipad(worldSize);
        helicopter = new Helicopter(worldSize, helipad.getCenter(), MAX_FUEL);

        //place buildings
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

        //for each of the buildings, place two or three fires inside
        //start the fires
        fires = new Group();
        for (int i = rand.nextInt(2); i <= 2; i++){
            Fire tmpFire = new Fire(building1.getLocation(),
                    building1.getDimension(), rand);
            fires.add(tmpFire);
            building1.addFire(tmpFire);
            building1.setFireInBuilding(tmpFire);
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            Fire tmpFire = new Fire(building2.getLocation(),
                    building2.getDimension(), rand);
            fires.add(tmpFire);
            building2.addFire(tmpFire);
            building2.setFireInBuilding(tmpFire);
        }
        for (int i = rand.nextInt(2); i <= 2; i++){
            Fire tmpFire = new Fire(building3.getLocation(),
                    building3.getDimension(), rand);
            fires.add(tmpFire);
            building3.addFire(tmpFire);
            building3.setFireInBuilding(tmpFire);
        }

        //adding game objects to collection
        gameObjects = new ArrayList<>();
        gameObjects.add(river);
        gameObjects.add(helipad);
        gameObjects.add(buildings);
        gameObjects.add(fires);
        gameObjects.add(helicopter);

    }

    void tick() {
        //move helicopter and lose fuel
        helicopter.move();
        helicopter.loseFuel();
        //randomly grow fires if they are still burning
        for (int i = 0; i < fires.size(); i++) {
            Fire tmpFire = (Fire) fires.getGameObjects().get(i);
            if (!tmpFire.isExtinguished()){
                if (rand.nextInt(60) == 0) {
                    tmpFire.grow();
                }
            }
        }
        //determining if the game is over and the reason why
        if (helicopter.hasLanded(helipad.getCenter(),
                helipad) && helicopter.hasMoved() && getNumFires() == 0) {
            gameIsOver = "Fires all out";
            gameWon = true;
        } else if (helicopter.getFuelLevel() <= 0) {
            gameIsOver = "You ran out of fuel!";
        } else if (getTotalDamage() == 100){
            gameIsOver = "The buildings were destroyed!";
        }
        //if the game is over, display score or reason for loss and give
        //options to replay or quit
        if (!gameIsOver.equals("") && gameWon) {
            if (Dialog.show("Game Over!!", "Your score is " +
                            getWinningScore(), "play again!",
                    "I'm done playing")) {
                init();
            } else {
                exit();
            }
        } else if (!gameIsOver.equals("")) {
            if (Dialog.show("Game Over!!", gameIsOver, "play again!",
                    "I'm done playing")) {
                init();
            } else {
                exit();
            }
        }
    }

    //exits the game. called from exit command
    public void exit() {Display.getInstance().exitApplication();}

    //getter for game objects. needed so they can be drawn from map view
    public ArrayList<GameObject> getGameObjectCollection(){
        return gameObjects;
    }

    //steers helicopter to the left
    public void turnLeft(){helicopter.steerLeft();}

    //steers helicopter to the right
    public void turnRight(){helicopter.steerRight();}

    //speeds up helicopter
    public void accelerate(){
        if (helicopter.canSpeedUp()) {
            helicopter.updateCurrentSpeed(1);
        }
    }

    //slows down helicopter
    public void brake(){
        if (helicopter.getCurrentSpeed() > 0) {
            helicopter.updateCurrentSpeed(-1);
        }
    }

    public void setDimension(Dimension worldSize) {this.worldSize = worldSize;}

    //fights fires by dropping water
    public void fight() {
        int amtWater = helicopter.getCurrentWater();
        for (int i = 0; i < fires.size(); i++) {
            Fire tmpFire = (Fire) fires.getGameObjects().get(i);
            if (tmpFire.isBurning()){ //if fire is burning
                //if the helicopter is over the fire
                if (tmpFire.helicopterInRange(helicopter)) {
                    //shrink the fire based on amount of water helicopter has
                    tmpFire.shrink(amtWater / 10);
                    //if the fire has no area, set it to extinguished
                    if (tmpFire.getSize() <= 0){
                        tmpFire.setExtinguished();
                    }
                }
            }

        }
        //drain all water from the helicopter
        helicopter.updateWaterLevel(-1);
    }

    //attempts to drink water if in range of the river, speed of the helicopter
    //is less than or equal to 2, and there is capacity in the tank
    public void drink() {
        if (river.getLowerRiverBound() > helicopter.getHVertLocation() &&
                helicopter.getHVertLocation() > river.getUpperRiverBound() &&
                helicopter.getCurrentSpeed() <= 2) {
            if (helicopter.getCurrentWater() < MAX_WATER) {
                helicopter.updateWaterLevel(1);
            }
        }
    }

    //returns current heading of helicopter
    public String getHelicopterHeading() {
        return String.valueOf(helicopter.getHeading());
    }

    //returns current speed of helicopter
    public String getHelicopterSpeed() {
        return String.valueOf(helicopter.getCurrentSpeed());
    }

    //returns current fuel level of helicopter
    public String getHelicopterFuel() {
        return String.valueOf(helicopter.getFuelLevel());
    }

    //returns number of fires burning as an int
    public int getNumFires(){
        int num = 0;
        for (int i = 0; i < fires.size(); i++){
            Fire tmp = (Fire) fires.getGameObjects().get(i);
            if (!tmp.isExtinguished()){
                num++;
            }
        }
        return num;
    }

    //returns number of fires burning as a string
    public String getNumFiresAsString() {
        return String.valueOf(getNumFires());
    }

    //returns size of all fires combined
    public String getTotalFireSize() {
        totalFireArea = 0;
        for (int i = 0; i < fires.size(); i++){
            Fire tmp = (Fire) fires.getGameObjects().get(i);
            if (tmp.isBurning()){
                totalFireArea += tmp.getSize();
            }
        }
        return String.valueOf(totalFireArea);
    }

    //returns total damage to buildings as a string
    public String getTotalDamageAsString() {return getTotalDamage() + "%";}

    //returns total damage to buildings as an int
    public int getTotalDamage(){
        int totalDamage = 0;
        for (int i = 0; i < buildings.size(); i++){
            Building tmpB = (Building) buildings.getGameObjects().get(i);
            totalDamage += tmpB.getBuildingDamage();
        }
        totalDamage /= 3;
        return totalDamage;
    }

    //returns loss incurred
    public String getLoss() {
        return String.valueOf(getTotalDamage()*(getBuildingCost()/100));
    }

    //returns total cost of buildings
    private int getBuildingCost(){
        int bArea = 0;
        for (int i = 0; i < buildings.size(); i++){
            Building tmpB = (Building) buildings.getGameObjects().get(i);
            bArea += tmpB.getCost();
        }
        return bArea;
    }

    private int getWinningScore(){
        return 100 - getTotalDamage();
    }
}
