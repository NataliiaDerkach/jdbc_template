package com.example.storedprocedure;

import com.example.DatabaseConfig;
import com.example.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class LikesDao {

    @Autowired
    private DatabaseConfig databaseConfig;
    static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public void executeInsertLikes(int postid, int userid, LocalDateTime timestamp) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call insert_likes(?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, postid);
                callableStatement.setInt(2, userid);
                callableStatement.setTimestamp(3, Timestamp.valueOf(timestamp));
                callableStatement.execute();
                log.info("Likes inserted successfully");
            }
        } catch (SQLException e) {
            log.error("Error inserting likes", e);
        }
    }

    public void executeUpdateLikes(int postid, int userid, LocalDateTime timestamp) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call update_likes(?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, postid);
                callableStatement.setInt(2, userid);
                callableStatement.setTimestamp(3, Timestamp.valueOf(timestamp));
                callableStatement.execute();
                log.info("Likes updated successfully");
            }
        } catch (SQLException e) {
            log.error("Error updating likes", e);
        }
    }

    public void executeDeleteLikes(int postid) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call delete_likes(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, postid);
                callableStatement.execute();
                log.info("Likes deleted successfully");
            }
        } catch (SQLException e) {
            log.error("Error deleting likes", e);
        }
    }
}
