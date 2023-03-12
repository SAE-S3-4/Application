package fr.univ_amu.iut;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class JoinRoomController{

    @FXML
    TextField roomId;

    @FXML
    Button joinRoomBtn;
    @FXML
    public void joinRoom(ActionEvent e){
        joinRoomBtn.setUserData(roomId.getText()+",room");
        SwitchTo switchTo = new SwitchTo();
        switchTo.switchToController(e);
    }

}
