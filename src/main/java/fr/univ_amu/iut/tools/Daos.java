package fr.univ_amu.iut.tools;

import fr.univ_amu.iut.dao.DAOQuestions;
import fr.univ_amu.iut.dao.DAORooms;
import fr.univ_amu.iut.dao.DAOScores;
import fr.univ_amu.iut.dao.DAOUser;
import fr.univ_amu.iut.dao.factory.DAOFactoryProducer;
import fr.univ_amu.iut.dao.factory.DAOType;

public class Daos {
    public static DAOUser daoUser;
    public static DAOQuestions daoQuestions;
    public static DAOScores daoScores;
    public static DAORooms daoRooms;

    public static void initDaos(){
        Daos.daoQuestions = DAOFactoryProducer.getFactory(DAOType.JPA).createDAOQuestions();
        Daos.daoRooms = DAOFactoryProducer.getFactory(DAOType.JPA).createDAORooms();
        Daos.daoScores = DAOFactoryProducer.getFactory(DAOType.JPA).createDAOScores();
        Daos.daoUser = DAOFactoryProducer.getFactory(DAOType.JPA).createDAOUsers();
    }
}
