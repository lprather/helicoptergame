package org.csc133.a2;

import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.gameobjects.*;

import java.util.ArrayList;

public class GameWorld {

    private River river;
    private ArrayList<GameObject> gameObjects;
    private Dimension worldSize;
    private Helipad helipad;
    private Group buildings;
    private Group fires;
    /*private Helicopter helicopter;
    private boolean gameIsOver;
    private int numFiresOut;
    private boolean gameWon;*/

    //public final int MAX_WATER = 1000;

    public GameWorld() {}

    public void init() {

        /*gameWon = false;
        numFiresOut = 0;
        gameIsOver = false;

        helicopter = new Helicopter(helipad.getCenter());
        fires = new Fire[3];;*/

        river = new River(worldSize);
        helipad = new Helipad(worldSize);

        buildings = new Group();
        buildings.add(new Building(worldSize,
                new Point2D(worldSize.getWidth()/7, worldSize.getHeight()/22),
                new Dimension(5*worldSize.getWidth()/7,
                worldSize.getHeight()/9))); //above river
        buildings.add(new Building(worldSize,
                new Point2D(worldSize.getWidth()/16, worldSize.getHeight()/2.25),
                new Dimension(worldSize.getWidth()/10,
                        (int)(worldSize.getHeight()/2.5)))); //left
        buildings.add(new Building(worldSize,
                new Point2D(13*worldSize.getWidth()/16,
                        worldSize.getHeight()/2.25),
                new Dimension(worldSize.getWidth()/10,
                        worldSize.getHeight()/3))); //right

        fires = new Group();
        fires.add(new Fire(new Point2D(400,400), new Dimension(100,100)));

        gameObjects = new ArrayList<>();
        gameObjects.add(river);
        gameObjects.add(helipad);
        gameObjects.add(buildings);
        gameObjects.add(fires);

    }

    void draw(Graphics g) {
        //g.clearRect(0, 0, Game.DISP_W, Game.DISP_H);
        //g.setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN,
                //Font.SIZE_MEDIUM)); //setting font size for readability
        //river.draw(g, river.getRiverLocation());
        /*for (Fire fire : fires) {
            fire.draw(g);
        }
        helipad.draw(g);
        helicopter.draw(g);*/
    }

    void tick() {
        /*helicopter.move();
        helicopter.loseFuel();
        if (rand.nextInt(40) == 0) {
            for (int i = 0; i < 3; i++) {
                //if the fire size is not larger than 0
                //set it off-screen and tell game it's out
                if (fires[i].grow() == -1 && fires[i].getXCoord() >= 0) {
                    fires[i] = new Fire(-1000, -1000);
                    numFiresOut++;
                }
            }
        }
        if (helicopter.hasLanded(helipad.getCenter()) && helicopter.hasMoved()
                && numFiresOut == 3) {
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
        }*/
    }

    public void quit() {
        Display.getInstance().exitApplication();
    }

    public ArrayList<GameObject> getGameObjectCollection(){
        return gameObjects;
    }

    public void handleInputKey(int input) {
        /*if (input == -91) { //speed increase
            if (helicopter.canSpeedUp()) {
                helicopter.updateCurrentSpeed(1);
            }
        } else if (input == -92) { //speed decrease
            if (helicopter.getCurrentSpeed() > 0) {
                helicopter.updateCurrentSpeed(-1);
            }
        } else if (input == -93) { //turn right
            helicopter.updateHeading(-1);
        } else if (input == -94) { //turn left
            helicopter.updateHeading(1);
        }*/
    }

    public void setDimension(Dimension worldSize) {
        this.worldSize = worldSize;
    }

    public Dimension getDimension() {
        return worldSize;
    }

    /*public void fightFire() {
        int amtWater = helicopter.getCurrentWater();
        for (Fire fire : fires) {
            if (fire.helicopterInRange(helicopter) && fire.getSize() > 0) {
                fire.shrink(amtWater / 10);
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
    }*/

}
