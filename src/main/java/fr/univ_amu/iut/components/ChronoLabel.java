package fr.univ_amu.iut.components;

import fr.univ_amu.iut.Chrono;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class ChronoLabel extends Label {
    private Chrono c;

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

    public ChronoLabel(long startTime) {
        super();
        c = new Chrono(startTime);
        super.textProperty().bind(new SimpleStringProperty(c.getTimeString()));
    }

    public Chrono getC() {
        return c;
    }
}
