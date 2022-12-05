package fr.univ_amu.iut;

import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.univ_amu.iut.LoginController.userLogged;
import static java.lang.Integer.parseInt;

/**
 * Class used as the controller of the leader board scene
 */
public class ScoreBoardController {
    @FXML
    Label first;

    @FXML
    Label second;

    @FXML
    Label third;

    @FXML
    Label fourth;

    @FXML
    Label fifth;

    @FXML
    Label score;

    @FXML
    Label name;

    private List<User> listUsers = initializePodium();

    /**
     * Constructor used to create the LeaderBoard scene
     *
     * @throws SQLException
     */
    public ScoreBoardController() throws SQLException {
    }

    /**
     * Method used to get the 5 players with the highest score
     *
     * @return a list containing the 5 players with the highest score
     * @throws SQLException
     */
    public List<User> initializePodium() throws SQLException {
        ArrayList<User> usersSorted = DAOUsersJDBC.getDAOUsersJDBC().getLeaderBoard();
        return usersSorted;
    }

    /**
     * Method used to initialize the widgets in the scene with the correct values (the podium and the user logged score)
     *
     * @throws SQLException
     */
    @FXML
    public void initialize() throws SQLException {
        name.setText(userLogged.getNickname());
        score.setText(String.valueOf(userLogged.getScore()));
        ArrayList<Label> podiumLines = new ArrayList<Label>(Arrays.asList(first,second,third,fourth,fifth));
        int numLabel = 0;
        for (User u:listUsers) {
            podiumLines.get(numLabel).setText((numLabel + 1) + " - " + u.getNickname() + " : " + u.getScore());
            numLabel++;
        }
    }


    /**
     * Method used to switch between the scenes
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchToPane(ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        String data;

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
     * Method used to switch between the scenes where the player can play
     *
     * @param event
     */
    @FXML
    public void switchToPlay(ActionEvent event) {
        Node node = (Node) event.getSource() ;
        String userData = ((String)(node.getUserData()));       //userData = id,pageFxml
        String[] userDataArray = userData.split(",");     // ["id","pageFxml"]
        new PlayController(parseInt(userDataArray[0]), userDataArray[1] ,event);
    }
}
