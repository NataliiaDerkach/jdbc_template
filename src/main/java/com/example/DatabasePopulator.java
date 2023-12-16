package com.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.*;

import static com.example.DemoApplication.log;


@Component
@PropertySource("classpath:application.yml")
public class DatabasePopulator {
    @Autowired
    private DatabaseConfig databaseConfig;
    @Autowired
    private TableNamesDictionary tableNamesDictionary;
    @Autowired
    private ColumnNamesDictionary columnNamesDictionary;
    @Autowired
    private ColumnTypesDictionary columnTypesDictionary;

    String columnName;
    String columnType;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    public void createRandomTables() {
        for (int i = 0; i < databaseConfig.getNumTables(); i++) {
            String tableName = tableNamesDictionary.getRandomTableName();
            int numColumns = (int) (Math.random() * databaseConfig.getMaxColumns()) + 1;

            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
            StringBuilder dropTable = new StringBuilder("DROP TABLE IF EXISTS " + tableName + " CASCADE");
            Set<String> usedColumnNames = new HashSet<>();
            for (int j = 0; j < numColumns; j++) {
                // Keep generating a new column name until a unique one is found
                do {
                    columnName = columnNamesDictionary.getRandomColumnName();
                } while (usedColumnNames.contains(columnName));

                usedColumnNames.add(columnName);

                columnType = getColumnType(columnName);

                createTableQuery.append(columnName).append(" ").append(columnType);
                if (j < numColumns - 1) {
                    createTableQuery.append(", ");
                }
            }

            createTableQuery.append(")");

            jdbcTemplate.update(dropTable.toString());
            jdbcTemplate.update(createTableQuery.toString());

            int numRows = databaseConfig.getRowsArray().get(i);

            // Insert m random rows for the i-th table
            for (int k = 0; k < numRows; k++) {
                insertRandomRow(tableName);
            }

        }
    }


    public String getColumnType(String columnName) {
        if ("timestamp".equalsIgnoreCase(columnName)) {
            return columnType = "TIMESTAMP";
        } else if ("birthdate".equalsIgnoreCase(columnName)) {
            return columnType = "DATE";
        } else if (columnName.contains("id") || "id".equalsIgnoreCase(columnName)) {
            return columnType = "BIGINT";
        } else {
            return columnType = "VARCHAR(255)";
        }
    }

    private void insertRandomRow(String tableName) {
        StringBuilder insertRowQuery = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        List<String> columnNames = getColumnNamesForTable(tableName);

        for (int i = 0; i < columnNames.size(); i++) {
            insertRowQuery.append(columnNames.get(i));
            values.append(getRandomColumnValue(columnNames.get(i)));
            if (i < columnNames.size() - 1) {
                insertRowQuery.append(", ");
                values.append(", ");
            }
        }

        insertRowQuery.append(") ");
        values.append(")");

        jdbcTemplate.update(insertRowQuery.toString() + values.toString());
        log.info("Insert into " + tableName + " and column name " + columnName);
    }

    private List<String> getColumnNamesForTable(String tableName) {
        return jdbcTemplate.queryForList(
                "SELECT column_name FROM information_schema.columns " +
                        "WHERE table_name = ? AND table_schema = 'public'",
                String.class, tableName);
    }

    private String getRandomColumnValue(String columnName) {
        if ("timestamp".equalsIgnoreCase(columnName)) {
            return "'2023-12-15 12:00:00'";
        } else if ("birthdate".equalsIgnoreCase(columnName)) {
            return "'1990-01-01'";
        } else if (columnName.contains("id") || "id".equalsIgnoreCase(columnName)) {
            return String.valueOf(new Random().nextInt(1000) + 1);
        } else {
            return "'Random Text'";
        }
    }
}
