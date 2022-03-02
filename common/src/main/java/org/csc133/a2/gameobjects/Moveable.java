package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class Moveable extends GameObject{

    private int heading;
    private int speed;

    public Moveable(Point inputPoint, Dimension inputDim, int inputColor) {
        super(inputPoint, inputDim, inputColor);
    }

    /*public Moveable(int inputHeading, int inputSpeed){
        heading = inputHeading;
        speed = inputSpeed;
    }*/

    public void move(){

    }

    public int getHeading(){
        return heading;
    }

    public int getSpeed(){
        return speed;
    }

}
