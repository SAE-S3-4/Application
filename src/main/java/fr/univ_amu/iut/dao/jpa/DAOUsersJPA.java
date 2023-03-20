package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import fr.univ_amu.iut.model.Users;

import java.util.List;

/**
 * This class is an implementation of DAOUser and provides methods for deleting, finding all, getting by ID, inserting, and updating Users.
 *
 * @see DAOUser
 */
public class DAOUsersJPA implements DAOUser {

    private EntityManager entityManager;

    /**
     * Constructor for DAOUsersJPA
     * @param entityManager the entity manager
     */
    public DAOUsersJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Deletes the user object passed in
     * @param obj the user object to delete
     * @return true if the user is successfully deleted and false otherwise
     */
    @Override
    public boolean delete(Users obj) {
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
     * Retrieves a list of all users
     * @return a list of all users
     */
    @Override
    public List<Users> findAll() {
        TypedQuery<Users> query = entityManager.createNamedQuery("Users.findAll", Users.class);
        return query.getResultList();
    }

    /**
     * Retrieves user with the specified ID
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     */
    @Override
    public Users getById(String id) {
        TypedQuery<Users> query = entityManager.createNamedQuery("Users.getById", Users.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Inserts a new user object
     * @param obj the user object to insert
     * @return the inserted user object
     */
    @Override
    public Users insert(Users obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Users.class, obj.getId());
    }

    /**
     * Updates an existing user object
     * @param obj the user object to update
     * @return true if the user is successfully updated and false otherwise
     */
    @Override
    public boolean update(Users obj) {
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
