/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.interfaces;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

//for objects that can be drawn
public interface Drawable {

    void draw(Graphics g, Point2D containerOrigin);

}
