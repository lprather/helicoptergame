package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.Game;
import org.csc133.a2.interfaces.Drawable;

public class River extends Fixed implements Drawable {

    public River(Dimension worldSize) {
        //super();
        this.worldSize = worldSize;
        this.color = ColorUtil.BLUE;
        this.location = new Point2D(0, worldSize.getHeight());
        this.dim = new Dimension(worldSize.getWidth(), worldSize.getHeight());
    }

    //public River(Point2D point2D, Dimension dimension, int i) {
        //super();
    //}

    public Point2D getRiverLocation() {
        return location;
    }

    /*public void draw(Graphics g) {
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.DISP_W,
                Game.DISP_H / 8, 5);
    }*/

    //getter for top of river needed for helicopter drinking
    public int getUpperRiverBound() {
        return (int)location.getY();
    }

    //getter for bottom of river. needed for helicopter drinking
    public int getLowerRiverBound() {
        return (int)location.getY() + Game.DISP_H / 8;
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);


        g.drawRect((int)containerOrigin.getX(), (int)containerOrigin.getY(), Game.DISP_W - 5,
                Game.DISP_H / 8, 5);
    }
}
