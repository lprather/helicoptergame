package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.util.UITimer;
import org.csc133.a2.views.ControlCluster;
import org.csc133.a2.views.GlassCockpit;
import org.csc133.a2.views.MapView;

public class Game extends Form implements Runnable {

    private GameWorld gw;
    MapView mapView;
    GlassCockpit cockpitView;
    ControlCluster controlView;

    public final static int DISP_W = Display.getInstance().getDisplayWidth();
    public final static int DISP_H = Display.getInstance().getDisplayHeight();

    public static int getSmallDim() {
        return Math.min(DISP_W, DISP_H);
    }

    public Game() {
        gw = new GameWorld();

        mapView = new MapView(gw);
        cockpitView = new GlassCockpit(gw);
        controlView = new ControlCluster(gw);

        addKeyListener('Q', (evt) -> gw.quit());
        addKeyListener(-91, (evt) -> gw.handleInputKey(-91)); //up arrow
        addKeyListener(-92, (evt) -> gw.handleInputKey(-92)); //down arrow
        addKeyListener(-93, (evt) -> gw.handleInputKey(-93)); //left arrow
        addKeyListener(-94, (evt) -> gw.handleInputKey(-94)); //right arrow
        //addKeyListener('f', (evt) -> gw.fightFire());
        //addKeyListener('d', (evt) -> gw.drink());

        UITimer timer = new UITimer(this);
        timer.schedule(100, true, this);

        this.getAllStyles().setBgColor(ColorUtil.BLACK);
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