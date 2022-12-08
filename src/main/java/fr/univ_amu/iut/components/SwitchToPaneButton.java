package fr.univ_amu.iut.components;

import fr.univ_amu.iut.SwitchTo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Custom widget used to make a button with the default action that is switching to a pane
 */
public class SwitchToPaneButton extends Button {

    /**
     * Constructor of the custom widget
     */
    public SwitchToPaneButton(){
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                SwitchTo switchTo = new SwitchTo();
                switchTo.switchToPane(event);
            }
        });
    }

}
