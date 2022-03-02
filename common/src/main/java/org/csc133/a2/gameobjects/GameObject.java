package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class GameObject {

    private Point location;
    private Dimension dim;
    private int color;

    public GameObject(Point inputPoint, Dimension inputDim, int inputColor){

    }

    public GameObject(Dimension inputDim, int inputColor) {
        dim = inputDim;
        color = inputColor;
    }
}
