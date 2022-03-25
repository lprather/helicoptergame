/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

//base game object class
public class GameObject implements Drawable {

    public Point location;
    public Dimension dim;
    public int color;
    public Dimension worldSize;

    public GameObject() {}

    @Override
    public void draw(Graphics g, Point2D containerOrigin) {}

}
