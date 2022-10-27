package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.*;
import fr.univ_amu.iut.dao.factory.DAOFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAOFactoryJPA implements DAOFactory{

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if(entityManager == null){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gestionUsagesPU");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public DAOFactoryJPA() {}

    @Override
    public DAOAdmin createDAOAdmin(){return new DAOAdminJPA(getEntityManager());}

    @Override
    public DAOQuestion createDAOQuestion(){return new DAOQuestionJPA(getEntityManager());}

    @Override
    public DAOUsers createDAOUser(){return new DAOUsersJPA(getEntityManager());}
}
