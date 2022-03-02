package org.csc133.a2.gameobjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import org.csc133.a2.interfaces.Drawable;

public class GameObject implements Drawable {

    private Point location;
    private Dimension dim;
    private int color;

    public GameObject(Point inputPoint, Dimension inputDim, int inputColor){

    }

    public GameObject(Dimension inputDim, int inputColor) {
        dim = inputDim;
        color = inputColor;
    }

    @Override
    public void draw(Graphics g, Point containterOrigin) {

    }
}
