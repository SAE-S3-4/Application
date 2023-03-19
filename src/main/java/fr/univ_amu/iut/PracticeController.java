package fr.univ_amu.iut;

import fr.univ_amu.iut.tools.Daos;
import fr.univ_amu.iut.tools.PlayController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Class used as the controller of the practice page
 */
public class PracticeController extends PlayController {
    private int level;
    private final long MAX_PRACTICE_QUESTIONS = Daos.daoQuestions.findNumberQuestionsByRoom(super.getCurrentRoom());
    private Label levelLabel;

    /**
     * Constructor of PracticeController
     *
     * @param level
     * @param event
     */
    public PracticeController(int level, ActionEvent event) {
        super(level, "practice", event);
        this.level = level;

        //setting up the level Label
        levelLabel = new Label("Niveau : "+level);
        levelLabel.setId("levelNumber");
        getTopHbox().getChildren().add(levelLabel);
    }

    /**
     * Set the solution method
     */
    @Override
    public void setSolution(){
        getTerminalPane().getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            //Get all the old text - all the new text = what has been appended
            String newChunkedValue = newValue.substring(oldValue.length());

            if(newChunkedValue.equals("\nUser : "+getQuestion().getAnswer())) {
                Button nextQuestion = new Button();
                nextQuestion.setId("nextQuestionButton");

                if (level<MAX_PRACTICE_QUESTIONS){
                    nextQuestion.setText("Passer à la prochaine question");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            level = level+1;
                            PracticeController.super.initLevel(level,"practice");
                            updateLevelLabel();
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

    @Override
    public void initActions(){
        getSuggestionBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                showSuggestion();
            }
        });

        getSolutionBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                showSolution();
            }
        });
    }

    /**
     * Method used to update the levelLabel
     */
    public void updateLevelLabel(){
        levelLabel.setText("Niveau : "+level);
    }
}
