package fr.univ_amu.iut.model;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.getById", query = "SELECT u FROM User u WHERE u.id = :id"),
})

@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID_USER")
    int id;

    @Column(name = "NICKNAME")
    String nom;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "SCORE")
    int score;

    @Column(name = "PASSWORD")
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
