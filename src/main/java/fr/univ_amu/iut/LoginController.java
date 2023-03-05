package fr.univ_amu.iut;


import fr.univ_amu.iut.dao.beans.Users;
import fr.univ_amu.iut.tools.Daos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigInteger;
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
    public static Users userLogged;
    @FXML
    private TextField nickname;
    @FXML
    private TextField password;

    @FXML
    private Button loginBtn;

    /**
     * Method used initialize the login button as the default button when the user presses ENTER
     */
    @FXML
    public void initialize() {
        loginBtn.setDefaultButton(true);
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
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(inputPassword.getBytes(UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String hashPassword = no.toString(16);
        
        Users userDB;
        userDB = Daos.daoUser.getById(nickname.getText());
        if (inputNickname.equals(userDB.getId()) && hashPassword.equals(userDB.getPassword())) {
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
