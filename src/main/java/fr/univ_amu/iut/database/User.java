package fr.univ_amu.iut.database;

import fr.univ_amu.iut.PlayController;
import fr.univ_amu.iut.components.ChronoLabel;

import static fr.univ_amu.iut.LoginController.userLogged;
import static fr.univ_amu.iut.components.ChronoLabel.c;

public class User {
    private int id_user;

    private String nickname;

    private String email;

    private int score;

    private String password;

    /**
     *
     * @param id_user
     * @param nickname
     * @param email
     * @param score
     * @param password
     */
    public User(int id_user, String nickname, String email, int score, String password) {
        this.id_user = id_user;
        this.nickname = nickname;
        this.email = email;
        this.score = score;
        this.password = password;
    }

    public User() {}

    /**
     *
     * @return the user's ID
     */
    public int getId_user() {
        return id_user;
    }

    /**
     *
     * @return the user's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return the user's score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method used to set the user's id
     *
     * @param id_user
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * Method used to set the user's nickname
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Method used to set the user's email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method used to set the user's score
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    public static int scoreCalculation() {
        int score = 1000;
        score -= c.getDurationInSeconds();
        userLogged.setScore(score);
        return score;
    }

    /**
     * Method used to set the user's password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return the User's information in a String format
     */
    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", score=" + score +
                ", password='" + password + '\'' +
                '}';
    }
}
