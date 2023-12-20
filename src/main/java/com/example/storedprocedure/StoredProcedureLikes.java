package com.example.storedprocedure;

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
public class StoredProcedureLikes {


    @Autowired
    private DatabaseConfig databaseConfig;

    @PostConstruct
    public void dropAllLikesStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            connection.setAutoCommit(true);

            String dropInsertLikesProcedureSql = "DROP FUNCTION IF EXISTS insert_likes(BIGINT, BIGINT, TIMESTAMP);";
            String dropUpdateLikesProcedureSql = "DROP FUNCTION IF EXISTS update_likes(BIGINT, BIGINT, TIMESTAMP);";
            String dropDeleteLikesProcedureSql = "DROP FUNCTION IF EXISTS delete_likes(BIGINT, BIGINT, TIMESTAMP);";

            try (Statement statement = connection.createStatement()) {
                statement.execute(dropInsertLikesProcedureSql);
                statement.execute(dropUpdateLikesProcedureSql);
                statement.execute(dropDeleteLikesProcedureSql);
                System.out.println("All Likes Stored procedure dropped successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createInsertLikesStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String createProcedureSql = "CREATE OR REPLACE FUNCTION insert_likes(IN p_postid BIGINT, IN p_userid BIGINT, IN p_timestamp TIMESTAMP) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    INSERT INTO likes (postid, userid, timestamp) VALUES (p_postid, p_userid, p_timestamp); " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(createProcedureSql);
                System.out.println("Insert Likes Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createUpdateLikesStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String updateProcedureSql = "CREATE OR REPLACE FUNCTION update_likes(IN p_postid BIGINT, IN p_userid BIGINT, IN p_timestamp TIMESTAMP) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    UPDATE likes SET userid = p_userid, timestamp = p_timestamp WHERE postid = p_postid; " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(updateProcedureSql);
                System.out.println("Update Likes Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void createDeleteLikesStoredProcedure() {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String deleteProcedureSql = "CREATE OR REPLACE FUNCTION delete_likes(IN p_postid BIGINT) " +
                    "RETURNS VOID AS $$ " +
                    "BEGIN " +
                    "    DELETE FROM likes WHERE postid = p_postid; " +
                    "END; " +
                    "$$ LANGUAGE plpgsql;";

            try (Statement statement = connection.createStatement()) {
                statement.execute(deleteProcedureSql);
                System.out.println("Delete Likes Stored procedure created successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
