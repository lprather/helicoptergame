/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class River extends Fixed implements Drawable {

    public River(Dimension worldSize) {
        super(new Point2D(0, worldSize.getHeight()/4));
        this.worldSize = worldSize;
        this.color = ColorUtil.BLUE;
        this.dim = new Dimension(worldSize.getWidth(),
                worldSize.getHeight()/8);
    }

    //getter for top of river needed for helicopter drinking
    public int getUpperRiverBound() {
        return (int)location.getY() + dim.getHeight();
    }

    //getter for bottom of river. needed for helicopter drinking
    public int getLowerRiverBound() {
        return (int)location.getY() + 2*dim.getHeight();
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        g.drawRect((int)containerOrigin.getX()+(int)this.location.getX(),
                (int)containerOrigin.getY()+(int)this.location.getY(),
                dim.getWidth(),dim.getHeight(),5);
    }

}
