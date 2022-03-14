/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

public class Moveable extends GameObject{

    private int heading;
    private int speed;

    public Moveable() {
        heading = 0;
        speed = 0;
    }

    public void move(){}

    public int getHeading(){return heading;}

    public int getSpeed(){return speed;}

    protected void setSpeed(int input) {speed += input;}

    public void updateHeading(int input){heading += input;}
}
