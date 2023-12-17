package com.example.storedprocedur;

import com.example.DatabaseConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Component
@PropertySource("classpath:application.yml")
public class StoredProcedureCreator {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DatabaseConfig databaseConfig;

    @PostConstruct
    public void dropStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            connection.setAutoCommit(true);

            String dropProcedureSql = "DROP FUNCTION IF EXISTS insert_user(INT, VARCHAR(255), VARCHAR(255));";

            try (Statement statement = connection.createStatement()) {
                statement.execute(dropProcedureSql);
                System.out.println("Stored procedure dropped successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createInsertStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String createProcedureSql = "CREATE OR REPLACE FUNCTION insert_user(IN p_id BIGINT, IN p_first_name VARCHAR(255), IN p_last_name VARCHAR(255)) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    INSERT INTO users (id, first_name, last_name) VALUES (p_id, p_first_name, p_last_name); " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(createProcedureSql);
                System.out.println("Insert Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       }

    @PostConstruct
    public void createUpdateStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String updateProcedureSql = "CREATE OR REPLACE FUNCTION update_user(IN p_id BIGINT, IN p_first_name VARCHAR(255), IN p_last_name VARCHAR(255)) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    UPDATE users SET first_name = p_first_name, last_name = p_last_name WHERE id = p_id; " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(updateProcedureSql);
                System.out.println("Update Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createDeleteStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String deleteProcedureSql = "CREATE OR REPLACE FUNCTION delete_user(IN p_id BIGINT) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    DELETE FROM users WHERE id = p_id; " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(deleteProcedureSql);
                System.out.println("Delete Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

