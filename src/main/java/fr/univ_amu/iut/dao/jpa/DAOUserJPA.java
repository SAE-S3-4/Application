package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOUser;
import fr.univ_amu.iut.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.lang.reflect.Type;
import java.util.List;

public class DAOUserJPA implements DAOUser {
    private EntityManager entityManager;

    public DAOUserJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean delete(User obj) {
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
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User getById(Integer id) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.getById", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User insert(User obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(User.class, obj.getId());
    }

    @Override
    public boolean update(User obj) {
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
