package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.*;
import fr.univ_amu.iut.dao.factory.DAOFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * DAOFactoryJPA is an implementation of the DAOFactory interface. It creates a connection to a database in order to perform CRUD operations on the entities.
 *
 */
public class DAOFactoryJPA implements DAOFactory {

    /**
     * EntityManager object to be used for all the database operations.
     */
    private EntityManager entityManager;

    /**
     * Creates an EntityManager object if it is not initialized, and returns it.
     * @return an EntityManager object.
     */
    public EntityManager getEntityManager() {
        if(entityManager == null){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestionApp");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    /**
     * Constructor for the DAOFactoryJPA class.
     */
    public DAOFactoryJPA() {}

    /**
     * Creates an instance of the DAOQuestionsJPA class.
     * @return an instance of the DAOQuestionsJPA class.
     */
    @Override
    public DAOQuestions createDAOQuestions() {
        return new DAOQuestionsJPA(getEntityManager());
    }

    /**
     * Creates an instance of the DAOUsersJPA class.
     * @return an instance of the DAOUsersJPA class.
     */
    @Override
    public DAOUser createDAOUsers() {
        return new DAOUsersJPA(getEntityManager());
    }

    /**
     * Creates an instance of the DAORoomsJPA class.
     * @return an instance of the DAORoomsJPA class.
     */
    @Override
    public DAORooms createDAORooms() {
        return new DAORoomsJPA(getEntityManager());
    }

    /**
     * Creates an instance of the DAOScoresJPA class.
     * @return an instance of the DAOScoresJPA class.
     */
    @Override
    public DAOScores createDAOScores() {
        return new DAOScoresJPA(getEntityManager());
    }

    /**
     * Creates an instance of the DAOWhitelistJPA class.
     * @return an instance of the DAOWhitelistJPA class.
     */
    @Override
    public DAOWhitelist createDAOWhitelist() {
        return new DAOWhitelistJPA(getEntityManager());
    }
}