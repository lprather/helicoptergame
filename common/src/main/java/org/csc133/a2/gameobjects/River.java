package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.Game;

public class River extends Fixed{

    public River(Point inputLocation) {
        super(inputLocation);
    }

    public void draw(Graphics g) {
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.DISP_W,
                Game.DISP_H / 8, 5);
    }

    //getter for top of river needed for helicopter drinking
    public int getUpperRiverBound() {
        return location.getY();
    }

    //getter for bottom of river. needed for helicopter drinking
    public int getLowerRiverBound() {
        return location.getY() + Game.DISP_H / 8;
    }

}
