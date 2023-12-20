package com.example.storedprocedure;

import com.example.DatabaseConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@Component
public class StoredProcedureUser {

    @Autowired
    private DatabaseConfig databaseConfig;

    @PostConstruct
    public void dropAllUserStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            connection.setAutoCommit(true);

            String dropInsertProcedureSql = "DROP FUNCTION IF EXISTS insert_users(BIGINT, VARCHAR(255), VARCHAR(255), TIMESTAMP);";
            String dropUpdateProcedureSql = "DROP FUNCTION IF EXISTS update_users(BIGINT, VARCHAR(255), VARCHAR(255), TIMESTAMP);";
            String dropDeleteProcedureSql = "DROP FUNCTION IF EXISTS delete_user(BIGINT, VARCHAR(255), VARCHAR(255), TIMESTAMP);";

            try (Statement statement = connection.createStatement()) {
                statement.execute(dropInsertProcedureSql);
                statement.execute(dropUpdateProcedureSql);
                statement.execute(dropDeleteProcedureSql);
                System.out.println("All User Stored procedure dropped successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createInsertStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String createProcedureSql = "CREATE OR REPLACE FUNCTION insert_users(IN p_id BIGINT, IN p_first_name VARCHAR(255), IN p_last_name VARCHAR(255), IN p_birth_date TIMESTAMP) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    INSERT INTO users (id, first_name, last_name, birth_date) VALUES (p_id, p_first_name, p_last_name, p_birth_date); " +
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
            String updateProcedureSql = "CREATE OR REPLACE FUNCTION update_users(IN p_id BIGINT, IN p_first_name VARCHAR(255), IN p_last_name VARCHAR(255), IN p_birth_date TIMESTAMP) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    UPDATE users SET first_name = p_first_name, last_name = p_last_name, birth_date = p_birth_date WHERE id = p_id; " +
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

