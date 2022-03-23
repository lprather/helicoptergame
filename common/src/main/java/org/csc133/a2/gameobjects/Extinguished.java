package org.csc133.a2.gameobjects;

public class Extinguished extends StateF {

    Extinguished(Fire fire) {
        super(fire);
        //fire.setExtinguished();
    }

    @Override
    public Boolean onUnStarted() {
        return false;
    }

    @Override
    public Boolean onBurning() {
        return false;
    }

    @Override
    public Boolean onExtinguished() {
        return true;
    }
}
