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
    private Group fires = new Group();
    private int buildingArea;
    private int buildingDamage = 0;

    public Building(Dimension worldSize, Point2D inputLocation,
                    Dimension inputDim){
        super(inputLocation);
        this.worldSize = worldSize;
        this.color = ColorUtil.rgb(255,0,0);
        this.dim = inputDim;
        this.buildingCost = Math.round((
                this.dim.getWidth() * this.dim.getHeight()) / 20000) * 100;
        this.buildingArea = (this.dim.getWidth() * this.dim.getHeight())/10000;
        this.buildingDamage = getBuildingDamage();
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
        g.drawString("D: " + getBuildingDamage() + "%",
                (int)containerOrigin.getX()+(int)this.location.getX()
                        + dim.getWidth() + 20,
                (int)containerOrigin.getY()+(int)this.location.getY()
                        + dim.getHeight() + 50);
    }

    //getter for dimension. used when placing fires inside a building
    public Dimension getDimension() {return this.dim;}

    //getter for location. used when placing fires inside a building
    public Point2D getLocation() {return this.location;}

    //adding fire object to collection of fires
    //used when determining damage to a building
    public void addFire(Fire fire){fires.add(fire);}

    //find the damage to the building based on the size of the fires in it
    public int getBuildingDamage(){
        int totalDamage = 0;
        for (int i = 0; i < fires.size(); i++){
            Fire tmp = (Fire) fires.getGameObjects().get(i);
            //if the fire has positive size and is burning
            if (tmp.getSize() > 0 && tmp.isBurning()){
                totalDamage += tmp.getSize();
            }
        }
        totalDamage = totalDamage/buildingArea;
        //if the damage is more than it was previously, update it
        if (totalDamage > buildingDamage){
            buildingDamage = totalDamage;
        }
        return buildingDamage;
    }

    //ignites a fire in the building
    public void setFireInBuilding(Fire fire){fire.start();}

    //getter for cost of building. used when computing total loss
    public int getCost(){return this.buildingCost;}

}
