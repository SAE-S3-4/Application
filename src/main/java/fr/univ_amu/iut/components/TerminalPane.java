package fr.univ_amu.iut.components;

import fr.univ_amu.iut.connection.ClientTerminal;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;

public class TerminalPane extends BorderPane {
    public TerminalPane(){
        super();

        ClientTerminal client = new ClientTerminal("localhost", 10007);
        try {
            client.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.listen();

        TextField inputZone = new TextField("");
        inputZone.setStyle("-fx-background-color: transparent");

        TextArea textField = new TextArea("Type help for more information");
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: transparent");

        inputZone.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    //Envoie demande au serv
                    client.send(inputZone.getText());
                    //Ecris dans le champ la commande envoyé
                    textField.appendText("\nUser : "+inputZone.getText());
                    //Lis la reponse du serv
                    List<String> buffReceived = client.getBufferReceived();
                    //Ecris la rep du serv dans la zone de txt
                    buffReceived.forEach((line) -> textField.appendText("\n"+line));
                    //Reset
                    inputZone.setText("");
                    textField.setScrollTop(Double.MAX_VALUE);
                }
            }
        });

        super.setCenter(textField);
        super.setBottom(inputZone);
    }
}
