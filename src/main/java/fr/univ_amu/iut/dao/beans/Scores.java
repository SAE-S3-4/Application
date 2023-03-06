package fr.univ_amu.iut.dao.beans;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Scores.findAll", query = "SELECT s FROM Scores s"),
        @NamedQuery(name = "Scores.getById", query = "SELECT s FROM Scores s WHERE s.id = :id"),
        @NamedQuery(name = "Scores.getByRoom", query = "SELECT s FROM Scores s WHERE s.room = :room"),
        @NamedQuery(name = "Scores.getByUserAndRoom", query = "SELECT s FROM Scores s WHERE s.user = :user AND s.room = :room"),
        @NamedQuery(name = "Scores.getBestByRoom", query = "SELECT s FROM Scores s WHERE s.room = :room AND s.score IS NOT NULL ORDER BY s.score DESC")
})

@Entity
public class Scores {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    int id;

    @Column(name = "SCORE")
    int score;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    Users user;

    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    Rooms room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    @Override
    public String toString() {
        return "Scores{" +
                "id=" + id +
                ", score=" + score +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}
