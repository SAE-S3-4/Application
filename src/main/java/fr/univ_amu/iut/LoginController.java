package fr.univ_amu.iut;

import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Class used as the controller of the login page
 */
public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private String data;
    public static User userLogged;
    @FXML
    private TextField nickname;
    @FXML
    private TextField password;

    /**
     * Method used to switch between scenes
     *
     * @param event
     * @throws IOException
     */
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

    /**
     * Method used to create a hyperlink to the register web page, clickable from the login page
     *
     * @param e
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
    public void openRegister(ActionEvent e) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        Desktop.getDesktop().browse(new URI("https://google.com"));
    }

    /**
     * Method used to verify if the user is in the DataBase
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @FXML
    public void secureSwitchTo(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {

        List<User> users;
        users = initialiseDatabaseLogin();

        List<String> loginUsers;
        loginUsers = initialiseInputLogin();
        String inputNickname = loginUsers.get(0);
        String inputPassword = loginUsers.get(1);
        inputPassword += inputNickname;             // adding the salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(inputPassword.getBytes(UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String hashPassword = no.toString(16);
        Boolean connected = false;
        if(users.isEmpty()){
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Pas d'utilisateurs dans la base");
            invalidInput.show();
        }
        for (User u: users) {
            if (inputNickname.equals(u.getNickname()) && hashPassword.equals(u.getPassword())) {
                SwitchController s = new SwitchController();
                s.switchToPane(event);
                connected = true;
                userLogged = u;
            }
        }
        if(!connected){
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Identifiant ou mot de passe incorrect");
            invalidInput.show();
        }

    }

    /**
     *
     * @return the users in the DB
     * @throws SQLException
     */
    public List<User> initialiseDatabaseLogin() throws SQLException {
        return DAOUsersJDBC.getDAOUsersJDBC().findAll();
    }

    /**
     *
     * @return the list with the values in the text fields for the nickname and password
     */
    public List<String> initialiseInputLogin() {
        List<String> login = new ArrayList<>();
        login.add(nickname.getText());
        login.add(password.getText());
        return login;
    }
}
