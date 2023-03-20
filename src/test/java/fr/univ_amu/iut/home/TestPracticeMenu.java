package fr.univ_amu.iut.home;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.tools.Daos;
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
        robot.write("johndoe");
        robot.clickOn("#loginForm_password");
        robot.write("password");
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
        Daos.initDaos();
    }

    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    @Test
    public void shouldHaveLevelOneButtonVisible() {
        verifyThat("#questionButton1", isVisible());
    }

    @Test
    public void shouldHaveLevelTwoButtonVisible() {
        verifyThat("#questionButton2", isVisible());
    }

    @Test
    public void shouldHaveLevelThreeButtonVisible() {
        verifyThat("#questionButton4", isVisible());
    }

    @Test
    public void shouldHaveLevelFourButtonVisible() {
        verifyThat("#questionButton3", isVisible());
    }

    @Test
    public void shouldHaveLevelFiveButtonVisible() {
        verifyThat("#questionButton5", isVisible());
    }

    @Test
    public void shouldAccessToLevelOne(FxRobot robot){
        robot.clickOn("#questionButton1");
    }
}
