package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.interfaces.Drawable;

public class GameObject implements Drawable {

    private Point2D location;
    public Dimension dim;
    public int color;
    Dimension worldSize;

    public GameObject(Point inputPoint, Dimension inputDim, int inputColor){

    }

    public GameObject(Dimension inputDim, int inputColor) {
        dim = inputDim;
        color = inputColor;
    }

    public GameObject() {

    }

    @Override
    public void draw(Graphics g, Point2D containterOrigin) {

    }
}
