/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;
import org.csc133.a2.interfaces.Steerable;

public class Helicopter extends Moveable implements Drawable, Steerable {

    private double xChange;
    private double yChange;
    private final int size;
    private int fuelLevel;
    private int waterLevel;
    private boolean noFuel;
    private boolean speedHasChanged;

    private final int MAX_SPEED = 10;

    public Helicopter(Dimension worldSize, Point helicopterCenter,
                      int initialFuel) {
        super();
        this.color = ColorUtil.YELLOW;

        size = Math.min(worldSize.getWidth(), worldSize.getHeight()) / 45;
        this.worldSize = worldSize;
        this.location = helicopterCenter;
        this.location.setY(location.getY() + (int) (size / 1.75));
        this.dim = new Dimension(size, size);
        speedHasChanged = false;
        fuelLevel = initialFuel;
        xChange = updateXChange();
        yChange = updateXChange();
        waterLevel = 0;
    }

    public void draw(Graphics g, Point2D containerOrigin){
        g.setColor(color);

        g.drawLine(location.getX(), location.getY(),
                location.getX() + updateXChange(),
                location.getY() - updateYChange());
        g.fillArc(location.getX() - (int)(dim.getWidth() / 1.75),
                location.getY() - (int)(dim.getHeight() / 1.75),
                dim.getWidth(), dim.getHeight(), 0, 360);
        g.drawString("F : " + fuelLevel, location.getX(),
                location.getY() + (int)(2.5 * size));
        g.drawString("W : " + waterLevel, location.getX(),
                location.getY() + (int)(3.5 * size));
    }


    //getter for water level. used when attempting to fight fire
    public int getCurrentWater() {return waterLevel;}

    //adjusts water level in helicopter based on whether it is drinking or
    //dumping the water out
    public void updateWaterLevel(int input) {
        if (input == 1) {
            waterLevel += 100; //drinking
        } else {
            waterLevel = 0; //drop on fire
        }
    }

    //getter for y location of helicopter. used for drinking, fighting fires,
    //and determining if the helicopter has landed
    public int getHVertLocation() {return location.getY();}

    //getter for x location of helicopter. used for drinking, fighting fires,
    //and determining if the helicopter has landed
    public int getHHorizLocation() {return location.getX();}

    //getter for speed. used for drink method and knowing if the game is over
    public int getCurrentSpeed() {return this.getSpeed();}

    //determines if the helicopter is able to speed up based on max speed
    public boolean canSpeedUp() {return this.getSpeed() < MAX_SPEED;}

    //setter for speed. used when helicopter speeds up or slows down
    public void updateCurrentSpeed(int input) {this.setSpeed(input);}

    //moves the helicopter in the direction of current heading at current speed
    //overrides move method from moveable class
    @Override
    public void move() {
        location.setX(location.getX() +
                (this.getSpeed() * updateXChange() / 10));
        location.setY(location.getY() -
                (this.getSpeed() * updateYChange()) / 10);
        if (this.getSpeed() > 0) {
            speedHasChanged = true;
        }
    }

    //causes the helicopter to lose fuel based on the current speed
    public void loseFuel() {
        fuelLevel -= this.getSpeed() * this.getSpeed() + 5;
        if (fuelLevel <= 0) {
            fuelLevel = 0;
            noFuel = true;
        }
    }

    //determines if the helicopter has landed on the helipad. used for
    //determining if the game is over/won
    public boolean hasLanded(Point helipadCenter, Helipad helipad) {
        int helipadSize = helipad.getDim().getWidth();
        boolean inXRange = helipadCenter.getX() -
                helipadSize / 2 < location.getX()
                && location.getX() < helipadCenter.getX() + helipadSize / 2;
        boolean inYRange = helipadCenter.getY() +
                helipadSize / 2 > location.getY()
                && location.getY() > helipadCenter.getY() - helipadSize / 2;
        return (this.getSpeed() == 0 && inXRange && inYRange);
    }

    //setter for x value used to put tail of helicopter in direction of heading
    private int updateXChange() {
        xChange = 2 * size * Math.sin(this.getHeading() * 0.01745329);
        return (int) Math.round(xChange);
    }

    //setter for y value used to put tail of helicopter in direction of heading
    private int updateYChange() {
        yChange = 2 * size * Math.cos(this.getHeading() * 0.01745329);
        return (int) Math.round(yChange);
    }

    //getter for fuel level. used when determining if the game is over
    public int getFuelLevel() {return fuelLevel;}

    //determines if the helicopter has moved. needed so that ending conditions
    //are not triggered before the player has moved
    public boolean hasMoved() {return speedHasChanged;}

    @Override
    public void steerLeft() {
        this.updateHeading(-15);
        if (this.getHeading() < 0){
            this.updateHeading(360);
        }
    }

    @Override
    public void steerRight() {
        this.updateHeading(15);
        if (this.getHeading() >= 360) {
            this.updateHeading(-360);
        }
    }
}
