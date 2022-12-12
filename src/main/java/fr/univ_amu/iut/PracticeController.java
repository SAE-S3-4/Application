package fr.univ_amu.iut;

import fr.univ_amu.iut.components.TerminalPane;
import fr.univ_amu.iut.tools.PlayController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Class used as the controller of the practice page
 */
public class PracticeController extends PlayController {
    private int level;
    private final int MAX_PRACTICE_QUESTIONS = 15;

    /**
     * Constructor of PracticeController
     *
     * @param level
     * @param event
     */
    public PracticeController(int level, ActionEvent event) {
        super(level, "practice", event);
        this.level = level;
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

                if (level<MAX_PRACTICE_QUESTIONS){
                    nextQuestion.setText("Passer à la prochaine question");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            level = level+1;
                            PracticeController.super.initLevel(level);
                            getBotBtns().getChildren().setAll(PracticeController.super.getSuggestionBtn(),PracticeController.super.getSolutionBtn());
                        }
                    });
                }else {
                    nextQuestion.setText("Revenir au Menu");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            SwitchTo switchTo = new SwitchTo();

                            try {
                                switchTo.switchToPane(e,"practice_menu");
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
