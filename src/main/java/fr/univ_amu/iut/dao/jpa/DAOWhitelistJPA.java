package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOWhitelist;
import fr.univ_amu.iut.model.Rooms;
import fr.univ_amu.iut.model.Whitelist;
import fr.univ_amu.iut.model.Users;
import fr.univ_amu.iut.model.Whitelist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DAOWhitelistJPA implements DAOWhitelist {
    private EntityManager entityManager;

    public DAOWhitelistJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

    @Override
    public List<Whitelist> findAll() {
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.findAll", Whitelist.class);
        return query.getResultList();
    }

    @Override
    public Whitelist getById(Integer id) {
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.getById", Whitelist.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public Whitelist insert(Whitelist obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Whitelist.class, obj.getId());
    }

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

    @Override
    public Whitelist getByUserAndRoom(Users user, Rooms room){
        TypedQuery<Whitelist> query = entityManager.createNamedQuery("Whitelist.getByUserAndRoom", Whitelist.class);
        query.setParameter("user", user).setParameter("room",room);

        return query.getSingleResult();
    }
}
