package fr.univ_amu.iut.model;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    @JoinColumn(name = "ID_USER")
    int id_user;

    @Id
    @GeneratedValue
    @Column(name = "ID_ADMIN")
    int id_admin;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
}
