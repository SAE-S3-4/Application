package fr.univ_amu.iut;

import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Whitelist;
import fr.univ_amu.iut.tools.Daos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.OffsetDateTime;
import java.time.ZoneId;


public class JoinRoomController{

    @FXML
    TextField roomId;

    @FXML
    Button joinRoomBtn;

    private Whitelist userWhitelistAccess;

    @FXML
    public void joinRoom(ActionEvent e){
        Rooms roomToJoin = Daos.daoRooms.getById(roomId.getText());
        if( checkIfUserIsAllowed() &&
            OffsetDateTime.now().isAfter(roomToJoin.getStart_date()) &&
            OffsetDateTime.now().isBefore(roomToJoin.getEnd_date())){

            joinRoomBtn.setUserData(roomId.getText()+",room");

            Daos.daoWhitelist.delete( userWhitelistAccess );

            SwitchTo switchTo = new SwitchTo();
            switchTo.switchToController(e);
        }else {
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas acces à cet Salon");
            invalidInput.show();
        }

    }

    private boolean checkIfUserIsAllowed(){
        try {
            userWhitelistAccess = Daos.daoWhitelist.getByUserAndRoom( LoginController.userLogged,  Daos.daoRooms.getById(roomId.getText()) );
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
