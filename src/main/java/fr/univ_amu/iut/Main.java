package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOQuestionJDBC;
import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {
    private static Connection connection;

    private static DAOUsersJDBC daoUsersJDBC;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Find the breach");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) throws SQLException {
        connection = Database.getDBConnection();
        daoUsersJDBC = new DAOUsersJDBC();
        launch();
    }

    public static DAOUsersJDBC getDaoUsersJDBC(){return daoUsersJDBC;}
    public static Connection getDBConnection() {
        return connection;
    }
}