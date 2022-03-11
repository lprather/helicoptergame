/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class GameObject implements Drawable {

    public Point2D location;
    public Dimension dim;
    public int color;
    Dimension worldSize;

    public GameObject(Point2D inputPoint, Dimension inputDim, int inputColor){

    }

    public GameObject(Dimension inputDim, int inputColor) {
        dim = inputDim;
        color = inputColor;
    }

    public GameObject() {

    }

    @Override
    public void draw(Graphics g, Point2D containerOrigin) {

    }
}
