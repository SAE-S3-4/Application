package fr.univ_amu.iut.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UserTest {
    private User u;
    @BeforeEach
    public void setUp() {
        u = new User(1, "nickname", "email", 10, "password");
    }

    @Test
    void testGetId_user() {
        assertEquals(1, u.getId_user());
    }

    @Test
    void testGetNickname() {
        assertEquals("nickname", u.getNickname());
    }

    @Test
    void testGetEmail() {
        assertEquals("email", u.getEmail());
    }

    @Test
    void testGetScore() {
        assertEquals(10, u.getScore());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", u.getPassword());
    }

    @Test
    void testSetId_user() {
        u.setId_user(2);
        assertEquals(2, u.getId_user());
    }

    @Test
    void testSetNickname() {
        u.setNickname("newNickname");
        assertEquals("newNickname", u.getNickname());
    }

    @Test
    void testSetEmail() {
        u.setEmail("newEmail");
        assertEquals("newEmail", u.getEmail());
    }

    @Test
    void testSetScore() {
        u.setScore(20);
        assertEquals(20, u.getScore());
    }

    @Test
    void testSetPassword() {
        u.setPassword("newPassword");
        assertEquals("newPassword", u.getPassword());
    }
}
