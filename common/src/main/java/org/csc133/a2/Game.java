/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;

public class Game extends Form implements Runnable {

    private GameWorld gw;
    private MapView mapView;
    private GlassCockpit cockpitView;
    private ControlCluster controlView;

    public Game() {
        gw = new GameWorld();

        mapView = new MapView(gw);
        cockpitView = new GlassCockpit(gw);
        controlView = new ControlCluster(gw);

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, cockpitView);
        this.add(BorderLayout.CENTER, mapView);
        this.add(BorderLayout.SOUTH, controlView);

        addKeyListener('Q', (evt) -> gw.quit());
        addKeyListener(-91, (evt) -> gw.handleInputKey(-91)); //up arrow
        addKeyListener(-92, (evt) -> gw.handleInputKey(-92)); //down arrow
        addKeyListener(-93, (evt) -> gw.handleInputKey(-93)); //left arrow
        addKeyListener(-94, (evt) -> gw.handleInputKey(-94)); //right arrow
        addKeyListener('f', (evt) -> gw.fightFire());
        addKeyListener('d', (evt) -> gw.drink());

        UITimer timer = new UITimer(this);
        timer.schedule(100, true, this);

        this.show();
    }

    public void paint(Graphics g) {
        super.paint(g);
        gw.draw(g);
    }

    @Override
    public void run() {
        gw.tick();
        repaint();
    }

}