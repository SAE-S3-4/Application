package fr.univ_amu.iut;

import fr.univ_amu.iut.database.jdbc.DAOQuestionJDBC;
import fr.univ_amu.iut.database.jdbc.DAOUsersJDBC;
import fr.univ_amu.iut.database.Database;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {
    /**
     * Method used to launch the application
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException{
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
    public static void main(String[] args) {
        Database.initDBConnection();
        if(Database.connection.equals(null)){
            System.out.println("Pas de connection au serveur");
        }
        try {
            DAOUsersJDBC.initDAOUsersJDBC();
            DAOQuestionJDBC.initDAOQuestionsJDBC();
        } catch (SQLException e) {
            System.out.println("Erreur dans l'initialisation des DAOs");
            throw new RuntimeException(e);
        }
        launch();
    }
}