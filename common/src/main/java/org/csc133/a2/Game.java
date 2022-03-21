/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.commands.*;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;

import static org.csc133.a2.GameWorld.getGameWorld;

public class Game extends Form implements Runnable {

    private GameWorld gw;
    private MapView mapView;
    private GlassCockpit cockpitView;
    private ControlCluster controlView;

    public Game() {
        gw = getGameWorld();

        mapView = new MapView(gw);
        cockpitView = new GlassCockpit(gw);
        controlView = new ControlCluster(gw);

        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, cockpitView);
        this.add(BorderLayout.CENTER, mapView);
        this.add(BorderLayout.SOUTH, controlView);

        addKeyListener('Q', new ExitCommand(gw));
        addKeyListener(-91, new AccelerateCommand(gw)); //up arrow
        addKeyListener(-92, new BrakeCommand(gw)); //down arrow
        addKeyListener(-93, new TurnLeftCommand(gw)); //left arrow
        addKeyListener(-94, new TurnRightCommand(gw)); //right arrow
        addKeyListener('f', new FightCommand(gw));
        addKeyListener('d', new DrinkCommand(gw));

        UITimer timer = new UITimer(this);
        timer.schedule(100, true, this);

        this.show();
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void run() {
        gw.tick();
        cockpitView.update();
        repaint();
    }

}