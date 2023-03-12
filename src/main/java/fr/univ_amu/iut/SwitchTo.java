package fr.univ_amu.iut;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * Class used to contain the methods used to switch in between pages
 * Mostly used in the custom widget SwitchToButton
 */
public class SwitchTo {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String data;

    /**
     * Constructor of SwitchTo
     */
    public SwitchTo(){

    }

    /**
     * Method used to switch between panes
     *
     * @param event
     * @throws IOException
     */
    public void switchToPane(ActionEvent event){
        try {
            Node node = (Node) event.getSource() ;
            data = (String) node.getUserData();
            root = FXMLLoader.load(getClass().getResource(data));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1280, 720);
            scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            stage.setTitle("Find the breach");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to switch from a page to a specified pane
     *
     * @param event
     * @param page
     * @throws IOException
     */
    public void switchToPane(ActionEvent event,String page) throws IOException {
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
     * Method used to switch between the "play" scenes
     *
     * @param event
     * @throws IOException
     */
    public void switchToController(ActionEvent event){
        Node node = (Node) event.getSource() ;
        String userData = ((String)(node.getUserData()));       //userData = id,pageFxml
        String[] userDataArray = userData.split(",");     // ["id","pageFxml"]
        switch (userDataArray[1]){
            case "practice":
                new PracticeController(parseInt(userDataArray[0]),event);
                break;
            case "game":
                new GameController(parseInt(userDataArray[0]),event);
                break;
            case "score":
                new ScoreBoardController(userDataArray[0],event);
                break;
        }
    }
}
