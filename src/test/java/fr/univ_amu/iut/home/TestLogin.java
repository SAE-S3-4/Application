package fr.univ_amu.iut.home;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.tools.Daos;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
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
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
public class TestLogin {
    Stage stage;

    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestLogin.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new Main().start(TestLogin.this.stage);
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
    public void shouldAccesToLogin(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        assertThat(stage.isShowing());
    }

    @Test
    public void shouldHaveAbortButton(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        verifyThat("#buttonAbort_login", hasText("Abandonner"));
    }

    @Test
    public void abortButtonIsShowing(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        verifyThat("#buttonAbort_login", isVisible());
    }

    @Test
    public void shouldHaveLoginButton(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        verifyThat("#buttonLogin_login", hasText("Se connecter"));
    }

    @Test
    public void loginButtonIsShowing(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        verifyThat("#buttonLogin_login", isVisible());
    }

    @Test
    public void shouldHaveEMailLabel(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        assertThat("#textLogin_Username".equals("<Label[id=textLogin_Username, styleClass=label]'Nom d'utilisateur :'>"));
        verifyThat("#textLogin_Username", isVisible());
    }

    @Test
    public void shouldHavePasswordLabel(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        assertThat("#textLogin_Password".equals("<Label[id=textLogin_Password, styleClass=label]'Mot de passe :'>"));
        verifyThat("#textLogin_Password", isVisible());
    }

    @Test
    public void shouldLogin(FxRobot robot){
        robot.clickOn("#switchToPaneButtonLogin");
        robot.clickOn("#loginForm_nickname");
        robot.write("johndoe");
        robot.clickOn("#loginForm_password");
        robot.write("password");
        robot.clickOn("#buttonLogin_login");
        verifyThat("#switchToPaneButtonGameLogged", isVisible());
    }

    @Test
    public void shouldAbortConnection(FxRobot robot){
        robot.clickOn("#switchToPaneButtonLogin");
        robot.clickOn("#loginForm_nickname");
        //TODO: Changer utilisateur
        robot.write("johndoe");
        robot.clickOn("#loginForm_password");
        robot.write("password");
        robot.clickOn("#buttonAbort_login");
        verifyThat("#switchToPaneButtonGame", isVisible());
    }
}
