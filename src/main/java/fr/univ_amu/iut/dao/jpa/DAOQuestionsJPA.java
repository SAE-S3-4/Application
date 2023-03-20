package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOQuestions;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import fr.univ_amu.iut.model.Questions;
import fr.univ_amu.iut.model.Rooms;

import java.util.List;

/**
 * This class implements DAOQuestions interface and is used to make CRUD operations over the Questions entity.
 *
 * @see DAOQuestions
 */
public class DAOQuestionsJPA implements DAOQuestions {
    private EntityManager entityManager;

    /**
     * Constructor for DAOQuestionsJPA
     * @param entityManager EntityManager to use for CRUD operations
     */
    public DAOQuestionsJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Deletes the given Questions object from the database
     * @param obj Questions object to delete
     * @return true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(Questions obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds all Questions objects from the database
     * @return List of Questions objects
     */
    @Override
    public List<Questions> findAll() {
        TypedQuery<Questions> query = entityManager.createNamedQuery("Questions.findAll", Questions.class);
        return query.getResultList();
    }

    /**
     * Finds a Questions object with the given id
     * @param q_id id to search for
     * @return Questions object with the given id
     */
    @Override
    public Questions getById(Integer q_id) {
        TypedQuery<Questions> query = entityManager.createNamedQuery("Questions.getById", Questions.class);
        query.setParameter("q_id", q_id);
        return query.getSingleResult();
    }

    /**
     * Inserts a Questions object in the database
     * @param obj Questions object to insert
     * @return Questions object inserted
     */
    @Override
    public Questions insert(Questions obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Questions.class, obj.getId());
    }

    /**
     * Updates a Questions object in the database
     * @param obj Questions object to update
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean update(Questions obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds all Questions objects from the given Room
     * @param room Room to search for
     * @return List of Questions objects from the given Room
     */
    @Override
    public List<Questions> getByRoom(Rooms room){
        TypedQuery<Questions> query = entityManager.createNamedQuery("Questions.getByRoom", Questions.class);
        query.setParameter("room", room);
        return query.getResultList();
    }

    /**
     * Finds the Questions object with the given Room and number
     * @param room Room to search for
     * @param order_question number to search for
     * @return Questions object with the given Room and number
     */
    @Override
    public Questions getByRoomAndNum(Rooms room, Integer order_question){
        TypedQuery<Questions> query = entityManager.createNamedQuery("Questions.getByRoomAndNum", Questions.class);
        query.setParameter("room", room).setParameter("order_question",order_question);
        return query.getSingleResult();
    }

    /**
     * Finds the number of Questions objects from the given Room
     * @param room Room to search for
     * @return number of Questions objects from the given Room
     */
    @Override
    public Long findNumberQuestionsByRoom(Rooms room) {
        TypedQuery<Long> query = entityManager.createNamedQuery("Questions.findNumberQuestionsByRoom", Long.class);
        query.setParameter("room", room);

        return query.getSingleResult();
    }
}
