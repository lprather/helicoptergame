package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class BuildingGroup extends GameObjectCollection<Building>{

    public BuildingGroup(){
        super();
        this.color = ColorUtil.rgb(255,0,0);
    }

    @Override
    public void draw(Graphics g, Point2D containerOrigin){
        for (Building building : getGameObjects()){
            building.draw(g, containerOrigin);
        }
    }

}
