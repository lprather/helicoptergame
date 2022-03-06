package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

public class Helipad extends Fixed{

    public Helipad(Dimension worldSize) {
        super(new Point2D(worldSize.getWidth()/2-worldSize.getWidth()/20,
                9*worldSize.getHeight()/10-worldSize.getHeight()/20/2));
        this.worldSize = worldSize;
        this.color = ColorUtil.GRAY;
        this.dim = new Dimension(worldSize.getWidth()/20, worldSize.getWidth()/20);
    }

    //getter to be used for reference. returns center of helipad
    /*public Point getCenter() {
        Point center = new Point((location.getX() + size / 2),
                (location.getY() + size / 2));
        return center;
    }*/

    public void draw(Graphics g, Point2D containerOrigin){
        g.setColor(color);

        g.drawRect((int)this.location.getX(), (int)this.location.getY(), dim.getWidth(), dim.getHeight(), 5); //square
        g.drawArc((int)this.location.getX() + 10, (int)this.location.getY() + 10, dim.getWidth() - 20,
                dim.getHeight() - 20, 0, 360); //inner circle
    }

}
