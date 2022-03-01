package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.Game;

public class Helipad extends Fixed{

    private final int size = Game.DISP_W / 15;

    public Helipad(Point inputLocation) {
        super(inputLocation);
    }

    public void draw(Graphics g) {
        g.setColor(ColorUtil.GRAY);
        g.drawRect(location.getX(), location.getY(), size, size, 5); //square
        g.drawArc(location.getX() + 10, location.getY() + 10, size - 20,
                size - 20, 0, 360); //inner circle
    }

    //getter to be used for reference. returns center of helipad
    public Point getCenter() {
        Point center = new Point((location.getX() + size / 2),
                (location.getY() + size / 2));
        return center;
    }

}
