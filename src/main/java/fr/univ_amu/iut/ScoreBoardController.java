package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<User> listUsers = initializePodium();

    public ScoreBoardController() throws SQLException {
    }

    public List<User> initializePodium() throws SQLException {
        ArrayList<User> usersSorted = DAOUsersJDBC.getDAOUsersJDBC().getLeaderBoard();
        return usersSorted;
    }

    @FXML
    public void initialize() throws SQLException {
        ArrayList<Label> podiumLines = new ArrayList<Label>(Arrays.asList(first,second,third,fourth,fifth));
        for (int numLabel = 0; numLabel < 5; numLabel++) {
            podiumLines.get(numLabel).setText((numLabel + 1) + " - " + listUsers.get(numLabel).getNickname() + " : " + listUsers.get(numLabel).getScore());
        }
    }
}
