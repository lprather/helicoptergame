package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class River extends Fixed implements Drawable {

    public River(Dimension worldSize) {
        this.worldSize = worldSize;
        this.color = ColorUtil.BLUE;
        this.location = new Point2D(0, worldSize.getHeight()/3);
        //this.dim = new Dimension(100, 100);
        this.dim = new Dimension(worldSize.getWidth(), worldSize.getHeight()/8);
    }

    public Point2D getRiverLocation() {
        return location;
    }

    /*public void draw(Graphics g) {
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.DISP_W,
                Game.DISP_H / 8, 5);
    }*/

    //getter for top of river needed for helicopter drinking
    //public int getUpperRiverBound() {
        //return (int)location.getY();
    //}

    //getter for bottom of river. needed for helicopter drinking
    //public int getLowerRiverBound() {
        //return (int)location.getY() + Game.DISP_H / 8;
    //}

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        g.drawRect((int)location.getX(), (int)location.getY(),
                dim.getWidth(),dim.getHeight(),5);
                //this.dim.getWidth(), this.dim.getHeight() / 8, 5);
    }
    //(int)containerOrigin.getX() +
    //(int)containerOrigin.getY() +
}
