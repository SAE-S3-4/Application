package fr.univ_amu.iut.components;

import fr.univ_amu.iut.LoginController;
import fr.univ_amu.iut.tools.ClientTerminal;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.util.List;

import static fr.univ_amu.iut.LoginController.userLogged;

public class ChatPane extends BorderPane {
    private String reponse;
    private TextArea textField;
    private TextField inputZone;

    /**
     * Constructor of the widget TerminalPane used to create a linux console
     */
    public ChatPane(){
        super();
        //super.setStyle("-fx-alignment : RIGHT");
        super.setMaxWidth(600);

        // Initialize the connection
        ClientTerminal client = new ClientTerminal("findthebreach.sytes.net", 10013);
        client.connect();
        client.listen();

        //Initialize the widgets
        inputZone = new TextField("");
        inputZone.setStyle("-fx-background-color: transparent");
        inputZone.setId("terminalInputZone");

        textField = new TextArea("Ecrire dans le chat");
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: transparent");
        textField.setId("terminalTxtField");

        inputZone.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    textField.appendText("\n" + userLogged.getName() + " : " + inputZone.getText());
                    inputZone.setText("");
                    //Implement the global chat
                }
            }
        });

        Button changeBtn = new Button("⟶");
        changeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1),ChatPane.this);
                translateTransition.setFromX(300);
                translateTransition.setToX(1280);
                translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        ChatPane.super.toBack();
                    }
                });
                translateTransition.play();
            }
        });

        super.setCenter(textField);
        super.setBottom(inputZone);
        super.setRight(changeBtn);
    }

}
