package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Scores;
import fr.univ_amu.iut.model.Users;

import java.util.List;

public interface DAOScores extends DAO<Scores, Integer>{
    List<Scores> getByRoom(Rooms room);
    Scores getByUserAndRoom(Users user, Rooms room);
    List<Scores> getBestByRoom(Rooms room);
}
