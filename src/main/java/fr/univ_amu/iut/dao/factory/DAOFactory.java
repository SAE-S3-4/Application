package fr.univ_amu.iut.dao.factory;

import fr.univ_amu.iut.dao.*;

public interface DAOFactory {
    DAOQuestions createDAOQuestions();
    DAOUser createDAOUsers();
    DAORooms createDAORooms();
    DAOScores createDAOScores();
    DAOWhitelist createDAOWhitelist();

}
