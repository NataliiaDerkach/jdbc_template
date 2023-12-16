package com.example.database;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
@Component
@PropertySource("classpath:application.yml")
public class DataBaseInsertData {

    private static final Logger log = LoggerFactory.getLogger(DataBaseCreating.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void insertUserRandomData() {
        try {
            // Генерация и добавление 1000 пользователей
            Faker faker = new Faker(new Locale("en"));
            String insertQuery = "INSERT INTO users (id, first_name, last_name, birth_date) VALUES (?, ?, ?, ?)";

            for (int i = 0; i < 1000; i++) {
                // Генерация случайных данных
                long userId = i + 1;
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                LocalDate birthDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Вставка данных с использованием jdbcTemplate
                jdbcTemplate.update(insertQuery, userId, firstName, lastName, birthDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("--------Inserted Users Data--------");

    }


    public void insertFriendshipsData() {
        try {
            // Генерация и добавление 70 тысяч дружб
            String insertQuery = "INSERT INTO friendships (userid1, userid2, timestamp) VALUES (?, ?, ?)";

            for (int i = 0; i < 70000; i++) {
                // Генерация случайных данных
                long userId1 = ThreadLocalRandom.current().nextLong(1, 1001); // Используйте 1001, так как генерация включает минимальное значение, но не максимальное
                long userId2 = ThreadLocalRandom.current().nextLong(1, 1001);

                // Генерация случайной временной метки
                LocalDateTime timestamp = LocalDateTime.now();

                // Вставка данных с использованием jdbcTemplate
                jdbcTemplate.update(insertQuery, userId1, userId2, timestamp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("--------Inserted friendships Data--------");
    }

    public void insertPostsData() {
        try {
            Faker faker = new Faker(new Locale("en"));
            for (int i = 0; i < 300000; i++) {
                // Генерация случайных данных
                long userId = ThreadLocalRandom.current().nextLong(1, 1001);
                String text = faker.lorem().sentence();
                LocalDateTime timestamp = LocalDateTime.now();

                // Вставка данных с использованием jdbcTemplate
                jdbcTemplate.update("INSERT INTO posts (id, userid, text, timestamp) VALUES (?, ?, ?, ?)",
                        i + 1, userId, text, timestamp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("--------Inserted Posts Data--------");
    }

    public void insertLikesData(){
        try{
            for (int i = 0; i < 300000; i++) {
                // Генерация случайных данных
                long postId = ThreadLocalRandom.current().nextLong(1, 300000 + 1);
                long userId = ThreadLocalRandom.current().nextLong(1, 1001);
                LocalDateTime timestamp = LocalDateTime.of(2025, ThreadLocalRandom.current().nextInt(1, 13), ThreadLocalRandom.current().nextInt(1, 29), ThreadLocalRandom.current().nextInt(0, 24), ThreadLocalRandom.current().nextInt(0, 60), ThreadLocalRandom.current().nextInt(0, 60));


                // Проверка, что лайк сделан в 2025 году
                if (timestamp.getYear() != 2025) {
                    // Если лайк не сделан в 2025 году, пропустить текущую итерацию
                    continue;
                }

                // Вставка данных с использованием jdbcTemplate
                jdbcTemplate.update("INSERT INTO likes (postid, userid, timestamp) VALUES (?, ?, ?)",
                        postId, userId, timestamp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        log.info("--------Inserted Likes Data--------");
    }
}

