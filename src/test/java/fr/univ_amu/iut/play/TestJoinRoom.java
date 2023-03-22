package fr.univ_amu.iut.play;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Whitelist;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

@ExtendWith(ApplicationExtension.class)
public class TestJoinRoom {
    private Stage stage;
    @Start
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> {
            TestJoinRoom.this.stage = new Stage();
            try {
                FxToolkit.setupStage((sta) -> {
                    try {
                        new Main().start(TestJoinRoom.this.stage);
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

    @BeforeEach
    public void accessToPracticePane(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        robot.clickOn("#loginForm_nickname");
        robot.write("ComptePourTests");
        robot.clickOn("#loginForm_password");
        robot.write("JeSuisUnMdp0#");
        robot.clickOn("#buttonLogin_login");
        robot.clickOn("#switchToPaneButtonRoomLogged");
    }

    @AfterEach
    void afterEachTest(FxRobot robot) throws TimeoutException {
        FxToolkit.cleanupStages();
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    @Test
    public void shouldHaveAJoinBtn(FxRobot robot){
        verifyThat("#joinRoomBtn", isVisible());
    }

    @Test
    public void shouldHaveRoomIdTxtField(FxRobot robot){
        verifyThat("#roomId", isVisible());
    }

    @Test
    public void canJoinTestRoom(FxRobot robot){
        robot.clickOn("#roomId");
        robot.write("N9HJRL");
        robot.clickOn("#joinRoomBtn");

        verifyThat("#terminalInputZone", isVisible());
    }
}
