package com.example;


import java.util.Arrays;
import java.util.List;


import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {

    private String url;
    private String username;
    private String password;
    private int numTables;
    private int maxColumns;
    private List<Integer> rowsArray;
    private int numConnections;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumTables() {
        return numTables;
    }

    public void setNumTables(int numTables) {
        this.numTables = numTables;
    }

    public int getMaxColumns() {
        return maxColumns;
    }

    public void setMaxColumns(int maxColumns) {
        this.maxColumns = maxColumns;
    }

    public List<Integer> getRowsArray() {
        return rowsArray;
    }

    public void setRowsArray(List<Integer> rowsArray) {
        this.rowsArray = rowsArray;
    }

    public int getNumConnections() {
        return numConnections;
    }

    public void setNumConnections(int numConnections) {
        this.numConnections = numConnections;
    }
}
