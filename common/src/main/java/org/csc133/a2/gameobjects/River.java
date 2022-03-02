package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.Game;
import org.csc133.a2.interfaces.Drawable;

public class River extends Fixed implements Drawable {

    public River(Point inputLocation, Dimension inputDim, int inputColor) {
        super(inputLocation, inputDim, inputColor);
    }

    public Point getRiverLocation() {
        return location;
    }

    /*public void draw(Graphics g) {
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.DISP_W,
                Game.DISP_H / 8, 5);
    }*/

    //getter for top of river needed for helicopter drinking
    public int getUpperRiverBound() {
        return location.getY();
    }

    //getter for bottom of river. needed for helicopter drinking
    public int getLowerRiverBound() {
        return location.getY() + Game.DISP_H / 8;
    }

    @Override
    public void draw(Graphics g, Point containterOrigin) {
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.DISP_W,
                Game.DISP_H / 8, 5);
    }
}
