package fr.univ_amu.iut.dao.jdbc;


import fr.univ_amu.iut.dao.DAOAdmin;
import fr.univ_amu.iut.dao.DAOQuestion;
import fr.univ_amu.iut.dao.DAOUser;
import fr.univ_amu.iut.dao.factory.DAOFactory;

public class DAOFactoryJDBC implements DAOFactory {
    @Override
    public DAOAdmin createDAOAdmin(){return null;}

    @Override
    public DAOQuestion createDAOQuestion(){return null;}

    @Override
    public DAOUser createDAOUser(){return null;}
}
