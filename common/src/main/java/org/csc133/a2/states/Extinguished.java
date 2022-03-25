/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.states;

import org.csc133.a2.gameobjects.Fire;

//extinguished state of fire
public class Extinguished extends StateF {

    public Extinguished(Fire fire) {super(fire);}

    @Override
    public Boolean onUnStarted() {return false;}

    @Override
    public Boolean onBurning() {return false;}

    @Override
    public Boolean onExtinguished() {return true;}
}
