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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

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

    public PlayController(int level, String page, ActionEvent event)  {
        this.level = level;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/"+page+".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(this,1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.show();

        initActions();

        question = DAOQuestionJDBC.getDAOQuestionsJDB().findQuestionById(level);
        System.out.println(question);

        showInstructions();

        text.setWrapText(true);
        text.setMaxWidth(360);
        text.setMaxHeight(350);

        title.setWrapText(true);
        title.setMaxWidth(360);
        title.setMaxHeight(200);

        System.out.println(question.getSolution());

        terminalPane.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            String newChunkedValue = newValue.substring(oldValue.length());
            if(newChunkedValue.contains(question.getSolution())){
                Button nextQuestion = new Button("Passer à la prochaine Question");
                nextQuestion.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                            new PlayController(level+1,page,e);
                    }
                });
                botBtns.getChildren().setAll(new Button(),nextQuestion);
            }
        });
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