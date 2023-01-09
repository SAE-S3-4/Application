package fr.univ_amu.iut.home;

import fr.univ_amu.iut.Main;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
public class TestHome {
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestHome.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new Main().start(TestHome.this.stage);
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
    public void shouldInitializeStageIsShowing() {
        assertThat(stage.isShowing());
    }

    @Test
    public void shouldHaveLoginButton() {
        verifyThat("#switchToPaneButtonLogin", isVisible());
    }

    @Test
    public void shouldHaveButtonPractice() {
        verifyThat("#switchToPaneButtonPractice", isVisible());
    }

    @Test
    public void shouldHaveButtonGame() {
        verifyThat("#switchToPaneButtonGame", isVisible());
    }
    
    @Test
    public void loginButtonShouldRedirectToLogin(FxRobot robot){
        robot.clickOn("#switchToPaneButtonLogin");
        verifyThat("#buttonLogin_login", isVisible());
    }

    @Test
    public void practiceButtonUnloggedShouldRedirectToLogin(FxRobot robot){
        robot.clickOn("#switchToPaneButtonPractice");
        verifyThat("#buttonLogin_login", isVisible());
    }

    @Test
    public void gameButtonUnloggedShouldRedirectToLogin(FxRobot robot){
        robot.clickOn("#switchToPaneButtonGame");
        verifyThat("#buttonLogin_login", isVisible());
    }
}
