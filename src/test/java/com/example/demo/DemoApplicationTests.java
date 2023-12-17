package com.example.demo;

import com.example.storedprocedur.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testDeleteUser() {
		userDao.executeInsertUser(1009, "Alisa", "Dark");
		userDao.executeDeleteUser(1009);

		String selectQuery = "SELECT * FROM users WHERE id = 1009";
		List<User> users = jdbcTemplate.query(selectQuery, (resultSet, rowNum) ->
				new User(
						resultSet.getInt("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name")
				)
		);

		assertEquals(0, users.size());
	}

	@Test
	void testInsertUser() {
		userDao.executeDeleteUser(1008);
		userDao.executeInsertUser(1008, "Kira", "Yang");

		String selectQuery = "SELECT * FROM users WHERE id = 1008";
		User insertedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
				new User(
						resultSet.getInt("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name")
				)
		);

		assertEquals(1008, insertedUser.getId());
		assertEquals("Kira", insertedUser.getFirstName());
		assertEquals("Yang", insertedUser.getLastName());
	}

	@Test
	void testUpdateUser() {
		userDao.executeInsertUser(1002, "John", "Doe");
		userDao.executeUpdateUser(1002, "UpdatedJohn", "UpdatedDoe");

		String selectQuery = "SELECT * FROM users WHERE id = 1002";
		User updatedUser = jdbcTemplate.queryForObject(selectQuery, (resultSet, rowNum) ->
				new User(
						resultSet.getInt("id"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name")
				)
		);

		assertEquals(1002, updatedUser.getId());
		assertEquals("UpdatedJohn", updatedUser.getFirstName());
		assertEquals("UpdatedDoe", updatedUser.getLastName());
	}


}
