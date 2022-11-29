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
    private String page;

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

    private void initActions(){
        terminalPane.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            //Get all the old text - all the new text = what has been appended
            String newChunkedValue = newValue.substring(oldValue.length());

            if(newChunkedValue.contains(question.getSolution())) {
                Button nextQuestion = new Button();
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
                    nextQuestion.setText("Voir le LeaderBoard");
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
                botBtns.getChildren().setAll(new Button(), nextQuestion);
            }
        });
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
