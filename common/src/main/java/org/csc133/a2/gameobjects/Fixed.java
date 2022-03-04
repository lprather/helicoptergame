package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

public class Fixed extends GameObject{

    public static Point2D location; //should be final

    public Fixed(Point2D inputLocation, Dimension inputDim, int inputColor){
        super(inputDim, inputColor);
        location = inputLocation;
    }

    public Fixed() {
        super();
    }
}
