package fr.univ_amu.iut.dao.factory;

import fr.univ_amu.iut.dao.DAOQuestions;
import fr.univ_amu.iut.dao.DAORooms;
import fr.univ_amu.iut.dao.DAOScores;
import fr.univ_amu.iut.dao.DAOUser;

public interface DAOFactory {
    DAOQuestions createDAOQuestions();
    DAOUser createDAOUsers();
    DAORooms createDAORooms();
    DAOScores createDAOScores();

}
