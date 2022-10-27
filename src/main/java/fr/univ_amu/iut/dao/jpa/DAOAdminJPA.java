package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOAdmin;
import fr.univ_amu.iut.model.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DAOAdminJPA implements DAOAdmin {
    private EntityManager entityManager;
    public DAOAdminJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean delete(Admin obj) {
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
    public List<Admin> findAll() {
        TypedQuery<Admin> query = entityManager.createNamedQuery("Admin.findAll", Admin.class);
        return query.getResultList();
    }

    @Override
    public Admin getById(Integer id_user) {
        TypedQuery<Admin> query = entityManager.createNamedQuery("Admin.getById", Admin.class);
        query.setParameter("id_user", id_user);
        return query.getSingleResult();
    }

    @Override
    public Admin insert(Admin obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();

        return entityManager.find(Admin.class, obj.getId_admin());
    }

    @Override
    public boolean update(Admin obj) {
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
