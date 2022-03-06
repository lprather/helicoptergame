package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Group extends GameObjectCollection<GameObject>{

    public Group(){
        super();
    }

    @Override
    public void draw(Graphics g, Point2D containerOrigin){
        for (GameObject go : getGameObjects()){
            go.draw(g, containerOrigin);
        }
    }

}
