package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.dao.beans.Rooms;
import fr.univ_amu.iut.dao.beans.Scores;
import fr.univ_amu.iut.dao.beans.Users;

import java.util.List;

public interface DAOScores extends DAO<Scores, Integer>{
    List<Scores> getByRoom(Rooms room);
    Scores getByUserAndRoom(Users user, Rooms room);
    List<Scores> getBestByRoom(Rooms room);
}
