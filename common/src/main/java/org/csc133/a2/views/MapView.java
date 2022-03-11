/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;
import org.csc133.a2.GameWorld;
import org.csc133.a2.gameobjects.GameObject;

public class MapView extends Container {

    private static GameWorld gw;

    public MapView(GameWorld gw) {
        this.gw = gw;
        this.getStyle().setBgColor(ColorUtil.BLACK);
        this.getStyle().setBgTransparency(255);
    }

    @Override
    public void laidOut(){
        gw.setDimension(new Dimension(this.getWidth(), this.getHeight()));
        gw.init();
    }

    public void paint(Graphics g){
        //draw all objects in the gameworld relative to this container object
        for (GameObject go: gw.getGameObjectCollection()){
            go.draw(g, new Point2D(this.getX(), this.getY()));
        }
    }

}
