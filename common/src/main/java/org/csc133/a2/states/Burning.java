/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.states;

import org.csc133.a2.gameobjects.Fire;

//burning state of fire
public class Burning extends StateF {

    public Burning(Fire fire) {super(fire);}

    @Override
    public Boolean onUnStarted() {return false;}

    @Override
    public Boolean onBurning() {return true;}

    @Override
    public Boolean onExtinguished() {return false;}

}
