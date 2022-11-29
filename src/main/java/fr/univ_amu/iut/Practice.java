package fr.univ_amu.iut;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Practice {
    private Stage stage;
    private Scene scene;
    public Practice (int level, ActionEvent event){
        PracticeController p1 = new PracticeController(level);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(p1,1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.show();
    }
}
