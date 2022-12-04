package fr.univ_amu.iut;

import fr.univ_amu.iut.components.TerminalPane;
import fr.univ_amu.iut.database.jdbc.DAOQuestionJDBC;
import fr.univ_amu.iut.database.Question;
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
import java.sql.SQLException;

import static fr.univ_amu.iut.LoginController.userLogged;
import static fr.univ_amu.iut.components.ChronoLabel.c;
import static java.lang.Integer.parseInt;

/**
 * Class used as the controller of the "play" (Practice and Play) scenes
 */
public class PlayController extends BorderPane {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static String data;
    @FXML
    Label title;
    @FXML
    Label text;
    @FXML
    Button homeBtn;
    @FXML
    Button practiceMenuBtn;
    @FXML
    Button playBtn;
    @FXML
    TerminalPane terminalPane;
    @FXML
    Button suggestionBtn;
    @FXML
    Button solutionBtn;
    @FXML
    HBox botBtns;
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/"+page+".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //Set the current window to the new node created
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(this,1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.show();

        question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(level);

        initActions();
        showInstructions();

        //Text Style
        text.setWrapText(true);
        text.setMaxWidth(360);
        text.setMaxHeight(350);

        title.setWrapText(true);
        title.setMaxWidth(360);
        title.setMaxHeight(200);
    }

    /**
     * Method used to initialize the actions of every widget in the scene
     */
    private void initActions(){
        terminalPane.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            //Get all the old text - all the new text = what has been appended
            String newChunkedValue = newValue.substring(oldValue.length());

            if(newChunkedValue.contains(question.getSolution())) {
                Button nextQuestion = new Button();
                nextQuestion.setId("nextQuestionButton");
                if (level<9){
                    nextQuestion.setText("Passer à la prochaine question");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            new PlayController(level + 1, page, e);
                        }
                    });

                } else if (page.equals("practice")) {
                    //TODO : Fix l'acces au menu apres avoir fini les questions d'entrainement
                    nextQuestion.setText("Revenir au Menu");
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                switchTo(e,"practice_menu");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                }else{
                    nextQuestion.setText("Voir le tableau des scores");
                    try {
                        DAOUsersJDBC.getDAOUsersJDBC().updateUserScore(userLogged);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    c.stop();
                    nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                switchTo(e,"score");
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                }
                botBtns.getChildren().setAll(nextQuestion);
            }
        });
        //TODO Creer une classe qui extend Button et lui implementer le setOnAction --> switchToPane pour ne pas utiliser le mm controller de partout
        homeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    switchTo(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        practiceMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    switchTo(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Node node = (Node) e.getSource() ;
                String userData = ((String)(node.getUserData()));       //userData = id,pageFxml
                String[] userDataArray = userData.split(",");     // ["id","pageFxml"]
                new PlayController(parseInt(userDataArray[0]), userDataArray[1] ,e);
            }
        });

        suggestionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                showSuggestion();
            }
        });

        solutionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                showSolution();
            }
        });
    }

    /**
     * Method used to switch between the scenes
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchTo(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource() ;
        data = (String) node.getUserData();
        root = FXMLLoader.load(getClass().getResource(data));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method used to switch between the "play" scenes
     *
     * @param event
     * @param page
     * @throws IOException
     */
    @FXML
    public void switchTo(ActionEvent event,String page) throws IOException {
        Node node = (Node) event.getSource() ;
        data = (String) node.getUserData();
        root = FXMLLoader.load(getClass().getResource("fxml/"+page+".fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.show();
    }

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

}
