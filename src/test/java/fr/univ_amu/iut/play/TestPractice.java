package fr.univ_amu.iut.play;

import fr.univ_amu.iut.Main;
import fr.univ_amu.iut.model.Questions;
import fr.univ_amu.iut.model.Rooms;
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
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
public class TestPractice {
    private Stage stage;
    private Rooms practiceRoom;
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
        Daos.initDaos();
        practiceRoom = Daos.daoRooms.getById("practice");
    }

    @BeforeEach
    public void accessToPracticePane(FxRobot robot) {
        robot.clickOn("#switchToPaneButtonLogin");
        robot.clickOn("#loginForm_nickname");
        robot.write("ComptePourTests");
        robot.clickOn("#loginForm_password");
        robot.write("JeSuisUnMdp0#");
        robot.clickOn("#buttonLogin_login");
        robot.clickOn("#switchToPaneButtonPracticeLogged");
        robot.clickOn("#questionButton1");
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

        Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,1);
        verifyThat("#title", hasText(question.getTitle()));
    }

    @Test
    public void verifyThatHasLevel1Text(FxRobot robot) {
        Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,1);
        verifyThat("#text", hasText(question.getAssignement()));
    }

    @Test
    public void verifyThatHasSolutionTextLevel1(FxRobot robot) {
        Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,1);
        verifyThat("#text", hasText(question.getAnswer()));
    }

    @Test
    public void verifyThatHasSuggestionTextLevel1(FxRobot robot) {
        Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,1);
        verifyThat("#text", hasText(question.getSuggestion()));
    }

    @Test
    public void verifyThatTerminalWorks(FxRobot robot){
        Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,1);

        robot.clickOn("#terminalInputZone");
        robot.write(question.getAnswer());
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

        robot.clickOn("#nextQuestionButton");
        assertThat(stage.isShowing());
    }

    @Test
    public void verifyThatAllLevelsWork(FxRobot robot){
        for (int i =1; i< Daos.daoQuestions.findNumberQuestionsByRoom(practiceRoom);++i){

            Questions question = Daos.daoQuestions.getByRoomAndNum(practiceRoom,i);

            robot.clickOn("#terminalInputZone");
            robot.write(question.getAnswer());
            robot.press(KeyCode.ENTER).release(KeyCode.ENTER);

            robot.clickOn("#nextQuestionButton");
        }
        assertThat(stage.isShowing());
    }
}
