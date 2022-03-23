package org.csc133.a2.gameobjects;

public class UnStarted extends State{

    UnStarted(Fire fire) {
        super(fire);
        fire.setStarted(false);
    }

    @Override
    public Boolean onUnStarted() {
        return true;
    }

    @Override
    public Boolean onBurning() {
        return false;
    }

    @Override
    public Boolean onExtinguished() {
        return false;
    }

}
