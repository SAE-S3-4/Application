package fr.univ_amu.iut.dao.jdbc;


import fr.univ_amu.iut.dao.*;
import fr.univ_amu.iut.dao.factory.DAOFactory;

public class DAOFactoryJDBC implements DAOFactory {

    @Override
    public DAOQuestions createDAOQuestions() {
        return null;
    }

    @Override
    public DAOUser createDAOUsers() {
        return null;
    }

    @Override
    public DAORooms createDAORooms() {
        return null;
    }

    @Override
    public DAOScores createDAOScores() {
        return null;
    }

    @Override
    public DAOWhitelist createDAOWhitelist(){return null;};
}
