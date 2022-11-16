package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    private ArrayList<User> listUsers = initializePodium();

    public ScoreBoardController() throws SQLException {
    }

    public ArrayList<User> initializePodium() throws SQLException {
        DAOUsersJDBC daoJDBC = DAOUsersJDBC.getDAOUsersJDBC();
        ArrayList<User> listUsers;
        listUsers = (ArrayList<User>) daoJDBC.findAll();
        Collections.sort(listUsers, Comparator.comparingInt(User::getScore).reversed());
        return listUsers;
    }

    @FXML
    public void initialize() throws SQLException {
        first.setText("1 - " + listUsers.get(0).getNickname());
        second.setText("2 - " + listUsers.get(1).getNickname());
        third.setText("3 - " + listUsers.get(2).getNickname());
        fourth.setText("4 - " + listUsers.get(3).getNickname());
        fifth.setText("5 - " + listUsers.get(4).getNickname());
    }
}
