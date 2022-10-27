package fr.univ_amu.iut.model;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
        @NamedQuery(name = "Question.getById", query = "SELECT q FROM Question q WHERE q.q_id = :q_id"),
})

@Entity
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "Q_ID")
    int q_id;

    @Column(name = "TXT")
    String txt;

    @Column(name = "TITLE")
    String title;

    @Column(name = "SUGGESTION")
    String suggestion;

    @Column(name = "SOLUTION")
    String solution;

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
