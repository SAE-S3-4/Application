package fr.univ_amu.iut.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

public class QuestionTest {
    private Question question;
    @BeforeEach
    public void setUp() {
        question = new Question(1, "text", "title", "suggestion", "solution");
    }

    @Test
    public void testGetTitle() {
        assertEquals(question.getTitle(), "title");
    }

    @Test
    public void testSetTitle() {
        question.setTitle("new title");
        assertEquals(question.getTitle(), "new title");
    }

    @Test
    public void testGetSuggestion() {
        assertEquals(question.getSuggestion(), "suggestion");
    }

    @Test
    public void testSetSuggestion() {
        question.setSuggestion("new suggestion");
        assertEquals(question.getSuggestion(), "new suggestion");
    }

    @Test
    public void testGetSolution() {
        assertEquals(question.getSolution(), "solution");
    }

    @Test
    public void testSetSolution() {
        question.setSolution("new solution");
        assertEquals(question.getSolution(), "new solution");
    }

    @Test
    public void testGetID() {
        assertEquals(question.getID(), 1);
    }

    @Test
    public void testGetText() {
        assertEquals(question.getText(), "text");
    }

    @Test
    public void testSetID() {
        question.setID(2);
        assertEquals(question.getID(), 2);
    }

    @Test
    public void testSetText() {
        question.setText("new text");
        assertEquals(question.getText(), "new text");
    }

    @Test
    public void testToString() {
        assertNotNull(question.toString());
    }

    @Test
    public void testQuestionWithParameters() {
        Question question = new Question(1, "text", "title", "suggestion", "solution");
        assertEquals(1, question.getID());
        assertEquals("text", question.getText());
        assertEquals("title", question.getTitle());
        assertEquals("suggestion", question.getSuggestion());
        assertEquals("solution", question.getSolution());
    }

    @Test
    public void testQuestionWithoutParameters() {
        Question question = new Question();
        question.setID(2);
        question.setText("text");
        question.setTitle("title");
        question.setSuggestion("suggestion");
        question.setSolution("solution");
        assertEquals(2, question.getID());
        assertEquals("text", question.getText());
        assertEquals("title", question.getTitle());
        assertEquals("suggestion", question.getSuggestion());
        assertEquals("solution", question.getSolution());
    }

}