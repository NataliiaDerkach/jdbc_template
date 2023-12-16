package com.example;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class ColumnTypesDictionary {

    private static final List<String> TYPE_DICTIONARY = Arrays.asList(
            "VARCHAR(255)", "BIGINT", "TEXT", "TIMESTAMP", "DATE", "INT", "BOOLEAN"
            // Добавьте сюда другие возможные типы столбцов
    );

    public static String getRandomColumnType() {
        Random random = new Random();
        int index = random.nextInt(TYPE_DICTIONARY.size());
        return TYPE_DICTIONARY.get(index);
    }
}
