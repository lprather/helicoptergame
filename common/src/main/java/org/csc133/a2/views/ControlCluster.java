/* CSC 133-03, Professor Posnett
   Assignment A2 full submission
   Lauren Prather, #9545
 */

package org.csc133.a2.views;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.*;

public class ControlCluster extends Container {

    private GameWorld gw;

    //BUG:
    //left and right arrow keys cause one button at a time
    //to appear white instead of gray
    //unable to find a fix at this time
    public ControlCluster(GameWorld gw) {
        this.gw = gw;

        this.setLayout(new GridLayout(1,15));
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getStyle().setBgTransparency(255);

        this.add(buttonMaker(new TurnLeftCommand(gw)));
        this.add(buttonMaker(new TurnRightCommand(gw)));
        this.add(buttonMaker(new FightCommand(gw)));

        this.add("");
        this.add("");
        this.add("");
        this.add("");

        this.add(buttonMaker(new ExitCommand(gw)));

        this.add("");
        this.add("");
        this.add("");
        this.add("");

        this.add(buttonMaker(new DrinkCommand(gw)));
        this.add(buttonMaker(new BrakeCommand(gw)));
        this.add(buttonMaker(new AccelerateCommand(gw)));

    }

    private Button buttonMaker(Command cmd){
        Button output = new Button();

        output.getAllStyles().setBgColor(ColorUtil.GRAY);
        output.getAllStyles().setFgColor(ColorUtil.BLUE);
        output.getStyle().setBgTransparency(255);

        output.setCommand(cmd);
        return output;
    }

}
