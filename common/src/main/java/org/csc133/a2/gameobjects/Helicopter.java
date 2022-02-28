package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.Game;

public class Helicopter {

    private Point location;
    private double xChange;
    private double yChange;
    private int size;
    private int fuelLevel;
    private int waterLevel;
    private int speed;
    private int heading;
    private boolean noFuel;
    private boolean speedHasChanged;

    private final int MAX_SPEED = 10;
    private final int MAX_FUEL = 25000;

    public Helicopter(Point helipadCenter) {
        speedHasChanged = false;
        fuelLevel = MAX_FUEL;
        size = Game.getSmallDim() / 55;
        location = helipadCenter;
        location.setY(location.getY() + (int) (size / 1.75));
        xChange = updateXChange();
        yChange = updateXChange();
        waterLevel = 0;
        speed = 0;
        heading = 0;
    }

    public void draw(Graphics g) {
        g.setColor(ColorUtil.YELLOW);
        g.drawLine(location.getX(), location.getY(), location.getX() +
                updateXChange(), location.getY() - updateYChange());
        g.fillArc(location.getX() - (int) (size / 1.75),
                location.getY() - (int) (size / 1.75), size, size, 0, 360);
        g.drawString("F : " + fuelLevel, location.getX(), location.getY() +
                (int) (2.5 * size));
        g.drawString("W : " + waterLevel, location.getX(), location.getY() +
                (int) (3.5 * size));
    }

    //getter for water level. used when attempting to fight fire
    public int getCurrentWater() {
        return waterLevel;
    }

    public void updateWaterLevel(int input) {
        if (input == 1) {
            waterLevel += 100; //drinking
        } else {
            waterLevel = 0; //drop on fire
        }
    }

    //getter for y location of helicopter. used for drinking, fighting fires,
    //and determining if the helicopter has landed
    public int getHVertLocation() {
        return location.getY();
    }

    //getter for x location of helicopter. used for drinking, fighting fires,
    //and determining if the helicopter has landed
    public int getHHorizLocation() {
        return location.getX();
    }

    //getter for speed. used for drink method and knowing if the game is over
    public int getCurrentSpeed() {
        return speed;
    }

    public boolean canSpeedUp() {
        return speed < MAX_SPEED;
    }

    public void updateCurrentSpeed(int input) {
        speed += input;
    }

    public void move() {
        location.setX(location.getX() + (speed * updateXChange() / 10));
        location.setY(location.getY() - (speed * updateYChange()) / 10);
        if (speed > 0) {
            speedHasChanged = true;
        }
    }

    public void loseFuel() {
        fuelLevel -= speed * speed + 5;
        if (fuelLevel <= 0) {
            fuelLevel = 0;
            noFuel = true;
        }
    }

    public boolean hasLanded(Point helipadCenter) {
        int helipadSize = Game.DISP_W / 15; //same as in helipad class
        boolean inXRange = helipadCenter.getX() - helipadSize / 2 < location.getX()
                && location.getX() < helipadCenter.getX() + helipadSize / 2;
        boolean inYRange = helipadCenter.getY() + helipadSize / 2 > location.getY()
                && location.getY() > helipadCenter.getY() - helipadSize / 2;
        return (speed == 0 && inXRange && inYRange);
    }

    public void updateHeading(int input) {
        if (input == -1) {
            heading -= 15;
            if (heading < 0) {
                heading += 360;
            }
        } else if (input == 1) {
            heading += 15;
            if (heading >= 360) {
                heading -= 360;
            }
        }
    }

    //setter for x value used to put tail of helicopter in direction of heading
    private int updateXChange() {
        xChange = 2 * size * Math.sin(heading * 0.01745329);
        return (int) Math.round(xChange);
    }

    //setter for y value used to put tail of helicopter in direction of heading
    private int updateYChange() {
        yChange = 2 * size * Math.cos(heading * 0.01745329);
        return (int) Math.round(yChange);
    }

    //getter for fuel level. used when determining if the game is over
    public int getFuelLevel() {
        return fuelLevel;
    }

    //determines if the helicopter has moved. needed so that ending conditions
    //are not triggered before the player has moved
    public boolean hasMoved() {
        return speedHasChanged;
    }

}
