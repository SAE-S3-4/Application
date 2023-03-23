package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOWhitelist;
import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Whitelist;
import fr.univ_amu.iut.model.Users;
import fr.univ_amu.iut.model.Whitelist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * DAOWhitelistJPA implements the DAOWhitelist interface to manage the data of a Whitelist.
 *
 * @see DAOWhitelist
 */
public class DAOWhitelistJPA implements DAOWhitelist {
    private EntityManager entityManager;

    /**
     * Constructor of DAOWhitelistJPA which initializes the entityManager with the given parameter
     * @param entityManager EntityManager
     */
    public DAOWhitelistJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Deletes a Whitelist from the database
     * @param obj Whitelist to be deleted
     * @return boolean true if the Whitelist was successfully deleted, false otherwise
     */
    @Override
    public boolean delete(Whitelist obj) {
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
     * Finds all Whitelist objects in the database
     * @return List of Whitelists
     */
    @Override
    public List<Whitelist> findAll() {
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.findAll", Whitelist.class);
        return query.getResultList();
    }

    /**
     * Gets a Whitelist from the database by its id
     * @param id Integer used to find the Whitelist
     * @return Whitelist with the given id
     */
    @Override
    public Whitelist getById(ArrayList<Object> id) {
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.getById", Whitelist.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    /**
     * Inserts a Whitelist into the database
     * @param obj Whitelist to be inserted
     * @return Whitelist that was inserted
     */
    @Override
    public Whitelist insert(Whitelist obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        //return entityManager.find(Whitelist.class, obj.getRoom()obj.getUser());
        return null;
    }

    /**
     * Updates a Whitelist in the database
     * @param obj Whitelist to be updated
     * @return boolean true if the Whitelist was successfully updated, false otherwise
     */
    @Override
    public boolean update(Whitelist obj) {
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
     * Gets a Whitelist from the database by its user and room
     * @param user Users used to find the Whitelist
     * @param room Rooms used to find the Whitelist
     * @return Whitelist with the given user and room
     */
    @Override
    public Whitelist getByUserAndRoom(Users user, Rooms room){
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.getByUserAndRoom", Whitelist.class);
        query.setParameter("user", user).setParameter("room",room);

        return query.getSingleResult();
    }
}
