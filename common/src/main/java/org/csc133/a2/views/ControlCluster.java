package org.csc133.a2.views;

import com.codename1.ui.Container;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.GameWorld;;

public class ControlCluster extends Container {

    private GameWorld gw;

    public ControlCluster(GameWorld gw) {
        this.gw = gw;

        this.setLayout(new GridLayout(1,5));
        this.add("PLACEHOLDER 1");
        this.add("PLACEHOLDER 2");
        this.add("PLACEHOLDER 3");
        this.add("PLACEHOLDER 4");
        this.add("PLACEHOLDER 5");

    }
}
