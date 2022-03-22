package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.csc133.a2.GameWorld;

public class AccelerateCommand extends Command implements ActionListener {

    private GameWorld gw;

    public AccelerateCommand(GameWorld gw){
        super("Accel");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gw.accelerate();
    }
}
