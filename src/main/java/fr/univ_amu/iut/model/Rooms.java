package fr.univ_amu.iut.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.OffsetDateTime;

@NamedQueries({
        @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
        @NamedQuery(name = "Rooms.getById", query = "SELECT r FROM Rooms r WHERE r.id = :id"),
})

@Table(name = "ROOMS")
@Entity
public class Rooms {
    @Id
    @Column(name="ID")
    String id;

    @Column(name = "NAME")
    String name;

    @Column(name = "ADMIN_ID")
    String admin_id;

    @Column(name = "START_DATE")
    OffsetDateTime start_date;

    @Column(name = "END_DATE")
    OffsetDateTime end_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public OffsetDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(OffsetDateTime start_date) {
        this.start_date = start_date;
    }

    public OffsetDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(OffsetDateTime end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", admin_id='" + admin_id + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                '}';
    }
}
