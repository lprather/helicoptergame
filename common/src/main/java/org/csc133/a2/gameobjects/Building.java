package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class Building extends Fixed implements Drawable {

    public Building(Dimension worldSize, Point2D inputLocation){
        super(inputLocation);
        this.worldSize = worldSize;
        this.color = ColorUtil.rgb(255,0,0);
        this.dim = new Dimension(100,100);
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        g.drawRect((int)this.location.getX(), (int)this.location.getY(), dim.getWidth(), dim.getHeight(),5);
    }

}
