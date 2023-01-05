package fr.univ_amu.iut.components;

import fr.univ_amu.iut.tools.ClientTerminal;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;

/**
 * Custom widget used to simulate a linux console
 */
public class TerminalPane extends BorderPane {
    private String reponse;
    private TextArea textField;
    private TextField inputZone;

    /**
     * Constructor of the widget TerminalPane used to create a linux console
     */
    public TerminalPane(){
        super();

        // Initialize the connection
        ClientTerminal client = new ClientTerminal("localhost", 10007);
        try {
            client.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.listen();

        //Initialize the widgets
        inputZone = new TextField("");
        inputZone.setStyle("-fx-background-color: transparent");

        textField = new TextArea("Type help for more information");
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: transparent");

        inputZone.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    if (inputZone.getText().contains(reponse)){
                        textField.appendText("\nUser : "+inputZone.getText()+"\nBonne reponse !");
                        inputZone.setText("");
                        return;
                    }
                    //Send a request to the server
                    client.send(inputZone.getText());
                    //Write in the text field the command requested from the user
                    textField.appendText("\nUser : "+inputZone.getText());
                    //Read the server response
                    List<String> buffReceived = client.getBufferReceived();
                    //Write the server response in the text field
                    buffReceived.forEach((line) -> textField.appendText("\n"+line));
                    //Reset the input zone for the next message
                    inputZone.setText("");
                    textField.setScrollTop(Double.MAX_VALUE);
                }
            }
        });
        super.setCenter(textField);
        super.setBottom(inputZone);
    }

    /**
     *
     * @return the text field widget showing the communication between the client and the server
     */
    public TextArea getTextField(){
        return textField;
    }
    public void setReponse(String reponse){this.reponse = reponse;}
}
