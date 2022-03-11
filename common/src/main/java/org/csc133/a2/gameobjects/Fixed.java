/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point2D;

public class Fixed extends GameObject{

    //public static Point2D location; //should be final

    public Fixed(Point2D inputLocation) {
        location = inputLocation;
    }
}
