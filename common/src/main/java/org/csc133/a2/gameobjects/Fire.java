package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class Fire extends Fixed implements Drawable {

    private Point2D drawStart;

    public Fire(Point2D buildingLocation, Dimension buildingDim){
        super(buildingLocation);
        this.color = ColorUtil.MAGENTA;
        this.drawStart = new Point2D(600,600);
        this.dim = buildingDim;
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        if (dim.getWidth() > 0) {
            g.fillArc((int)drawStart.getX(), (int)drawStart.getY(), dim.getWidth(), dim.getHeight(), 0, 360);
            g.drawString(" " + dim.getWidth() + " ", (int)location.getX() + dim.getWidth() / 2,
                    (int)location.getY() + dim.getHeight() / 2);
        }
    }

    /*private Point center;
    private Point drawStart;
    private int size;
    private Random rand = new Random();

    public Fire(int xCoord, int yCoord) {
        center = new Point(xCoord, yCoord);
        size = rand.nextInt(Game.getSmallDim() / 12) +
                Game.getSmallDim() / 12 + 100;
        drawStart = new Point(center.getX() - size / 2, center.getY() - size / 2);
    }

    public void draw(Graphics g) {
        updateDrawStart();
        g.setColor(ColorUtil.MAGENTA);
        if (size > 0) {
            g.fillArc(drawStart.getX(), drawStart.getY(), size, size, 0, 360);
            g.drawString(" " + size + " ", center.getX() + size / 2,
                    center.getY() + size / 2);
        }
    }

    //method to change where the arc representing fire should begin being drawn
    private void updateDrawStart() {
        drawStart.setX(center.getX() - size / 2);
        drawStart.setY(center.getY() - size / 2);
    }

    public int grow() {
        if (size > 0) {
            size += rand.nextInt(5) + 5;
            return 0;
        } else {
            return -1;
        }
    }

    //to be used when fires are being fought
    public void shrink(int input) {
        size -= input;
    }

    //getter for size. helps know when fires are out
    public int getSize() {
        return size;
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
        return center.getX();
    }*/

}
