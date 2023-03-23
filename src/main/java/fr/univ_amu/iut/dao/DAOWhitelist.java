package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Scores;
import fr.univ_amu.iut.model.Users;
import fr.univ_amu.iut.model.Whitelist;

import java.util.ArrayList;

public interface DAOWhitelist extends DAO<Whitelist, ArrayList<Object>>{
    Whitelist getByUserAndRoom(Users user, Rooms room);
}
