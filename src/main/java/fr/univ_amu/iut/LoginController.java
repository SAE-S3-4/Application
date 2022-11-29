package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String data;
    public static User userLogged;

    private SwitchController sc = new SwitchController();
    @FXML
    private TextField nickname;
    @FXML
    private TextField password;

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
    public void openRegister(ActionEvent e) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        Desktop.getDesktop().browse(new URI("https://google.com"));
    }

    @FXML
    public void secureSwitchTo(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {

        List<User> users;
        users = initialiseDatabaseLogin();

        List<String> loginUsers;
        loginUsers = initialiseInputLogin();
        String inputNickname = loginUsers.get(0);
        String inputPassword = loginUsers.get(1);
        inputPassword += inputNickname; //ajout du salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(inputPassword.getBytes(UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String hashPassword = no.toString(16);
        Boolean connected = false;

        for (User u: users) {
            if (inputNickname.equals(u.getNickname()) && hashPassword.equals(u.getPassword())) {
                SwitchController s = new SwitchController();
                s.switchTo(event);
                connected = true;
                userLogged = u;
            }
        }
        if(!connected){
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Identifiant ou mot de passe incorrect");
            invalidInput.show();
        }

    }

    public List<User> initialiseDatabaseLogin() throws SQLException {
        DAOUsersJDBC daoJDBC = DAOUsersJDBC.getDAOUsersJDBC(); //TODO Effacer daoJDB et la remplacer en pas par DAOUsersJDBC.getDAOUsersJDBC()
        List<User> listUsers;
        listUsers = daoJDBC.findAll();
        return listUsers;
    }

    public List<String> initialiseInputLogin() {
        List<String> login = new ArrayList<>();
        login.add(nickname.getText());
        login.add(password.getText());
        return login;
    }
}
