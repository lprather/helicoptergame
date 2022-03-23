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

public class Building extends Fixed implements Drawable {

    private int buildingCost;
    private int buildingDamage;

    public Building(Dimension worldSize, Point2D inputLocation,
                    Dimension inputDim){
        super(inputLocation);
        this.worldSize = worldSize;
        this.color = ColorUtil.rgb(255,0,0);
        this.dim = inputDim;
        this.buildingCost = Math.round((this.dim.getWidth() * this.dim.getHeight()) / 20000) * 100;
    }

    public void draw(Graphics g, Point2D containerOrigin) {
        g.setColor(color);

        g.drawRect((int)containerOrigin.getX()+(int)this.location.getX(),
                (int)containerOrigin.getY()+(int)this.location.getY(),
                dim.getWidth(), dim.getHeight(),5);
        g.drawString("V: " + buildingCost,
                (int)containerOrigin.getX()+(int)this.location.getX()
                        + dim.getWidth() + 20,
                (int)containerOrigin.getY()+(int)this.location.getY()
                        + dim.getHeight() + 20);
        g.drawString("D: " + updateBuildingDamage() + "%",
                (int)containerOrigin.getX()+(int)this.location.getX()
                        + dim.getWidth() + 20,
                (int)containerOrigin.getY()+(int)this.location.getY()
                        + dim.getHeight() + 50);
    }

    public Dimension getDimension() {return this.dim;}

    public Point2D getLocation() {return this.location;}

    private int updateBuildingDamage(){
        return -1;
    }

    public void setFireInBuilding(Fire fire){
        fire.start();
    }

}
