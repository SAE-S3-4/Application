package fr.univ_amu.iut.components;

import fr.univ_amu.iut.tools.Chrono;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Custom widget used to create a custom-made Chronometer for the application
 */
public class ChronoLabel extends Label {
    private Chrono c;

    /**
     * Constructor of the label containing the Chronometer
     */
    public ChronoLabel() {
        super();
        c = new Chrono();
        c.launch();

        super.setId("chrono");

        //Launch chrono
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.0), e -> {
                    setText(c.getTimeString());
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Constructor used to make a label containing the Chronometer with a start point
     *
     * @param startTime
     */
    public ChronoLabel(long startTime) {
        super();
        c = new Chrono(startTime);
        super.textProperty().bind(new SimpleStringProperty(c.getTimeString()));
    }

    /**
     *
     * @return the Chronometer object used in the widget
     */
    public Chrono getC() {
        return c;
    }
}
