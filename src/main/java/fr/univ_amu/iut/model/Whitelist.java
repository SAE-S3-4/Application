package fr.univ_amu.iut.model;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Whitelist.findAll", query = "SELECT w FROM Whitelist w"),
        @NamedQuery(name = "Whitelist.getById", query = "SELECT w FROM Whitelist w WHERE w.id = :id"),
        @NamedQuery(name = "Whitelist.getByUserAndRoom", query = "SELECT w FROM Whitelist w WHERE w.user = :user AND w.room = :room")
})

@Table(name = "WHITELIST")
@Entity
public class Whitelist {
    @Id
    @Column(name="ID")
    int id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    Users user;

    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    Rooms room;

    public Whitelist() {
    }

    public Whitelist(Users user, Rooms room) {
        this.user = user;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }
}
