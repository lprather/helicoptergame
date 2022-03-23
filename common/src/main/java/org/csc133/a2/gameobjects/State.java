package org.csc133.a2.gameobjects;

public abstract class State {

    Fire fire;

    State(Fire fire){
        this.fire = fire;
    }

    public abstract Boolean onUnStarted();
    public abstract Boolean onBurning();
    public abstract Boolean onExtinguished();

}
