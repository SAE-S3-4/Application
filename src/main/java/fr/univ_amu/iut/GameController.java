package fr.univ_amu.iut;

import fr.univ_amu.iut.components.SwitchToPlayButton;
import fr.univ_amu.iut.model.Scores;
import fr.univ_amu.iut.tools.Daos;
import fr.univ_amu.iut.tools.PlayController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Timer;
import java.util.TimerTask;

import static fr.univ_amu.iut.LoginController.userLogged;
import static fr.univ_amu.iut.components.ChronoLabel.c;

/**
 * Class used for the controller of the "game" page
 */
public class GameController extends PlayController {
    @FXML
    Button suggestionBtn;

    @FXML
    Button solutionBtn;

    public static Scores userScore;
    private int level;
    private final long MAX_GAME_QUESTIONS = Daos.daoQuestions.findNumberQuestionsByRoom(super.getCurrentRoom());
    private String roomId;

    /**
     * Constructor of the GameController
     *
     * @param level
     * @param event
     */
    public GameController(int level,String roomId, ActionEvent event) {
        super(level,roomId, event);
        this.roomId = roomId;
        this.level = level;
        initUserScore();
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
            if(newChunkedValue.equals("\nUser : "+getQuestion().getAnswer())) {
                Button nextQuestion = new Button();
                nextQuestion.setId("nextQuestionButton");
                if (level<MAX_GAME_QUESTIONS){
                    nextQuestion.setText("Passer à la prochaine question");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            level = level+1;
                            GameController.super.initLevel(level,roomId);
                            getBotBtns().getChildren().setAll(GameController.super.getSuggestionBtn(),GameController.super.getSolutionBtn());
                            initTimerButtons();
                        }
                    });
                }else {
                    nextQuestion.setText("Aller au Tableau des Scores");
                    userScore.setScore((int) (userScore.getScore()-(c.getDurationInSeconds()*10)));
                    c.stop();
                    Daos.daoScores.insert(userScore);
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            nextQuestion.setUserData(roomId+",score");
                            SwitchTo switchTo = new SwitchTo();
                            switchTo.switchToController(e);

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
                userScore.setScore(userScore.getScore()-50);
                showSuggestion();
            }
        });

        getSolutionBtn().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                userScore.setScore(userScore.getScore()-200);
                showSolution();
            }
        });
    }

    public void initUserScore(){
        userScore = new Scores((int) (1000 * (MAX_GAME_QUESTIONS)),userLogged,super.getCurrentRoom());
    }
}
