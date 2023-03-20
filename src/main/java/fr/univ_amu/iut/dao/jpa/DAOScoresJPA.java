package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOScores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Scores;
import fr.univ_amu.iut.model.Users;

import java.util.List;

/**
 * DAOScoresJPA implements the interface DAOScores and provides the implementations of the methods declared in the interface.
 * The DAOScoresJPA class makes use of an EntityManager to handle the interactions with the database.
 *
 * @see     DAOScores
 */
public class DAOScoresJPA implements DAOScores {
    private EntityManager entityManager;

    /**
     * Constructor
     *
     * @param entityManager EntityManager used for the interactions with the database
     */
    public DAOScoresJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Deletes an object of type Scores from the database
     *
     * @param   obj The Scores object to be deleted
     * @return  true if the deletion was successful, false otherwise
     */
    @Override
    public boolean delete(Scores obj) {
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
     * Retrieves a list of all the Scores objects stored in the database
     *
     * @return A list containing all the Scores objects stored in the database
     */
    @Override
    public List<Scores> findAll() {
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.findAll", Scores.class);
        return query.getResultList();
    }

    /**
     * Retrieves the Scores object with a given id
     *
     * @param   id The id of the Scores object to be retrieved
     * @return  The Scores object with the given id
     */
    @Override
    public Scores getById(Integer id) {
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getById", Scores.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    /**
     * Inserts an object of type Scores into the database
     *
     * @param   obj The Scores object to be inserted
     * @return  The Scores object after it has been inserted
     */
    @Override
    public Scores insert(Scores obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Scores.class, obj.getId());
    }

    /**
     * Updates an object of type Scores in the database
     *
     * @param   obj The Scores object to be updated
     * @return  true if the update was successful, false otherwise
     */
    @Override
    public boolean update(Scores obj) {
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
     * Retrieves a list of all Scores objects for a given Room
     *
     * @param   room The Room whose associated Scores objects should be retrieved
     * @return  A list containing all the Scores objects associated with the given room
     */
    @Override
    public List<Scores> getByRoom(Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getByRoom", Scores.class);
        query.setParameter("room",room);

        return query.getResultList();
    }

    /**
     * Retrieves the Scores object with a given user and room
     *
     * @param   user The user whose Scores object should be retrieved
     * @param   room The room associated with the Scores object
     * @return  The Scores object associated with the given user and room
     */
    @Override
    public Scores getByUserAndRoom(Users user, Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getByUserAndRoom", Scores.class);
        query.setParameter("user", user).setParameter("room",room);

        return query.getSingleResult();
    }

    /**
     * Retrieves the five best Scores objects from a given Room
     *
     * @param   room The Room whose best Scores objects should be retrieved
     * @return  A list containing the five best Scores objects for the given room
     */
    @Override
    public List<Scores> getBestByRoom(Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getBestByRoom", Scores.class);
        query.setParameter("room",room);
        query.setMaxResults(5); // Equivalent to adding LIMIT 5 at the end of the SQL Query

        return query.getResultList();
    }
}