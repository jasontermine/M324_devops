package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DatabaseControllerTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        // Prüfen, ob JdbcTemplate korrekt injiziert wurde
        assertThat(jdbcTemplate).isNotNull();

        try {
            // Versuchen, eine einfache SQL-Abfrage auszuführen um die Verbindung zu testen
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class); 
            assertThat(result).isEqualTo(1);
        } catch (DataAccessException e) {
            System.err.println("Database connection failed: " + e.getMessage()); // Error Message
            assertThat(e).isNull(); // Der Test schlägt fehl, da eine Exception auftrat
        }
    }
}
