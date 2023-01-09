package fr.univ_amu.iut.tools;

import fr.univ_amu.iut.SwitchTo;
import fr.univ_amu.iut.components.TerminalPane;
import fr.univ_amu.iut.database.Question;
import fr.univ_amu.iut.database.jdbc.DAOQuestionJDBC;
import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Abstract class used as the controller of the "play" scenes
 */
public abstract class  PlayController extends BorderPane {
    private Stage stage;
    private Scene scene;
    @FXML
    Label title;
    @FXML
    Label text;
    @FXML
    TerminalPane terminalPane;
    @FXML
    Button suggestionBtn;
    @FXML
    Button solutionBtn;
    @FXML
    HBox botBtns;
    @FXML
    HBox topHbox;
    private int level;
    private Question question;
    private String page;

    /**
     * The controller used for the scenes where the user can play the game (practice and play)
     *
     * @param level
     * @param page
     * @param event
     */
    public PlayController(int level, String page, ActionEvent event)  {
        this.level = level;
        this.page = page;

        loadFXML(page,event);

        initLevel(level);

        setSolution();
        //Text Style
        text.setWrapText(true);
        text.setMaxWidth(360);
        text.setMaxHeight(350);

        title.setWrapText(true);
        title.setMaxWidth(360);
        title.setMaxHeight(200);
    }

    /**
     * Method used to set the conditions for a solution
     */
    public abstract void setSolution();

    /**
     * Method used to initialize a level
     *
     * @param level
     */
    public void initLevel(int level){
        setQuestion(level);
        initActions();
        showInstructions();
        terminalPane.setReponse(question.getSolution());
    }

    /**
     * Method used to load the FXML of a certain page
     *
     * @param page
     * @param event
     */
    public void loadFXML(String page, ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/"+page+".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        try {
            //Set the current window to the new node created
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(this,1280, 720);
            scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            stage.setTitle("Find the breach");
            stage.setScene(scene);
            stage.show();
        }catch (NullPointerException e){
            //Catch a NullPointerException coming from the fact that the stage could be null, or result null
        }
    }

    /**
     * Method used to set the question object for the current level
     *
     * @param level
     */
    public void setQuestion(int level){
        question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(level);
    }

    /**
     * Method used to initialize the actions of every widget in the scene
     */
    public abstract void initActions();

    /**
     * Method used to set the title and text to the solution for the question
     */
    @FXML
    public void showSolution(){
        title.setText(question.getTitle());
        text.setText(question.getSolution());
    }

    /**
     * Method used to set the title and text to the suggestion for the question
     */
    @FXML
    public void showSuggestion(){
        title.setText(question.getTitle());
        text.setText(question.getSuggestion());
    }

    /**
     * Method used to set the title and text to the instructions for the question
     */
    @FXML
    public void showInstructions(){
        title.setText(question.getTitle());
        text.setText(question.getText());
    }

    /**
     *
     * @return the Terminal Pane object
     */
    public TerminalPane getTerminalPane() {
        return terminalPane;
    }

    /**
     *
     * @return the current Question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     *
     * @return the current buttons on the bottom
     */
    public HBox getBotBtns() {
        return botBtns;
    }

    /**
     *
     * @return solution button
     */
    public Button getSolutionBtn() {
        return solutionBtn;
    }

    /**
     *
     * @return suggestion button
     */
    public Button getSuggestionBtn() {
        return suggestionBtn;
    }

    /**
     *
     * @return the top Hbox
     */
    public HBox getTopHbox(){
        return topHbox;
    }

}
