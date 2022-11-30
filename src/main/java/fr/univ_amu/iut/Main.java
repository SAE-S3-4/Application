package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOQuestionJDBC;
import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.Database;
import javafx.application.Application;
import javafx.application.Platform;

import javax.swing.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        if (Taskbar.isTaskbarSupported()) {
            Taskbar taskbar = Taskbar.getTaskbar();
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                Image dockIcon = defaultToolkit.getImage(getClass().getResource("pictures/logo.png"));
                taskbar.setIconImage(dockIcon);
            }
        }
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) throws SQLException {
        Database.initDBConnection();
        if(Database.connection.equals(null)){

        }
        DAOUsersJDBC.initDAOUsersJDBC();
        DAOQuestionJDBC.initDAOQuestionsJDBC();
        launch();
    }
}