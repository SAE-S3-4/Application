package fr.univ_amu.iut;

import fr.univ_amu.iut.tools.PlayController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class used for the controller of the "game" page
 */
public class GameController extends PlayController {
    @FXML
    Button suggestionBtn;

    @FXML
    Button solutionBtn;
    private int level;
    private final int MAX_GAME_QUESTIONS = 20;

    /**
     * Constructor of the GameController
     *
     * @param level
     * @param event
     */
    public GameController(int level, ActionEvent event) {
        super(level, "game", event);
        this.level = level;
        initTimerButtons();
    }

    /**
     * Method which allows the management of the display of the buttons solutions
     * and suggestions according to time
     */
    public void initTimerButtons() {
        Timer timer = new Timer(true);
        suggestionBtn.setVisible(false);
        solutionBtn.setVisible(false);
        timer.schedule(new TimerTask() {
            public void run() {
                suggestionBtn.setVisible(true);
            }
        }, 300000);
        timer.schedule(new TimerTask() {
            public void run() {
                solutionBtn.setVisible(true);
            }
        }, 600000);
    }

    /**
     * Set the solution method
     */
    @Override
    public void setSolution(){
        getTerminalPane().getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            //Get all the old text - all the new text = what has been appended
            String newChunkedValue = newValue.substring(oldValue.length());
            if(newChunkedValue.contains(getQuestion().getSolution())) {
                Button nextQuestion = new Button();
                nextQuestion.setId("nextQuestionButton");
                if (level<MAX_GAME_QUESTIONS){
                    nextQuestion.setText("Passer à la prochaine question");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            level = level+1;
                            GameController.super.initLevel(level);
                            getBotBtns().getChildren().setAll(GameController.super.getSuggestionBtn(),GameController.super.getSolutionBtn());
                            initTimerButtons();
                        }
                    });
                }else {

                    nextQuestion.setText("Passer au score board");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            SwitchTo switchTo = new SwitchTo();

                            try {
                                switchTo.switchToPane(e,"score");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    });
                }
                getBotBtns().getChildren().setAll(nextQuestion);
            }
        });
    }
}
