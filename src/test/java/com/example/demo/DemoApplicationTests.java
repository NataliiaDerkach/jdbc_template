package com.example.demo;

import com.example.storedprocedur.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserDao userDao;

    LocalDate birthDate;
    LocalDate updateBirthDate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testDeleteUser() {
        birthDate = LocalDate.of(1995, 5, 20);

        userDao.executeInsertUser(1009, "Alisa", "Dark", birthDate);
        userDao.executeDeleteUser(1009);

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
        birthDate = LocalDate.of(1998, 5, 20);

        userDao.executeDeleteUser(1008);
        userDao.executeInsertUser(1008, "Kira", "Yang", birthDate);

        String selectQuery = "SELECT * FROM users WHERE id = 1008";
        User insertedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                )
        );

        assertEquals(1008, insertedUser.getId());
        assertEquals("Kira", insertedUser.getFirstName());
        assertEquals("Yang", insertedUser.getLastName());
        assertEquals(birthDate, insertedUser.getBirthDate());
    }

    @Test
    void testUpdateUser() {

        birthDate = LocalDate.of(1985, 5, 20);

        updateBirthDate = LocalDate.of(1988, 8, 28);

        userDao.executeInsertUser(1002, "John", "Doe", birthDate);
        userDao.executeUpdateUser(1002, "UpdatedJohn", "UpdatedDoe", updateBirthDate);

        String selectQuery = "SELECT * FROM users WHERE id = 1002";
        User updatedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
                new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate()
                )
        );

        assertEquals(1002, updatedUser.getId());
        assertEquals("UpdatedJohn", updatedUser.getFirstName());
        assertEquals("UpdatedDoe", updatedUser.getLastName());
        assertEquals(updateBirthDate, updatedUser.getBirthDate());
    }


}
