package fr.univ_amu.iut;

import fr.univ_amu.iut.components.TerminalPane;
import fr.univ_amu.iut.dao.DAOQuestionJDBC;
import fr.univ_amu.iut.database.Question;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
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

    private Question question;
    public GameController(){
        int level = 2;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initActions();

        question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(level);
        System.out.println(question);


        showInstructions();
        text.setWrapText(true);
        text.setMaxWidth(360);
        text.setMaxHeight(350);
        //showSuggestion(question);
        //showSolution(question);
        System.out.println(question.getSolution());

        terminalPane.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            String newChunkedValue = newValue.substring(oldValue.length());
            if(newChunkedValue.contains(question.getSolution())){
                System.out.println("bonne reponse!");
                Button nextQuestion = new Button("Passer à la prochaine Question");
                nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        new Practice(level+1,e);
                    }
                });
                botBtns.getChildren().set(1,nextQuestion);
            }
        });
    }

    @FXML
    public void switchTo(ActionEvent event) throws IOException {
        //TODO : Passer le ChronoLabel actuel en parametre à chaque changement
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

    private void initActions(){
        //TODO Creer une classe qui extend Button et lui implementer le setOnAction --> switchTo pour ne pas utiliser le mm controller de partout
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
                try {
                    switchTo(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
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

    @FXML
    public void showSolution(){
        title.setText(question.getTitle());
        text.setText(question.getSolution());
    }

    @FXML
    public void showSuggestion(){
        title.setText(question.getTitle());
        text.setText(question.getSuggestion());
    }

    @FXML
    public void showInstructions(){
        title.setText(question.getTitle());
        text.setText(question.getText());
    }
}
