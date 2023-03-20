package fr.univ_amu.iut;

import fr.univ_amu.iut.components.SwitchToPlayButton;
import fr.univ_amu.iut.tools.Daos;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * The controller of the view "Practice Menu"
 */
public class PracticeMenuController {
    @FXML
    private VBox levels;

    private final long MAX_PRACTICE_QUESTIONS = Daos.daoQuestions.findNumberQuestionsByRoom(Daos.daoRooms.getById("practice"));

    /**
     * Method used to initialize the level selection menu in the practice menu page
     */
    @FXML public void initialize() {
        for (int i=1; i<= MAX_PRACTICE_QUESTIONS; ++i) {
            SwitchToPlayButton b = new SwitchToPlayButton();
            b.setText("Question " + i);
            b.getStyleClass().add("questionButton");
            b.setUserData(i + ",practice");
            b.setId("questionButton"+i);
            levels.getChildren().add(b);
        }
    }
}
