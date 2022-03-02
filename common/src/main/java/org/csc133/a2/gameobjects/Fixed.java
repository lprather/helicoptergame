package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point;

public class Fixed extends GameObject{

    public static final Point location;

    public Fixed(Point inputLocation){
        location = inputLocation;
    }

    public Point getLocation(){
        return location;
    }

}
