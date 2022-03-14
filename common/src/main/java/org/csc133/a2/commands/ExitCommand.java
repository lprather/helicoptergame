package org.csc133.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import org.csc133.a2.GameWorld;

public class ExitCommand extends Command implements ActionListener {

    private GameWorld gw;

    public ExitCommand(GameWorld gw) {
        super("");
        this.gw = gw;
    }

    public ExitCommand(GameWorld gw, String input){
        super(input);
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        gw.exit();
    }
}
