/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point2D;

public class Fixed extends GameObject{

    public final Point2D location;

    public Fixed(Point2D inputLocation) {
        location = inputLocation;
    }
}
