/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.states;

import org.csc133.a2.gameobjects.Fire;

//unstarted state for fire
public class UnStarted extends StateF {

    public UnStarted(Fire fire) {super(fire);}

    @Override
    public Boolean onUnStarted() {return true;}

    @Override
    public Boolean onBurning() {return false;}

    @Override
    public Boolean onExtinguished() {return false;}

}
