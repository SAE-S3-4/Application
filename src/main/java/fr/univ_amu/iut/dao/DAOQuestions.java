package fr.univ_amu.iut.dao;

import fr.univ_amu.iut.model.Questions;
import fr.univ_amu.iut.model.Rooms;

import java.util.List;

public interface DAOQuestions extends DAO<Questions, Integer> {
    List<Questions> getByRoom(Rooms room);
    Questions getByRoomAndNum(Rooms room, Integer order_question);
    Long findNumberQuestionsByRoom(Rooms room);
}
