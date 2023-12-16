package com.example.database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
@PropertySource("classpath:application.yml")
public class DatabaseSamplingData {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @PostConstruct
    public void printOutNamesByCondition(){

        String query = "SELECT DISTINCT u.first_name, u.last_name " +
                "FROM users u " +
                "JOIN friendships f ON u.id = f.userid1 OR u.id = f.userid2 " +
                "JOIN likes l ON u.id = l.userid AND EXTRACT(YEAR FROM l.timestamp) = 2025 " +
                "WHERE (f.userid1 = u.id OR f.userid2 = u.id) " +
                "GROUP BY u.id " +
                "HAVING COUNT(DISTINCT f.userid1) + COUNT(DISTINCT f.userid2) > 100 " +
                "AND COUNT(DISTINCT l.postid) > 100";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(query);

        if (result.isEmpty()) {
            System.out.println("No users found matching the criteria.");
        } else {
            System.out.println("Users with more than 100 friends and 100 likes in March 2025:");
            for (Map<String, Object> row : result) {
                String firstName = (String) row.get("first_name");
                String lastName = (String) row.get("last_name");
                System.out.println(firstName + " " + lastName);
            }
        }
    }
}
