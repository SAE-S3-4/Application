package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOQuestion;
import fr.univ_amu.iut.model.Question;
import fr.univ_amu.iut.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DAOQuestionJPA implements DAOQuestion {
    private EntityManager entityManager;

    public DAOQuestionJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean delete(Question obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Question> findAll() {
        TypedQuery<Question> query = entityManager.createNamedQuery("Question.findAll", Question.class);
        return query.getResultList();
    }

    @Override
    public Question getById(Integer q_id) {
        TypedQuery<Question> query = entityManager.createNamedQuery("Question.getById", Question.class);
        query.setParameter("q_id", q_id);
        return query.getSingleResult();
    }

    @Override
    public Question insert(Question obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Question.class, obj.getQ_id());
    }

    @Override
    public boolean update(Question obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
