/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.states;

import org.csc133.a2.gameobjects.Fire;

//abstract state for fires
public abstract class StateF {

    Fire fire;

    StateF(Fire fire){this.fire = fire;}

    public abstract Boolean onUnStarted();
    public abstract Boolean onBurning();
    public abstract Boolean onExtinguished();

}
