package fr.univ_amu.iut.home;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.database.Database;
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

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
public class TestPracticeMenu {
    Stage stage;

    //TODO: Ajouter le lancement du server terminal
    @BeforeEach
    public void accessToPracticeMenu(FxRobot robot) {
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
    }

    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestPracticeMenu.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new Main().start(TestPracticeMenu.this.stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });
    }

    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    @Test
    public void shouldHaveLevelOneButtonVisible() {
        verifyThat("#level1Btn", isVisible());
    }

    @Test
    public void shouldHaveLevelTwoButtonVisible() {
        verifyThat("#level2Btn", isVisible());
    }

    @Test
    public void shouldHaveLevelThreeButtonVisible() {
        verifyThat("#level3Btn", isVisible());
    }

    @Test
    public void shouldHaveLevelFourButtonVisible() {
        verifyThat("#level4Btn", isVisible());
    }

    @Test
    public void shouldHaveLevelFiveButtonVisible() {
        verifyThat("#level5Btn", isVisible());
    }

    @Test
    public void shouldAccessToLevelOne(FxRobot robot){
        robot.clickOn("#level1Btn");
    }
}
