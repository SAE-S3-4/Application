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

/**
 * Class that represents the controller of the page "Join Room"
 */
public class JoinRoomController{

    @FXML
    TextField roomId;

    @FXML
    Button joinRoomBtn;

    /**
     * Method used to join a room
     *
     * @param e
     */
    @FXML
    public void joinRoom(ActionEvent e){
        Rooms roomToJoin = Daos.daoRooms.getById(roomId.getText());

        //Checks if the user is allowed and if the room is still accessible (start date and end date)
        if( checkIfUserIsAllowed() &&
            OffsetDateTime.now().isAfter(roomToJoin.getStart_date()) &&
            OffsetDateTime.now().isBefore(roomToJoin.getEnd_date())){

            joinRoomBtn.setUserData(roomId.getText()+",room");

            SwitchTo switchTo = new SwitchTo();
            switchTo.switchToController(e);
        }else {
            Alert invalidInput = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas rejoindre ce Salon");
            invalidInput.show();
        }

    }

    /**
     * Method used to check if the logged user has been invited to the room
     *
     * @return boolean that indicates if the user is allowed
     */
    private boolean checkIfUserIsAllowed(){
        try {
            Daos.daoWhitelist.getByUserAndRoom( LoginController.userLogged,  Daos.daoRooms.getById(roomId.getText()) );
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
