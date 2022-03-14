package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.csc133.a2.GameWorld;

public class BrakeCommand extends Command implements ActionListener {

    private GameWorld gw;

    public BrakeCommand(GameWorld gw) {
        super("");
        this.gw = gw;
    }

    public BrakeCommand(GameWorld gw, String input){
        super(input);
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gw.brake();
    }
}
