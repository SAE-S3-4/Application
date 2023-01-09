package fr.univ_amu.iut.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void testGetDBConnection() {
        Database.getDBConnection();
        assertNotNull(Database.connection);
    }

    @Test
    void testInitDBConnection() {
        Database.initDBConnection();
        assertNotNull(Database.connection);
    }

    @Test
    void testGetConnection() {
        Database.initDBConnection();
        assertNotNull(Database.getConnetion());
    }


}