package fr.univ_amu.iut.dao.factory;

import fr.univ_amu.iut.dao.DAOAdmin;
import fr.univ_amu.iut.dao.DAOQuestion;
import fr.univ_amu.iut.dao.DAOUsers;

public interface DAOFactory {
    DAOAdmin createDAOAdmin();
    DAOQuestion createDAOQuestion();
    DAOUsers createDAOUser();
}
