package org.csc133.a2.gameobjects;

public class Burning extends State {

    Burning(Fire fire) {
        super(fire);
        fire.setBurning(this);
    }

    @Override
    public Boolean onUnStarted() {
        return null;
    }

    @Override
    public Boolean onBurning() {
        return null;
    }

    @Override
    public Boolean onExtinguished() {
        return null;
    }

}
