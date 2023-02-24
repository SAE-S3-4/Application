package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOScores;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import fr.univ_amu.iut.dao.beans.Rooms;
import fr.univ_amu.iut.dao.beans.Scores;
import fr.univ_amu.iut.dao.beans.Users;

import java.util.List;

public class DAOScoresJPA implements DAOScores {
    private EntityManager entityManager;

    public DAOScoresJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

    @Override
    public List<Scores> findAll() {
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.findAll", Scores.class);
        return query.getResultList();
    }

    @Override
    public Scores getById(Integer id) {
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getById", Scores.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public Scores insert(Scores obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Scores.class, obj.getId());
    }

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

    @Override
    public List<Scores> getByRoom(Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getByRoom", Scores.class);
        query.setParameter("room",room);

        return query.getResultList();
    }

    @Override
    public Scores getByUserAndRoom(Users user, Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getByUserAndRoom", Scores.class);
        query.setParameter("user", user).setParameter("room",room);

        return query.getSingleResult();
    }

    @Override
    public List<Scores> getBestByRoom(Rooms room){
        TypedQuery<Scores> query = entityManager.createNamedQuery("Scores.getBestByRoom", Scores.class);
        query.setParameter("room",room);
        query.setMaxResults(5); // Equivalent to adding LIMIT 5 at the end of the SQL Query
        
        return query.getResultList();
    }
}
