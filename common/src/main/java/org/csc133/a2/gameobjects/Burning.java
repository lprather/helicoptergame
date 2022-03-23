package org.csc133.a2.gameobjects;

public class Burning extends StateF {

    Burning(Fire fire) {
        super(fire);
        //fire.setBurning();
    }

    @Override
    public Boolean onUnStarted() {
        return false;
    }

    @Override
    public Boolean onBurning() {
        return true;
    }

    @Override
    public Boolean onExtinguished() {
        return false;
    }

}
