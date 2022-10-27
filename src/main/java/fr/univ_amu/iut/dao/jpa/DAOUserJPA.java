package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOUser;
import fr.univ_amu.iut.model.User;

import java.util.List;

public class DAOUserJPA implements DAOUser {
    @Override
    public boolean delete(User obj) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User insert(User obj) {
        return null;
    }

    @Override
    public boolean update(User obj) {
        return false;
    }
}
