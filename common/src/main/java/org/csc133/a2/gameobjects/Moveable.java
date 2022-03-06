package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

public class Moveable extends GameObject{

    private int heading;
    private int speed;

    public Moveable() {
        heading = 0;
        speed = 0;
    }

    public void move(){

    }

    public int getHeading(){
        return heading;
    }

    public int getSpeed(){
        return speed;
    }

}
