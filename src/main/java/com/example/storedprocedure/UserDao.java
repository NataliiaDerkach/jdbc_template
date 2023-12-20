package com.example.storedprocedure;

import com.example.DatabaseConfig;
import com.example.DemoApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class UserDao {


    @Autowired
    private DatabaseConfig databaseConfig;
    static final Logger log = LoggerFactory.getLogger(DemoApplication.class);


    public void executeInsertUser(int id, String firstName, String lastName, LocalDate birthDate) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call insert_users(?, ?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, id);
                callableStatement.setString(2, firstName);
                callableStatement.setString(3, lastName);
                callableStatement.setDate(4, java.sql.Date.valueOf(birthDate));
                callableStatement.execute();
                log.info("User inserted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdateUser(int id, String firstName, String lastName, LocalDate birthDate) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call update_users(?, ?, ?, ?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, id);
                callableStatement.setString(2, firstName);
                callableStatement.setString(3, lastName);
                callableStatement.setDate(4, java.sql.Date.valueOf(birthDate));
                callableStatement.execute();
                log.info("User updated successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeDeleteUser(int id) {
        try (Connection connection = DriverManager.getConnection(databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {
            String storedProcedureCall = "{call delete_user(?)}";
            try (CallableStatement callableStatement = connection.prepareCall(storedProcedureCall)) {
                callableStatement.setInt(1, id);
                callableStatement.execute();
                log.info("User deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
