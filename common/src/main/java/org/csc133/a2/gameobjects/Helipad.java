/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Helipad extends Fixed{

    public Helipad(Dimension worldSize) {
        super(new Point2D(worldSize.getWidth()/2-worldSize.getWidth()/15,
                9*worldSize.getHeight()/10-worldSize.getHeight()/15/2));
        this.worldSize = worldSize;
        this.color = ColorUtil.GRAY;
        this.dim = new Dimension(worldSize.getWidth()/15,
                worldSize.getWidth()/15);
    }

    public void draw(Graphics g, Point2D containerOrigin){
        g.setColor(color);

        g.drawRect((int)this.location.getX(), (int)this.location.getY(),
                dim.getWidth(), dim.getHeight(), 5); //square
        g.drawArc((int)this.location.getX() + 10,
                (int)this.location.getY() + 10, dim.getWidth() - 20,
                dim.getHeight() - 20, 0, 360); //inner circle
    }


    //getter for center. used in relation to helicopter
    public Point getCenter() {
        return new Point((int)(location.getX() + dim.getWidth() / 2),
                (int)(location.getY() + dim.getHeight() / 2));
    }

    //getter for dimension. used to determine if the helicopter is inside
    public Dimension getDim() {return dim;}
}
