package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.beans.Rooms;
import fr.univ_amu.iut.dao.beans.Scores;
import fr.univ_amu.iut.tools.Daos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.univ_amu.iut.LoginController.userLogged;

/**
 * Class used as the controller of the leader board scene
 */
public class ScoreBoardController extends BorderPane {
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

    private Stage stage;
    private Scene scene;

    private Rooms room ;
    private List<Scores> podium;

    /**
     * Constructor used to create the LeaderBoard scene
     */
    public ScoreBoardController(String page, ActionEvent e) {
        loadFXML(e);

        this.room = Daos.daoRooms.getById(page);
        this.podium = Daos.daoScores.getBestByRoom(this.room);
    }

    /**
     * Method used to initialize the widgets in the scene with the correct values (the podium and the user logged score)
     *
     */
    @FXML
    public void initialize() {
        name.setText(userLogged.getId());
        score.setText(String.valueOf(Daos.daoScores.getByUserAndRoom(userLogged,room)));
        ArrayList<Label> podiumLines = new ArrayList<Label>(Arrays.asList(first,second,third,fourth,fifth));
        int numLabel = 0;
        for (Scores s:podium) {
            podiumLines.get(numLabel).setText((numLabel + 1) + " - " + s.getUser().getId() + " : " + s.getScore());
            numLabel++;
        }
    }

    /**
     * Method used to load the FXML of a certain page
     *
     * @param event
     */
    public void loadFXML(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/score.fxml"));
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

}
