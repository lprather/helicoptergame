package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;

public class Fixed extends GameObject{

    public static Point location; //should be final

    public Fixed(Point inputLocation, Dimension inputDim, int inputColor){
        super(inputDim, inputColor);
        location = inputLocation;
    }

}
