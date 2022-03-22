/* CSC 133-03, Professor Posnett
   Assignment A2 check-in
   Lauren Prather, #9545
 */

package org.csc133.a2.views;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import org.csc133.a2.GameWorld;
import org.csc133.a2.commands.*;

public class ControlCluster extends Container {

    private GameWorld gw;

    public ControlCluster(GameWorld gw) {
        this.gw = gw;

        this.setLayout(new GridLayout(1,15));

        Button left = new Button();
        left.setCommand(new TurnLeftCommand(gw));
        this.add(left);
        Button right = new Button();
        right.setCommand(new TurnRightCommand(gw));
        this.add(right);
        Button fight = new Button();
        fight.setCommand(new FightCommand(gw));
        this.add(fight);

        this.add("");
        this.add("");

        Button exit = new Button();
        exit.setCommand(new ExitCommand(gw));
        this.add(exit);

        this.add("");
        this.add("");

        Button drink = new Button();
        drink.setCommand(new DrinkCommand(gw));
        this.add(drink);
        Button brake = new Button();
        brake.setCommand(new BrakeCommand(gw));
        this.add(brake);
        Button accel = new Button();
        accel.setCommand(new AccelerateCommand(gw));
        this.add(accel);
    }
}
