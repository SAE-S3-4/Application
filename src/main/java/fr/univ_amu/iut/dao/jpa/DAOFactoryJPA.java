package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.*;
import fr.univ_amu.iut.dao.factory.DAOFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAOFactoryJPA implements DAOFactory {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if(entityManager == null){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestionApp");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public DAOFactoryJPA() {}

    @Override
    public DAOQuestions createDAOQuestions() {
        return new DAOQuestionsJPA(getEntityManager());
    }

    @Override
    public DAOUser createDAOUsers() {
        return new DAOUsersJPA(getEntityManager());
    }

    @Override
    public DAORooms createDAORooms() {
        return new DAORoomsJPA(getEntityManager());
    }

    @Override
    public DAOScores createDAOScores() {
        return new DAOScoresJPA(getEntityManager());
    }
    @Override
    public DAOWhitelist createDAOWhitelist() {
        return new DAOWhitelistJPA(getEntityManager());
    }
}
