package com.example.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class DataBaseCreating {

    private static final Logger log = LoggerFactory.getLogger(DataBaseCreating.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTables() {

        log.info("-----------Creating table Users-------------");

        jdbcTemplate.update("DROP TABLE IF EXISTS users CASCADE");
        jdbcTemplate.execute("CREATE TABLE users (" +
                "id BIGINT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), birth_date TIMESTAMP)");


        log.info("-----------Creating table Friendships-------------");

        jdbcTemplate.execute("DROP TABLE IF EXISTS friendships CASCADE");
        jdbcTemplate.execute("CREATE TABLE friendships (" +
                "userid1 BIGINT, userid2 BIGINT, timestamp TIMESTAMP, " +
                "FOREIGN KEY (userid1) REFERENCES users(id), " +
                "FOREIGN KEY (userid2) REFERENCES users(id))");


        log.info("-----------Creating table Posts-------------");

        jdbcTemplate.execute("DROP TABLE IF EXISTS posts CASCADE");
        jdbcTemplate.execute("CREATE TABLE posts (" +
                "id BIGINT PRIMARY KEY," +
                "userid BIGINT," +
                "text VARCHAR(1000)," +
                "timestamp TIMESTAMP," +
                "FOREIGN KEY (userid) REFERENCES users(id)" +
                ")");


        log.info("-----------Creating table Likes-------------");

        jdbcTemplate.execute("DROP TABLE IF EXISTS likes CASCADE");
        jdbcTemplate.execute("CREATE TABLE likes (" +
                "postid BIGINT," +
                "userid BIGINT," +
                "timestamp TIMESTAMP," +
                "FOREIGN KEY (postid) REFERENCES posts(id)," +
                "FOREIGN KEY (userid) REFERENCES users(id)" +
                ")");
    }
}
