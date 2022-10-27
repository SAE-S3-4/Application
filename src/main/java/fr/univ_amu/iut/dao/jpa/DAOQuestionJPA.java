package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOQuestion;
import fr.univ_amu.iut.model.Question;

import java.util.List;

public class DAOQuestionJPA implements DAOQuestion {
    @Override
    public boolean delete(Question obj) {
        return false;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public Question getById(Integer id) {
        return null;
    }

    @Override
    public Question insert(Question obj) {
        return null;
    }

    @Override
    public boolean update(Question obj) {
        return false;
    }
}
