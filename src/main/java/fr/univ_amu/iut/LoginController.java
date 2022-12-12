package fr.univ_amu.iut;

import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
import fr.univ_amu.iut.database.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

import static fr.univ_amu.iut.database.jdbc.DAOUsersJDBC.getDAOUsersJDBC;
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

    @FXML
    private Button loginBtn;

    @FXML
    public void initialize() {
        loginBtn.setDefaultButton(true);
    }

    /**
     * Method used to create a hyperlink to the register web page, clickable from the login page
     *
     * @param e
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
    public void openRegister(ActionEvent e) throws IOException, URISyntaxException {
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
        Boolean connected = false;
        String inputNickname;
        String inputPassword;

        List<String> loginUsers;
        loginUsers = initialiseInputLogin();
        inputNickname = loginUsers.get(0);
        inputPassword = loginUsers.get(1);

        inputPassword += inputNickname;             // adding the salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(inputPassword.getBytes(UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String hashPassword = no.toString(16);
        
        User userDB;
        userDB = DAOUsersJDBC.getDAOUsersJDBC().initialiseDatabaseLoginByNickname(inputNickname);
        if (inputNickname.equals(userDB.getNickname()) && hashPassword.equals(userDB.getPassword())) {
            SwitchTo switchTo = new SwitchTo();
            switchTo.switchToPane(event);
            connected = true;
            userLogged = userDB;
        }
        if(!connected){
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Identifiant ou mot de passe incorrect");
            invalidInput.show();
        }
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
