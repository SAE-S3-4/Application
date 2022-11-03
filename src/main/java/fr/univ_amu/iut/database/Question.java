package fr.univ_amu.iut.database;

public class Question {
    private int ID;
    private String text;
    private String title;
    private String suggestion;
    private String solution;

    public Question(int ID, String text, String title, String suggestion, String solution) {
        this.ID = ID;
        this.text = text;
        this.title = title;
        this.suggestion = suggestion;
        this.solution = solution;
    }

    public Question() {
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

    public int getID() {
        return ID;
    }

    public String getText() {
        return text;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "ID=" + ID +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
