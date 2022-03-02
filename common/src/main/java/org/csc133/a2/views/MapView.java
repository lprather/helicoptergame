package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;

public class MapView extends Container {

    private GameWorld gw;
    private Graphics g;

    //constructor
    public void MapView(GameWorld inputgw){
        gw = inputgw;
    }

    public void draw(){
        //draw all objects in the gameworld relative to this container object
        for (GameObject go: gw.getGameObjectCollection()){
            go.draw(g, new Point(this.getX(), this.getY()));
        }
    }

}
