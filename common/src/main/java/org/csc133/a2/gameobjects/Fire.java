/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.states.Burning;
import org.csc133.a2.states.Extinguished;
import org.csc133.a2.states.StateF;
import org.csc133.a2.states.UnStarted;
import org.csc133.a2.interfaces.Drawable;
import java.util.Random;

public class Fire extends Fixed implements Drawable {

    private StateF state;
    private Point2D drawStart;
    private int smallerDimOfBuilding;
    private int size;
    private Point2D containerOrigin;

    public Fire(Point2D buildingLocation, Dimension buildingDim, Random rand){
        super(new Point2D(buildingLocation.getX()+rand.nextInt(
                        3*buildingDim.getWidth()/4)+
                buildingDim.getWidth()/8,
                buildingLocation.getY()+rand.nextInt(
                        3*buildingDim.getHeight()/4)+
                        buildingDim.getHeight()/8));

        setUnStarted();

        this.color = ColorUtil.MAGENTA;

        this.smallerDimOfBuilding = Math.min(
                buildingDim.getWidth(), buildingDim.getHeight());

        size = rand.nextInt(smallerDimOfBuilding/10) + smallerDimOfBuilding/8;

        this.dim = new Dimension(size, size);
        this.drawStart = new Point2D(0,0);
        updateDrawStart();
    }

    public void draw(Graphics g, Point2D containerOrigin) {

        this.containerOrigin = containerOrigin;
        g.setColor(color);

        if (state.onBurning()){
            updateDrawStart();

            if (dim.getWidth() > 0) {
                g.fillArc((int)containerOrigin.getX()+(int)drawStart.getX(),
                        (int)containerOrigin.getY()+(int)drawStart.getY(),
                        dim.getWidth(), dim.getHeight(), 0, 360);
                g.drawString(String.valueOf(getSize()),
                        (int)containerOrigin.getX()+(int)location.getX() +
                                dim.getWidth() / 2,
                        (int)containerOrigin.getY()+(int)location.getY() +
                                dim.getHeight() / 2);
            }
        }
    }

    //method to change where the arc representing fire should begin being drawn
    private void updateDrawStart() {
        drawStart.setX((int)location.getX() - dim.getWidth() / 2);
        drawStart.setY((int)location.getY() - dim.getHeight() / 2);
    }

    //grows the fire by 10. called semi-randomly
    public void grow() {
        dim.setWidth(dim.getWidth() + 10);
        dim.setHeight(dim.getHeight() + 10);
    }

    //to be used when fires are being fought
    //fire size is decreased by the inputted amount
    public void shrink(int input) {
        dim.setWidth(dim.getWidth()-input);
        dim.setHeight(dim.getHeight()-input);
    }

    //getter for size. helps know when fires are out
    public int getSize() {return dim.getWidth();}

    //determines if the helicopter is close enough to a fire to put it out
    public boolean helicopterInRange(Helicopter helicopter) {
        boolean result = false;
        updateDrawStart();
        if (drawStart.getX() < helicopter.getHHorizLocation()) {
            if (drawStart.getX() + dim.getWidth() >
                    helicopter.getHHorizLocation()) {
                if (drawStart.getY() + containerOrigin.getY() <
                        helicopter.getHVertLocation()) {
                    if (drawStart.getY() + containerOrigin.getY() +
                            dim.getHeight() > helicopter.getHVertLocation()) {
                        result = true;

                    }
                }
            }
        }
        return result;
    }

    //sets state to unstarted. fires are not burning until start is called
    private void setUnStarted(){
        state = new UnStarted(this);
    }

    //starts the fire
    public void start(){setBurning();}

    //sets fire state to burning. called by building it is in
    private void setBurning(){
        state = new Burning(this);
    }

    //sets fire state to extinguished
    public void setExtinguished(){state = new Extinguished(this);}

    //tells whether the fire is out
    public boolean isExtinguished() {return state.onExtinguished();}

    //tells whether the fire is actively burning
    public boolean isBurning() {
        return state.onBurning();
    }
}
