package com.example;

import com.example.database.DataBaseCreating;
import com.example.database.DataBaseInsertData;
import com.example.database.DatabaseSamplingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseConfig.class)
@ComponentScan("com.example")
public class DemoApplication implements CommandLineRunner {

    static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    DataBaseCreating dataBaseCreating;
    @Autowired
    DataBaseInsertData dataBaseInsertData;
    @Autowired
    DatabaseSamplingData databaseSamplingData;
    @Autowired
    DatabasePopulator databasePopulator;

    @Autowired
    @Qualifier("databaseConfig")
    DatabaseConfig databaseConfig;

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        dataBaseCreating.createTables();

        dataBaseInsertData.insertUserRandomData();

        dataBaseInsertData.insertFriendshipsData();

        dataBaseInsertData.insertPostsData();

        dataBaseInsertData.insertLikesData();

        databaseSamplingData.printOutNamesByCondition();


        log.info("Executing randon table");

        databasePopulator.createRandomTables();

        log.info("Starting database population...");

//        // Output the current database configuration
//        log.info("Database Configuration: {}", databaseConfig);

        databasePopulator.createRandomTables();
        log.info("Random tables created successfully.");


        log.info("Random data populated successfully.");

        log.info("Database population completed.");
    }
}
