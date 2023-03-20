package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAORooms;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import fr.univ_amu.iut.model.Rooms;

import java.util.List;

/**
 * This class DAORoomsJPA implements the DAORooms interface and provides methods to interact with Rooms data stored in a database.
 * It uses the Java Persistence API (JPA) to access and modify the data.
 *
 *  @see DAORooms
 */
public class DAORoomsJPA implements DAORooms {

    /** The entity manager. */
    private EntityManager entityManager;

    /**
     * Instantiates a new DAO rooms jpa.
     *
     * @param entityManager the entity manager
     */
    public DAORoomsJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Delete a room from the database.
     *
     * @param obj the room object to delete
     * @return true, if successful
     */
    @Override
    public boolean delete(Rooms obj) {
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
     * Find all rooms from the database.
     *
     * @return the list of rooms
     */
    @Override
    public List<Rooms> findAll() {
        TypedQuery<Rooms> query = entityManager.createNamedQuery("Rooms.findAll", Rooms.class);
        return query.getResultList();
    }

    /**
     * Gets a room by its id.
     *
     * @param id the room id
     * @return the room
     */
    @Override
    public Rooms getById(String id) {
        TypedQuery<Rooms> query = entityManager.createNamedQuery("Rooms.getById", Rooms.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Insert a new room into the database.
     *
     * @param obj the room object to insert
     * @return the inserted room
     */
    @Override
    public Rooms insert(Rooms obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Rooms.class, obj.getId());
    }

    /**
     * Update a room in the database.
     *
     * @param obj the room object to update
     * @return true, if successful
     */
    @Override
    public boolean update(Rooms obj) {
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
