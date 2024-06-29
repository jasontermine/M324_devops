package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.dao.DataAccessException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DatabaseControllerTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String tableName = "todo";

    /**
     * Tests if the application can connect to the database.
     * This test ensures that the JdbcTemplate is correctly injected and
     * can execute a simple SQL query.
     */
    @Test
    public void testDatabaseConnection() {
        assertThat(jdbcTemplate).isNotNull();

        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            assertThat(result).isEqualTo(1);
        } catch (DataAccessException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            assertThat(e).isNull();
        }
    }

    /**
     * Tests if a specific table exists in the database.
     * This test checks the database metadata to verify the presence of the table
     * with the given name.
     */
    @SuppressWarnings("null")
    @Test
    public void testTableExists() {
        assertThat(jdbcTemplate).isNotNull();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[] { "TABLE" })) {
                assertThat(resultSet.next()).isTrue();
            }
        } catch (SQLException e) {
            System.err.println("Failed to check table existence: " + e.getMessage());
            assertThat(e.getMessage()).isNull();
        }
    }

        /**
     * Tests creating a test entry in the database, querying it, and rolling back the transaction.
     * This test ensures that a new entry can be inserted and retrieved correctly, and that the transaction is rolled back.
     */
    @Test
    @Transactional
    public void testCreateAndRollbackEntry() {
        assertThat(jdbcTemplate).isNotNull();

        String insertSql = "INSERT INTO " + tableName + " (task, done) VALUES (?, ?)";
        String querySql = "SELECT task FROM " + tableName + " WHERE id = ?";
        String testTask = "Test Task";
        boolean testDone = false;

        // Insert a test entry
        jdbcTemplate.update(insertSql, testTask, testDone);

        // Get the last inserted ID
        Integer lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        // Query the test entry
        List<Map<String, Object>> results = jdbcTemplate.queryForList(querySql, lastInsertedId);
        assertThat(results).hasSize(1);
        assertThat(results.get(0).get("task")).isEqualTo(testTask);
    }
}
