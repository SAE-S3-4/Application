package fr.univ_amu.iut.database;

public class Question {
    private String ID;
    private String text;

    public Question(String ID, String text) {
        this.ID = ID;
        this.text = text;
    }

    public Question() {
    }

    public String getID() {
        return ID;
    }

    public String getText() {
        return text;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "ID='" + ID + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
