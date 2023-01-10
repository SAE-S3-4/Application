package fr.univ_amu.iut.play;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.Question;
import fr.univ_amu.iut.database.jdbc.DAOQuestionJDBC;
import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class TestPractice {
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestPractice.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new Main().start(TestPractice.this.stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }

    @BeforeEach
    public void accessToPracticePane(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        robot.clickOn("#loginForm_nickname");
        robot.write("a");
        robot.clickOn("#loginForm_password");
        robot.write("a");
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        robot.clickOn("#buttonLogin_login");
        robot.clickOn("#switchToPaneButtonPracticeLogged");
        robot.clickOn("#level1Btn");
    }

    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    @Test
    public void shouldHaveToSolutionBtn(FxRobot robot) {
        robot.clickOn("#solutionBtn");
        assertThat(stage.isShowing());
    }
    @Test
    public void shouldHaveToSuggestionBtn(FxRobot robot) {
        robot.clickOn("#suggestionBtn");
        assertThat(stage.isShowing());
    }

    @Test
    public void shouldHaveToPlayBtn(FxRobot robot) {
        robot.clickOn("#playBtn");
        assertThat(stage.isShowing());
    }

    @Test
    public void shouldHaveToPracticeMenuBtn(FxRobot robot) {
        robot.clickOn("#practiceMenuBtn");
        assertThat(stage.isShowing());
    }

    @Test
    public void shouldHaveHomeBtn(FxRobot robot) {
        robot.clickOn("#homeBtn");
        assertThat(stage.isShowing());
    }

    @Test
    public void verifyThatHasLevel1Title(FxRobot robot) {
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(1);
        verifyThat("#title", hasText(question.getTitle()));
    }

    @Test
    public void verifyThatHasLevel1Text(FxRobot robot) {
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(1);
        verifyThat("#text", hasText(question.getText()));
    }

    @Test
    public void verifyThatHasSolutionTextLevel1(FxRobot robot) {
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        robot.clickOn("#solutionBtn");

        Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(1);
        verifyThat("#text", hasText(question.getSolution()));
    }

    @Test
    public void verifyThatHasSuggestionTextLevel1(FxRobot robot) {
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        robot.clickOn("#suggestionBtn");

        Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(1);
        verifyThat("#text", hasText(question.getSuggestion()));
    }

    @Test
    public void verifyThatTerminalWorks(FxRobot robot){
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(1);

        robot.clickOn("#terminalInputZone");
        robot.write(question.getSolution());
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#nextQuestionButton");
        assertThat(stage.isShowing());
    }

    @Test
    public void verifyThatAllLevelsWork(FxRobot robot){
        Database.initDBConnection();
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i =1; i<16;++i){

            Question question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(i);

            robot.clickOn("#terminalInputZone");
            robot.write(question.getSolution());
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

            robot.clickOn("#nextQuestionButton");
        }
        assertThat(stage.isShowing());
    }
}
