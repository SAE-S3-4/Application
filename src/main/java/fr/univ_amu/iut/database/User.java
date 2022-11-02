package fr.univ_amu.iut.database;

public class User {
    private int id_user;

    private String nickname;

    private String email;

    private int score;

    private String password;

    public User(int id_user, String nickname, String email, int score, String password) {
        this.id_user = id_user;
        this.nickname = nickname;
        this.email = email;
        this.score = score;
        this.password = password;
    }

    public User() {}

    public int getId_user() {
        return id_user;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public int getScore() {
        return score;
    }

    public String getPassword() {
        return password;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
