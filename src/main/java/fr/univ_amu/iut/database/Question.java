package fr.univ_amu.iut.database;

public class Question {
    private int ID;
    private String text;
    private String title;
    private String suggestion;
    private String solution;


    /**
     *
     * @param ID
     * @param text
     * @param title
     * @param suggestion
     * @param solution
     */
    public Question(int ID, String text, String title, String suggestion, String solution) {
        this.ID = ID;
        this.text = text;
        this.title = title;
        this.suggestion = suggestion;
        this.solution = solution;
    }

    public Question() {
    }

    /**
     *
     * @return the question title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method used to set the title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Method used to set the suggestion
     * @param suggestion
     */
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    /**
     *
     * @return the question's solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Method used to set the question's solution
     *
     * @param solution
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     *
     * @return the question's ID
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @return the question's text
     */
    public String getText() {
        return text;
    }

    /**
     * Method used to set the question's ID
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Method used to set the question's text
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return the Question information in a String format
     */
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
