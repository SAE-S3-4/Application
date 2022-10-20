package sae_s3.findthebreach;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class LoginController {
    @FXML
    public void openRegister(ActionEvent e) throws URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        Desktop.getDesktop().browse(new URI("https://google.com"));
    }
}
