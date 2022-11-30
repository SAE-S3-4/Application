package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fr.univ_amu.iut.LoginController.userLogged;

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

    public ScoreBoardController() throws SQLException {
    }

    public List<User> initializePodium() throws SQLException {
        ArrayList<User> usersSorted = DAOUsersJDBC.getDAOUsersJDBC().getLeaderBoard();
        return usersSorted;
    }

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
}
