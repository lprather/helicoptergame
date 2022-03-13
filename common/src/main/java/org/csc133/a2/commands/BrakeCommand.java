package org.csc133.a2.commands;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.csc133.a2.GameWorld;

public class BrakeCommand implements ActionListener {

    private GameWorld gw;

    public BrakeCommand(GameWorld gw) {
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gw.brake();
    }
}
