package org.csc133.a2.gameobjects;

public abstract class StateF {

    Fire fire;

    StateF(Fire fire){
        this.fire = fire;
    }

    public abstract Boolean onUnStarted();
    public abstract Boolean onBurning();
    public abstract Boolean onExtinguished();

}
