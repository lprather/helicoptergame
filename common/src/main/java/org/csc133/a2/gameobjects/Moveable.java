package org.csc133.a2.gameobjects;

public class Moveable extends GameObject{

    private int heading;
    private int speed;

    public Moveable(int inputHeading, int inputSpeed){
        heading = inputHeading;
        speed = inputSpeed;
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
