package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;

public class MapView extends Container {

    private static GameWorld gw;

    public MapView(GameWorld gw) {
        this.gw = gw;
        //gw.setDimension(new Dimension(this.getWidth(), this.getHeight()));

    }

    @Override
    public void laidOut(){
        gw.setDimension(new Dimension(this.getWidth(), this.getHeight()));
        gw.init();
    }

    //constructor
    //public void MapView(GameWorld inputgw){
        //gw = inputgw;
    //}

    public void paint(Graphics g){
        //draw all objects in the gameworld relative to this container object
        for (GameObject go: gw.getGameObjectCollection()){
            go.draw(g, new Point2D(this.getX(), this.getY()));
        }
    }

}
