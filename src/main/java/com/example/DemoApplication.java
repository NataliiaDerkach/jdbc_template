package com.example;

import com.example.database.DataBaseCreating;
import com.example.database.DataBaseInsertData;
import com.example.database.DatabaseSamplingData;
import com.example.storedprocedure.StoredProcedureUser;
import com.example.storedprocedure.StoredProcedureLikes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;


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
    StoredProcedureUser storedProcedureUser;

    @Autowired
    StoredProcedureLikes storedProcedureLikes;


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        //Creating simple database with tables Users, Friendships, Posts, Likes
        dataBaseCreating.createTables();

        dataBaseInsertData.insertUserRandomData();

        dataBaseInsertData.insertFriendshipsData();

        dataBaseInsertData.insertPostsData();

        dataBaseInsertData.insertLikesData();

        databaseSamplingData.printOutNamesByCondition();


        //Highload Writing Console Tool that populates the database
        log.info("Executing randon table");
        databasePopulator.createRandomTables();
        log.info("Database population completed.");


        //Executing Store Procedures

        storedProcedureUser.dropAllUserStoredProcedure();
        storedProcedureUser.createInsertStoredProcedure();
        storedProcedureUser.createUpdateStoredProcedure();
        storedProcedureUser.createDeleteStoredProcedure();

        storedProcedureLikes.dropAllLikesStoredProcedure();
        storedProcedureLikes.createInsertLikesStoredProcedure();
        storedProcedureLikes.createUpdateLikesStoredProcedure();
        storedProcedureLikes.createDeleteLikesStoredProcedure();
    }
}
