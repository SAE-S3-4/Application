package fr.univ_amu.iut.dao.jpa;

import fr.univ_amu.iut.dao.DAOAdmin;
import fr.univ_amu.iut.model.Admin;

import java.util.List;

public class DAOAdminJPA implements DAOAdmin {
    @Override
    public boolean delete(Admin obj) {
        return false;
    }

    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Admin getById(Integer id) {
        return null;
    }

    @Override
    public Admin insert(Admin obj) {
        return null;
    }

    @Override
    public boolean update(Admin obj) {
        return false;
    }
}
