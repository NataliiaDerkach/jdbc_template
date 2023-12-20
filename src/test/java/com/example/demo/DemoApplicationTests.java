package com.example.demo;

import com.example.storedprocedure.LikesDao;
import com.example.storedprocedure.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LikesDao likesDao;

    private LocalDate birthDate;
    private LocalDate updateBirthDate;

    private LocalDateTime timestamp;
    private int postid;

    private int userid;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testDeleteUser() {
        userid = 1009;
        birthDate = LocalDate.of(1995, 5, 20);

        userDao.executeInsertUser(userid, "Alisa", "Dark", birthDate);
        userDao.executeDeleteUser(userid);

        String selectQuery = "SELECT * FROM users WHERE id = 1009";
        List<User> users = jdbcTemplate.query(selectQuery, (resultSet, rowNum) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                )
        );

        assertEquals(0, users.size());
    }

    @Test
    void testInsertUser() {
        userid = 1008;
        birthDate = LocalDate.of(1998, 5, 20);

        userDao.executeDeleteUser(userid);
        userDao.executeInsertUser(userid, "Kira", "Yang", birthDate);

        String selectQuery = "SELECT * FROM users WHERE id = 1008";
        User insertedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                )
        );
        assertNotNull(insertedUser);
        assertEquals(userid, insertedUser.getId());
        assertEquals("Kira", insertedUser.getFirstName());
        assertEquals("Yang", insertedUser.getLastName());
        assertEquals(birthDate, insertedUser.getBirthDate());

        System.out.println("Inserted User Information:");
        System.out.println(" ID: " + insertedUser.getId());
        System.out.println("User name: " + insertedUser.getFirstName());
        System.out.println("User last name: " + insertedUser.getLastName());
        System.out.println("User birth date: " + insertedUser.getBirthDate());
    }

    @Test
    void testUpdateUser() {
        userid = 1002;
        birthDate = LocalDate.of(1985, 5, 20);
        updateBirthDate = LocalDate.of(1988, 8, 28);

        userDao.executeDeleteUser(userid);
        userDao.executeInsertUser(userid, "John", "Doe", birthDate);
        userDao.executeUpdateUser(userid, "UpdatedJohn", "UpdatedDoe", updateBirthDate);

        String selectQuery = "SELECT * FROM users WHERE id = 1002";
        User updatedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                )
        );

        assertNotNull(updatedUser);
        assertEquals(1002, updatedUser.getId());
        assertEquals("UpdatedJohn", updatedUser.getFirstName());
        assertEquals("UpdatedDoe", updatedUser.getLastName());
        assertEquals(updateBirthDate, updatedUser.getBirthDate());

        System.out.println("Updated User Information:");
        System.out.println(" ID: " + updatedUser.getId());
        System.out.println("User name: " + updatedUser.getFirstName());
        System.out.println("User last name: " + updatedUser.getLastName());
        System.out.println("User birth date: " + updatedUser.getBirthDate());
    }

    @Test
    void testInsertLikes() {
        postid = 1005;
        timestamp = LocalDateTime.now();
        likesDao.executeDeleteLikes(postid);
        likesDao.executeInsertLikes(postid, 506, timestamp);

        String selectQuery = "SELECT * FROM likes WHERE postid = 1005";
        Likes insertedLikes = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new Likes(
                        resultSet.getInt("postid"),
                        resultSet.getInt("userid"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime()
                )
        );
        assertNotNull(insertedLikes);
        assertEquals(postid, insertedLikes.getPostid());
        assertEquals(506, insertedLikes.getUserid());
        assertEquals(timestamp, insertedLikes.getTimestamp());

        System.out.println("Inserted Likes Information:");
        System.out.println("Post ID: " + insertedLikes.getPostid());
        System.out.println("User ID: " + insertedLikes.getUserid());
        System.out.println("Timestamp: " + insertedLikes.getTimestamp());

    }

    @Test
    void testUpdateLikes() {
        postid = 1008;
        timestamp = LocalDateTime.of(2023, 1, 1, 12, 0);
        likesDao.executeDeleteLikes(postid);
        likesDao.executeInsertLikes(postid, 440, timestamp);
        likesDao.executeUpdateLikes(postid, 222, timestamp);

        String selectQuery = "SELECT * FROM likes WHERE postid = 1008";
        Likes updatedLikes = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new Likes(
                        resultSet.getInt("postid"),
                        resultSet.getInt("userid"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime()
                )
        );

        assertNotNull(updatedLikes);
        assertEquals(postid, updatedLikes.getPostid());
        assertEquals(222, updatedLikes.getUserid());
        assertEquals(timestamp, updatedLikes.getTimestamp());

        System.out.println("Updated Likes Information:");
        System.out.println("Post ID: " + updatedLikes.getPostid());
        System.out.println("User ID: " + updatedLikes.getUserid());
        System.out.println("Timestamp: " + updatedLikes.getTimestamp());
    }
}
