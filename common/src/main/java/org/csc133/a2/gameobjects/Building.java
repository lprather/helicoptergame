package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class Building extends Fixed implements Drawable {

    public Building(Dimension worldSize, Point2D inputLocation,
                    Dimension inputDim){
        super(inputLocation);
        this.worldSize = worldSize;
        this.color = ColorUtil.rgb(255,0,0);
        this.dim = inputDim;
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        g.drawRect((int)containerOrigin.getX()+(int)this.location.getX(),
                (int)containerOrigin.getY()+(int)this.location.getY(),
                dim.getWidth(), dim.getHeight(),5);
    }

}
