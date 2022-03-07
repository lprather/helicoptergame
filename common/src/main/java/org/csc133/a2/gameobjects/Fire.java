package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

import java.util.Random;

public class Fire extends Fixed implements Drawable {

    private Point2D drawStart;
    private int smallerDimOfBuilding;
    private int size;

    public Fire(Point2D buildingLocation, Dimension buildingDim, Random rand){
        super(new Point2D(buildingLocation.getX()+rand.nextInt(
                        3*buildingDim.getWidth()/4)+
                buildingDim.getWidth()/8,
                buildingLocation.getY()+rand.nextInt(
                        3*buildingDim.getHeight()/4)+
                        buildingDim.getHeight()/8));
        this.color = ColorUtil.MAGENTA;

        this.smallerDimOfBuilding = Math.min(
                buildingDim.getWidth(), buildingDim.getHeight());

        size = rand.nextInt(smallerDimOfBuilding/6) + smallerDimOfBuilding/4;

        this.dim = new Dimension(size, size);
        this.drawStart = new Point2D(0,0);
        updateDrawStart();
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        updateDrawStart();

        if (dim.getWidth() > 0) {
            g.fillArc((int)containerOrigin.getX()+(int)drawStart.getX(),
                    (int)containerOrigin.getY()+(int)drawStart.getY(),
                    dim.getWidth(), dim.getHeight(), 0, 360);
            g.drawString(" " + dim.getWidth() + " ",
                    (int)containerOrigin.getX()+(int)location.getX() +
                            dim.getWidth() / 2,
                    (int)containerOrigin.getY()+(int)location.getY() +
                            dim.getHeight() / 2);
        }
    }

    //method to change where the arc representing fire should begin being drawn
    private void updateDrawStart() {
        drawStart.setX((int)location.getX() - dim.getWidth() / 2);
        drawStart.setY((int)location.getY() - dim.getHeight() / 2);
    }

    public int grow() {
        if (dim.getWidth() > 0) {
            dim.setWidth(dim.getWidth()+10);
            dim.setHeight(dim.getHeight()+10);
            return 0;
        } else {
            return -1;
        }
    }

    //to be used when fires are being fought
    public void shrink(int input) {
        dim.setWidth(dim.getWidth()-input);
        dim.setHeight(dim.getHeight()-input);
    }

    //getter for size. helps know when fires are out
    public int getSize() {
        return dim.getWidth();
    }

    //determines if the helicopter is close enough to a fire to put it out
    public boolean helicopterInRange(Helicopter helicopter) {
        boolean result = false;
        if (drawStart.getX() < helicopter.getHHorizLocation()) {
            if (drawStart.getX() + size > helicopter.getHHorizLocation()) {
                if (drawStart.getY() < helicopter.getHVertLocation()) {
                    if (drawStart.getY() + size >
                            helicopter.getHVertLocation()) {
                        result = true;

                    }
                }
            }
        }
        return result;
    }

    //getter for x coordinate. used for knowing if fire is on screen
    public int getXCoord() {
        return (int)location.getX();
    }

}
