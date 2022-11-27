package fr.univ_amu.iut;

import fr.univ_amu.iut.dao.DAOQuestionJDBC;
import fr.univ_amu.iut.dao.DAOUsersJDBC;
import fr.univ_amu.iut.database.Database;
import fr.univ_amu.iut.database.Question;
import fr.univ_amu.iut.database.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws SQLException, InterruptedException {
        /*
        Connection connection = Database.getDBConnection();
        DAOQuestionJDBC daoQuestionJDBC = new DAOQuestionJDBC();

        daoQuestionJDBC.findQuestionById(1);

        System.out.println(daoQuestionJDBC.findQuestionById(1));

         */

        Chrono c =new Chrono();
        c.launch();
        Thread.sleep(30000);
        System.out.println(c.getTimeString());
        c.stop();
    }

}
